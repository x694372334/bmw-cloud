package com.bmw.flowable.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.bmw.flowable.model.FlowConfigure;
import com.bmw.flowable.service.IFlowConfigureService;

/**
 * <p>
 * 流程配置表 服务实现类
 * </p>
 *
 * @author zhangtan123
 * @since 2018-08-24
 */
@Service
public class FlowConfigureServiceImpl implements IFlowConfigureService{

	@Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_baseservice_url="";
	
	@Value("${flowable.flowConfigureList}")
	private String flowable_flowConfigureList="";
	
	@Value("${flowable.flowConfigureDetail}")
	private String flowable_flowConfigureDetail="";
	
	@Value("${flowable.flowConfigureUpdate}")
	private String flowable_flowConfigureUpdate="";
	
	@Value("${flowable.flowConfigureDel}")
	private String flowable_flowConfigureDel="";
	
	@Value("${flowable.flowConfigureAdd}")
	private String flowable_flowConfigureAdd="";
	
	@Override
	public JSONArray findList(FlowConfigure condition) {
		JSONArray jsonArray=null;
    	try {
    		String rlt= HttpUtils.doPost(bmw_cloud_baseservice_url+flowable_flowConfigureList,JSONObject.toJSONString(condition).toString(),null);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public FlowConfigure getdetail(Integer flowConfigureId) {
		FlowConfigure flowConfigure = new FlowConfigure();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+flowable_flowConfigureDetail+flowConfigureId);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				flowConfigure=jsonObject.toJavaObject(FlowConfigure.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return flowConfigure;
	}

	@Override
	public String add(FlowConfigure flowConfigure) {
		try {
    		HttpUtils.doPost(bmw_cloud_baseservice_url+flowable_flowConfigureAdd,JSONObject.toJSONString(flowConfigure).toString(),null);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "";
	}

	@Override
	public String update(FlowConfigure flowConfigure) {
    	try {
    		HttpUtils.doPost(bmw_cloud_baseservice_url+flowable_flowConfigureUpdate,JSONObject.toJSONString(flowConfigure).toString(),null);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "";
	}

	@Override
	public String del(Integer flowConfigureId) {
    	try {
    		HttpUtils.doGet(bmw_cloud_baseservice_url+flowable_flowConfigureDel+flowConfigureId,null);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "";
	}


}
