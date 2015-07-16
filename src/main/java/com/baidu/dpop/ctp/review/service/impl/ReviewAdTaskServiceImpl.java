/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.review.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.dpop.ctp.adtag.vo.AdTagAssignedVo;
import com.baidu.dpop.ctp.common.exception.BaseRuntimeException;
import com.baidu.dpop.ctp.group.constant.GroupDataType;
import com.baidu.dpop.ctp.nfs.service.FileArchiveService;
import com.baidu.dpop.ctp.nfs.utils.NfsUtils;
import com.baidu.dpop.ctp.review.bo.ReviewAdTask;
import com.baidu.dpop.ctp.review.bo.ReviewGroup;
import com.baidu.dpop.ctp.review.bo.ReviewTask;
import com.baidu.dpop.ctp.review.dao.ReviewAdTaskDao;
import com.baidu.dpop.ctp.review.service.ReviewAdTaskService;
import com.baidu.dpop.ctp.review.service.ReviewTaskConditionService;
import com.baidu.dpop.ctp.review.service.ReviewTaskService;
import com.baidu.dpop.ctp.review.vo.DistributeReviewGroupResult;
import com.baidu.dpop.ctp.review.vo.PresentedReviewTask;
import com.baidu.dpop.ctp.review.vo.ReviewAdTaskSubmitVo;
import com.baidu.dpop.ctp.review.vo.ReviewBeidouTask;
import com.baidu.dpop.ctp.review.vo.ReviewDSPTask;
import com.baidu.dpop.ctp.review.vo.ReviewNewDSPTask;
import com.baidu.dpop.ctp.review.vo.ReviewQiushiTask;
import com.baidu.dpop.ctp.task.bo.DownloadInfo;
import com.baidu.dpop.ctp.task.bo.GeneralTask;
import com.baidu.dpop.ctp.task.service.GeneralTaskService;
import com.baidu.dpop.ctp.user.bo.User;

@Service
public class ReviewAdTaskServiceImpl extends BaseService<ReviewAdTask, Long> implements ReviewAdTaskService {

    private static final Logger LOG = Logger.getLogger(ReviewAdTaskServiceImpl.class);

    @Autowired
    private ReviewAdTaskDao taskDao;

    @Autowired
    private ReviewTaskConditionService reviewTaskConditionService;

    @Autowired
    private GeneralTaskService generalTaskService;

    @Autowired
    private ReviewTaskService reviewTaskService;

    @Autowired
    private ReviewTaskConditionService conditionService;

    @Autowired
    private FileArchiveService fileArchiveService;

    @Value("${dpop.ctp.invoke.taskInsertNumber}")
    private long defaultInsertion;

    @Override
    public GenericMapperDao<ReviewAdTask, Long> getDao() {
        return taskDao;
    }

    @Override
    public DistributeReviewGroupResult getReviewTasksByReviewGroup(ReviewGroup group, ReviewTask task) {
        List<ReviewAdTask> temp = taskDao.selectReviewTasksByReviewGroup(group);
        if (CollectionUtils.isEmpty(temp)) {
            throw new BaseRuntimeException("The review group: " + group.getId() + " has now review ad task!");
        }

        List<Long> ids = new ArrayList<Long>();
        for (ReviewAdTask rtask : temp) {
            ids.add(rtask.getRefAdId());
        }
        Map<Long, GeneralTask> map = generalTaskService.batchGetMapped(ids, group.getDataType());

        List<PresentedReviewTask> list = new ArrayList<PresentedReviewTask>();
        for (ReviewAdTask rtask : temp) {
            list.add(new PresentedReviewTask(map.get(rtask.getRefAdId()), rtask, task.getBlind(), task.getTaskType()));
        }

        DistributeReviewGroupResult result = new DistributeReviewGroupResult();
        GeneralTask gt = map.get(temp.get(0).getRefAdId());
        result.setAccountId(gt.getUserId().longValue());
        result.setCompanyName(gt.getCompany());
        result.setCompanyUrl(gt.getWebsite());
        result.setGroup(group);
        result.setTask(task);
        result.setList(list);
        return result;
    }

    @Override
    public List<ReviewAdTask> getReviewTasksByTaskId(Integer taskIdReview) {
        return this.taskDao.selectReviewTasksByTaskId(taskIdReview);
    }

    @Override
    public List<ReviewAdTask> batchGetByRefAdId(List<Long> list) {
        return this.taskDao.batchSelectByRefAdId(list);
    }

    @Override
    public List<ReviewAdTask> getReviewWrongDetail(List<Long> reviewGroupIdList) {
        return this.taskDao.selectReviewWrongDetail(reviewGroupIdList);
    }

    @Override
    public String download(List<Integer> list) {
        Assert.notEmpty(list);
        List<String> fileNames = new ArrayList<String>();
        String date = new SimpleDateFormat("yy-MM-dd hh-mm-ss").format(new Date());
        for (Integer tid : list) {
            ReviewTask rt = reviewTaskService.findById(tid.longValue());
            Assert.notNull(rt);

            List<ReviewAdTask> ads = this.getReviewTasksByTaskId(tid);

            List<Long> beidouIds = new ArrayList<Long>();
            List<ReviewAdTask> beidouTasks = new ArrayList<ReviewAdTask>();

            List<Long> qiushiIds = new ArrayList<Long>();
            List<ReviewAdTask> qiushiTasks = new ArrayList<ReviewAdTask>();

            List<Long> dspIds = new ArrayList<Long>();
            List<ReviewAdTask> dspTasks = new ArrayList<ReviewAdTask>();

            List<Long> newDspIds = new ArrayList<Long>();
            List<ReviewAdTask> newDspTasks = new ArrayList<ReviewAdTask>();

            for (ReviewAdTask rtask : ads) {
                if (GroupDataType.isBeidou(rtask.getDataType())) {
                    beidouIds.add(rtask.getRefAdId());
                    beidouTasks.add(rtask);
                } else if (GroupDataType.isQiushi(rtask.getDataType())) {
                    qiushiIds.add(rtask.getRefAdId());
                    qiushiTasks.add(rtask);
                } else if (GroupDataType.isDSP(rtask.getDataType())) {
                    dspIds.add(rtask.getRefAdId());
                    dspTasks.add(rtask);
                } else if (GroupDataType.isNewDsp(rtask.getDataType())) {
                    newDspIds.add(rtask.getRefAdId());
                    newDspTasks.add(rtask);
                }
            }

            if (CollectionUtils.isNotEmpty(beidouIds)) {
                List<DownloadInfo> dlist = new ArrayList<DownloadInfo>();
                Map<Long, GeneralTask> bmap =
                        generalTaskService.batchGetMapped(beidouIds, GroupDataType.BEIDOU.getId());
                for (ReviewAdTask btask : beidouTasks) {
                    ReviewBeidouTask task = new ReviewBeidouTask(bmap.get(btask.getRefAdId()), btask);
                    dlist.add(task);
                }
                String fileName = "BeidouAuditTask_" + date + ".csv";
                download(dlist, fileNames, fileName, ReviewBeidouTask.genFullStringTitle(rt.getTaskType()),
                        rt.getTaskType());
            }

            if (CollectionUtils.isNotEmpty(qiushiIds)) {
                List<DownloadInfo> dlist = new ArrayList<DownloadInfo>();
                Map<Long, GeneralTask> bmap =
                        generalTaskService.batchGetMapped(qiushiIds, GroupDataType.QIUSHI.getId());
                for (ReviewAdTask btask : qiushiTasks) {
                    ReviewQiushiTask task = new ReviewQiushiTask(bmap.get(btask.getRefAdId()), btask);
                    dlist.add(task);
                }
                String fileName = "QiushiAuditTask_" + date + ".csv";
                download(dlist, fileNames, fileName, ReviewQiushiTask.genFullStringTitle(rt.getTaskType()),
                        rt.getTaskType());
            }

            if (CollectionUtils.isNotEmpty(dspIds)) {
                List<DownloadInfo> dlist = new ArrayList<DownloadInfo>();
                Map<Long, GeneralTask> bmap = generalTaskService.batchGetMapped(dspIds, GroupDataType.DSP.getId());
                for (ReviewAdTask btask : dspTasks) {
                    ReviewDSPTask task = new ReviewDSPTask(bmap.get(btask.getRefAdId()), btask);
                    dlist.add(task);
                }
                String fileName = "DSPAuditTask_" + date + ".csv";
                download(dlist, fileNames, fileName, ReviewDSPTask.genFullStringTitle(rt.getTaskType()),
                        rt.getTaskType());
            }

            if (CollectionUtils.isNotEmpty(newDspIds)) {
                List<DownloadInfo> dlist = new ArrayList<DownloadInfo>();
                Map<Long, GeneralTask> bmap =
                        generalTaskService.batchGetMapped(newDspIds, GroupDataType.NEWDSP.getId());
                for (ReviewAdTask btask : newDspTasks) {
                    ReviewNewDSPTask task = new ReviewNewDSPTask(bmap.get(btask.getRefAdId()), btask);
                    dlist.add(task);
                }
                String fileName = "NewDSPAuditTask_" + date + ".csv";
                download(dlist, fileNames, fileName, ReviewNewDSPTask.genFullStringTitle(rt.getTaskType()),
                        rt.getTaskType());
            }
        }
        return fileArchiveService.getPackageFilePath(fileNames, "FullAuditTask[" + date + "].zip");
    }

    private void download(List<DownloadInfo> list, List<String> fileNames, String fileName, String title,
            Number taskType) {
        FileOutputStream stream = null;
        fileNames.add(fileName);
        try {
            File file = NfsUtils.getWriteFile(fileName);
            stream = new FileOutputStream(file);
            stream.write(new byte[] { (byte) 239, (byte) 187, (byte) 191 }); // 为了让excel识别utf-8编码，需要在文件头添加这三个字节
            stream.write(title.getBytes("utf-8"));
            for (DownloadInfo dl : list) {
                stream.write(dl.genFullString(taskType).getBytes("utf-8"));
            }
        } catch (IOException e) {
            LOG.error("getFullReviewTaskInfoByTaskId Exception: ", e);
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                LOG.error("getFullTaskInfoByTaskId stream close Exception: ", e);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public int batchInsertTasks(List<ReviewAdTask> list) {
        // 分批插入数据，每次插入1000条
        List<ReviewAdTask> temp = new ArrayList<ReviewAdTask>();

        int insertion = 0;
        int result = 0;

        if (CollectionUtils.isEmpty(list)) {
            return 0;
        }

        for (ReviewAdTask ad : list) {
            temp.add(ad);
            insertion++;
            if (insertion == defaultInsertion) {
                result += this.taskDao.batchInsertTasks(temp);
                insertion = 0;
                temp.clear();
            }
        }

        if (temp.size() > 0) {
            result += this.taskDao.batchInsertTasks(temp);
            insertion = 0;
            temp.clear();
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void assignedAdTags(Integer taskId, Integer dataType, List<Long> ids) {
        Assert.notNull(taskId);
        Assert.notNull(dataType);
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }

        AdTagAssignedVo vo = new AdTagAssignedVo();
        vo.setTaskId(taskId);
        vo.setDataType(dataType);
        vo.setIds(ids);
        this.taskDao.updateAssigned(vo);
    }

    @Override
    public void submitReviewTasks(List<ReviewAdTask> list, User updateUser, Date updateTime) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        ReviewAdTaskSubmitVo vo = new ReviewAdTaskSubmitVo();
        vo.setList(list);
        vo.setUpdateTime(updateTime);
        vo.setUpdateUser(updateUser.getUserName());
        taskDao.batchUpdate(vo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void deleteByTaskId(Integer taskId) {
        Assert.notNull(taskId);
        this.taskDao.deleteByTaskId(taskId);
    }
}
