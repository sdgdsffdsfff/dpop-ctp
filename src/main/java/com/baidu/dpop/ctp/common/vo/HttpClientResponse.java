package com.baidu.dpop.ctp.common.vo;

public class HttpClientResponse {
    /**
     * 响应的二进制数据
     */
    private byte[] responseBody;

    /**
     * 状态码
     */
    private int statusCode;

    public byte[] getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(byte[] responseBody) {
        this.responseBody = responseBody;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

}
