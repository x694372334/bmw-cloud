package com.bmw.common.autoconfigure;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.bmw.common.springmvc.conversion.StringToDateConverter;
import com.bmw.common.springmvc.conversion.StringToTimestampConverter;



/**
 * Spring MVC 配置
 * @author lyl
 * 2016年11月7日
 */
@Configuration
public class WebConfiguration {
	
	@Autowired
	private RequestMappingHandlerAdapter handlerAdapter;
	
	
	/**
	 * 数据转换
	 * @author lyl
	 * 2016年11月7日
	 */
	@PostConstruct
	public void initEditableValidation() {
		ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter
				.getWebBindingInitializer();
		if (initializer.getConversionService() != null) {
			GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();
			genericConversionService.addConverter(new StringToDateConverter()); // Date类型转换
			genericConversionService.addConverter(new StringToTimestampConverter()); // Timestamp类型转换
		}

	}
}
