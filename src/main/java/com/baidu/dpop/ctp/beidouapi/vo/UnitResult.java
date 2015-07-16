package com.baidu.dpop.ctp.beidouapi.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * beidou mcpack调用结果封装
 * 
 * */
public class UnitResult<T> {

    /** 0--成功，1--失败 ，2--参数错误 **/
    private int status = ResponseStatus.SUCCESS;

    /** 返回数据 **/
    private List<T> data;

    /** 总页数 **/
    private Integer totalPage;

    /** 总数量 **/
    private Integer totalNum;

    public void addResult(T result) {
        if (data == null) {
            data = new ArrayList<T>();
        }
        data.add(result);
    }

    public void addResults(List<T> results) {
        if (data == null) {
            data = new ArrayList<T>();
        }
        data.addAll(results);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }
}
