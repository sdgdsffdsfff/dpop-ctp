package com.baidu.dpop.ctp.common.utils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.baidu.dpop.ctp.common.vo.HttpClientResponse;

@SuppressWarnings("restriction")
public class HttpClientUtils {
    private static final Logger LOGER = Logger.getLogger(HttpClientUtils.class);

    /**
     * 读取超时(milliseconds)
     */
    private int soTimeout = 10000;

    /**
     * 连接超时(milliseconds)
     */
    private int connectionTimeout = 10000;
    /**
     * 每个HOST的最大连接数量
     */
    private int maxConnectionsPerHost = 20;

    /**
     * 连接池的最大连接数maxHostConnections The default maximum.
     */
    private int maxTotalConnections = 60;

    /**
     * 连接池
     */
    private HttpConnectionManager httpConnectionManager;

    /**
     * HttpConnectionManager初始化锁
     */
    private byte[] httpConnectionInitLock = new byte[1];

    enum RequestType {
        GET("GET"), POST("POST");
        
        @SuppressWarnings("unused")
        private String desc;
        
        private RequestType(String desc) {
            this.desc = desc;
        }
    }

    /**
     * 初始化httpConnectionManager
     */

    @PostConstruct
    public void initHttpConnectionManager() {
        if (null != httpConnectionManager) {
            return;
        }

        synchronized (httpConnectionInitLock) {
            httpConnectionManager = new MultiThreadedHttpConnectionManager();
            HttpConnectionManagerParams params = httpConnectionManager.getParams();
            params.setConnectionTimeout(this.getConnectionTimeout());
            params.setSoTimeout(this.getSoTimeout());
            params.setDefaultMaxConnectionsPerHost(this.getMaxConnectionsPerHost());
            params.setMaxTotalConnections(this.getMaxTotalConnections());
        }
    }

    public HttpClientResponse doPostRequest(String url, Map<String, Object> requestParam) {
        HttpClientResponse response = this.request(url, requestParam, RequestType.POST);
        return response;
    }

    public HttpClientResponse doGetRequest(String url, Map<String, Object> requestParam) {
        HttpClientResponse response = this.request(url, requestParam, RequestType.GET);
        return response;
    }

    /**
     * 发送一个请求，并获取响应后关闭http连接
     * 
     * @param url
     *            ：请求的URL
     * @param requestParam
     *            ：请求的参数
     * @param type
     *            ：请求的类型GET(Byte.valueOf("0"), "GET请求"), POST(Byte.valueOf("1"),
     *            "POST请求");
     * @return
     */
    private HttpClientResponse request(String url, Map<String, Object> requestParam, RequestType type) {
        if (StringUtils.isBlank(url)) {
            throw new RuntimeException("request url can not be blank");
        }
        HttpMethod httpMethod = getHttpMethod(url, requestParam, type);
        HttpClient httpClient = getHttpClient();
        try {
            httpClient.executeMethod(httpMethod);
            HttpClientResponse response = tranHttpMethodResponse(httpMethod);
            return response;
        } catch (HttpException e) {
            LOGER.error(String.format("HttpClientUtils request[%s] has HttpException ,Detail:", url), e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            LOGER.error(String.format("HttpClientUtils request[%s] has IOException ,Detail:", url), e);
            throw new RuntimeException(e);
        } finally {
            // release连接
            httpMethod.releaseConnection();
        }
    }

    /**
     * 将HttpMethod请求响应转化成 HttpClientResponse
     * 
     * @param httpMethod
     * @return
     */
    private HttpClientResponse tranHttpMethodResponse(HttpMethod httpMethod) {
        if (null == httpMethod) {
            throw new RuntimeException("httpMethod can not be null");
        }
        HttpClientResponse httpClientResponse = new HttpClientResponse();
        try {
            httpClientResponse.setStatusCode(httpMethod.getStatusCode());
            httpClientResponse.setResponseBody(httpMethod.getResponseBody());
            return httpClientResponse;
        } catch (IOException e) {
            LOGER.error(String.format("HttpClientUtils tranHttpMethodResponse[%s] has IOException ," + "Detail:",
                    httpMethod.getPath()), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * getHttpClient
     * 
     * @return
     */
    private HttpClient getHttpClient() {
        // 初始化httpConnectionManager
        initHttpConnectionManager();

        HttpClient httpClient = new HttpClient(httpConnectionManager);
        HttpClientParams httparams = new HttpClientParams();
        httparams.setSoTimeout(this.getSoTimeout());
        httparams.setConnectionManagerTimeout(this.getConnectionTimeout());
        httpClient.setParams(httparams);

        return httpClient;
    }

    /**
     * 获取一个HttpMethod
     * 
     * @param url
     *            ：请求的URL
     * @param requestParam
     *            ：请求的参数
     * @param type
     *            ：请求的类型GET(Byte.valueOf("0"), "GET请求"), POST(Byte.valueOf("1"),
     *            "POST请求");
     * @return
     */
    private HttpMethod getHttpMethod(String url, Map<String, Object> requestParam, RequestType type) {
        if (StringUtils.isBlank(url)) {
            throw new RuntimeException("request url can not be blank");
        }

        HttpMethod httpMethod = new GetMethod(url);
        if (RequestType.POST.equals(type)) {
            httpMethod = new PostMethod(url);
        }

        if (null != requestParam) {
            List<NameValuePair> queries = new LinkedList<NameValuePair>();
            for (Map.Entry<String, Object> item : requestParam.entrySet()) {
                String name = item.getKey();
                Object value = item.getValue();
                queries.add(new NameValuePair(name, value == null ? null : String.valueOf(value)));
            }
            httpMethod.setQueryString(queries.toArray(new NameValuePair[queries.size()]));
        }
        return httpMethod;
    }

    public int getSoTimeout() {
        return soTimeout;
    }

    public void setSoTimeout(int soTimeout) {
        this.soTimeout = soTimeout;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getMaxConnectionsPerHost() {
        return maxConnectionsPerHost;
    }

    public void setMaxConnectionsPerHost(int maxConnectionsPerHost) {
        this.maxConnectionsPerHost = maxConnectionsPerHost;
    }

    public int getMaxTotalConnections() {
        return maxTotalConnections;
    }

    public void setMaxTotalConnections(int maxTotalConnections) {
        this.maxTotalConnections = maxTotalConnections;
    }

}
