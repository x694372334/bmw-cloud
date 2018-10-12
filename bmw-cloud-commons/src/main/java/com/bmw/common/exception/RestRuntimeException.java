package com.bmw.common.exception;

import org.springframework.http.HttpStatus;

/**
 * REST请求异常
 * @author lyl
 * 2016年11月8日
 */
public class RestRuntimeException extends RuntimeException{
	
	static final long serialVersionUID = -7034897190745766939L;
	
	/**
	 * 状态码
	 */
	private HttpStatus httpStatus = null;
	
	
	public RestRuntimeException(String msg,HttpStatus httpStatus){
		super(msg);
		this.httpStatus = httpStatus;
	}

	
	/**
	 * 获取异常状态码
	 * @author lyl
	 * 2016年11月8日
	 */
	public HttpStatus getHttpStatus(){
		return this.httpStatus;
	}
	
}
