/**
 * 
 */
package com.puyixiaowo.rsnake.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author weishaoqiang
 * @date 2016年12月24日 下午5:42:18
 */
public class DateUtil {
	private static String formatStr = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 获取当前时间字符串
	 * @return
	 */
	public static String getNowStr(){
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		return format.format(now);
	}
}
