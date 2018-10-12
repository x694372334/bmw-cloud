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
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.Advertising;
import com.bmw.property.model.ParkingInfo;
import com.bmw.property.service.IAdvertisingInfoService;


@Service
public class AdvertisingInfoServiceImpl implements IAdvertisingInfoService {

	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_baseservice_url="";

	@Value("${advertising.advertisingList}")
	private String advertising_advertisingList="";

	@Value("${advertising.advertisingDetail}")
	private String advertising_advertisingDetail="";
	
	@Value("${advertising.selectData}")
	private String advertising_selectData="";
	
	@Value("${advertising.selectCheckedData}")
	private String advertising_selectCheckedData="";
	
	

	@Value("${advertising.add}")
	private String advertising_add="";

	@Value("${advertising.update}")
	private String advertising_update="";

	@Value("${advertising.del}")
	private String advertising_del="";
	
	@Value("${advertising.findContractor}")
	private String advertising_findContractor="";
	
	@Value("${advertising.createGGWTree}")
	private String advertising_createGGWTree;



	@Override
	public JSONArray findList(String condition,String eId) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
		json.put("aName", condition);
		json.put("eId", eId);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+advertising_advertisingList+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public Advertising getdetail(Integer advertisingId) {
		Advertising advertising=new Advertising();
		JSONObject json=new JSONObject();
        json.put("advertisingId", advertisingId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+advertising_advertisingDetail+params, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				advertising=jsonObject.toJavaObject(Advertising.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return advertising;
	}

	@Override
	public void add(Advertising advertising) {
		advertising.setaStatus(1);
		ShiroExt shiro = new ShiroExt();
		advertising.setCreateMan(shiro.getUser().getName());
		advertising.setCreateManId(shiro.getUser().getId());
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url+advertising_add, HttpUtils.convertParam(advertising));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(Advertising advertising) {
		ShiroExt shiro = new ShiroExt();
		advertising.setEditMan(shiro.getUser().getName());
		advertising.setEditManId(shiro.getUser().getId());
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url+advertising_update, HttpUtils.convertParam(advertising));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void del(Integer advertisingId) {
		JSONObject json=new JSONObject();
        json.put("advertisingId", advertisingId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doDelete(bmw_cloud_baseservice_url+advertising_del+params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Override
	public JSONArray findContractor(Integer condition) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
		json.put("advertisingId", condition);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+advertising_findContractor+params,null);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public List<ZTreeNode> createGGWTree(Integer eId, Integer nbId) {
		
		Map<String,String> httpSetting=new HashMap<>();
		List<ZTreeNode> treeList=new ArrayList<>();
		Map<String,Integer> param=new HashMap<>();
		param.put("eId", eId);
		param.put("nbId", nbId);
		httpSetting.put("contentType", "application/json");
    	try {
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+advertising_createGGWTree,JSONObject.toJSONString(param).toString(),null);
			treeList=JSONObject.parseObject(rlt).getJSONArray("items").toJavaList(ZTreeNode.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return treeList;
	}

	@Override
	public Object selectData(Integer nbId, Integer id, Integer relevanceId) {
		JSONObject json=new JSONObject();
		json.put("nbId", nbId);
		json.put("batchId", id);
		json.put("relevanceId", relevanceId);
		List<Map<String,Object>> checkedMap = Lists.newArrayList();
		List<Map<String,Object>> uncheckedMap = Lists.newArrayList();
		
		Map<String,List<Map<String,Object>>> maps= Maps.newHashMap();
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+advertising_selectData+params);
			List<Advertising> unCheckList=JSONObject.parseObject(rlt).getJSONArray("items").toJavaList(Advertising.class);
			
			String rlt1=HttpUtils.doGet(bmw_cloud_baseservice_url+advertising_selectCheckedData+params);
			List<Advertising> checkedList=JSONObject.parseObject(rlt1).getJSONArray("items").toJavaList(Advertising.class);
			
			
			
			for(Advertising a : unCheckList) {
				Map<String,Object> map = Maps.newHashMap();
				map.put("rId", a.getaId() + "@"); //@判断是否是后台带过来的还是前台后选择
				map.put("rRoomnum", a.getaName());
				uncheckedMap.add(map);
			}
			maps.put("uncheckedMap", uncheckedMap);
			
			for(Advertising a : checkedList) {
				Map<String,Object> map = Maps.newHashMap();
				map.put("rId", a.getaId() + "#"); //@判断是否是后台带过来的还是前台后选择
				map.put("rRoomnum", a.getaName());
				checkedMap.add(map);
			}
			maps.put("checkedMap", checkedMap);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return maps;
	}
	
	

}