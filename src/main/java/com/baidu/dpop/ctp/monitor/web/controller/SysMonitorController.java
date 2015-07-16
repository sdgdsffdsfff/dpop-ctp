package com.baidu.dpop.ctp.monitor.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SysMonitorController {

	/**
	 * jpass心跳功能
	 * */
	@RequestMapping(value = "sysMonitor.action")
	@ResponseBody
	public int sysMonitor() {
		return 1;
	}

}
