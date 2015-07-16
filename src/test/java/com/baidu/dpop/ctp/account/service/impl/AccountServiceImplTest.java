package com.baidu.dpop.ctp.account.service.impl;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.Verifications;

import com.baidu.dpop.ctp.common.rpc.service.AccountRemoteService;

public class AccountServiceImplTest {
    
    @Tested
    AccountServiceImpl accountServiceImpl;
    
    @Injectable
    AccountRemoteService accountRemoteService; 
    
    @SuppressWarnings("unchecked")
    @Test
    public void testGetUserInfoBatch() {
        
        accountServiceImpl.getUserInfoBatch(Arrays.asList(1), false);
        new Verifications() {
            {
                accountRemoteService.getUserInfoBatch((List<Integer>) any, (Boolean) any);
                times = 1;
            }
        };
        
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testGetUserInfoBatchNull() {

        new NonStrictExpectations() {
            {
                accountRemoteService.getUserInfoBatch((List<Integer>) any, (Boolean) any);
                result = new Exception();
            }
        };
        
        Assert.assertNull(accountServiceImpl.getUserInfoBatch(Arrays.asList(1), false));
    }

}
