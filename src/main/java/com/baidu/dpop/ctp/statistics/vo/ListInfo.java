package com.baidu.dpop.ctp.statistics.vo;

import java.util.ArrayList;
import java.util.List;

public class ListInfo<T> {
    
    private String name;
    private String text;
    private List<T> data;
    
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
    
    public List<T> getData() {
        return data;
    }
    
    public void setData(List<T> data) {
        this.data = data;
    }
    
    public void add(T data) {
        if (this.data == null) {
            this.data = new ArrayList<T>();
        }
        this.data.add(data);
    }
}
