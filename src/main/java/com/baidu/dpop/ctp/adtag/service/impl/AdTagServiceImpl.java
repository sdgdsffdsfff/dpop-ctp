/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.adtag.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.dpop.ctp.adtag.bo.AdTag;
import com.baidu.dpop.ctp.adtag.dao.AdTagDao;
import com.baidu.dpop.ctp.adtag.service.AdTagService;
import com.baidu.dpop.ctp.adtag.service.TagTypeService;
import com.baidu.dpop.ctp.adtag.vo.AdTagAssignedVo;
import com.baidu.dpop.ctp.adtag.vo.AdTagGetVo;
import com.baidu.dpop.ctp.adtag.vo.AdTagSubmitVo;
import com.baidu.dpop.ctp.task.bo.GeneralTask;
import com.baidu.dpop.ctp.task.service.GeneralTaskService;
import com.baidu.dpop.ctp.task.vo.GeneralTaskQueryVo;
import com.baidu.dpop.ctp.user.bo.User;

@Service
public class AdTagServiceImpl extends BaseService<AdTag, Long> implements AdTagService {

    @Autowired
    private AdTagDao adtagDao;

    @Autowired
    private TagTypeService tagTypeService;

    @Autowired
    private GeneralTaskService generalTaskService;

    @Override
    public GenericMapperDao<AdTag, Long> getDao() {
        return adtagDao;
    }

    @Override
    public List<AdTag> getByGroup(Long groupId) {
        return this.adtagDao.selectByGroup(groupId);
    }

    @Override
    public AdTag getByRefId(Long refId, Number dataType) {
        AdTagGetVo vo = new AdTagGetVo();
        vo.setDataType(dataType.intValue());
        vo.setRefId(refId);
        return this.adtagDao.selectByRefId(vo);
    }

    @Override
    public List<AdTag> batchGet(AdTagGetVo vo) {
        return this.adtagDao.batchSelect(vo);
    }
    
    @Override
    public Set<Long> getTestedAdTagByRefId(List<Long> list, Byte dataType) {
        return this.adtagDao.selectTestedAdTagByRefId(list, dataType);
    }

    @Override
    public Map<Long, AdTag> getAdTagMap(List<Long> refAdIdList, Integer dataType) {
        Map<Long, AdTag> ret = new HashMap<Long, AdTag>();

        // 输入参数有误
        if (CollectionUtils.isEmpty(refAdIdList) || dataType == null) {
            return ret;
        }

        AdTagGetVo vo = new AdTagGetVo();
        vo.setList(refAdIdList);
        vo.setDataType(dataType);
        List<AdTag> adTagList = this.batchGet(vo);
        if (CollectionUtils.isNotEmpty(adTagList)) {
            for (AdTag item : adTagList) {
                ret.put(item.getRefId(), item);
            }
        }

        return ret;
    }

    @Override
    public Map<Long, AdTag> getMapedTagedtags(GeneralTaskQueryVo vo) {
        List<AdTag> list = this.getTagedtags(vo);
        Map<Long, AdTag> map = new HashMap<Long, AdTag>();
        for (AdTag tag : list) {
            map.put(tag.getRefId(), tag);
        }
        return map;
    }

    @Override
    public List<AdTag> getTagedtags(GeneralTaskQueryVo vo) {
        return this.adtagDao.selectTagedtags(vo);
    }

    @Override
    public List<String> getAdTag(Integer taskId, Integer page, Integer size) {
        return this.adtagDao.selectAdTag(taskId, page, size);
    }

    @Override
    public Integer getTagedCount(GeneralTaskQueryVo vo) {
        return this.adtagDao.selectTagedCount(vo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void submit(List<AdTag> list, User user, Date time) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        Byte dataType = list.get(0).getDataType().byteValue();
        List<Long> refIds = new ArrayList<Long>();

        List<AdTag> insert = new ArrayList<AdTag>();
        List<AdTag> update = new ArrayList<AdTag>();

        for (AdTag tag : list) {
            refIds.add(tag.getRefId());
        }
        
        Set<Long> exists = this.getTestedAdTagByRefId(refIds, dataType);
        
        for (AdTag tag : list) {
            tag.setUpdateTime(time);
            tag.setUpdateUser(user.getUserName());
            if (exists.contains(tag.getRefId())) {
                update.add(tag);
            } else {
                insert.add(tag);
            }
        }
        
        

        if (!CollectionUtils.isEmpty(insert)) {
            List<Long> ids = new ArrayList<Long>();
            for (AdTag tag : insert) {
                ids.add(tag.getRefId());
            }
            Map<Long, GeneralTask> map = generalTaskService.batchGetMapped(ids, dataType);

            for (AdTag tag : insert) {
                tag.setGeneralWuliaoType(map.get(tag.getRefId()).getGeneralWuliaoType().intValue());
            }
            adtagDao.batchInsert(insert);
        }

        if (!CollectionUtils.isEmpty(update)) {
            AdTagSubmitVo vo = new AdTagSubmitVo();
            vo.setList(update);
            vo.setUpdateTime(time);
            vo.setUpdateUser(user.getUserName());
            adtagDao.batchUpdate(vo);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void assignedAdTags(Integer taskId, Integer dataType, List<Long> ids) {
        Assert.notNull(taskId);
        Assert.notNull(dataType);
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }

        AdTagAssignedVo vo = new AdTagAssignedVo();
        vo.setTaskId(taskId);
        vo.setDataType(dataType);
        vo.setIds(ids);
        this.adtagDao.updateAssigned(vo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void deleteAdDetail(Integer taskId) {
        Assert.notNull(taskId);
        this.adtagDao.deleteAdDetail(taskId);
    }
}
