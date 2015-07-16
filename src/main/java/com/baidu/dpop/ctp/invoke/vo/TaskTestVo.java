package com.baidu.dpop.ctp.invoke.vo;

public class TaskTestVo {

	private Long adId;
	private Long taskId;

	public TaskTestVo() {}
	
	public TaskTestVo(long taskId, long adId) {
		this.taskId = taskId;
		this.adId = adId;
	}

	public int hashCode() {
		int result = 17;
		result = (int)(31 * result + taskId);
		result = (int)(31 * result + adId);
		return result;
	}

	public boolean equals(Object other) {
		if (!(other instanceof TaskTestVo)) {
			return false;
		}

		TaskTestVo vo = (TaskTestVo) other;
		return this.taskId.intValue() == vo.taskId.intValue()
				&& this.taskId.intValue() == vo.taskId.intValue();
	}
	
	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

}
