package com.baidu.dpop.ctp.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.baidu.dpop.ctp.common.utils.ZipUtils.AbstractFile;

/**   
 * ZipUtilsTest
 * @author cgd  
 * @date 2015年5月15日 上午11:33:40 
 */
public class ZipUtilsTest {
    
    @Test
    public void testpackageFiles() throws IOException {
        
        List<AbstractFile> files = new ArrayList<AbstractFile>();
        AbstractFile file = new MockAbstractFile();
        files.add(file);
        OutputStream os = new ByteArrayOutputStream();
        
        ZipUtils.packageFiles(files, os);
        Assert.assertTrue(true);
        
    }
    
    class MockAbstractFile implements AbstractFile {

        @Override
        public String getFileZipPath() {
            return "file_path";
        }

        @Override
        public InputStream openInputStream() throws IOException {
            byte[] data = "byte_data".getBytes();
            InputStream is = new ByteArrayInputStream(data);
            return is;
        }
        
    }
}
