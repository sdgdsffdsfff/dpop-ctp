/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.group.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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
import com.baidu.dpop.ctp.adtag.bo.AdTag;
import com.baidu.dpop.ctp.adtag.service.AdTagService;
import com.baidu.dpop.ctp.adtag.vo.SubmitTagInfo;
import com.baidu.dpop.ctp.common.exception.BaseRuntimeException;
import com.baidu.dpop.ctp.group.bo.Group;
import com.baidu.dpop.ctp.group.constant.GroupStatus;
import com.baidu.dpop.ctp.group.dao.GroupDao;
import com.baidu.dpop.ctp.group.service.GroupService;
import com.baidu.dpop.ctp.group.vo.DistributeGroupResult;
import com.baidu.dpop.ctp.group.vo.GroupDownloadInfo;
import com.baidu.dpop.ctp.group.vo.SubmitInfoGetVo;
import com.baidu.dpop.ctp.group.vo.TrendInfoGetVo;
import com.baidu.dpop.ctp.invoke.bo.DownloadInfo;
import com.baidu.dpop.ctp.invoke.constants.FileType;
import com.baidu.dpop.ctp.invoke.service.DownloadAction;
import com.baidu.dpop.ctp.invoke.service.DownloadInfoService;
import com.baidu.dpop.ctp.invoke.service.DownloadService;
import com.baidu.dpop.ctp.invoke.vo.GroupCountVo;
import com.baidu.dpop.ctp.mainTask.bo.Task;
import com.baidu.dpop.ctp.mainTask.constant.TaskStatus;
import com.baidu.dpop.ctp.mainTask.service.TaskService;
import com.baidu.dpop.ctp.nfs.utils.NfsUtils;
import com.baidu.dpop.ctp.outerinvoke.service.OuterInvokeService;
import com.baidu.dpop.ctp.statistics.vo.TrendStatisticsItem;
import com.baidu.dpop.ctp.statistics.vo.UserStatisticsItem;
import com.baidu.dpop.ctp.task.service.GeneralTaskService;
import com.baidu.dpop.ctp.task.vo.TagFollowInfoVo;
import com.baidu.dpop.ctp.user.bo.User;
import com.baidu.dpop.ctp.user.service.UserService;

@Service
public class GroupServiceImpl extends BaseService<Group, Long> implements GroupService {

    private static Logger LOG = Logger.getLogger(GroupServiceImpl.class);

    @Value("${dpop.ctp.invoke.taskInsertNumber}")
    private long INSERTION;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private GeneralTaskService generalTaskService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private AdTagService adTagService;

    @Autowired
    private OuterInvokeService outerInvokeService;

    @Autowired
    private UserService userService;

    @Autowired
    private DownloadService downloadService;

    @Autowired
    private DownloadInfoService downloadInfoService;

    @Override
    public GenericMapperDao<Group, Long> getDao() {
        return groupDao;
    }

    @Override
    public Group getGroup(Group group) {
        Assert.notNull(group);
        return groupDao.selectGroup(group);
    }

    @Override
    public Group distributeNewGroup(Task task) {
        Integer count = groupDao.selectUnstartCount(task.getId());
        if (count == null) {
            LOG.error("Get unstart count null. task:" + task + ", taskId:" + task.getId());
            throw new BaseRuntimeException("Get unstart count null.");
        }

        if (count.intValue() == 0) {
            return null;
        }
        Random r = new Random();
        count = r.nextInt(count > 200 ? 200 : count);
        Group g = groupDao.selectRandomGroup(task.getId(), count);

        if (g == null) {
            return null;
        }
        return g;
    }

    @Override
    public DistributeGroupResult getTasksByGroup(Task task, Group group, User user) {

        // 更新group状态，如果group的状态为已完成，则不更新
        if (!GroupStatus.isFinished(group.getStatus())) {
            group.setStatus(GroupStatus.STARTED.getId());
            group.setStartTime(new Date());
            group.setModifyUserId(user.getId());

            Group updateGroup = new Group();
            updateGroup.setId(group.getId());
            updateGroup.setStatus(GroupStatus.STARTED.getId());
            updateGroup.setStartTime(new Date());
            updateGroup.setModifyUserId(user.getId());

            updateByIdSelective(updateGroup);
        }

        // 如果task状态为未开始，则需要设置为进行中
        if (task.getStatus().intValue() == TaskStatus.NOT_STARTED.getId().intValue()) {
            task.setStatus(TaskStatus.STARTED.getId());
            taskService.updateByIdSelective(task);
        }

        // 获取adList
        List<Group> historyGroup = getGroupByUser(task, user);
        DistributeGroupResult result = generalTaskService.getTasksByGroup(group, user, historyGroup);
        result.setTask(task);
        result.setGroup(group);
        return result;
    }

    @Override
    public Integer getUnfinishedCount(Integer taskId) {
        return this.groupDao.selectUnfinishedCount(taskId);
    }

    @Override
    public Map<GroupCountVo, Group> getTestedGroup(List<GroupCountVo> list) {
        Map<GroupCountVo, Group> map = new HashMap<GroupCountVo, Group>();
        if (CollectionUtils.isNotEmpty(list)) {
            List<Group> temp = this.groupDao.selectTestedGroup(list);
            if (CollectionUtils.isNotEmpty(temp)) {
                for (Group g : temp) {
                    map.put(new GroupCountVo(g.getTaskId(), g.getGroupId(), g.getDataType()), g);
                }
            }
        }
        return map;
    }

    @Override
    public Group getStartedGroupByUser(Task task, User user) {
        Assert.notNull(task);
        Assert.notNull(user);
        return this.groupDao.selectStartedGroupByUser(task.getId(), user.getId());
    }

    @Override
    public List<Group> getGroupByUser(Task task, User user) {
        Assert.notNull(task);
        Assert.notNull(user);
        return groupDao.selectHistoryGroups(task.getId(), user.getId());
    }

    @Override
    public List<Group> getGroupListByCondition(Integer taskId, Integer status, Integer isNotFinished) {
        return this.groupDao.selectGroupListByCondition(taskId, status, isNotFinished);
    }

    @Override
    public TrendStatisticsItem getTrendInfo(TrendInfoGetVo vo) {
        Assert.notNull(vo);
        return this.groupDao.selectTrendInfo(vo);
    }

    @Override
    public List<UserStatisticsItem> getUserStatisticsInfo(Integer taskId) {
        Assert.notNull(taskId);
        return this.groupDao.selectUserStatisticsInfo(taskId);
    }

    @Override
    public List<TagFollowInfoVo> getTagFollowInfoList(Integer taskId) {
        Assert.notNull(taskId);

        List<TagFollowInfoVo> ret = this.groupDao.selectTagFollowInfoList(taskId);
        if (CollectionUtils.isEmpty(ret)) {
            ret = new ArrayList<TagFollowInfoVo>();
        }

        return ret;
    }

    @Override
    public Long getGroupDownloadInfo(final SubmitInfoGetVo vo) {
        Assert.notNull(vo);
        Assert.notNull(vo.getTaskId());

        User user = userService.getCurrentLoginUser();

        if (vo.getName() != null) {
            User u = userService.getUserByName(vo.getName());
            vo.setUserId(u.getId());
        }

        Date date = new Date();
        final String fileName = "GroupInfo_" + new SimpleDateFormat("yyyyMMddHHmmss").format(date) + ".csv";

        return downloadService.doUnBlockedDownload(fileName, user, date, FileType.GROUP_INFO.getId(),
                new DownloadAction() {

                    @Override
                    public void download(DownloadInfo info) {
                        FileOutputStream stream = null;

                        try {
                            File file = NfsUtils.getWriteFile(fileName);
                            stream = new FileOutputStream(file);
                            stream.write(new byte[] { (byte) 239, (byte) 187, (byte) 191 });
                            stream.write(GroupDownloadInfo.genFullTitle().getBytes("utf-8"));

                            String[] taskIds = vo.getTaskId().split(",");

                            int fileId = 0;
                            for (String id : taskIds) {
                                fileId++;
                                Integer i = Integer.valueOf(id);
                                vo.setTaskIdNumber(i);
                                Set<GroupDownloadInfo> set = groupDao.selectGroupDownloadInfo(vo);
                                for (GroupDownloadInfo dinfo : set) {
                                    stream.write(dinfo.genFullInfo().getBytes("utf-8"));
                                }
                                info.setLineNum(info.getLineNum() + set.size());
                                info.setPercentage(fileId * 100 / taskIds.length);
                                downloadInfoService.updateByIdSelective(info);
                            }
                            info.setFileSize(file.getTotalSpace());

                        } catch (Exception e) {
                            LOG.error("获取group下载信息失败", e);
                        } finally {
                            try {
                                stream.close();
                            } catch (Exception e) {
                                // TODO NOTHING
                            }
                        }

                        info.setPercentage(100);
                        info.setDoneTime(new Date());
                        downloadInfoService.updateByIdSelective(info);
                    }
                });

    }

    @Override
    public List<Group> batchGet(List<Long> list) {
        return this.groupDao.batchSelect(list);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void batchInsert(List<Group> groups) {
        this.groupDao.batchInsert(groups);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void submitTasks(List<SubmitTagInfo> list, Long groupId, User user) {
        Group group = groupDao.selectByPrimaryKey(groupId);

        if (group == null) {
            throw new BaseRuntimeException("提交的id有误，提交失败！");
        }

        if (group.getModifyUserId() == null || !group.getModifyUserId().equals(user.getId())) {
            throw new BaseRuntimeException("NOT BELONG TO THIS USER");
        }

        if (!TaskStatus.canConfirm(groupDao.selectTaskStatusById(groupId))) {
            throw new BaseRuntimeException("Task status is wrong, can't submit!");
        }

        Task task = taskService.findById(group.getTaskId().longValue());

        Date doneTime = new Date();
        group.setDoneTime(doneTime);
        group.setStatus(GroupStatus.FINISHED.getId());

        Group updateGroup = new Group();
        updateGroup.setId(group.getId());
        updateGroup.setDoneTime(doneTime);
        updateGroup.setStatus(GroupStatus.FINISHED.getId());
        groupDao.updateByPrimaryKeySelective(updateGroup);

        Date date = new Date();
        List<AdTag> submit = new ArrayList<AdTag>();
        List<Long> ids = new ArrayList<Long>();

        for (SubmitTagInfo info : list) {
            AdTag tag = info.toAdTag(task.getTaskType().intValue());
            tag.setRefGroupId(group.getId());
            tag.setTaskId(group.getTaskId());
            tag.setDataType(group.getDataType().intValue());
            submit.add(tag);
            ids.add(tag.getRefId());
        }

        adTagService.submit(submit, user, date);
        outerInvokeService.doUnBlockedAssignTask(false, ids, list, group.getDataType().intValue(), group.getTaskId(),
                date);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public Boolean giveUpGroup(Long id, User user) {
        Group group = groupDao.selectByPrimaryKey(id);
        if (group == null) {
            return true;
        }

        if (!group.getStatus().equals(GroupStatus.STARTED.getId())) {
            return true;
        }

        if (!user.getId().equals(group.getModifyUserId())) {
            return false;
        }

        group.setDoneTime(null);
        group.setModifyUserId(null);
        group.setStartTime(null);
        group.setStatus(GroupStatus.NOT_STARTED.getId());
        groupDao.updateByPrimaryKey(group);
        return true;
    }

    @Override
    public void recycleAssignGroups(Date beginTime) {
        Assert.notNull(beginTime);
        this.groupDao.recycleAssignGroups(beginTime);
    }

    @Override
    public void batchUpdate(List<Group> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        this.groupDao.batchUpdate(list);
    }

    @Override
    public void deleteGroupByTaskId(Integer taskId) {
        this.groupDao.deleteGroupByTaskId(taskId);
    }
}
