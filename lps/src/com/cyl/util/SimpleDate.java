package com.cyl.util;

import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SimpleDate {
	
	public static Date getTodayDate(){
		Date d = null;
		try {
			d =  new SimpleDateFormat("yyyy-MM-dd").parse(
					new java.sql.Date(
							new java.util.Date().getTime()).toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	public static String getTodayDateDay(){
		return new SimpleDateFormat("yyyy-MM-dd").format(getTodayDate()).toString();
	}
	
	public static String getDate(int year, int month, int date){
		Calendar c = Calendar.getInstance();
		c.set(year, month - 1, date);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}
	
}
