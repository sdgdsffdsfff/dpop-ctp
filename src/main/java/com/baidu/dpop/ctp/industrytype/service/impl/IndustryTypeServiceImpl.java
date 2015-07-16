/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.industrytype.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.dpop.ctp.industrytype.bo.IndustryType;
import com.baidu.dpop.ctp.industrytype.bo.IndustryTypeTreeNode;
import com.baidu.dpop.ctp.industrytype.bo.NewIndustryType;
import com.baidu.dpop.ctp.industrytype.dao.IndustryTypeDao;
import com.baidu.dpop.ctp.industrytype.dao.NewIndustryTypeDao;
import com.baidu.dpop.ctp.industrytype.service.IndustryTypeService;

@Service
public class IndustryTypeServiceImpl implements IndustryTypeService {

    @Autowired
    private NewIndustryTypeDao newIndustryTypeDao;

    @Autowired
    private IndustryTypeDao industryTypeDao;

    @Override
    public Map<Integer, IndustryTypeTreeNode> getLevelThreeIndustryType() {
        List<NewIndustryType> typeList = null;
        typeList = this.newIndustryTypeDao.selectLevel3Data();

        if (CollectionUtils.isEmpty(typeList)) {
            return new HashMap<Integer, IndustryTypeTreeNode>();
        }

        int nowFirstType = -1;
        int nowSecondType = -1;

        Map<Integer, IndustryTypeTreeNode> result = new HashMap<Integer, IndustryTypeTreeNode>();

        IndustryTypeTreeNode firstNode = null; // 用以暂存第一级行业
        IndustryTypeTreeNode secondNode = null; // 用以暂存第二级行业
        IndustryTypeTreeNode thirdNode = null; // 用以暂存第三级行业

        boolean firstChangeFlag;

        for (NewIndustryType type : typeList) {
            firstChangeFlag = false;
            if (type.getFirstId() != nowFirstType) { // 一级行业不同的时候

                nowFirstType = type.getFirstId();
                if (firstNode != null) {
                    firstChangeFlag = true;
                    result.put(firstNode.getId(), firstNode);
                }
                firstNode = new IndustryTypeTreeNode();
                firstNode.setId(type.getFirstId());
                firstNode.setName(type.getFirstName());
            }

            if (type.getSecondId() != nowSecondType || firstChangeFlag) { // 二级行业不同的时候
                nowSecondType = type.getSecondId();
                secondNode = new IndustryTypeTreeNode();
                secondNode.setId(type.getSecondId());
                secondNode.setName(type.getSecondName());
                firstNode.addChild(secondNode.getId(), secondNode);
            }

            // 每条记录都是一个独立的三级行业
            thirdNode = new IndustryTypeTreeNode();
            thirdNode.setId(type.getFullId());
            thirdNode.setName(type.getThirdName());
            secondNode.addChild(thirdNode.getId(), thirdNode);
        }

        if (firstNode != null) {
            // 最后一个一级行业需要手动放入结果中
            result.put(firstNode.getId(), firstNode);
        }

        return result;
    }

    @Override
    public Map<Integer, String> getLevelTwoIndustryType() {
        List<IndustryType> typeList = null;
        typeList = this.industryTypeDao.selectLevel2Data();

        if (CollectionUtils.isEmpty(typeList)) {
            return new HashMap<Integer, String>();
        }

        Map<Integer, String> result = new HashMap<Integer, String>();

        for (IndustryType type : typeList) {
            result.put(type.getFullId(), type.getFullName());
        }

        return result;
    }

    @Override
    public List<NewIndustryType> getLevel3Data() {
        return this.newIndustryTypeDao.selectLevel3Data();
    }

    @Override
    public Map<Integer, NewIndustryType> batchGetMapped(List<Integer> list) {
        Map<Integer, NewIndustryType> result = new HashMap<Integer, NewIndustryType>();
        for (NewIndustryType type : this.newIndustryTypeDao.batchSelect(list)) {
            result.put(type.getFullId(), type);
        }
        return result;
    }

    @Override
    public List<Integer> getAllFirstTradeType() {
        return this.newIndustryTypeDao.selectAllFirstTradeType();
    }
    
    @Override
    public void batchUpdateNewIndustryType(List<String> list) {
        List<NewIndustryType> update = new ArrayList<NewIndustryType>(1000);
        int k = 0;
        for (String line : list) {
            String[] args = line.split("\t");
            NewIndustryType type = new NewIndustryType();
            type.setFullId(Integer.valueOf(args[0]));
            type.setLevel2Id(Integer.valueOf(args[1]));
            type.setLevel2Name(args[2]);
            update.add(type);
            k++;

            if (k == 1000) {
                this.newIndustryTypeDao.batchUpdateLevel2IdAndName(update);
                update = new ArrayList<NewIndustryType>(1000);
            }
        }

        if (update.size() > 0) {
            this.newIndustryTypeDao.batchUpdateLevel2IdAndName(update);
        }
    }

}
