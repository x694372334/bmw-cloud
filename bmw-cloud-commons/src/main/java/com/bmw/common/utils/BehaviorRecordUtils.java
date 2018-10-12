//package com.bmw.common.utils;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.integration.support.MessageBuilder;
//
//import com.alibaba.fastjson.JSONObject;
//import com.bmw.common.constant.behavior.RequestSysConstant;
//import com.bmw.common.model.BehaviorRecordModel;
////import com.bmw.common.mq.KafkaBehaviorSource;
//
///**
// * 用户行为记录
// * @author lyl
// * 2017年1月10日
// */
//public class BehaviorRecordUtils {
//
//
//	/**
//	 * 用户行为记录
//	 *
//	 * @return true:发送成功,false:发送失败
//	 * @author lyl
//	 * 2017年1月10日
//	 */
//	public static boolean record(BehaviorRecordModel record){
//		boolean result = false;
//		if(StringUtils.isBlank(record.getUserId())
//				|| StringUtils.isBlank(record.getUserName())
//				|| StringUtils.isBlank(record.getRequestFunc())
//				|| record.getRequestTime() == null){
//			return false;
//		}
//
//		if(StringUtils.isBlank(record.getRequestSys())){
//			String sys = getSystemName(record.getRequestUrl());
//			record.setRequestSys(sys);
//		}
//
//		String r = JSONObject.toJSONString(record);
//		KafkaBehaviorSource BehaviorSource = SpringContextUtils.getBean(KafkaBehaviorSource.class);
//		result = BehaviorSource.BEHAVIOR_MESSAGE_OUTPUT().send(MessageBuilder.withPayload(r.getBytes()).build());
//
//		return result;
//	}
//
//
//
//
//	/**
//	 * 根据URL获取系统标识,用于用户日志记录
//	 * @author lyl
//	 * 2017年1月12日
//	 */
//	public static String getSystemName(String url){
//		if(StringUtils.isBlank(url)){
//			return "通过域名不能识别出系统标识,请修改Base系统完成域名转换系统标识功能。";
//		}
//
//		if(url.length() > 30){
//			url = url.substring(0, 30); // 截取字符，确保之后验证的是域名内容
//		}
//
//		if(url.indexOf("bmw-OA") != -1){
//			return RequestSysConstant.bmw_OA;
//		}
//
//		return "通过域名不能识别出系统标识,请修改Base系统完成域名转换系统标识功能。";
//	}
//
//}
