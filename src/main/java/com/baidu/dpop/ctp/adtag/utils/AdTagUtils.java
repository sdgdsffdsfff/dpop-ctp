package com.baidu.dpop.ctp.adtag.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.Assert;

import com.baidu.dpop.ctp.adtag.bo.GeneralTag;
import com.baidu.dpop.ctp.adtag.bo.Tag;
import com.baidu.dpop.ctp.adtag.bo.TagItem;
import com.baidu.dpop.ctp.adtag.bo.TagGroup;
import com.baidu.dpop.ctp.adtag.bo.TagItemList;
import com.baidu.dpop.ctp.adtag.vo.TaskType;

public class AdTagUtils {

    public static final char NULL = '-';
    private static String others = "";

    public static final TagItemList BLACKWHITE = new TagItemList(0, new TagItem("白", "3"), new TagItem("灰", "2"),
            new TagItem("黑", "1"));

    public static final TagItemList FALSEORNOT = new TagItemList(0, new TagItem("无", "3"), new TagItem("疑似", "2"),
            new TagItem("虚假", "1"));

    public static final TagItemList HASORNOT = new TagItemList(0, new TagItem("无", "3"), new TagItem("有", "1"));

    public static final TagItemList COMPETITION = new TagItemList(0, new TagItem("无", "3"), new TagItem("对比", "2"),
            new TagItem("指名", "1"));

    public static final TagItemList HIGHLOW = new TagItemList(1, new TagItem("低", "1"), new TagItem("中", "2"),
            new TagItem("高", "3"));

    public static final TagItemList ISORNOT = new TagItemList(0, new TagItem("否", "2"), new TagItem("是", "1"));

    public static final TagItemList GOODBAD = new TagItemList(0, new TagItem("好", "3"), new TagItem("差", "1"));

    public static final TagItemList GOODNOTBAD = new TagItemList(0, new TagItem("好", "3"), new TagItem("无", "2"),
            new TagItem("差", "1"));

    public static final TagItemList ISORNOTNEW = new TagItemList(0, new TagItem("是", "3"), new TagItem("否", "1"));

    public static final TagItemList NOTORIS = new TagItemList(1, new TagItem("是", "1"), new TagItem("否", "3"));

    public static final TagGroup GENERAL = new TagGroup("general", "通用标签");
    public static final TagGroup FALSE = new TagGroup("false", "合规风险1");
    public static final TagGroup ADDITION = new TagGroup("addition", "合规风险2");
    public static final TagGroup GENADD = new TagGroup("genadd", "欺诈度");
    public static final TagGroup USEREXP = new TagGroup("userexp", "用户体验1");
    public static final TagGroup USEREXP2 = new TagGroup("userexp2", "用户体验2");

    public static final GeneralTag BEAUTY = new GeneralTag("beauty", "美观度", 3, 0, null, null, GENERAL.getName(),
            HIGHLOW);
    public static final GeneralTag VULGAR = new GeneralTag("vulgar", "低俗度", 1, 1, 0, 1, GENERAL.getName(), BLACKWHITE);
    public static final GeneralTag DANGER = new GeneralTag("danger", "高危度", 2, 2, null, null, GENERAL.getName(),
            ISORNOT);

    public static final GeneralTag CHEAT = new GeneralTag("cheat", "欺诈度", 1, 3, null, null, GENADD.getName(), ISORNOT);

    public static final GeneralTag FALSEINFO = new GeneralTag("falseInfo", "信息虚假", 1, 4, 0, 2, FALSE.getName(),
            FALSEORNOT);
    public static final GeneralTag FALSEEFFECT = new GeneralTag("falseEffect", "效果虚假", 2, 5, 0, 3, FALSE.getName(),
            FALSEORNOT);
    public static final GeneralTag CURERATE = new GeneralTag("cureRate", "治愈率&有效率", 3, 6, 0, 4, FALSE.getName(),
            HASORNOT);
    public static final GeneralTag FALSESERVICE = new GeneralTag("falseService", "服务承诺虚假", 4, 7, 0, 5, FALSE.getName(),
            HASORNOT);

    public static final GeneralTag VICIOUSCOMPETITION = new GeneralTag("viciousCompetition", "恶性竞争", 3, 8, 0, 6,
            ADDITION.getName(), COMPETITION);
    public static final GeneralTag FALSEDESCRIPTION = new GeneralTag("falseDescription", "禁用描述", 1, 9, 0, 7,
            ADDITION.getName(), HASORNOT);
    public static final GeneralTag LICENSING = new GeneralTag("licensing", "专利许可", 4, 10, 0, 8, ADDITION.getName(),
            HASORNOT);
    public static final GeneralTag PORTRAIT = new GeneralTag("portrait", "人物肖像", 2, 11, 0, 9, ADDITION.getName(),
            HASORNOT);
    public static final GeneralTag RECOMMENDATION = new GeneralTag("recommendation", "推荐机构", 5, 12, 0, 10,
            ADDITION.getName(), HASORNOT);
    public static final GeneralTag PROPAGANDA = new GeneralTag("propaganda", "禁止宣传", 5, 13, 0, 11, FALSE.getName(),
            HASORNOT);

    public static final GeneralTag LAYOUT = new GeneralTag("layout", "排版布局", 2, 14, 0, 12, USEREXP.getName(), GOODBAD);
    public static final GeneralTag COLOUR = new GeneralTag("colour", "颜色使用", 3, 15, 0, 13, USEREXP.getName(), GOODBAD);
    public static final GeneralTag IMAGE = new GeneralTag("image", "图片使用", 4, 16, 0, 14, USEREXP.getName(), GOODNOTBAD);
    public static final GeneralTag TEXT = new GeneralTag("text", "文字效果", 5, 17, 0, 15, USEREXP.getName(), GOODBAD);
    public static final GeneralTag FLASH = new GeneralTag("flash", "动画效果", 1, 18, 0, 16, USEREXP.getName(), GOODNOTBAD);

    public static final GeneralTag EFFEATTR = new GeneralTag("effectiveAttraction", "有效吸引", 1, 19, 0, 17,
            USEREXP2.getName(), ISORNOTNEW);
    public static final GeneralTag EXPRCLEAR = new GeneralTag("expressClearly", "表达清晰", 2, 20, 0, 18,
            USEREXP2.getName(), ISORNOTNEW);
    public static final GeneralTag HATE = new GeneralTag("hate", "厌恶", 3, 21, 0, 19, USEREXP2.getName(), NOTORIS);
    public static final GeneralTag LPTRUST = new GeneralTag("lpTrust", "LP信任", 5, 22, 0, 20, USEREXP2.getName(),
            ISORNOTNEW);
    public static final GeneralTag LPRELEVANCE = new GeneralTag("lpRelevance", "LP相关性", 4, 23, 0, 21,
            USEREXP2.getName(), ISORNOTNEW);

    public static final GeneralTag PRIVACY = new GeneralTag("privacy", "个人隐私", 6, 24, 1, 1, ADDITION.getName(),
            HASORNOT);

    /**
     * 标签组，排列是按顺序的
     */
    public static final List<GeneralTag> TAGLIST = Arrays.asList(BEAUTY, VULGAR, DANGER, CHEAT, FALSEINFO, FALSEEFFECT,
            CURERATE, FALSESERVICE, VICIOUSCOMPETITION, FALSEDESCRIPTION, LICENSING, PORTRAIT, RECOMMENDATION,
            PROPAGANDA, LAYOUT, COLOUR, IMAGE, TEXT, FLASH, EFFEATTR, EXPRCLEAR, HATE, LPTRUST, LPRELEVANCE, PRIVACY);

    /**
     * 全部行业都具有的标签
     */
    public static final List<GeneralTag> GENERALLIST = new ArrayList<GeneralTag>();

    /**
     * 标签组列表，排列是按顺序的
     */
    public static final List<TagGroup> TAGGROUPLIST = Arrays
            .asList(GENERAL, GENADD, FALSE, ADDITION, USEREXP, USEREXP2);

    /**
     * tag名称和tag的对应关系表
     */
    private static Map<String, GeneralTag> tagMap;

    /**
     * 初始化各个参数
     */
    static {

        for (GeneralTag tag : TAGLIST) {
            if (tag.getGroup().equals(GENERAL.getName())) {
                GENERALLIST.add(tag);
            }

        }

        StringBuilder sb = new StringBuilder();

        // TODO others即除了上传的四个普通标签
        for (int i = 0; i < TAGLIST.size() - 4; i++) {
            sb.append("0");
        }
        others = sb.toString();

        tagMap = new HashMap<String, GeneralTag>();
        for (GeneralTag tag : TAGLIST) {
            tagMap.put(tag.getName(), tag);
        }
    }

    /**
     * 将Tag类转化为TagGroup的列表
     * 
     * @param tag 需要转化的Tag实例
     * @return 转化成功的列表
     */
    public static List<TagGroup> getGroupList(Tag tag) {
        List<TagGroup> result = new ArrayList<TagGroup>();
        for (TagGroup group : TAGGROUPLIST) {
            TagGroup g = tag.getMap().get(group.getName());
            if (g != null) {
                result.add(g);
            }
        }
        return result;
    }

    /**
     * 根据标签组的名称获取一个标签组，默认获取通用标签组
     * 
     * @param name 标签组名称
     * @return 获取到的标签组实例，里面没有任何标注数据
     */
    public static TagGroup getGroup(String name) {

        for (TagGroup group : TAGGROUPLIST) {
            if (name.equals(group.getName())) {
                return new TagGroup(group.getName(), group.getText());
            }
        }

        return new TagGroup(GENERAL.getName(), GENERAL.getText());
    }

    /**
     * 通过String型的adTag获取TagGroup信息
     * 
     * @param s 标注的adTag
     * @return TagGroup的列表信息
     */
    public static List<TagGroup> getTag(String s) {
        Tag tag = new Tag();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != NULL) {
                tag.put(TAGLIST.get(i), (c - '0') + "");
            }
        }
        return getGroupList(tag);
    }

    /**
     * 根据Tag类实例获取字符串型的tag数据
     * 
     * @param tag 需要获取的Tag类实例
     * @return 转化后的字符串
     */
    public static String getTagString(Tag tag) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < TAGLIST.size(); i++) {
            String value = tag.get(TAGLIST.get(i));
            sb.append(value == null ? NULL : value);
        }
        return sb.toString();
    }

    /**
     * 根据行业将标签变形。此行业中不包含的标签将用-代替
     * 
     * @param ori 原始标签值
     * @param trade 行业数据，即此行业含有什么标签
     * @return 转化后的字符串
     */
    public static String transform(String ori, Set<String> trade) {

        if (trade == null) {
            trade = new HashSet<String>();
        }

        for (GeneralTag tag : GENERALLIST) {
            // 所有类型都有通用标签
            trade.add(tag.getName());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ori.length(); i++) {
            sb.append(trade.contains(TAGLIST.get(i).getName()) ? ori.charAt(i) : NULL);
        }

        return sb.toString();
    }

    /**
     * 通过给定的标签集合生成TagGroup的列表信息
     * 
     * @param tags 标签集合
     * @return 生成的列表信息
     */
    public static List<TagGroup> getTags(Set<String> tags) {
        Tag tag = new Tag();

        for (GeneralTag gt : GENERALLIST) {
            tags.add(gt.getName());
        }

        for (String t : tags) {
            GeneralTag gt = tagMap.get(t);
            if (gt != null) {
                tag.put(gt, "0");
            }
        }
        return getGroupList(tag);
    }

    public static String getTagFromMap(Map<String, String> tag) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < TAGLIST.size(); i++) {
            String value = tag.get(TAGLIST.get(i).getName());
            sb.append(value == null ? NULL : value);
        }
        return sb.toString();
    }

    public static String getTagNames(Number taskType) {
        TaskType t = TaskTypeUtils.getType(taskType);
        Assert.notNull(t);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < TAGLIST.size(); i++) {
            if (t.contains(TAGLIST.get(i))) {
                sb.append(TAGLIST.get(i).getNameCh() + ",");
            }
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    public static String getTagNamesReview(Number taskType) {
        TaskType t = TaskTypeUtils.getType(taskType);
        Assert.notNull(t);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < TAGLIST.size(); i++) {
            if (t.contains(TAGLIST.get(i))) {
                sb.append(TAGLIST.get(i).getNameCh() + "(审核|标注)" + ",");
            }
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    public static String getAdTagFromTagList(List<Long> tagList) {
        StringBuilder sb = new StringBuilder();
        for (GeneralTag tag : AdTagUtils.TAGLIST) {
            sb.append(tag.getValue(tagList));
        }
        return sb.toString();
    }

    public static long[] tagtoLongList(String tag) {
        long[] result = new long[3];
        for (GeneralTag t : TAGLIST) {
            char c = tag.charAt(t.getIndex());
            long tg = c == '-' ? 0 : c - '0';
            if (t.getTagIndex() != null) {
                result[t.getTagIndex()] += (tg << (3 * (t.getMask() - 1)));
            }
        }
        return result;
    }

    public static String getSplitLongString(Long value) {
        StringBuilder sb = new StringBuilder();
        long mask = 0x4000000000000000L;
        for (int i = 1; i < 64; i++) {
            sb.append((value & mask) >>> (63 - i));
            if (i % 3 == 0 && i != 63) {
                sb.append("_");
            }
            mask = mask >>> 1;
        }
        return sb.toString();
    }

    public static GeneralTag getTagByName(String name) {
        for (GeneralTag tag : TAGLIST) {
            if (tag.getName().equals(name)) {
                return tag;
            }
        }
        return null;
    }
    
    public static String getOthers() {
        return others;
    }
}
