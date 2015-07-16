package com.baidu.dpop.ctp.account.service;

import java.util.List;
import com.baidu.dpop.ctp.account.vo.AccountGetResultInfo;


public interface AccountService {
    
    /**
     * 批量获取账户信息，从凤巢提供的接口
     * @param arrUids 需要获取的列表
     * @param bolNeedEditInfo 设置为false即可
     * @return 账户信息获取结果
     * @see AccountGetResultInfo
     */
    public AccountGetResultInfo getUserInfoBatch(List<Integer> arrUids, Boolean bolNeedEditInfo);

}
