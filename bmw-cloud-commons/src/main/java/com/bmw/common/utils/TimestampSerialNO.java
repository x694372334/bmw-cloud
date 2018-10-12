package com.bmw.common.utils;

import java.util.Date;

/**
 * 基于时间戳的序列编号
 * @author lyl
 * 2016.12.30
 * */
public class TimestampSerialNO {
	private static long orderNum = 0l;
	private static long taskNum = 0l;
	private static long contractNum = 0l;
	private static long agreementNum = 0l;
	private static String date;
	
	/**
	 * 创建项目或购买服务的 的 序列编号 格式为yyyyMMddHHmmss+XXXXX
	 * @author lyl
	 * */
	public static synchronized String createTaskSerialNo() {
        String str = DateUtil.getDateStr(new Date(),"yyyyMMddHHmmss");  
        if(date==null||!date.equals(str)){  
            date = str;  
            taskNum  = 0l;  
        }  
        taskNum ++;  
        long taskNo = Long.parseLong(date) * 10000;
        taskNo += taskNum;
        return taskNo+"";  
    }
	/**
	 * 创建订单序列编号 格式为yyMMddHHmmss+XXXXX
	 * @author lyl
	 * */
	public static synchronized String createOrderSerialNo() {
		String str = DateUtil.getDateStr(new Date(),"yyMMddHHmmss");  
		if(date==null||!date.equals(str)){  
			date = str;  
			orderNum  = 0l;  
		}  
		orderNum ++;  
		long orderNo = Long.parseLong(date) * 10000;
		orderNo += orderNum;
		return orderNo+"";  
	}
	
	
	/**
	 * 创建合同序列编号 格式为yyMMddHHmmss+XXXXX
	 * @author lyl
	 * */
	public static synchronized String createContractSerialNo() {
		String str = DateUtil.getDateStr(new Date(),"yyMMddHHmmss");  
		if(date==null||!date.equals(str)){  
			date = str;  
			contractNum  = 0l;  
		}  
		contractNum ++;  
		long orderNo = Long.parseLong(date) * 10000;
		orderNo += contractNum;
		return orderNo+"";  
	}
	/**
	 * 创建代销协议序列编号 格式为yyMMddHHmmss+XXXXX
	 * @author pr
	 * */
	public static synchronized String createServiceAgreementSerialNo() {
		String str = DateUtil.getDateStr(new Date(),"yyMMddHHmmss");  
		if(date==null||!date.equals(str)){  
			date = str;  
			agreementNum  = 0l;  
		}  
		contractNum ++;  
		long orderNo = Long.parseLong(date) * 100;
		orderNo += agreementNum;
		return "TLHL-DXFW-"+orderNo+"";  
	}
	
}
