package com.baidu.dpop.ctp.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.baidu.dpop.ctp.mainTask.service.UBMCService;
import com.baidu.dpop.ctp.mainTask.vo.FlashIdeaResponse;
import com.baidu.dpop.ctp.mainTask.vo.IdeaInfo;
import com.baidu.dpop.ctp.task.vo.PresentedTaskDetail;

/**
 * UBMCUtilsTest
 * 
 * @author cgd
 * @date 2015年5月15日 上午10:35:11
 */
public class UBMCUtilsTest {

    @Test
    public void testneedFlash() {
        Assert.assertTrue(UBMCUtils.needFlash(3, 0));
        Assert.assertTrue(!UBMCUtils.needFlash(2, 2));
        Assert.assertTrue(!UBMCUtils.needFlash(2, 3));
    }

    @Test
    public void testneedSmartUrl() {
        Assert.assertTrue(UBMCUtils.needSmartUrl(9, 0));

        Assert.assertTrue(!UBMCUtils.needSmartUrl(2, 3));
    }

    @Test
    public void testgetAppId() {
        Assert.assertTrue(UBMCUtils.getAppId(0) == 3);
        Assert.assertTrue(UBMCUtils.getAppId(1) == 10);
        Assert.assertTrue(UBMCUtils.getAppId(2) == 6);

        Assert.assertTrue(UBMCUtils.getAppId(90) == -1);
    }

    @Test
    public void testsetUBMCImgUrl() {
        List<PresentedTaskDetail> list = new ArrayList<PresentedTaskDetail>();
        PresentedTaskDetail item = new PresentedTaskDetail();
        item.setAdId(1);
        item.setWuliaoType(2);
        item.setMcId(1);
        item.setMcVersionId(1);
        list.add(item);

        UBMCUtils.setUBMCImgUrl(list, new MockUBMCService(), 0);

        list.get(0).setWuliaoType(2);
        UBMCUtils.setUBMCImgUrl(list, new MockUBMCService(), 1);
        UBMCUtils.setUBMCImgUrl(list, new MockUBMCService(), 2);

        Assert.assertTrue(true);
    }

    @Test
    public void testsetUBMCImgUrl2() {
        PresentedTaskDetail item = new PresentedTaskDetail();
        item.setAdId(1);
        item.setWuliaoType(2);
        item.setMcId(1);
        item.setMcVersionId(1);

        UBMCUtils.setUBMCImgUrl(item, new MockUBMCService(), 0);

        item.setWuliaoType(2);
        UBMCUtils.setUBMCImgUrl(item, new MockUBMCService(), 1);
        UBMCUtils.setUBMCImgUrl(item, new MockUBMCService(), 2);

        Assert.assertTrue(true);
    }

    class MockUBMCService implements UBMCService {
        @Override
        public List<IdeaInfo> getIdeasInfoFromUBMC(Integer appId, List<IdeaInfo> ideaInfoList) {
            List<IdeaInfo> ret = new ArrayList<IdeaInfo>();
            IdeaInfo item = new IdeaInfo();
            item.setAdId(1L);
            ret.add(item);
            return ret;
        }

        @Override
        public FlashIdeaResponse getFlashIdeasInfo(Integer mcId, Integer versionId) {
            return null;
        }

    }

}
