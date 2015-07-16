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
import com.baidu.dpop.ctp.task.bo.BeidouTask;
import com.baidu.dpop.ctp.task.bo.GeneralTask;
import com.baidu.dpop.ctp.task.dao.BeidouTaskDao;
import com.baidu.dpop.ctp.task.service.BeidouTaskService;
import com.baidu.dpop.ctp.task.vo.PresentedTask;
import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;

@Service
public class BeidouTaskServiceImpl extends BaseService<BeidouTask, Long> implements BeidouTaskService {

    private static final Logger LOG = Logger.getLogger(BeidouTaskServiceImpl.class);

    @Autowired
    private TaskService taskService;

    @Autowired
    private BeidouTaskDao beidouTaskDao;

    @Autowired
    private UBMCService ubmcService;

    @Autowired
    private AdTagService adTagService;

    @Override
    public GenericMapperDao<BeidouTask, Long> getDao() {
        return beidouTaskDao;
    }

    @Override
    public DistributeGroupResult getBeidouTasksByGroup(Group group) {
        List<BeidouTask> list = beidouTaskDao.selectBeidouTasksByGroup(group.getTaskId(), group.getGroupId());

        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        BeidouTask first = list.get(0);
        DistributeGroupResult result = new DistributeGroupResult();
        result.setAccountId(first.getUserId());
        result.setCompanyName(first.getCompany());
        result.setCompanyUrl(first.getWebsite());

        List<PresentedTask> temp = new ArrayList<PresentedTask>();
        for (BeidouTask task : list) {
            PresentedTask t = new PresentedTask(task);
            t.setTagInfo(task.genTagInfo());
            temp.add(t);
        }
        result.setList(temp);

        return result;
    }

    @Override
    public BeidouTask getBeidouTaskById(Long id, Integer region) {
        return beidouTaskDao.selectByPrimaryKey(id);
    }

    @Override
    public List<String> getFullTaskInfoByTaskId(Integer taskId, List<String> fileNames, String date) {
        FileOutputStream stream = null;
        String fileName = "beidou[taskId_" + taskId + "][" + date + "].csv";
        Task t = taskService.findById(taskId.longValue());
        Assert.notNull(taskId);
        fileNames.add(fileName);
        try {
            File file = NfsUtils.getWriteFile(fileName); // beidou
            stream = new FileOutputStream(file);

            stream.write(new byte[] { (byte) 239, (byte) 187, (byte) 191 }); // 为了让excel识别utf-8编码，需要在文件头添加这三个字节
            stream.write(BeidouTask.genFullStringTitle(t.getTaskType()).getBytes("utf-8"));
            int count = beidouTaskDao.selectDownloadCount(taskId);
            int page = 0;
            int size = 10000;
            for (; page * size < count; page++) {
                PageHelper.startPage(page + 1, size);
                List<BeidouTask> list = beidouTaskDao.selectBeidouTasksByTaskId(taskId);
                if (CollectionUtils.isEmpty(list)) {
                    continue;
                }

                // 获取明细对应的Tag标注信息
                List<Long> refAdIdList = new ArrayList<Long>();
                for (BeidouTask item : list) {
                    refAdIdList.add(item.getId());
                }
                Map<Long, AdTag> adTagMap =
                        this.adTagService.getAdTagMap(refAdIdList, Integer.valueOf(GroupDataType.BEIDOU.getId()));

                // 将获取的tag信息set到list中，并写入os流
                for (BeidouTask task : list) {
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
        return this.beidouTaskDao.selectTestedTasks(list);
    }

    @Override
    public List<BeidouTask> batchGet(List<Long> list) {
        return this.beidouTaskDao.batchSelect(list);
    }

    @Override
    public List<Group> getTaskCount(List<GroupCountVo> groupIds) {
        return this.beidouTaskDao.selectTaskCount(groupIds);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public int batchInsertTasks(List<BeidouTask> tasks) {
        return this.beidouTaskDao.batchInsertTasks(tasks);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void deleteAdDetail(Integer taskId) {
        this.beidouTaskDao.deleteAdDetail(taskId);
    }
}
