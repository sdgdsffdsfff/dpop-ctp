package com.baidu.dpop.ctp.nfs.service.impl;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import mockit.Mock;
import mockit.MockUp;
import mockit.Tested;

import org.junit.Test;

import com.baidu.dpop.ctp.nfs.utils.NfsUtils;

/**   
 * FileArchiveServiceImplTest
 * @author cgd  
 * @date 2015年5月19日 上午11:12:15 
 */
public class FileArchiveServiceImplTest {
    
    @Tested
    private FileArchiveServiceImpl fileArchiveService;
    
    @Test(expected=Exception.class)
    public void testgetPackageFilePathWithException() {
        new MockUp<NfsUtils>() {
            @Mock
            public File getFile(String fileRelativePath) {
                return null;
            }
            @Mock
            public OutputStream openFileOutputStream(String fileRelativePath) {
                return null;
            }
        };
        
        List<String> files = new ArrayList<String>();
        String archiveFilePath = "archiveFilePath";
        
        this.fileArchiveService.getPackageFilePath(files, archiveFilePath);
    }
    
    /*@Test
    public void testgetPackageFilePath() {
        new MockUp<NfsUtils>() {
            @Mock
            public File getFile(String fileRelativePath) {
                return new MockFile();
            }
            @Mock
            public OutputStream openFileOutputStream(String fileRelativePath) {
                OutputStream os = new ByteArrayOutputStream();
                return os;
            }
        };
        
        List<String> files = Arrays.asList("file1");
        String archiveFilePath = "archiveFilePath";
        
        String ret = this.fileArchiveService.getPackageFilePath(files, archiveFilePath);
        Assert.assertNotNull(ret);
    }
    
    class MockFile extends File {
        private static final long serialVersionUID = 1675237468233534013L;
        
        public MockFile() {
            super("test");
        }

        public MockFile(String pathname) {
            super(pathname);
        }
        
    }*/

}
