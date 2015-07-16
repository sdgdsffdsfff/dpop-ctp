package com.baidu.dpop.ctp.nfs.service;

import java.util.List;

public interface FileArchiveService {
	
	/**
	 * 压缩文件
	 * @param files 需要压缩的文件名(相对路径)
	 * @param archiveFilePath 压缩结果的文件名(相对路径)
	 * @return 压缩结果的文件名(相对路径)
	 */
	 public String getPackageFilePath(List<String> files, String archiveFilePath);
}
