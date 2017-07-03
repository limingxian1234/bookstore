package com.atguigu.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class WebUtils {

	/**
	 * 把字符串转换成为int类型
	 * 
	 * @param intStr
	 * @param defaultValue
	 * @return
	 */
	public static int parseInt(String intStr, int defaultValue) {
		int result = 0;
		try {
			result = Integer.parseInt(intStr);
		} catch (Exception e) {
			result = defaultValue;
		}
		return result;
	}
	
	public static double parseDouble(String doubleStr, double defaultValue) {
		double result = 0;
		try {
			result = Double.parseDouble(doubleStr);
		} catch (Exception e) {
			result = defaultValue;
		}
		return result;
	}

	/**
	 * HttpServletRequest 这个对象是在web的API才会有。耦合
	 * 
	 * @param request
	 * @param bean
	 * @return
	 */
	public static Object copyParam2Bean(HttpServletRequest request, Object bean) {

		try {
			BeanUtils.populate(bean, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bean;
	}

	/**
	 * 参数的类型，尽量使用javaSe中提供的类
	 * 
	 * @param paramMap
	 * @param bean
	 * @return
	 */
	public static <T> T copyParam2Bean(Map paramMap, T bean) {

		try {
			// beanUtils在把map中的值注入到Bean对象中的时候，使用的是setXxx方法
			BeanUtils.populate(bean, paramMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bean;
	}

}
