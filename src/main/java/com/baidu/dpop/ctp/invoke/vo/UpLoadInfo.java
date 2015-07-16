package com.baidu.dpop.ctp.invoke.vo;

import org.springframework.web.multipart.MultipartFile;

public class UpLoadInfo {

    private String token;
    private MultipartFile upLoadFile;
    private String filePath;
    private Byte dataType;
    private String fileName;
    private Long fileSize;
    private String md5value;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public MultipartFile getUpLoadFile() {
        return upLoadFile;
    }

    public void setUpLoadFile(MultipartFile upLoadFile) {
        this.upLoadFile = upLoadFile;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Byte getDataType() {
        return dataType;
    }

    public void setDataType(Byte dataType) {
        this.dataType = dataType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMd5value() {
        return md5value;
    }

    public void setMd5value(String md5value) {
        this.md5value = md5value;
    }

}
