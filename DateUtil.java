package com.best.web.htyt.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {

	// 默认显示日期的格式
	public static final String DATAFORMAT_STR_YYYY_MM_SLASH = "yyyy/MM";
	// 默认显示日期的格式 yyyy/MM/dd
	public static final String DATAFORMAT_STR_YYYY_MM_DD_SLASH = "yyyy/MM/dd";
	// 默认显示日期时间的格式
	public static final String DATATIMEF_STR_YYYY_MM_DD_HH_MM_SS_SLASH = "yyyy/MM/dd HH:mm:ss";

	// 默认显示日期的格式
	public static final String DATAFORMAT_STR_YYYY_MM_BAR = "yyyy-MM";
	// 默认显示日期的格式 yyyy-MM-dd
	public static final String DATAFORMAT_STR_YYYY_MM_DD_BAR = "yyyy-MM-dd";
	// 默认显示日期时间的格式
	public static final String DATATIMEF_STR_YYYY_MM_DD_HH_MM_SS_BAR = "yyyy-MM-dd HH:mm:ss";

	//
	public static final String DATATIMEF_STR_YYYY_MM_DD_HH_MM_BAR = "yyyyMMddHHmm";
	// 默认显示简体中文日期的格式
	public static final String ZHCN_DATAFORMAT_STR_YYYY_MM = "yyyy年MM月";
	// 默认显示简体中文日期的格式
	public static final String ZHCN_DATAFORMAT_STR_YYYY_MM_DD = "yyyy年MM月dd日";
	// 默认显示简体中文日期时间的格式
	public static final String ZHCN_DATATIMEF_STR_YYYY_MM_DD_HHMMSS = "yyyy年MM月dd日 HH:mm:ss";
	// 默认显示简体中文日期时间的格式
	public static final String ZHCN_DATATIMEF_STR_YYYY_MM_DD_HH_MM_SS = "yyyy年MM月dd日HH时mm分ss秒";

	public static final String NUMERIC_DATA_FORMAT = "yyyyMMddHHmm";

	public static int getTodayint() {
		String dateString = dateToStr(new Date(), "MMdd");
		return Integer.parseInt(dateString);
	}

	@SuppressWarnings("deprecation")
	public static int getMonthDiff(Date startDate, Date endDate) {
		int result = 0;

		int yeardiff = endDate.getYear() - startDate.getYear();
		int nonthdiff = endDate.getMonth() - startDate.getMonth();
		result = yeardiff * 12 + nonthdiff;
		return result;
	}

	/**
	 * 获取本周一0点的日期
	 * 
	 * @return
	 */
	public static Date getThisWeekFirstDayDate() {
		Date result;
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, 1);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		result = c.getTime();
		return result;
	}

	/**
	 * 日期 -> 字符串（格式化日期）
	 * 
	 * @author bl00252
	 * @param date
	 * @return
	 */
	public static String dateToStr(Date date) {
		if (date == null)
			return "";
		DateFormat ymdhmsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return ymdhmsFormat.format(date);
	}

	/**
	 * 日期 -> 字符串（格式化日期）
	 * 
	 * @author bl00252
	 * @param date
	 * @return
	 */
	public static String dateToStr(Date date, String format) {
		if (date == null)
			return "";
		DateFormat ymdhmsFormat = new SimpleDateFormat(format);
		return ymdhmsFormat.format(date);
	}

	/**
	 * 日期 -> 字符串（格式化日期） for JXL JXL解析出来的日期时区是GMT，需要转化为本地时区，不然差8个小时
	 * 
	 * @author bl00252
	 * @param date
	 * @return
	 */
	public static String dateToStrForJxl(Date date, String format) {
		if (date == null)
			return "";
		try {
			date = convertDate4JXL(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		DateFormat ymdhmsFormat = new SimpleDateFormat(format);
		ymdhmsFormat.setTimeZone(TimeZone.getDefault());
		return ymdhmsFormat.format(date);
	}

	/**
	 * JXL中通过DateCell.getDate()获取单元格中的时间为（实际填写日期+8小时），原因是JXL是按照GMT时区来解析XML。
	 * 本方法用于获取单元格中实际填写的日期！ 例如单元格中日期为“2009-9-10”，getDate得到的日期便是“Thu Sep 10
	 * 08:00:00 CST 2009”；单元格中日期为“2009-9-10 16:00:00”，getDate得到的日期便是“Fri Sep 11
	 * 00:00:00 CST 2009”
	 * 
	 * @param jxlDate
	 *            通过DateCell.getDate()获取的时间
	 * @return
	 * @throws ParseException
	 */

	public static Date convertDate4JXL(Date jxlDate) throws ParseException {

		if (jxlDate == null)
			return null;

		TimeZone gmt = TimeZone.getTimeZone("GMT");

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

		dateFormat.setTimeZone(gmt);

		String str = dateFormat.format(jxlDate);

		TimeZone local = TimeZone.getDefault();

		dateFormat.setTimeZone(local);

		return dateFormat.parse(str);

	}

	/**
	 * 字符串 -> 日期
	 * 
	 * @author bl00252
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Date strToDate(String str) {
		try {
			DateFormat ymdhmsFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return ymdhmsFormat.parse(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 字符串转换为日期
	 * 
	 * @param str
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date strToDate(String str, String format) {
		try {
			DateFormat ymdhmsFormat = new SimpleDateFormat(format);
			return ymdhmsFormat.parse(str);
		} catch (Exception e) {
			return null;
		}
	}

	public static Date autoFormatDate(String dateString, String format) {
		Date result = null;
		try {
			if (isDateFormatOk(dateString, format)) {
				result = strToDate(dateString, format);
			} else {
				format = getFormateStr(dateString);
				result = strToDate(dateString, format);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 判断字符日期格式是否正确
	 * 
	 * @param str
	 * @param format
	 * @return
	 */
	public static boolean isDateFormatOk(String str, String format) {
		try {
			DateFormat ymdhmsFormat = new SimpleDateFormat(format);
			ymdhmsFormat.parse(str);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * 设置date的时间为当天的结束时间, 即23:59:59
	 * 
	 * @param date
	 * @return
	 */
	public static Date setTimeToTheEndOfTheDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}

	/**
	 * 设置date的时间为当天的开始时间, 即00:00:00
	 * 
	 * @param date
	 * @return
	 */
	public static Date setTimeToTheStartOfTheDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 设置date的时间为当月的开始时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date setTimeToTheStartOfTheMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, 1);

		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	/**
	 * 设置date的时间为当月的结束时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date setTimeToTheEndOfTheMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, c.getActualMaximum(Calendar.DATE));

		c.set(Calendar.HOUR, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}

	public static Date dateAddDays(Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, days);

		return c.getTime();
	}

	public static Date dateAddSeconds(Date date, int seconds) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.SECOND, seconds);

		return c.getTime();
	}

	public static Date dateAddMinutes(Date date, int minutes) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, minutes);

		return c.getTime();
	}

	public static Date dateAddHours(Date date, int hours) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR, hours);

		return c.getTime();
	}

	/**
	 * 根据一个日期字符串，返回日期格式，目前支持4种 如果都不是，则返回null
	 * 
	 * @param DateString
	 * @return
	 */
	public static String getFormateStr(String DateString) {
		// 默认显示日期的格式
		String pattern_yyyy_mm_slash = "\\d{4}\\/\\d{1,2}"; // yyyy/MM
		// 默认显示日期的格式
		String pattern_yyyy_mm_dd_slash = "\\d{4}\\/\\d{1,2}\\/\\d{1,2}"; // yyyy/MM/dd
		// 默认显示日期时间的格式
		String pattern_4y_mm_dd_hhmmss_slash = "\\d{4}\\/\\d{1,2}\\/\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}"; // yyyy/MM/dd
																											// HH:mm:ss;

		// 默认显示日期的格式
		String pattern_yyyy_mm_bar = "\\d{4}-\\d{1,2}"; // yyyy-MM
		// 默认显示日期的格式
		String pattern_yyyy_mm_dd_bar = "\\d{4}-\\d{1,2}-\\d{1,2}"; // yyyy-MM-dd
		// 默认显示日期时间的格式
		String pattern_4y_mm_dd_hhmmss_bar = "\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}"; // yyyy-MM-dd
																										// HH:mm:ss;

		// 默认显示简体中文日期的格式
		String zhcn_pattern_4y_mm = "\\d{4}年\\d{1,2}月";
		// 默认显示简体中文日期的格式
		String zhcn_pattern_4y_mm_dd = "\\d{4}年\\d{1,2}月\\d{1,2}日";// yyyy年MM月dd日
		// 默认显示简体中文日期的格式
		String zhcn_pattern_4y_mm_dd_hhmmss = "\\d{4}年\\d{1,2}月\\d{1,2}日\\s\\d{1,2}:\\d{1,2}:\\d{1,2}";// yyyy年MM月dd日
		// 默认显示简体中文日期时间的格式
		String zhcn_pattern_4y_mm_dd_hh_mm_ss = "\\d{4}年\\d{1,2}月\\d{1,2}日 \\d{1,2}时\\d{1,2}分\\d{1,2}秒";// yyyy年MM月dd日HH时mm分ss秒

		// yyyy/MM
		Pattern p = Pattern.compile(pattern_yyyy_mm_slash);
		Matcher m = p.matcher(DateString);
		boolean b = m.matches();
		if (b)
			return DATAFORMAT_STR_YYYY_MM_SLASH;
		// yyyy/MM/dd
		p = Pattern.compile(pattern_yyyy_mm_dd_slash);
		m = p.matcher(DateString);
		b = m.matches();
		if (b)
			return DATAFORMAT_STR_YYYY_MM_DD_SLASH;
		// yyyy/MM/dd HH:mm:ss
		p = Pattern.compile(pattern_4y_mm_dd_hhmmss_slash);
		m = p.matcher(DateString);
		b = m.matches();
		if (b)
			return DATATIMEF_STR_YYYY_MM_DD_HH_MM_SS_SLASH;

		// yyyy-MM
		p = Pattern.compile(pattern_yyyy_mm_bar);
		m = p.matcher(DateString);
		b = m.matches();
		if (b)
			return DATAFORMAT_STR_YYYY_MM_BAR;
		// yyyy-MM-dd
		p = Pattern.compile(pattern_yyyy_mm_dd_bar);
		m = p.matcher(DateString);
		b = m.matches();
		if (b)
			return DATAFORMAT_STR_YYYY_MM_DD_BAR;
		// yyyy-MM-dd HH:mm:ss
		p = Pattern.compile(pattern_4y_mm_dd_hhmmss_bar);
		m = p.matcher(DateString);
		b = m.matches();
		if (b)
			return DATATIMEF_STR_YYYY_MM_DD_HH_MM_SS_BAR;

		// yyyy年MM月
		p = Pattern.compile(zhcn_pattern_4y_mm);
		m = p.matcher(DateString);
		b = m.matches();
		if (b)
			return ZHCN_DATAFORMAT_STR_YYYY_MM;
		// yyyy年MM月dd日
		p = Pattern.compile(zhcn_pattern_4y_mm_dd);
		m = p.matcher(DateString);
		b = m.matches();
		if (b)
			return ZHCN_DATAFORMAT_STR_YYYY_MM;
		// yyyy年MM月dd日 HH:mm:ss
		p = Pattern.compile(zhcn_pattern_4y_mm_dd_hhmmss);
		m = p.matcher(DateString);
		b = m.matches();
		if (b)
			return ZHCN_DATATIMEF_STR_YYYY_MM_DD_HHMMSS;
		// yyyy年MM月dd日HH时mm分ss秒
		p = Pattern.compile(zhcn_pattern_4y_mm_dd_hh_mm_ss);
		m = p.matcher(DateString);
		b = m.matches();
		if (b)
			return ZHCN_DATATIMEF_STR_YYYY_MM_DD_HH_MM_SS;

		return DATAFORMAT_STR_YYYY_MM_DD_SLASH;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Date now = DateUtil.setTimeToTheEndOfTheDay(new Date());
		Date bgTd = DateUtil.setTimeToTheStartOfTheDay(now);
		Date seven = DateUtil.dateAddDays(now, -6);
		Date month = DateUtil.dateAddDays(now, -29);

		String fname = DateUtil.dateToStr(bgTd, DateUtil.DATAFORMAT_STR_YYYY_MM_DD_BAR) + ".xlsx";

		System.out.println(fname);

		// List<String> dateFormates = new ArrayList<String>();
		//
		// String date10 = "2011/03";
		// String date11 = "2011/03/21";
		// String date12 = "2011/03/21 12:14:30";
		// dateFormates.add(date10);
		// dateFormates.add(date11);
		// dateFormates.add(date12);
		//
		//
		// String date20 = "2011-03";
		// String date21 = "2011-03-21";
		// String date22 = "2011-03-21 12:14:30";
		// dateFormates.add(date20);
		// dateFormates.add(date21);
		// dateFormates.add(date22);
		//
		// String date30 = "2011年03月";
		// String date31 = "2011年03月21日";
		// String date32 = "2011年03月21日 12:14:30";
		// String date33 = "2011年03月21日 12时14分30秒";
		// dateFormates.add(date30);
		// dateFormates.add(date31);
		// dateFormates.add(date32);
		// dateFormates.add(date33);

		// String format="";
		// Date dt;
		// for(String item : dateFormates ){
		// format = getFormateStr(item);
		// System.out.println("Date ["+ item+"] dateFormate:" + format);
		// if( isDateFormatOk(item , format ) ){
		// try{
		// dt = strToDate( item, format);
		// System.out.println(dateToStr(dt));
		// }catch( Exception e){
		// System.out.println(e.getMessage());
		// }
		//
		// }
		// System.out.println("*******************************************");
		// }

		// //2011-12-25
		// Calendar calendar1 = new GregorianCalendar(2011, Calendar.DECEMBER,
		// 25,0,0,0);
		// Date startDate1 = calendar1.getTime();
		// //2012-01-01
		// Calendar calendar2 = new GregorianCalendar(2012, Calendar.JANUARY,
		// 1,0,0,0);
		// Date endDate1 = calendar2.getTime();
		//
		// int diff = getMonthDiff(startDate1,endDate1);
		// System.out.println("month between " + dateToStr(startDate1) + " and "
		// + dateToStr(endDate1) + " is :" + diff);
		//
		// //2011-11-25
		// calendar1 = new GregorianCalendar(2011, Calendar.NOVEMBER, 25,0,0,0);
		// startDate1 = calendar1.getTime();
		// //2011-12-01
		// calendar2 = new GregorianCalendar(2011, Calendar.DECEMBER, 1,0,0,0);
		// endDate1 = calendar2.getTime();
		//
		// diff = getMonthDiff(startDate1,endDate1);
		// System.out.println("month between " + dateToStr(startDate1) + " and "
		// + dateToStr(endDate1) + " is :" + diff);
		//
		// //2012-01-25
		// calendar1 = new GregorianCalendar(2012, Calendar.JANUARY, 25,0,0,0);
		// startDate1 = calendar1.getTime();
		// //2011-12-01
		// calendar2 = new GregorianCalendar(2011, Calendar.DECEMBER, 1,0,0,0);
		// endDate1 = calendar2.getTime();
		//
		// diff = getMonthDiff(startDate1,endDate1);
		// System.out.println("month between " + dateToStr(startDate1) + " and "
		// + dateToStr(endDate1) + " is :" + diff);
	}

	public static Date trimMileSec(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Date addMinutes(Date date, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, amount);
		return c.getTime();
	}

	public static Date addDay(Date date, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, amount);
		return c.getTime();
	}

	public static Date addMonth(Date date, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, amount);
		return c.getTime();
	}

	public static Date addSeconds(Date date, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.SECOND, amount);
		return c.getTime();
	}

	// if date 1 < date 2 then return true ,else return false
	// public static boolean compareDate(Date reportDate, Date todayBeginDate) {
	// reportDate.getTime()
	// return false;
	// }
	public static boolean isToday(Date lastLoginTime) {

		return setTimeToTheStartOfTheDay(new Date()).equals(lastLoginTime);
	}

	public static Long dateToLongWithFormat(Date date, String format) {
		String dateString = dateToStr(date, format);
		return Long.parseLong(dateString);
	}
}
