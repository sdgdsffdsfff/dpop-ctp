package com.baidu.dpop.ctp.invoke.vo;

/**
 * DataLoadInfoVo
 * 
 * @author cgd
 * @date 2015年4月17日 下午4:30:53
 */
public class DataLoadInfoVo {
    /**
     * 文件名
     * */
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
     * 添加时间
     * */
    private String addTime;

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Byte getDataType() {
        return dataType;
    }

    public void setDataType(Byte dataType) {
        this.dataType = dataType;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getScanRecord() {
        return scanRecord;
    }

    public void setScanRecord(Integer scanRecord) {
        this.scanRecord = scanRecord;
    }

    public Integer getInsertRecord() {
        return insertRecord;
    }

    public void setInsertRecord(Integer insertRecord) {
        this.insertRecord = insertRecord;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
