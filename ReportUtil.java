  package com.best.web.htyt.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

public class ReportUtil {

	public static final LinkedHashMap<String, String> printRowMapper = new LinkedHashMap<>();
	public static final LinkedHashMap<String, String> UserRowMapper = new LinkedHashMap<>();

	static {
		printRowMapper.put("userId", "用户ID");
		printRowMapper.put("date", "日期");
		printRowMapper.put("expressCode", "快递编码");
		printRowMapper.put("expressName", "快递名称");
		printRowMapper.put("printCount", "打单量");

		UserRowMapper.put("id", "用户ID");
		UserRowMapper.put("createTime", "创建时间");
		UserRowMapper.put("username", "用户名");
		UserRowMapper.put("province", "省");
		UserRowMapper.put("city", "市");
		UserRowMapper.put("country", "区");
		UserRowMapper.put("lastLoginTime", "上次登录时间");
	}

	public static void createCSV(List exportData, LinkedHashMap rowMapper, OutputStream outputStream) {
		BufferedWriter csvFileOutputStream = null;
		try {
			csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(outputStream, "gb2312"), 1024);

			for (Iterator propertyIterator = rowMapper.entrySet().iterator(); propertyIterator.hasNext();) {
				java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
				csvFileOutputStream.write("\"" + propertyEntry.getValue().toString() + "\"");
				if (propertyIterator.hasNext()) {
					csvFileOutputStream.write(",");
				}
			}
			csvFileOutputStream.newLine();

			for (Iterator iterator = exportData.iterator(); iterator.hasNext();) {
				Object row = iterator.next();
				for (Iterator propertyIterator = rowMapper.entrySet().iterator(); propertyIterator.hasNext();) {
					java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
					String value = null;
					try {
						value = BeanUtils.getProperty(row, propertyEntry.getKey().toString()).toString();

					} catch (NullPointerException e) {
						value = " ";
					}
					csvFileOutputStream.write("\"" + value + "\"");
					if (propertyIterator.hasNext()) {
						csvFileOutputStream.write(",");
					}
				}
				if (iterator.hasNext()) {
					csvFileOutputStream.newLine();
				}
			}
			csvFileOutputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				csvFileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
