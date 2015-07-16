/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.operationRecord.service;

import java.util.List;

import com.baidu.dpop.frame.core.base.GenericMapperService;
import com.baidu.dpop.ctp.operationRecord.bo.OperationRecord;

public interface OperationRecordService extends GenericMapperService<OperationRecord, Long> {

    /**
     * 插入一条新的操作记录
     * 
     * @param operationType 操作类型
     * @param operation 操作内容，字符串类型
     * @param operator 操作者id
     * @see com.baidu.dpop.ctp.operationRecord.constants.OperationType
     */
    public void insertNewOperation(Integer operationType, String operation, Long operator);

    /**
     * 获取所有操作记录
     * 
     * @return 所有操作记录的List
     */
    public List<OperationRecord> getAll(Integer type, Integer page, Integer size, String order, String orderBy);
}
