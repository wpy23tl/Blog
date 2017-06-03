package com.wpy.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {

	/**
	 * ÈÕÆÚ×ª×Ö·û´®
	 * @param dateTime
	 * @param template
	 * @return
	 */
	public static String DateToString(Date dateTime,String pattern){
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String dateString = simpleDateFormat.format(dateTime);
		return dateString;
	}
}
