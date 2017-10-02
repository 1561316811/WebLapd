package com.cyl.util;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SimpleDate {
	
	public static Date getTodayDate(){
		Date d = null;
		try {
			d =  new SimpleDateFormat("yyyy-mm-dd").parse(
					new java.sql.Date(
							new java.util.Date().getTime()).toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	public static String getTodayDateDay(){
		return new SimpleDateFormat("yyyy-mm-dd").format(getTodayDate()).toString();
	}
	
}
