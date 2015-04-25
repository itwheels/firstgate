package com.itwheel.edigate.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 简单介绍：用于取得Spring管理的bean对象<br>
 * 详细介绍：<br>
 */
public class SpringLocator implements ApplicationContextAware {

	private static ApplicationContext cxt;
	private SpringLocator(){}

	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.cxt = arg0;
	}

	public static Object getBean(String beanName) {
		return cxt.getBean(beanName);
	}
}
