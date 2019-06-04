package com.njwd.zuul.util;

import java.util.ResourceBundle;

public class SystemUtil {

	/**
	 * 根据属性获取属性配置文件中的属性值
	 * 
	 * @param propertyName
	 *            :属性名
	 * @return
	 */
	public static String getProperty(String propertyName) {
		try {
			ResourceBundle res = ResourceBundle.getBundle("system");
			return res.getString(propertyName);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 根据属性获取属性配置文件中的属性值
	 * 
	 * @param propertyFile
	 *            :属性文件名
	 * @param propertyName
	 *            :属性
	 * @return
	 */
	public static String getProperty(String propertyFile, String propertyName) {
		try {
			ResourceBundle res = ResourceBundle.getBundle(propertyFile);
			return res.getString(propertyName);
		} catch (Exception e) {
			return null;
		}
	}

}
