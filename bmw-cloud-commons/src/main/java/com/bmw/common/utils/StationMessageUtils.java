//package com.bmw.common.utils;
//
////import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
////import org.springframework.integration.support.MessageBuilder;
//
//import com.alibaba.fastjson.JSONObject;
//import com.bmw.common.model.StationMessageContent;
////import com.bmw.common.mq.KafkaStationMessageSource;
//
//
///**
// * 站内信工具类
// * @author lyl
// * 2016年12月28日
// */
//public class StationMessageUtils {
//
//	private static Logger log = LoggerFactory.getLogger(StationMessageUtils.class);
//
//
//	/**
//	 * 发送站内信
//	 * @param pc 发送短信模型
//	 *
//	 * @return true:发送成功,false:发送失败
//	 * @author lyl
//	 * 2016年12月28日
//	 */
//	public static boolean send(StationMessageContent content){
//		boolean result = false;
//		try{
//			if(content != null && StringUtils.isBlank(content.getEnterpriseId()) && StringUtils.isBlank(content.getUserId())){
//				return false;
//			}
//
//			String message = JSONObject.toJSONString(content);
//
//			KafkaStationMessageSource station = SpringContextUtils.getBean(KafkaStationMessageSource.class);
//			result = station.STATION_MESSAGE_OUTPUT().send(MessageBuilder.withPayload(message.getBytes()).build());
//		}catch(Exception e){
//			log.error("站内信发送日志记录 异常信息：", e);
//		}
//
//		return result;
//	}
//}
