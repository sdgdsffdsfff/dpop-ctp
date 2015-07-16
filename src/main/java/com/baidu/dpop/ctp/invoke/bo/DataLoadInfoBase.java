/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.dpop.ctp.invoke.bo;

import java.util.Date;

public class DataLoadInfoBase implements java.io.Serializable {

    private static final long serialVersionUID = 5142682839909383284L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 产品线: 0-北斗, 1-秋实, 2DSP
     */
    private Byte dataType;

    /**
     * 状态: 0-处理中, 1-已完成, 2-失败
     */
    private Byte status;

    /**
     * 物料类型
     */
    private Integer scanRecord;

    /**
     * 物料类型
     */
    private Integer insertRecord;

    /**
     * 文件md5值
     */
    private String md5Value;

    /**
     * 新增时间
     */
    private Date addTime;

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
     * 产品线: 0-北斗, 1-秋实, 2DSP
     * 
     * @param dataType the value for data_type
     */
    public void setDataType(Byte dataType) {
        this.dataType = dataType;
    }

    /**
     * 产品线: 0-北斗, 1-秋实, 2DSP
     * 
     * @return dataType the value for data_type
     */
    public Byte getDataType() {
        return this.dataType;
    }

    /**
     * 状态: 0-处理中, 1-已完成, 2-失败
     * 
     * @param status the value for status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 状态: 0-处理中, 1-已完成, 2-失败
     * 
     * @return status the value for status
     */
    public Byte getStatus() {
        return this.status;
    }

    /**
     * 物料类型
     * 
     * @param scanRecord the value for scan_record
     */
    public void setScanRecord(Integer scanRecord) {
        this.scanRecord = scanRecord;
    }

    /**
     * 物料类型
     * 
     * @return scanRecord the value for scan_record
     */
    public Integer getScanRecord() {
        return this.scanRecord;
    }

    /**
     * 物料类型
     * 
     * @param insertRecord the value for insert_record
     */
    public void setInsertRecord(Integer insertRecord) {
        this.insertRecord = insertRecord;
    }

    /**
     * 物料类型
     * 
     * @return insertRecord the value for insert_record
     */
    public Integer getInsertRecord() {
        return this.insertRecord;
    }

    /**
     * 文件md5值
     * 
     * @param md5Value the value for md5_value
     */
    public void setMd5Value(String md5Value) {
        this.md5Value = md5Value;
    }

    /**
     * 文件md5值
     * 
     * @return md5Value the value for md5_value
     */
    public String getMd5Value() {
        return this.md5Value;
    }

    /**
     * 新增时间
     * 
     * @param addTime the value for add_time
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 新增时间
     * 
     * @return addTime the value for add_time
     */
    public Date getAddTime() {
        return this.addTime;
    }
}
