package com.baidu.dpop.ctp.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class IOUtils {

    /**
     * 将一个输入流传入一个输出流（输入流与输出流在完成后均不会被主动关闭）
     *
     * @param in 输入流
     * @param out 输出流
     * @param bufferSize 缓存大小
     * @throws IOException
     */
    public static void writeTo(InputStream in, OutputStream out, int bufferSize) throws IOException {
        if (in == null || out == null || bufferSize <= 0) {
            throw new IllegalArgumentException();
        }

        byte[] buffer = new byte[bufferSize];
        while (true) {
            int len = in.read(buffer);
            if (len <= 0) {
                break;
            }

            out.write(buffer, 0, len);
        }
    }

    /**
     * 返回一个空的输入流（第一次读取时返回EOF）
     * @return 空的输入流
     */
    public static InputStream emptyInputStream() {
        return new InputStream() {
            @Override
            public int read() throws IOException {
                return -1;
            }
        };
    }

    /**
     * 读取输入流中所有数据（输入流在方法返回后不会被关闭）
     * @param in 输入流
     * @return 输入流中所有数据
     * @throws IOException
     */
    public static byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            writeTo(in, out, 1024);
            return out.toByteArray();
        } finally {
            out.close();
        }
    }

    /**
     * 只读取输入流中前max字节数据
     * @param in 输入流
     * @param max 最多读取字节数
     * @return 输入流最多前max字节数据，长度不大于max的字节数组。若实际数据不足max，则数组的长度为实际读取到的长度。
     * @throws IOException
     */
    public static byte[] toByteArray(InputStream in, int max) throws IOException {
        if (max < 0) {
            throw new IllegalArgumentException();
        }
        if (max == 0) {
            return new byte[0];
        }

        int remains = max;
        byte[] result = new byte[max];

        while (remains > 0) {
            int actualRead = in.read(result, max - remains, remains);
            if (actualRead < 0) {
                break;
            }

            remains -= actualRead;
        }

        if (result.length == max) {
            return result;
        }
        return Arrays.copyOf(result, max);
    }
}
