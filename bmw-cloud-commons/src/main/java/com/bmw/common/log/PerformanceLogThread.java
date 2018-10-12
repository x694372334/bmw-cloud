package com.bmw.common.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.integration.support.MessageBuilder;

import com.alibaba.fastjson.JSONObject;
import com.bmw.common.model.PerformanceLog;
//import com.bmw.common.mq.KafkaBaseSource;
import com.bmw.common.utils.SpringContextUtils;

/**
 * 性能日志
 * @author lyl
 * 2018年6月12日
 */
public class PerformanceLogThread implements Runnable{
	
	
	private Logger log = LoggerFactory.getLogger(PerformanceLogThread.class);
	
//	private KafkaBaseSource kafkaSource;
	
	private PerformanceLog performanceLog;
	
	
	public PerformanceLogThread(PerformanceLog performanceLog) {
		this.performanceLog = performanceLog;
//		kafkaSource = SpringContextUtils.getBean(KafkaBaseSource.class);
	}
	
	

	@Override
	public void run() {
		try{
			String content = JSONObject.toJSONString(performanceLog);
			
			// 发送到消息队列中
//			boolean r = kafkaSource.SYS_LOG_PERFORMANCE_OUTPUT().send(MessageBuilder.withPayload(content.getBytes()).build());
			
//			if(!r){
//				log.error("系统性能日志发送队列消息失败！content={}",content);
//			}
		}catch(Exception e){
			log.error("性能日志记录 异常信息：", e);
		}
		
	}

}
