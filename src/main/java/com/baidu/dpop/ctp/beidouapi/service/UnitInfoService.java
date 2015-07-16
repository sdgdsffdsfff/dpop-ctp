package com.baidu.dpop.ctp.beidouapi.service;

import com.baidu.dpop.ctp.beidouapi.vo.UnitPreResult;
import com.baidu.dpop.ctp.beidouapi.vo.UnitResult;

/**
 * beidou 创意信息获取接口
 * */
public interface UnitInfoService {

    /**
     * 获取普通创意的临时URL
     */
    public UnitResult<String> getUbmcTmpUrl(Long mcId, Integer versionId);

    /**
     * 获取智能创意对应的HTML代码信息
     * 
     * @param userId 用户ID
     * @param unitId 创意ID
     * @param flowFlag 0-beidou 1-google
     * */
    public UnitResult<UnitPreResult> getHtmlSnippetForSmartUnit(Long unitId, Long userId, Integer flowTag);

}
