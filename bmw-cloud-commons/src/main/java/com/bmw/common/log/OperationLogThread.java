package com.bmw.common.log;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.integration.support.MessageBuilder;

import com.alibaba.fastjson.JSONObject;
import com.bmw.common.model.OperationLog;
//import com.bmw.common.mq.KafkaBaseSource;
import com.bmw.common.utils.SpringContextUtils;

/**
 * 操作日志
 * @author lyl
 * 2018年6月12日
 */
public class OperationLogThread implements Runnable{
	
	private Logger log = LoggerFactory.getLogger(OperationLogThread.class);
	
//	private KafkaBaseSource kafkaSource;
	
	private OperationLog operLog;
	
	
//	public OperationLogThread(OperationLog operLog) {
//		this.operLog = operLog;
//		kafkaSource = SpringContextUtils.getBean(KafkaBaseSource.class);
//	}
	

	@Override
	public void run() {
		// 发送到消息队列中
		try{
			String content = JSONObject.toJSONString(operLog);
			
//			boolean r = kafkaSource.SYS_LOG_OPERATION_OUTPUT().send(MessageBuilder.withPayload(content.getBytes()).build());

//			if(!r){
//				log.error("系统功能日志发送队列消息失败！content={}",content);
//			}
		}catch(Exception e){
			log.error("操作日志记录 异常信息：", e);
		}
		
	}


}
