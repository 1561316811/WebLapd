package com.cyl.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OrderStatus {

	private Properties prop = null;
	private static OrderStatus os = null;

	private OrderStatus() {
		prop = new Properties();

		try {
			InputStream in = new BufferedInputStream(
					new FileInputStream(this.getClass().getResource("../properties/orderStatus.properties").getPath()));
			prop.load(in);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static OrderStatus getInstance() {
		if (os == null) {
			synchronized (OrderStatus.class) {
				if (os == null) {
					os = new OrderStatus();
				}
			}
		}
		return os;
	}

	public static String getStatus(String key) {
		return getInstance().prop.getProperty(key);
	}

}
