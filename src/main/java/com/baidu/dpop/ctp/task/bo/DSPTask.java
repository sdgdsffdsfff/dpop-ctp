/*
 * Copyright 2014 baidu dpop
 * All right reserved.
 *
 */
package com.baidu.dpop.ctp.task.bo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.baidu.dpop.ctp.adtag.bo.AdTag;
import com.baidu.dpop.ctp.adtag.bo.GeneralTag;
import com.baidu.dpop.ctp.adtag.bo.TagInfo;
import com.baidu.dpop.ctp.adtag.bo.TagTypeTreeNode;
import com.baidu.dpop.ctp.adtag.utils.AdTagUtils;
import com.baidu.dpop.ctp.adtag.utils.TaskTypeUtils;
import com.baidu.dpop.ctp.adtag.vo.TaskType;
import com.baidu.dpop.ctp.common.exception.BaseRuntimeException;
import com.baidu.dpop.ctp.group.constant.GroupDataType;
import com.baidu.dpop.ctp.mainTask.constant.GeneralMcType;

/**
 * DSP BO类
 */
public class DSPTask extends DSPTaskBase implements GeneralTask, DownloadInfo {

    private static final long serialVersionUID = 1L;

    // 额外属性-------------------------------------------------------------

    private String adTradeNameLevel2; // 二级行业名称
    private String adTradeNameLevel3; // 三级行业名称
    private AdTag adTagNew; // 标注
    private Byte taskType; // 任务类型

    public String getAdTradeNameLevel2() {
        return adTradeNameLevel2;
    }

    public void setAdTradeNameLevel2(String adTradeNameLevel2) {
        this.adTradeNameLevel2 = adTradeNameLevel2;
    }

    public String getAdTradeNameLevel3() {
        return adTradeNameLevel3;
    }

    public void setAdTradeNameLevel3(String adTradeNameLevel3) {
        this.adTradeNameLevel3 = adTradeNameLevel3;
    }

    public AdTag getAdTagNew() {
        return adTagNew;
    }

    public void setAdTagNew(AdTag adTagNew) {
        this.adTagNew = adTagNew;
    }

    public Byte getTaskType() {
        return taskType;
    }

    public void setTaskType(Byte taskType) {
        this.taskType = taskType;
    }

    // 接口需求-------------------------------------------------------------

    @Override
    public Long getGroupId() {
        return getUserId();
    }

    @Override
    public String getCompany() {
        return getNickname();
    }

    @Override
    public String getTargetUrl() {
        return this.getLandingPage();
    }

    @Override
    public String getShowUrl() {
        return this.getLandingPage();
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getDescription1() {
        return null;
    }

    @Override
    public String getDescription2() {
        return null;
    }

    /**
     * 获取数据类型
     */
    @Override
    public Number getDataType() {
        return GroupDataType.DSP.getId();
    }

    /**
     * 获取通用过的物料类型
     */
    public Integer getGeneralWuliaoType() {
        return GeneralMcType.getGeneralMcType(getWuliaoType(), GroupDataType.DSP.getId());
    }

    /**
     * 获取优先级
     */
    public Long getGeneralPriority() {
        long result = getPriority();
        return (result << 56) + getSecondPriority();
    }

    /**
     * 获取标注版本，DSP统一为0
     */
    public Integer getTagVersion() {
        return 0;
    }

    /**
     * 根据自身含有的数据生成标注信息
     */
    @Override
    public TagInfo genTagInfo() {
        TagInfo info = new TagInfo();

        info.setAdTradeIdLevel3(getAdTradeIdLevel3());
        info.setComment("");
        info.setRefId(getId());
        info.setSample(getAdTag());
        info.setTag(AdTagUtils.getTag(TaskTypeUtils.hideUnusedTag(getAdTag(), taskType)));

        return info;
    }

    /**
     * 根据行业变换adTag，即将不含有的标签置为-
     */
    @Override
    public void transformAdTag(Map<Integer, TagTypeTreeNode> map) {
        Set<String> tags = TagTypeTreeNode.getType(getAdTradeIdLevel3(), map);
        setAdTag(AdTagUtils.transform(getAdTag(), tags));
    }

    // 上传需求-------------------------------------------------------------

    @Override
    public void setArgs(String line) {
        String[] args = line.split("\t");
        try {
            this.setAdId(Long.valueOf(args[0]));
            this.setUserId(Long.valueOf(args[1]));
            this.setDspId(Long.valueOf(args[2]));
            this.setWuliaoType(Integer.valueOf(args[3]));
            this.setNickname(args[4]);
            this.setWebsite(args[5]);
            this.setLandingPage(args[6]);
            this.setDspname(args[7]);
            this.setWidth(Integer.valueOf(args[8]));
            this.setHeight(Integer.valueOf(args[9]));

            int temp = Integer.valueOf(args[10]);
            if (temp > 10000) {
                temp = 0;
            }
            this.setAdTradeIdLevel2(temp);

            temp = Integer.valueOf(args[11]);
            if (temp < 9999) {
                temp = 0;
            }
            this.setAdTradeIdLevel3(0);

            this.setMcId(Integer.valueOf(args[12]));
            this.setMcVersionId(Integer.valueOf(args[13]));
            this.setAdTag(getTagString(Integer.valueOf(args[14]), args[15]));
            this.setPriority(Integer.valueOf(args[16]));
            this.setSecondPriority(Integer.valueOf(args[17]));
            this.setModuserLevel(Integer.valueOf(args[18]));
            this.setTaskId(Integer.valueOf(args[19]));
            this.setTaskName(args[20]);
            this.setChatime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(args[21]));
            if (args.length < 23) {
                this.setTaskType((byte) 3);
            } else {
                this.setTaskType(Byte.valueOf(args[22]));
            }

            this.setAddTime(new Date());
            this.setAddUser("System");
        } catch (ParseException e) {
            throw new BaseRuntimeException("数据错误：DSP，数据行:" + line);
        }
    }

    private String getTagString(Integer ori, String now) {
        StringBuilder sb = new StringBuilder();
        sb.append(ori & 0x0000000F);
        sb.append((ori & 0x0000F000) >> 8);
        sb.append((ori & 0x0000F000) >> 12);
        sb.append((ori & 0x000000F0) >> 4);
        sb.append(now.equals("0") ? AdTagUtils.getOthers() : now);
        return sb.toString();
    }

    // 下载需求-------------------------------------------------------------

    public String genTagedString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder sb = new StringBuilder();
        sb.append(getAdId() + "\t");
        sb.append(getGroupId() + "\t");
        sb.append(getDspId() + "\t");
        sb.append(getWuliaoType() + "\t");

        // tag 信息
        AdTag tagInfo = getAdTagNew();
        if (tagInfo == null) {
            return "";
        }

        sb.append(tagInfo.getAdTradeIdLevel3() + "\t");
        if (StringUtils.isNotEmpty(tagInfo.getAdTag())) {
            String tag = tagInfo.getAdTag();
            tag = tag.replace(AdTagUtils.NULL, '0');
            sb.append(tag.charAt(0) + "\t"); // beauty
            sb.append(tag.charAt(1) + "\t"); // vulgar
            sb.append(tag.charAt(3) + "\t"); // cheat
            sb.append(tag.charAt(2) + "\t"); // danger
            sb.append(tag.substring(4) + "\t");
        } else {
            sb.append("null\t");
            sb.append("null\t");
            sb.append("null\t");
            sb.append("null\t");
            sb.append("null\t");
        }

        // comment
        sb.append(tagInfo.getComment().replace("\n", " ") + "\t");
        sb.append(sdf.format(getChatime()) + "\t");
        // update Time
        sb.append(sdf.format(tagInfo.getUpdateTime()) + "\t");
        // update user
        sb.append(tagInfo.getUpdateUser() + "\t");

        sb.append(getTaskId() + "\t");
        sb.append(getTaskName() + "\n");

        return sb.toString();
    }

    public static String genFullStringTitle(Number taskType) {
        StringBuilder sb = new StringBuilder();
        sb.append("创意ID,");
        sb.append("用户ID,");
        sb.append("DSP_ID,");
        sb.append("物料类型,");
        sb.append("落地页,");
        sb.append("创意宽度,");
        sb.append("创意高度,");
        sb.append("公司,");
        sb.append("公司网址,");
        sb.append("DSP名称,");
        sb.append("二级行业ID,");
        sb.append("二级行业,");
        sb.append("三级行业ID,");
        sb.append("三级行业,");
        sb.append(AdTagUtils.getTagNames(taskType) + ",");
        sb.append("备注,");
        sb.append("标注时间,");
        sb.append("chatime,");
        sb.append("标注人\n");
        return sb.toString();
    }

    public String genFullString(Number taskType) {
        StringBuilder sb = new StringBuilder();
        sb.append(getAdId() + ",");
        sb.append(getUserId() + ",");
        sb.append(getDspId() + ",");
        sb.append(getWuliaoType() + ",");
        sb.append(getLandingPage() + ","); // dbtype 为北斗数据 1
        sb.append(getWidth() + ",");
        sb.append(getHeight() + ",");

        setNickname(getNickname().replace("\"", "\"\""));
        sb.append(getNickname().contains(",") ? ('"' + getCompany() + '"') : getCompany() + ",");

        setWebsite(getWebsite().replace("\"", "\"\""));
        sb.append(getWebsite().contains(",") ? ('"' + getWebsite() + '"') : getWebsite() + ",");

        AdTag tagInfo = this.getAdTagNew();

        sb.append(getAdTradeIdLevel2() + ",");
        sb.append(getAdTradeNameLevel2() + ",");
        sb.append(tagInfo == null ? getAdTradeIdLevel3() + "," : tagInfo.getAdTradeIdLevel3() + ",");
        sb.append(tagInfo == null ? getAdTradeNameLevel3() + "," : tagInfo.getAdTradeNameLevel3() + ",");

        // BEAUTY, CHEAT, DANGER, VULGAR 标签信息获取

        String tagValue = tagInfo == null ? getAdTag() : tagInfo.getAdTag();

        int i = 0;
        TaskType type = TaskTypeUtils.getType(taskType);
        Assert.notNull(type);
        for (GeneralTag tag : AdTagUtils.TAGLIST) {
            if (type.contains(tag)) {
                sb.append(tagValue.charAt(i) + ",");
            }
            i++;
        }

        // comment
        String comment = tagInfo == null ? "NULL" : tagInfo.getComment();
        if (StringUtils.isNotEmpty(comment)) {
            comment = comment.replace("\"", "\"\"").replace("\n", " ");
            sb.append(comment.contains(",") ? ('"' + comment + '"') : comment + ",");
        } else {
            sb.append("NULL,");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sb.append((tagInfo == null ? "NULL" : sdf.format(tagInfo.getUpdateTime())) + ",");
        sb.append(sdf.format(getChatime()) + ",");
        sb.append((tagInfo == null ? "NULL" : tagInfo.getUpdateUser()) + "\n");

        if (tagInfo != null && StringUtils.isNotEmpty(tagInfo.getAdTag())) {

        }
        return sb.toString();
    }

}