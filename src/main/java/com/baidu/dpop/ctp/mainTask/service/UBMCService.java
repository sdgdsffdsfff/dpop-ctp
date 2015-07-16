package com.baidu.dpop.ctp.mainTask.service;

import java.util.List;

import com.baidu.dpop.ctp.mainTask.vo.FlashIdeaResponse;
import com.baidu.dpop.ctp.mainTask.vo.IdeaInfo;

public interface UBMCService {

    /**
     * 传入的是创意的list,包括appId,mcId,mcVersionId,mcType; 返回的结构体中会包括对应的url.目前对于文本信息直接获取北斗库内容，不访问UBMC.
     * 
     * @return
     */
    public List<IdeaInfo> getIdeasInfoFromUBMC(Integer appId, List<IdeaInfo> ideaInfoList);

    /**
     * 获取Flash创意信息
     * 
     * @param mcId mcId
     * @param versionId mc versionId
     * */
    public FlashIdeaResponse getFlashIdeasInfo(Integer mcId, Integer versionId);
}
