/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.operationRecord.dao;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.dpop.ctp.operationRecord.bo.OperationRecord;
import com.baidu.dpop.ctp.operationRecord.vo.OperationRecordQueryVo;

public interface OperationRecordDao extends
		GenericMapperDao<OperationRecord, Long> {
	
	/**
	 * 获取所有操作记录
	 * @return 所有操作记录的列表
	 */
	public List<OperationRecord> selectAll(OperationRecordQueryVo vo);

}
