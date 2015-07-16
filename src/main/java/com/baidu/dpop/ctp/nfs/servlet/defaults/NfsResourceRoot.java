package com.baidu.dpop.ctp.nfs.servlet.defaults;

import com.baidu.dpop.ctp.common.constants.FileTypeEnum;
import com.baidu.dpop.ctp.nfs.utils.NfsUtils;
import com.baidu.dpop.frame.core.servlet.WebResource;
import com.baidu.dpop.frame.core.servlet.WebResourceRoot;

public class NfsResourceRoot implements WebResourceRoot {

	@Override
	public WebResource getResource(String path) {
		String[] tmp = path.split("\\.");
		FileTypeEnum ft = FileTypeEnum.TYPE_TXT;
		if (tmp.length == 0) {
			ft = FileTypeEnum.TYPE_TXT;
		} else if (tmp[tmp.length - 1].equals("csv")) {
			ft = FileTypeEnum.TYPE_CSV;
		}
		return new NfsResource(NfsUtils.getFile(path), ft);
	}
}
