package com.bmw.flowable.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.bmw.flowable.model.FlowNodeConfigure;
import com.bmw.flowable.service.IFlowNodeConfigureService;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 流程节点配置表 服务实现类
 * </p>
 *
 * @author zhangtan123
 * @since 2018-08-24
 */
@Service
public class FlowNodeConfigureServiceImpl implements IFlowNodeConfigureService {

	@Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_baseservice_url="";
	
	@Value("${flowable.flowNodeConfigureList}")
	private String flowable_flowNodeConfigureList="";
	
	@Value("${flowable.flowNodeConfigureDetail}")
	private String flowable_flowNodeConfigureDetail="";
	
	@Value("${flowable.flowNodeConfigureUpdate}")
	private String flowable_flowNodeConfigureUpdate="";
	
	@Value("${flowable.flowNodeConfigureDel}")
	private String flowable_flowNodeConfigureDel="";
	
	@Value("${flowable.flowNodeConfigureAdd}")
	private String flowable_flowNodeConfigureAdd="";
	
	@Override
	public JSONArray findList(FlowNodeConfigure condition) {
		JSONArray jsonArray=null;
    	try {
    		String rlt= HttpUtils.doPost(bmw_cloud_baseservice_url+flowable_flowNodeConfigureList,JSONObject.toJSONString(condition).toString(),null);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public FlowNodeConfigure getdetail(Integer flowNodeConfigureId) {
		FlowNodeConfigure flowNodeConfigure = new FlowNodeConfigure();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+flowable_flowNodeConfigureDetail+flowNodeConfigureId);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				flowNodeConfigure=jsonObject.toJavaObject(FlowNodeConfigure.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return flowNodeConfigure;
	}

	@Override
	public String add(FlowNodeConfigure flowNodeConfigure) {
		try {
    		HttpUtils.doPost(bmw_cloud_baseservice_url+flowable_flowNodeConfigureAdd,JSONObject.toJSONString(flowNodeConfigure).toString(),null);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "";
	}

	@Override
	public String update(FlowNodeConfigure flowNodeConfigure) {
    	try {
    		HttpUtils.doPost(bmw_cloud_baseservice_url+flowable_flowNodeConfigureUpdate,JSONObject.toJSONString(flowNodeConfigure).toString(),null);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "";
	}

	@Override
	public String del(Integer flowNodeConfigureId) {
    	try {
    		HttpUtils.doGet(bmw_cloud_baseservice_url+flowable_flowNodeConfigureDel+flowNodeConfigureId,null);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "";
	}



}
