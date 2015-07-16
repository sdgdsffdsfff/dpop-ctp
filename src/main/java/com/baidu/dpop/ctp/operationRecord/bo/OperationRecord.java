/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.dpop.ctp.operationRecord.bo;

import org.codehaus.jackson.annotate.JsonProperty;

/**
* 
*/
public class OperationRecord extends OperationRecordBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String operatorName;
	
	@JsonProperty("operationType")
	private String operationName;
	
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	public String getOperatorName() {
		return operatorName;
	}
	
	public String getOperationName() {
	    return operationName;
	}
	
	public void setOperationName(String operationName) {
	    this.operationName = operationName;
	}

}