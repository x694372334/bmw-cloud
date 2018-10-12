package com.bmw.common.springmvc.conversion;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map.Entry;


import org.apache.commons.collections.CollectionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.bmw.common.model.PowerfulModel;
import com.bmw.common.model.Result;

/**
 * JSON输出处理
 * @author lyl
 * 2016年11月7日
 */
public class JsonHttpMessageConverter extends FastJsonHttpMessageConverter{

	private FastJsonConfig fastJsonConfig = new FastJsonConfig(); 
	
	
	@Override
	protected void writeInternal(Object obj, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
        HttpHeaders headers = outputMessage.getHeaders();
        ByteArrayOutputStream outnew = new ByteArrayOutputStream();
        
        Result r = null;
        if(obj instanceof Result){
        	r = (Result)obj; // rest返回结果类
        	// items 字段处理
        	Object items = r.getItems(); 
        	if(items instanceof PowerfulModel){
        		PowerfulModel pw = (PowerfulModel)items;
        		List<String> includeField = r.getIncludeField();
        		if(CollectionUtils.isNotEmpty(includeField)){
        			int pwSize = pw.size();
            		int includeFieldSize = includeField.size();
            		// 展示字段大于原数据中的字段，把展示字段添加到源数据中并赋值为空。
            		if(includeFieldSize > pwSize){
            			String field = null;
            			String key = null;
            			boolean flag = false; // 在源数据重是否存在该字段， true:存在
        				for(int i=0;i<includeFieldSize;i++){
        					field = includeField.get(i);
        					flag = false;
        					for(Entry<String, Object> m : pw.entrySet()){
                				key = m.getKey();
            					if(field.equals(key)){
            						flag = true;
            						break;
            					}
            				}
        					if(!flag){
        						pw.put(field, "");
        					}
            			}
            		}
            		
            		((Result)obj).setItems(pw);
        		}
        	}
        }else{
        	r = new Result();
        }
        
        
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss"); // 格式化日期数据
        fastJsonConfig.setSerializeFilters(getValueFilterRules(r.getIncludeField(),r.getExcludeField())); // 自定义filter规则
        
        int len = JSON.writeJSONString(outnew, //
                                       fastJsonConfig.getCharset(), //
                                       obj, //
                                       fastJsonConfig.getSerializeConfig(), //
                                       fastJsonConfig.getSerializeFilters(), //
                                       fastJsonConfig.getDateFormat(), //
                                       JSON.DEFAULT_GENERATE_FEATURE, //
                                       fastJsonConfig.getSerializerFeatures());
        headers.setContentLength(len);
        OutputStream out = outputMessage.getBody();
        outnew.writeTo(out);
        outnew.close();
    }
	
	
	
	/**
	 * 自定义Filter规则
	 * @author lyl
	 * 2016年12月14日
	 */
	private ValueFilter getValueFilterRules(final List<String> includeFields,final List<String> excludeFields){
		ValueFilter filter = new ValueFilter() {
			@Override
			public Object process(Object object, String name, Object value) {
				if(name.equals("statusCode")){
					return value;
				}else if(name.equals("message")){
					return value;
				}else if(name.equals("items")){
					if(value == ""){
						return null;
					}else{
						return value;
					}
				}else if(name.equals("totalCount")){
					if(value != null && value != ""){
						return value;
					}else{
						return null;
					}
				}else if(name.equals("pageNumber")){
					if(value != null && value != ""){
						return value;
					}else{
						return null;
					}
				}else if(name.equals("pageSize")){
					if(value != null && value != ""){
						return value;
					}else{
						return null;
					}
				}else if(name.equals("includeField")){
					return null;
				}else if(name.equals("excludeField")){
					return null;
				}else{
					if(CollectionUtils.isNotEmpty(includeFields)){ // 显示出去的字段
						for(String k : includeFields){
							if(k.equals(name)){
								if(value == null){
									return "";
								}else{
									return value;
								}
							}
						}
						return null;
					}else if(CollectionUtils.isNotEmpty(excludeFields)){ // 排除掉的字段
						for(String k : excludeFields){
							if(k.equals(name)){
								return null;
							}
						}
					}
					
					
					if(value == null){
						return "";
					}else{
						return value;
					}
				}
				
			}
		};
		
		return filter;
	}
	
}
