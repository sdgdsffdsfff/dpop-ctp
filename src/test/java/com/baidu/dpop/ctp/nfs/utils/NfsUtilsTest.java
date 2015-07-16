package com.baidu.dpop.ctp.nfs.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import com.baidu.dpop.ctp.base.StaticUtilsMockUp;

public class NfsUtilsTest {

	private static final String TEST_FILE_NAME = "NfsUtilsTest.csv";

	@Before
	public void setUp() {
		StaticUtilsMockUp.getDpopPropertyUtilsMockUp();
	}

	@Test
	public void testGetWriteFile() {
		try {
			NfsUtils.getWriteFile(TEST_FILE_NAME);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetFile() {
		NfsUtils.getFile(TEST_FILE_NAME);
	}

	@Test
	public void testOpenFileInputStream() {
		try {
			File testFile = NfsUtils.getWriteFile(TEST_FILE_NAME);
			Assert.notNull(testFile);
			Assert.isTrue(testFile.exists());

			InputStream input = NfsUtils.openFileInputStream(TEST_FILE_NAME);
			Assert.notNull(input);
			input.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testOpenFileInputStream2() {
		try {
			File testFile = NfsUtils.getWriteFile(TEST_FILE_NAME);
			Assert.notNull(testFile);
			Assert.isTrue(testFile.exists());

			InputStream input = NfsUtils.openFileInputStream(testFile);
			Assert.notNull(input);
			input.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// @Test
	// public void testReadFullyBytes() {
	// try {
	// byte[] datas = NfsUtils.readFullyBytes(TEST_FILE_NAME);
	// Assert.notNull(datas);
	// Assert.isTrue(datas.length > 0);
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// @Test
	// public void testReadFullyBytes2() {
	// try {
	// byte[] datas = NfsUtils.readFullyBytes(NfsUtils.getFile(TEST_FILE_NAME));
	// Assert.notNull(datas);
	// Assert.isTrue(datas.length > 0);
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	// @Test
	// public void testWriteFileBytes() {
	// final String outTestFile = "mytestOutwrite11";
	// try {
	// byte[] datas = NfsUtils.readFullyBytes(TEST_FILE_NAME);
	// Assert.notNull(datas);
	// Assert.isTrue(datas.length > 0);
	//
	// NfsUtils.writeFile(datas, outTestFile);
	// NfsUtils.deleteFile(outTestFile);
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	@Test
	public void testWriteFileInputStream() {
		try {
			InputStream datasInputStream = NfsUtils
					.openFileInputStream(TEST_FILE_NAME);
			Assert.notNull(datasInputStream);
			datasInputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testCopy() {
		final String outTestFile = "mytestOutwrite1";
		try {
			InputStream datasInputStream = NfsUtils
					.openFileInputStream(TEST_FILE_NAME);
			Assert.notNull(datasInputStream);

			File outFile = NfsUtils.getWriteFile(outTestFile);
			OutputStream output = NfsUtils.openFileOutputStream(outFile);
			NfsUtils.copy(datasInputStream, output);

			datasInputStream.close();
			output.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// @Test
	// public void testCopy2() {
	// final String outTestFile = "mytestOutwrite";
	// try {
	// InputStream datasInputStream =
	// NfsUtils.openFileInputStream(TEST_FILE_NAME);
	// Assert.notNull(datasInputStream);
	//
	// File outFile = NfsUtils.getWriteFile(outTestFile);
	// OutputStream output = NfsUtils.openFileOutputStream(outFile);
	// NfsUtils.copy(datasInputStream, output,0L,100L);
	//
	// datasInputStream.close();
	// output.close();
	//
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	// @Test
	// public void testGetNfsHttpVisitURL(){
	//
	// String descUrl =
	// StaticUtilsMockUp.WWW_ROOT_TEST+"nfs/images/huhailiang/0_121221.png";
	//
	// String url01 =
	// NfsUtils.getNfsHttpVisitURL("/images/huhailiang/0_121221.png");
	// Assert.isTrue(descUrl.equals(url01));
	//
	// String url02 =
	// NfsUtils.getNfsHttpVisitURL("images/huhailiang/0_121221.png");
	// Assert.isTrue(descUrl.equals(url02));
	//
	// }

	@Test
	public void testWriteFile() {
		final String writeTestFile = "mytestwrite";
		try {
			InputStream datasInputStream = NfsUtils
					.openFileInputStream(TEST_FILE_NAME);

			NfsUtils.writeFile(datasInputStream, writeTestFile);
			NfsUtils.deleteFile(writeTestFile);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testOpenFileOutputStream() {
		try {
			OutputStream outputStream = NfsUtils
					.openFileOutputStream(TEST_FILE_NAME);
			Assert.notNull(outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
