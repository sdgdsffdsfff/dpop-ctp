package com.baidu.dpop.ctp.invoke.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.baidu.dpop.ctp.adtag.bo.AdTag;
import com.baidu.dpop.ctp.adtag.service.AdTagService;
import com.baidu.dpop.ctp.common.mybatis.page.PageHelper;
import com.baidu.dpop.ctp.invoke.bo.DataLoadInfo;
import com.baidu.dpop.ctp.invoke.service.DataLoadInfoService;
import com.baidu.dpop.ctp.invoke.vo.DataLoadStatus;
import com.baidu.dpop.ctp.invoke.vo.DownloadTagFileVo;
import com.baidu.dpop.ctp.nfs.utils.NfsUtils;
import com.baidu.dpop.ctp.review.bo.ReviewAdTask;
import com.baidu.dpop.ctp.review.service.ReviewAdTaskService;
import com.baidu.dpop.ctp.task.bo.GeneralTask;
import com.baidu.dpop.ctp.task.service.GeneralTaskService;
import com.baidu.dpop.ctp.task.vo.GeneralTaskQueryVo;
import com.baidu.dpop.frame.core.context.SpringContextUtil;

/**
 * 起线程实现标注数据下载
 * 
 * @author cgd
 * @date 2015年6月26日 上午10:18:38
 */
public class DownloadTagFileThread extends Thread {

    private static final Logger LOG = Logger.getLogger(DownloadTagFileThread.class);

    private DownloadTagFileVo downLoadVo;

    public DownloadTagFileThread(DownloadTagFileVo conditionVo) {
        this.downLoadVo = conditionVo;
    }

    @Override
    public void run() {

        long start = System.currentTimeMillis();
        LOG.info("DownloadTagFile Task Begin!");

        FileOutputStream stream = null;

        // 导入任务信息记录表中新增一个任务
        DataLoadInfo dataLoadInfo = new DataLoadInfo(downLoadVo);
        DataLoadInfoService dataLoadInfoService = SpringContextUtil.getBean(DataLoadInfoService.class);
        dataLoadInfoService.inserDataInfo(dataLoadInfo);

        try {
            File file = NfsUtils.getWriteFile(downLoadVo.getFileName());
            stream = new FileOutputStream(file);

            // 查询条件转换
            GeneralTaskQueryVo vo = new GeneralTaskQueryVo();
            vo.setDataType(downLoadVo.getDataType());
            vo.setStartTimeString(downLoadVo.getStartTimeString());
            vo.setEndTimeString(downLoadVo.getEndTimeString());
            vo.setTaskIdList(downLoadVo.getTaskIdList());

            // 获取指定查询条件下所有已标注Tag数据
            AdTagService adTagService = SpringContextUtil.getBean(AdTagService.class);
            Integer total = adTagService.getTagedCount(vo);
            total = total == null ? 0 : total;
            int page = 0;
            int size = 10000;

            if (total == 0) {
                stream.write("NO DATA".getBytes("utf-8"));
            }

            for (; page * size < total; page++) {
                PageHelper.startPage(page + 1, size);
                Map<Long, AdTag> tagMap = adTagService.getMapedTagedtags(vo);

                GeneralTaskService generalTaskService = SpringContextUtil.getBean(GeneralTaskService.class);
                List<GeneralTask> taskList =
                        generalTaskService.batchGet(new ArrayList<Long>(tagMap.keySet()), downLoadVo.getDataType());

                ReviewAdTaskService reviewAdTaskService = SpringContextUtil.getBean(ReviewAdTaskService.class);
                List<ReviewAdTask> reviewList =
                        reviewAdTaskService.batchGetByRefAdId(new ArrayList<Long>(tagMap.keySet()));
                Map<Long, ReviewAdTask> reviewMap = new HashMap<Long, ReviewAdTask>();
                for (ReviewAdTask rtask : reviewList) {
                    reviewMap.put(rtask.getRefAdId(), rtask);
                }

                for (GeneralTask item : taskList) {
                    ReviewAdTask rtask = reviewMap.get(item.getId());
                    if (rtask != null && !rtask.getUpdateUser().equals(ReviewAdTask.updateUser)) {
                        item.setAdTagNew(rtask.toAdTag());
                    } else {
                        item.setAdTagNew(tagMap.get(item.getId()));
                    }
                    stream.write(item.genTagedString().getBytes("utf-8"));
                }

                // 记录完成情况
                Integer insertCount = dataLoadInfo.getInsertRecord() + taskList.size();
                dataLoadInfo.setInsertRecord(insertCount);
                dataLoadInfo.setScanRecord(insertCount);
                dataLoadInfoService.updateDataInfo(dataLoadInfo);

                LOG.info("Page " + page + " data is write to file.");
            }

            // 记录完成情况
            dataLoadInfo.setInsertRecord(total);
            dataLoadInfo.setScanRecord(total);
            dataLoadInfo.setStatus(DataLoadStatus.SUCCESS.getId());
            dataLoadInfoService.updateDataInfo(dataLoadInfo);

        } catch (IOException e) {
            LOG.error("Write file error in DownloadTagFileThread:", e);

            // 写入任务失败
            dataLoadInfo.setStatus(DataLoadStatus.FAIL.getId());
            dataLoadInfoService.updateDataInfo(dataLoadInfo);
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                throw new RuntimeException("Stream close error in DownloadTagFileThread.", e);
            }
            LOG.info("DownloadTagFile Task End! Total Time: " + (System.currentTimeMillis() - start) / 1000.0 + " s.");
        }

    }

}
