package com.best.web.htyt.util;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 记录程序执行时间，用来调整性能
 * @author BL00064
 *
 */
public class TimeLogger {
	/**
	 * Logger for this class
	 */
	private static final Log logger = LogFactory.getLog(TimeLogger.class);
	
	private static long startTime;
	
	/**
	 * 计时开始
	 * @param startPoint 开始点描述
	 */
	public static void start(String startPoint){
		startTime = System.currentTimeMillis();
		logger.warn("start point:" + startPoint + ". time:" + new Date(startTime));
	}
	/**
	 * 记录从计时开始时间到现在过去了多少毫秒
	 * @param point
	 */
	public static void logElapsedTime(String point){
		long now = System.currentTimeMillis();
		long elapsedTime = now - startTime;
		logger.warn("point" + point + ". elapsed time: " + elapsedTime + "ms");
	}
	
}
