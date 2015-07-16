package com.baidu.dpop.ctp.nfs.servlet.defaults;

import java.io.File;

import com.baidu.dpop.ctp.common.constants.FileTypeEnum;
import com.baidu.dpop.ctp.common.utils.MimeUtils;
import com.baidu.dpop.frame.core.servlet.fs.FSWebResource;

class NfsResource extends FSWebResource {

	private final FileTypeEnum type;

	public NfsResource(File file, FileTypeEnum type) {
		super(file);
		this.type = type;
	}

	@Override
	public String getMimeType() {
		return MimeUtils.getMimeType(this.type.getFormat());
	}
}
