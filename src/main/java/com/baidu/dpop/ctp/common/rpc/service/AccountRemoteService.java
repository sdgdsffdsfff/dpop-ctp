package com.baidu.dpop.ctp.common.rpc.service;

import java.util.List;

import com.baidu.dpop.ctp.account.vo.AccountGetResultInfo;

public interface AccountRemoteService {
    
    public AccountGetResultInfo getUserInfoBatch(List<Integer> arrUids, Boolean bolNeedEditInfo);

}
