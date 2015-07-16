package com.baidu.dpop.ctp.task.vo;

import java.util.ArrayList;
import java.util.List;

import com.baidu.dpop.ctp.mainTask.vo.FlashIdeaResponse;
import com.baidu.dpop.ctp.task.bo.GeneralTask;

public class PresentedTaskDetail extends PresentedTask {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5376356024370953890L;
	
	private Integer userId;
	private Integer width;
	private Integer height;
	private FlashIdeaResponse flash;
	
	private List<String> imgUrl;
	
	public PresentedTaskDetail() {}
	
	public PresentedTaskDetail(GeneralTask gt) {
		super(gt);
		setTaskType(gt.getTaskType());
		setWidth(gt.getWidth().intValue());
		setHeight(gt.getHeight().intValue());
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}
	
	public FlashIdeaResponse getFlash() {
		return flash;
	}
	
	public void setFlash(FlashIdeaResponse flash) {
		this.flash = flash;
	}
	
	public List<String> getImgUrl() {
		return imgUrl;
	}
	
	public void setImgUrl(List<String> imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public void setImgUrl(String... urls) {
		this.imgUrl = new ArrayList<String>(urls.length);
		for (String url : urls) {
			imgUrl.add(url);
		}
	}
}
