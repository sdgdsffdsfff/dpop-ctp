/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.operationRecord.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.dpop.ctp.common.exception.BaseRuntimeException;
import com.baidu.dpop.ctp.common.mybatis.page.PageHelper;
import com.baidu.dpop.ctp.operationRecord.bo.OperationRecord;
import com.baidu.dpop.ctp.operationRecord.constants.OperationType;
import com.baidu.dpop.ctp.operationRecord.dao.OperationRecordDao;
import com.baidu.dpop.ctp.operationRecord.service.OperationRecordService;
import com.baidu.dpop.ctp.operationRecord.vo.OperationRecordQueryVo;

@Service
public class OperationRecordServiceImpl extends
		BaseService<OperationRecord, Long> implements OperationRecordService {

	@Autowired
	private OperationRecordDao operationRecordDao;
	
	@Value("${dpop.ctp.task.page}")
	private Integer PAGE;
	
	@Value("${dpop.ctp.task.size}")
	private Integer SIZE;

	@Override
	public GenericMapperDao<OperationRecord, Long> getDao() {
		return operationRecordDao;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void insertNewOperation(Integer operationType, String operation,
			Long operator) {
		OperationType type = OperationType.get(operationType);
		if (type == null) {
			throw new BaseRuntimeException("非法的操作类型: " + operationType);
		}

		OperationRecord record = new OperationRecord();
		record.setOperation(operation);
		record.setOperationType(operationType);
		record.setOperator(operator);
		record.setUpdateTime(new Date());

		try {
			operationRecordDao.insert(record);
		} catch (Exception e) {
			throw new BaseRuntimeException("插入操作记录错误: "
					+ e.getLocalizedMessage());
		}
	}

	@Override
	public List<OperationRecord> getAll(Integer type, Integer page, Integer size,
			String order, String orderBy) {
		PageHelper.startPage(page, size);
		OperationRecordQueryVo vo = new OperationRecordQueryVo();
		vo.setOrder(order.equals("desc") ? false :true);
		vo.setOrderBy(orderBy);
		vo.setType(type);
		List<OperationRecord> list = this.operationRecordDao.selectAll(vo);
		for (OperationRecord record : list) {
		    record.setOperationName(OperationType.get(record.getOperationType()).getDesc());
		}
		return list;
	}
}
