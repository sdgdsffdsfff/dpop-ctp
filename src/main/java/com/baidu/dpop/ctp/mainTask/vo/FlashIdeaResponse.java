package com.baidu.dpop.ctp.mainTask.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.codehaus.jackson.annotate.JsonProperty;

import com.google.common.base.Strings;

/**
 * FlashIdea Response
 * 
 * @author cgd
 * @date 2015年3月12日 下午5:06:19
 */
public class FlashIdeaResponse implements Serializable {

    private static final long serialVersionUID = -776496013996959237L;

    // 是否二进制数据标志位
    @JsonProperty("has_binary")
    private Byte hasBinary;

    // 反编译得到的文字
    @JsonProperty("text_list")
    private List<String> textList;

    // 得到的图片
    @JsonProperty("image_files")
    private List<String> imageList;

    // 外链
    @JsonProperty("out_mc_urls")
    private List<String> outMcUrl;

    // 外链
    @JsonProperty("Message")
    private String message;

    @JsonProperty("flashType")
    private String flashType;

    public List<String> getTextList() {
        return textList;
    }

    public void setTextList(List<String> textList) {
        this.textList = textList;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public List<String> getOutMcUrl() {
        return outMcUrl;
    }

    public void setOutMcUrl(List<String> outMcUrl) {
        this.outMcUrl = outMcUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Byte getHasBinary() {
        return hasBinary;
    }

    public void setHasBinary(Byte hasBinary) {
        this.hasBinary = hasBinary;
    }

    public String getFlashType() {
        return flashType;
    }

    public void setFlashType(String flashType) {
        this.flashType = flashType;
    }

    private List<String> removeEmpty(List<String> list) {
        List<String> result = new ArrayList<String>();
        for (String s : list) {
            if (!Strings.isNullOrEmpty(s)) {
                result.add(s);
            }
        }
        return result;
    }

    public void removeEmpty() {
        this.setImageList(removeEmpty(getImageList()));
        this.setOutMcUrl(removeEmpty(getOutMcUrl()));
        this.setTextList(removeEmpty(getTextList()));

        if (getHasBinary() != null && getHasBinary() == 1) {
            setFlashType("Binary");
        } else if (CollectionUtils.isNotEmpty(getOutMcUrl())) {
            setFlashType("外链");
        } else if (CollectionUtils.isNotEmpty(getImageList())
                || CollectionUtils.isNotEmpty(getTextList())) {
            setFlashType("多图");
        } else {
            setFlashType("无法解析");
        }
    }

    public static FlashIdeaResponse getFaildFlash() {
        FlashIdeaResponse result = new FlashIdeaResponse();
        result.setFlashType("解析失败");
        return result;
    }

}
