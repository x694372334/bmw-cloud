package com.bmw.common.utils;

import java.util.UUID;

/**
 * ID生成工具类
 * @author lyl
 * 2016年7月6日
 */
public class IDUtils {
	
	

	/**
	 * 获取32位UUID
	 * 
	 * @return String UUID
	 */
	public static String getUUID32() {
		String tempUUID = UUID.randomUUID().toString();
		String UUID = tempUUID.replace("-", "");
		return UUID;
	}
	
}
