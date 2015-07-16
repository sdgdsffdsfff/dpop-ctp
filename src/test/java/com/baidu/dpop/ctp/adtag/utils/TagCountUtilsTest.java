package com.baidu.dpop.ctp.adtag.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import com.baidu.dpop.ctp.adtag.bo.GeneralTag;
import com.baidu.dpop.ctp.statistics.vo.ListInfo;
import com.baidu.dpop.ctp.statistics.vo.TagInfo;

public class TagCountUtilsTest {

    @Test
    public void testGetTagDimensionInfo() {
        Map<String, Map<String, Integer>> map = new HashMap<String, Map<String, Integer>>();
        List<String> tags = new ArrayList<String>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < AdTagUtils.TAGLIST.size(); i++) {
            if (i % 2 == 0) {
                sb.append("0");
            } else {
                sb.append("-");
            }
        }
        
        String tagString = sb.toString();
        for (int i = 0; i < 10; i++) {
            tags.add(tagString);
        }

        map = TagCountUtils.getTagDimensionInfo(tags, map);
        int k = 0;
        for (GeneralTag tag : AdTagUtils.TAGLIST) {
            Assert.assertEquals(10, map.get(tag.getName()).get(k % 2 == 0 ? "0" : "-").intValue());
            k++;
        }
    }
    
    @Test
    public void testcountTagDimensionInfo() {
        Map<String, Map<String, Integer>> map = new HashMap<String, Map<String, Integer>>();
        List<String> tags = new ArrayList<String>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < AdTagUtils.TAGLIST.size(); i++) {
            if (i % 2 == 0) {
                sb.append("0");
            } else {
                sb.append("-");
            }
        }
        
        String tagString = sb.toString();
        for (int i = 0; i < 10; i++) {
            tags.add(tagString);
        }

        map = TagCountUtils.getTagDimensionInfo(tags, map);
        List<ListInfo<ListInfo<TagInfo>>> result = TagCountUtils.countTagDimensionInfo(map);
        Assert.assertEquals(AdTagUtils.TAGGROUPLIST.size(), result.size());
    }
    
    

}
