package com.baidu.dpop.ctp.group.dao.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import mockit.Mocked;
import mockit.Verifications;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidu.dpop.ctp.base.AbstractDAOTests;
import com.baidu.dpop.ctp.group.bo.Group;
import com.baidu.dpop.ctp.group.dao.GroupDao;
import com.baidu.dpop.ctp.group.dao.mapper.GroupMapper;
import com.baidu.dpop.ctp.group.vo.SubmitInfoGetVo;
import com.baidu.dpop.ctp.invoke.vo.GroupCountVo;
import com.baidu.dpop.ctp.statistics.vo.UserStatisticsItem;
import com.baidu.dpop.ctp.task.vo.TagFollowInfoVo;

@SuppressWarnings("restriction")
public class GroupDaoImplTest extends AbstractDAOTests {

    @Resource
    private GroupDao groupDao;

    @Mocked
    private GroupMapper groupMapper;

    @Before
    public void setUp() {
        this.executeDatas("group/group_dataset_init.sql");
    }

    @Test
    public void testSelectGroup() {
        Group g = new Group();
        g.setGroupId(1000L);
        g.setTaskId(0);
        g.setDataType((byte) 0);
        Group temp = groupDao.selectGroup(g);
        Assert.assertEquals(g.getGroupId(), temp.getGroupId());
    }

    @Test
    public void testSelectRandomGroup() {
        Group g = groupDao.selectRandomGroup(0, 2);
        Assert.assertEquals(Long.valueOf(1004), g.getGroupId());
    }

    @Test
    public void testSelectetUnstartCount() {
        Integer count = groupDao.selectUnstartCount(0);
        Assert.assertEquals(Integer.valueOf(8), count);

        count = groupDao.selectUnstartCount(1);
        Assert.assertEquals(Integer.valueOf(0), count);
    }

    @Test
    public void testSelectUnfinishedCount() {
        Integer count = groupDao.selectUnfinishedCount(0);
        Assert.assertEquals(Integer.valueOf(9), count);

        count = groupDao.selectUnfinishedCount(1);
        Assert.assertEquals(Integer.valueOf(0), count);
    }

    @Test
    public void testSelectTestedGroup() {
        Assert.assertEquals(
                1,
                groupDao.selectTestedGroup(
                        Arrays.asList(new GroupCountVo(0L, 1001L, (byte) 0), new GroupCountVo(0L, 1001L, (byte) 1)))
                        .size());

        Assert.assertEquals(0, groupDao.selectTestedGroup(null).size());
    }

    @Test
    public void testSelectTaskStatusById() {
        Assert.assertEquals(11, groupDao.selectTaskStatusById(1L).intValue());
    }

    @Test
    public void testSelectStartedGroupByUser() {
        Group g = groupDao.selectStartedGroupByUser(0, 1);
        Assert.assertEquals(1001L, g.getGroupId().longValue());
    }

    @Test
    public void testSelectHistoryGroups() {
        List<Group> gl = groupDao.selectHistoryGroups(0, 0);
        Assert.assertEquals(1, gl.size());
        Assert.assertEquals(1000L, gl.get(0).getGroupId().longValue());
    }

    @Test
    public void testGetGroupListByCondition() {

        List<Group> ret = this.groupDao.selectGroupListByCondition(0, null, null);
        Assert.assertTrue(ret.size() > 0);

        ret = this.groupDao.selectGroupListByCondition(0, 3, null);
        Assert.assertTrue(ret.size() == 0);

        ret = this.groupDao.selectGroupListByCondition(null, null, 1);
        Assert.assertTrue(ret.size() > 0);

        ret = this.groupDao.selectGroupListByCondition(null, null, null);
        Assert.assertTrue(ret.size() == 0);
    }

    @Test
    public void testGetUserStatisticsInfo() {
        List<UserStatisticsItem> ret = this.groupDao.selectUserStatisticsInfo(0);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() > 0);

    }

    @Test
    public void testSelectTagFollowInfoList() {
        List<TagFollowInfoVo> list = groupDao.selectTagFollowInfoList(0);
        Assert.assertEquals("bb", list.get(0).getUserName());
    }
    
    @Test
    public void testSelectGroupDownloadInfo() {
        SubmitInfoGetVo vo = new SubmitInfoGetVo();
        vo.setTaskIdNumber(0);
        Assert.assertEquals(1, groupDao.selectGroupDownloadInfo(vo).size());
    }

    // @Test
    // public void testGetTrendInfo() {
    // TrendInfoGetVo vo = new TrendInfoGetVo();
    // vo.setTaskId(0);
    // vo.setUserName("cgd");
    // vo.setStartTime(new Date());
    // vo.setEndTime(new Date());
    // TrendStatisticsItem ret = this.groupDao.selectTrendInfo(vo);
    // Assert.assertTrue(ret.getIsDoneAds() == 0);
    //
    // vo.setTaskId(null);
    // ret = this.groupDao.selectTrendInfo(vo);
    //
    // ret = this.groupDao.selectTrendInfo(null);
    // Assert.assertNull(ret);
    // }

    @Test
    public void testrecycleAssignGroups() {
        Date beginTime = new Date();
        this.groupDao.recycleAssignGroups(beginTime);

        Assert.assertTrue(true);
    }

    @Test
    public void testBatchSelect() {
        Assert.assertEquals(2, groupDao.batchSelect(Arrays.asList(1L, 2L, 1000L)).size());
        Assert.assertEquals(0, groupDao.batchSelect(null).size());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchInsert() {
        groupDao.batchInsert(null);
        new Verifications() {
            {
                groupMapper.batchInsert((List<Group>) any);
                times = 0;
            }
        };
    }

    @Test
    public void testRecyleAssignGroups() {
        groupDao.recycleAssignGroups(new Date());
        Group g = groupDao.selectByPrimaryKey(2L);
        Assert.assertNull(g.getModifyUserId());
        Assert.assertEquals(0, g.getStatus().intValue());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testBatchUpdate() {
        groupDao.batchUpdate(null);
        new Verifications() {
            {
                groupMapper.batchUpdate((List<Group>) any);
                times = 0;
            }
        };
    }

    @Test
    public void testDeleteGroupByTaskId() {
        groupDao.deleteGroupByTaskId(0);
        Group g = groupDao.selectRandomGroup(0, 2);
        Assert.assertNull(g);
    }
}
