package com.bmw.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 未授权异常；状态码：401
 * @author lyl
 * 2016年11月8日
 */
public class RestUnauthorizedException extends RestRuntimeException{
	
	static final long serialVersionUID = -7034897190745766939L;
	
	
	public RestUnauthorizedException(String msg){
		super(msg,HttpStatus.UNAUTHORIZED); // 401码
	}
	
}
