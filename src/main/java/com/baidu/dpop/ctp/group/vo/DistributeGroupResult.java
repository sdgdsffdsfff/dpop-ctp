package com.baidu.dpop.ctp.group.vo;

import java.util.List;

import com.baidu.dpop.ctp.group.bo.Group;
import com.baidu.dpop.ctp.mainTask.bo.Task;
import com.baidu.dpop.ctp.task.vo.PresentedTask;

/**
 * Group的分配信息，用以储存group的分配结果，包含以下信息： <li>task - group所属的task信息 <li>group -
 * 分配的group的信息 <li>historyAdNum - 此用户在此task中已经标注的所有ad数量 <li>historyGroupNum -
 * 此用户在此task中已标注的所有group数量 <li>accountId - 分配的group中的ad的账户id <li>companyName -
 * 分配的group中的ad的公司名称 <li>companyUrl - 分配的group中的ad的公司网址 <li>list - ad列表
 * 
 * @author mading01
 * 
 */
public class DistributeGroupResult {

    private Task task; // task信息
    private Group group; // group信息

    private Integer historyAdNum; // 已标注创意总数
    private List<Group> historyGroup; // 历史任务group列表
    private Integer historyGroupNum; // 历史任务group总数
    private Long accountId;
    private String companyName;
    private String companyUrl;
    private List<PresentedTask> list;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Integer getHistoryAdNum() {
        return historyAdNum;
    }

    public void setHistoryAdNum(Integer historyAdNum) {
        this.historyAdNum = historyAdNum;
    }

    public List<Group> getHistoryGroup() {
        return historyGroup;
    }

    public void setHistoryGroup(List<Group> historyGroup) {
        this.historyGroup = historyGroup;
    }

    public Integer getHistoryGroupNum() {
        return historyGroupNum;
    }

    public void setHistoryGroupNum(Integer historyGroupNum) {
        this.historyGroupNum = historyGroupNum;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public List<PresentedTask> getList() {
        return list;
    }

    public void setList(List<PresentedTask> list) {
        this.list = list;
    }
}
