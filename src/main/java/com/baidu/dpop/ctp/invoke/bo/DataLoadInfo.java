/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.dpop.ctp.invoke.bo;

import java.util.Date;

import com.baidu.dpop.ctp.invoke.vo.DownloadTagFileVo;
import com.baidu.dpop.ctp.invoke.vo.UpLoadInfo;
import com.baidu.dpop.ctp.invoke.vo.DataLoadStatus;

/**
 * 下载文件的信息
 * 
 * @author mading01
 * 
 */
public class DataLoadInfo extends DataLoadInfoBase {

    private static final long serialVersionUID = 1218389937474624821L;

    /**
     * 默认构造函数
     * */
    public DataLoadInfo() {

    }

    /**
     * DataLoadInfo 初始化
     * */
    public DataLoadInfo(UpLoadInfo uploadInfo) {
        Date now = new Date();
        this.setAddTime(now);
        this.setFileName(uploadInfo.getFileName());
        this.setFileSize(uploadInfo.getFileSize());
        this.setDataType(uploadInfo.getDataType());
        this.setInsertRecord(0);
        this.setScanRecord(0);
        this.setMd5Value(uploadInfo.getMd5value());
        this.setStatus(DataLoadStatus.ING.getId());

    }

    /**
     * Download Tag file 初始化
     * */
    public DataLoadInfo(DownloadTagFileVo vo) {
        Date now = new Date();
        this.setAddTime(now);
        this.setFileName(vo.getFileName());
        this.setFileSize(0L); // 文件是写入，init为0
        this.setDataType(vo.getDataType());
        this.setInsertRecord(0);
        this.setScanRecord(0);
        this.setMd5Value(vo.getMd5Value());
        this.setStatus(DataLoadStatus.ING.getId());
    }
}