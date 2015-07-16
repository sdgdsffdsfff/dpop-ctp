package com.baidu.dpop.ctp.adtag.utils;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;

import com.baidu.dpop.ctp.adtag.bo.GeneralTag;
import com.baidu.dpop.ctp.adtag.vo.TagTypeChangeResult;

public class TaskTypeUtilsTest {

    @Test
    public void testTransformTag() {
        String sample = "000---000";
        String tag = "111------";
        Assert.assertEquals("111---777", TaskTypeUtils.transformAdTag(sample, tag));
    }

    @Test
    public void testHideUnusedTags() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < AdTagUtils.TAGLIST.size(); i++) {
            sb.append("0");
        }
        Assert.assertEquals("00000000000000----------0", TaskTypeUtils.hideUnusedTag(sb.toString(), 1));
    }

    @Test
    public void testGetSampleAndTagList() {
        Set<String> set = new HashSet<String>();
        int i = 0;

        StringBuilder sb = new StringBuilder();
        for (GeneralTag tag : AdTagUtils.TAGLIST) {
            if (i % 2 == 0) {
                set.add(tag.getName());
                sb.append("0");
            } else {
                sb.append("-");
            }
            i++;
        }

        TagTypeChangeResult result = TaskTypeUtils.getSampleAndTagList(set, 1);
        Assert.assertEquals(sb.toString(), result.getSample());
        Assert.assertEquals(3, result.getList().size()); // 任务类型为1，只有3个group
    }

    @Test
    public void testGetType() {
        Assert.assertEquals(1, TaskTypeUtils.getType(1).getValue().intValue());
        Assert.assertNull(TaskTypeUtils.getType(5));
    }
}
