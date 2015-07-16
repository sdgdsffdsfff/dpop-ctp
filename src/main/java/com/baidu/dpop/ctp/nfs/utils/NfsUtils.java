package com.baidu.dpop.ctp.nfs.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.baidu.dpop.ctp.common.constants.Constants;
import com.baidu.dpop.ctp.common.exception.BaseRuntimeException;
import com.baidu.dpop.frame.core.context.DpopPropertyUtils;

/**
 * ClassName: NFSUtils <br/>
 * <p/>
 * date: 2014-7-29 下午2:04:46 <br/>
 * <p/>
 * 不同的部署环境，NFS部署情况不同<br/>
 * 这会导致读取NFS文件不能根据全路径读取，<br/>
 * 需根据文件相对于NFS挂载目录的路径<br/>
 * <p/>
 * 目前RD/QA/Online三套环境，分别不同<br/>
 * 例如：<br/>
 * RD环境模拟NFS挂载在"c:/nfs/"目录下<br/>
 * QA环境模拟NFS挂载在"/home/work/nfs/mut/"目录下<br/>
 * Online环境NFS挂载在"/home/work/rmp/nfs/mut/"目录下<br/>
 * <p/>
 * 那么读取一图片"images/0_rmp.jpg"绝对路径分别是 <br/>
 * RD： c:/nfs/images/0_rmp.jpg <br/>
 * QA： /home/work/nfs/mut/images/0_rmp.jpg <br/>
 * Online：/home/work/rmp/nfs/mut/images/0_rmp.jpg <br/>
 * <p/>
 * <br/>
 * <br/>
 * <br/>
 * <br/>
 * 
 * @author huhailiang
 * @since JDK 1.6
 */
public class NfsUtils {

	private static final Logger logger = Logger.getLogger(NfsUtils.class);

	/**
	 * 默认copy大小
	 */
	public static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

	/**
	 * NFS挂载的root目录
	 */
	public static String NFS_ROOT_DIR;

	/**
	 * 项目的顶级域名
	 */
	public static String WWW_ROOT;

	/**
	 * 初始化挂载目录参数. <br/>
	 * 
	 * @author huhailiang
	 * @since JDK 1.6
	 */
	private static void initNfsRoot() {
		if (NFS_ROOT_DIR != null) {
			return;
		}

		String nfsRootDir = DpopPropertyUtils
				.getProperty(Constants.NFS_ROOT_DIR_KEY);
		if (StringUtils.isBlank(nfsRootDir)) {
			String message = String.format("can not find propertys[%s] value，"
					+ "please check configure[constants.properties]",
					Constants.NFS_ROOT_DIR_KEY);

			logger.error(message);
			throw new BaseRuntimeException(message);
		}

		NFS_ROOT_DIR = nfsRootDir;
	}

	/**
	 * 初始化一个可以写的文件. <br/>
	 * 如果文件不存在则创建一个新空白文件 <br/>
	 * 和getFile()方法不同的是，getFile不会创建文件，如果文件为空则直接返回 <br/>
	 * 而getWriteFile如果文件盒文件所处的目录为空则自动创建 <br/>
	 * 
	 * @param fileRelativePath
	 *            ：文件的相对路径
	 * @return
	 * @throws IOException
	 * @since JDK 1.6
	 */
	public static File getWriteFile(String fileRelativePath) throws IOException {
		initNfsRoot();
		String fileAbsolutePath = FilenameUtils.concat(NFS_ROOT_DIR,
				fileRelativePath);
		File descFile = new File(fileAbsolutePath);
		// 父目录不存在，创建目录
		if (!descFile.getParentFile().exists()) {
			descFile.getParentFile().mkdirs();
		}
		// 如果文件不存在则创建，
		if (!descFile.exists()) {
			try {
				descFile.createNewFile();
			} catch (IOException e) {
				String message = String.format(
						"nfs [%s] file.createNewFile() has error",
						fileAbsolutePath);
				logger.error(message);
				throw e;
			}
		}
		return descFile;
	}

	/**
	 * 根据文件的相对路径获取NFS文件. <br/>
	 * 
	 * @param fileRelativePath
	 *            :相对路径
	 * @return
	 * @since JDK 1.6
	 */
	public static File getFile(String fileRelativePath) {
		String fileAbsolutePath = getNfsAbsolutePath(fileRelativePath);
		return new File(fileAbsolutePath);
	}

	/**
	 * getNfsHttpVisitURL:(获取访问NFS的http url). <br/>
	 * 
	 * @param fileRelativePath
	 * @return
	 * @author huhailiang
	 * @since JDK 1.6
	 */
	public static String getNfsHttpVisitURL(String fileRelativePath) {
		if (WWW_ROOT == null) {
			String wwwRoot = DpopPropertyUtils
					.getProperty(Constants.WWW_ROOT_KEY);
			if (StringUtils.isBlank(wwwRoot)) {
				String message = String
						.format("can not find propertys[%s] value，"
								+ "please check configure[constants.properties]",
								Constants.WWW_ROOT_KEY);

				logger.error(message);
				throw new BaseRuntimeException(message);
			}
			WWW_ROOT = wwwRoot;
		}

		if (StringUtils.isEmpty(fileRelativePath)) {
			return WWW_ROOT;
		}

		String fileRelativePathTemp = fileRelativePath;
		if (fileRelativePath.startsWith("/")) {
			fileRelativePathTemp = fileRelativePath.substring(1);
		}

		if (WWW_ROOT.endsWith("/")) {
			return String.format("%snfs/%s", WWW_ROOT, fileRelativePathTemp);
		}

		return String.format("%s/nfs/%s", WWW_ROOT, fileRelativePathTemp);

	}

	public static String getNfsAbsolutePath(String fileRelativePath) {
		initNfsRoot();
		String fileAbsolutePath = FilenameUtils.concat(NFS_ROOT_DIR,
				fileRelativePath);
		return fileAbsolutePath;
	}

	/**
	 * 删除文件，如果是目录则删除目录下全部文件，如果是文件则删除本文件. <br/>
	 * 删除成功 返回 true 删除失败返回 false
	 * 
	 * @param fileRelativePath
	 * @return
	 * @since JDK 1.6
	 */
	public static boolean deleteFile(String fileRelativePath) {
		String fileAbsolutePath = getNfsAbsolutePath(fileRelativePath);
		return FileUtils.deleteQuietly(new File(fileAbsolutePath));
	}

	/**
	 * 根据文件的相对路径获取NFS文件流<br/>
	 * 
	 * @param fileRelativePath
	 *            :NFS文件输入路径
	 * @return
	 * @throws IOException
	 *             :文件IO流异常
	 * @since JDK 1.6
	 */
	public static InputStream openFileInputStream(String fileRelativePath)
			throws IOException {
		File nfsDesFile = getFile(fileRelativePath);
		return openFileInputStream(nfsDesFile);
	}

	/**
	 * 根据文件对象 获取NFS文件输入流<br/>
	 * 
	 * @param file
	 *            :NFS文件对象
	 * @return
	 * @throws IOException
	 *             :文件IO流异常
	 * @since JDK 1.6
	 */
	public static InputStream openFileInputStream(File file) throws IOException {
		if (null == file) {
			throw new BaseRuntimeException(
					"NfsUtils.readFileStream(File file) params file can not be null");
		}
		return FileUtils.openInputStream(file);
	}

	/**
	 * 根据文件的相对路径获取NFS文件输出流<br/>
	 * 
	 * @param fileRelativePath
	 *            :NFS文件路径
	 * @return
	 * @throws IOException
	 *             :文件IO流异常
	 * @since JDK 1.6
	 */
	public static OutputStream openFileOutputStream(String fileRelativePath)
			throws IOException {
		File nfsDesFile = getFile(fileRelativePath);
		return openFileOutputStream(nfsDesFile);
	}

	/**
	 * 根据文件对象 获取NFS out 文件输出流<br/>
	 * 
	 * @param file
	 *            :NFS文件对象
	 * @return
	 * @throws IOException
	 *             :文件IO流异常
	 * @since JDK 1.6
	 */
	public static OutputStream openFileOutputStream(File file)
			throws IOException {
		if (null == file) {
			throw new BaseRuntimeException(
					"NfsUtils.readFileStream(File file) params file can not be null");
		}
		return FileUtils.openOutputStream(file);
	}

	/**
	 * 通过文件相对路径读取文件的全部字节流 <br/>
	 * 
	 * @param fileRelativePath
	 *            ：文件相对路径
	 * @return
	 * @throws IOException
	 *             :文件IO流异常
	 * @since JDK 1.6
	 */
	public static byte[] readFullyBytes(String fileRelativePath)
			throws IOException {
		File nfsDesFile = getFile(fileRelativePath);
		return readFullyBytes(nfsDesFile);
	}

	/**
	 * 通过文件读取文件的全部字节流 <br/>
	 * 
	 * @param fileRelativePath
	 *            ：文件相对路径
	 * @return
	 * @throws IOException
	 *             :文件IO流异常
	 * @since JDK 1.6
	 */
	public static byte[] readFullyBytes(File file) throws IOException {
		if (null == file) {
			throw new BaseRuntimeException(
					"NfsUtils.readFullyBytes(File file) params file can not be null");
		}
		return FileUtils.readFileToByteArray(file);
	}

	/**
	 * 从一个 <code>InputStream</code>复制 bytes 到一个 <code>OutputStream</code>.
	 * 
	 * @param input
	 * @param output
	 * @return
	 * @throws IOException
	 * @since JDK 1.6
	 */
	public static long copy(InputStream input, OutputStream output)
			throws IOException {
		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		long count = 0;
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
			count += n;
		}
		return count;
	}

	/**
	 * 从一个 <code>InputStream</code>复制 bytes 到一个 <code>OutputStream</code>.
	 * 
	 * @param input
	 *            :输入流
	 * @param output
	 *            ：输出流
	 * @param start
	 *            ：开始流标记
	 * @param end
	 *            ：结束流标记
	 * @return
	 * @throws IOException
	 * @since JDK 1.6
	 */
	public static long copy(InputStream input, OutputStream output, long start,
			long end) throws IOException {

		long skipped = 0;
		try {
			skipped = input.skip(start);
		} catch (IOException e) {
			throw e;
		}
		if (skipped < start) {
			throw new IOException(String.format("skipfail start[%d] end[%d] ",
					start, end));
		}
		// 总的复制数
		long bytesToRead = end - start + 1;

		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		int len = buffer.length;
		while ((bytesToRead > 0) && (len >= buffer.length)) {
			try {
				len = input.read(buffer);
				if (bytesToRead >= len) {
					output.write(buffer, 0, len);
					bytesToRead -= len;
				} else {
					output.write(buffer, 0, (int) bytesToRead);
					bytesToRead = 0;
				}
			} catch (IOException e) {
				throw e;
			}

			// 为-1的时候
			if (len < buffer.length) {
				break;
			}

		}
		return 1L;
	}

	/**
	 * 将文件数据写入文件中. <br/>
	 * 
	 * @param fileFullyBytes
	 *            ：文件的byte数据
	 * @param fileRelativePath
	 *            :文件的相对路径
	 * @throws IOException
	 * @since JDK 1.6
	 */
	public static void writeFile(byte[] fileFullyBytes, String fileRelativePath)
			throws IOException {
		File descFile = NfsUtils.getWriteFile(fileRelativePath);
		FileUtils.writeByteArrayToFile(descFile, fileFullyBytes);
	}

	/**
	 * 将文件流写入文件中. <br/>
	 * 
	 * @param input
	 *            ：文件数据输入流
	 * @param fileRelativePath
	 *            :存储文件的目标相对路径
	 * @throws IOException
	 * @since JDK 1.6
	 */
	public static void writeFile(InputStream input, String fileRelativePath)
			throws IOException {//
		File descFile = NfsUtils.getWriteFile(fileRelativePath);
		OutputStream outWriteStream = null;
		try {
			outWriteStream = FileUtils.openOutputStream(descFile);
			copy(input, outWriteStream);
		} finally {
			IOUtils.closeQuietly(outWriteStream);
		}
	}
}
