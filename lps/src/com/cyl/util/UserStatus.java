//package com.cyl.util;
//
//import java.io.BufferedInputStream;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Properties;
//
//public class UserStatus {
//
//	private Properties prop = null;
//	private static UserStatus os = null;
//
//	private UserStatus() {
//		
//		prop = new Properties();
//
//		try {
//			InputStream in = new BufferedInputStream(
//					new FileInputStream(this.getClass().getResource("../properties/userStatus.properties").getPath()));
//			prop.load(in);
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	static UserStatus getInstance() {
//		if (os == null) {
//			synchronized (UserStatus.class) {
//				if (os == null) {
//					os = new UserStatus();
//				}
//			}
//		}
//		return os;
//	}
//
//	public static String getStatus(String key) {
//		return getInstance().prop.getProperty(key);
//	}
//
//}
