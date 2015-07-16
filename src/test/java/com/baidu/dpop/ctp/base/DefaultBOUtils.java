package com.baidu.dpop.ctp.base;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.baidu.dpop.ctp.adtag.bo.AdTag;
import com.baidu.dpop.ctp.adtag.bo.GeneralTag;
import com.baidu.dpop.ctp.adtag.utils.AdTagUtils;
import com.baidu.dpop.ctp.group.bo.Group;
import com.baidu.dpop.ctp.group.constant.GroupDataType;
import com.baidu.dpop.ctp.group.constant.GroupStatus;
import com.baidu.dpop.ctp.group.vo.DistributeGroupResult;
import com.baidu.dpop.ctp.mainTask.bo.Task;
import com.baidu.dpop.ctp.mainTask.constant.BeidouMcType;
import com.baidu.dpop.ctp.mainTask.constant.DSPMcType;
import com.baidu.dpop.ctp.mainTask.constant.ModuserLevel;
import com.baidu.dpop.ctp.mainTask.constant.NewDSPMcType;
import com.baidu.dpop.ctp.mainTask.constant.QiushiMcType;
import com.baidu.dpop.ctp.mainTask.constant.TaskStatus;
import com.baidu.dpop.ctp.review.bo.ReviewAdTask;
import com.baidu.dpop.ctp.task.bo.BeidouTask;
import com.baidu.dpop.ctp.task.bo.DSPTask;
import com.baidu.dpop.ctp.task.bo.NewDSPTask;
import com.baidu.dpop.ctp.task.bo.QiushiTask;
import com.baidu.dpop.ctp.task.vo.PresentedTask;
import com.baidu.dpop.ctp.user.bo.User;

public class DefaultBOUtils {

    public static final String DEFAULT_COMPANY = "百度中国";
    public static final String DEFAULT_URL = "www.baidu.com";
    public static final String DEFAULT_TITLE = "测试广告";
    public static final String DEFAULT_DESCRIPTION = "测试";
    public static final Integer DEFAULT_ID = 1;
    public static final Integer DEFAULT_SIZE = 100;
    public static final Integer DEFAULT_TRADE = 0;
    public static final Integer DEFAULT_ADTYPE = 0;

    public static final String DEFAULT_USERNAME = "user";
    public static final String DEFAULT_PASSWORD = "123456";

    public static final String DEFUALT_ADDUSER = "System";
    public static final String DEFAULT_TASKNAME = "task1";
    public static final Byte DEFAULT_TASKTYPE = (byte) 3;

    public static final Long DEFAULT_PRIORITY = 1L;
    public static final Integer DEFAULT_ADNUM = 10;

    public static final String DEFAULT_TAG = getTagSample('1', '1', '1', '1');
    public static final String DEFAULT_TAG_NEW = getTagSample('0', '0', '0', '0');

    public static User getUser(Integer id, String userName, Number roleType) {
        User user = new User();
        user.setId(id);
        user.setUserName(userName);
        user.setPassword(DEFAULT_PASSWORD);
        user.setRoleType(roleType.byteValue());
        user.setAddTime(new Date());
        user.setUpdateTime(new Date());
        user.setUpdateUser(-1);
        user.setDeleteFlag(false);
        user.setLoginTryTimes(0);
        return user;
    }

    public static Task getTask(Integer id, String taskName) {
        Task task = new Task();
        task.setTaskName(taskName);
        task.setId(id);
        task.setModuserLevel(ModuserLevel.OUTSIDE.getId());
        task.setAddTime(new Date());
        task.setAddUser(DEFUALT_ADDUSER);
        task.setStatus(TaskStatus.NOT_STARTED.getId());
        task.setTaskType(DEFAULT_TASKTYPE);
        return task;
    }

    public static Group getGroup(Long id, Long groupId, Byte dataType) {
        Group group = new Group();
        group.setId(id);
        group.setGroupId(groupId);
        group.setDataType(dataType);
        group.setTaskId(DEFAULT_ID);
        group.setTaskName(DEFAULT_TASKNAME);
        group.setAdNum(DEFAULT_ADNUM);
        group.setPriority(DEFAULT_PRIORITY);
        group.setStatus(GroupStatus.NOT_STARTED.getId());
        group.setModifyUserId(-1);
        return group;
    }

    public static BeidouTask getBeidouTask(Long id, Long adId, Long groupId, Integer wuliaoType) {
        BeidouTask task = new BeidouTask();
        task.setId(id);
        task.setAdId(adId);
        task.setGroupId(groupId);
        task.setPlanId(DEFAULT_ID.longValue());
        task.setUserId(DEFAULT_ID.longValue());
        task.setWuliaoType(wuliaoType);
        task.setCompany(DEFAULT_COMPANY);
        task.setWebsite(DEFAULT_URL);
        task.setShowUrl(DEFAULT_URL);
        task.setTargetUrl(DEFAULT_URL);
        task.setWidth(DEFAULT_SIZE);
        task.setHeight(DEFAULT_SIZE);
        task.setTitle(DEFAULT_TITLE);
        task.setDescription1(DEFAULT_DESCRIPTION);
        task.setDescription2(DEFAULT_DESCRIPTION);
        task.setAdTradeIdLevel2(DEFAULT_TRADE);
        task.setAdTradeIdLevel3(DEFAULT_TRADE);
        task.setAdTag(DEFAULT_TAG_NEW);
        task.setMcId(DEFAULT_ID);
        task.setMcVersionId(DEFAULT_ID);
        task.setPriority(DEFAULT_PRIORITY.intValue());
        task.setSecondPriority(DEFAULT_PRIORITY.intValue());
        task.setModuserLevel(ModuserLevel.OUTSIDE.getId());
        task.setTaskId(DEFAULT_ID);
        task.setTaskName(DEFAULT_TASKNAME);
        task.setAdType(DEFAULT_ADTYPE);
        task.setChatime(new Date());
        task.setTaskType(DEFAULT_TASKTYPE);
        return task;
    }

    public static QiushiTask getQiushiTask(Long id, Long adId, Long groupId, Integer wuliaoType) {
        QiushiTask task = new QiushiTask();
        task.setId(id);
        task.setAdId(adId);
        task.setGroupId(groupId);
        task.setPlanId(DEFAULT_ID.longValue());
        task.setUserId(DEFAULT_ID.longValue());
        task.setWuliaoType(wuliaoType);
        task.setCompany(DEFAULT_COMPANY);
        task.setWebsite(DEFAULT_URL);
        task.setShowUrl(DEFAULT_URL);
        task.setTargetUrl(DEFAULT_URL);
        task.setWidth(DEFAULT_SIZE);
        task.setHeight(DEFAULT_SIZE);
        task.setTitle(DEFAULT_TITLE);
        task.setDescription1(DEFAULT_DESCRIPTION);
        task.setDescription2(DEFAULT_DESCRIPTION);
        task.setAdTradeIdLevel2(DEFAULT_TRADE);
        task.setAdTradeIdLevel3(DEFAULT_TRADE);
        task.setAdTag(DEFAULT_TAG_NEW);
        task.setMcId(DEFAULT_ID);
        task.setMcVersionId(DEFAULT_ID);
        task.setPriority(DEFAULT_PRIORITY.intValue());
        task.setSecondPriority(DEFAULT_PRIORITY.intValue());
        task.setModuserLevel(ModuserLevel.OUTSIDE.getId());
        task.setTaskId(DEFAULT_ID);
        task.setTaskName(DEFAULT_TASKNAME);
        task.setChatime(new Date());
        task.setTaskType(DEFAULT_TASKTYPE);
        return task;
    }

    public static DSPTask getDSPTask(Long id, Long adId, Long groupId, Integer wuliaoType) {
        DSPTask task = new DSPTask();
        task.setId(id);
        task.setAdId(adId);
        task.setUserId(groupId);
        task.setDspId(DEFAULT_ID.longValue());
        task.setDspname(DEFAULT_COMPANY);
        task.setWuliaoType(wuliaoType);
        task.setNickname(DEFAULT_COMPANY);
        task.setWebsite(DEFAULT_URL);
        task.setLandingPage(DEFAULT_URL);
        task.setWidth(DEFAULT_SIZE);
        task.setHeight(DEFAULT_SIZE);
        task.setAdTradeIdLevel2(DEFAULT_TRADE);
        task.setAdTradeIdLevel3(DEFAULT_TRADE);
        task.setAdTag(DEFAULT_TAG_NEW);
        task.setMcId(DEFAULT_ID);
        task.setMcVersionId(DEFAULT_ID);
        task.setPriority(DEFAULT_PRIORITY.intValue());
        task.setSecondPriority(DEFAULT_PRIORITY.intValue());
        task.setModuserLevel(ModuserLevel.OUTSIDE.getId());
        task.setTaskId(DEFAULT_ID);
        task.setTaskName(DEFAULT_TASKNAME);
        task.setChatime(new Date());
        task.setTaskType(DEFAULT_TASKTYPE);
        return task;
    }

    public static NewDSPTask getNewDSPTask(Long id, Long adId, Long groupId, Integer wuliaoType) {
        NewDSPTask task = new NewDSPTask();
        task.setId(id);
        task.setAdId(adId);
        task.setUserId(groupId);
        task.setAdverId(DEFAULT_ID.longValue());
        task.setWuliaoType(wuliaoType);
        task.setCompany(DEFAULT_COMPANY);
        task.setWebsite(DEFAULT_URL);
        task.setShowUrl(DEFAULT_URL);
        task.setTargetUrl(DEFAULT_URL);
        task.setWidth(DEFAULT_SIZE);
        task.setHeight(DEFAULT_SIZE);
        task.setTitle(DEFAULT_TITLE);
        task.setDescription(DEFAULT_DESCRIPTION);
        task.setAdTradeIdLevel2(DEFAULT_TRADE);
        task.setAdTradeIdLevel3(DEFAULT_TRADE);
        task.setAdTag(DEFAULT_TAG_NEW);
        task.setTaskId(DEFAULT_ID);
        task.setTaskName(DEFAULT_TASKNAME);
        task.setTagVersion(0);
        task.setUrl(DEFAULT_URL);
        task.setChatime(new Date());
        task.setTaskType(DEFAULT_TASKTYPE);
        return task;
    }

    public static ReviewAdTask getReviewAdTask(Long refId, Long refGroupId, Long groupId, Number dataType) {
        ReviewAdTask rtask = new ReviewAdTask();
        rtask.setId(refId);
        rtask.setAddTime(new Date());
        rtask.setAddUser(DEFAULT_USERNAME);
        rtask.setAdTag(DEFAULT_TAG);
        rtask.setAdTagReview(DEFAULT_TAG);
        rtask.setAdTradeIdLevel3(DEFAULT_TRADE);
        rtask.setAdTradeIdLevel3Review(DEFAULT_TRADE);
        rtask.setAdTradeNameLevel3("NULL");
        rtask.setAdTradeNameLevel3Review("NULL");
        rtask.setAssigned((byte) 0);
        rtask.setComment("");
        rtask.setCommentReview("");
        rtask.setDataType(dataType.byteValue());
        rtask.setGroupId(refGroupId);
        rtask.setGroupIdReview(groupId);
        rtask.setRefAdId(refId);
        rtask.setTagTime(new Date());
        rtask.setTagUser(DEFAULT_USERNAME);
        rtask.setTaskId(DEFAULT_ID.longValue());
        rtask.setUpdateTime(new Date());
        rtask.setUpdateUser(DEFAULT_USERNAME);
        rtask.setWuliaoType(1);
        return rtask;
    }

    public static List<BeidouTask> getBeidouTasks(int size, Long groupId) {
        List<BeidouTask> list = new ArrayList<BeidouTask>();
        for (int i = 0; i < size; i++) {
            BeidouTask task = getBeidouTask(i + 0L, i + 0L, groupId, getValidMcType(GroupDataType.BEIDOU.getId(), i));
            list.add(task);
        }

        return list;
    }

    public static List<QiushiTask> getQiushiTasks(int size, Long groupId) {
        List<QiushiTask> list = new ArrayList<QiushiTask>();
        for (int i = 0; i < size; i++) {
            QiushiTask task = getQiushiTask(i + 0L, i + 0L, groupId, getValidMcType(GroupDataType.QIUSHI.getId(), i));
            list.add(task);
        }

        return list;
    }

    public static List<DSPTask> getDSPTasks(int size, Long groupId) {
        List<DSPTask> list = new ArrayList<DSPTask>();
        for (int i = 0; i < size; i++) {
            DSPTask task = getDSPTask(i + 0L, i + 0L, groupId, getValidMcType(GroupDataType.DSP.getId(), i));
            list.add(task);
        }

        return list;
    }

    public static List<NewDSPTask> getNewDSPTasks(int size, Long groupId) {
        List<NewDSPTask> list = new ArrayList<NewDSPTask>();
        for (int i = 0; i < size; i++) {
            NewDSPTask task = getNewDSPTask(i + 0L, i + 0L, groupId, getValidMcType(GroupDataType.NEWDSP.getId(), i));
            list.add(task);
        }

        return list;
    }

    public static List<ReviewAdTask> getReviewAdTasks(int size, Long groupId) {
        List<ReviewAdTask> list = new ArrayList<ReviewAdTask>();
        for (int i = 0; i < size; i++) {
            ReviewAdTask task =
                    getReviewAdTask(i + 0L, i + 0L, groupId, getValidMcType(GroupDataType.NEWDSP.getId(), i));
            list.add(task);
        }
        return list;
    }

    public static Integer getValidMcType(Number dataType, Number id) {
        int type = id.intValue() < 0 ? -id.intValue() : id.intValue();
        if (GroupDataType.isBeidou(dataType)) {
            switch (type % 5) {
                case 0:
                    return BeidouMcType.TXT.getId();
                case 1:
                    return BeidouMcType.IMG.getId();
                case 2:
                    return BeidouMcType.IMGTXT.getId();
                case 3:
                    return BeidouMcType.FLASH.getId();
                case 4:
                    return BeidouMcType.HTML.getId();
                default:
                    return BeidouMcType.TXT.getId();
            }
        } else if (GroupDataType.isQiushi(id)) {
            switch (type % 7) {
                case 0:
                    return QiushiMcType.INVALID.getId();
                case 1:
                    return QiushiMcType.TXT.getId();
                case 2:
                    return QiushiMcType.IMG.getId();
                case 3:
                    return QiushiMcType.SMALLIMGTXT.getId();
                case 4:
                    return QiushiMcType.BIGIMGTXT.getId();
                case 5:
                    return QiushiMcType.RICHMEDIA.getId();
                case 6:
                    return QiushiMcType.VIDEO.getId();
                default:
                    return QiushiMcType.TXT.getId();
            }
        } else if (GroupDataType.isDSP(id)) {
            switch (type % 3) {
                case 0:
                    return DSPMcType.IMG.getId();
                case 1:
                    return DSPMcType.FLASH.getId();
                case 2:
                    return DSPMcType.VIDEO.getId();
                default:
                    return DSPMcType.IMG.getId();
            }
        } else if (GroupDataType.isNewDsp(id)) {
            switch (type % 3) {
                case 0:
                    return NewDSPMcType.FLASH.getId();
                case 1:
                    return NewDSPMcType.HTML.getId();
                case 2:
                    return DSPMcType.VIDEO.getId();
                default:
                    return DSPMcType.IMG.getId();
            }
        }

        return 1;
    }

    public static DistributeGroupResult getDefaultResult(Number dataType) {
        DistributeGroupResult result = new DistributeGroupResult();
        result.setAccountId(1L);
        result.setCompanyName(DEFAULT_COMPANY);
        result.setCompanyUrl(DEFAULT_URL);
        result.setGroup(getGroup(DEFAULT_ID.longValue(), DEFAULT_ID.longValue(), dataType.byteValue()));
        List<PresentedTask> list = new ArrayList<PresentedTask>();
        for (int i = 0; i < 10; i++) {
            if (GroupDataType.isBeidou(dataType)) {
                list.add(new PresentedTask(getBeidouTask(i + 0L, i + 0L, DEFAULT_ID.longValue(), i)));
            } else if (GroupDataType.isQiushi(dataType)) {
                list.add(new PresentedTask(getQiushiTask(i + 0L, i + 0L, DEFAULT_ID.longValue(), i)));
            } else if (GroupDataType.isDSP(dataType)) {
                list.add(new PresentedTask(getDSPTask(i + 0L, i + 0L, DEFAULT_ID.longValue(), i)));
            } else if (GroupDataType.isNewDsp(dataType)) {
                list.add(new PresentedTask(getNewDSPTask(i + 0L, i + 0L, DEFAULT_ID.longValue(), i)));

            }
        }
        result.setList(list);
        return result;
    }

    public static List<AdTag> getAdTags(Integer size, Number dataType) {
        List<AdTag> result = new ArrayList<AdTag>();
        for (int i = 0; i < size; i++) {
            AdTag tag = new AdTag();
            tag.setId(i + 0L);
            tag.setRefId(i + 0L);
            tag.setRefGroupId(DEFAULT_ID + 0L);
            tag.setTaskId(DEFAULT_ID);
            tag.setAdTag(DEFAULT_TAG);
            tag.setAdTradeIdLevel3(510101);
            tag.setComment("");
            tag.setUpdateTime(new Date());
            tag.setUpdateUser("user1");
            tag.setGeneralWuliaoType(1);
            tag.setDataType(dataType.intValue());
            result.add(tag);
        }
        return result;
    }

    public static Map<Long, AdTag> getAdTags(List<AdTag> list) {
        Map<Long, AdTag> map = new HashMap<Long, AdTag>();
        for (AdTag tag : list) {
            map.put(tag.getRefId(), tag);
        }
        return map;
    }

    public static Set<String> getAllTagType() {
        Set<String> set = new HashSet<String>();
        for (GeneralTag tag : AdTagUtils.TAGLIST) {
            set.add(tag.getName());
        }
        return set;
    }
    
    public static String getTagSample(Character... chars) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < AdTagUtils.TAGLIST.size(); i++) {
            sb.append(i < chars.length ? chars[i] : '-');
        }
        return sb.toString();
    }

}
