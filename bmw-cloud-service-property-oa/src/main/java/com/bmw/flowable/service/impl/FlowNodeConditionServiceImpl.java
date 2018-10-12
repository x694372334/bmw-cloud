package com.bmw.flowable.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.bmw.flowable.model.FlowNodeCondition;
import com.bmw.flowable.service.IFlowNodeConditionService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 流程节点条件表 服务实现类
 * </p>
 *
 * @author zhangtan123
 * @since 2018-08-24
 */
@Service
public class FlowNodeConditionServiceImpl  implements IFlowNodeConditionService {

	@Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_baseservice_url="";
	
	@Value("${flowable.flowNodeConditionList}")
	private String flowable_flowNodeConditionList="";
	
	@Value("${flowable.flowNodeConditionDetail}")
	private String flowable_flowNodeConditionDetail="";
	
	@Value("${flowable.flowNodeConditionUpdate}")
	private String flowable_flowNodeConditionUpdate="";
	
	@Value("${flowable.flowNodeConditionDel}")
	private String flowable_flowNodeConditionDel="";
	
	@Value("${flowable.flowNodeConditionAdd}")
	private String flowable_flowNodeConditionAdd="";
	
	@Override
	public JSONArray findList(FlowNodeCondition condition) {
		JSONArray jsonArray=null;
    	try {
    		String rlt= HttpUtils.doPost(bmw_cloud_baseservice_url+flowable_flowNodeConditionList,JSONObject.toJSONString(condition).toString(),null);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public FlowNodeCondition getdetail(Integer flowNodeConditionId) {
		FlowNodeCondition flowNodeCondition = new FlowNodeCondition();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+flowable_flowNodeConditionDetail+flowNodeConditionId);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				flowNodeCondition=jsonObject.toJavaObject(FlowNodeCondition.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return flowNodeCondition;
	}

	@Override
	public String add(FlowNodeCondition flowNodeCondition) {
		try {
    		HttpUtils.doPost(bmw_cloud_baseservice_url+flowable_flowNodeConditionAdd,JSONObject.toJSONString(flowNodeCondition).toString(),null);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "";
	}

	@Override
	public String update(FlowNodeCondition flowNodeCondition) {
    	try {
    		HttpUtils.doPost(bmw_cloud_baseservice_url+flowable_flowNodeConditionUpdate,JSONObject.toJSONString(flowNodeCondition).toString(),null);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "";
	}

	@Override
	public String del(Integer flowNodeConditionId) {
    	try {
    		HttpUtils.doGet(bmw_cloud_baseservice_url+flowable_flowNodeConditionDel+flowNodeConditionId,null);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return "";
	}



}
