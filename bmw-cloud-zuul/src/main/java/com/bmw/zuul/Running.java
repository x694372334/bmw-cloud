package com.bmw.zuul;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
//import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bmw.zuul.convert.JsonHttpMessageConverter;
import com.bmw.zuul.filter.ErrorFilter;
import com.bmw.zuul.filter.PostResultFilter;
import com.bmw.zuul.filter.PreAccessFilter;
import com.bmw.zuul.filter.PreSecurityFilter;
//import com.bmw.zuul.globalHandler.GlobalErrorController;
//import com.bmw.zuul.globalHandler.GlobalExceptionHandler;
//import com.bmw.zuul.mq.KafkaRequestCloudSource;
import com.bmw.zuul.utils.SpringContextUtils;


@EnableZuulProxy
@SpringCloudApplication
@RestController
//@EnableBinding(KafkaRequestCloudSource.class)
public class Running {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(Running.class).web(true).run(args);
	}
	
	
	/**
	 * 首页设置
	 * @author lyl
	 * 2016年11月1日
	 */
	@RequestMapping("/")
	public String index(){
		return "Hello bmw cloud service zuul !";
	}
	
	
	/**
	 * 安全访问过滤器
	 * @author lyl
	 * 2017年3月1日
	 */
	@Bean
	public PreSecurityFilter preSecurityFilter(){
		return new PreSecurityFilter();
	}
	
	
	/**
	 * 访问过滤器
	 * @author lyl
	 * 2016年11月22日
	 */
	@Bean
	public PreAccessFilter accessFilter() {
		return new PreAccessFilter();
	}
	
	/**
	 * 执行后过滤器
	 * @author lyl
	 * 2017年1月22日
	 */
	@Bean
	public PostResultFilter postResultFilter(){
		return new PostResultFilter();
	}
	
	
	/**
	 * 错误过滤器
	 * @author lyl
	 * 2016年11月22日
	 */
	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
	
	
	

	/**
	 * 服务错误处理初始化
	 * @author lyl
	 * 2016年11月1日
	 */
//	@Bean
//	@ConditionalOnMissingBean
//	public GlobalErrorController globalErrorController(){
//		return new GlobalErrorController();
//	}
	
	/**
	 * 服务异常处理初始化
	 * @author lyl
	 * 2016年11月1日
	 */
//	@Bean
//	@ConditionalOnMissingBean
//	public GlobalExceptionHandler globalExceptionHandler(){
//		return new GlobalExceptionHandler();
//	}
	
	
	/**
	 * JSON转换
	 * @author lyl
	 * 2016年11月7日
	 */
	@Bean
	@ConditionalOnMissingBean
	public JsonHttpMessageConverter jsonHttpMessageConverter(){
		return new JsonHttpMessageConverter();
	}
	
	

	/**
	 * SpringContextUtils初始化
	 * @author lyl
	 * 2016年11月1日
	 */
	@Bean
	@ConditionalOnMissingBean
	public SpringContextUtils springContextUtils(){
		return new SpringContextUtils();
	}
	
}
