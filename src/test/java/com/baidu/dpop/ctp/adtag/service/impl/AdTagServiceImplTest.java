package com.baidu.dpop.ctp.adtag.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import com.baidu.dpop.ctp.adtag.bo.AdTag;
import com.baidu.dpop.ctp.adtag.dao.AdTagDao;
import com.baidu.dpop.ctp.adtag.service.TagTypeService;
import com.baidu.dpop.ctp.adtag.vo.AdTagGetVo;
import com.baidu.dpop.ctp.adtag.vo.AdTagSubmitVo;
import com.baidu.dpop.ctp.base.DefaultBOUtils;
import com.baidu.dpop.ctp.group.constant.GroupDataType;
import com.baidu.dpop.ctp.task.bo.BeidouTask;
import com.baidu.dpop.ctp.task.bo.GeneralTask;
import com.baidu.dpop.ctp.task.service.GeneralTaskService;
import com.baidu.dpop.ctp.task.vo.GeneralTaskQueryVo;
import com.baidu.dpop.ctp.user.constant.UserRoleType;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.Verifications;

public class AdTagServiceImplTest {

    @Tested
    AdTagServiceImpl adTagServiceImpl;

    @Injectable
    AdTagDao adTagDao;

    @Injectable
    TagTypeService tagTypeService;

    @Injectable
    GeneralTaskService generalTaskService;

    @Test
    public void testGetByGroup() {
        new NonStrictExpectations() {
            {
                adTagDao.selectByGroup((Long) any);
                result = DefaultBOUtils.getAdTags(10, 0);
            }
        };

        Assert.assertEquals(10, adTagServiceImpl.getByGroup(1L).size());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testSubmit() {

        new NonStrictExpectations() {
            {
                generalTaskService.batchGetMapped((List<Long>) any, (Number) any);
                List<BeidouTask> list = DefaultBOUtils.getBeidouTasks(10, 1L);
                Map<Long, GeneralTask> map = new HashMap<Long, GeneralTask>();
                for (BeidouTask b : list) {
                    map.put(b.getId(), b);
                }
                result = map;
            }
        };

        new NonStrictExpectations() {
            {
                adTagServiceImpl.getTestedAdTagByRefId((List<Long>) any, (Byte) any);
                result = new HashSet<Long>(Arrays.asList(0L, 2L, 4L, 6L, 8L));
            }
        };

        List<AdTag> list = DefaultBOUtils.getAdTags(10, 0);

        adTagServiceImpl.submit(list, DefaultBOUtils.getUser(1, "user1", UserRoleType.INNER_ADMIN_USER.getId()),
                new Date());

        new Verifications() {
            {
                adTagDao.batchInsert((List<AdTag>) any);
                times = 1;

                adTagDao.batchUpdate((AdTagSubmitVo) any);
                times = 1;
            }
        };
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testSubmitEmpty() {

        new NonStrictExpectations() {
            {
                generalTaskService.batchGetMapped((List<Long>) any, (Number) any);
                List<BeidouTask> list = DefaultBOUtils.getBeidouTasks(10, 1L);
                Map<Long, GeneralTask> map = new HashMap<Long, GeneralTask>();
                for (BeidouTask b : list) {
                    map.put(b.getId(), b);
                }
                result = map;
            }
        };

        // 提交空
        List<AdTag> list = new ArrayList<AdTag>();
        adTagServiceImpl.submit(list, DefaultBOUtils.getUser(1, "user1", UserRoleType.INNER_ADMIN_USER.getId()),
                new Date());

        // 插入空
        new NonStrictExpectations() {
            {
                adTagServiceImpl.getTestedAdTagByRefId((List<Long>) any, (Byte) any);
                result = new HashSet<Long>(Arrays.asList(0L, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L));
            }
        };

        list = DefaultBOUtils.getAdTags(10, 0);
        adTagServiceImpl.submit(list, DefaultBOUtils.getUser(1, "user1", UserRoleType.INNER_ADMIN_USER.getId()),
                new Date());

        new Verifications() {
            {
                adTagDao.batchUpdate((AdTagSubmitVo) any);
                times = 1;
            }
        };

        // 更新空
        new NonStrictExpectations() {
            {
                adTagServiceImpl.getTestedAdTagByRefId((List<Long>) any, (Byte) any);
                result = new HashSet<Long>();
            }
        };

        adTagServiceImpl.submit(list, DefaultBOUtils.getUser(1, "user1", UserRoleType.INNER_ADMIN_USER.getId()),
                new Date());

        new Verifications() {
            {
                adTagDao.batchInsert((List<AdTag>) any);
                times = 1;
            }
        };
    }

    @Test
    public void testBatchGet() {
        AdTagGetVo vo = new AdTagGetVo();
        vo.setList(new ArrayList<Long>());
        vo.setDataType(0);
        adTagServiceImpl.batchGet(vo);
        new Verifications() {
            {
                adTagDao.batchSelect((AdTagGetVo) any);
                times = 1;
            }
        };
    }

    @Test
    public void testGetByRefId() {
        adTagServiceImpl.getByRefId(0L, 0);
        new Verifications() {
            {
                adTagDao.selectByRefId((AdTagGetVo) any);
                times = 1;
            }
        };
    }

    @Test
    public void testGetAdTagMap() {
        new NonStrictExpectations() {
            {
                adTagServiceImpl.batchGet((AdTagGetVo) any);
                result = DefaultBOUtils.getAdTags(5, GroupDataType.BEIDOU.getId());
            }
        };

        Map<Long, AdTag> map =
                this.adTagServiceImpl.getAdTagMap(Arrays.asList(0L, 1L, 2L, 3L, 4L), GroupDataType.BEIDOU.getId()
                        .intValue());

        Assert.assertEquals(5, map.size());

        new NonStrictExpectations() {
            {
                adTagServiceImpl.batchGet((AdTagGetVo) any);
                result = null;
            }
        };

        map =
                this.adTagServiceImpl.getAdTagMap(Arrays.asList(0L, 1L, 2L, 3L, 4L), GroupDataType.BEIDOU.getId()
                        .intValue());
        Assert.assertEquals(0, map.size());

        map = this.adTagServiceImpl.getAdTagMap(null, GroupDataType.BEIDOU.getId().intValue());
        Assert.assertEquals(0, map.size());

        map = this.adTagServiceImpl.getAdTagMap(Arrays.asList(0L, 1L, 2L, 3L, 4L), null);
        Assert.assertEquals(0, map.size());
    }

    @Test
    public void testGetTagedCount() {
        adTagServiceImpl.getTagedCount(new GeneralTaskQueryVo());
        new Verifications() {
            {
                adTagDao.selectTagedCount((GeneralTaskQueryVo) any);
                times = 1;
            }
        };
    }

    @Test
    public void testGetTagedtags() {
        adTagServiceImpl.getTagedtags(new GeneralTaskQueryVo());
        new Verifications() {
            {
                adTagDao.selectTagedtags((GeneralTaskQueryVo) any);
                times = 1;
            }
        };
    }

    @Test
    public void testGetMappedTagedtags() {
        new NonStrictExpectations() {
            {
                adTagServiceImpl.getTagedtags((GeneralTaskQueryVo) any);
                result = DefaultBOUtils.getAdTags(5, GroupDataType.BEIDOU.getId());
            }
        };
        Map<Long, AdTag> map = adTagServiceImpl.getMapedTagedtags(new GeneralTaskQueryVo());
        Assert.assertEquals(1L, map.get(1L).getId().longValue());
    }
}
