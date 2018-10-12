package com.bmw.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.integration.support.MessageBuilder;

import com.alibaba.fastjson.JSONObject;
import com.bmw.common.model.ExceptionLog;
//import com.bmw.common.mq.KafkaBaseSource;
import com.bmw.common.utils.SpringContextUtils;

/**
 * 异常日志
 * @author lyl
 * 2018年6月12日
 */
public class ExceptionLogThread implements Runnable{
	
	
	private Logger log = LoggerFactory.getLogger(ExceptionLogThread.class);
	
//	private KafkaBaseSource kafkaSource;
	
	private ExceptionLog exceptionLog;
	
	
//	public ExceptionLogThread(ExceptionLog exceptionLog) {
//		this.exceptionLog = exceptionLog;
//		kafkaSource = SpringContextUtils.getBean(KafkaBaseSource.class);
//	}

	@Override
	public void run() {
		try{
			String content = JSONObject.toJSONString(exceptionLog);
	        // 发送到消息队列中
//			boolean r = kafkaSource.SYS_LOG_EXCEPTION_OUTPUT().send(MessageBuilder.withPayload(content.getBytes()).build());
			
//			if(!r){
//				log.error("系统异常日志发送队列消息失败！content={}",content);
//			}
		}catch(Exception e){
			log.error("异常日志记录 异常信息：", e);
		}
		
		
	}

}
