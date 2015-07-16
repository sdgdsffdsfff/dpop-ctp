package com.baidu.dpop.ctp.account.vo;

import java.io.Serializable;
import java.util.List;

import com.baidu.dpop.ctp.account.bo.AccountInfo;

/**
 * 账户信息获取结果，包括是否成功，与获取到的信息列表
 * 
 * @author mading01
 * 
 */
public class AccountGetResultInfo implements Serializable {

    private static final long serialVersionUID = 5390490165625067670L;

    private Integer flag;
    private List<AccountInfo> data;

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public List<AccountInfo> getData() {
        return data;
    }

    public void setData(List<AccountInfo> data) {
        this.data = data;
    }

}
