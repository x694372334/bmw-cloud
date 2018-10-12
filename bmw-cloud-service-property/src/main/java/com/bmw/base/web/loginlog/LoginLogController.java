package com.bmw.base.web.loginlog;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.bmw.base.module.loginlog.domain.LoginLog;
import com.bmw.base.module.loginlog.service.LoginLogService;
import com.bmw.common.model.Result;
import com.bmw.common.utils.ResultUtils;

@RestController
@RequestMapping("base/loginlog")
public class LoginLogController {
	
	@Autowired
	private LoginLogService loginLogService;
	
	
	/**    
	* <p>Description: 带分页查询的登录日志列表</p>  
	* @author lyl  
	* @date 2018年4月27日  
	*/  
	@RequestMapping(value="getloginLogs/{jsonParams}",method=RequestMethod.GET)
	public Result getloginLogs(@PathVariable("jsonParams") String jsonParams){
		JSONObject jsonrlt =new JSONObject();
		try {
			String params=new String(Base64Utils.decode(jsonParams.getBytes("UTF-8")));
			JSONObject jsonobj=JSONObject.parseObject(params);
			Integer pageNum		=jsonobj.getInteger("pageNum");
			Integer pageSize	=jsonobj.getInteger("pageSize");
			String  beginTime	=jsonobj.getString("beginTime");
			String  endTime		=jsonobj.getString("endTime");
			String  logName		=jsonobj.getString("logName");
			String  orderByField=jsonobj.getString("orderByField");
			Boolean isAsc		=jsonobj.getBoolean("asc");
			Page page = PageHelper.startPage(pageNum, pageSize);
			List<Map<String, Object>> list=loginLogService.getLoginLogs(beginTime, endTime, logName, orderByField, isAsc);
			jsonrlt.put("result", list);
			jsonrlt.put("total", page.getTotal());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResultUtils.getMethodData(jsonrlt);
	}
	
	

	/**    
	* <p>Description: 清空登录日志</p>  
	* @author lyl  
	* @date 2018年4月27日  
	*/  
	@RequestMapping(value="del",method=RequestMethod.DELETE)
	public Result del(){
		loginLogService.deleteAll();
		return ResultUtils.getMethodData("清空操作日志成功");
	}
	
	
	/**    
	* <p>Description: 添加登录日志</p>  
	* @author lyl  
	* @date 2018年4月27日  
	*/  
	@RequestMapping(value="add",method=RequestMethod.POST)
	public Object add(LoginLog log){
		loginLogService.add(log);
		return ResultUtils.getMethodData("添加操作日志成功");
	}
	
}
