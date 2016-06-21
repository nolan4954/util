package com.best.web.htyt.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class SignUtil {

	public static final String CHARSET_NAME_UTF8 = "UTF-8";
	public static final char[] digital = "0123456789ABCDEF".toCharArray();
	public static final String DEFAULT_DATA_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	public static final String HMAC_SHA1 = "HmacSHA1";

	public static String getSign(Map<String, String> params, String secret) {

		StringBuffer signBuilder = new StringBuffer(secret);
		Object[] key = params.keySet().toArray();
		Arrays.sort(key);

		for (int i = 0; i < key.length; i++) {
			signBuilder.append(key[i]).append(params.get(key[i]));
		}

		signBuilder.append(secret);

		return getMD5Str(signBuilder.toString());
	}

	private static String getMD5Str(String str) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.reset();

			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return md5StrBuff.toString();
	}

	public static String getSignByHmac(String urlPath,Map<String, String> params, String secret) {

		StringBuffer signBuilder = new StringBuffer(urlPath);
		Object[] key = params.keySet().toArray();
		Arrays.sort(key);

		for (int i = 0; i < key.length; i++) {
			signBuilder.append(key[i]).append(params.get(key[i]));
		}

		String signature = hmacSha1ToHexStr(signBuilder.toString(), secret);

		return signature;
	}

	public static byte[] hmacSha1(byte[] data, byte[] key, int offset, int len) {
		SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);
		Mac mac = null;
		try {
			mac = Mac.getInstance(HMAC_SHA1);
			mac.init(signingKey);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		mac.update(data, offset, len);
		return mac.doFinal();
	}

	public static byte[] hmacSha1(byte[][] datas, byte[] key) {
		SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);
		Mac mac = null;
		try {
			mac = Mac.getInstance(HMAC_SHA1);
			mac.init(signingKey);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		for (byte[] data : datas) {
			mac.update(data);
		}
		return mac.doFinal();
	}

	public static byte[] hmacSha1(String[] datas, byte[] key) {
		SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);
		Mac mac = null;
		try {
			mac = Mac.getInstance(HMAC_SHA1);
			mac.init(signingKey);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (InvalidKeyException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		try {
			for (String data : datas) {
				mac.update(data.getBytes(CHARSET_NAME_UTF8));
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return mac.doFinal();
	}

	public static String hmacSha1ToHexStr(byte[] data, byte[] key, int offset, int len) {
		byte[] rawHmac = hmacSha1(data, key, offset, len);
		return encodeHexStr(rawHmac);
	}

	public static String hmacSha1ToHexStr(byte[] data, String key, int offset, int len) {
		try {
			return hmacSha1ToHexStr(data, key.getBytes(CHARSET_NAME_UTF8), offset, len);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static String hmacSha1ToHexStr(String str, String key) {
		try {
			byte[] data = str.getBytes(CHARSET_NAME_UTF8);
			return hmacSha1ToHexStr(data, key.getBytes(CHARSET_NAME_UTF8), 0, data.length);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static String format(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATA_TIME_FORMAT);
		return format.format(date);
	}

	public static String encodeHexStr(final byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		char[] result = new char[bytes.length * 2];
		for (int i = 0; i < bytes.length; i++) {
			result[i * 2] = digital[(bytes[i] & 0xf0) >> 4];
			result[i * 2 + 1] = digital[bytes[i] & 0x0f];
		}
		return new String(result);
	}

	public static byte[] decodeHexStr(final String str) {
		if (str == null) {
			return null;
		}
		char[] charArray = str.toCharArray();
		if (charArray.length % 2 != 0) {
			throw new RuntimeException("hex str length must can mod 2, str:" + str);
		}
		byte[] bytes = new byte[charArray.length / 2];
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			int b;
			if (c >= '0' && c <= '9') {
				b = (c - '0') << 4;
			} else if (c >= 'A' && c <= 'F') {
				b = (c - 'A' + 10) << 4;
			} else {
				throw new RuntimeException("unsport hex str:" + str);
			}
			c = charArray[++i];
			if (c >= '0' && c <= '9') {
				b |= c - '0';
			} else if (c >= 'A' && c <= 'F') {
				b |= c - 'A' + 10;
			} else {
				throw new RuntimeException("unsport hex str:" + str);
			}
			bytes[i / 2] = (byte) b;
		}
		return bytes;
	}

	public static String toString(final byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		try {
			return new String(bytes, CHARSET_NAME_UTF8);
		} catch (final UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static String toString(final byte[] bytes, String charset) {
		if (bytes == null) {
			return null;
		}
		try {
			return new String(bytes, charset);
		} catch (final UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static byte[] toBytes(final String str) {
		if (str == null) {
			return null;
		}
		try {
			return str.getBytes(CHARSET_NAME_UTF8);
		} catch (final UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}
