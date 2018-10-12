package com.bmw.zuul.filter;

import org.springframework.beans.factory.annotation.Autowired;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.bmw.zuul.record.RequestCloudService;

/**
 * 网关访问前置过滤器
 * @author lyl
 * 2016年10月14日
 */
public class PreAccessFilter extends ZuulFilter {
	
	

	@Autowired
	private RequestCloudService requestCloudService;
	
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		requestCloudService.requestRecordDispose(ctx); // 访问记录
		return null;
	}
}