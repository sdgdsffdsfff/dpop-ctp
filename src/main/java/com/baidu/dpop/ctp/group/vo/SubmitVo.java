package com.baidu.dpop.ctp.group.vo;

import java.util.List;

import com.baidu.dpop.ctp.adtag.vo.SubmitTagInfo;

/**
 * 提交任务时使用，包含: <li>list - 提交的信息列表 <li>groupId - 提交的group的主键id
 * 
 * @author mading01
 * 
 */
public class SubmitVo {

    private List<SubmitTagInfo> list;
    private Long groupId;

    public List<SubmitTagInfo> getList() {
        return list;
    }

    public void setList(List<SubmitTagInfo> list) {
        this.list = list;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

}
