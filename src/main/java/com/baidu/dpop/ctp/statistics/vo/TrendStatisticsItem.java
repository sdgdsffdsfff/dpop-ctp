package com.baidu.dpop.ctp.statistics.vo;

/**
 * 趋势Item类
 * 
 * @author cgd
 * @date 2015年3月17日 下午2:49:21
 */
public class TrendStatisticsItem {
    
    private String time; // 时间点
    private Integer isDoneAds; // 对应时间点已标注的创意数

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getIsDoneAds() {
        return isDoneAds;
    }

    public void setIsDoneAds(Integer isDoneAds) {
        this.isDoneAds = isDoneAds;
    }

}
