package com.best.web.htyt.util;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExportUtil {

	/**
	 * 创建生成的导出文件所在目录
	 * @param contextPath 
	 * @param type	导出文件业务类别
	 * @return 返回创建的目录
	 */
	public static String createDownloadDir(String contextPath, String type){
		String fileDir = contextPath + "download" + File.separator + type;
		File dir = new File(fileDir);
		if(!dir.exists()){
			dir.mkdirs();
		}
		return fileDir;
	}
	/**
	 * 生成下载文件的url
	 * @param requestUrl	当前servlet的request url
	 * @param type 导出文件业务类别
	 * @param fileName 下载文件名字
	 * @return 下载文件的url
	 */
	public static String generateDownloadUrl(String requestUrl, String type, String fileName){
		return requestUrl + "/download/" + type + "/" + fileName;
	}
	/**
	 * 生成下载的文件名字
	 * @param type 导出文件业务类别
	 * @return 返回生成的下载文件名字
	 */
	public static String generateDownloadFileName(String type) {
		String fileName = "";
		DateFormat ymdhmsFormat = new SimpleDateFormat("yyyyMMdd");
		Date today = new Date();
		String now = ymdhmsFormat.format(today);
		fileName = type + "_" + now.toString() + "_" + today.getTime() + ".csv";
		return fileName;
	}
}
