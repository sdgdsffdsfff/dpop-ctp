package com.baidu.dpop.ctp.mainTask.vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class SearchUbmcIdeaInfo {
    @JsonProperty("mc_id")
    private Long mcId;
    @JsonProperty("version_id")
    private int versionId;
    @JsonProperty("mc_content")
    private String mcContent;
    
    public Long getMcId() {
        return mcId;
    }
    
    public void setMcId(Long mcId) {
        this.mcId = mcId;
    }
    
    public int getVersionId() {
        return versionId;
    }
    
    public void setVersionId(int versionId) {
        this.versionId = versionId;
    }
    
    public String getMcContent() {
        return mcContent;
    }
    
    public void setMcContent(String mcContent) {
        this.mcContent = mcContent;
    }
}
