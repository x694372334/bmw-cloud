package com.bmw.zuul.filter;



import org.springframework.http.HttpStatus;
import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * 错误过滤器
 * @author lyl
 * 2016年11月25日
 */
public class ErrorFilter extends ZuulFilter {

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
		return "error";
	}

	/**
	 * filter执行顺序，通过数字指定 
	 * @author lyl
	 * 2016年11月25日
	 */
	@Override
	public int filterOrder() {
		return 0;
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
		RequestContext ctx = RequestContext.getCurrentContext();
		
		JSONObject json = new JSONObject();
		json.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
		json.put("message", "Zuul服务内部出现错误，请联系开发人员！");
		
		ctx.setSendZuulResponse(false); // 令zuul过滤该请求，不对其进行路由
		ctx.getResponse().setContentType("application/json");
		ctx.setResponseBody(json.toJSONString());
		ctx.setResponseStatusCode(500);
		
		
		return null;
	}

}
