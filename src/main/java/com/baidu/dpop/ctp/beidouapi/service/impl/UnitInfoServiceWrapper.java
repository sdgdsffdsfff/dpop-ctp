package com.baidu.dpop.ctp.beidouapi.service.impl;

import com.baidu.dpop.ctp.beidouapi.service.UnitInfoService;
import com.baidu.dpop.ctp.beidouapi.vo.ResponseStatus;
import com.baidu.dpop.ctp.beidouapi.vo.UnitPreResult;
import com.baidu.dpop.ctp.beidouapi.vo.UnitResult;
import com.baidu.dpop.ctp.common.utils.BnsUtils;
import com.baidu.dpop.frame.core.context.DpopPropertyUtils;
import com.baidu.rpc.client.McpackRpcProxy;
import com.baidu.rpc.client.ProxyFactory;
import com.baidu.rpc.exception.ExceptionHandler;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import java.util.List;

//online serverlist
// private static String[] internalServerList = { "http://10.46.141.33:8080"
// , "http://10.44.182.44:8080", "http://10.44.182.45:8080",
// "http://10.52.86.55:8080", "http://10.52.87.35:8080",
// "http://10.23.244.60:8080", "http://10.38.45.34:8080" };
// private static final String SMART_IDEA_SERVER_NAME = "api.beidou.all";

/**
 * 北斗创意相关mcpack接口调用封装
 * */
public class UnitInfoServiceWrapper {
    private static Logger logger = Logger.getLogger(UnitInfoServiceWrapper.class);

    // 北斗Server在bns中对应的serverName
    private static String beidouBnsServerName;
    static {
        beidouBnsServerName = DpopPropertyUtils.getProperty("beidou.bns.server.name");
    }

    /**
     * 获取智能创意对应的HTML代码信息
     * 
     * @param userId 用户ID
     * @param unitId 创意ID
     * @param flowFlag 0-beidou 1-google
     * */
    public static String getSmartUnitHtmlSnippet(Long userId, Long unitId, Integer flowTag) {
        Assert.notNull(userId);
        Assert.notNull(unitId);
        Assert.notNull(flowTag);

        // 获取可访问的server List，随机选一台RPC调用
        List<String> internalServerList = BnsUtils.getIpPortList(beidouBnsServerName);
        if (CollectionUtils.isEmpty(internalServerList)) {
            logger.error(String.format(
                    "Failed to get beidou server list from bns. HtmlSnippet of userId [%s] unitId [%s].", userId,
                    unitId));
            return "ERROR: Can not get available beidou server.";
        }
        int randIndex = (int) Math.floor(Math.random() * internalServerList.size());
        String server = String.format("http://%s/api/UnitInfoService", internalServerList.get(randIndex));

        // mcpack远程接口调用
        McpackRpcProxy proxy = new McpackRpcProxy(server, "UTF-8", new ExceptionHandler());
        UnitInfoService exporter = ProxyFactory.createProxy(UnitInfoService.class, proxy);
        UnitResult<UnitPreResult> result = exporter.getHtmlSnippetForSmartUnit(unitId, userId, flowTag);

        // 0--成功，1--失败 ，2--参数错误
        if (ResponseStatus.SUCCESS != result.getStatus()) {
            logger.error(String.format(
                    "Failed to get HtmlSnippet of userId [%s] unitId [%s] with status=[%s] as server[%s]", userId,
                    unitId, result.getStatus(), server));
            return "ERROR: Status error! " + server;
        }

        if (result.getData() == null) {
            logger.error(String.format(
                    "get Empty HtmlSnippet of userId [%s] unitId [%s] with status=[%s] as server[%s]", userId, unitId,
                    result.getStatus(), server));
            return "ERROR: EMPTY DATA! " + server;
        }
        return result.getData().get(0).getHtmlSnippet();
    }

    /**
     * 获取普通创意的临时URL
     * */
    public static String getUbmcTmpUrl(Long mcId, Integer versionId) {
        List<String> internalServerList = BnsUtils.getIpPortList(beidouBnsServerName);
        if (CollectionUtils.isEmpty(internalServerList)) {
            logger.error(String.format(
                    "Failed to get beidou server list from bns. mcId [%s] versionId [%s].", mcId, versionId));
            return "ERROR: Can not get available beidou server.";
        }
        
        int randIndex = (int) Math.floor(Math.random() * internalServerList.size());
        String server = String.format("http://%s/api/UnitInfoService", internalServerList.get(randIndex));

        McpackRpcProxy proxy = new McpackRpcProxy(server, "UTF-8", new ExceptionHandler());
        UnitInfoService exporter = ProxyFactory.createProxy(UnitInfoService.class, proxy);
        UnitResult<String> result = exporter.getUbmcTmpUrl(mcId, versionId);
        // 0--成功，1--失败 ，2--参数错误
        if (0 != result.getStatus()) {
            logger.error(String.format("Failed to get HtmlSnippet of mcId [%s] versionId [%s] with status=[%s]", mcId,
                    versionId, result.getStatus()));
            return "ERROR: NO HTML FOUND!";
        }

        return result.getData().get(0);
    }
}
