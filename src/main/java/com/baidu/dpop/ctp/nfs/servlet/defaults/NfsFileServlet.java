package com.baidu.dpop.ctp.nfs.servlet.defaults;

import com.baidu.dpop.frame.core.servlet.WebResourceRoot;
import com.baidu.dpop.frame.core.servlet.WebResourceServlet;

/**
 * NFS文件读取用servlet。从nfs获取文件，添加相应的缓存规则返回给客户端。
 * 
 * init-param参数说明： cacheDeltaSeconds：静态文件缓存时间（单位：秒）
 * supportByteRangeRequest：是否支持HTTP分段请求，true或false
 * 
 * HTTP请求参数说： filename：文件名称。当传递这个参数时，下载文件的名称将使用这个参数的值；没有传递时，会使用nfs中存储的文件名。
 */
public class NfsFileServlet extends WebResourceServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * 初始化函数
	 */
	@Override
	public void init() {
		super.init();
		WebResourceRoot resources = new NfsResourceRoot();
		setResources(resources);
	}
}
