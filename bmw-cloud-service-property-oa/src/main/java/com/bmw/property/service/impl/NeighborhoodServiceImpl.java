package com.bmw.property.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.bmw.property.model.Neighborhood;
import com.bmw.property.service.INeighborhoodService;

@Service
public class NeighborhoodServiceImpl implements INeighborhoodService {

	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_baseservice_url="";

	@Value("${neighborhood.neighborhoodList}")
	private String neighborhood_neighborhoodList="";

	@Value("${neighborhood.neighborhoodDetail}")
	private String neighborhood_neighborhoodDetail="";

	@Value("${neighborhood.add}")
	private String neighborhood_add="";

	@Value("${neighborhood.update}")
	private String neighborhood_update="";

	@Value("${neighborhood.del}")
	private String neighborhood_del="";
	
	@Value("${neighborhood.getNewCode}")
	private String neighborhood_getNewCode="";
	
	@Value("${neighborhood.findByAddress}")
	private String neighborhood_findAddress="";
	
	@Value("${neighborhood.findEid}")
	private String neighborhood_findEid="";
	
	@Value("${neighborhood.findEditAddress}")
	private String neighborhood_findEditAddress="";

	@Value("${neighborhood.deleteBuilding}")
	private String neighborhood_deleteBuilding="";



	@Override
	public JSONArray findList(Integer eId,String condition) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
		json.put("nName", condition);
		json.put("eId", eId);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+neighborhood_neighborhoodList+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public Neighborhood getdetail(Integer neighborhoodId) {
		Neighborhood neighborhood=new Neighborhood();
		JSONObject json=new JSONObject();
        json.put("neighborhoodId", neighborhoodId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+neighborhood_neighborhoodDetail+params, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				neighborhood=jsonObject.toJavaObject(Neighborhood.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return neighborhood;
	}

	@Override
	public void add(Neighborhood neighborhood) {
		ShiroExt shiro = new ShiroExt();
		Map<String , String> map = new HashMap<String , String>();
		map.put("code", neighborhood.getAdCode().toString());
		map.put("test", "");
		map.put("number", "4");
		String data = getNewCode(map);
		neighborhood.setnCode(data);
		neighborhood.setAdName(neighborhood.getAdName());
		neighborhood.setCreateMan(shiro.getUser().getName());
		neighborhood.setCreateManId(shiro.getUser().getId());
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url+neighborhood_add, HttpUtils.convertParam(neighborhood));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(Neighborhood neighborhood) {
		ShiroExt shiro = new ShiroExt();
		neighborhood.setEditMan(shiro.getUser().getName());
		neighborhood.setEditManId(shiro.getUser().getId());
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url+neighborhood_update, HttpUtils.convertParam(neighborhood));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public int del(Integer neighborhoodId) {
		JSONObject json=new JSONObject();
        json.put("neighborhoodId", neighborhoodId);
        int i = 1;
    	try {
    		String rlt = HttpUtils.doGet(bmw_cloud_baseservice_url+neighborhood_deleteBuilding+neighborhoodId);
    		JSONObject data=JSON.parseObject(rlt).getJSONObject("items");
    		if(data.get("data").equals("0") ) {
    			String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
    			HttpUtils.doDelete(bmw_cloud_baseservice_url+neighborhood_del+params);
    		}else {
    			i = 0;
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return i;
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
    		String  rlt = HttpUtils.doPost(bmw_cloud_baseservice_url+neighborhood_getNewCode+params , null);
    		JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
    		data= jsonObject.get("neighbor").toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return data ;
	}
	
	@Override
	public JSONArray findAddress(String data) {
		JSONArray jsonArray=null;
		try {
			if(!data.equals("1")) {
				 data=Base64Utils.encodeToString(JSON.toJSONString(data).toString().getBytes("UTF-8"));
			}
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url+neighborhood_findAddress+data,null);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return jsonArray;
	}
	
	@Override
	public Neighborhood findEid(Integer eId) {
		Neighborhood neighborhood=new Neighborhood();
		try {
			String rlt = HttpUtils.doGet(bmw_cloud_baseservice_url+neighborhood_findEid+eId);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				neighborhood=jsonObject.toJavaObject(Neighborhood.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return neighborhood;
	}
	
	
	@Override
	public JSONArray findEditAddress(String data) {
		JSONArray jsonArray=null;
		try {
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url+neighborhood_findEditAddress+data,null);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return jsonArray;
	}
	
	


}