package com.baidu.dpop.ctp.group.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.dpop.ctp.adtag.bo.AdTag;
import com.baidu.dpop.ctp.adtag.service.AdTagService;
import com.baidu.dpop.ctp.adtag.vo.SubmitTagInfo;
import com.baidu.dpop.ctp.base.DefaultBOUtils;
import com.baidu.dpop.ctp.common.exception.BaseRuntimeException;
import com.baidu.dpop.ctp.group.bo.Group;
import com.baidu.dpop.ctp.group.constant.GroupStatus;
import com.baidu.dpop.ctp.group.dao.GroupDao;
import com.baidu.dpop.ctp.group.vo.TrendInfoGetVo;
import com.baidu.dpop.ctp.invoke.vo.GroupCountVo;
import com.baidu.dpop.ctp.mainTask.bo.Task;
import com.baidu.dpop.ctp.mainTask.service.TaskService;
import com.baidu.dpop.ctp.outerinvoke.service.OuterInvokeService;
import com.baidu.dpop.ctp.task.bo.GeneralTask;
import com.baidu.dpop.ctp.task.service.GeneralTaskService;
import com.baidu.dpop.ctp.user.bo.User;
import com.baidu.dpop.ctp.user.constant.UserRoleType;
import com.baidu.dpop.frame.core.context.DpopPropertyUtils;

import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.Verifications;

public class GroupServiceImplTest {

    @Tested
    GroupServiceImpl groupServiceImpl;

    @Injectable
    GroupDao groupDao;

    @Injectable
    AdTagService adTagService;

    @Injectable
    GeneralTaskService generalTaskService;

    @Injectable
    TaskService taskService;

    @Injectable
    OuterInvokeService outerInvokeService;

    @Before
    public void setUp() {
        new MockUp<DpopPropertyUtils>() {

            @Mock
            public String getProperty(String key) {
                return "1000";
            }
        };
    }

    @Test
    public void testGetGroup() {
        Group g = DefaultBOUtils.getGroup(1L, 1L, (byte) 0);
        groupServiceImpl.getGroup(g);
        new Verifications() {
            {
                groupDao.selectGroup((Group) any);
                times = 1;
            }
        };
    }

    @Test
    public void testDistributeNewGroup() {
        new NonStrictExpectations() {
            {
                groupDao.selectUnstartCount((Integer) any);
                result = 0;
            }
        };
        Assert.assertNull(groupServiceImpl.distributeNewGroup(DefaultBOUtils.getTask(1, "task1")));

        new NonStrictExpectations() {
            {
                groupDao.selectUnstartCount((Integer) any);
                result = 10;

                groupDao.selectRandomGroup((Integer) any, (Integer) any);
                result = DefaultBOUtils.getGroup(1L, 1L, (byte) 0);

                groupDao.updateByPrimaryKey((Group) any);
            }
        };
        Assert.assertNotNull(groupServiceImpl.distributeNewGroup(DefaultBOUtils.getTask(1, "task1")));
    }

    @Test
    public void testDistributeNewGroupNull() {
        new NonStrictExpectations() {
            {
                groupDao.selectUnstartCount((Integer) any);
                result = 0;
            }
        };
        Assert.assertNull(groupServiceImpl.distributeNewGroup(DefaultBOUtils.getTask(1, "task1")));

        new NonStrictExpectations() {
            {
                groupDao.selectUnstartCount((Integer) any);
                result = 10;

                groupDao.selectRandomGroup((Integer) any, (Integer) any);
                result = null;
            }
        };
        Assert.assertNull(groupServiceImpl.distributeNewGroup(DefaultBOUtils.getTask(1, "task1")));
    }

    @Test(expected = BaseRuntimeException.class)
    public void testDistributeNewGroupCountNull() {
        new NonStrictExpectations() {
            {
                groupDao.selectUnstartCount((Integer) any);
                result = null;
            }
        };
        groupServiceImpl.distributeNewGroup(DefaultBOUtils.getTask(1, "task1"));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetTestedGroup() {
        new NonStrictExpectations() {
            {
                groupDao.selectTestedGroup((List<GroupCountVo>) any);
                List<Group> list = new ArrayList<Group>();
                for (int i = 0; i < 5; i++) {
                    list.add(DefaultBOUtils.getGroup(i + 0L, i + 0L, (byte) 0));
                }
                result = list;
            }
        };

        List<GroupCountVo> list = new ArrayList<GroupCountVo>();
        for (int i = 0; i < 5; i++) {
            list.add(new GroupCountVo(DefaultBOUtils.DEFAULT_ID.longValue(), i + 0L, (byte) 0));
        }
        Map<GroupCountVo, Group> map = groupServiceImpl.getTestedGroup(list);
        Assert.assertEquals(5, map.size());
        Assert.assertEquals(3, map.get(list.get(3)).getId().intValue());

        Assert.assertEquals(0, groupServiceImpl.getTestedGroup(null).size());

        new NonStrictExpectations() {
            {
                groupDao.selectTestedGroup((List<GroupCountVo>) any);
                result = null;
            }
        };
        Assert.assertEquals(0, groupServiceImpl.getTestedGroup(null).size());
    }

    @Test
    public void testGetStartedGroupByUser() {

        groupServiceImpl.getStartedGroupByUser(DefaultBOUtils.getTask(1, "task1"),
                DefaultBOUtils.getUser(1, "user1", UserRoleType.INNER_ADMIN_USER.getId()));
        new Verifications() {
            {
                groupDao.selectStartedGroupByUser((Integer) any, (Integer) any);
                times = 1;
            }
        };
    }

    @Test
    public void testGetGroupByUser() {

        groupServiceImpl.getGroupByUser(DefaultBOUtils.getTask(1, "task1"),
                DefaultBOUtils.getUser(1, "user1", UserRoleType.INNER_ADMIN_USER.getId()));
        new Verifications() {
            {
                groupDao.selectHistoryGroups((Integer) any, (Integer) any);
                times = 1;
            }
        };
    }

    @Test
    public void testGetGroupListByCondition() {

        groupServiceImpl.getGroupListByCondition(1, 1, 1);
        new Verifications() {
            {
                groupDao.selectGroupListByCondition((Integer) any, (Integer) any, (Integer) any);
                times = 1;
            }
        };
    }

    @Test
    public void testGetUserStatisticsInfo() {

        groupServiceImpl.getUserStatisticsInfo(1);
        new Verifications() {
            {
                groupDao.selectUserStatisticsInfo((Integer) any);
                times = 1;
            }
        };
    }

    @Test
    public void testGetTagFollowInfoList() {
        groupServiceImpl.getTagFollowInfoList(0);
        new Verifications() {
            {
                groupDao.selectTagFollowInfoList((Integer) any);
                times = 1;
            }
        };

        new NonStrictExpectations() {
            {
                groupDao.selectTagFollowInfoList((Integer) any);
                result = null;
            }
        };
        Assert.assertEquals(0, groupServiceImpl.getTagFollowInfoList(0).size());
    }

    @Test
    public void testGetTrendInfo() {

        TrendInfoGetVo vo = new TrendInfoGetVo();
        vo.setTaskId(1);
        groupServiceImpl.getTrendInfo(vo);
        new Verifications() {
            {
                groupDao.selectTrendInfo((TrendInfoGetVo) any);
                times = 1;
            }
        };
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchGet() {

        groupServiceImpl.batchGet(Arrays.asList(1L, 2L));
        new Verifications() {
            {
                groupDao.batchSelect((List<Long>) any);
                times = 1;
            }
        };
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchInsert() {
        groupServiceImpl.batchInsert(new ArrayList<Group>());
        new Verifications() {
            {
                groupDao.batchInsert((List<Group>) any);
                times = 1;
            }
        };
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testSubmitTasks() {
        new NonStrictExpectations() {
            {
                groupDao.selectByPrimaryKey((Long) any);
                Group g = DefaultBOUtils.getGroup(1L, 1L, (byte) 0);
                g.setModifyUserId(1);
                result = g;
                
                taskService.findById((Long) any);
                result = DefaultBOUtils.getTask(1, "task1");

                groupDao.updateByPrimaryKey((Group) any);

                groupDao.selectTaskStatusById((Long) any);
                result = (byte) 1;

                adTagService.submit((List<AdTag>) any, (User) any, (Date) any);

                taskService.updateByIdSelective((Task) any);

                groupDao.selectUnfinishedCount((Integer) any);
                result = 0;

                generalTaskService.batchGetMapped((List<Long>) any, (Integer) any);
                Map<Long, GeneralTask> map = new HashMap<Long, GeneralTask>();
                for (int i = 0; i < DefaultBOUtils.DEFAULT_ADNUM; i++) {
                    map.put(i + 0L, DefaultBOUtils.getBeidouTask(i + 0L, i + 0L, 1L, 1));
                }
                result = map;

                outerInvokeService.doUnBlockedAssignTask((Boolean) any, (List<Long>) any, (List<SubmitTagInfo>) any,
                        (Integer) any, (Integer) any, (Date) any);
            }
        };

        List<SubmitTagInfo> list = new ArrayList<SubmitTagInfo>();
        Map<String, String> tag = new HashMap<String, String>();
        tag.put("beauty", "1");
        tag.put("cheat", "1");
        for (int i = 0; i < DefaultBOUtils.DEFAULT_ADNUM; i++) {
            SubmitTagInfo info = new SubmitTagInfo();
            info.setAdTradeIdLevel3(510101);
            info.setComment("");
            info.setId(i + 0L);
            info.setRefId(i + 0L);
            info.setTag(tag);
            info.setSample(DefaultBOUtils.getTagSample('1', '1', '1', '1', '1', '1'));
            list.add(info);
        }
        groupServiceImpl.submitTasks(list, 1L, DefaultBOUtils.getUser(1, "user1", 0));

        new NonStrictExpectations() {
            {
                groupDao.selectUnfinishedCount((Integer) any);
                result = 1;
            }
        };
        groupServiceImpl.submitTasks(list, 1L, DefaultBOUtils.getUser(1, "user1", 0));
    }

    @Test(expected = BaseRuntimeException.class)
    public void testSubmitTasksGroupNull() {
        new NonStrictExpectations() {
            {
                groupDao.selectByPrimaryKey((Long) any);
                result = null;
            }
        };
        groupServiceImpl.submitTasks(Arrays.asList(new SubmitTagInfo()), 1L, DefaultBOUtils.getUser(1, "user1", 0));
    }

    @Test(expected = BaseRuntimeException.class)
    public void testSubmitTasksGroupNoUpdateUser() {
        new NonStrictExpectations() {
            {
                groupDao.selectByPrimaryKey((Long) any);
                Group g = DefaultBOUtils.getGroup(1L, 1L, (byte) 0);
                g.setModifyUserId(1);
                result = g;
            }
        };
        groupServiceImpl.submitTasks(Arrays.asList(new SubmitTagInfo()), 1L, DefaultBOUtils.getUser(2, "user1", 0));
    }

    @Test(expected = BaseRuntimeException.class)
    public void testSubmitTasksTaskStatusWrong() {
        new NonStrictExpectations() {
            {
                groupDao.selectByPrimaryKey((Long) any);
                Group g = DefaultBOUtils.getGroup(1L, 1L, (byte) 0);
                g.setModifyUserId(1);
                result = g;

                groupDao.selectTaskStatusById((Long) any);
                result = (byte) 11;
            }
        };
        groupServiceImpl.submitTasks(Arrays.asList(new SubmitTagInfo()), 1L, DefaultBOUtils.getUser(1, "user1", 0));
    }

    @Test(expected = BaseRuntimeException.class)
    public void testSubmitTasksGroupUserWrong() {
        new NonStrictExpectations() {
            {
                groupDao.selectByPrimaryKey((Long) any);
                result = DefaultBOUtils.getGroup(1L, 1L, (byte) 0);
            }
        };
        groupServiceImpl.submitTasks(Arrays.asList(new SubmitTagInfo()), 1L, DefaultBOUtils.getUser(1, "user1", 0));
    }

    @Test
    public void testGiveUpGroup() {

        new NonStrictExpectations() {
            {
                groupDao.selectByPrimaryKey((Long) any);
                Group g = DefaultBOUtils.getGroup(1L, 1L, (byte) 0);
                g.setStatus(GroupStatus.STARTED.getId());
                g.setModifyUserId(1);
                result = g;

                groupDao.updateByPrimaryKey((Group) any);
            }
        };

        User u = DefaultBOUtils.getUser(1, "user1", UserRoleType.INNER_ADMIN_USER.getId());
        Assert.assertTrue(groupServiceImpl.giveUpGroup(1L, u));

        new NonStrictExpectations() {
            {
                groupDao.selectByPrimaryKey((Long) any);
                Group g = DefaultBOUtils.getGroup(1L, 1L, (byte) 0);
                g.setStatus(GroupStatus.STARTED.getId());
                g.setModifyUserId(1);
                result = g;

                groupDao.updateByPrimaryKey((Group) any);
            }
        };
        u.setId(100);
        Assert.assertFalse(groupServiceImpl.giveUpGroup(1L, u));

        new NonStrictExpectations() {
            {
                groupDao.selectByPrimaryKey((Long) any);
                Group g = DefaultBOUtils.getGroup(1L, 1L, (byte) 0);
                g.setStatus(GroupStatus.FINISHED.getId());
                g.setModifyUserId(1);
                result = g;

                groupDao.updateByPrimaryKey((Group) any);
            }
        };
        u.setId(1);
        Assert.assertTrue(groupServiceImpl.giveUpGroup(1L, u));

        new NonStrictExpectations() {
            {
                groupDao.selectByPrimaryKey((Long) any);
                result = null;

                groupDao.updateByPrimaryKey((Group) any);
            }
        };
        Assert.assertTrue(groupServiceImpl.giveUpGroup(1L, u));

    }

    @Test
    public void testRecycleAssignGroups() {

        groupServiceImpl.recycleAssignGroups(new Date());
        new Verifications() {
            {
                groupDao.recycleAssignGroups((Date) any);
                times = 1;
            }
        };
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchUpdate() {

        groupServiceImpl.batchUpdate(Arrays.asList(new Group()));
        new Verifications() {
            {
                groupDao.batchUpdate((List<Group>) any);
                times = 1;
            }
        };
    }

    @Test
    public void testDeleteGroupByTaskId() {
        groupServiceImpl.deleteGroupByTaskId(1);
        new Verifications() {
            {
                groupDao.deleteGroupByTaskId((Integer) any);
                times = 1;
            }
        };
    }

}
