package com.baidu.dpop.ctp.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

/**
 * IOUtilsTest
 * 
 * @author cgd
 * @date 2015年5月14日 下午5:31:21
 */
public class IOUtilsTest {

    @Test
    public void testwriteTo() throws Exception {
        String data = "ioData";
        InputStream is = new ByteArrayInputStream(data.getBytes());
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        IOUtils.writeTo(is, os, 2);
        Assert.assertTrue(os.toByteArray().length > 0);
    }

    @Test
    public void testemptyInputStream() {
        InputStream is = IOUtils.emptyInputStream();
        Assert.assertNotNull(is);
    }

    @Test
    public void testtoByteArray() throws Exception {
        String data = "ioData";
        InputStream is = new ByteArrayInputStream(data.getBytes());

        byte[] ret = IOUtils.toByteArray(is);
        Assert.assertTrue(ret.length > 0);
    }

    @Test(expected = Exception.class)
    public void testtoByteArrayWithException() throws Exception {
        String data = "ioData";
        InputStream is = new ByteArrayInputStream(data.getBytes());

        IOUtils.toByteArray(is, -1);
    }

    @Test
    public void testtoByteArray2() throws Exception {
        String data = "ioData";
        InputStream is = new ByteArrayInputStream(data.getBytes());

        byte[] ret = IOUtils.toByteArray(is, 0);
        Assert.assertTrue(ret.length == 0);

        ret = IOUtils.toByteArray(is, 2);
        Assert.assertTrue(ret.length > 0);
    }

}
