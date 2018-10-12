package com.bmw.property.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.bmw.property.model.SelectBulter;
import com.bmw.property.service.ISelectBulterService;


@Service
public class SelectBulterServiceImpl implements ISelectBulterService {

	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_baseservice_url="";

	@Value("${selectBulter.selectBulterList}")
	private String selectBulter_selectBulterList="";

	@Value("${selectBulter.selectBulterDetail}")
	private String selectBulter_selectBulterDetail="";

	@Value("${neighborhood.userRoleView}")
	private String neighborhood_userRoleView="";




	@Override
	public JSONArray findList(String condition , Integer ss , String data ) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
		ShiroExt shiro = new ShiroExt();
		boolean b = shiro.getUser().isAdmin;
		json.put("name", condition);
		if(!b) {
			json.put("eId", ss);
			json.put("userId", data);
		}
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+selectBulter_selectBulterList+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public SelectBulter getdetail(Integer selectBulterId) {
		SelectBulter selectBulter=new SelectBulter();
		JSONObject json=new JSONObject();
        json.put("selectBulterId", selectBulterId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+selectBulter_selectBulterDetail+params, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				selectBulter=jsonObject.toJavaObject(SelectBulter.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return selectBulter;
	}
    

}