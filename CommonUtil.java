package com.best.web.htyt.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

public class CommonUtil {

	public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static HashMap<String, String> validateCodeMap = Maps.newHashMap();
	public static final String ENCODE = "UTF-8";
	private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static Map<String, String> createTaobaoParams(String session) {
		// TODO Auto-generated method stub
		Map<String, String> map = Maps.newHashMap();
		map.put("session", session);
		return map;
	}

	public static String generateString(int length) // 参数为返回随机数的长度
	{
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(allChar.charAt(random.nextInt(allChar.length())));
		}
		return sb.toString();
	}

	public static String encodeMessage(String message) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return java.net.URLEncoder.encode(message, "utf-8");
	}

	public static String decodeMessage(String message) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return java.net.URLDecoder.decode(message, "utf-8");
	}

	public static String getMessageByMessageCode(String messageCode) {
		String message = "";
		switch (messageCode) {
		case "1":
			message = "登录名或密码错误,请重新输入";
			break;
		case "2":
			message = "没有注册,请先注册";
			break;
		default:
			message = "";
			break;
		}
		return message;
	}

	public static String Base64(String data){
		if(data==null)
		return "";
		return (new sun.misc.BASE64Encoder()).encode(data.getBytes());
	}
	
	public static String makeSign(String bizData, String pwd) {
		String data = bizData + pwd;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(data.getBytes(ENCODE));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
		byte[] b = md.digest();
		// return new String(Base64.encodeBase64(b));
		return (new sun.misc.BASE64Encoder()).encode(b);
	}

	
	public static String makeYUNDASign(String bizData, String username,String pwd) {
		String data = bizData+username+pwd;
		return MD5(data);
	}
	public static String makeYTOSign(String bizData, String pwd) {
		String data = bizData + pwd;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(data.getBytes(ENCODE));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
		byte[] b = md.digest();
		return new String(Base64.encodeBase64(b));
		// return (new sun.misc.BASE64Encoder()).encode(b);
	}

	public static String getBASE64(String s) {
		if (s == null)
			return null;
		String tempres = Base64.encodeBase64String(s.getBytes());
		if (tempres.substring(tempres.length() - 2).endsWith("\r\n")) {
			return tempres.substring(0, tempres.length() - 2);
		} else {
			return tempres;
		}
	}

	public static String toHexString(byte[] b) {
		// String to byte
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
			sb.append(HEX_DIGITS[b[i] & 0x0f]);
		}
		return sb.toString();
	}

	public static String make16ByteMD5Sign(String s) {
		try {
			// Create MD5 Hash
			MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
			digest.update(s.getBytes());
			byte messageDigest[] = digest.digest();
			return toHexString(messageDigest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return "";
	}

	public static String MD5(String inStr) {
		MessageDigest md5 = null;
		StringBuffer hexValue = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			byte[] byteArray = inStr.getBytes("UTF-8");
			byte[] md5Bytes = md5.digest(byteArray);
			hexValue = new StringBuffer();
			for (int i = 0; i < md5Bytes.length; i++) {
				int val = (md5Bytes[i]) & 0xff;
				if (val < 16) {
					hexValue.append("0");
				}
				hexValue.append(Integer.toHexString(val));
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}

		return hexValue.toString();
	}
	
	
	public static String fiterInvalidXmlCharacter(String raw){
		  char[] charArray = raw.toCharArray();
		  for(int i=0;i<charArray.length;i++){
			  if(charArray[i]==0x8)
			  charArray[i]=' ';
		  }
		return new String(charArray);
	}
	
	public static String clean(String s) {
		if (Strings.isNullOrEmpty(s)) {
			return s;
		}
		s = s.replace('&', ' ');
		s = s.replace('<', ' ');
		s = s.replace('>', ' ');
		s = s.replace('\b',' ');
		return s;
	};
	
}
