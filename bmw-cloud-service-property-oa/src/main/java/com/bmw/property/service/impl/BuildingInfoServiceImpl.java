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
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.bmw.property.model.BuildingInfo;
import com.bmw.property.service.IBuildingInfoService;

@Service
public class BuildingInfoServiceImpl implements IBuildingInfoService {

	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_baseservice_url="";

	@Value("${buildingInfo.buildingInfoList}")
	private String buildingInfo_buildingInfoList="";

	@Value("${buildingInfo.buildingInfoDetail}")
	private String buildingInfo_buildingInfoDetail="";

	@Value("${buildingInfo.add}")
	private String buildingInfo_add="";

	@Value("${buildingInfo.update}")
	private String buildingInfo_update="";

	@Value("${buildingInfo.del}")
	private String buildingInfo_del="";
	
	@Value("${buildingInfo.findNeighbor}")
	private String buildingInfo_findNeighbor="";
	
	@Value("${buildingInfo.findNeighborUpdate}")
	private String buildingInfo_findNeighborUpdate="";
	
	@Value("${buildingInfo.getNewCode}")
	private String buildingInfo_getNewCode="";
	
	@Value("${buildingInfo.deleteRoom}")
	private String buildingInfo_deleteRoom="";





	@Override
	public JSONArray findList(String xqName , String lyName,String eId) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
		json.put("bName", lyName);
		json.put("nName", xqName);
		json.put("eId",eId);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+buildingInfo_buildingInfoList+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public BuildingInfo getdetail(Integer buildingInfoId) {
		BuildingInfo buildingInfo=new BuildingInfo();
		JSONObject json=new JSONObject();
        json.put("buildingInfoId", buildingInfoId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+buildingInfo_buildingInfoDetail+params, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				buildingInfo=jsonObject.toJavaObject(BuildingInfo.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return buildingInfo;
	}

	@Override
	public void add(BuildingInfo buildingInfo) {
		ShiroExt shiro = new ShiroExt();
		Map<String , String> map = new HashMap<String , String>();
		String date = buildingInfo.getbCode().toString();
		String[] text=date.split(",");
		if(text.length>1) {
		buildingInfo.setnId(Integer.parseInt(text[1]));
		}
		map.put("code", text[0]);
		map.put("test", "");
		map.put("number", "3");
		String data = getNewCode(map);
		buildingInfo.setbCode(data);
		buildingInfo.setCreateMan(shiro.getUser().getName());
		buildingInfo.setCreateManId(shiro.getUser().getId());
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url+buildingInfo_add, HttpUtils.convertParam(buildingInfo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(BuildingInfo buildingInfo) {
		Map<String , String> map = new HashMap<String , String>();
		String date = buildingInfo.getbCode().toString();
		ShiroExt shiro = new ShiroExt();
		String[] text=date.split(",");
		buildingInfo.setbCode(text[0]);
		if(text.length>1) {
			buildingInfo.setnId(Integer.parseInt(text[1]));
		}
		//查询原有bCode比对 如有更改则更新操作
		BuildingInfo data1=getdetail(buildingInfo.getbId());
		if(!data1.getbCode().equals(buildingInfo.getbCode())) {
			map.put("code", text[0]);
			map.put("test", "");
			map.put("number", "3");
			String data = getNewCode(map);
			buildingInfo.setbCode(data);
		}
		buildingInfo.setEditMan(shiro.getUser().getName());
		buildingInfo.setEditManId(shiro.getUser().getId());
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url+buildingInfo_update, HttpUtils.convertParam(buildingInfo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public int del(Integer buildingInfoId) {
		JSONObject json=new JSONObject();
        json.put("buildingInfoId", buildingInfoId);
        int i = 1;
    	try {
    		String rlt = HttpUtils.doGet(bmw_cloud_baseservice_url+buildingInfo_deleteRoom+buildingInfoId);
    		JSONObject data=JSON.parseObject(rlt).getJSONObject("items");
    		if(data.get("data").equals("0") ) {
    			String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
    			HttpUtils.doDelete(bmw_cloud_baseservice_url+buildingInfo_del+params);
    		}else {
    			i = 0;
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return i ;

	}
	
	@Override
	public JSONArray findNeighbor(Integer eId) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
        json.put("eId", eId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url+buildingInfo_findNeighbor+params , null);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return jsonArray;
	}
	
	@Override
	public JSONArray findNeighborUpdate(Integer nId) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
        json.put("nId", nId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url+buildingInfo_findNeighborUpdate+params , null);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return jsonArray;
	}
	
	@Override
	public String getNewCode(Map<String,String> map) {
		JSONObject json=new JSONObject();
        json.put("code", map.get("code"));
        json.put("test", map.get("test"));
        json.put("number", map.get("number"));
        String data = "" ;
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
    		String  rlt = HttpUtils.doPost(bmw_cloud_baseservice_url+buildingInfo_getNewCode+params , null);
    		JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
    		data= jsonObject.get("neighbor").toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return data ;
	}
	


}