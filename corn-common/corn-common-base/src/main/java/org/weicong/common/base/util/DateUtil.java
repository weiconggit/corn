package org.weicong.common.base.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description 
 * @author weicong
 * @date 2019年6月15日
 * @version 1.0
 */
public abstract class DateUtil {

	// 时间格式
	public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	// 支付时间格式
	public static final SimpleDateFormat SDF_PAY = new SimpleDateFormat("yyyyMMddHHmmss");
	
	/**
	 * 获取当前时间指定格式的时间字符串
	 * @param sdf   时间格式
	 * @return      时间字符串
	 */
	public static String getStringDate(SimpleDateFormat sdf) {
		return sdf.format(new Date());
	}
	
	/**
	 * 获取指定时间指定格式的时间字符串
	 * @param sdf   时间格式
	 * @param date  指定日期
	 * @return      时间字符串
	 */
	public static String getStringDate(SimpleDateFormat sdf, Date date) {
		return sdf.format(date);
	}
	
	/**
	 * 获取当前时间戳字符串
	 * @return
	 */
	public static String getTimestamp() {
		return String.valueOf(System.currentTimeMillis());
	}
	
}
