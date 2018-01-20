package com.faye.factory;

import java.util.ResourceBundle;

/**
 * 工厂：创建dao或者service实例
 * @author Faye
 *
 */
public class BeanFactory {
	private static ResourceBundle bundle;
	static {
		bundle = ResourceBundle.getBundle("instance");
	}
	
	public static <T> T GetInstance(String key, Class<T> clazz)  {
		String className = bundle.getString(key);
		
		try {
			return  (T) Class.forName(className).newInstance();
		} catch (Exception e) {
			throw new RuntimeException();
		} 
	}
}
