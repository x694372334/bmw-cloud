package com.bmw.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 未提供此服务异常；状态码：503
 * @author lyl
 * 2016年11月10日
 */
public class RestServiceUnavailableException extends RestRuntimeException{

	static final long serialVersionUID = -7034897190745766939L;
	
	
	public RestServiceUnavailableException(String msg){
		super(msg,HttpStatus.SERVICE_UNAVAILABLE); // 503码
	}
}