package com.baidu.dpop.ctp.common.utils;

import org.junit.Test;
import org.springframework.util.Assert;

public class MimeUtilTest {
	
	@Test
    public void testMimeUtils(){
        //avi=video/x-msvideo
        String mimeType = MimeUtils.getMimeType("csv");
        Assert.isTrue("text/csv".equals(mimeType));
    }

}
