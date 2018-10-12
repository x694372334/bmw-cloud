package com.stylefeng.guns.modular.system.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.OperationLog;



/**  
* <p>Title: IOperationLogService</p>  
* <p>Description: 操作日志服务表</p>  
* @author lyl  
* @date 2018年4月27日  
*/  
public interface IOperationLogService {

    /**
     * 获取操作日志列表
     */
	JSONObject getOperationLogs(Page<OperationLog> page, String beginTime, String endTime, String logName, String logType, String orderByField, boolean asc);
	
	
	/**
	 * 获取操作日志详情
	 */
	OperationLog deltial(Integer operationLogsId);
	
	/**
	 * 清空操作日志
	 */
	void del(); 
	
	
	/**
	 * 添加操作日志
	 */
	void add(OperationLog log); 
}
