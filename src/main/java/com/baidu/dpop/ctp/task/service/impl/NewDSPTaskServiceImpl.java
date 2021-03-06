/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.task.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.dpop.ctp.account.bo.AccountInfo;
import com.baidu.dpop.ctp.adtag.bo.AdTag;
import com.baidu.dpop.ctp.adtag.service.AdTagService;
import com.baidu.dpop.ctp.common.mybatis.page.PageHelper;
import com.baidu.dpop.ctp.group.bo.Group;
import com.baidu.dpop.ctp.group.constant.GroupDataType;
import com.baidu.dpop.ctp.group.vo.DistributeGroupResult;
import com.baidu.dpop.ctp.invoke.vo.GroupCountVo;
import com.baidu.dpop.ctp.invoke.vo.TaskTestVo;
import com.baidu.dpop.ctp.mainTask.bo.Task;
import com.baidu.dpop.ctp.mainTask.service.TaskService;
import com.baidu.dpop.ctp.mainTask.service.UBMCService;
import com.baidu.dpop.ctp.nfs.utils.NfsUtils;
import com.baidu.dpop.ctp.task.bo.NewDSPTask;
import com.baidu.dpop.ctp.task.bo.GeneralTask;
import com.baidu.dpop.ctp.task.dao.NewDSPTaskDao;
import com.baidu.dpop.ctp.task.service.NewDSPTaskService;
import com.baidu.dpop.ctp.task.vo.PresentedTask;

@Service
public class NewDSPTaskServiceImpl extends BaseService<NewDSPTask, Long> implements NewDSPTaskService {

    private static final Logger LOG = Logger.getLogger(NewDSPTaskServiceImpl.class);

    @Autowired
    private TaskService taskService;

    @Autowired
    private NewDSPTaskDao newDSPTaskDao;

    @Autowired
    private UBMCService ubmcService;

    @Autowired
    private AdTagService adTagService;

    @Override
    public GenericMapperDao<NewDSPTask, Long> getDao() {
        return newDSPTaskDao;
    }

    @Override
    public DistributeGroupResult getNewDSPTasksByGroup(Group group) {
        List<NewDSPTask> list = newDSPTaskDao.selectNewDSPTasksByGroup(group.getTaskId(), group.getGroupId());

        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        NewDSPTask first = list.get(0);
        DistributeGroupResult result = new DistributeGroupResult();
        result.setAccountId(first.getUserId());
        result.setCompanyName(first.getCompany());
        result.setCompanyUrl(first.getWebsite());

        List<PresentedTask> temp = new ArrayList<PresentedTask>();
        for (NewDSPTask task : list) {
            PresentedTask t = new PresentedTask(task);
            t.setTagInfo(task.genTagInfo());
            temp.add(t);
        }
        result.setList(temp);

        return result;
    }

    @Override
    public NewDSPTask getNewDSPTaskById(Long id, Integer region) {
        return newDSPTaskDao.selectByPrimaryKey(id);
    }

    @Override
    public List<String> getFullTaskInfoByTaskId(Integer taskId, List<String> fileNames, String date) {
        FileOutputStream stream = null;
        String fileName = "newDSP[taskId_" + taskId + "][" + date + "].csv";
        fileNames.add(fileName);
        Task t = taskService.findById(taskId.longValue());
        Assert.notNull(t);
        
        try {
            File file = NfsUtils.getWriteFile(fileName); // newDSP
            stream = new FileOutputStream(file);

            stream.write(new byte[] { (byte) 239, (byte) 187, (byte) 191 }); // 为了让excel识别utf-8编码，需要在文件头添加这三个字节
            stream.write(NewDSPTask.genFullStringTitle(t.getTaskType()).getBytes("utf-8"));
            int count = newDSPTaskDao.selectDownloadCount(taskId);
            int page = 0;
            int size = 10000;
            for (; page * size < count; page++) {
                PageHelper.startPage(page + 1, size);
                List<NewDSPTask> list = newDSPTaskDao.selectNewDSPTasksByTaskId(taskId);
                if (CollectionUtils.isEmpty(list)) {
                    continue;
                }

                // 获取明细对应的Tag标注信息
                List<Long> refAdIdList = new ArrayList<Long>();
                for (NewDSPTask item : list) {
                    refAdIdList.add(item.getId());
                }
                Map<Long, AdTag> adTagMap =
                        this.adTagService.getAdTagMap(refAdIdList, GroupDataType.NEWDSP.getId().intValue());

                // 将获取的tag信息set到list中，并写入os流
                for (NewDSPTask task : list) {
                    AdTag tagInfo = adTagMap.get(task.getId());
                    if (tagInfo != null) {
                        task.setAdTagNew(tagInfo);
                    }

                    String tm = task.genFullString(t.getTaskType());
                    stream.write(tm.getBytes("utf-8"));
                }
            }

        } catch (IOException e) {
            LOG.error("getFullTaskInfoByTaskId Exception: ", e);
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                LOG.error("getFullTaskInfoByTaskId stream close Exception: ", e);
            }
        }
        return fileNames;
    }

    @Override
    public List<TaskTestVo> getTestedTasks(List<GeneralTask> list) {
        return this.newDSPTaskDao.selectTestedTasks(list);
    }

    @Override
    public List<NewDSPTask> batchGet(List<Long> list) {
        return this.newDSPTaskDao.batchSelect(list);
    }

    @Override
    public List<Group> getTaskCount(List<GroupCountVo> groupIds) {
        return this.newDSPTaskDao.selectTaskCount(groupIds);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public int batchInsertTasks(List<NewDSPTask> tasks) {
        return this.newDSPTaskDao.batchInsertTasks(tasks);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void updateCompanyInfo(List<AccountInfo> list, List<Integer> taskIds) {
        this.newDSPTaskDao.updateCompanyInfo(list, taskIds);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void deleteAdDetail(Integer taskId) {
        this.newDSPTaskDao.deleteAdDetail(taskId);
    }

}
