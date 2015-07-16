/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.operationRecord.dao.mapper;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapper;
import com.baidu.dpop.ctp.operationRecord.bo.OperationRecord;
import com.baidu.dpop.ctp.operationRecord.vo.OperationRecordQueryVo;

public interface OperationRecordMapper extends
		GenericMapper<OperationRecord, Long> {
	
	public List<OperationRecord> selectAll(OperationRecordQueryVo vo);

}