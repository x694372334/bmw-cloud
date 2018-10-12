package com.bmw.zuul.filter;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.bmw.zuul.model.Result;
import com.bmw.zuul.security.AuthorizationConfig;
import com.bmw.zuul.utils.ResultUtils;

/**
 * 安全访问过滤器
 * @author lyl
 * 2017年3月1日
 */
public class PreSecurityFilter extends ZuulFilter {

	
	@Autowired
	private AuthorizationConfig authorizationConfig;

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request =  ctx.getRequest();
		HttpServletResponse response = ctx.getResponse();
		String requsetId = request.getHeader("bmw-RequestId");
		/*	Enumeration<String> headers=request.getHeaderNames();
		while(headers.hasMoreElements()) {
			System.out.println(headers.nextElement().toString());
			
		}
		System.out.println("requsetId-----------------"+requsetId);*/
		boolean flag = true; // 授权成功标识
		if(StringUtils.isNotBlank(requsetId)){
			if(authorizationConfig.validateAuth(requsetId)){
				flag = true;
			}else{
				flag = false;
			}
		}else{
			// 获取统一资源标识符
			String URI = request.getRequestURI();
			if(URI != null && URI.indexOf("bmw-open-modules")>-1){
				flag = true;
			}else{
				flag = false;
			}
		}
		
		if(!flag){
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			
			// 异常信息输出
//			Result r = ResultUtils.resultData(HttpStatus.UNAUTHORIZED, "No unauthorized access ! ", null);
			response.setContentType("application/json");
//			ctx.setResponseBody(JSON.toJSONString(r));
			ctx.setResponse(response);
		}
		
		return null;
	}
}
