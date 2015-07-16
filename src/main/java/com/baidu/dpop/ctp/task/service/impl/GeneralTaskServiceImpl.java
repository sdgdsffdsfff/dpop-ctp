package com.baidu.dpop.ctp.task.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.baidu.dpop.ctp.account.bo.AccountInfo;
import com.baidu.dpop.ctp.adtag.bo.AdTag;
import com.baidu.dpop.ctp.adtag.service.AdTagService;
import com.baidu.dpop.ctp.common.exception.BaseRuntimeException;
import com.baidu.dpop.ctp.common.mybatis.page.PageHelper;
import com.baidu.dpop.ctp.common.utils.UBMCUtils;
import com.baidu.dpop.ctp.group.bo.Group;
import com.baidu.dpop.ctp.group.constant.GroupDataType;
import com.baidu.dpop.ctp.group.vo.DistributeGroupResult;
import com.baidu.dpop.ctp.invoke.vo.GroupCountVo;
import com.baidu.dpop.ctp.invoke.vo.TaskTestVo;
import com.baidu.dpop.ctp.mainTask.constant.GeneralMcType;
import com.baidu.dpop.ctp.mainTask.service.UBMCService;
import com.baidu.dpop.ctp.nfs.service.FileArchiveService;
import com.baidu.dpop.ctp.nfs.utils.NfsUtils;
import com.baidu.dpop.ctp.outerinvoke.service.OuterInvokeService;
import com.baidu.dpop.ctp.review.bo.ReviewAdTask;
import com.baidu.dpop.ctp.review.service.ReviewAdTaskService;
import com.baidu.dpop.ctp.task.bo.BeidouTask;
import com.baidu.dpop.ctp.task.bo.DSPTask;
import com.baidu.dpop.ctp.task.bo.GeneralTask;
import com.baidu.dpop.ctp.task.bo.NewDSPTask;
import com.baidu.dpop.ctp.task.bo.QiushiTask;
import com.baidu.dpop.ctp.task.service.BeidouTaskService;
import com.baidu.dpop.ctp.task.service.DSPTaskService;
import com.baidu.dpop.ctp.task.service.GeneralTaskService;
import com.baidu.dpop.ctp.task.service.NewDSPTaskService;
import com.baidu.dpop.ctp.task.service.QiushiTaskService;
import com.baidu.dpop.ctp.task.utils.GeneralTaskUtils;
import com.baidu.dpop.ctp.task.vo.GeneralTaskQueryVo;
import com.baidu.dpop.ctp.task.vo.PresentedTask;
import com.baidu.dpop.ctp.task.vo.PresentedTaskDetail;
import com.baidu.dpop.ctp.user.bo.User;

@Service
public class GeneralTaskServiceImpl implements GeneralTaskService {

    @Autowired
    private BeidouTaskService beidouTaskService;

    @Autowired
    private QiushiTaskService qiushiTaskService;

    @Autowired
    private DSPTaskService dspTaskService;

    @Autowired
    private NewDSPTaskService newDSPTaskService;

    @Autowired
    private UBMCService ubmcService;

    @Autowired
    private OuterInvokeService outerInvokeService;

    @Autowired
    private FileArchiveService fileArchiveService;

    @Autowired
    private ReviewAdTaskService reviewAdTaskService;

    @Autowired
    private AdTagService adTagService;

    private static final Logger LOG = Logger.getLogger(GeneralTaskService.class);

    @Override
    public DistributeGroupResult getTasksByGroup(Group group, User u, List<Group> historyGroup) {
        DistributeGroupResult result = null;
        if (GroupDataType.isBeidou(group.getDataType())) {
            result = beidouTaskService.getBeidouTasksByGroup(group);
        } else if (GroupDataType.isQiushi(group.getDataType())) {
            result = qiushiTaskService.getQiushiTasksByGroup(group);
        } else if (GroupDataType.isDSP(group.getDataType())) {
            result = dspTaskService.getDSPTasksByGroup(group);
        } else if (GroupDataType.isNewDsp(group.getDataType())) {
            result = newDSPTaskService.getNewDSPTasksByGroup(group);
        }

        if (result == null) {
            throw new BaseRuntimeException("Group id: " + group.getGroupId() + " 没有对应的标注任务！");
        }

        List<AdTag> tagList = adTagService.getByGroup(group.getId());
        Map<Long, AdTag> map = new HashMap<Long, AdTag>();
        if (!CollectionUtils.isEmpty(tagList)) {
            for (AdTag tag : tagList) {
                map.put(tag.getRefId(), tag);
            }
        }

        // 填充adTag
        for (PresentedTask pt : result.getList()) {
            pt.setTagInfo(map.get(pt.getId()));
        }

        Collections.sort(result.getList());

        int adCount = 0;
        for (Group g : historyGroup) {
            adCount += g.getAdNum();
        }

        result.setHistoryAdNum(adCount);
        result.setHistoryGroup(historyGroup);
        result.setHistoryGroupNum(historyGroup.size());
        return result;
    }

    @Override
    public PresentedTaskDetail getTaskById(Long id, Integer region, Integer dataType) {

        GeneralTask task = null;
        if (GroupDataType.isBeidou(dataType)) {
            task = beidouTaskService.getBeidouTaskById(id, region);
        } else if (GroupDataType.isQiushi(dataType)) {
            task = qiushiTaskService.getQiushiTaskById(id, region);
        } else if (GroupDataType.isDSP(dataType)) {
            task = dspTaskService.getDSPTaskById(id, region);
        } else if (GroupDataType.isNewDsp(dataType)) {
            task = newDSPTaskService.getNewDSPTaskById(id, region);
        }

        if (task == null) {
            return null;
        }

        AdTag tag = adTagService.getByRefId(task.getId().longValue(), task.getDataType());
        PresentedTaskDetail pt = new PresentedTaskDetail(task);
        pt.setTagInfo(tag == null ? task.genTagInfo() : tag.toTagInfo(pt.getTaskType().intValue()));
        UBMCUtils.setUBMCImgUrl(pt, ubmcService, task.getDataType().intValue());
        outerInvokeService.getCreativityInfo(Arrays.asList(pt));
        return pt;
    }

    @Override
    public List<GeneralTask> batchGet(List<Long> ids, Number dataType) {
        List<GeneralTask> result = new ArrayList<GeneralTask>();
        if (GroupDataType.isBeidou(dataType)) {
            List<BeidouTask> list = beidouTaskService.batchGet(ids);
            for (BeidouTask task : list) {
                result.add(task);
            }
        } else if (GroupDataType.isQiushi(dataType)) {
            List<QiushiTask> list = qiushiTaskService.batchGet(ids);
            for (QiushiTask task : list) {
                result.add(task);
            }
        } else if (GroupDataType.isDSP(dataType)) {
            List<DSPTask> list = dspTaskService.batchGet(ids);
            for (DSPTask task : list) {
                result.add(task);
            }
        } else if (GroupDataType.isNewDsp(dataType)) {
            List<NewDSPTask> list = newDSPTaskService.batchGet(ids);
            for (NewDSPTask task : list) {
                result.add(task);
            }
        }
        return result;
    }

    @Override
    public Map<Long, GeneralTask> batchGetMapped(List<Long> ids, Number dataType) {
        Map<Long, GeneralTask> map = new HashMap<Long, GeneralTask>();
        if (GroupDataType.isBeidou(dataType)) {
            List<BeidouTask> list = beidouTaskService.batchGet(ids);
            for (BeidouTask task : list) {
                map.put(task.getId(), task);
            }
        } else if (GroupDataType.isQiushi(dataType)) {
            List<QiushiTask> list = qiushiTaskService.batchGet(ids);
            for (QiushiTask task : list) {
                map.put(task.getId(), task);
            }
        } else if (GroupDataType.isDSP(dataType)) {
            List<DSPTask> list = dspTaskService.batchGet(ids);
            for (DSPTask task : list) {
                map.put(task.getId(), task);
            }
        } else if (GroupDataType.isNewDsp(dataType)) {
            List<NewDSPTask> list = newDSPTaskService.batchGet(ids);
            for (NewDSPTask task : list) {
                map.put(task.getId(), task);
            }
        }
        return map;
    }

    @Override
    public Map<GroupCountVo, Group> getTaskCount(List<GroupCountVo> groupIds, Byte dataType) {
        List<Group> list = null;
        if (GroupDataType.isBeidou(dataType)) {
            list = beidouTaskService.getTaskCount(groupIds);
        } else if (GroupDataType.isQiushi(dataType)) {
            list = qiushiTaskService.getTaskCount(groupIds);
        } else if (GroupDataType.isDSP(dataType)) {
            list = dspTaskService.getTaskCount(groupIds);
        } else if (GroupDataType.isNewDsp(dataType)) {
            list = newDSPTaskService.getTaskCount(groupIds);
        }
        Map<GroupCountVo, Group> map = new HashMap<GroupCountVo, Group>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (Group g : list) {
                g.setDataType(dataType);
                map.put(new GroupCountVo(g.getTaskId(), g.getGroupId(), dataType), g);
            }
        }
        return map;
    }

    public String downloadWhenCreateReviewTask(List<AdTag> tagList, Integer groupNum, Number taskType) {
        String date = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date());
        String archiveFile = "CreateReviewTask[" + date + "].zip";
        Set<Long> groupIds = new HashSet<Long>();
        for (AdTag tag : tagList) {
            groupIds.add(tag.getRefGroupId());
        }

        int size = 0;
        Set<Long> addGroups = new HashSet<Long>();
        for (Long groupId : groupIds) {
            addGroups.add(groupId);
            size++;
            if (size == groupNum.intValue()) {
                break;
            }
        }

        List<AdTag> download = new ArrayList<AdTag>();

        for (AdTag tag : tagList) {
            if (addGroups.contains(tag.getRefGroupId())) {
                download.add(tag);
            }
        }

        List<String> fileNames = new ArrayList<String>();
        for (GroupDataType type : GroupDataType.values()) {
            if (!type.equals(GroupDataType.ALL)) {
                getFileWhenCreateReview(download, fileNames, date, type.getId(), taskType);
            }
        }
        return "/nfs/" + fileArchiveService.getPackageFilePath(fileNames, archiveFile);
    }

    private List<String> getFileWhenCreateReview(List<AdTag> tagList, List<String> fileNames, String date,
            Number dataType, Number taskType) {
        // 没有在tagList层做分页是因为tagList已经做了做大数量30W的限制
        FileOutputStream stream = null;
        GroupDataType type = GroupDataType.get(dataType);
        if (type == null || type.equals(GroupDataType.ALL)) {
            throw new BaseRuntimeException("download when create wrong: invaild data type : " + dataType);
        }
        String fileName = type.getDesc() + "[" + date + "].csv";
        fileNames.add(fileName);
        try {
            File file = NfsUtils.getWriteFile(fileName); // beidou
            stream = new FileOutputStream(file);

            stream.write(new byte[] { (byte) 239, (byte) 187, (byte) 191 }); // 为了让excel识别utf-8编码，需要在文件头添加这三个字节
            stream.write(GeneralTaskUtils.getReviewDownloadTitle(dataType, taskType).getBytes("utf-8"));
            int size = 10000;
            int count = 0;
            List<Long> idList = new ArrayList<Long>();
            Map<Long, AdTag> tagMap = new HashMap<Long, AdTag>();
            for (AdTag tag : tagList) {
                if (tag.getDataType().intValue() == dataType.intValue()) {
                    idList.add(tag.getRefId());
                    tagMap.put(tag.getRefId(), tag);
                    count++;
                }

                if (count == size) {
                    List<GeneralTask> list = this.batchGet(idList, type.getId());
                    for (GeneralTask task : list) {
                        task.setAdTagNew(tagMap.get(task.getId()));
                        String tm = task.genFullString(taskType);
                        stream.write(tm.getBytes("utf-8"));
                    }
                    count = 0;
                    idList = new ArrayList<Long>();
                }
            }

            if (idList.size() > 0) {
                List<GeneralTask> list = this.batchGet(idList, type.getId());
                for (GeneralTask task : list) {
                    task.setAdTagNew(tagMap.get(task.getId()));
                    String tm = task.genFullString(taskType);
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
    public String getTagedFile(GeneralTaskQueryVo vo) {
        Assert.notNull(vo);
        Assert.notNull(vo.getDataType());

        long start = System.currentTimeMillis();
        LOG.info("Get TagFile Task Begin!");

        FileOutputStream stream = null;

        // 自定义fileName
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        StringBuilder fileName = new StringBuilder();
        fileName.append("tagedTask_dataType_");
        fileName.append(vo.getDataType());
        fileName.append("_time_");
        fileName.append(sdf.format(new Date()));

        try {
            File file = NfsUtils.getWriteFile(fileName.toString());
            stream = new FileOutputStream(file);

            Integer count = this.adTagService.getTagedCount(vo);
            count = count == null ? 0 : count;
            int page = 0;
            int size = 10000;

            if (count == 0) {
                stream.write("NO DATA".getBytes("utf-8"));
            }

            for (; page * size < count; page++) {
                PageHelper.startPage(page + 1, size);
                Map<Long, AdTag> tagMap = adTagService.getMapedTagedtags(vo);
                List<GeneralTask> taskList = this.batchGet(new ArrayList<Long>(tagMap.keySet()), vo.getDataType());
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

                LOG.info("Page " + page + " data is write to file.");
            }
        } catch (IOException e) {
            LOG.error("Write file error in getBeidouFile:", e);
            throw new RuntimeException("Write file error:", e);
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                throw new RuntimeException("Stream close error in getBeidouFile.", e);
            }
            LOG.info("LoadData Task End! Total Time: " + (System.currentTimeMillis() - start) / 1000.0 + " s.");
        }

        return fileName.toString();
    }

    @Override
    public String getFullTaskInfoByTaskId(Integer taskId) {
        String date = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date());
        String archiveFile = "FullTask[taskId_" + taskId + "][" + date + "].zip";
        List<String> fileNames = new ArrayList<String>();
        beidouTaskService.getFullTaskInfoByTaskId(taskId, fileNames, date);
        qiushiTaskService.getFullTaskInfoByTaskId(taskId, fileNames, date);
        dspTaskService.getFullTaskInfoByTaskId(taskId, fileNames, date);
        newDSPTaskService.getFullTaskInfoByTaskId(taskId, fileNames, date);
        return fileArchiveService.getPackageFilePath(fileNames, archiveFile);
    }

    @Override
    public String getFullTaskInfoBytTaskIds(List<Integer> taskIds) {
        String fileName = "FullTask[" + new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date()) + "].zip";
        List<String> fileNames = new ArrayList<String>();
        for (Integer id : taskIds) {
            fileNames.add(getFullTaskInfoByTaskId(id));
        }
        return fileArchiveService.getPackageFilePath(fileNames, fileName);
    }

    @Override
    public List<String> getImgUrl(Long id, Integer dataType) {
        if (GroupDataType.isBeidou(dataType)) {
            PresentedTaskDetail task = new PresentedTaskDetail(beidouTaskService.findById(id));
            UBMCUtils.setUBMCImgUrl(task, ubmcService, dataType);
            return task.getImgUrl();
        } else if (GroupDataType.isQiushi(dataType)) {
            PresentedTaskDetail task = new PresentedTaskDetail(qiushiTaskService.findById(id));
            UBMCUtils.setUBMCImgUrl(task, ubmcService, dataType);
            return task.getImgUrl();
        } else if (GroupDataType.isDSP(dataType)) {
            PresentedTaskDetail task = new PresentedTaskDetail(dspTaskService.findById(id));
            UBMCUtils.setUBMCImgUrl(task, ubmcService, dataType);
            return task.getImgUrl();
        } else if (GroupDataType.isNewDsp(dataType)) {
            // new dsp库不在这里获取
            return null;
        }

        return null;
    }

    @Override
    public List<PresentedTaskDetail> getImgUrls(List<Long> list, Integer dataType) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        List<PresentedTaskDetail> temp = new ArrayList<PresentedTaskDetail>();
        if (GroupDataType.isBeidou(dataType)) {
            List<BeidouTask> l = this.beidouTaskService.batchGet(list);
            for (BeidouTask t : l) {
                if (GeneralMcType.needUrl(t.getGeneralWuliaoType())) {
                    temp.add(new PresentedTaskDetail(t));
                }
            }
        } else if (GroupDataType.isQiushi(dataType)) {
            List<QiushiTask> l = this.qiushiTaskService.batchGet(list);
            for (QiushiTask t : l) {
                if (GeneralMcType.needUrl(t.getGeneralWuliaoType())) {
                    temp.add(new PresentedTaskDetail(t));
                }
            }
        } else if (GroupDataType.isDSP(dataType)) {
            List<DSPTask> l = this.dspTaskService.batchGet(list);
            for (DSPTask t : l) {
                if (GeneralMcType.needUrl(t.getGeneralWuliaoType())) {
                    temp.add(new PresentedTaskDetail(t));
                }
            }
        } else if (GroupDataType.isNewDsp(dataType)) {
            // new dsp 不同过ubmc，直接从数据库中获取即可
            List<NewDSPTask> l = this.newDSPTaskService.batchGet(list);
            for (NewDSPTask t : l) {
                if (GeneralMcType.needUrl(t.getGeneralWuliaoType())) {
                    PresentedTaskDetail pt = new PresentedTaskDetail(t);
                    pt.setImgUrl(t.getUrl());
                    temp.add(pt);
                }
            }
            return temp;
        }
        UBMCUtils.setUBMCImgUrl(temp, ubmcService, dataType);
        return temp;
    }

    @Override
    public void updateCompanyInfo(List<AccountInfo> list, List<Integer> taskIds, Number dataType) {
        if (GroupDataType.isNewDsp(dataType)) {
            newDSPTaskService.updateCompanyInfo(list, taskIds);
        }
    }

    @Override
    public void deleteExpiredAdDetail(Integer taskId) {
        Assert.notNull(taskId);

        this.beidouTaskService.deleteAdDetail(taskId);
        this.qiushiTaskService.deleteAdDetail(taskId);
        this.dspTaskService.deleteAdDetail(taskId);
        this.newDSPTaskService.deleteAdDetail(taskId);
        this.adTagService.deleteAdDetail(taskId);
    }

    @Override
    public int insertGeneralTask(List<GeneralTask> tasks, Byte dataType) {
        if (GroupDataType.isBeidou(dataType)) {
            Set<TaskTestVo> testSet = new HashSet<TaskTestVo>(beidouTaskService.getTestedTasks(tasks));
            List<BeidouTask> temp = new ArrayList<BeidouTask>();
            for (GeneralTask task : tasks) {
                if (!testSet.contains(new TaskTestVo(task.getTaskId().longValue(), task.getAdId().longValue()))) {
                    temp.add((BeidouTask) task);
                } else {
                    ((BeidouTask) task).setSecondPriority(0);
                }
            }
            beidouTaskService.batchInsertTasks(temp);
            return temp.size();
        } else if (GroupDataType.isQiushi(dataType)) {
            Set<TaskTestVo> testSet = new HashSet<TaskTestVo>(qiushiTaskService.getTestedTasks(tasks));
            List<QiushiTask> temp = new ArrayList<QiushiTask>();
            for (GeneralTask task : tasks) {
                if (!testSet.contains(new TaskTestVo(task.getTaskId().longValue(), task.getAdId().longValue()))) {
                    temp.add((QiushiTask) task);
                } else {
                    ((QiushiTask) task).setSecondPriority(0);
                }
            }
            qiushiTaskService.batchInsertTasks(temp);
            return temp.size();
        } else if (GroupDataType.isDSP(dataType)) {
            Set<TaskTestVo> testSet = new HashSet<TaskTestVo>(dspTaskService.getTestedTasks(tasks));
            List<DSPTask> temp = new ArrayList<DSPTask>();
            for (GeneralTask task : tasks) {
                if (!testSet.contains(new TaskTestVo(task.getTaskId().longValue(), task.getAdId().longValue()))) {
                    temp.add((DSPTask) task);
                } else {
                    ((DSPTask) task).setSecondPriority(0);
                }
            }
            dspTaskService.batchInsertTasks(temp);
            return temp.size();
        } else if (GroupDataType.isNewDsp(dataType)) {
            Set<TaskTestVo> testSet = new HashSet<TaskTestVo>(newDSPTaskService.getTestedTasks(tasks));
            List<NewDSPTask> temp = new ArrayList<NewDSPTask>();
            for (GeneralTask task : tasks) {
                if (!testSet.contains(new TaskTestVo(task.getTaskId().longValue(), task.getAdId().longValue()))) {
                    temp.add((NewDSPTask) task);
                }
            }
            newDSPTaskService.batchInsertTasks(temp);
            return temp.size();
        }
        return 0;
    }

}
