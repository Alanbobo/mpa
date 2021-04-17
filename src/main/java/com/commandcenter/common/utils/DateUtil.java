package com.commandcenter.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

	private static String defaultDatePattern = "yyyy-MM-dd";
	private static String expandDatePattern = "yyyy/MM/dd";
	private static String fullDateTimePattern = "yyyy-MM-dd HH:mm:ss";
	private static String hourTimePattern = "yyyy-MM-dd HH";
	private static String monthTimePattern = "yyyy-MM";
	private static String yearTimePattern = "yyyy";
	private static String chineseDatePattern = "yyyy年MM月";
	private static String chineseYearDatePattern = "yyyy年";

	private static Long DAY = 24 * 3600 * 1000L;

	/**
	 * 获得默认的 date pattern
	 */
	public static String getDatePattern() {
		return defaultDatePattern;
	}

	/**
	 * 获得默认的 date pattern
	 */
	public static String getExpandDatePattern() {
		return expandDatePattern;
	}

	public static String getFullDateTimePattern() {
		return fullDateTimePattern;
	}

	public static String getMonthTimePattern() {
		return monthTimePattern;
	}

	public static String getYearTimePattern() {
		return yearTimePattern;
	}

	public static String getHourTimePattern() {
		return hourTimePattern;
	}

	public static String getChineseDatePattern() {
		return chineseDatePattern;
	}

	public static String getChineseYearDatePattern() {
		return chineseYearDatePattern;
	}

	/**
	 * 返回预设Format的当前日期字符串
	 */
	public static String getToday() {
		Date today = new Date();
		return format(today);
	}

	public static String getToday(String pattern) {
		Date today = new Date();
		return format(today, pattern);
	}

	/**
	 * 返回预设Format的当前日期字符串
	 */
	public static String getTodayExpand() {
		Date today = new Date();
		return format(today, getExpandDatePattern());
	}

	/**
	 * @description 获取当前年月 如201509
	 */
	public static String getCurrentMonth() {
		Date today = new Date();
		return format(today, monthTimePattern);
	}

	/**
	 * @description 获取当前年 如2015
	 */
	public static String getCurrentYear() {
		Date today = new Date();
		return format(today, yearTimePattern);
	}

	/**
	 * @description 获取前一天的年月日 如2015年9月29日获取的为 20150928
	 */
	public static String getBeforeDay() {
		Date today = new Date();
		return format(addDay(today, -1), "yyyyMMdd");
	}

	public static String getBeforeMonthDay() {
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();// 当前日期
		Calendar calendar = Calendar.getInstance();// 日历对象
		calendar.setTime(date);// 设置当前日期
		calendar.add(Calendar.MONTH, -1);// 月份减一
		return fm.format(calendar.getTime());
	}

	public static String getBeforeMonthDay(String datePattern) {
		SimpleDateFormat fm = new SimpleDateFormat(datePattern);
		Date date = new Date();// 当前日期
		Calendar calendar = Calendar.getInstance();// 日历对象
		calendar.setTime(date);// 设置当前日期
		calendar.add(Calendar.MONTH, -1);// 月份减一
		return fm.format(calendar.getTime());
	}


	public static String getCustomDay(String date,String timeScope) throws ParseException {
		int num = Integer.parseInt(timeScope);
		SimpleDateFormat fm = new SimpleDateFormat(fullDateTimePattern);
		Calendar calendar = Calendar.getInstance();// 日历对象
		Date date1 = fm.parse(date);
		calendar.setTime(date1);// 设置当前日期
		calendar.add(Calendar.DATE, -num);// 减去时间段
		return fm.format(calendar.getTime());
	}

	public static String getCustomYearDay(String date) throws ParseException {
		SimpleDateFormat fm = new SimpleDateFormat(fullDateTimePattern);
		Calendar calendar = Calendar.getInstance();// 日历对象
		Date date1 = fm.parse(date);
		calendar.setTime(date1);// 设置当前日期
		calendar.add(Calendar.YEAR, -1);// 减去时间段
		return fm.format(calendar.getTime());
	}

	public static String getBeforeMonthDay(String todayStr, String datePattern) {
		SimpleDateFormat fm = new SimpleDateFormat(datePattern);

		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat(datePattern);
		try {
			date = format.parse(todayStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Calendar calendar = Calendar.getInstance();// 日历对象
		calendar.setTime(date);// 设置当前日期
		calendar.add(Calendar.MONTH, -1);// 月份减一
		return fm.format(calendar.getTime());
	}

	public static String getBeforeWeekDay(String datePattern) {
		Date today = new Date();
		return format(addDay(today, -7), "yyyy-MM-dd");
	}

	public static String getBeforeSixDay(String todayStr, String datePattern) {
		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat(datePattern);
		try {
			date = format.parse(todayStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return format(addDay(date, -6), datePattern);
	}

	/**
	 * 使用预设Format格式化Date成字符串
	 */
	public static String format(Date date) {
		return format(date, getDatePattern());
	}

	/**
	 * 使用参数Format格式化Date成字符串
	 */
	public static String format(Date date, String pattern) {
		String returnValue = "";

		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			returnValue = df.format(date);
		}

		return (returnValue);
	}

	/**
	 * 使用预设格式将字符串转为Date
	 */
	public static Date parse(String strDate) {
		return parse(strDate, monthTimePattern);
	}

	/**
	 * 使用参数Format将字符串转为Date
	 */
	public static Date parse(String strDate, String pattern) {
		Date d = null;
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			d = df.parse(strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 在日期上增加数个整月
	 */
	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}

	/**
	 * 在日期上增加数个整日(n为负数则是减少数日)
	 */
	public static Date addDay(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, n);
		return cal.getTime();
	}

	/**
	 * 在日期上增加数个小时(n为负数则是减少数小时)
	 */
	public static Date addHour(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, n);
		return cal.getTime();
	}



	/**
	 * 字符串转化为日期,通用性相对较强
	 *
	 * @param dateString
	 * 具有日期格式的字符串
	 * @param DataFormat
	 * 日期格式
	 * @return Date
	 */
	public static Date stringToDate(String dateString, String DataFormat) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DataFormat);
			date = sdf.parse(dateString);
		} catch (ParseException ex) {
			return null;
		}
		return date;
	}

	/**
	 * 求出两个时间段的时间差（精确到天/小时/分）
	 */
	public static String timeLeft(Date timeNow, Date timeLimit) {
		if (timeNow == null || timeLimit == null) {
			return "0";
		}
		long now = timeNow.getTime();
		long limit = timeLimit.getTime();
		int day = (int) (Math.abs(now - limit) / (3600000 * 24));
		int hour = (int) (Math.abs(now - limit) % (3600000 * 24)) / 3600000;
		int minute = (int) ((Math.abs(now - limit) % (3600000 * 24)) % 3600000) / 60000;
		String timeLeft = "0";
		StringBuffer sb = new StringBuffer();
		if (now < limit) {
			sb.append("剩余").append(day).append("天").append(hour).append("小时").append(minute).append("分");
		}
		if (now > limit) {
			sb.append("超过").append(day).append("天").append(hour).append("小时").append(minute).append("分");
		}
		timeLeft = sb.toString();
		return timeLeft;
	}

	/**
	 * 时间一是否超过时间二
	 */
	public static String isExceed(Date timeNow, Date timeLimit) {
		if (timeNow == null || timeLimit == null) {
			return "false";
		}
		long now = timeNow.getTime();
		long limit = timeLimit.getTime();
		if (now > limit) {
			return "true";
		}
		return "false";
	}

	/**
	 * 求出两个时间段的时间差(精确到小时)
	 */
	public static int timeInterval(Date timeNow, Date timeLimit) {
		if (timeNow == null || timeLimit == null) {
			return 0;
		}
		long now = timeNow.getTime();
		long limit = timeLimit.getTime();
		int interval = (int) ((now - limit) / 3600000);
		return interval;
	}

	/**
	 * 返回java.sql.Date类型的字段值。
	 *
	 * @return 只有日期
	 */
	public static java.sql.Date getSqlDate() {
		return new java.sql.Date(System.currentTimeMillis());
	}

	/**
	 * 返回java.sql.Timestamp类型的字段值。
	 *
	 * @return 有日期和时间（毫秒级）
	 */
	public static java.sql.Timestamp getSqlTimestamp() {
		return new java.sql.Timestamp(System.currentTimeMillis());
	}

	public static Date parseFullDate(String dateStr, String pattern) {
		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获得前一天
	 */
	public static String getYesterday(String todayStr, String pattern) {
		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			date = format.parse(todayStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		long today = date.getTime();
		long yesterDay = today - DAY;
		Date t = new Date(yesterDay);
		return format.format(t);
	}

	public static List<Map<String, Object>> getPreMonth() {
		List<Map<String, Object>> opTimes = new ArrayList<Map<String, Object>>();

		String[] months = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy,MM");
		String[] array = format.format(now).split(",");
		String year = array[0];
		String month = array[1];

		int resultYear = Integer.valueOf(year);

		int index = Integer.valueOf(month.replaceAll("0", "")) - 1;
		for (int i = index, j = 0; j < 12; j++, i--) {
			if (i < 0) {
				i = i + 12;
				resultYear -= 1;
			}
			String m = months[i];
			String nowTime = resultYear + "-" + m;
			Map<String, Object> o = new HashMap<String, Object>();
			o.put("opTime", nowTime);
			opTimes.add(o);
		}
		return opTimes;
	}

	/**
	 * 根据指定日期计算所在周的周一
	 *
	 * @param time
	 * @return
	 */
	public static String getMondayForDate(Date time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar cal = Calendar.getInstance();
		// 此处传入所要计算的日期
		cal.setTime(time);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		// 得到星期一imptimeBegin
		String imptimeBegin = sdf.format(cal.getTime());
		cal.add(Calendar.DATE, 6);
		return imptimeBegin;
	}

	/**
	 * 根据指定日期计算所在周的周日
	 *
	 * @param time
	 * @return
	 */
	public static String getSundayForDate(Date time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar cal = Calendar.getInstance();
		// 此处传入所要计算的日期
		cal.setTime(time);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		cal.add(Calendar.DATE, 6);
		// 得到星期日imptimeEnd
		String imptimeEnd = sdf.format(cal.getTime());
		return imptimeEnd;
	}

	/**
	 * 根据传入日期，返回Date类型
	 *
	 * @return
	 */
	public static Date getDate(Date sfDate) {
		Date date = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String fDate = simpleDateFormat.format(sfDate);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = format.parse(fDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取当前日期时间，返回Date类型
	 *
	 * @return
	 */
	public static Date getNowDate() {
		Date date = null;
		Calendar c = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fDate = simpleDateFormat.format(c.getTime());
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = format.parse(fDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 韩虎成 2011-06-21 将 String 类型日期转换成 Date 类型
	 *
	 * @return Date
	 */
	public static Date getStringChangeDate(String day) {
		Date date = null;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = format.parse(day);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 韩虎成 2011-06-21 将 String 类型日期转换成 Date 类型
	 *
	 * @return Date
	 */
	public static Date getStringChangeDateTime(String day) {
		Date date = null;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = format.parse(day);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获得上一周一周的日期
	 *
	 * @return
	 */
	public static List getBeforeWeekDate() {
		List list = new ArrayList();
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_WEEK, (-6 - cal.get(Calendar.DAY_OF_WEEK)) % 7);
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < 7; i++) {
			list.add(d.format(cal.getTime()));
			cal.roll(Calendar.DAY_OF_YEAR, false);
		}
		return list;
	}

	/**
	 * 得到本周一周的日期
	 *
	 * @author hhc
	 *
	 */
	public static List getWeekDate() {
		List list = new ArrayList();
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_WEEK, (2 - cal.get(Calendar.DAY_OF_WEEK)) % 7);
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < 7; i++) {
			list.add(d.format(cal.getTime()));
			cal.roll(Calendar.DAY_OF_YEAR, true);
		}
		return list;
	}

	/**
	 * 获得下一周的日期
	 *
	 * @return
	 */
	public static List getNextWeekDate() {

		List list = new ArrayList();
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_WEEK, (9 - cal.get(Calendar.DAY_OF_WEEK)) % 7);
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");

		for (int i = 0; i < 7; i++) {
			list.add(d.format(cal.getTime()));
			cal.roll(Calendar.DAY_OF_YEAR, true);
		}
		return list;
	}

	/**
	 * 得到本周周一
	 *
	 * @return yyyy-MM-dd
	 */
	public static String getMondayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0) {
			dayofweek = 7;
		}
		c.add(Calendar.DATE, -dayofweek + 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(c.getTime());
	}

	/**
	 * 得到本周周二
	 *
	 * @return yyyy-MM-dd
	 */
	public static String getTuesdayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0) {
			dayofweek = 7;
		}
		c.add(Calendar.DATE, -dayofweek + 2);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(c.getTime());
	}

	/**
	 * 得到本周周三
	 *
	 * @return yyyy-MM-dd
	 */
	public static String getWednesdayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0) {
			dayofweek = 7;
		}
		c.add(Calendar.DATE, -dayofweek + 3);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(c.getTime());
	}

	/**
	 * 得到本周周四
	 *
	 * @return yyyy-MM-dd
	 */
	public static String getThursdayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0) {
			dayofweek = 7;
		}
		c.add(Calendar.DATE, -dayofweek + 4);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(c.getTime());
	}

	/**
	 * 得到本周周五
	 *
	 * @return yyyy-MM-dd
	 */
	public static String getFridayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0) {
			dayofweek = 7;
		}
		c.add(Calendar.DATE, -dayofweek + 5);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(c.getTime());
	}

	/**
	 * 得到本周周六
	 *
	 * @return yyyy-MM-dd
	 */
	public static String getSaturdayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0) {
			dayofweek = 7;
		}
		c.add(Calendar.DATE, -dayofweek + 6);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(c.getTime());
	}

	/**
	 * 得到本周周日
	 *
	 * @return yyyy-MM-dd
	 */
	public static String getSundayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayofweek == 0) {
			dayofweek = 7;
		}
		c.add(Calendar.DATE, -dayofweek + 7);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(c.getTime());
	}

	/**
	 * 得到当前日期的月首 格式为：2009-08-01
	 */
	public static String monthFist() {
		Calendar localTime = Calendar.getInstance();
		String strY = null;// 日期属性：日
		int x = localTime.get(Calendar.YEAR); // 日期属性：年
		int y = localTime.get(Calendar.MONTH) + 1; // 日期属性：月
		strY = y >= 10 ? String.valueOf(y) : ("0" + y); // 组合月份
		return x + "-" + strY + "-01"; // 最后组合成yyyy-mm-dd形式字符串
	}

	/**
	 * 得到上个月月首 格式为：2009-08-01
	 */
	public static String beforeMonthFirst() {
		Calendar localTime = Calendar.getInstance();
		localTime.add(Calendar.MONTH, -1); // 通过提取这个月计算上个月号
		String strz = null;
		int x = localTime.get(Calendar.YEAR); // 得到年
		int y = localTime.get(Calendar.MONTH) + 1; // 得到月
		strz = y >= 10 ? String.valueOf(y) : ("0" + y);
		return x + "-" + strz + "-01";
	}
	/**
	 * 得到上个月月首 格式为：2009-08-01
	 */
	public static String beforeMonth() {
		Calendar localTime = Calendar.getInstance();
		localTime.add(Calendar.MONTH, -1); // 通过提取这个月计算上个月号
		String strz = null;
		String strhehe = null;
		int x = localTime.get(Calendar.YEAR); // 得到年
		int y = localTime.get(Calendar.MONTH) + 1; // 得到月
		int z = localTime.get(Calendar.DAY_OF_MONTH); // 得到月
		strz = y >= 10 ? String.valueOf(y) : ("0" + y);
		strhehe = z >= 10 ? String.valueOf(z) : ("0" + z);
		return x + "-" + strz + "-"+strhehe;
	}
	/**
	 * 得到上个月月首 格式为：2009-08-01
	 */
	public static String beforeYear() {
		Calendar localTime = Calendar.getInstance();
		//localTime.add(Calendar.MONTH, -1); // 通过提取这个月计算上个月号
		String strz = null;
		String strhehe = null;
		int x = localTime.get(Calendar.YEAR)-1; // 得到年
		int y = localTime.get(Calendar.MONTH) + 1; // 得到月
		int z = localTime.get(Calendar.DAY_OF_MONTH); // 得到月
		strz = y >= 10 ? String.valueOf(y) : ("0" + y);
		strhehe = z >= 10 ? String.valueOf(z) : ("0" + z);
		return x + "-" + strz + "-"+strhehe;
	}
	/**
	 * 得到上个月月首 格式为：2009-08-01
	 */
	public static String beforeYearyyyy() {
		Calendar localTime = Calendar.getInstance();
		//localTime.add(Calendar.MONTH, -1); // 通过提取这个月计算上个月号
		String strz = null;
		String strhehe = null;
		int x = localTime.get(Calendar.YEAR)-1; // 得到年

		return String.valueOf(x);
	}
	/**
	 * 得到当前日期 格式为：2009-08-01
	 */
	public static String curDate() {

		// 分别根据日历时间提取当前年月日组合成字符串
		Calendar localTime = Calendar.getInstance();

		int x = localTime.get(Calendar.YEAR);
		int y = localTime.get(Calendar.MONTH) + 1;
		int z = localTime.get(Calendar.DAY_OF_MONTH);
		return x + "-" + y + "-" + z;
	}

	/**
	 * 得到当前日期 格式为：2009-08-01
	 */
	public static String curDateTime() {

		// 分别根据日历时间提取当前年月日组合成字符串
		Calendar localTime = Calendar.getInstance();

		int x = localTime.get(Calendar.YEAR);
		int y = localTime.get(Calendar.MONTH) + 1;
		int z = localTime.get(Calendar.DAY_OF_MONTH);
		int h = localTime.get(Calendar.HOUR_OF_DAY);
		int m = localTime.get(Calendar.MINUTE);
		int s = localTime.get(Calendar.SECOND);
		return x + "-" + y + "-" + z + " " + h + ":" + m + ":" + s;
	}

	/**
	 * 给定的日期加一个月 格式为：2009-08-01
	 */
	public static String addMonth(String strdate) {

		Date date = new Date(); // 构造一个日期型中间变量

		String dateresult = null; // 返回的日期字符串
		// 创建格式化格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		// 加减日期所用
		GregorianCalendar gc = new GregorianCalendar();

		try {
			date = df.parse(strdate); // 将字符串格式化为日期型
		} catch (ParseException e) {
			e.printStackTrace();
		}

		gc.setTime(date); // 得到gc格式的时间

		gc.add(2, 1); // 2表示月的加减，年代表1依次类推(周,天。。)
		// 把运算完的时间从新赋进对象
		gc.set(gc.get(GregorianCalendar.YEAR), gc.get(GregorianCalendar.MONTH), gc.get(GregorianCalendar.DATE));
		// 在格式化回字符串时间
		dateresult = df.format(gc.getTime());

		return dateresult;
	}

	/**
	 * 判端date1是否在date2之前；当date1的时间早于date2是返回true date1，date2的格式为：2009-08-01
	 */
	public static boolean isDate10Before(String date1, String date2) {
		try {
			DateFormat df = DateFormat.getDateInstance();
			return df.parse(date1).before(df.parse(date2));
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 给定的日期减一个月 格式为：2009-08-01
	 */
	public static String subMonth(String strdate) {

		Date date = new Date(); // 构造一个日期型中间变量

		String dateresult = null; // 返回的日期字符串
		// 创建格式化格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		// 加减日期所用
		GregorianCalendar gc = new GregorianCalendar();

		try {
			date = df.parse(strdate); // 将字符串格式化为日期型
		} catch (ParseException e) {
			e.printStackTrace();
		}

		gc.setTime(date); // 得到gc格式的时间

		gc.add(2, -1); // 2表示月的加减，年代表1依次类推(周,天。。)
		// 把运算完的时间从新赋进对象
		gc.set(gc.get(GregorianCalendar.YEAR), gc.get(GregorianCalendar.MONTH), gc.get(GregorianCalendar.DATE));
		// 在格式化回字符串时间
		dateresult = df.format(gc.getTime());

		return dateresult;
	}

	/**
	 * 给定的日期减一天 格式为：2009-08-01
	 */
	public static String subDay(String strdate) {

		Date date = new Date(); // 构造一个日期型中间变量

		String dateresult = null; // 返回的日期字符串
		// 创建格式化格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		// 加减日期所用
		GregorianCalendar gc = new GregorianCalendar();

		try {
			date = df.parse(strdate); // 将字符串格式化为日期型
		} catch (ParseException e) {
			e.printStackTrace();
		}

		gc.setTime(date); // 得到gc格式的时间

		gc.add(5, -1); // 2表示月的加减，年代表1依次类推(３周....5天。。)
		// 把运算完的时间从新赋进对象
		gc.set(gc.get(GregorianCalendar.YEAR), gc.get(GregorianCalendar.MONTH), gc.get(GregorianCalendar.DATE));
		// 在格式化回字符串时间
		dateresult = df.format(gc.getTime());

		return dateresult;
	}

	/**
	 * 给定的日期加一天 格式为：2009-08-01
	 */
	public static String addDay(String strdate) {

		Date date = new Date(); // 构造一个日期型中间变量

		String dateresult = null; // 返回的日期字符串
		// 创建格式化格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		// 加减日期所用
		GregorianCalendar gc = new GregorianCalendar();

		try {
			date = df.parse(strdate); // 将字符串格式化为日期型
		} catch (ParseException e) {
			e.printStackTrace();
		}

		gc.setTime(date); // 得到gc格式的时间

		gc.add(5, 1); // 2表示月的加减，年代表1依次类推(３周....5天。。)
		// 把运算完的时间从新赋进对象
		gc.set(gc.get(GregorianCalendar.YEAR), gc.get(GregorianCalendar.MONTH), gc.get(GregorianCalendar.DATE));
		// 在格式化回字符串时间
		dateresult = df.format(gc.getTime());

		return dateresult;
	}

	/**
	 * 给定的日期得到年月 格式为：2009-08-01
	 */
	public static String giveyrmo(String yrmoday) {
		// 以“－"为分隔符拆分字符串
		String[] strArray = yrmoday.split("-");

		String tempyear = strArray[0]; // 得到字符串中的年

		String tempmonth = strArray[1]; // 得到字符串中的月

		// 拼接成月首字符串
		return tempyear + "-" + tempmonth; // 最后组合成yyyy-mm形式字符串

	}

	/**
	 * 两个日期做减法，返回相差天数
	 *
	 * @throws ParseException
	 * @throws ParseException
	 */
	public static long datesub(Date date1, Date date2) throws ParseException {

		@SuppressWarnings("unused")
		long l = date1.getTime() - date2.getTime() > 0 ? date1.getTime() - date2.getTime()
				: date2.getTime() - date1.getTime();

		// 日期相减得到相差的日期
		long day = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000) > 0
				? (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000)
				: (date2.getTime() - date1.getTime()) / (24 * 60 * 60 * 1000);

		return day + 1;
	}

	/**
	 * 根据给定的年月构造日期月首字符串
	 */
	public static String giveMonthFist(Integer yr, Integer mo) {

		// 拼接成月首字符串
		if (mo >= 10) {
			return yr + "-" + mo + "-01";
		} else {
			return yr + "-" + "0" + mo + "-01";
		}

	}

	/**
	 * 根据给定的年月构造年月字符串
	 */
	public static String giveYrMo(Integer yr, Integer mo) {

		// 拼接成月首字符串
		if (mo >= 10) {
			return yr + "-" + mo;
		} else {
			return yr + "-" + "0" + mo;
		}

	}

	/**
	 * 给定年月字串返回一个整型月份 格式为：2009-08-01
	 */
	public static Integer retrunmo(String yrmoday) {
		// 以“－"为分隔符拆分字符串
		String[] strArray = yrmoday.split("-");

		String tempmonth = strArray[1]; // 得到字符串中的月

		return new Integer(tempmonth);
	}

	/**
	 * 给定年月字串返回一个整型年份 格式为：2009-08-01
	 */
	public static Integer retrunyr(String yrmoday) {
		// 以“－"为分隔符拆分字符串
		String[] strArray = yrmoday.split("-");

		String tempmonth = strArray[0]; // 得到字符串中的月

		return new Integer(tempmonth);
	}

	/**
	 * 给定的两个日期作比较，返回bool的类型 格式为：2009-08-01
	 *
	 * @throws ParseException
	 */
	public static boolean boolcompara(String startdate, String enddate) throws ParseException {

		if (DateFormat.getDateInstance().parse(startdate)
				.compareTo(DateFormat.getDateInstance().parse(startdate)) >= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 *
	 * @ 时间格式 2008-08-08 16:16:34
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isDateBefore(String date1, String date2) {
		try {
			DateFormat df = DateFormat.getDateTimeInstance();
			return df.parse(date1).before(df.parse(date2));
		} catch (ParseException e) {
			System.out.print("[SYS] " + e.getMessage());
			return false;
		}
	}

	// 判断当前时间是否在时间date2之前
	// 时间格式 2005-4-21 16:16:34
	public static boolean isDateBefore(String date2) {
		try {
			Date date1 = new Date();
			DateFormat df = DateFormat.getDateTimeInstance();
			return date1.before(df.parse(date2));
		} catch (ParseException e) {
			System.out.print("[SYS] " + e.getMessage());
			return false;
		}
	}

	/**
	 *
	 * 当前时间增加间隔小时后的时间 时间格式 2008-08-08 16:16:34
	 */
	public static String addHours(String startDate, int intHour) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = df.parse(startDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			long longMills = cal.getTimeInMillis() + intHour * 60 * 60 * 1000;
			cal.setTimeInMillis(longMills);

			// 返回日期
			return df.format(cal.getTime());
		} catch (Exception Exp) {
			return null;
		}
	}

	/**
	 *
	 * 当前时间减去间隔小时后的时间 时间格式 2008-08-08 16:16:34
	 */
	public static String delHours(String startDate, int intHour) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = df.parse(startDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			long longMills = cal.getTimeInMillis() - intHour * 60 * 60 * 1000;
			cal.setTimeInMillis(longMills);

			// 返回日期
			return df.format(cal.getTime());
		} catch (Exception Exp) {
			return null;
		}
	}

	/**
	 * 得到当前日期 日期格式 2008-08-08
	 */
	public static String getCurrentDate() {
		try {
			long longCalendar = 0;
			// 获得当前日期
			Calendar cldCurrent = Calendar.getInstance();
			// 获得年月日
			String strYear = String.valueOf(cldCurrent.get(Calendar.YEAR));
			String strMonth = String.valueOf(cldCurrent.get(Calendar.MONTH) + 1);
			String strDate = String.valueOf(cldCurrent.get(Calendar.DATE));
			// 整理格式
			if (strMonth.length() < 2) {
				strMonth = "0" + strMonth;
			}
			if (strDate.length() < 2) {
				strDate = "0" + strDate;
			}
			// 组合结果
			longCalendar = Long.parseLong(strYear + strMonth + strDate);
			// 系统默认月份加一
			longCalendar += 100L;
			// 创建上初始化上下文环境并返回
			return String.valueOf(strYear + "-" + strMonth + "-" + strDate);
		} catch (Exception Exp) {
			return "2008-08-08";
		}
	}


	/**
	 * 获得系统中使用长整形表示的日期 返回参数：long:表示的日期的8位长整形值 如：20090723
	 */
	public static long getLongCalendar() {
		try {
			long longCalendar = 0;

			// 获得当前日期
			Calendar cldCurrent = Calendar.getInstance();

			// 获得年月日
			String strYear = String.valueOf(cldCurrent.get(Calendar.YEAR));
			String strMonth = String.valueOf(cldCurrent.get(Calendar.MONTH));
			String strDate = String.valueOf(cldCurrent.get(Calendar.DATE));

			// 整理格式
			if (strMonth.length() < 2) {
				strMonth = "0" + strMonth;
			}
			if (strDate.length() < 2) {
				strDate = "0" + strDate;
			}

			// 组合结果
			longCalendar = Long.parseLong(strYear + strMonth + strDate);

			// 系统默认月份加一
			longCalendar += 100L;

			// 创建上初始化上下文环境并返回
			return longCalendar;
		} catch (Exception Exp) {
			return 0;
		}
	}

	/**
	 * 返回字符串行日期
	 *
	 * @param canlendar
	 * 20090801或20090802080808
	 * @return 2009/08/01或2009/08/01 08:08:08
	 */
	public static String toString(long canlendar) {
		try {
			StringBuffer sbCalendar = new StringBuffer();

			sbCalendar.insert(0, canlendar);

			// 整理格式
			if (sbCalendar.length() == 8) {
				sbCalendar.insert(6, "/");
				sbCalendar.insert(4, "/");
			} else if (sbCalendar.length() == 14) {
				sbCalendar.insert(12, ":");
				sbCalendar.insert(10, ":");
				sbCalendar.insert(8, " ");
				sbCalendar.insert(6, "/");
				sbCalendar.insert(4, "/");
			} else {
				// 错误处理
				return null;
			}
			// 返回格式化好的整形日期的字符串格式
			return sbCalendar.toString();
		} catch (Exception Exp) {
			// 错误处理
			return null;
		}
	}

	/**
	 * 长整形时间类组合Calender，封装Calender的数字接口和方法 返回 20090802080808 包含日期和时间
	 *
	 */
	public static long getLongTime() {
		try {
			long longCalendar = 0;

			// 获得当前日期
			Calendar cldCurrent = Calendar.getInstance();

			// 获得年月日
			String strYear = String.valueOf(cldCurrent.get(Calendar.YEAR));
			String strMonth = String.valueOf(cldCurrent.get(Calendar.MONTH) + 1);
			String strDate = String.valueOf(cldCurrent.get(Calendar.DATE));
			String strHour = String.valueOf(cldCurrent.get(Calendar.HOUR));
			String strAM_PM = String.valueOf(cldCurrent.get(Calendar.AM_PM));
			String strMinute = String.valueOf(cldCurrent.get(Calendar.MINUTE));
			String strSecond = String.valueOf(cldCurrent.get(Calendar.SECOND));

			// 把时间转换为24小时制
			// strAM_PM=="1",表示当前时间是下午，所以strHour需要加12
			if ("1".equals(strAM_PM)) {
				strHour = String.valueOf(Long.parseLong(strHour) + 12);
			}

			// 整理格式
			if (strMonth.length() < 2) {
				strMonth = "0" + strMonth;
			}
			if (strDate.length() < 2) {
				strDate = "0" + strDate;
			}
			if (strHour.length() < 2) {
				strHour = "0" + strHour;
			}
			if (strMinute.length() < 2) {
				strMinute = "0" + strMinute;
			}
			if (strSecond.length() < 2) {
				strSecond = "0" + strSecond;
			}
			// 组合结果
			longCalendar = Long.parseLong(strYear + strMonth + strDate + strHour + strMinute + strSecond);

			// 创建上初始化上下文环境并返回
			return longCalendar;
		} catch (Exception Exp) {
			return 0;
		}
	}

	/**
	 * 由长整型时间变为字符 通过长整数变为时间格式,可以自动适应8位或16位 输入："20090808"或"20090808080808"
	 * 返回："2009年08月08日"或 "2009年08月08日 08:08:08"
	 */
	public static String getDateStringByLongDatetime(long longCalendar) {
		try {
			String StrCalendar = String.valueOf(longCalendar);
			String StrCalendarResult = "";
			// 判断为日期型
			if (StrCalendar.length() == 8) {
				StrCalendarResult = StrCalendar.substring(0, 4) + "年" + StrCalendar.substring(4, 6) + "月"
						+ StrCalendar.substring(6, 8) + "日";
				return StrCalendarResult;
			}
			// 判断为日期及时间型
			if (StrCalendar.length() == 14) {
				StrCalendarResult = StrCalendar.substring(0, 4) + "年" + StrCalendar.substring(4, 6) + "月"
						+ StrCalendar.substring(6, 8) + "日";
				StrCalendarResult = StrCalendarResult + " " + StrCalendar.substring(8, 10) + ":"
						+ StrCalendar.substring(10, 12) + ":" + StrCalendar.substring(12, 14);
				return StrCalendarResult;
			}
			// 否则返回空字符
			return "";
		} catch (Exception e) {
			// 错误处理
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 由长整型时间变为字符 通过长整数变为时间格式,可以自动适应8位或16位 输入："20090808"或"20090808080808"
	 * 返回："2009/08/08"或 "2009/08/08 08:08:08"
	 */
	public static String getDateStringByLongDatetimeForPage(long longCalendar) {
		try {
			String StrCalendar = String.valueOf(longCalendar);
			String StrCalendarResult = "";
			// 判断为日期型
			if (StrCalendar.length() == 8) {
				StrCalendarResult = StrCalendar.substring(0, 4) + "/" + StrCalendar.substring(4, 6) + "/"
						+ StrCalendar.substring(6, 8);
				return StrCalendarResult;
			}
			// 判断为日期及时间型
			if (StrCalendar.length() == 14) {
				StrCalendarResult = StrCalendar.substring(0, 4) + "/" + StrCalendar.substring(4, 6) + "/"
						+ StrCalendar.substring(6, 8);
				StrCalendarResult = StrCalendarResult + " " + StrCalendar.substring(8, 10) + ":"
						+ StrCalendar.substring(10, 12);
				return StrCalendarResult;
			}
			// 否则返回空字符
			return "";
		} catch (Exception e) {
			// 错误处理
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 得到系统当前时间 返回参数：String:系统当前时间 格式：yyyy/mm/dd
	 */
	public static String getCurrentDateTime() {
		return getDateStringByLongDatetimeForPage(getLongCalendar());
	}

	/**
	 * 得到系统当前日期显示， 返回格式：yyyy/mm/dd
	 */
	public static String getCurrentDateView() {
		// 获得当前日期
		Calendar cldCurrent = Calendar.getInstance();
		// 获得年月日
		String strYear = String.valueOf(cldCurrent.get(Calendar.YEAR));
		String strMonth = String.valueOf(cldCurrent.get(Calendar.MONTH) + 1);
		String strDate = String.valueOf(cldCurrent.get(Calendar.DATE));
		// 整理格式
		if (strMonth.length() < 2) {
			strMonth = "0" + strMonth;
		}
		if (strDate.length() < 2) {
			strDate = "0" + strDate;
		}
		// 得出当天日期的字符串
		String StrCurrentCalendar = strYear + "/" + strMonth + "/" + strDate;
		return StrCurrentCalendar;
	}

	/**
	 * 得到给定时间显示，格式：yyyy/mm/dd 参数格式："20090808"或"20090808080808"
	 */
	public static String getCurrentDateView(long longCalendar) {
		if (longCalendar == 0) {
			return "";
		}
		String strDateView = String.valueOf(longCalendar);
		// 获得年月日
		String strYear = strDateView.substring(0, 4);
		String strMonth = strDateView.substring(4, 6);
		String strDate = strDateView.substring(6, 8);
		// 整理格式
		if (strMonth.length() < 2) {
			strMonth = "0" + strMonth;
		}
		if (strDate.length() < 2) {
			strDate = "0" + strDate;
		}
		// 得出当天日期的字符串
		String StrCurrentCalendar = strYear + "/" + strMonth + "/" + strDate;
		return StrCurrentCalendar;
	}

	/**
	 * 由长整型时间变为字符 通过长整数变为时间格式,输入参数为6位的时间 如"123143" 返回格式：12点31分43秒
	 */
	public static String getTimeStringByLongTime(long longCalendar) {
		try {
			String StrCalendar = String.valueOf(longCalendar);
			String StrCalendarResult = "";

			// 判断为时间型
			if (StrCalendar.length() == 6) {
				StrCalendarResult = StrCalendar.substring(0, 2) + "点" + StrCalendar.substring(2, 4) + "分"
						+ StrCalendar.substring(4, 6) + "秒";
				return StrCalendarResult;
			}
			// 否则返回空字符
			return "";
		} catch (Exception e) {
			// 错误处理
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 由长整型时间变为字符 通过长整数变为时间格式,输入参数为6位的时间 如"123143" 返回格式：12:31:43
	 */
	public static String getTimeStringByLongTimeForPage(long longCalendar) {
		try {
			String StrCalendar = String.valueOf(longCalendar);
			String StrCalendarResult = "";

			// 判断为时间型
			if (StrCalendar.length() == 6) {
				StrCalendarResult = StrCalendar.substring(0, 2) + ":" + StrCalendar.substring(2, 4) + ":"
						+ StrCalendar.substring(4, 6);
				return StrCalendarResult;
			}
			// 否则返回空字符
			return "";
		} catch (Exception e) {
			// 错误处理
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 给指定的Calendar，返回常用的8位时间
	 */
	public static long getLongCalendar(Calendar yourCalendar) {
		try {
			long longCalendar = 0;

			// 获得年月日
			String strYear = String.valueOf(yourCalendar.get(Calendar.YEAR));
			String strMonth = String.valueOf(yourCalendar.get(Calendar.MONTH));
			String strDate = String.valueOf(yourCalendar.get(Calendar.DATE));

			// 整理格式
			if (strMonth.length() < 2) {
				strMonth = "0" + strMonth;
			}
			if (strDate.length() < 2) {
				strDate = "0" + strDate;
			}

			// 组合结果
			longCalendar = Long.parseLong(strYear + strMonth + strDate);

			// 系统默认月份加一
			longCalendar += 100L;

			// 创建上初始化上下文环境并返回
			return longCalendar;
		} catch (Exception Exp) {
			return 0;
		}
	}

	/**
	 * 给指定的Calendar，返回常用的１４位时间
	 */
	public static long getLongTime(Calendar yourCalendar) {
		try {
			long longCalendar = 0;
			// 获得年月日
			String strYear = String.valueOf(yourCalendar.get(Calendar.YEAR));
			String strMonth = String.valueOf(yourCalendar.get(Calendar.MONTH) + 1);
			String strDate = String.valueOf(yourCalendar.get(Calendar.DATE));
			String strHour = String.valueOf(yourCalendar.get(Calendar.HOUR));
			String strAM_PM = String.valueOf(yourCalendar.get(Calendar.AM_PM));
			String strMinute = String.valueOf(yourCalendar.get(Calendar.MINUTE));
			String strSecond = String.valueOf(yourCalendar.get(Calendar.SECOND));

			// 把时间转换为24小时制
			// strAM_PM=="1",表示当前时间是下午，所以strHour需要加12
			if ("1".equals(strAM_PM)) {
				strHour = String.valueOf(Long.parseLong(strHour) + 12);
			}

			// 整理格式
			if (strMonth.length() < 2) {
				strMonth = "0" + strMonth;
			}
			if (strDate.length() < 2) {
				strDate = "0" + strDate;
			}
			if (strHour.length() < 2) {
				strHour = "0" + strHour;
			}
			if (strMinute.length() < 2) {
				strMinute = "0" + strMinute;
			}
			if (strSecond.length() < 2) {
				strSecond = "0" + strSecond;
			}
			// 组合结果
			longCalendar = Long.parseLong(strYear + strMonth + strDate + strHour + strMinute + strSecond);

			// 创建上初始化上下文环境并返回
			return longCalendar;
		} catch (Exception Exp) {
			return 0;
		}
	}

	/**
	 * 将长整型日期转换为日历,自适应８位或１４位
	 */
	public static Calendar getCalendar(long longCalendar) {
		long longNF = 0;
		long longYF = 0;
		long longRZ = 0;
		long longXS = 0;
		long longFZ = 0;
		long longM = 0;
		GregorianCalendar gc = null;
		// 判断是８位还是１４位
		if (String.valueOf(longCalendar).length() < 14) {
			longNF = Long.parseLong(String.valueOf(longCalendar).substring(0, 4));
			longYF = Long.parseLong(String.valueOf(longCalendar).substring(4, 6)) - 1;
			longRZ = Long.parseLong(String.valueOf(longCalendar).substring(6));
			gc = new GregorianCalendar((int) longNF, (int) longYF, (int) longRZ);
		} else {
			longNF = Long.parseLong(String.valueOf(longCalendar).substring(0, 4));
			longYF = Long.parseLong(String.valueOf(longCalendar).substring(4, 6)) - 1;
			longRZ = Long.parseLong(String.valueOf(longCalendar).substring(6, 8));
			longXS = Long.parseLong(String.valueOf(longCalendar).substring(8, 10));
			longFZ = Long.parseLong(String.valueOf(longCalendar).substring(10, 12));
			longM = Long.parseLong(String.valueOf(longCalendar).substring(12));

			gc = new GregorianCalendar((int) longNF, (int) longYF, (int) longRZ, (int) longXS, (int) longFZ,
					(int) longM);

		}
		return gc;
	}

	/**
	 * 当前时间增加间隔分钟后的时间
	 */
	public static long addMinutes(long longCalendar, int intMin) {
		try {
			//
			long longDate = 0;
			Calendar cal = getCalendar(longCalendar);
			long longMills = cal.getTimeInMillis() + intMin * 60 * 1000;
			cal.setTimeInMillis(longMills);

			if (String.valueOf(longCalendar).length() < 14) {
				longDate = getLongCalendar(cal);
			} else {
				longDate = getLongTime(cal);
			}

			// 返回日期
			return longDate;
		} catch (Exception Exp) {
			return -1;
		}
	}

	/**
	 * 当前时间增加间隔小时后的时间
	 */
	public static long addHours(long longCalendar, int intHour) {
		try {
			//
			long longDate = 0;
			Calendar cal = getCalendar(longCalendar);
			long longMills = cal.getTimeInMillis() + intHour * 60 * 60 * 1000;
			cal.setTimeInMillis(longMills);

			if (String.valueOf(longCalendar).length() < 14) {
				longDate = getLongCalendar(cal);
			} else {
				longDate = getLongTime(cal);
			}

			// 返回日期
			return longDate;
		} catch (Exception Exp) {
			return -1;
		}
	}

	/**
	 * 当前时间增加间隔天后的时间
	 */
	public static long addDays(long longCalendar, int intDay) {
		try {
			//
			long longDate = 0;
			Calendar cal = getCalendar(longCalendar);
			long longMills = cal.getTimeInMillis() + intDay * 24 * 60 * 60 * 1000;
			cal.setTimeInMillis(longMills);

			if (String.valueOf(longCalendar).length() < 14) {
				longDate = getLongCalendar(cal);
			} else {
				longDate = getLongTime(cal);
			}

			// 返回日期
			return longDate;
		} catch (Exception Exp) {
			return -1;
		}
	}

	/**
	 * 当前时间增加间隔星期后的时间
	 */
	public static long addWeeks(long longCalendar, int intWeek) {
		try {
			//
			long longDate = 0;
			Calendar cal = getCalendar(longCalendar);
			long longMills = cal.getTimeInMillis() + intWeek * 7 * 24 * 60 * 60 * 1000;
			cal.setTimeInMillis(longMills);

			if (String.valueOf(longCalendar).length() < 14) {
				longDate = getLongCalendar(cal);
			} else {
				longDate = getLongTime(cal);
			}

			// 返回日期
			return longDate;
		} catch (Exception Exp) {
			return -1;
		}
	}

	/**
	 * 当前日期增加间隔月份后的时间
	 */
	public static long addMonths(long longCalendar, int intMonth) {
		try {
			long longNF = 0;
			long longYF = 0;
			long longRZ = 0;
			long longDate = 0;
			long longNIAN = 0;
			String strYF = "";
			String strRZ = "";
			longNF = Long.parseLong(String.valueOf(longCalendar).substring(0, 4));
			longYF = Long.parseLong(String.valueOf(longCalendar).substring(4, 6));
			longRZ = Long.parseLong(String.valueOf(longCalendar).substring(6, 8));
			longYF = longYF + intMonth;

			if (longYF > 12) {
				longNIAN = (long) Math.floor(longYF / 12);
				longYF = longYF % 12;

				if (longYF == 0) {
					longYF = 12;
				}

				longNF = longNF + longNIAN;
			}

			// 处理特殊日
			if (longRZ >= 28) {
				longRZ = getNormalDay(longNF, longYF, longRZ);
			}

			if (longYF < 10) {
				strYF = "0" + String.valueOf(longYF);
			} else {
				strYF = String.valueOf(longYF);
			}

			if (longRZ < 10) {
				strRZ = "0" + String.valueOf(longRZ);
			} else {
				strRZ = String.valueOf(longRZ);
			}

			// 判断是８位还是１４位
			if (String.valueOf(longCalendar).length() < 14) {
				longDate = Long.parseLong(String.valueOf(longNF) + strYF + strRZ);
			} else {
				longDate = Long.parseLong(
						String.valueOf(longNF) + strYF + strRZ + String.valueOf(longCalendar).substring(8, 14));
			}
			// 返回日期
			return longDate;
		} catch (Exception Exp) {
			return -1;
		}
	}

	/**
	 * 返回正常日－处理３０／３１／２８ 输入参数：long calendar 当前时间, int intWeek 间隔星期 返回值：
	 * long:处理后的时间
	 */
	public static long getNormalDay(long longNF, long longYF, long longRZ) {
		try {
			// 只有日为２８／２９／３０／３１才运行此函数
			// 处理２月份
			if (longYF == 2) {
				if ((longNF % 4) == 0) {
					if (longRZ > 28) {
						longRZ = 29;
					}
				} else {
					longRZ = 28;
				}
			}
			if (longRZ == 31) {
				if (longYF == 4 || longYF == 6 || longYF == 9 || longYF == 11) {
					longRZ = 30;
				}
			}
			return longRZ;

		} catch (Exception Exp) {
			return -1;
		}
	}

	/**
	 * 获得系统中使用长整形表示的日期加星期
	 */
	public static String getStringCalendarAndWeek() {
		try {
			String strCalendar = "";

			// 获得当前日期
			Calendar cldCurrent = Calendar.getInstance();

			// 获得年月日
			String strYear = String.valueOf(cldCurrent.get(Calendar.YEAR));
			String strMonth = String.valueOf(cldCurrent.get(Calendar.MONTH) + 1);
			String strDate = String.valueOf(cldCurrent.get(Calendar.DATE));

			// 整理格式
			if (strMonth.length() < 2) {
				strMonth = "0" + strMonth;
			}
			if (strDate.length() < 2) {
				strDate = "0" + strDate;
			}
			// 组合结果
			strCalendar = strYear + "年" + strMonth + "月" + strDate + "日";

			int intWeek = cldCurrent.get(Calendar.DAY_OF_WEEK);
			String strWeek = "";
			switch (intWeek) {
			case 1:
				strWeek = "星期日";
				break;
			case 2:
				strWeek = "星期一";
				break;
			case 3:
				strWeek = "星期二";
				break;
			case 4:
				strWeek = "星期三";
				break;
			case 5:
				strWeek = "星期四";
				break;
			case 6:
				strWeek = "星期五";
				break;
			case 7:
				strWeek = "星期六";
				break;
			default:
				break;
			}

			strCalendar = strCalendar + " " + strWeek + " ";

			// 创建上初始化上下文环境并返回
			return strCalendar;
		} catch (Exception Exp) {
			return "";
		}
	}

	public static String Text2HtmlToPageContent(String text) {
		// 若原字符串为空，则返回空对象
		if (text == null) {
			return "";
		}
		// 保存原字符串，方法结束时此字符串被转换成HTML格式的字符串
		String strSource = text;
		// 转换字符串的临时空间
		StringBuffer sbTarget = new StringBuffer();
		// 纯文本中要转换的字符或字符串
		char[] charArraySource = { '<', '>', '&', '"', '\n' };
		// 纯文本中要转换的字符或字符串对应的HTML表达方式
		String[] strArrayTarget = { "&lt;", "&gt;", "&amp;", "&quot;", "<br>" };

		// 记录处理过特殊字符的位置
		int intStart = 0;

		// 依次检查每一个源字符串的字符或字符串
		for (int i = 0; i < strSource.length(); i++) {
			// 字符串中包含要转换的字符或字符串，则进行相应转换
			for (int j = 0; j < charArraySource.length; j++) {
				// 当前检查的字符是要转换的特殊字符，则进行处理
				if (strSource.charAt(i) == charArraySource[j]) {
					sbTarget.append(strSource.substring(intStart, i));
					sbTarget.append(strArrayTarget[j]);
					intStart = i + 1;
					continue;
				}
			}
		}
		// 将所有处理位置之后的字符串放入目标字符串中
		sbTarget.append(strSource.substring(intStart));

		// 转换完成，返回转换结果
		return sbTarget.toString();
	}

	public static String getDateStringByLongDate(long longCalendar) {
		try {
			String StrCalendar = String.valueOf(longCalendar);
			String StrCalendarResult = "";

			StrCalendarResult = StrCalendar.substring(0, 4) + "年" + StrCalendar.substring(4, 6) + "月"
					+ StrCalendar.substring(6, 8) + "日";
			return StrCalendarResult;
		} catch (Exception e) {
			// 错误处理
			e.printStackTrace();
			return "";
		}
	}

	public static String getJi(String strYue) {
		int intYue = Integer.decode(strYue).intValue();
		if (intYue >= 1 && intYue <= 3) {
			return "1";
		} else if (intYue >= 4 && intYue <= 6) {
			return "2";
		} else if (intYue >= 7 && intYue <= 9) {
			return "3";
		} else {
			return "4";
		}
	}

	public static long getTimeMills(String strTime) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = df.parse(strTime);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			long longMills = cal.getTimeInMillis();
			return longMills;
		} catch (Exception e) {
			// 错误处理
			e.printStackTrace();
			return -1;
		}

	}

	public static String getMonthORYearRate(String caseVoDate, String mark, int dateCount) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date date;
		try {
			date = formatter.parse(caseVoDate);
			calendar.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if ("year".equals(mark)) {
			calendar.add(Calendar.YEAR, dateCount);
		}
		if ("month".equals(mark)) {
			calendar.add(Calendar.MONTH, dateCount);
		}
		String dateStr = DateUtil.format(calendar.getTime(), "yyyy-MM-dd");
		return dateStr;
	}
	public static Date chgDateToTimestrap(Date date,String formatPattern,String timeStrapParttern){
		Date resultDate = null;
		if(formatPattern == null){
			formatPattern = fullDateTimePattern;
		}
		if(timeStrapParttern == null){
			timeStrapParttern = fullDateTimePattern;
		}
		try{
			if(date == null){
				return null;
			}
			String format = format(date, formatPattern);
			resultDate = parse(format,timeStrapParttern);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultDate;
	}

	/**
	 * 获取当天的第一刻 00:00:00
	 *
	 * @param date 日期
	 */
	public static Date firstSecOfDay(Date date) {
		Date returnDate = null;
		try {
			SimpleDateFormat d = new SimpleDateFormat(fullDateTimePattern);
			String day = format(date, defaultDatePattern);
			String day_first_sec = day + " 00:00:00";
			returnDate = d.parse(day_first_sec);
		}catch (Exception e){
			e.printStackTrace();
		}
		return returnDate;
	}


	/**
	 * 获取今天的0点
	 */
	public static String getTodayStartTime() {
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR_OF_DAY, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		String str = String.valueOf(todayStart.getTime().getTime());

		return str;
	}
	/**
	 * 获取今天的时间尾
	 */
	public static String getTodayEndTime() {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.HOUR_OF_DAY, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		String str = String.valueOf(todayEnd.getTime().getTime());
		return str;
	}

	/**
	 * 获取本月第一天
	 */
	public static String getMonthStartTime() {
		Calendar monthStart = Calendar.getInstance();
		monthStart.set(Calendar.DAY_OF_MONTH, 1);
		monthStart.set(Calendar.HOUR_OF_DAY, 0);
		monthStart.set(Calendar.MINUTE, 0);
		monthStart.set(Calendar.SECOND, 0);
		String str = String.valueOf(monthStart.getTime().getTime());
		return str;
	}
	/**
	 * 获取本月最后一天
	 */
	public static String getMonthEndTime() {
		Calendar monthEnd = Calendar.getInstance();
		//如果传入日期可以获得月份的第一天和最后一天
//		monthEnd.setTime(date);
		monthEnd.set(Calendar.DAY_OF_MONTH, monthEnd.getActualMaximum(Calendar.DAY_OF_MONTH));
		monthEnd.set(Calendar.HOUR_OF_DAY, 23);
		monthEnd.set(Calendar.MINUTE, 59);
		monthEnd.set(Calendar.SECOND, 59);
		Date date = monthEnd.getTime();
		String str = String.valueOf(monthEnd.getTime().getTime());
		return str;
	}

}
