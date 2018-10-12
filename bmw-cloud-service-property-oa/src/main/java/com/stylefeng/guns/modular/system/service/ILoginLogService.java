package com.stylefeng.guns.modular.system.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.modular.system.model.LoginLog;
import com.stylefeng.guns.modular.system.model.OperationLog;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 登录记录 服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-02-22
 */
public interface ILoginLogService  {

    /**
     * 获取登录日志列表
     */
    JSONObject getLoginLogs(Page<OperationLog> page, String beginTime, String endTime, String logName, String orderByField, boolean asc);

    
    /**
     * 清空登录日志
     */
    public void del();
    
    /**
	 * 添加操作日志
	 */
	public void add(LoginLog log);
}
