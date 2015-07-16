/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.review.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.dpop.ctp.adtag.bo.AdTag;
import com.baidu.dpop.ctp.adtag.utils.AdTagUtils;
import com.baidu.dpop.ctp.adtag.utils.TaskTypeUtils;
import com.baidu.dpop.ctp.adtag.vo.TaskType;
import com.baidu.dpop.ctp.common.exception.BaseRuntimeException;
import com.baidu.dpop.ctp.common.mybatis.page.PageHelper;
import com.baidu.dpop.ctp.group.constant.GroupDataType;
import com.baidu.dpop.ctp.group.constant.GroupStatus;
import com.baidu.dpop.ctp.mainTask.constant.TaskStatus;
import com.baidu.dpop.ctp.mainTask.constant.TaskStatusChangeType;
import com.baidu.dpop.ctp.mainTask.service.TaskService;
import com.baidu.dpop.ctp.mainTask.vo.TaskQueryInfo;
import com.baidu.dpop.ctp.mainTask.vo.TaskStatusChangeVo;
import com.baidu.dpop.ctp.operationRecord.constants.OperationType;
import com.baidu.dpop.ctp.operationRecord.service.OperationRecordService;
import com.baidu.dpop.ctp.review.bo.ReviewGroup;
import com.baidu.dpop.ctp.review.bo.ReviewTask;
import com.baidu.dpop.ctp.review.bo.ReviewTaskCondition;
import com.baidu.dpop.ctp.review.constants.ReviewTaskModuserLevel;
import com.baidu.dpop.ctp.review.dao.ReviewTaskDao;
import com.baidu.dpop.ctp.review.service.ReviewAdTaskService;
import com.baidu.dpop.ctp.review.service.ReviewGroupService;
import com.baidu.dpop.ctp.review.service.ReviewTaskConditionService;
import com.baidu.dpop.ctp.review.service.ReviewTaskService;
import com.baidu.dpop.ctp.review.vo.DistributeReviewGroupResult;
import com.baidu.dpop.ctp.review.vo.ReviewCountVo;
import com.baidu.dpop.ctp.user.bo.User;
import com.baidu.dpop.ctp.user.constant.UserRoleType;
import com.baidu.dpop.ctp.user.service.UserService;

@Service
public class ReviewTaskServiceImpl extends BaseService<ReviewTask, Long> implements ReviewTaskService {

    @Autowired
    private ReviewTaskDao mainTaskDao;

    @Autowired
    private ReviewTaskConditionService reviewTaskConditionService;

    @Autowired
    private ReviewGroupService reviewGroupService;

    @Autowired
    private ReviewAdTaskService reviewAdTaskService;

    @Autowired
    private TaskService mainTaskService;

    @Autowired
    private UserService userService;

    @Autowired
    private OperationRecordService operationRecordService;

    @Override
    public GenericMapperDao<ReviewTask, Long> getDao() {
        return mainTaskDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public ReviewTask createNewReviewTask(ReviewTask task, ReviewTaskCondition condition, List<Integer> taskList) {

        User u = userService.getCurrentLoginUser();
        TaskStatusChangeVo tvo = new TaskStatusChangeVo();
        tvo.setStatusChange(TaskStatusChangeType.CANTOPEN.getId());
        tvo.setTaskIds(taskList);
        mainTaskService.changeTaskState(tvo);

        // 如果没有选择条件，则需要手动创建一个条件，仅设置dataType
        if (condition == null) {
            condition = new ReviewTaskCondition();
            condition.setDataType(GroupDataType.ALL.getId().intValue());
        }
        condition.genAdTagCondition();
        task.setTaskCondition(reviewTaskConditionService.insertReturnId(condition).intValue());
        task.setAddUser(u.getUserName());
        Long id = insertReturnId(task);
        
        
        Map<String, List<String>> tagValue = condition.getTagValue();
        if (tagValue != null) {
            TaskType type = TaskTypeUtils.getType(task.getTaskType());
            Assert.notNull(type);
            for (String tag : tagValue.keySet()) {
                if (!type.contains(AdTagUtils.getTagByName(tag))) {
                    throw new BaseRuntimeException("所选标签与任务类型不符！");
                }
            }
        }
        

        // 设置查询条件，查询ad
        List<AdTag> tagList = reviewTaskConditionService.getAdTagWhenCreateReviewTask(condition, taskList);
        if (CollectionUtils.isEmpty(tagList)) {
            throw new BaseRuntimeException("没有符合条件的任务!");
        }

        int groupActualNum = reviewGroupService.createReviewTask(tagList, task);
        task.setGroupNumActual(groupActualNum);
        task.setId(id.intValue());
        this.updateByIdSelective(task);

        if (id < 0) {
            throw new BaseRuntimeException("插入审核任务失败！");
        }

        tvo = new TaskStatusChangeVo();
        tvo.setStatusChange(TaskStatusChangeType.CANOPEN.getId());
        tvo.setTaskIds(taskList);
        mainTaskService.changeTaskState(tvo);
        operationRecordService.insertNewOperation(OperationType.OP_REVIEW_CREATE.getId(),
                "[" + id + ", " + task.getTaskName() + "]", u.getId().longValue());
        return task;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public Long insertReturnId(ReviewTask task) {
        if (insertSelective(task) > 0) {
            return mainTaskDao.selectLastInsertId();
        }
        return -1L;
    }

    @Override
    public List<ReviewTask> getTaskListByTaskInfo(Integer page, Integer size, TaskQueryInfo taskQueryInfo) {
        User u = userService.getCurrentLoginUser();
        if (null == u) {
            throw new IllegalArgumentException("getUserByName fail, user is null");
        }

        if (null == taskQueryInfo) {
            throw new IllegalArgumentException("taskQueryInfo is null");
        }

        Set<Integer> list = new HashSet<Integer>();
        list.add(ReviewTaskModuserLevel.INOOUT.getId());
        if (UserRoleType.isAdminRoleType(u.getRoleType())) {
            list.add(ReviewTaskModuserLevel.INSIDE.getId());
            list.add(ReviewTaskModuserLevel.OUTSIDE.getId());
        } else if (UserRoleType.isOutUserRoleType(u.getRoleType())) {
            list.add(ReviewTaskModuserLevel.OUTSIDE.getId());
        } else if (UserRoleType.isInnerUserRoleType(u.getRoleType())) {
            list.add(ReviewTaskModuserLevel.INSIDE.getId());
        }

        PageHelper.startPage(page, size);
        return mainTaskDao.getTaskListByTaskInfo(taskQueryInfo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public DistributeReviewGroupResult getTasksByGroup(ReviewTask task, ReviewGroup group, User user) {

        // 更新group状态，如果group的状态为已完成，则不更新
        if (!GroupStatus.isFinished(group.getStatus())) {
            group.setStatus(GroupStatus.STARTED.getId());
            group.setStartTime(new Date());
            group.setModifyUserIdReview(user.getId());
            group.setModifyUserNameReview(user.getUserName());
            long muid = group.getModifyUserId();
            group.setModifyUserName(userService.findById(muid).getUserName());
            reviewGroupService.updateByIdSelective(group);
        }

        // 如果task状态为未开始，则需要设置为进行中
        if (TaskStatus.isNOTSTARTED(task.getStatus())) {
            task.setStatus(TaskStatus.STARTED.getId());
            this.updateByIdSelective(task);
        }

        // 生成结果
        DistributeReviewGroupResult result = reviewAdTaskService.getReviewTasksByReviewGroup(group, task);

        ReviewCountVo vo = new ReviewCountVo();
        vo.setReviewTaskId(task.getId().longValue());
        Integer count = reviewGroupService.getAdCount(vo);
        result.setAdNumAll(count == null ? 0 : count);

        count = reviewGroupService.getGroupCount(vo);
        result.setGroupNumAll(count == null ? 0 : count);

        vo.setStatus(GroupStatus.FINISHED.getId());

        count = reviewGroupService.getAdCount(vo);
        result.setAdNumDone(count == null ? 0 : count);

        count = reviewGroupService.getGroupCount(vo);
        result.setGroupNumDone(count == null ? 0 : count);

        vo.setUserId(userService.getCurrentLoginUser().getId());
        result.setHistoryGroup(reviewGroupService.getHistoryGroups(vo));
        result.setIsReview(1);

        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void deleteByTaskId(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return;
        }
        
        User u = userService.getCurrentLoginUser();

        for (Integer id : ids) {
            ReviewTask t = this.findById(id.longValue());
            if (t != null) {
                this.deleteById(t.getId().longValue());
                reviewTaskConditionService.deleteById(t.getTaskCondition().longValue());
                reviewGroupService.deleteByTaskId(id);
                reviewAdTaskService.deleteByTaskId(id);
                operationRecordService.insertNewOperation(OperationType.OP_REVIEW_DELETE.getId(),
                        "[" + id + ", " + t.getTaskName() + "]", u.getId().longValue());
            }
        }
    }
}
