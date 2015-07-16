package com.baidu.dpop.ctp.adtag.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.baidu.dpop.ctp.adtag.bo.GeneralTag;
import com.baidu.dpop.ctp.adtag.bo.Tag;
import com.baidu.dpop.ctp.adtag.bo.TagGroup;

@RunWith(JMockit.class)
public class AdTagUtilsTest {

    @Test
    public void testGetGroupList() {
        Tag tag = new Tag();
        tag.put(AdTagUtils.BEAUTY, "1");
        tag.put(AdTagUtils.CHEAT, "1");
        tag.put(AdTagUtils.DANGER, "1");
        tag.put(AdTagUtils.VULGAR, "1");
        List<TagGroup> list = AdTagUtils.getGroupList(tag);

        TagGroup ex = AdTagUtils.TAGGROUPLIST.get(0);
        TagGroup ac = list.get(0);
        Assert.assertEquals(ex.getName(), ac.getName());
        Assert.assertEquals(3, ac.getTags().size());

        tag = new Tag();
        tag.put(AdTagUtils.BEAUTY, "1");
        tag.put(AdTagUtils.VULGAR, "1");
        list = AdTagUtils.getGroupList(tag);

        ex = AdTagUtils.TAGGROUPLIST.get(0);
        ac = list.get(0);
        Assert.assertEquals(ex.getName(), ac.getName());
        Assert.assertEquals(2, ac.getTags().size());
    }

    @Test
    public void testGetGroup() {
        for (TagGroup tg : AdTagUtils.TAGGROUPLIST) {
            Assert.assertEquals(tg.getName(), AdTagUtils.getGroup(tg.getName()).getName());
        }

        TagGroup ret = AdTagUtils.getGroup("test");
        Assert.assertEquals(ret.getName(), "general");
    }

    @Test
    public void testGetTag() {
        String tagString = "111-1---------";
        List<TagGroup> list = AdTagUtils.getTag(tagString);

        Set<String> names = new HashSet<String>();
        for (TagGroup tg : list) {
            for (String name : tg.getTags().keySet()) {
                Assert.assertFalse(names.contains(name));
                names.add(name);
                Assert.assertEquals("1", tg.get(name));
            }
        }

        for (int i = 0; i < tagString.length(); i++) {
            if (tagString.charAt(i) == '1') {
                Assert.assertTrue(names.contains(AdTagUtils.TAGLIST.get(i).getName()));
            }
        }
    }

    @Test
    public void testGetTagString() {
        Tag tag = new Tag();
        for (int i = 0; i < AdTagUtils.TAGLIST.size(); i += 2) {
            tag.put(AdTagUtils.TAGLIST.get(i), "1");
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < AdTagUtils.TAGLIST.size(); i++) {
            sb.append(i % 2 == 0 ? "1" : "-");
        }
        Assert.assertEquals(sb.toString(), AdTagUtils.getTagString(tag));
    }

    @Test
    public void testTransform() {
        Set<String> set = new HashSet<String>();
        for (int i = 1; i < AdTagUtils.TAGLIST.size(); i += 2) {
            set.add(AdTagUtils.TAGLIST.get(i).getName());
        }

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < AdTagUtils.TAGLIST.size(); i++) {
            sb1.append((i % 2 == 0 && i >= AdTagUtils.GENERALLIST.size()) ? "-" : "0");
            sb2.append("0");
        }

        Assert.assertEquals(sb1.toString(), AdTagUtils.transform(sb2.toString(), set));

        String ret = AdTagUtils.transform("test", null);
        Assert.assertNotNull(ret);
    }

    @Test
    public void testGetTags() {
        Set<String> set = new HashSet<String>();
        for (GeneralTag gt : AdTagUtils.TAGLIST) {
            set.add(gt.getName());
        }

        set.add("null");

        List<TagGroup> list = AdTagUtils.getTags(set);
        Assert.assertEquals(AdTagUtils.TAGGROUPLIST.size(), list.size());
    }

    @Test
    public void testGetTagFromMap() {
        Map<String, String> map = new HashMap<String, String>();
        int i = 0;
        StringBuilder sb = new StringBuilder();
        for (GeneralTag tag : AdTagUtils.TAGLIST) {
            if (i % 2 == 0) {
                sb.append("1");
                map.put(tag.getName(), "1");
            } else {
                sb.append("-");
            }
            i++;
        }
        Assert.assertEquals(sb.toString(), AdTagUtils.getTagFromMap(map));
    }

    @Test
    public void testGetTagNamesReview() {
        String[] result = AdTagUtils.getTagNamesReview(3).split(",");
        for (String s : result) {
            Assert.assertTrue(s.indexOf("(审核|标注)") > 0);
        }
    }

    @Test
    public void testGetAdTagFromTagList() {
        List<Long> list = Arrays.asList(585L, 0L, 0L);
        Assert.assertEquals("0100111000000000000000000", AdTagUtils.getAdTagFromTagList(list));
    }

    @Test
    public void testTagToLongList() {
        String tag = "0100111000000000000000000";
        long[] result = AdTagUtils.tagtoLongList(tag);
        Assert.assertEquals(585L, result[0]);
        Assert.assertEquals(0L, result[1]);
        Assert.assertEquals(0L, result[2]);
    }

    @Test
    public void testGetSplitLongString() {
        long value = 0x0001001001001000L;
        Assert.assertEquals("000_000_000_000_001_000_000_000_001_000_000_000_001_000_000_000_001_000_000_000_000",
                AdTagUtils.getSplitLongString(value));
    }
    
    @Test
    public void testGetTagByName() {
        GeneralTag tag = AdTagUtils.getTagByName("beauty");
        Assert.assertNotNull(tag);
        Assert.assertEquals("beauty", tag.getName());
        
        Assert.assertNull(AdTagUtils.getTagByName("nothing"));
    }
}
