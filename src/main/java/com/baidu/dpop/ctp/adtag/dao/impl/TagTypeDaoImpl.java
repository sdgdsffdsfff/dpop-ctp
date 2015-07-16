package com.baidu.dpop.ctp.adtag.dao.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.ctp.adtag.bo.TagType;
import com.baidu.dpop.ctp.adtag.dao.TagTypeDao;
import com.baidu.dpop.ctp.adtag.dao.mapper.TagTypeMapper;
import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;

@Repository
public class TagTypeDaoImpl extends BaseDao<TagType, Long> implements TagTypeDao {
	
	@Autowired
	private TagTypeMapper tagTypeMapper;
	
	@Override
	public GenericMapper<TagType, Long> getMapper() {
		return this.tagTypeMapper;
	}

	@Override
	public Set<String> selectByTradeId(Integer tradeId) {
		return this.tagTypeMapper.selectByTradeId(tradeId);
	}

	@Override
	public List<TagType> selectAll() {
		return this.tagTypeMapper.selectAll();
	}

    @Override
    public void batchInsertTagTypes(List<TagType> list) {
        this.tagTypeMapper.batchInsertTagTypes(list);
    }

    @Override
    public void deleteByIds(List<Long> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        this.tagTypeMapper.deleteByIds(list);
    }

}
