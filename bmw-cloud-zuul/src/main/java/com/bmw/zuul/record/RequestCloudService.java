package com.bmw.zuul.record;

import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.context.RequestContext;
//import com.bmw.zuul.mq.KafkaRequestCloudSource;
import com.bmw.zuul.security.AuthorizationConfig;
import com.bmw.zuul.utils.CommonUtils;

/**
 * 微服务访问记录
 * @author lyl
 * 2018年6月12日
 */
@Service
@SuppressWarnings("deprecation")
public class RequestCloudService {
//
//	@Autowired
//	private KafkaRequestCloudSource kafkaRequestCloudSource;

	@Autowired
	private AuthorizationConfig authorizationConfig;



	public void requestRecordDispose(RequestContext ctx){
		HttpServletRequest request =  ctx.getRequest();

		String method = request.getMethod();
		String requestURI = request.getRequestURI();
		String requestURL = request.getRequestURL().toString();
		String queryString = request.getQueryString();
		String authCode = request.getHeader("TL-RequestId");

		// 不记录URL
		if(requestURI.equals("/bmw-base/tool/request/record/send")){
			return;
		}


		// data form 中参数
		StringBuilder dataFormParams = new StringBuilder();
		Map<String,String[]> params = request.getParameterMap();
		if(!params.isEmpty()){
			for(Entry<String, String[]> m : params.entrySet()){
				dataFormParams.append(m.getKey());
				dataFormParams.append("=");

				String[] temp = m.getValue();
				for(int i=0;i<temp.length;i++){
					if(i == 0){
						dataFormParams.append(temp[i]);
					}else{
						dataFormParams.append(",");
						dataFormParams.append(temp[i]);
					}
				}
				dataFormParams.append("&");
			}
		}


		if(StringUtils.isNotBlank(queryString)){
			queryString = URLDecoder.decode(queryString);
		}else{
			queryString = "";
		}

		// 参数组合
		String requestParma = "";
		if(StringUtils.isBlank(dataFormParams.toString())){
			requestParma = queryString;
		}else{
			requestParma = queryString + "\tData form parms: " + dataFormParams.toString();
		}


		String requestName = ""; // 访问的项目名称，微服务项目特有
		int index = requestURI.indexOf("/",1);
		if(index > 1){
			requestName = requestURI.substring(1, index);
		}

		RequestCloudRecord record = new RequestCloudRecord();
		record.setRequestMethod(method);
		record.setRequestName(requestName);
		record.setRequestProjectIdent(authorizationConfig.authUser.get(authCode));
		record.setRequestProjectCode(authCode);
		record.setRequestUri(requestURI);
		record.setRequestUrl(requestURL);
		record.setRequestParam(requestParma);
		record.setRequestIp(CommonUtils.getOriginIP(request));
		record.setRequestDate(new Timestamp(System.currentTimeMillis()));


		// 发送日志到mq
		String content = JSONObject.toJSONString(record);
//		kafkaRequestCloudSource.REQUEST_RECORD_MESSAGE_OUTPUT()
//			.send(MessageBuilder.withPayload(content.getBytes()).build());

		record = null;
	}


}
