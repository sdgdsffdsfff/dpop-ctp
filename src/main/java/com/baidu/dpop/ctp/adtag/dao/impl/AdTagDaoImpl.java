package com.baidu.dpop.ctp.adtag.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.baidu.dpop.ctp.adtag.bo.AdTag;
import com.baidu.dpop.ctp.adtag.dao.AdTagDao;
import com.baidu.dpop.ctp.adtag.dao.mapper.AdTagMapper;
import com.baidu.dpop.ctp.adtag.vo.AdTagAssignedVo;
import com.baidu.dpop.ctp.adtag.vo.AdTagGetVo;
import com.baidu.dpop.ctp.adtag.vo.AdTagSubmitVo;
import com.baidu.dpop.ctp.task.vo.GeneralTaskQueryVo;
import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;

@Repository
public class AdTagDaoImpl extends BaseDao<AdTag, Long> implements AdTagDao {

    @Autowired
    private AdTagMapper adTagMapper;

    @Override
    public GenericMapper<AdTag, Long> getMapper() {
        return this.adTagMapper;
    }

    @Override
    public List<AdTag> selectByGroup(Long groupId) {
        Assert.notNull(groupId);
        return this.adTagMapper.selectByGroup(groupId);
    }

    @Override
    public AdTag selectByRefId(AdTagGetVo vo) {
        Assert.notNull(vo);
        return this.adTagMapper.selectByRefId(vo);
    }

    @Override
    public List<AdTag> batchSelect(AdTagGetVo vo) {
        Assert.notNull(vo);
        if (CollectionUtils.isEmpty(vo.getList())) {
            return new ArrayList<AdTag>();
        }
        return this.adTagMapper.batchSelect(vo);
    }

    @Override
    public Set<Long> selectTestedAdTagByRefId(List<Long> list, Byte dataType) {
        if (CollectionUtils.isEmpty(list)) {
            return new HashSet<Long>();
        }
        Assert.notNull(dataType);

        return this.adTagMapper.selectTestedAdTagByRefId(list, dataType);
    }

    @Override
    public List<AdTag> selectTagedtags(GeneralTaskQueryVo vo) {
        Assert.notNull(vo);
        return this.adTagMapper.selectTagedtags(vo);
    }

    @Override
    public List<String> selectAdTag(@Param("taskId") Integer taskId, @Param("page") Integer page,
            @Param("size") Integer size) {
        Assert.notNull(taskId);
        Assert.isTrue(page != null && page >= 0);
        Assert.isTrue(size != null && size > 0);
        return this.adTagMapper.selectAdTag(taskId, page, size);
    }

    @Override
    public Integer selectTagedCount(GeneralTaskQueryVo vo) {
        Assert.notNull(vo);
        return this.adTagMapper.selectTagedCount(vo);
    }

    @Override
    public int batchInsert(List<AdTag> list) {
        if (CollectionUtils.isEmpty(list)) {
            return 0;
        }
        return this.adTagMapper.batchInsert(list);
    }

    public void updateAssigned(AdTagAssignedVo vo) {
        Assert.notNull(vo);
        this.adTagMapper.updateAssigned(vo);
    }

    @Override
    public void batchUpdate(AdTagSubmitVo vo) {
        Assert.notNull(vo);
        this.adTagMapper.batchUpdate(vo);
    }

    @Override
    public void deleteAdDetail(Integer taskId) {
        Assert.notNull(taskId);
        this.adTagMapper.deleteAdDetail(taskId);
    }
}
