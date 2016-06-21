package com.best.web.htyt.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XMLUtil {
	protected static final Log logger = LogFactory.getLog(JsonUtil.class);
	private static XmlMapper mapper = new XmlMapper();

	public static String toStr(Object o) {
		try {
			return mapper.writeValueAsString(o);
		} catch (Exception e) {
			logger.error("xml write error ", e);
			throw new RuntimeException("xml write error ");
		}
	}
}
