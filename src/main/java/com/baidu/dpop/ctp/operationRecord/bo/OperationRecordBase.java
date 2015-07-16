/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.dpop.ctp.operationRecord.bo;

import java.util.Date;

/**
* 
*/
public class OperationRecordBase implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 366086340923466747L;

	/**
	 * 主键ID
	 */
	private Long id;

	/**
	 * 操作者id
	 */
	private Long operator;

	/**
	 * 操作时间
	 */
	private Date updateTime;

	/**
	 * 操作类型
	 */
	private Integer operationType;

	/**
	 * 操作内容
	 */
	private String operation;

	/**
	 * 主键ID
	 * 
	 * @param id
	 *            the value for id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 主键ID
	 * 
	 * @return id the value for id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * 操作者id
	 * 
	 * @param operator
	 *            the value for operator
	 */
	public void setOperator(Long operator) {
		this.operator = operator;
	}

	/**
	 * 操作者id
	 * 
	 * @return operator the value for operator
	 */
	public Long getOperator() {
		return this.operator;
	}

	/**
	 * 操作时间
	 * 
	 * @param updateTime
	 *            the value for update_time
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 操作时间
	 * 
	 * @return updateTime the value for update_time
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 操作类型
	 * 
	 * @param operationType
	 *            the value for operation_type
	 */
	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}

	/**
	 * 操作类型
	 * 
	 * @return operationType the value for operation_type
	 */
	public Integer getOperationType() {
		return this.operationType;
	}

	/**
	 * 操作内容
	 * 
	 * @param operation
	 *            the value for operation
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * 操作内容
	 * 
	 * @return operation the value for operation
	 */
	public String getOperation() {
		return this.operation;
	}
}
