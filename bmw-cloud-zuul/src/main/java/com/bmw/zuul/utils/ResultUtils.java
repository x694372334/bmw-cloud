package com.bmw.zuul.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.bmw.zuul.model.Result;


/**
 * 服务返回结果工具类
 * 
 * <P><b>
 * 成功状态码 </br>
 * 	GET:200 </br>
 * 	POST:201 </br>
 * 	PUT:204 </br>
 * 	DELETE:204 </br>
 * 	PATCH:204 </br>
 * </b></p>
 * 
 * @author lyl
 * 2016年10月25日
 */
public class ResultUtils {

	private static Logger log = LoggerFactory.getLogger(ResultUtils.class);
	
	
	/**
	 * GET请求数据结果
	 * @param message 消息内容
	 * @param data 数据项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result getMethodData(Object data){
		return resultData(HttpStatus.OK,"successful",data);
	}
	
	
	/**
	 * GET请求数据结果
	 * @param message 消息内容
	 * @param data 数据集合项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result getMethodData(List<?> data){
		return resultData(HttpStatus.OK,"successful",data);
	}
	
	
	
	/**
	 * POST请求数据结果
	 * @param message 消息内容
	 * @param data 数据项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result postMethodData(Object data){
		return resultData(HttpStatus.CREATED,"successful",data);
	}
	
	
	
	/**
	 * POST请求数据结果
	 * @param message 消息内容
	 * @param data 数据集合项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result postMethodData(List<?> data){
		return resultData(HttpStatus.CREATED,"successful",data);
	}
	
	
	/**
	 * PUT请求数据结果
	 * @param message 消息内容
	 * @param data 数据项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result putMethodData(Object data){
		return resultData(HttpStatus.NO_CONTENT,"successful",data);
	}
	
	
	
	/**
	 * PUT请求数据结果
	 * @param message 消息内容
	 * @param data 数据集合项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result putMethodData(List<?> data){
		return resultData(HttpStatus.NO_CONTENT,"successful",data);
	}
	
	
	/**
	 * DELETE请求数据结果
	 * @param message 消息内容
	 * @param data 数据项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result deleteMethodData(Object data){
		return resultData(HttpStatus.NO_CONTENT,"successful",data);
	}
	
	
	
	/**
	 * DELETE请求数据结果
	 * @param message 消息内容
	 * @param data 数据集合项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result deleteMethodData(List<?> data){
		return resultData(HttpStatus.NO_CONTENT,"successful",data);
	}
	
	
	
	
	/**
	 * PATCH请求数据结果
	 * @param message 消息内容
	 * @param data 数据项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result patchMethodData(Object data){
		return resultData(HttpStatus.NO_CONTENT,"successful",data);
	}
	
	
	
	/**
	 * PATCH请求数据结果
	 * @param message 消息内容
	 * @param data 数据集合项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result patchMethodData(List<?> data){
		return resultData(HttpStatus.NO_CONTENT,"successful",data);
	}
	
	
	
	
	
	/**
	 * 服务请求返回结果模型
	 * 
	 * @param httpStatus 状态码
	 * @param message 消息内容
	 * @param data 数据集合项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result resultData(HttpStatus httpStatus,String message,List<?> data){
		Result result = new Result();
		
		if(httpStatus == null){
			log.debug("HttpStatus状态码字段不能为空！");
			return null;
		}
		
		// 状态码
		result.setStatusCode(httpStatus.value()); 
		
		// 消息内容
		if(StringUtils.isBlank(message)){
			result.setMessage("");
		}else{
			result.setMessage(message);
		}
		
		// 数据项 分页项
		if(data != null){
//			if(data instanceof Page){
//				Page<?> page = (Page<?>)data;
//				result.setItems(page);
//				result.setTotalCount(page.getTotal());
//				result.setPageNumber(page.getPageNum());
//				result.setPageSize(page.getPageSize());
//			}else{
				result.setItems(data);
//			}
			
		}
		
		return result;
	}
	
	
	/**
	 * 服务请求返回结果模型
	 * 
	 * @param httpStatus 状态码
	 * @param message 消息内容
	 * @param data 数据项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result resultData(HttpStatus httpStatus,String message,Object data){
		Result result = new Result();
		
		if(httpStatus == null){
			log.debug("HttpStatus状态码字段不能为空！");
			return null;
		}
		
		// 状态码
		result.setStatusCode(httpStatus.value()); 
		
		// 消息内容
		if(StringUtils.isBlank(message)){
			result.setMessage("");
		}else{
			result.setMessage(message);
		}
		
		// 数据项 分页项
		if(data != null){
			result.setItems(data);
		}
		
		return result;
	}
	
	
	
}
