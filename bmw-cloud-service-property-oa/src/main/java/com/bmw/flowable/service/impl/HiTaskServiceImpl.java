package com.bmw.flowable.service.impl;

import com.bmw.flowable.model.TaskVO;
import com.bmw.flowable.service.IFlowableService;
import com.bmw.flowable.service.IHiTaskService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.beetl.ShiroExt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangt123
 * @since 2018-07-23
 */
@Service
public class HiTaskServiceImpl implements IHiTaskService {
	
	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_baseservice_url="";

	@Value("${hiTask.hiTaskList}")
	private String hiTask_hiTaskList="";
	
	@Value("${hiTask.findHiTaskByPid}")
	private String hiTask_findHiTaskByPid="";
	
	@Override
	public JSONArray findList(String name) {
		ShiroExt shiro = new ShiroExt();
		String param = shiro.getUser().getId().toString();
		JSONArray jsonArray=null;
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+hiTask_hiTaskList+param+"/"+name);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public JSONArray history(String pid) {
		JSONArray jsonArray=null;
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+hiTask_findHiTaskByPid+pid);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;

	}
}
