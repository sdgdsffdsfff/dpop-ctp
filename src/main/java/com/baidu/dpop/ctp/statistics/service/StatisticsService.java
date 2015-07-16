package com.baidu.dpop.ctp.statistics.service;

import java.util.List;

import com.baidu.dpop.ctp.statistics.vo.BasicStatisticsInfoVo;
import com.baidu.dpop.ctp.statistics.vo.ListInfo;
import com.baidu.dpop.ctp.statistics.vo.QueryConditionVo;
import com.baidu.dpop.ctp.statistics.vo.TagInfo;
import com.baidu.dpop.ctp.statistics.vo.TrendStatisticsItem;
import com.baidu.dpop.ctp.statistics.vo.UserStatisticsPageInfo;

/**   
 * 创意标注统计相关service
 * @author cgd  
 * @date 2015年3月17日 上午10:16:14 
 */
public interface StatisticsService {
    
    /**
     * 获取指定task的基本统计信息
     * 
     * @param taskId 任务ID
     * @return BasicStatisticsInfoVo
     * */
    public BasicStatisticsInfoVo getBasicInfo(Integer taskId);
    
    
    /**
     * 获取指定page的人员统计信息
     * 
     * @param conditionVo 查询条件
     * @return 一个page的人员统计信息
     * */
    public UserStatisticsPageInfo getUserInfo(QueryConditionVo conditionVo);
    
    /**
     * 人员各时间点标注完成量的趋势图
     * 
     * @param taskId 任务ID
     * @param userName 指定的标注人，为null则统计所有人的
     * @return 趋势图数据
     * */
    public List<TrendStatisticsItem> getTrendInfo(Integer taskId, String userName);
    
    
    /**
     * 获取指定task的各维度统计信息
     * 
     * @param taskId 任务ID
     * @return 各维度统计信息
     * */
    public List<ListInfo<ListInfo<TagInfo>>> getDimensionInfo(Integer taskId);
    
}
