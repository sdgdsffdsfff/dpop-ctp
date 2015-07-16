package com.baidu.dpop.ctp.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

public class ZipUtils {

    /**
     * 用于描述一个用于被压缩的文件
     */
    public static interface AbstractFile {

        /**
         * 用于描述该文件在zip中的路（路径分隔符为正斜杠）
         *
         * @return 文件在zip中的路径，如home/Downloads/foobar.png
         */
        String getFileZipPath();

        /**
         * 打开文件的输入流
         * @return 文件的输入流
         */
        InputStream openInputStream() throws IOException;
    }

    /**
     * 打包所有传入文件至输出流中
     *
     * @param files 需要压缩的文件
     * @param zippedOut 打包后的zip输出流（压缩完成后不会被关闭）
     */
    public static void packageFiles(Iterable<? extends AbstractFile> files, OutputStream zippedOut) throws IOException {
        ZipArchiveOutputStream out = new ZipArchiveOutputStream(zippedOut);
        out.setCreateUnicodeExtraFields(ZipArchiveOutputStream.UnicodeExtraFieldPolicy.ALWAYS);

        for (AbstractFile file : files) {
            ZipArchiveEntry entry = new ZipArchiveEntry(file.getFileZipPath());
            out.putArchiveEntry(entry);

            InputStream entryIn = file.openInputStream();
            try {
                IOUtils.writeTo(entryIn, out, 1024);
            } finally {
                entryIn.close();
            }
            out.closeArchiveEntry();
        }

        out.flush();
        out.finish();
    }
}
