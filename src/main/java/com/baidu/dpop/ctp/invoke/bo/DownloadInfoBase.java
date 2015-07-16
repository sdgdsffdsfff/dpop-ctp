/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.dpop.ctp.invoke.bo;

import java.util.Date;

public class DownloadInfoBase implements java.io.Serializable {

    private static final long serialVersionUID = -1324956061090214295L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件类型
     */
    private Integer fileType;

    /**
     * 文件行数
     */
    private Integer lineNum;

    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date doneTime;

    /**
     * 执行人
     */
    private String startUser;

    /**
     * 完成度
     */
    private Integer percentage;

    /**
     * 主键ID
     * 
     * @param id the value for id
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
     * 文件名
     * 
     * @param fileName the value for file_name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 文件名
     * 
     * @return fileName the value for file_name
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * 文件类型
     * 
     * @param fileType the value for file_type
     */
    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    /**
     * 文件类型
     * 
     * @return fileType the value for file_type
     */
    public Integer getFileType() {
        return this.fileType;
    }

    /**
     * 文件行数
     * 
     * @param lineNum the value for line_num
     */
    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    /**
     * 文件行数
     * 
     * @return lineNum the value for line_num
     */
    public Integer getLineNum() {
        return this.lineNum;
    }

    /**
     * 文件大小
     * 
     * @param fileSize the value for file_size
     */
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * 文件大小
     * 
     * @return fileSize the value for file_size
     */
    public Long getFileSize() {
        return this.fileSize;
    }

    /**
     * 开始时间
     * 
     * @param startTime the value for start_time
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 开始时间
     * 
     * @return startTime the value for start_time
     */
    public Date getStartTime() {
        return this.startTime;
    }

    /**
     * 结束时间
     * 
     * @param doneTime the value for done_time
     */
    public void setDoneTime(Date doneTime) {
        this.doneTime = doneTime;
    }

    /**
     * 结束时间
     * 
     * @return doneTime the value for done_time
     */
    public Date getDoneTime() {
        return this.doneTime;
    }

    /**
     * 执行人
     * 
     * @param startUser the value for start_user
     */
    public void setStartUser(String startUser) {
        this.startUser = startUser;
    }

    /**
     * 执行人
     * 
     * @return startUser the value for start_user
     */
    public String getStartUser() {
        return this.startUser;
    }

    /**
     * 完成度
     * 
     * @param percentage the value for percentage
     */
    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    /**
     * 完成度
     * 
     * @return percentage the value for percentage
     */
    public Integer getPercentage() {
        return this.percentage;
    }
}
