package com.baidu.dpop.ctp.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MimeUtils {

	private static final Map<String, String> DEFAULT_MIME_MAPPINGS = new HashMap<String, String>();
	private static final String mappingFile = "mime-mappings.properties";;

	static {
		InputStream in = MimeUtils.class.getResourceAsStream(mappingFile);
		if (in == null) {
			throw new IllegalStateException(
					"failed to load mime mapping file (file not found)");
		}

		try {
			Properties mineProperties = new Properties();
			mineProperties.load(in);
			Enumeration<?> propertyNames = mineProperties.propertyNames();
			while (propertyNames.hasMoreElements()) {
				String postfix = (String) propertyNames.nextElement();
				DEFAULT_MIME_MAPPINGS.put(postfix,
						mineProperties.getProperty(postfix));
			}
		} catch (IOException e) {
			throw new IllegalStateException("failed to load mime mapping file",
					e);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				throw new IllegalStateException(
						"failed to close mime mapping file", e);
			}
		}
	}

	/**
	 * 根据文件名后缀获取MIME类型
	 * 
	 * @param postfix
	 *            文件名后缀
	 * @return MIME类型
	 */
	public static String getMimeType(String postfix) {
		if (postfix == null) {
			return null;
		}

		return DEFAULT_MIME_MAPPINGS.get(postfix);
	}
}
