package com.bmw.property.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.IVehicleInfo;
import com.bmw.property.service.IVehicleInfoService;


@Service
public class IVehicleInfoServiceImpl implements IVehicleInfoService {

	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_baseservice_url="";

	@Value("${iVehicleInfo.iVehicleInfoList}")
	private String iVehicleInfo_iVehicleInfoList="";

	@Value("${iVehicleInfo.iVehicleInfoDetail}")
	private String iVehicleInfo_iVehicleInfoDetail="";

	@Value("${iVehicleInfo.add}")
	private String iVehicleInfo_add="";

	@Value("${iVehicleInfo.update}")
	private String iVehicleInfo_update="";

	@Value("${iVehicleInfo.del}")
	private String iVehicleInfo_del="";
	


	@Override
	public JSONArray findList(String condition,String nName,String iName,String eId) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
		json.put("vNumber", condition);
		json.put("nName", nName);
		json.put("iName", iName);
		json.put("eId", eId);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+iVehicleInfo_iVehicleInfoList+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public IVehicleInfo getdetail(Integer IVehicleInfoId) {
		IVehicleInfo IVehicleInfo=new IVehicleInfo();
		JSONObject json=new JSONObject();
        json.put("iVehicleInfoId", IVehicleInfoId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+iVehicleInfo_iVehicleInfoDetail+params, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				IVehicleInfo=jsonObject.toJavaObject(IVehicleInfo.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return IVehicleInfo;
	}

	@Override
	public void add(IVehicleInfo IVehicleInfo) {
		ShiroExt shiro = new ShiroExt();
		IVehicleInfo.setCreateMan(shiro.getUser().getName());
		IVehicleInfo.setCreateManId(shiro.getUser().getId());
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url+iVehicleInfo_add, HttpUtils.convertParam(IVehicleInfo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(IVehicleInfo IVehicleInfo) {
		ShiroExt shiro = new ShiroExt();
		IVehicleInfo.setEditMan(shiro.getUser().getName());
		IVehicleInfo.setEditManId(shiro.getUser().getId());
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url+iVehicleInfo_update, HttpUtils.convertParam(IVehicleInfo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void del(Integer IVehicleInfoId) {
		JSONObject json=new JSONObject();
        json.put("iVehicleInfoId", IVehicleInfoId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doDelete(bmw_cloud_baseservice_url+iVehicleInfo_del+params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	




}