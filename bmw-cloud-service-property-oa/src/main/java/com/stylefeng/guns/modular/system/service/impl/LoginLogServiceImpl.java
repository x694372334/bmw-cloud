package com.stylefeng.guns.modular.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.modular.system.model.LoginLog;
import com.stylefeng.guns.modular.system.model.OperationLog;
import com.stylefeng.guns.modular.system.service.ILoginLogService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

/**
 * <p>
 * 登录记录 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-02-22
 */
@Service
public class LoginLogServiceImpl implements ILoginLogService {
	@Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_baseservice_url="";
	
	@Value("${loginlog.getLoginLogs}")
	private String loginlog_getLoginLogs="";
	
	@Value("${loginlog.del}")
	private String loginlog_del="";
	
	@Value("${loginlog.add}")
	private String loginlog_add="";
	
    @Override
    public JSONObject getLoginLogs(Page<OperationLog> page, String beginTime, String endTime, String logName, String orderByField, boolean asc) {
    	int pageSize=page.getSize();
    	int pageNum=page.getCurrent();
    	JSONObject paraJson=new JSONObject();
    	paraJson.put("beginTime", beginTime!=null?beginTime:"");
    	paraJson.put("endTime", endTime!=null?endTime:"");
    	paraJson.put("logName", logName!=null?logName:"");
    	paraJson.put("orderByField", orderByField!=null?orderByField:"");
    	paraJson.put("asc", asc);
    	paraJson.put("pageSize", pageSize);
    	paraJson.put("pageNum", pageNum);
    	JSONObject jsonrlt=new JSONObject();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+loginlog_getLoginLogs+new String(Base64Utils.encode(paraJson.toString().getBytes("UTF-8"))));
			jsonrlt=JSONObject.parseObject(rlt).getJSONObject("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	return jsonrlt;
    }
    
    @Override
    public void del() { 
    	try {
			HttpUtils.doDelete(bmw_cloud_baseservice_url+loginlog_del);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
	 * 添加操作日志
	 */
	public void add(LoginLog log) {
		try {
			String URL=bmw_cloud_baseservice_url+loginlog_add;
			HttpUtils.doPost(URL, HttpUtils.convertParam(log));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
