package com.bmw.common.exception;

import org.springframework.http.HttpStatus;

import com.bmw.common.model.Result;

/**
 * 根据Result结果抛出异常
 * @author lyl
 * 2017年5月2日
 */
public class RestResultException extends RestRuntimeException{

	static final long serialVersionUID = -7034897190745766939L;
	
	
	public RestResultException(Result result){
		super(result.getMessage(),HttpStatus.valueOf(result.getStatusCode()));
	}
}
