package com.cyl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TextMain {

	public static void main(String[] args) throws ParseException {
		OrderStatus.getInstance();
		System.out.println(new java.util.Date().getTime());
		System.out.println(
				new SimpleDateFormat("yyyy-mm-dd").parse(new java.sql.Date(new java.util.Date().getTime()).toString()).getTime());
		System.out.println(new java.util.Date().after(new SimpleDateFormat("yyyy-mm-dd").parse(new java.sql.Date(new java.util.Date().getTime()).toString())));

	}

}
