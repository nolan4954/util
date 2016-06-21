package com.best.web.htyt.util;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

public class CamelJsonUtil {
	protected static final Log logger = LogFactory.getLog(CamelJsonUtil.class);
	private static ObjectMapper jm = new ObjectMapper();
	static {
		jm.setSerializationInclusion(Include.NON_NULL);
		jm.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
	}

	public static String toStr(Object o) {
		try {
			return jm.writeValueAsString(o);
		} catch (JsonProcessingException e) {
			logger.error("json write error ", e);
			throw new RuntimeException("json write error ");
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

}
