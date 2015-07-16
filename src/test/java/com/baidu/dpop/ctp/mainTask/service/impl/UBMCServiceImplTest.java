package com.baidu.dpop.ctp.mainTask.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;

import com.baidu.dpop.ctp.common.utils.HttpClientUtils;
import com.baidu.dpop.ctp.common.vo.HttpClientResponse;
import com.baidu.dpop.ctp.mainTask.vo.FlashIdeaResponse;
import com.baidu.dpop.ctp.mainTask.vo.IdeaInfo;

/**
 * UBMCServiceImpl Test
 * 
 * @author cgd
 * @date 2015年3月15日 上午10:05:25
 */
public class UBMCServiceImplTest {

    @Tested
    private UBMCServiceImpl uBMCService;

    @Injectable
    private HttpClientUtils httpClientUtils;

    @Test
    @SuppressWarnings("unchecked")
    public void testGetFlashIdeasInfo() {
        new NonStrictExpectations() {
            {
                httpClientUtils.doGetRequest(anyString, (Map<String, Object>) any);
                HttpClientResponse response = new HttpClientResponse();
                String data =
                        "success_jsonpCallback([{\"text_list\":[\"������Ƶ  ��׼��ͨ��ý 010-88515119 �����㲥\"],\"image_files\":[\"http://ubmcmm.baidustatic.com/media/v1/0f000Q4WuUTJo8RW2gnMl6.jpg\",\"http://ubmcmm.baidustatic.com/media/v1/0f000nUxYWAY1DyVo9bc-s.png\",\"http://ubmcmm.baidustatic.com/media/v1/0f000nNNG58d-HtphKqEGf.png\",\"http://ubmcmm.baidustatic.com/media/v1/0f000D9Pdg9MZDDlQlDg3f.png\",\"http://ubmcmm.baidustatic.com/media/v1/0f00001wsn7Bihoae3MG8f.png\"],\"out_mc_urls\":[]}])";
                response.setResponseBody(data.getBytes());

                result = response;
            }
        };

        FlashIdeaResponse ret = uBMCService.getFlashIdeasInfo(1, 1);
        Assert.assertNotNull(ret);
        Assert.assertNotNull(ret.getImageList());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetIdeasInfoFromUBMC() {
        new NonStrictExpectations() {
            {
                httpClientUtils.doGetRequest(anyString, (Map<String, Object>) any);
                HttpClientResponse response = new HttpClientResponse();
                String data =
                        "{\"success\":0,\"error_code\":0,\"search_id\":\"f3951a9d55abe757\",\"mc_texts\":[{\"mc_id\":1122566013,\"version_id\":1,\"mc_content\":\"wuliaoType:3 title:300-250   showUrl:rickshaw.cc targetUrl:http://dian.rickshaw.cc wirelessShowUrl:rickshaw.cc   wirelessTargetUrl:http://wap.rickshaw.cc    width:300   height:250  fileSrc:http://ubmcmm.baidustatic.com/media/v1/0f000Fj4MoGj6H74bbHnns.swf?url_type=1&snapsho=& fileSrcMd5:bf6cad84cef4bb3a3c5f32a655a2a2a4  attribute:  refMcId:    descInfo:   snapshot\"}]}";
                response.setResponseBody(data.getBytes());
                result = response;
            }
        };

        List<IdeaInfo> ideaList = new ArrayList<IdeaInfo>();
        IdeaInfo item = new IdeaInfo();
        item.setAdId(1L);
        item.setMcId(1);
        item.setMcType(1);
        item.setMcVersionId(1);
        item.setUrl("url");
        ideaList.add(item);

        List<IdeaInfo> ret = this.uBMCService.getIdeasInfoFromUBMC(1, ideaList);
        Assert.assertNotNull(ret);
        Assert.assertTrue(ret.size() > 0);
    }

}
