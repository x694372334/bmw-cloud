package com.stylefeng.guns.modular.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.modular.system.model.OperationLog;
import com.stylefeng.guns.modular.system.service.IOperationLogService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;



/**  
* <p>Title: OperationLogServiceImpl</p>  
* <p>Description: 登录日志服务实现</p>  
* @author lyl  
* @date 2018年4月27日  
*/  
@Service
public class OperationLogServiceImpl implements IOperationLogService {
	@Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_baseservice_url="";
	
	@Value("${operationlog.getOperationLogs}")
	private String operationlog_getOperationLogs="";
	
	@Value("${operationlog.deltial}")
	private String operationlog_deltial = "";
	
	@Value("${operationlog.del}")
	private String operationlog_del = "";
	
	@Value("${operationlog.add}")
	private String operationlog_add = "";
	
	
    @Override
    public JSONObject getOperationLogs(Page<OperationLog> page, String beginTime, String endTime, String logName, String logType, String orderByField, boolean asc) {
    	int pageSize=page.getSize();
    	int pageNum=page.getCurrent();
    	JSONObject paraJson=new JSONObject();
    	paraJson.put("beginTime", beginTime!=null?beginTime:"");
    	paraJson.put("endTime", endTime!=null?endTime:"");
    	paraJson.put("logName", logName!=null?logName:"");
    	paraJson.put("logType", logType!=null?logType:"");
    	paraJson.put("orderByField", orderByField!=null?orderByField:"");
    	paraJson.put("asc", asc);
    	paraJson.put("pageSize", pageSize);
    	paraJson.put("pageNum", pageNum);
    	JSONObject jsonrlt=new JSONObject();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+operationlog_getOperationLogs+new String(Base64Utils.encode(paraJson.toString().getBytes("UTF-8"))));
			jsonrlt=JSONObject.parseObject(rlt).getJSONObject("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	return jsonrlt;
    }
    
    @Override
    public OperationLog deltial(Integer operationLogsId) {
    	OperationLog operationLog= new OperationLog();
    	try {
			String str=HttpUtils.doGet(bmw_cloud_baseservice_url+operationlog_deltial+operationLogsId);
			JSONObject object=JSONObject.parseObject(str).getJSONObject("items");
			operationLog=JSONObject.toJavaObject(object, OperationLog.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return operationLog;
    }
    
    @Override
    public void del() { 
    	try {
			HttpUtils.doDelete(bmw_cloud_baseservice_url+operationlog_del);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
	 * 添加操作日志
	 */
	public void add(OperationLog log) {
		try {
			String URL=bmw_cloud_baseservice_url+operationlog_add;
			HttpUtils.doPost(URL, HttpUtils.convertParam(log));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
}
