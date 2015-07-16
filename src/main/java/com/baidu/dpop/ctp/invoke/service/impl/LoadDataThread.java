package com.baidu.dpop.ctp.invoke.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.baidu.dpop.ctp.group.constant.GroupDataType;
import com.baidu.dpop.ctp.invoke.bo.DataLoadInfo;
import com.baidu.dpop.ctp.invoke.service.DataLoadInfoService;
import com.baidu.dpop.ctp.invoke.service.InvokeService;
import com.baidu.dpop.ctp.invoke.vo.DataLoadStatus;
import com.baidu.dpop.ctp.invoke.vo.UpLoadInfo;
import com.baidu.dpop.ctp.nfs.utils.NfsUtils;
import com.baidu.dpop.ctp.task.utils.GeneralTaskUtils;
import com.baidu.dpop.frame.core.context.DpopPropertyUtils;
import com.baidu.dpop.frame.core.context.SpringContextUtil;

/**
 * 数据导入线程
 * 
 * @author cgd
 * @date 2015年4月17日 下午12:58:03
 */
public class LoadDataThread extends Thread {
    private static final Logger LOG = Logger.getLogger(LoadDataThread.class);

    private Integer toInserNum;

    // 待导入的数据文件
    private UpLoadInfo uploadInfo;
    private Byte dataType;

    @Autowired
    private InvokeService invokeService;

    @Autowired
    private DataLoadInfoService dataLoadInfoService;

    public LoadDataThread(UpLoadInfo uploadInfo) {
        this.uploadInfo = uploadInfo;
        this.dataType = uploadInfo.getDataType();

        this.toInserNum = Integer.valueOf(DpopPropertyUtils
                .getProperty("dpop.ctp.invoke.taskInsertNumber"));
        this.invokeService = SpringContextUtil.getBean(InvokeService.class);
        this.dataLoadInfoService = SpringContextUtil
                .getBean(DataLoadInfoService.class);
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        BufferedReader reader = null;
        LOG.info("LoadData Task Begin! File Size: " + uploadInfo.getFileSize());

        // 导入任务信息记录表中新增一个任务
        DataLoadInfo item = new DataLoadInfo(uploadInfo);
        this.dataLoadInfoService.inserDataInfo(item);

        try {
            // 从NFS上获取刚上传的File
            File dataFile = NfsUtils.getFile(uploadInfo.getFilePath());
            // reader = new BufferedReader(new FileReader(dataFile));
            InputStreamReader in = new InputStreamReader(new FileInputStream(
                    dataFile), "UTF-8");
            reader = new BufferedReader(in);

            String lineData = null;
            int lines = 0;

            List<String> elist = new ArrayList<String>();
            int total = 0;
            int processTotal = 0;

            if (GroupDataType.isNewDsp(dataType)) {
                // new dsp 一行包含1000个ad，每读取一行即可处理
                toInserNum = 1;
            }

            List<String> insertion = new ArrayList<String>(toInserNum);

            while ((lineData = reader.readLine()) != null) {
                insertion.add(lineData);
                lines++;

                if (lines == toInserNum) {
                    total += invokeService.uploadFile(GeneralTaskUtils
                            .getGeneralTasks(insertion, dataType), dataType,
                            elist);
                    processTotal += toInserNum;

                    // 记录插入情况更新
                    item.setInsertRecord(total);
                    item.setScanRecord(processTotal);
                    this.dataLoadInfoService.updateDataInfo(item);

                    lines = 0;
                    insertion = new ArrayList<String>();
                    LOG.info("Now process: " + processTotal + ", insert "
                            + total + ".");
                }
            }

            if (insertion.size() > 0) {
                total += invokeService.uploadFile(
                        GeneralTaskUtils.getGeneralTasks(insertion, dataType),
                        dataType, elist);
            }

            // 记录完成情况
            item.setInsertRecord(total);
            item.setScanRecord(processTotal + insertion.size());
            item.setStatus(DataLoadStatus.SUCCESS.getId());
            this.dataLoadInfoService.updateDataInfo(item);

            // 删除nfs上新增的数据文件
            Boolean isSuccess = NfsUtils.deleteFile(uploadInfo.getFilePath());
            LOG.info("Delete Load Data File： " + uploadInfo.getFilePath()
                    + ", is success: " + isSuccess);

        } catch (Exception e) {
            LOG.error("error", e);
            item.setStatus(DataLoadStatus.FAIL.getId());
            this.dataLoadInfoService.updateDataInfo(item);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
            }
            LOG.info("LoadData Task End! Total Time: "
                    + (System.currentTimeMillis() - start) / 1000.0 + " s.");
        }
    }
}
