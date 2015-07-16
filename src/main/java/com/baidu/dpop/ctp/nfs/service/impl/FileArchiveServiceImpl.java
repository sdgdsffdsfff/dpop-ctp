package com.baidu.dpop.ctp.nfs.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baidu.dpop.ctp.common.exception.BaseRuntimeException;
import com.baidu.dpop.ctp.common.utils.ZipUtils;
import com.baidu.dpop.ctp.nfs.service.FileArchiveService;
import com.baidu.dpop.ctp.nfs.utils.NfsUtils;

@Service
public class FileArchiveServiceImpl implements FileArchiveService {
	
    protected static final long ARCHIVE_SIZE_LIMIT_IN_MBS = 300;
    protected static final long ARCHIVE_SIZE_LIMIT_IN_BYTES = ARCHIVE_SIZE_LIMIT_IN_MBS << 20; // 300M
    
    public String getPackageFilePath(List<String> files, String archiveFilePath) {
    	List<NfsFile> nfsFiles = new ArrayList<NfsFile>();
    	for (String file : files) {
    		nfsFiles.add(new NfsFile(file));
    	}
    	return doPackage(nfsFiles, archiveFilePath);
    }
    
    private String doPackage(List<NfsFile> nfsFiles, String archiveFilePath) {

        // 判断是否过大
        long totalSizeInBytes = 0;
        for (NfsFile nfsFile : nfsFiles) {
        	File file = NfsUtils.getFile(nfsFile.getFileZipPath());
            if (file != null && file.isFile()) {
                totalSizeInBytes += file.length();
            }
        }
        if (totalSizeInBytes > ARCHIVE_SIZE_LIMIT_IN_BYTES) {
            throw new BaseRuntimeException("文件夹过大，超过" + ARCHIVE_SIZE_LIMIT_IN_MBS + "M。");
        }

        // 过期或第一次打包
        try {
            OutputStream archiveOut = NfsUtils.openFileOutputStream(archiveFilePath);
            try {
                ZipUtils.packageFiles(nfsFiles, archiveOut);
                return archiveFilePath;
            } finally {
                archiveOut.close();
            }
        } catch (IOException e) {
        	throw new BaseRuntimeException("打包失败", e);
        }
    }
	
	private class NfsFile implements ZipUtils.AbstractFile {
        
		private final String fileName;
		
        /**
         * 构造一个要打包的文件
         *
         * @param document 压缩文件对应的文档实体
         * @param folderPath 压缩文件在压缩包中所在文件夹的路径
         */
        NfsFile(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public String getFileZipPath() {
            return fileName;
        }

        @Override
        public InputStream openInputStream() throws IOException {
        	return NfsUtils.openFileInputStream(fileName);
        }
    }
}
