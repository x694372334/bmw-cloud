package com.bmw.property.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
import com.bmw.property.model.ACInfo;
import com.bmw.property.service.IACInfoService;


@Service
public class ACInfoServiceImpl implements IACInfoService {

	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_baseservice_url="";

	@Value("${aCInfo.aCInfoList}")
	private String aCInfo_aCInfoList="";

	@Value("${aCInfo.aCInfoDetail}")
	private String aCInfo_aCInfoDetail="";

	@Value("${aCInfo.add}")
	private String aCInfo_add="";

	@Value("${aCInfo.update}")
	private String aCInfo_update="";

	@Value("${aCInfo.del}")
	private String aCInfo_del="";



	@Override
	public JSONArray findList(String condition) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
//		json.put("aName", condition);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+aCInfo_aCInfoList+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public ACInfo getdetail(Integer acInfoId) {
		ACInfo acInfo=new ACInfo();
		JSONObject json=new JSONObject();
        json.put("acInfoId", acInfoId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+aCInfo_aCInfoDetail+params, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				acInfo=jsonObject.toJavaObject(ACInfo.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return acInfo;
	}

	@Override
	public void add(ACInfo acInfo) {
		ShiroExt shiro = new ShiroExt();
		acInfo.setCreateMan(shiro.getUser().getName());
		acInfo.setCreateManId(shiro.getUser().getId());
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url+aCInfo_add, HttpUtils.convertParam(acInfo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(ACInfo acInfo) {
		ShiroExt shiro = new ShiroExt();
		acInfo.setEditMan(shiro.getUser().getName());
		acInfo.setEditManId(shiro.getUser().getId());
		acInfo.setcETime(Timestamp.valueOf(LocalDateTime.now()));
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url+aCInfo_update, HttpUtils.convertParam(acInfo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void del(Integer acInfoId) {
		JSONObject json=new JSONObject();
        json.put("acInfoId", acInfoId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doDelete(bmw_cloud_baseservice_url+aCInfo_del+params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}