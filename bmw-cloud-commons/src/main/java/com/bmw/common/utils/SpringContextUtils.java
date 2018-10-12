package com.bmw.common.utils;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Spring上下文获取工具
 * 
 * @author lyl 
 * 2016年11月1日
 */
public class SpringContextUtils implements ApplicationContextAware {
	
	// Spring应用上下文环境
	private static ApplicationContext applicationContext;
	
	private static DefaultListableBeanFactory defaultListableBeanFactory;
	

	/**
	 * 实现ApplicationContextAware接口的回调方法，设置上下文环境
	 * 
	 * @param applicationContext
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		if(SpringContextUtils.applicationContext == null){
			SpringContextUtils.applicationContext = applicationContext;
			defaultListableBeanFactory = (DefaultListableBeanFactory)applicationContext.getAutowireCapableBeanFactory();
		}
	}

	/**
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	
	/**
	 * 获取Bean对象
	 * @param name Bean名称
	 * @return 返回Bean对象
	 * @author lyl
	 * 2016年11月1日
	 */
	public static Object getBean(String name){
		return applicationContext.getBean(name);
	}
	
	

	/**
	 * 获取Bean对象
	 * @param name Bean名称
	 * @param obj 转换对象
	 * @return 返回Bean对象
	 * @author lyl
	 * 2016年11月1日
	 */
	public static <T> T getBean(String beanName,Class<T> obj){
		return applicationContext.getBean(beanName,obj);
	}
	
	/**
	 * 获取Bean对象
	 * @param obj Bean类型
	 * @return 返回Bean对象
	 * @author lyl
	 * 2016年11月1日
	 */
	public static <T> T getBean(Class<T> obj){
		return applicationContext.getBean(obj);
	}
	
	
	/**
	 * 根据BeanName删除Bean
	 * @author lyl
	 * 2017年2月14日
	 */
	public static void removeBean(String beanName){
		defaultListableBeanFactory.removeBeanDefinition(beanName);
	}
	
}
