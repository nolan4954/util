package com.best.web.htyt.util;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	protected static final Log logger = LogFactory.getLog(JsonUtil.class);
	private static ObjectMapper jm = new ObjectMapper();
	static {
		jm.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		jm.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
		jm.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		jm.setSerializationInclusion(Include.NON_NULL);
	}

	public static String toStr(Object o) {
		try {
			return jm.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			logger.error("json write error ", e);
			throw new RuntimeException("json write error ");
		}
	}

	public static String toStrSilence(Object o) {
		try {
			return jm.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			logger.error("json write error ", e);
			return null;
		}
	}

	public static String toStrThrow(Object o) throws JsonProcessingException {
		return jm.writeValueAsString(o);
	}

	public static <T> T toObjThrow(String s, Class<T> c) throws JsonParseException, JsonMappingException, IOException {
		return jm.readValue(s, c);
	}

	public static <T> T toObj(String s, Class<T> c) {
		try {
			return jm.readValue(s, c);
		} catch (IOException e) {
			logger.error("json read error " + s, e);
			throw new RuntimeException("json read error " + s);
		}
	}
	
	public static <T> T toYhdObj(String s, Class<T> c) {
		try {
			int index = s.indexOf("response");
			String result = s.substring(index+10, s.length()-1);
			return jm.readValue(result, c);
		} catch (IOException e) {
			logger.error("json read error " + s, e);
			throw new RuntimeException("json read error " + s);
		}
	}
}
