package com.baidu.dpop.ctp.invoke.vo;

/**
 * 唯一区别一个group的参数类，包含：
 * <li>groupId - 由上游给出的推广组id
 * <li>taskId - 由上游给出的任务id
 * <li>dataType - 数据类型，即广告库类型
 * <br>根据以上三个参数可以唯一确定一个group实体
 * @author mading01
 *
 */
public class GroupCountVo {

	private Long taskId;
	private Long groupId;
	private Byte dataType;
	
	public GroupCountVo() {};
	
	public GroupCountVo(long taskId, long groupId, byte dataType) {
		this.taskId = taskId;
		this.groupId = groupId;
		this.dataType = dataType;
	}

	public int hashCode() {
		int result = 17;
		result = (int)(31 * result + taskId);
		result = (int)(31 * result + groupId);
		result = 31 * result + dataType;
		return result;
	}

	public boolean equals(Object other) {
		if (!(other instanceof GroupCountVo)) {
			return false;
		}

		GroupCountVo vo = (GroupCountVo) other;
		return this.taskId.longValue() == vo.taskId.longValue()
				&& this.groupId.longValue() == vo.groupId.longValue()
				&& this.dataType.longValue() == vo.dataType.longValue();
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	
	public Byte getDataType() {
		return dataType;
	}
	
	public void setDataType(Byte dataType) {
		this.dataType = dataType;
	}
}
