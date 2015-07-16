package com.baidu.dpop.ctp.review.vo;

import java.math.BigDecimal;

public class ReviewTaskAccuracyItem {

    private String name;
    private String text;
    private Double accuracy;
    
    public ReviewTaskAccuracyItem() {}
    
    public ReviewTaskAccuracyItem(String name, String text, Double accuracy) {
        this.name = name;
        this.text = text;
        BigDecimal bd = new BigDecimal(accuracy);
        this.accuracy = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

}
