package com.best.web.htyt.util;

import java.util.List;
import java.util.Random;

public class MStringUtils {
	private static Random randGen = null;
	private static char[] numbersAndLetters = null;
	private static Object initLock = new Object();

	public static final String NUMBERSAVER = "	";

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	public static final String lowerFirstChar(String name) {
		String firstC = name.substring(0, 1);
		return firstC.toLowerCase() + name.substring(1);
	}

	public static final String randomString(int length) {

		if (length < 1) {
			return null;
		}
		if (randGen == null) {
			synchronized (initLock) {
				if (randGen == null) {
					randGen = new Random();
					numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
							+ "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
					// numbersAndLetters =
					// ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
				}
			}
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
			// randBuffer[i] = numbersAndLetters[randGen.nextInt(35)];
		}
		return new String(randBuffer);
	}

	/**
	 * Determine Whether the Sting is <code>null</code> or <code>""</code> or
	 * only containing blank character
	 * <p>
	 * Examples: <blockquote>
	 * 
	 * <pre>
	 * StringUtil.isBlank(null) == true
	 * StringUtil.isBlank("") == true
	 * StringUtil.isBlank("  ") == true
	 * StringUtil.isBlank("Best") == false
	 * StringUtil.isBlank("  Best  ") == false
	 * </pre>
	 * 
	 * </blockquote>
	 * 
	 * @param string
	 *            the string for testing
	 * 
	 * @return If it is blank, return true
	 */
	public static final boolean isBlank(String string) {
		if (string == null) {
			return true;
		}
		if (string.length() == 0) {
			return true;
		}
		for (int i = 0; i < string.length(); i++) {
			if (!Character.isWhitespace(string.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static String nvl(String str) {
		if (str == null) {
			return "";
		} else {
			return str;
		}
	}

	public static final String capitalizeFirstLetter(String sentence) {
		if (sentence == null) {
			return null;
		}
		StringBuffer result = new StringBuffer();
		String[] words = sentence.split(" ");
		for (String word : words) {
			result.append(word.substring(0, 1).toUpperCase());
			result.append(word.substring(1));
			result.append(" ");
		}
		return result.toString();
	}

	public static boolean isChinese(char a) {

		int v = a;

		return (v >= 19968 && v <= 171941);

	}

	public static String keepChinese(String org) {
		if (org == null) {
			return null;
		}
		String res = org;
		Boolean flag = true;
		loop: while (flag) {
			for (int i = res.length() - 1; i >= 0; i--) {
				if (isChinese(res.charAt(i))) {

				} else {
					res = res.replace(res.substring(i, i + 1), "");
					continue loop;
				}
			}
			flag = false;
		}

		return res;
	}

	public static String removeFormatChar(String org) {
		if (org == null) {
			return null;
		}
		String res = org.replace('\n', ' ');
		res = res.replace('\t', ' ');
		res = res.replace('\r', ' ');
		return res;
	}

	public static String smallJoin(String[] ss, String spliter) {
		String res = "";
		for (int i = 0; i < ss.length; i++) {
			if (org.apache.commons.lang.StringUtils.isBlank(ss[i])) {

			} else {
				res += ss[i] + spliter;
			}

		}
		return res.substring(0, res.length() - 1);
	}

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