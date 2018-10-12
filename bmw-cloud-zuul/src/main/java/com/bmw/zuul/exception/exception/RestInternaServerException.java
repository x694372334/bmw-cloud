package com.bmw.zuul.exception.exception;

import org.springframework.http.HttpStatus;

/**
 * 内部服务错误异常；状态码：500
 * @author lyl
 * 2016年11月8日
 */
public class RestInternaServerException extends RestRuntimeException{

	static final long serialVersionUID = -7034897190745766939L;
	
	
	public RestInternaServerException(String msg){
		super(msg,HttpStatus.INTERNAL_SERVER_ERROR); // 500码
	}
	
	
}