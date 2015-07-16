package com.baidu.dpop.ctp.common.utils;

import java.util.ArrayList;
import java.util.List;

public class CollectionDisplayUtil {

	public static String listToString(List<?> list, String start, String end, String sep) {


		StringBuilder builder = new StringBuilder(start);
		if (list == null) {
			return builder.append(end).toString();
		}

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == null || list.get(i) == "") {
				continue;
			}
			// 如果值是list类型则调用自己
			if (list.get(i) instanceof List) {
				builder.append(listToString((List<?>) list.get(i), start, end, sep));
				builder.append(sep);
				// } else if (list.get(i) instanceof Map) {
				// sb.append(MapToString((Map<?, ?>) list.get(i)));
				// sb.append(SEP1);
			} else {
				builder.append(list.get(i));
				if (i < list.size() - 1) {
					builder.append(sep);
				}
			}
		}
		builder.append(end);
		return builder.toString();
	}
	
	public static List<Integer> getIntegerListFromString(String line, String sp) {
		if (line == null || line.length() < 1) {
			return null;
		}
		line = line.replaceAll("\\s", "");
		
		List<Integer> result = new ArrayList<Integer>();
		for (String i : line.split(sp)) {
			result.add(Integer.valueOf(i));
		}
		return result;
	}

}
