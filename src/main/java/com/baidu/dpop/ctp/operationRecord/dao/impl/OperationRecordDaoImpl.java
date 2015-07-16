/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.operationRecord.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baidu.dpop.frame.core.base.BaseDao;
import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.dpop.ctp.operationRecord.bo.OperationRecord;
import com.baidu.dpop.ctp.operationRecord.dao.OperationRecordDao;
import com.baidu.dpop.ctp.operationRecord.dao.mapper.OperationRecordMapper;
import com.baidu.dpop.ctp.operationRecord.vo.OperationRecordQueryVo;

@Repository
public class OperationRecordDaoImpl extends BaseDao<OperationRecord, Long>
		implements OperationRecordDao {

	@Autowired
	private OperationRecordMapper operationRecordMapper;

	@Override
	public GenericMapper<OperationRecord, Long> getMapper() {
		return this.operationRecordMapper;
	}

	@Override
	public List<OperationRecord> selectAll(OperationRecordQueryVo vo) {
		return this.operationRecordMapper.selectAll(vo);
	}

}
