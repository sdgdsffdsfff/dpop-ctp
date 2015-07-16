package com.baidu.dpop.ctp.account.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.baidu.dpop.ctp.account.service.AccountService;
import com.baidu.dpop.ctp.account.vo.AccountGetResultInfo;
import com.baidu.dpop.ctp.common.rpc.service.AccountRemoteService;

@SuppressWarnings("restriction")
@Service
public class AccountServiceImpl implements AccountService {

    Logger LOG = Logger.getLogger(AccountServiceImpl.class);
    
    @Resource
    AccountRemoteService accountRemoteService;
    
    @Override
    public AccountGetResultInfo getUserInfoBatch(List<Integer> arrUids, Boolean bolNeedEditInfo) {

        try {
            return accountRemoteService.getUserInfoBatch(arrUids, bolNeedEditInfo);
        } catch (Exception e) {
            LOG.error(e.getLocalizedMessage(), e);
            return null;
        }
    }
}
