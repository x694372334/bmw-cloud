package com.bmw.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 未找到服务资源异常；状态码：404
 * 
 * @author lyl
 * 2016年11月8日
 */
public class RestNotFoundException extends RestRuntimeException{

	static final long serialVersionUID = -7034897190745766939L;
	
	
	public RestNotFoundException(String msg){
		super(msg,HttpStatus.NOT_FOUND); // 404码
	}
	
}
