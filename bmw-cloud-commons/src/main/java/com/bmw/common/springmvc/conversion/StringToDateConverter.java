package com.bmw.common.springmvc.conversion;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

/**
 * 字符串转换Date
 * @author lyl
 * 2016年11月7日
 */
public class StringToDateConverter implements Converter<String, Date>{
	
	private Logger log = LoggerFactory.getLogger(StringToDateConverter.class);
	
	@Override
	public Date convert(String source) {
		String pattern = "";

		if(StringUtils.isBlank(source)){
			return null;
		}else{
			if(source.trim().length()==10){
				pattern = "yyyy-MM-dd";
			}else if(source.trim().length() > 10){
				pattern = "yyyy-MM-dd HH:mm:ss";
			}
		}
		
		Date date = null;  
        //注意format的格式要与日期String的格式相匹配  
        DateFormat sdf = new SimpleDateFormat(pattern);
		try {
			date = sdf.parse(source);
		} catch (ParseException e) {
			log.error("请求参数不符合标准,该参数类型为日期类型； 错误参数值："+source,0);
		}
        return date;
	}
	

}
