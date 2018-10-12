package com.bmw.common.utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.github.pagehelper.Page;
import com.bmw.common.model.PowerfulModel;
import com.bmw.common.model.Result;


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
	 * 返回异常错误信息
	 * @param 	
	 * 
	 * msg 具体异常消息
	 * 
	 * @author jiangying
	 * 2017年2月15日
	 */
	public static Result errorMethodData(HttpStatus status,String msg){
		return resultData(status,msg,null,null,null);
	}
	
	/**
	 * GET请求数据结果
	 * @param data 数据项
	 * 
	 * @author jiangying
	 * 2016年11月8日
	 */
	public static Result getMethodData(Object data){
		return resultData(HttpStatus.OK,"successful",data,null,null);
	}
	
	
	/**
	 * GET请求数据结果
	 * @param keyOrValue 数据集合项目。 可变数据类型，代表[键]或[值]含义，如：key,value,key,value，最终转换后的为2组JSON数据。如果参数为奇数省略最后一组JSON
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result getMethodData(String... keyOrValue){
		return resultData(HttpStatus.OK,"successful",convertKayValueData(keyOrValue),null,null);
	}
	
	
	/**
	 * GET请求数据结果
	 * @param data 数据项
	 * @param includeFields items内容集合中显示的字段项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result getMethodDataIncludeField(Object data,List<String> includeFields){
		return resultData(HttpStatus.OK,"successful",data,includeFields,null);
	}
	
	
	/**
	 * GET请求数据结果
	 * @param data 数据项
	 * @param excludeFields items内容集合中排除显示的字段项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result getMethodDataExcludeField(Object data,List<String> excludeFields){
		return resultData(HttpStatus.OK,"successful",data,null,excludeFields);
	}
	
	
	/**
	 * GET请求数据结果
	 * @param data 数据项
	 * @param excludeFields 可变参数，items内容集合中排除显示的字段项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result getMethodDataExcludeField(Object data,String... excludeFields){
		List<String> excludeLs = null;
		if(excludeFields != null){
			excludeLs = new ArrayList<String>();
			Collections.addAll(excludeLs, excludeFields);
		}
		return resultData(HttpStatus.OK,"successful",data,null,excludeLs);
	}
	
	
	
	
	
	
	
	
	
	/**
	 * POST请求数据结果
	 * @param data 数据项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result postMethodData(Object data){
		return resultData(HttpStatus.CREATED,"successful",data,null,null);
	}
	
	
	/**
	 * POST请求数据结果
	 * @param keyOrValue 数据集合项目。 可变数据类型，代表[键]或[值]含义，如：key,value,key,value，最终转换后的为2组JSON数据。如果参数为奇数省略最后一组JSON
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result postMethodData(String... keyOrValue){
		return resultData(HttpStatus.CREATED,"successful",convertKayValueData(keyOrValue),null,null);
	}
	
	
	/**
	 * POST请求数据结果
	 * @param data 数据项
	 * @param includeFields items内容集合中显示的字段项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result postMethodDataIncludeField(Object data,List<String> includeFields){
		return resultData(HttpStatus.CREATED,"successful",data,includeFields,null);
	}
	
	
	/**
	 * POST请求数据结果
	 * @param data 数据项
	 * @param excludeFields items内容集合中排除显示的字段项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result postMethodDataExcludeField(Object data,List<String> excludeFields){
		return resultData(HttpStatus.CREATED,"successful",data,null,excludeFields);
	}
	
	
	/**
	 * POST请求数据结果
	 * @param data 数据项
	 * @param excludeFields 可变参数，items内容集合中排除显示的字段项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result postMethodDataExcludeField(Object data,String... excludeFields){
		List<String> excludeLs = null;
		if(excludeFields != null){
			excludeLs = new ArrayList<String>();
			Collections.addAll(excludeLs, excludeFields);
		}
		return resultData(HttpStatus.CREATED,"successful",data,null,excludeLs);
	}
	
	
	
	
	
	
	
	
	/**
	 * PUT请求数据结果
	 * @param data 数据项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result putMethodData(Object data){
		return resultData(HttpStatus.NO_CONTENT,"successful",data,null,null);
	}
	
	
	/**
	 * PUT请求数据结果
	 * @param keyOrValue 数据集合项目。 可变数据类型，代表[键]或[值]含义，如：key,value,key,value，最终转换后的为2组JSON数据。如果参数为奇数省略最后一组JSON
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result putMethodData(String... keyOrValue){
		return resultData(HttpStatus.NO_CONTENT,"successful",convertKayValueData(keyOrValue),null,null);
	}
	
	
	/**
	 * PUT请求数据结果
	 * @param data 数据项
	 * @param includeFields items内容集合中显示的字段项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result putMethodDataIncludeField(Object data,List<String> includeFields){
		return resultData(HttpStatus.NO_CONTENT,"successful",data,includeFields,null);
	}
	
	/**
	 * PUT请求数据结果
	 * @param data 数据项
	 * @param excludeFields items内容集合中排除显示的字段项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result putMethodDataExcludeField(Object data,List<String> excludeFields){
		return resultData(HttpStatus.NO_CONTENT,"successful",data,null,excludeFields);
	}
	
	
	/**
	 * PUT请求数据结果
	 * @param data 数据项
	 * @param excludeFields 可变参数，items内容集合中排除显示的字段项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result putMethodDataExcludeField(Object data,String... excludeFields){
		List<String> excludeLs = null;
		if(excludeFields != null){
			excludeLs = new ArrayList<String>();
			Collections.addAll(excludeLs, excludeFields);
		}
		return resultData(HttpStatus.NO_CONTENT,"successful",data,null,excludeLs);
	}
	
	
	
	
	
	
	
	
	/**
	 * DELETE请求数据结果
	 * @param data 数据项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result deleteMethodData(Object data){
		return resultData(HttpStatus.NO_CONTENT,"successful",data,null,null);
	}
	
	
	/**
	 * DELETE请求数据结果
	 * @param keyOrValue 数据集合项目。 可变数据类型，代表[键]或[值]含义，如：key,value,key,value，最终转换后的为2组JSON数据。如果参数为奇数省略最后一组JSON
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result deleteMethodData(String... keyOrValue){
		return resultData(HttpStatus.NO_CONTENT,"successful",convertKayValueData(keyOrValue),null,null);
	}
	
	
	/**
	 * DELETE请求数据结果
	 * @param data 数据项
	 * @param includeFields items内容集合中显示的字段项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result deleteMethodDataIncludeField(Object data,List<String> includeFields){
		return resultData(HttpStatus.NO_CONTENT,"successful",data,includeFields,null);
	}
	
	/**
	 * DELETE请求数据结果
	 * @param data 数据项
	 * @param excludeFields items内容集合中排除显示的字段项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result deleteMethodDataExcludeField(Object data,List<String> excludeFields){
		return resultData(HttpStatus.NO_CONTENT,"successful",data,null,excludeFields);
	}
	
	/**
	 * DELETE请求数据结果
	 * @param data 数据项
	 * @param excludeFields 可变参数，items内容集合中排除显示的字段项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result deleteMethodDataExcludeField(Object data,String... excludeFields){
		List<String> excludeLs = null;
		if(excludeFields != null){
			excludeLs = new ArrayList<String>();
			Collections.addAll(excludeLs, excludeFields);
		}
		return resultData(HttpStatus.NO_CONTENT,"successful",data,null,excludeLs);
	}
	
	
	
	
	
	
	
	
	
	/**
	 * PATCH请求数据结果
	 * @param data 数据项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result patchMethodData(Object data){
		return resultData(HttpStatus.NO_CONTENT,"successful",data,null,null);
	}
	
	
	/**
	 * PATCH请求数据结果
	 * @param keyOrValue 数据集合项目。 可变数据类型，代表[键]或[值]含义，如：key,value,key,value，最终转换后的为2组JSON数据。如果参数为奇数省略最后一组JSON
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result patchMethodData(String... keyOrValue){
		return resultData(HttpStatus.NO_CONTENT,"successful",convertKayValueData(keyOrValue),null,null);
	}
	
	/**
	 * PATCH请求数据结果
	 * @param data 数据项
	 * @param includeFields items内容集合中显示的字段项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result patchMethodDataIncludeField(Object data,List<String> includeFields){
		return resultData(HttpStatus.NO_CONTENT,"successful",data,includeFields,null);
	}
	
	/**
	 * PATCH请求数据结果
	 * @param data 数据项
	 * @param excludeFields items内容集合中排除显示的字段项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result patchMethodDataExcludeField(Object data,List<String> excludeFields){
		return resultData(HttpStatus.NO_CONTENT,"successful",data,null,excludeFields);
	}
	
	/**
	 * PATCH请求数据结果
	 * @param data 数据项
	 * @param excludeFields 可变参数，items内容集合中排除显示的字段项
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result patchMethodDataExcludeField(Object data,String... excludeFields){
		List<String> excludeLs = null;
		if(excludeFields != null){
			excludeLs = new ArrayList<String>();
			Collections.addAll(excludeLs, excludeFields);
		}
		return resultData(HttpStatus.NO_CONTENT,"successful",data,null,excludeLs);
	}
	
	
	
	
	
	
	/**
	 * 服务请求返回结果模型
	 * 
	 * @param httpStatus 状态码
	 * @param message 消息内容
	 * @param data 数据集合项
	 * @param includeField 确定要包含的属性
	 * @param excludeField 要排除的属性
	 * 
	 * @author lyl
	 * 2016年11月8日
	 */
	public static Result resultData(HttpStatus httpStatus,String message,Object data,List<String> includeField,List<String> excludeField){
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
			if(data instanceof Page){
				Page<?> page = (Page<?>)data;
				result.setItems(page);
				result.setTotalCount(page.getTotal());
				result.setPageNumber(page.getPageNum());
				result.setPageSize(page.getPageSize());
			}else{
				result.setItems(data);
			}
			
		}
		
		// 确定包含的字段
		if(CollectionUtils.isNotEmpty(includeField)){
			result.setIncludeField(includeField);
		}
		
		// 要排除的字段
		if(CollectionUtils.isNotEmpty(excludeField)){
			result.setExcludeField(excludeField);
		}
		
		return result;
	}
	

	
	
	
	
	/**
	 * 转换为Map数据
	 * @param keyOrValue 为可变数据类型，代表[键]或[值]含义，如：key,value,key,value，最终转换后的为2组Map数据。如果参数为奇数省略最后一组Map
	 * 
	 * @return 转换后的Map数据
	 * @author lyl
	 * 2016年12月14日
	 */
	public static Map<String,String> convertKayValueData(String... keyOrValue){
		Map<String,String> data = null;

		if(ArrayUtils.isNotEmpty(keyOrValue)){
			data = new HashMap<String,String>();
			String key = "";
			String value = "";
			boolean flag = true;
			for(String v : keyOrValue){
				if(flag){
					key = v;
					flag = false;
				}else{
					value = v;
					data.put(key, value);
					flag = true;
				}
			}
		}
		
		return data;
	}
	
	
	
	
	
	

	/**
	 * 获取Resul中items中数据
	 * @param result Result对象
	 * 
	 * @return 以PowerfulModel对象形式返回items中数据,如果没找到返回NULL
	 * @author lyl
	 * 2016年12月14日
	 */
	public static PowerfulModel getResultItemsData(Result result){
		PowerfulModel power = null;
		int code = result.getStatusCode();
		if(code == 200 || code == 201 || code == 204){ // 成功请求
			Object i = result.getItems();
			if(i != null){
				power = (PowerfulModel) PowerfulModel.toPowerful(i);
			}
		}
		
		return power;
	}
	
	
	/**
	 * 获取Resul中items集合中数据
	 * 
	 * @return 以PowerfulModel对象形式返回items中数据,如果没找到返回NULL
	 * @author lyl
	 * 2017年2月22日
	 */
	@SuppressWarnings("unchecked")
	public static List<PowerfulModel> getResultItemsArrayData(Result result){
		List<PowerfulModel> powerLs = null;
		int code = result.getStatusCode();
		if(code == 200 || code == 201 || code == 204){ // 成功请求
			Object i = result.getItems();
			if(i != null){
				powerLs = (List<PowerfulModel>) PowerfulModel.toPowerful(i);
			}
		}
		
		return powerLs;
	}
	
	
	
	
	/**
	 * 获取Result类Items中数据结果，只能获取一组结果数据中的value值
	 * 
	 * @param result Result对象
	 * @param obj 将获取的数据转换成的数据类型
	 * 
	 * @return 转换后的数据结果，如果没找到返回NULL
	 * 
	 * @author lyl
	 * 2016年12月14日
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getResultItemsData(Result result,Class<T> obj){
		PowerfulModel itemJson = getResultItemsData(result);
		if(itemJson != null && !itemJson.isEmpty()){
			for(Entry<String, Object> i : itemJson.entrySet()){
				if(obj.getName().equals(String.class.getName())){ // String类型
					return (T) String.valueOf(i.getValue());
				}else if(obj.getName().equals(Double.class.getName())){ // Double类型
					return (T) Double.valueOf(i.getValue().toString());
				}else if(obj.getName().equals(Integer.class.getName())){ // Integer类型
					return (T) Integer.valueOf(i.getValue().toString());
				}else if(obj.getName().equals(Date.class.getName())){ // Date类型
					return (T) DateUtil.getDateByStr(i.getValue().toString(), "yyyy-MM-dd");
				}else if(obj.getName().equals(Timestamp.class.getName())){ // Timestamp类型
					return (T) DateUtil.getTimestamp(i.getValue().toString(), "yyyy-MM-dd HH:mm:ss");
				}
				break;
			}
		}
		
		return null;
	}
	
}
