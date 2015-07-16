package com.baidu.dpop.ctp.monitor.web.controller;

import org.junit.Assert;
import org.junit.Test;

import mockit.Tested;

/**
 * SysMonitorController Test
 * 
 * @author cgd
 * @date 2015年1月5日 上午11:10:13
 */
public class SysMonitorControllerTest {

	@Tested
	private SysMonitorController sysMonitorController;

	@Test
	public void testSysMonitor() {
		int ret = sysMonitorController.sysMonitor();
		Assert.assertTrue(ret == 1);
	}

}
