package com.baidu.dpop.ctp.adtag.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.baidu.dpop.ctp.adtag.bo.GeneralTag;
import com.baidu.dpop.ctp.adtag.bo.TagGroup;
import com.baidu.dpop.ctp.adtag.vo.TagTypeChangeResult;
import com.baidu.dpop.ctp.adtag.vo.TaskType;

public class TaskTypeUtils {

    public static final TaskType TYPE1 = new TaskType("合规风险", 1, AdTagUtils.GENERAL, AdTagUtils.GENADD,
            AdTagUtils.FALSE, AdTagUtils.ADDITION);

    public static final TaskType TYPE2 = new TaskType("用户体验", 2, AdTagUtils.USEREXP, AdTagUtils.USEREXP2);

    public static final TaskType TYPE3 = new TaskType("全部", 3, AdTagUtils.GENERAL, AdTagUtils.GENADD, AdTagUtils.FALSE,
            AdTagUtils.ADDITION, AdTagUtils.USEREXP, AdTagUtils.USEREXP2);

    public static final List<TaskType> TYPES = Arrays.asList(TYPE1, TYPE2, TYPE3);

    public static String transformAdTag(String sample, String tag) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tag.length(); i++) {
            if (sample.charAt(i) != '-' && tag.charAt(i) == '-') {
                sb.append("7");
            } else {
                sb.append(tag.charAt(i));
            }
        }
        return sb.toString();
    }

    public static String hideUnusedTag(String tag, Number taskType) {
        TaskType type = TYPES.get(taskType.intValue() - 1);

        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (GeneralTag gt : AdTagUtils.TAGLIST) {
            sb.append(type.contains(gt) ? tag.charAt(i) : '-');
            i++;
        }
        return sb.toString();
    }

    public static TagTypeChangeResult getSampleAndTagList(Set<String> tags, Integer taskType) {
        TaskType type = TYPES.get(taskType.intValue() - 1);

        StringBuilder sb = new StringBuilder();
        for (GeneralTag tag : AdTagUtils.TAGLIST) {
            sb.append(tags.contains(tag.getName()) ? '0' : '-');
        }

        TagTypeChangeResult result = new TagTypeChangeResult();
        List<TagGroup> list = AdTagUtils.getTags(tags);
        List<TagGroup> listResult = new ArrayList<TagGroup>();
        for (TagGroup tg : list) {
            if (type.contains(tg)) {
                listResult.add(tg);
            }
        }
        result.setList(listResult);
        result.setSample(sb.toString());

        return result;
    }

    public static TaskType getType(Number id) {
        int i = id.intValue();
        if (i <= 0 || i > TYPES.size()) {
            return null;
        }

        return TYPES.get(i - 1);
    }
}
