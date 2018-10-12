package com.bmw.zuul.filter;

//import com.esotericsoftware.minlog.Log;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 执行后数据处理
 * @author lyl
 * 2016年11月25日
 */
@SuppressWarnings("unused")
public class PostResultFilter  extends ZuulFilter{
	
	
	
	/**
	 * pre:请求执行之前filter、
	 * route: 处理请求，进行路由 、
	 * post: 请求处理完成后执行的filter 、
	 * error:出现错误时执行的filter
	 * 
	 * @author lyl
	 * 2016年11月25日
	 */
	@Override
	public String filterType() {
		return "post";
	}

	/**
	 * filter执行顺序，通过数字指定 
	 * @author lyl
	 * 2016年11月25日
	 */
	@Override
	public int filterOrder() {
		return 1;
	}

	/**
	 * filter是否需要执行 true：执行  false：不执行
	 * @author lyl
	 * 2016年11月25日
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * filter具体逻辑
	 * @author lyl
	 * 2016年11月25日
	 */
	
	@Override
	public Object run() {
//		Log.debug("post——error························································");
		RequestContext ctx = RequestContext.getCurrentContext();
		return null;
	}
	
}
