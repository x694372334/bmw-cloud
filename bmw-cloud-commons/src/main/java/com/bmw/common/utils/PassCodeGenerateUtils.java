package com.bmw.common.utils;

import java.util.Random;

/**
 * 随机密码生成器
 * 使用方法 PassCodeGenerateUtils.generateDefaultPassword();
 * 
 * @author lyl
 * 2016年12月1日
 */
public class PassCodeGenerateUtils {
	
	
	/**
	 * 大写字母
	 */
	private static String[] uppercase = new String[]{"Q","W","E","R","T","Y","U","I","O","p",
			"A","S","D","F","G","H","J","K","L","Z","X","C","V","B","N","M"};
	
	/**
	 * 小写字母
	 */
	private static String[] lowercase = new String[]{"q","w","e","r","t","y","u","i","o","p",
			"a","s","d","f","g","h","j","k","l","z","x","c","v","b","n","m"};
	
	private static Random rand = new Random();
	
	
	
	/**
	 * 简单随机密码生成器
	 * @param length 长度
	 * 
	 * @author lyl
	 * 2016年8月4日
	 */
	public static String generateDefaultPassword(int length){
		// 字母数
		int zmNum = rand.nextInt(length); 
		zmNum = zmNum + 1;
		// 字母位置
		Integer[] zmPosition = new Integer[zmNum];
		for(int i=0;i<zmNum;i++){
			zmPosition[i] = norepeatNum(zmPosition,length);
		}
		StringBuilder pwd = new StringBuilder();
		for(int i=0;i<length;i++){
			String tempStr = "";
			boolean zmFlag = true;
			for(int j=0;j<zmNum;j++){
				int tempInt = zmPosition[j];
				if(i == tempInt){
					zmFlag = false;
					// 字母大小写，0大写，1小写
					int dxx = rand.nextInt(2);
					if(dxx == 0){
						tempStr = uppercase[rand.nextInt(26)];
					}else{
						tempStr = lowercase[rand.nextInt(26)];
					}
					break;
				}
			}
			
			if(zmFlag){
				int num = rand.nextInt(10);
				tempStr = String.valueOf(num);
			}
			
			pwd.append(tempStr);
		}
		
		return pwd.toString();
	}
	
	
	/**
	 * 生成不重复的数字
	 * @param s 判断依据
	 * @param length 随机数字范围
	 * @author lyl
	 * 2016年8月4日
	 */
	private static int norepeatNum(Integer[] s,int length){
		int temp = rand.nextInt(length);
		// 判重
		boolean repeat = true;
		for(int j=0;j<s.length;j++){
			Integer num = s[j];
			if(num != null && num == temp){
				repeat = false;
			}
		}
		if(repeat){
			return temp;
		}else{
			return norepeatNum(s,length);
		}
	}
	
	
}
