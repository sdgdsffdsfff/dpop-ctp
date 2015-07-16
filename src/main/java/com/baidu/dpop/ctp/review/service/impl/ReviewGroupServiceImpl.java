/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */

package com.baidu.dpop.ctp.review.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.baidu.dpop.frame.core.base.BaseService;
import com.baidu.dpop.frame.core.base.GenericMapperDao;
import com.baidu.dpop.ctp.adtag.bo.AdTag;
import com.baidu.dpop.ctp.adtag.vo.SubmitTagInfo;
import com.baidu.dpop.ctp.common.exception.BaseRuntimeException;
import com.baidu.dpop.ctp.common.mybatis.page.PageHelper;
import com.baidu.dpop.ctp.common.mybatis.page.PageInfo;
import com.baidu.dpop.ctp.group.bo.Group;
import com.baidu.dpop.ctp.group.constant.GroupStatus;
import com.baidu.dpop.ctp.group.service.GroupService;
import com.baidu.dpop.ctp.mainTask.constant.TaskStatus;
import com.baidu.dpop.ctp.outerinvoke.service.OuterInvokeService;
import com.baidu.dpop.ctp.review.bo.ReviewAdTask;
import com.baidu.dpop.ctp.review.bo.ReviewGroup;
import com.baidu.dpop.ctp.review.bo.ReviewTask;
import com.baidu.dpop.ctp.review.dao.ReviewGroupDao;
import com.baidu.dpop.ctp.review.service.ReviewAdTaskService;
import com.baidu.dpop.ctp.review.service.ReviewGroupService;
import com.baidu.dpop.ctp.review.service.ReviewTaskService;
import com.baidu.dpop.ctp.review.vo.QueryConditionVo;
import com.baidu.dpop.ctp.review.vo.ReviewCountVo;
import com.baidu.dpop.ctp.review.vo.ReviewInfoItem;
import com.baidu.dpop.ctp.review.vo.ReviewPageInfoVo;
import com.baidu.dpop.ctp.task.service.GeneralTaskService;
import com.baidu.dpop.ctp.user.bo.User;
import com.baidu.dpop.ctp.user.service.UserService;
import com.baidu.dpop.ctp.review.vo.WrongAdDetailVo;

@Service
public class ReviewGroupServiceImpl extends BaseService<ReviewGroup, Long> implements ReviewGroupService {

    @Autowired
    private ReviewGroupDao groupDao;

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewAdTaskService reviewAdTaskService;

    @Autowired
    private ReviewTaskService reviewTaskService;

    @Autowired
    private GeneralTaskService generalTaskService;

    @Autowired
    OuterInvokeService outerInvokeService;

    @Value("${dpop.ctp.maxReviewTasks}")
    private long maxReviewTasks;

    @Override
    public GenericMapperDao<ReviewGroup, Long> getDao() {
        return groupDao;
    }

    @Value("${dpop.ctp.invoke.taskInsertNumber}")
    private long defaultInsertion;

    @Override
    public ReviewGroup distributeNewReviewGroup(ReviewTask task) {
        int count = groupDao.selectUnstartCount(task.getId());
        if (count == 0) {
            return null;
        }
        Random r = new Random();
        count = r.nextInt(count > 200 ? 200 : count);
        ReviewGroup g = groupDao.selectRandomGroup(task.getId(), count);

        if (g == null) {
            return null;
        }
        return g;
    }

    @Override
    public ReviewGroup getWithUserNameById(Long groupId) {
        ReviewGroup rg = this.groupDao.selectWithUserNameById(groupId);
        User u = userService.findById(rg.getModifyUserIdReview().longValue());
        rg.setModifyUserNameReview(u == null ? null : u.getUserName());
        return rg;
    }

    @Override
    public Integer getGroupCount(ReviewCountVo vo) {
        return this.groupDao.selectGroupCount(vo);
    }

    @Override
    public Integer getAdCount(ReviewCountVo vo) {
        return this.groupDao.selectAdCount(vo);
    }

    @Override
    public List<ReviewGroup> getReviewGroupsByReviewTaskId(Long reviewTaskId) {
        return this.groupDao.selectReviewGroupsByReviewTaskId(reviewTaskId);
    }

    @Override
    public ReviewGroup getStartedGroupByUser(ReviewTask task, User user) {
        return this.groupDao.selectStartedGroupByUser(task.getId(), user.getId());
    }

    @Override
    public List<ReviewGroup> getHistoryGroups(ReviewCountVo vo) {
        return this.groupDao.selectHistoryGroups(vo);
    }

    @Override
    public ReviewPageInfoVo getNotReviewedPageInfo(QueryConditionVo vo) {
        Assert.notNull(vo);
        Assert.notNull(vo.getReviewTaskId());
        PageHelper.startPage(vo.getPageNo(), vo.getPageSize());

        List<ReviewInfoItem> data = this.groupDao.selectNotReviewedPageInfo(vo);

        PageInfo<ReviewInfoItem> pageInfo = new PageInfo<ReviewInfoItem>(data);
        ReviewPageInfoVo ret = new ReviewPageInfoVo();
        ret.setList(data);
        ret.setPage(pageInfo.getPageNum());
        ret.setSize(pageInfo.getPageSize());
        ret.setTotal(pageInfo.getTotal());

        return ret;
    }

    @Override
    public ReviewPageInfoVo getReviewedRightPageInfo(QueryConditionVo vo) {
        Assert.notNull(vo);
        Assert.notNull(vo.getReviewTaskId());
        PageHelper.startPage(vo.getPageNo(), vo.getPageSize());

        List<ReviewInfoItem> data = this.groupDao.selectReviewedRightPageInfo(vo);

        PageInfo<ReviewInfoItem> pageInfo = new PageInfo<ReviewInfoItem>(data);
        ReviewPageInfoVo ret = new ReviewPageInfoVo();
        ret.setList(data);
        ret.setPage(pageInfo.getPages());
        ret.setSize(pageInfo.getSize());
        ret.setTotal(pageInfo.getTotal());

        return ret;
    }

    @Override
    public ReviewPageInfoVo getReviewedWrongPageInfo(QueryConditionVo vo) {
        Assert.notNull(vo);
        Assert.notNull(vo.getReviewTaskId());
        PageHelper.startPage(vo.getPageNo(), vo.getPageSize());

        // 获取审核有误列表数据
        List<ReviewInfoItem> data = this.groupDao.selectReviewedWrongPageInfo(vo);

        // set 分页信息
        PageInfo<ReviewInfoItem> pageInfo = new PageInfo<ReviewInfoItem>(data);
        ReviewPageInfoVo ret = new ReviewPageInfoVo();
        ret.setList(data);
        ret.setPage(pageInfo.getPages());
        ret.setSize(pageInfo.getSize());
        ret.setTotal(pageInfo.getTotal());

        return ret;
    }

    @Override
    public Integer getIsReviewedAdCount(Integer reviewTaskId) {
        return this.groupDao.selectIsReviewedAdCount(reviewTaskId);
    }

    @Override
    public List<WrongAdDetailVo> getReviewedAdDetail(Integer reviewTaskId, Integer resultType) {
        return this.groupDao.selectReviewedAdDetail(reviewTaskId, resultType);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public int batchInsert(List<ReviewGroup> list, Integer taskId, String taskName, Integer groupNum) {
        List<ReviewGroup> insert = new ArrayList<ReviewGroup>();
        if (groupNum == null || groupNum > list.size()) {
            groupNum = list.size();
        }

        for (int i = 0; i < groupNum; i++) {
            ReviewGroup g = list.get(i);
            g.setTaskIdReview(taskId);
            g.setTaskNameReview(taskName);
            g.setStatus(GroupStatus.NOT_STARTED.getId());
            insert.add(g);
        }

        int insertion = 0;
        int result = 0;
        List<ReviewGroup> temp = new ArrayList<ReviewGroup>();
        for (ReviewGroup g : insert) {
            temp.add(g);
            insertion++;
            if (insertion == defaultInsertion) {
                result += groupDao.batchInsert(temp);
                insertion = 0;
                temp = new ArrayList<ReviewGroup>();
            }
        }

        if (temp.size() > 0) {
            result += groupDao.batchInsert(temp);
        }
        return result;
    }

    private List<ReviewGroup> getInsertGroups(Map<Long, Integer> groups, Integer groupNum, boolean isFull) {

        if (isFull && (groupNum == null || groupNum.intValue() == groups.size())) {
            throw new BaseRuntimeException("选择数目太多!");
        }

        if (groupNum == null) {
            groupNum = -1;
        } else if (groupNum.intValue() == 0) {
            throw new BaseRuntimeException("设定数量应该大于0！");
        }

        // 因为获取到的信息本身不含原group的主键id，因此需要选出原group
        List<Long> list = new ArrayList<Long>(groups.keySet());
        List<Group> tempGroup = groupService.batchGet(list);
        Collections.shuffle(tempGroup); // 打乱顺序

        List<ReviewGroup> result = new ArrayList<ReviewGroup>();
        int num = 0;
        for (Group g : tempGroup) {
            if (g.getModifyUserId() != null) {
                if (groups.containsKey(g.getId())) {
                    ReviewGroup rg = new ReviewGroup();
                    rg.setArgs(g);
                    rg.setAdNumReview(groups.get(g.getId()));
                    result.add(rg);
                    num++;
                }

                if (num == groupNum) {
                    break;
                }
            }
        }

        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public int createReviewTask(List<AdTag> adList, ReviewTask rtask) {

        // 统计group数量
        Map<Long, Integer> groupMap = new HashMap<Long, Integer>();
        for (AdTag tag : adList) {
            Integer count = groupMap.get(tag.getRefGroupId());
            if (count == null) {
                groupMap.put(tag.getRefGroupId(), 1);
            } else {
                groupMap.put(tag.getRefGroupId(), count + 1);
            }
        }

        List<ReviewGroup> list = getInsertGroups(groupMap, rtask.getGroupNum(), adList.size() == maxReviewTasks);
        int result = batchInsert(list, rtask.getId(), rtask.getTaskName(), rtask.getGroupNum());
        List<ReviewGroup> temp = groupDao.selectReviewGroupsByReviewTaskId(rtask.getId().longValue());
        Map<Long, ReviewGroup> map = new HashMap<Long, ReviewGroup>();
        for (ReviewGroup rg : temp) {
            map.put(rg.getTagGroupId(), rg);
        }

        User u = userService.getCurrentLoginUser();
        List<ReviewAdTask> insertList = new ArrayList<ReviewAdTask>();

        Date addTime = new Date();
        for (AdTag tag : adList) {
            // 由于可能有数量限制，所以并非所有的group都会插入，因此需要筛选
            ReviewGroup group = map.get(tag.getRefGroupId());

            // group不是null，说明此ad是被选中的
            if (group != null) {
                ReviewAdTask task = new ReviewAdTask(tag, addTime, u.getUserName());
                task.setGroupIdReview(group.getId());
                task.setTaskId(rtask.getId().longValue());
                insertList.add(task);
            }

        }
        reviewAdTaskService.batchInsertTasks(insertList);
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void submitReviewTasks(List<SubmitTagInfo> list, Long groupId, User u) {
        ReviewGroup group = this.findById(groupId);

        if (group == null) {
            throw new BaseRuntimeException("提交的id有误，提交失败！");
        }

        if (!group.getModifyUserIdReview().equals(u.getId())) {
            throw new BaseRuntimeException("NOT BELONG TO THIS USER");
        }

        Date date = new Date();
        group.setModifyUserIdReview(u.getId());
        group.setModifyUserNameReview(u.getUserName());
        group.setDoneTime(date);
        group.setStatus(GroupStatus.FINISHED.getId());
        this.updateByIdSelective(group);
        
        ReviewTask rtask = reviewTaskService.findById(group.getTaskIdReview().longValue());

        List<ReviewAdTask> submit = new ArrayList<ReviewAdTask>();
        List<Long> ids = new ArrayList<Long>();
        for (SubmitTagInfo info : list) {
            AdTag tag = info.toAdTag(rtask.getTaskType().intValue());
            ReviewAdTask task = new ReviewAdTask();
            task.setId(info.getId());
            task.setAdTagReview(tag.getAdTag());
            task.setCommentReview(tag.getComment());
            task.setAdTradeIdLevel3Review(tag.getAdTradeIdLevel3());
            submit.add(task);
            ids.add(tag.getRefId());
        }

        reviewAdTaskService.submitReviewTasks(submit, u, new Date());

        int unstart = groupDao.selectUnfinishedCount(group.getTaskIdReview());
        if (unstart == 0) {
            ReviewTask task = new ReviewTask();
            task.setId(group.getTaskIdReview());
            task.setStatus(TaskStatus.FINISHED.getId());
            reviewTaskService.updateByIdSelective(task);
        }

        outerInvokeService.doUnBlockedAssignTask(true, ids, list, group.getDataType().intValue(),
                group.getTaskIdReview(), date);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void giveUpGroup(ReviewGroup group) {
        Assert.notNull(group);
        group.setDoneTime(null);
        group.setModifyUserId(null);
        group.setStartTime(null);
        group.setStatus(GroupStatus.NOT_STARTED.getId());
        groupDao.updateByPrimaryKeySelective(group);
    }

    @Override
    public void recycleAssignGroups(Date beginTime) {
        Assert.notNull(beginTime);
        this.groupDao.recycleAssignGroups(beginTime);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
    public void deleteByTaskId(Integer taskId) {
        this.groupDao.deleteByTaskId(taskId);
    }

}
