package com.baidu.dpop.ctp.task.utils;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.baidu.dpop.ctp.group.constant.GroupDataType;
import com.baidu.dpop.ctp.task.bo.BeidouTask;
import com.baidu.dpop.ctp.task.bo.DSPTask;
import com.baidu.dpop.ctp.task.bo.NewDSPTask;
import com.baidu.dpop.ctp.task.bo.QiushiTask;

public class GeneralTaskUtilsTest {

    @Test
    public void testGetNewGeneralTask() {
        Assert.assertTrue(GeneralTaskUtils.getNewGeneralTask(GroupDataType.BEIDOU.getId()) instanceof BeidouTask);
        Assert.assertTrue(GeneralTaskUtils.getNewGeneralTask(GroupDataType.QIUSHI.getId()) instanceof QiushiTask);
        Assert.assertTrue(GeneralTaskUtils.getNewGeneralTask(GroupDataType.DSP.getId()) instanceof DSPTask);
        Assert.assertTrue(GeneralTaskUtils.getNewGeneralTask(GroupDataType.NEWDSP.getId()) instanceof NewDSPTask);
        Assert.assertNull(GeneralTaskUtils.getNewGeneralTask(GroupDataType.ALL.getId()));
    }

    @Test
    public void testSumPriority() {
        Assert.assertEquals(3L, GeneralTaskUtils.sumPriority(3L, 1L, GroupDataType.NEWDSP.getId()).longValue());
        Assert.assertEquals(3L, GeneralTaskUtils.sumPriority(1L, 3L, GroupDataType.NEWDSP.getId()).longValue());

        long p1 = (3L << 56) + 1;
        long p2 = (1L << 56) + 1;

        long result = (3L << 56) + 2;

        Assert.assertEquals(result, GeneralTaskUtils.sumPriority(p1, p2, GroupDataType.BEIDOU.getId()).longValue());
        Assert.assertEquals(result, GeneralTaskUtils.sumPriority(p2, p1, GroupDataType.BEIDOU.getId()).longValue());
    }

    @Test
    public void testGetGeneralTasks() {
        String newDSP =
                "[{\"creativityId\":2,\"textTitle\":\"啃得起火腿，你的理想选择\",\"textDesc\":\"吃的起，啃得起，买得起。\n吃的起，啃得起，买得起。\","
                        + "\"adId\":3,\"productType\":1,\"tradeId\":510101,\"oldTradeId\":102,\"status\":13,"
                        + "\"sizeDesc\":\"N/A\",\"targetUrl\":\"www.baidu.com\",\"showUrl\":\"\",\"createTime\":"
                        + "\"20140415161811\",\"updateTime\":\"20140415165356\",\"ownerId\":8048,\"tagList\":[0,0,0],"
                        + "\"urlList\":null,\"tagVersion\":1}]";

        String beidou =
                "108003\t20651\t12194\t632250\t5\t广州市海诚装饰五金有限公司\twww.gzhaicheng.cn\t"
                        + "www.gzhaicheng.cn/main/products.asp\thttp://www.gzhaicheng.cn/main/products.asp\t60\t60\t"
                        + "广州海诚 肯德基铝合金门\t海诚铝合金门,造工精细!美观大方!\t不会变形,安装方便.适用于高级中西餐厅!\t1703\t590514\t0\t0\t"
                        + "282857639\t8\t10\t1\t1\t2015999999\t北斗测试任务\t0\t2015-04-16 18:12:37\t3";

        Assert.assertEquals(1, GeneralTaskUtils.getGeneralTasks(Arrays.asList(newDSP), GroupDataType.NEWDSP.getId())
                .size());
        
        Assert.assertEquals(1, GeneralTaskUtils.getGeneralTasks(Arrays.asList(beidou), GroupDataType.BEIDOU.getId())
                .size());

    }
}
