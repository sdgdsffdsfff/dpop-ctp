package com.baidu.dpop.ctp.mainTask.vo;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class BaseUbmcResponse {
    private int success;
    @JsonProperty("error_code")
    private int errorCode;
    @JsonProperty("search_id")
    private String searchId;

    @JsonProperty("mc_texts")
    private List<SearchUbmcIdeaInfo> ideaInfos;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    public List<SearchUbmcIdeaInfo> getIdeaInfos() {
        return ideaInfos;
    }

    public void setIdeaInfos(List<SearchUbmcIdeaInfo> ideaInfos) {
        this.ideaInfos = ideaInfos;
    }
}
