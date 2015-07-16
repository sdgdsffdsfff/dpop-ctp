package com.baidu.dpop.ctp.adtag.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baidu.dpop.ctp.adtag.bo.GeneralTag;
import com.baidu.dpop.ctp.adtag.bo.TagGroup;
import com.baidu.dpop.ctp.adtag.bo.TagItem;
import com.baidu.dpop.ctp.statistics.vo.ListInfo;
import com.baidu.dpop.ctp.statistics.vo.TagInfo;

public class TagCountUtils {

    public static final Map<String, TagGroup> GROUPMAP = new HashMap<String, TagGroup>();

    static {
        for (TagGroup g : AdTagUtils.TAGGROUPLIST) {
            GROUPMAP.put(g.getName(), g);
        }
    }

    /**
     * 将list型的adTag统计结果存储至map中
     * 
     * @param tags list型的adTag结果
     * @param temp 用以存储结果的map
     * @return Map<标签名称, Map<标签值名称, 统计个数>>
     */
    public static Map<String, Map<String, Integer>> getTagDimensionInfo(List<String> tags,
            Map<String, Map<String, Integer>> temp) {
        if (temp == null) {
            temp = new HashMap<String, Map<String, Integer>>();
        }

        for (String tag : tags) {
            for (GeneralTag gt : AdTagUtils.TAGLIST) {
                Map<String, Integer> map = temp.get(gt.getName());
                if (map == null) {
                    map = new HashMap<String, Integer>();
                    temp.put(gt.getName(), map);
                }
                String t = tag.charAt(gt.getIndex()) + "";
                Integer count = map.get(t);
                map.put(t, count == null ? 1 : count + 1);
            }
        }

        return temp;

    }

    /**
     * 将map型的统计结果分组格式化
     * 
     * 结果将以List的形式返回，格式如下： [{"group1", "标签组1", [{"tag1", "标签1", [{"黑", "1", 10}, ...]}, ...]}, ...]
     * 
     * @param tags Map<标签名称, Map<标签值名称, 统计个数>>
     * @return 结果信息列表
     */
    public static List<ListInfo<ListInfo<TagInfo>>> countTagDimensionInfo(Map<String, Map<String, Integer>> tags) {
        Map<String, ListInfo<ListInfo<TagInfo>>> r1 = new HashMap<String, ListInfo<ListInfo<TagInfo>>>();
        for (GeneralTag tag : AdTagUtils.TAGLIST) {
            Map<String, Integer> info = tags.get(tag.getName());

            ListInfo<ListInfo<TagInfo>> list = r1.get(tag.getGroup());
            if (list == null) {
                list = new ListInfo<ListInfo<TagInfo>>();
                list.setName(tag.getGroup());
                list.setText(GROUPMAP.get(tag.getGroup()).getText());
                r1.put(tag.getGroup(), list);
            }

            ListInfo<TagInfo> lt = new ListInfo<TagInfo>();
            lt.setName(tag.getName());
            lt.setText(tag.getNameCh());
            for (TagItem item : tag.getTags()) {
                TagInfo ti = new TagInfo();
                ti.setName(item.getValue());
                ti.setText(item.getText());
                ti.setData(info.get(item.getValue()) == null ? 0 : info.get(item.getValue()));
                lt.add(ti);
            }
            list.add(lt);
        }

        List<ListInfo<ListInfo<TagInfo>>> result = new ArrayList<ListInfo<ListInfo<TagInfo>>>();
        for (TagGroup group : AdTagUtils.TAGGROUPLIST) {
            result.add(r1.get(group.getName()));
        }
        return result;
    }
    
}
