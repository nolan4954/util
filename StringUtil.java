package com.best.web.htyt.util;

import java.util.List;

/**
 * @description:
 * 
 * @author ghdong
 * 
 */
public class StringUtil {

	public static String appendString(List<String> inputList, String rexg) {
		StringBuffer buf = new StringBuffer();
		for (String str : inputList) {
			buf.append(str).append(rexg);
		}
		String str = buf.toString();
		if (!"".equals(str)) {
			str = str.substring(0, str.lastIndexOf(rexg) - 1);
		}

		return str;
	}

	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * 不区分大小写判断2个字符串是否相同(null与空字符串""比较为相等)
	 * 
	 * @param firstStr
	 * @param secondStr
	 * @return
	 */
	public static boolean isEqualIgnoreCase(String firstStr, String secondStr) {
		if (firstStr == null) {
			firstStr = "";
		}
		if (secondStr == null) {
			secondStr = "";
		}
		if (firstStr.equalsIgnoreCase(secondStr)) {
			return true;
		}
		return false;
	}

	/**
	 * 区分大小写判断2个字符串是否相同(null与空字符串""比较为相等)
	 * 
	 * @param firstStr
	 * @param secondStr
	 * @return
	 */
	public static boolean isEqual(String firstStr, String secondStr) {
		if (firstStr == null) {
			firstStr = "";
		}
		if (secondStr == null) {
			secondStr = "";
		}
		if (firstStr.equalsIgnoreCase(secondStr)) {
			return true;
		}
		return false;
	}

	public static String nvl(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}

	public static final String NUMBERSAVER = "	";

	/*
	 * if there is a comma in string , we add "" to declare that was a whole
	 * part not to be separate ;
	 */
	public static String strFiter(String s) {
		String nvls = nvl(s);
		String s1 = nvls.replace("\n", "").replace("\t", "").replace("\r", "");
		String s2 = s1;
		if (s1.contains(",")) {
			s2 = s1.replace("\"", "\'");
			s2 = "\"" + s2 + "\"";
		}

		return s2;
	}

	public static String forCSV(String s) {
		String nvls = nvl(s);
		String s1 = nvls.replace("\n", "").replace("\r", "");
		String s2 = s1;
		if (s1.contains(",")) {
			s2 = s1.replace("\"", "\'");
			s2 = "\"" + s2 + "\"";
		}

		return s2;
	}
}
