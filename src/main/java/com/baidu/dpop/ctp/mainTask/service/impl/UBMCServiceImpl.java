package com.baidu.dpop.ctp.mainTask.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.codehaus.jackson.map.DeserializationConfig;

import com.baidu.dpop.ctp.common.exception.BaseRuntimeException;
import com.baidu.dpop.ctp.common.utils.HttpClientUtils;
import com.baidu.dpop.ctp.common.vo.HttpClientResponse;
import com.baidu.dpop.ctp.mainTask.constant.GeneralMcType;
import com.baidu.dpop.ctp.mainTask.constant.UBMCAppId;
import com.baidu.dpop.ctp.mainTask.service.UBMCService;
import com.baidu.dpop.ctp.mainTask.vo.BaseUbmcResponse;
import com.baidu.dpop.ctp.mainTask.vo.FlashIdeaResponse;
import com.baidu.dpop.ctp.mainTask.vo.IdeaInfo;
import com.baidu.dpop.ctp.mainTask.vo.SearchUbmcIdeaInfo;
import com.baidu.dpop.frame.core.context.DpopPropertyUtils;

@Service
public class UBMCServiceImpl implements UBMCService {

    private static final Logger LOGGER = Logger.getLogger(UBMCServiceImpl.class);

    @Autowired
    private HttpClientUtils httpClientUtils;

    // 普通创意ubmc访问接口（图文）
    private static String ubmcNormalHost;
    private static String ubmcNormalUserId;
    private static String ubmcNormalToken;

    // Flash创意访问接口
    private static String ubmcFlashHost;
    private static String ubmcFlashUserName;
    private static String ubmcFlashToken;
    private static String ubmcFlashReqType;

    static {
        ubmcNormalHost = DpopPropertyUtils.getProperty("ubmc.normal.host");
        ubmcNormalUserId = DpopPropertyUtils.getProperty("ubmc.normal.userid");
        ubmcNormalToken = DpopPropertyUtils.getProperty("ubmc.normal.token");

        ubmcFlashHost = DpopPropertyUtils.getProperty("ubmc.flash.host");
        ubmcFlashUserName = DpopPropertyUtils.getProperty("ubmc.flash.username");
        ubmcFlashToken = DpopPropertyUtils.getProperty("ubmc.flash.token");
        ubmcFlashReqType = DpopPropertyUtils.getProperty("ubmc.flash.reqtype");
    }

    @Override
    public FlashIdeaResponse getFlashIdeasInfo(Integer mcId, Integer versionId) {
        Assert.notNull(mcId);
        Assert.notNull(versionId);

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("reqtype", ubmcFlashReqType);
        queryMap.put("username", ubmcFlashUserName);
        queryMap.put("token", ubmcFlashToken);
        queryMap.put("mc_id", mcId);
        queryMap.put("version_id", versionId);

        FlashIdeaResponse data = FlashIdeaResponse.getFaildFlash();
        try {
            HttpClientResponse response = httpClientUtils.doGetRequest(ubmcFlashHost, queryMap);
            byte[] rawData = response.getResponseBody();
            String rawDataStr = new String(rawData, "GBK");
            String responseString = rawDataStr;

            // 若返回的是非标准json串则做转换
            Pattern pattern = Pattern.compile("success_jsonpCallback\\(\\[(.*)\\]\\)");
            Matcher matcher = pattern.matcher(rawDataStr);
            if (matcher.matches()) {
                responseString = matcher.group(1);
            }

            // json --> java obj 转换
            ObjectMapper objMapper =
                    new ObjectMapper().configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            objMapper.configure(Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
            data = objMapper.readValue(responseString, FlashIdeaResponse.class);
            data.removeEmpty();
        } catch (Exception e) {
            LOGGER.error("Error in getFlashIdeasInfo", e);
        }

        return data;
    }

    @Override
    public List<IdeaInfo> getIdeasInfoFromUBMC(Integer appId, List<IdeaInfo> ideaInfoList) {

        List<IdeaInfo> temp = new ArrayList<IdeaInfo>();
        List<IdeaInfo> result = new ArrayList<IdeaInfo>();
        for (IdeaInfo info : ideaInfoList) {
            temp.add(info);

            if (temp.size() == 20) {
                result.addAll(getIdeasInfoFromUBMCLimit(appId, temp));
                temp = new ArrayList<IdeaInfo>();
            }
        }

        if (temp.size() > 0) {
            result.addAll(getIdeasInfoFromUBMCLimit(appId, temp));
        }

        return result;
    }

    private List<IdeaInfo> getIdeasInfoFromUBMCLimit(Integer appId, List<IdeaInfo> ideaInfoList) {

        if (null == ideaInfoList) {
            throw new IllegalArgumentException("创意列表为空");
        }

        if (null == appId) {
            throw new IllegalArgumentException("产品线Id为空");
        }

        String mcIds = null;
        String mcVersionIds = null;
        IdeaInfo ideaInfo = null;
        for (int i = 0; i < ideaInfoList.size(); i++) {
            ideaInfo = ideaInfoList.get(i);
            if (null == ideaInfo) {
                throw new IllegalArgumentException("输入的创意信息为空");
            }
            if (null == ideaInfo.getMcId() || null == ideaInfo.getMcVersionId() || null == ideaInfo.getMcType()) {
                throw new IllegalArgumentException("创意的Mcid或者versionId或者mcType为空");
            }
            if (0 == i) {
                mcIds = Integer.toString(ideaInfo.getMcId());
                mcVersionIds = Integer.toString(ideaInfo.getMcVersionId());
            } else {
                mcIds = mcIds + "," + Integer.toString(ideaInfo.getMcId());
                mcVersionIds = mcVersionIds + "," + Integer.toString(ideaInfo.getMcVersionId());
            }
        }

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("mc_id", mcIds);
        queryMap.put("version_id", mcVersionIds);
        queryMap.put("app_id", appId);
        queryMap.put("user_id", ubmcNormalUserId);
        queryMap.put("qapikey", ubmcNormalToken);

        ObjectMapper objMapper =
                new ObjectMapper().configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objMapper.configure(Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);

        BaseUbmcResponse mappedRes;
        List<SearchUbmcIdeaInfo> ubmcIdeas = null;

        for (int tryTimes = 1; tryTimes <= 5; tryTimes++) {
            int success = 0;
            try {
                HttpClientResponse response = httpClientUtils.doGetRequest(ubmcNormalHost, queryMap);
                byte[] rawData = response.getResponseBody();
                String temp = new String(rawData, "UTF-8").replace("\\\\\"", "");
                mappedRes = objMapper.readValue(temp, BaseUbmcResponse.class);
                success = mappedRes.getSuccess();
                ubmcIdeas = mappedRes.getIdeaInfos();
                if (success == 0 && ubmcIdeas != null) {
                    break;
                }
            } catch (Exception e) {
                LOGGER.error("Some thing wrong during getting ubmc response : " + e.getLocalizedMessage(), e);
            }
            
            if (tryTimes == 5) {
                throw new BaseRuntimeException("Get ubmc response wrong, success : " + success);
            }
        }


        for (int j = 0; j < ubmcIdeas.size(); j++) {
            if (appId.equals(UBMCAppId.BEIDOU.getId())) {
                getBeidouIdeaInfo(ideaInfoList.get(j), ubmcIdeas.get(j));
            } else if (appId.equals(UBMCAppId.QIUSHI.getId())) {
                getQiushiIdeaInfo(ideaInfoList.get(j), ubmcIdeas.get(j));
            } else if (appId.equals(UBMCAppId.DSP.getId())) {
                getDSPIdeaInfo(ideaInfoList.get(j), ubmcIdeas.get(j));
            }
        }
        return ideaInfoList;
    }

    private void getBeidouIdeaInfo(IdeaInfo idea, SearchUbmcIdeaInfo info) {
        if (!GeneralMcType.needUrl(idea.getMcType())) {
            return;
        }

        String cont = info.getMcContent();
        if (null == cont) {
            throw new RuntimeException("ubmc返回值content部分获取失败");
        }

        // 创意对应的url
        Pattern patternSrc = Pattern.compile("(.*)?(fileSrc:([^\\s]*))(.*)?");
        Matcher matcher = patternSrc.matcher(cont);
        if (matcher.find()) {
            idea.setUrl(matcher.group(3));
        } else {
            throw new BaseRuntimeException("ubmc返回值解析content失败");
        }
    }

    private void getQiushiIdeaInfo(IdeaInfo idea, SearchUbmcIdeaInfo info) {
        if (!GeneralMcType.needUrl(idea.getMcType())) {
            return;
        }

        String cont = info.getMcContent();
        if (null == cont) {
            throw new RuntimeException("ubmc返回值content部分获取失败");
        }

        if (GeneralMcType.isVIDEO(idea.getMcType())) {
            Pattern patternSrc = Pattern.compile("(.*)?(logoUrl: \"([^\\s]*))\"(.*)?");
            Matcher matcher = patternSrc.matcher(cont);

            if (matcher.find()) {
                idea.setUrl("http://mobads.baidu.com/video/" + matcher.group(3));
                return;
            }
        } else {
            Pattern patternSrc = Pattern.compile("(.*)?(unitBasic\\s*\\{\\s*src: \"([^\"]*))(.*)?");
            Matcher matcher = patternSrc.matcher(cont);

            if (matcher.find()) {
                idea.setUrl(matcher.group(3));
                return;
            }
        }
        throw new BaseRuntimeException("ubmc返回值解析content失败");
    }

    private void getDSPIdeaInfo(IdeaInfo idea, SearchUbmcIdeaInfo info) {
        if (!GeneralMcType.needUrl(idea.getMcType())) {
            return;
        }

        String cont = info.getMcContent();
        if (null == cont) {
            throw new RuntimeException("ubmc返回值content部分获取失败");
        }

        if (GeneralMcType.isVIDEO(idea.getMcType())) {
            String img = cont.split("\t")[10];
            idea.setUrl(img.substring(img.indexOf("http")));
            return;
        }

        Pattern patternSrc = Pattern.compile("(.*)?(media:([^\"^\\s]*))(.*)?");
        Matcher matcher = patternSrc.matcher(cont);

        if (matcher.find()) {
            idea.setUrl(matcher.group(3));
            return;
        }
        throw new BaseRuntimeException("ubmc返回值解析content失败");
    }
}
