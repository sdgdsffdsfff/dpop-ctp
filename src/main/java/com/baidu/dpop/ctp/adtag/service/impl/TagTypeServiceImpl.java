/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.adtag.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.dpop.ctp.adtag.bo.GeneralTag;
import com.baidu.dpop.ctp.adtag.bo.TagType;
import com.baidu.dpop.ctp.adtag.bo.TagTypeTreeNode;
import com.baidu.dpop.ctp.adtag.dao.TagTypeDao;
import com.baidu.dpop.ctp.adtag.service.TagTypeService;
import com.baidu.dpop.ctp.adtag.utils.AdTagUtils;
import com.baidu.dpop.ctp.adtag.utils.TaskTypeUtils;
import com.baidu.dpop.ctp.adtag.vo.TagTypeChangeResult;
import com.baidu.dpop.ctp.adtag.vo.TaskType;

@Service
public class TagTypeServiceImpl extends BaseService<TagType, Long> implements TagTypeService {

    @Autowired
    private TagTypeDao tagTypeDao;

    @Override
    public GenericMapperDao<TagType, Long> getDao() {
        return tagTypeDao;
    }

    @Override
    public Set<String> getByTradeId(Integer tradeId) {
        Assert.notNull(tradeId);
        if (tradeId < 100) {
            tradeId = tradeId * 10000;
        } else if (tradeId < 10000) {
            tradeId = tradeId * 100;
        }
        Set<String> result = this.tagTypeDao.selectByTradeId(tradeId);
        return result;
    }

    @Override
    public List<String> getByTradeIdList(List<Integer> tradeIds, Byte taskType) {
        Assert.notNull(taskType);
        TaskType type = TaskTypeUtils.getType(taskType);
        Assert.notNull(taskType);
        
        Set<String> result = null;
        if (!CollectionUtils.isEmpty(tradeIds)) {
            result = this.getByTradeId(tradeIds.get(0));
            for (Integer id : tradeIds) {
                result.retainAll(this.getByTradeId(id));
            }
        } else {
            result = new HashSet<String>();
        }

        for (GeneralTag tag : AdTagUtils.GENERALLIST) {
            result.add(tag.getName());
        }

        List<String> list = new ArrayList<String>();
        for (GeneralTag tag : AdTagUtils.TAGLIST) {
            if (result.contains(tag.getName()) && type.contains(tag)) {
                list.add(tag.getName());
            }
        }
        return list;
    }

    @Override
    public Map<Integer, TagTypeTreeNode> getAll() {
        List<TagType> list = this.tagTypeDao.selectAll();
        Map<Integer, TagTypeTreeNode> result = new HashMap<Integer, TagTypeTreeNode>();

        for (TagType tag : list) {
            put(tag, result);
        }

        return result;
    }

    @Override
    public List<TagType> getAllList() {
        return this.tagTypeDao.selectAll();
    }

    private void put(TagType tag, Map<Integer, TagTypeTreeNode> tree) {
        Integer tradeId = tag.getTradeId();
        if (tradeId == null) {
            return;
        }

        Integer first = (tradeId / 10000) * 10000;
        Integer second = (tradeId / 100) * 100;
        Integer third = tradeId;

        TagTypeTreeNode father;

        TagTypeTreeNode node = tree.get(first);
        if (node == null) {
            node = new TagTypeTreeNode();
            node.setId(first);
            tree.put(first, node);
        }

        if (((tradeId / 100) % 100) == 0) {
            // 一级行业
            node.addName(tag.getTagType());
            return;
        }

        father = node;
        node = node.getChild(second);
        if (node == null) {
            node = new TagTypeTreeNode();
            node.setId(second);
            father.putChild(second, node);
        }

        if ((tradeId % 100) == 0) {
            // 二级行业
            node.addName(tag.getTagType());
            return;
        }

        father = node;
        node = node.getChild(third);
        if (node == null) {
            node = new TagTypeTreeNode();
            node.setId(third);
            father.putChild(third, node);
        }

        node.addName(tag.getTagType());
    }

    @Override
    public void batchInsertTagTypes(List<String> list) {
        List<TagType> insert = new ArrayList<TagType>();
        for (String s : list) {
            String[] args = s.split("\t");
            TagType type = new TagType();
            type.setTagType(args[1]);
            type.setTradeId(Integer.valueOf(args[0]));
            insert.add(type);
        }
        this.tagTypeDao.batchInsertTagTypes(insert);
    }

    @Override
    public void batchInsertTagTypesByBo(List<TagType> list) {
        this.tagTypeDao.batchInsertTagTypes(list);
    }

    @Override
    public TagTypeChangeResult changeTradeType(Integer adTradeId, Integer taskType) {
        Set<String> tags = getByTradeId(adTradeId);
        return TaskTypeUtils.getSampleAndTagList(tags, taskType);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        this.tagTypeDao.deleteByIds(ids);
    }

}
