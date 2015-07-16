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
import com.baidu.dpop.ctp.task.bo.QiushiTask;
import com.baidu.dpop.ctp.task.bo.GeneralTask;
import com.baidu.dpop.ctp.task.dao.QiushiTaskDao;
import com.baidu.dpop.ctp.task.service.QiushiTaskService;
import com.baidu.dpop.ctp.task.vo.PresentedTask;
import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;

@Service
public class QiushiTaskServiceImpl extends BaseService<QiushiTask, Long> implements QiushiTaskService {

    private static final Logger LOG = Logger.getLogger(QiushiTaskServiceImpl.class);
    
    @Autowired
    private TaskService taskService;

    @Autowired
    QiushiTaskDao qiushiTaskDao;

    @Autowired
    private UBMCService ubmcService;

    @Autowired
    private AdTagService adTagService;

    @Override
    public GenericMapperDao<QiushiTask, Long> getDao() {
        // TODO Auto-generated method stub
        return qiushiTaskDao;
    }

    @Override
    public DistributeGroupResult getQiushiTasksByGroup(Group group) {
        List<QiushiTask> list = qiushiTaskDao.selectQiushiTasksByGroup(group.getTaskId(), group.getGroupId());

        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        QiushiTask first = list.get(0);
        DistributeGroupResult result = new DistributeGroupResult();
        result.setAccountId(first.getUserId());
        result.setCompanyName(first.getCompany());
        result.setCompanyUrl(first.getWebsite());

        List<PresentedTask> temp = new ArrayList<PresentedTask>();
        for (QiushiTask task : list) {
            PresentedTask t = new PresentedTask(task);
            t.setTagInfo(task.genTagInfo());
            temp.add(t);
        }
        result.setList(temp);

        return result;
    }

    @Override
    public QiushiTask getQiushiTaskById(Long id, Integer region) {
        return qiushiTaskDao.selectByPrimaryKey(id);
    }

    @Override
    public List<String> getFullTaskInfoByTaskId(Integer taskId, List<String> fileNames, String date) {
        FileOutputStream stream = null;
        String fileName = "qiushi[taskId_" + taskId + "][" + date + "].csv";
        LOG.info("下载秋实任务:" + taskId);
        fileNames.add(fileName);
        Task t = taskService.findById(taskId.longValue());
        Assert.notNull(t);
        
        try {
            File file = NfsUtils.getWriteFile(fileName); // qiushi
            stream = new FileOutputStream(file);
            stream.write(new byte[] { (byte) 239, (byte) 187, (byte) 191 }); // 为了让excel识别utf-8编码，需要在文件头添加这三个字节
            stream.write(QiushiTask.genFullStringTitle(t.getTaskType()).getBytes("utf-8"));
            int count = qiushiTaskDao.selectDownloadCount(taskId);
            int page = 0;
            int size = 10000;
            for (; page * size < count; page++) {
                PageHelper.startPage(page + 1, size);
                List<QiushiTask> list = qiushiTaskDao.selectQiushiTasksByTaskId(taskId);
                if (CollectionUtils.isEmpty(list)) {
                    continue;
                }

                // 获取明细对应的Tag标注信息
                List<Long> refAdIdList = new ArrayList<Long>();
                for (QiushiTask item : list) {
                    refAdIdList.add(item.getId());
                }
                Map<Long, AdTag> adTagMap =
                        this.adTagService.getAdTagMap(refAdIdList, Integer.valueOf(GroupDataType.QIUSHI.getId()));
                
                // 将获取的tag信息set到list中，并写入os流
                for (QiushiTask task : list) {
                    AdTag tagInfo = adTagMap.get(task.getId());
                    if (tagInfo != null) {
                        task.setAdTagNew(tagInfo);
                    }
                    
                    String tm = task.genFullString(t.getTaskType());
                    stream.write(tm.getBytes("utf-8"));
                }
            }
        } catch (IOException e) {
            LOG.error("Exception in getFullTaskInfoByTaskId: ", e);
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                LOG.error("生成文件失败！", e);
            }
        }
        return fileNames;
    }
    
    @Override
    public List<TaskTestVo> getTestedTasks(List<GeneralTask> list) {
        return this.qiushiTaskDao.selectTestedTasks(list);
    }
    
    @Override
    public List<QiushiTask> batchGet(List<Long> list) {
        return this.qiushiTaskDao.batchSelect(list);
    }

    @Override
    public List<Group> getTaskCount(List<GroupCountVo> groupIds) {
        return this.qiushiTaskDao.selectTaskCount(groupIds);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public int batchInsertTasks(List<QiushiTask> tasks) {
        return this.qiushiTaskDao.batchInsertTasks(tasks);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void deleteAdDetail(Integer taskId) {
        Assert.notNull(taskId);
        this.qiushiTaskDao.deleteAdDetail(taskId);
    }
}