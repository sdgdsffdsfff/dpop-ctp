package com.baidu.dpop.ctp.statistics.vo;

/**
 * 标注人员基本统计信息
 * 
 * @author cgd
 * @date 2015年3月17日 下午5:45:14
 */
public class UserStatisticsItem {
    private Integer ranking; // 排名
    private String userName; // 标注人员账号
    private Integer isDoneGroups; // 已标注推广组数
    private Integer isDoneAds; // 已标注创意数

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getIsDoneGroups() {
        return isDoneGroups;
    }

    public void setIsDoneGroups(Integer isDoneGroups) {
        this.isDoneGroups = isDoneGroups;
    }

    public Integer getIsDoneAds() {
        return isDoneAds;
    }

    public void setIsDoneAds(Integer isDoneAds) {
        this.isDoneAds = isDoneAds;
    }
}
