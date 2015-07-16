package com.baidu.dpop.ctp.operationRecord.service.impl;

import org.junit.Test;

import com.baidu.dpop.ctp.common.exception.BaseRuntimeException;
import com.baidu.dpop.ctp.operationRecord.bo.OperationRecord;
import com.baidu.dpop.ctp.operationRecord.constants.OperationType;
import com.baidu.dpop.ctp.operationRecord.dao.OperationRecordDao;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

public class operationRecordServiceImplTest {

	@Tested
	private OperationRecordServiceImpl operationRecordServiceImpl;

	@Injectable
	private OperationRecordDao operationRecordDao;

	@Test
	public void testInserNewRecord() {
		new NonStrictExpectations() {
			{
				operationRecordDao.insert((OperationRecord) any);
			}
		};
		operationRecordServiceImpl.insertNewOperation(
				OperationType.OP_USER_CREATE.getId(), "test", 1L);
	}

	@Test(expected = BaseRuntimeException.class)
	public void testInserNewRecordWrong() {
		new NonStrictExpectations() {
			{
				operationRecordDao.insert((OperationRecord) any);
			}
		};
		operationRecordServiceImpl.insertNewOperation(-1, "test", 1L);
	}

	@Test(expected = BaseRuntimeException.class)
	public void testInserNewRecordException() {
		new NonStrictExpectations() {
			{
				operationRecordDao.insert((OperationRecord) any);
				result = new Exception("test");
			}
		};
		operationRecordServiceImpl.insertNewOperation(
				OperationType.OP_USER_CREATE.getId(), "test", 1L);
	}
}
