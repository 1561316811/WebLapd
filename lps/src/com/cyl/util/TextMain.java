package com.cyl.util;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.Date;

public class TextMain {

	public static void main(String[] args) throws ParseException, IllegalArgumentException, IllegalAccessException {
//		OrderStatus.getInstance();
//		System.out.println(new java.util.Date().getTime());
//		System.out.println(
//				new SimpleDateFormat("yyyy-mm-dd").parse(new java.sql.Date(new java.util.Date().getTime()).toString()).getTime());
//		System.out.println(new java.sql.Date(new java.util.Date().getTime()).toString());
//		System.out.println(new java.util.Date().after(new SimpleDateFormat("yyyy-mm-dd").parse(new java.sql.Date(new java.util.Date().getTime()).toString())));
//		System.out.println(new DecimalFormat("000").format(1));
//		Calendar c = Calendar.getInstance();
//		c.set(1996, Calendar.DECEMBER, 10, 0, 0, 0);
//		new SimpleDateFormat("yyyy-mm-dd").parse(c.toString());
//		System.out.println(new java.sql.Date(System.currentTimeMillis()).toString());
//		System.out.println(SimpleDate.getTodayDate().toString());
//		System.out.println(new SimpleDateFormat("yyyy-mm-dd").format(SimpleDate.getTodayDate()).toString());
//		System.out.println(new SimpleDateFormat("yyyy-mm-dd").format(SimpleDate.getTodayDate()).toString());
//		System.out.println(new java.sql.Date(c.getTime().getTime()).toString());
//		System.out.println(c.getTime().toString());
//		System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()));
		
		
		
		Integer in = null;
		in = 10;
		
		Text t = new Text();
		t.d = new Date();
		t.setI(2);
		
		Text t2 = new Text();
		t2.d = new Date();
		t2.setI(1000);
		
		Field[] f = t.getClass().getDeclaredFields();
		Field[] f2 = t2.getClass().getDeclaredFields();
		
		System.out.println(t.getClass() == t2.getClass());
		
		for (int i = 0; i < f.length; i++) {
			f[i].setAccessible(true);
			f2[i].setAccessible(true);
			 f[i].set(t, f2[i].get(t2));;
			 f[i].setAccessible(false);
				f2[i].setAccessible(false);
				 
		}
		System.out.println("t.i :" + t.getI());
		
		System.out.println(in);
//		System.out.println(new Text().i);
		
//		t.l.add(1);
	}

}
