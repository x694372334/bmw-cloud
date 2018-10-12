package com.bmw.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 未实现异常，服务器无法满足客户端请求的某个功能；状态码：501
 * @author lyl
 * 2016年11月10日
 */
public class RestNotImplementedException extends RestRuntimeException{

	static final long serialVersionUID = -7034897190745766939L;
	
	
	public RestNotImplementedException(String msg){
		super(msg,HttpStatus.NOT_IMPLEMENTED); // 501码
	}
}

