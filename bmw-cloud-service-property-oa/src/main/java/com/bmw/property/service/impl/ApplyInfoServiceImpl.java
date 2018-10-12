package com.bmw.property.service.impl;


import com.bmw.property.model.ApplyInfo;
import com.bmw.property.service.IApplyInfoService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.beetl.ShiroExt;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.ParseException;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 审批申请单 服务实现类
 * </p>
 *
 * @author Jinmy123
 * @since 2018-07-19
 */
@Service
public class ApplyInfoServiceImpl implements IApplyInfoService {


	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_baseservice_url="";

	@Value("${applyInfo.applyInfoList}")
	private String applyInfo_applyInfoList="";

	@Value("${applyInfo.applyInfoDetail}")
	private String applyInfo_applyInfoDetail="";

	@Value("${applyInfo.add}")
	private String applyInfo_add="";

	@Value("${applyInfo.update}")
	private String applyInfo_update="";

	@Value("${applyInfo.del}")
	private String applyInfo_del="";
	
	@Value("${applyInfo.flowabled_add}")
	private String flowabled_add="";

	@Value("${applyInfo.flowabled_apply}")
	private String flowabled_apply="";

	@Value("${applyInfo.flowabled_reject}")
	private String flowabled_reject="";
	
    @Autowired
    private ProcessEngine processEngine;
    
    @Autowired
    private TaskService taskService;
    
    



	@Override
	public JSONArray findList(String aType , String eaResult , String userId) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
		json.put("aType", aType);
		json.put("eaResult", eaResult);
		json.put("userId", userId);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+applyInfo_applyInfoList+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public ApplyInfo getdetail(Integer applyInfoId) {
		ApplyInfo applyInfo=new ApplyInfo();
		JSONObject json=new JSONObject();
        json.put("applyInfoId", applyInfoId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+applyInfo_applyInfoDetail+params, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				applyInfo=jsonObject.toJavaObject(ApplyInfo.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return applyInfo;
	}

	@Override
	public void add(ApplyInfo applyInfo) {
		ShiroExt shiro = new ShiroExt();
		applyInfo.setaUser(shiro.getUser().getId());
		applyInfo.setCreateMan(shiro.getUser().getName());
		applyInfo.setCreateManId(shiro.getUser().getId());
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url+applyInfo_add, HttpUtils.convertParam(applyInfo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(ApplyInfo applyInfo) {
		ShiroExt shiro = new ShiroExt();
		applyInfo.setEditMan(shiro.getUser().getName());
		applyInfo.setEditManId(shiro.getUser().getId());
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url+applyInfo_update, HttpUtils.convertParam(applyInfo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void del(Integer applyInfoId) {
		JSONObject json=new JSONObject();
        json.put("applyInfoId", applyInfoId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doDelete(bmw_cloud_baseservice_url+applyInfo_del+params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Override
	public void flowabled_add(ApplyInfo applyInfo) {
		ShiroExt shiro = new ShiroExt();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("eaUser", shiro.getUser().getId());
		jsonObject.put("businessId", applyInfo.getId());
		jsonObject.put("aType",applyInfo.getaType());
		try {
			String params=Base64Utils.encodeToString(JSON.toJSONString(jsonObject).toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+flowabled_add+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
     * 批准
     *
     * @param taskId 任务ID
     */
	@Override
	public void flowabled_apply(String taskId , String id) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("taskId", taskId);
		jsonObject.put("id", id);
	      //通过审核
	        try {
	        	String params=Base64Utils.encodeToString(JSON.toJSONString(jsonObject).toString().getBytes("UTF-8"));
	        	String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+flowabled_apply+params, null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}
	
	/**
     * 驳回
     *
     * @param taskId 任务ID
     */
	@Override
    public void flowabled_reject(String taskId , String id) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("taskId", taskId);
		jsonObject.put("id", id);
	        try {
	        	String params=Base64Utils.encodeToString(JSON.toJSONString(jsonObject).toString().getBytes("UTF-8"));
				String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+flowabled_reject+params, null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
    }

	
}
