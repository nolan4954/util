package com.best.web.htyt.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * HTTP工具类
 * 
 * @author lixiangyang
 * 
 */
public class HttpUtils {

	private static Log log = LogFactory.getLog(HttpUtils.class);

	/**
	 * 定义编码格式 UTF-8
	 */
	public static final String URL_PARAM_DECODECHARSET_UTF8 = "UTF-8";

	/**
	 * 定义编码格式 GBK
	 */
	public static final String URL_PARAM_DECODECHARSET_GBK = "GBK";

	private static final String URL_PARAM_CONNECT_FLAG = "&";

	private static final String EMPTY = "";

	private static MultiThreadedHttpConnectionManager connectionManager = null;

	private static int connectionTimeOut = 25000;

	// private static int connectionTimeOut = 2;

	private static int socketTimeOut = 25000;

	private static int maxConnectionPerHost = 20;

	private static int maxTotalConnections = 20;

	private static HttpClient client;

	static {
		connectionManager = new MultiThreadedHttpConnectionManager();
		connectionManager.getParams().setConnectionTimeout(connectionTimeOut);
		connectionManager.getParams().setSoTimeout(socketTimeOut);
		connectionManager.getParams().setDefaultMaxConnectionsPerHost(maxConnectionPerHost);
		connectionManager.getParams().setMaxTotalConnections(maxTotalConnections);
		client = new HttpClient(connectionManager);

		// ConnectingIOReactor ioReactor = null;
		// try {
		// ioReactor = new DefaultConnectingIOReactor();
		// } catch (IOReactorException e) {
		// e.printStackTrace();
		// }
		// PoolingNHttpClientConnectionManager cm = new
		// PoolingNHttpClientConnectionManager(ioReactor);
		// cm.setMaxTotal(100);
		// RequestConfig requestConfig =
		// RequestConfig.custom().setSocketTimeout(socketTimeOut).setConnectTimeout(connectionTimeOut).build();
		// asyncClient =
		// HttpAsyncClients.custom().setDefaultRequestConfig(requestConfig).setConnectionManager(cm).build();
		// asyncClient.start();
	}

	// static AtomicInteger ai = new AtomicInteger(0);
	//
	// private static Date bgDate;
	//
	// public static void main(String[] args) throws Exception {
	// String url =
	// "http://www.taobao.com/go/act/tb-fp/2014/hyqy.php?spm=0.0.0.0.JHcr5O";
	// int i = 0;
	// bgDate = new Date();
	// while (i < 50000) {
	// System.out.println("start:" + i);
	// i++;
	// asyncTest(url);
	// }
	// }
	//
	// private static void asyncTest(String url) throws Exception {
	//
	// final int st = ai.addAndGet(1);
	//
	// URLPostAsync(url, null, new FutureCallback<HttpResponse>() {
	//
	// @Override
	// public void failed(Exception ex) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void completed(HttpResponse result) {
	// System.out.println(st);
	//
	// System.out.println((System.currentTimeMillis() - bgDate.getTime()) +
	// "ms");
	// }
	//
	// @Override
	// public void cancelled() {
	// // TODO Auto-generated method stub
	//
	// }
	// });
	// }
	//
	// public static void stopAsync() {
	// try {
	// asyncClient.close();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	public static String URLPost(String url, Map<String, String> params) throws Exception {
		return URLPost(url, params, URL_PARAM_DECODECHARSET_UTF8);
	}

	public static Map URLPostMapResponse(String url, Map<String, String> params) throws Exception {
		return URLPostMapResponse(url, params, URL_PARAM_DECODECHARSET_UTF8);
	}

	/**
	 * POST方式提交数据
	 * 
	 * @param url
	 *            待请求的URL
	 * @param params
	 *            要提交的数据
	 * @param enc
	 *            编码
	 * @return 响应结果
	 * @throws Exception
	 * @throws IOException
	 *             IO异常
	 */
	public static String URLPost(String url, Map<String, String> params, String enc) throws Exception {

		String response = EMPTY;
		PostMethod postMethod = null;
		try {
			postMethod = new PostMethod(url);
			postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + enc);
			// 将表单的值放入postMethod中

			if (params != null) {
				Set<String> keySet = params.keySet();
				for (String key : keySet) {
					String value = params.get(key);
					postMethod.addParameter(key, value);
				}
			}

			// 执行postMethod
			int statusCode = client.executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) {
				response = postMethod.getResponseBodyAsString();
			} else {
				log.error("响应状态码 = " + postMethod.getStatusCode());
				throw new Exception("httpException-Code:" + postMethod.getStatusCode());
			}
		} catch (HttpException e) {
			log.error("发生网络的异常，可能是协议不对或者返回的内容有问题", e);
			e.printStackTrace();
			throw new HttpException("发生网络异常", e);
		} catch (IOException e) {
			log.error("发生IO异常", e);
			e.printStackTrace();
			throw new IOException("发生IO异常", e);
		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
				postMethod = null;
			}
		}

		return response;
	}

	/**
	 * GET方式提交数据
	 * 
	 * @param url
	 *            待请求的URL
	 * @param params
	 *            要提交的数据
	 * @param enc
	 *            编码
	 * @return 响应结果
	 * @throws IOException
	 *             IO异常
	 */
	public static String URLGet(String url, Map<String, String> params, String enc) {

		String response = EMPTY;
		GetMethod getMethod = null;
		StringBuffer strtTotalURL = new StringBuffer(EMPTY);

		if (strtTotalURL.indexOf("?") == -1) {
			strtTotalURL.append(url).append("?").append(getUrl(params, enc));
		} else {
			strtTotalURL.append(url).append("&").append(getUrl(params, enc));
		}
		log.debug("GET请求URL = \n" + strtTotalURL.toString());

		try {
			getMethod = new GetMethod(strtTotalURL.toString());
			getMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + enc);
			// 执行getMethod
			int statusCode = client.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				response = getMethod.getResponseBodyAsString();
			} else {
				log.debug("响应状态码 = " + getMethod.getStatusCode());
			}
		} catch (HttpException e) {
			log.error("发生致命的异常，可能是协议不对或者返回的内容有问题", e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error("发生网络异常", e);
			e.printStackTrace();
		} finally {
			if (getMethod != null) {
				getMethod.releaseConnection();
				getMethod = null;
			}
		}

		return response;
	}

	/**
	 * 据Map生成URL字符串
	 * 
	 * @param map
	 *            Map
	 * @param valueEnc
	 *            URL编码
	 * @return URL
	 */
	private static String getUrl(Map<String, String> map, String valueEnc) {

		if (null == map || map.keySet().size() == 0) {
			return (EMPTY);
		}
		StringBuffer url = new StringBuffer();
		Set<String> keys = map.keySet();
		for (Iterator<String> it = keys.iterator(); it.hasNext();) {
			String key = it.next();
			if (map.containsKey(key)) {
				String val = map.get(key);
				String str = val != null ? val : EMPTY;
				try {
					str = URLEncoder.encode(str, valueEnc);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				url.append(key).append("=").append(str).append(URL_PARAM_CONNECT_FLAG);
			}
		}
		String strURL = EMPTY;
		strURL = url.toString();
		if (URL_PARAM_CONNECT_FLAG.equals(EMPTY + strURL.charAt(strURL.length() - 1))) {
			strURL = strURL.substring(0, strURL.length() - 1);
		}

		return (strURL);
	}

	public static Map URLPostMapResponse(String url, Map<String, String> params, String enc) throws Exception {

		Map map = new HashMap();
		String response = EMPTY;
		int statusCode = 0;
		String errorMsg = EMPTY;
		PostMethod postMethod = null;
		try {
			postMethod = new PostMethod(url);
			postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + enc);
			// 将表单的值放入postMethod中
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				String value = params.get(key);
				postMethod.addParameter(key, value);
			}
			// 执行postMethod
			statusCode = client.executeMethod(postMethod);

			if (statusCode == HttpStatus.SC_OK) {
				response = postMethod.getResponseBodyAsString();
			} else {
				log.error("响应状态码 = " + postMethod.getStatusCode());
			}
		} catch (HttpException e) {
			log.error("发生致命的异常，可能是协议不对或者返回的内容有问题", e);
			errorMsg = "发生致命的异常，可能是协议不对或者返回的内容有问题" + e;
			e.printStackTrace();
		} catch (IOException e) {
			log.error("发生网络异常", e);
			errorMsg = "发生网络异常" + e;
			e.printStackTrace();
		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
				postMethod = null;
			}
		}
		map.put("response", response);
		map.put("statusCode", statusCode);
		map.put("errorMsg", errorMsg);
		return map;
	}
	
	public static String URLGet(String url,String params)
	{
		HttpClient client = new HttpClient();
		client.getParams().setParameter("http.socket.timeout",
				new Integer(6000));
		String getUrl = url+"?"+params;
		GetMethod getMethod = new GetMethod(getUrl);
		String responseTxt = "";
		getMethod.getResponseCharSet();
		try {
			int responseCode = client.executeMethod(getMethod);
			if (responseCode != HttpStatus.SC_OK) {
				responseTxt = getMethod.getResponseBodyAsString();
			} else {
				responseTxt = getMethod.getResponseBodyAsString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				getMethod.releaseConnection();
				client.getHttpConnectionManager().closeIdleConnections(0);
			} catch (Exception e) {
			}
		}
		return responseTxt;	
	}
}
