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
import com.bmw.property.model.AContractor;
import com.bmw.property.service.IAContractorInfoService;


@Service
public class AContractorInfoServiceImpl implements IAContractorInfoService {

	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_baseservice_url="";

	@Value("${acontractorInfo.acontractorInfoList}")
	private String acontractorInfo_acontractorInfoList="";

	@Value("${acontractorInfo.acontractorInfoDetail}")
	private String acontractorInfo_acontractorInfoDetail="";

	@Value("${acontractorInfo.add}")
	private String acontractorInfo_add="";

	@Value("${acontractorInfo.update}")
	private String acontractorInfo_update="";

	@Value("${acontractorInfo.del}")
	private String acontractorInfo_del="";

	@Override
	public JSONArray findList(String condition,String nName,String eId) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
		json.put("cName", condition);
		json.put("nName", nName);
		json.put("eId", eId);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+acontractorInfo_acontractorInfoList+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}
	



	@Override
	public AContractor getdetail(Integer acontractorInfoId) {
		AContractor acontractorInfo=new AContractor();
		JSONObject json=new JSONObject();
        json.put("acontractorInfoId", acontractorInfoId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+acontractorInfo_acontractorInfoDetail+params, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				acontractorInfo=jsonObject.toJavaObject(AContractor.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return acontractorInfo;
	}

	@Override
	public void add(AContractor acontractorInfo) {
		ShiroExt shiro = new ShiroExt();
		acontractorInfo.setCreateMan(shiro.getUser().getName());
		acontractorInfo.setCreateManId(shiro.getUser().getId());
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(acontractorInfo).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+acontractorInfo_add+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(AContractor acontractorInfo) {
		ShiroExt shiro = new ShiroExt();
		acontractorInfo.setEditMan(shiro.getUser().getName());
		acontractorInfo.setEditManId(shiro.getUser().getId());
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(acontractorInfo).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+acontractorInfo_update+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void del(Integer acontractorInfoId) {
		JSONObject json=new JSONObject();
        json.put("acontractorInfoId", acontractorInfoId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doDelete(bmw_cloud_baseservice_url+acontractorInfo_del+params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}