package com.bmw.common.exception;

import org.springframework.http.HttpStatus;

/**
 * 冲突异常，用于说明请求可能在资源上引发一些冲突； 状态码：409
 * @author lyl
 * 2016年11月10日
 */
public class RestConflictException extends RestRuntimeException{

	static final long serialVersionUID = -7034897190745766939L;
	
	
	public RestConflictException(String msg){
		super(msg,HttpStatus.CONFLICT); // 409码
	}
}
