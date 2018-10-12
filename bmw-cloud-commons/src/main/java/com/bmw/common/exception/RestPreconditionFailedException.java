package com.bmw.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 请求参数不符合要求异常；状态码：412
 * @author lyl
 * 2016年11月8日
 */
public class RestPreconditionFailedException extends RestRuntimeException{
	
	static final long serialVersionUID = -7034897190745766939L;
	
	
	public RestPreconditionFailedException(String msg){
		super(msg,HttpStatus.PRECONDITION_FAILED); // 412码
	}
	
}
