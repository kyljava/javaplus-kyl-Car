package com.turing.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class PropertyUtils {
	public static Map<String, Object> getyInfoOfPropert(String propertyPath) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		Properties prop = new Properties();
		InputStream inputStream = PropertyUtils.class.getClassLoader().getResourceAsStream(propertyPath);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));

		prop.load(bufferedReader);
		Iterator<String> it = prop.stringPropertyNames().iterator();
		while (it.hasNext()) {
			String key = it.next();
			System.out.println("key:" + prop.getProperty(key));
			map.put(key, prop.getProperty(key));
		}

		return map;

	}
	public static void main(String[] args) throws IOException {
		
		PropertyUtils.getyInfoOfPropert("mail.properties");
	}
}
