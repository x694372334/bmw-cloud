package com.bmw.property.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.bmw.property.model.MBaseInfo;
import com.bmw.property.service.IMBaseInfoService;


@Service
public class MBaseInfoServiceImpl implements IMBaseInfoService {

	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_baseservice_url="";

	@Value("${mBaseInfo.mBaseInfoList}")
	private String mBaseInfo_mBaseInfoList="";

	@Value("${mBaseInfo.mBaseDoubleInfoList}")
	private String mBaseInfo_mBaseDoubleInfoList="";

	@Value("${mBaseInfo.mBaseInfoDetail}")
	private String mBaseInfo_mBaseInfoDetail="";

	@Value("${mBaseInfo.add}")
	private String mBaseInfo_add="";

	@Value("${mBaseInfo.update}")
	private String mBaseInfo_update="";

	@Value("${mBaseInfo.del}")
	private String mBaseInfo_del="";
	
	@Value("${mBaseInfo.findDosage}")
	private String mBaseInfo_findDosage="";
	
	@Value("${mBaseInfo.findDouble}")
	private String mBaseInfo_findDouble="";
	
	@Value("${mBaseInfo.saveBills}")
	private String mBaseInfo_saveBills="";

	@Override
	public JSONArray findList(String nName , String bName , String rRoomnum,String eId) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
		json.put("nName", nName);
		json.put("bName", bName);
		json.put("rRoomnum", rRoomnum);
		json.put("eId", eId);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+mBaseInfo_mBaseInfoList+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}
	
	@Override
	public JSONArray findDoubleList(String nName , String bName , String rRoomnum,String eId) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
		json.put("eId", eId);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+mBaseInfo_mBaseDoubleInfoList+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}
	


	@Override
	public MBaseInfo getdetail(Integer mBaseInfoId) {
		MBaseInfo mBaseInfo=new MBaseInfo();
		JSONObject json=new JSONObject();
        json.put("mBaseInfoId", mBaseInfoId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+mBaseInfo_mBaseInfoDetail+params, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				mBaseInfo=jsonObject.toJavaObject(MBaseInfo.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return mBaseInfo;
	}

	@Override
	public void add(MBaseInfo mBaseInfo) {
		ShiroExt shiro = new ShiroExt();
		mBaseInfo.setCreateMan(shiro.getUser().getName());
		mBaseInfo.setCreateManId(shiro.getUser().getId());
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(mBaseInfo).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+mBaseInfo_add+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(MBaseInfo mBaseInfo) {
		ShiroExt shiro = new ShiroExt();
		mBaseInfo.setEditMan(shiro.getUser().getName());
		mBaseInfo.setEditManId(shiro.getUser().getId());
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(mBaseInfo).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+mBaseInfo_update+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void del(Integer mBaseInfoId) {
		JSONObject json=new JSONObject();
        json.put("mBaseInfoId", mBaseInfoId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doDelete(bmw_cloud_baseservice_url+mBaseInfo_del+params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	@Override
	public MBaseInfo findDosage(MBaseInfo mBase) {
		MBaseInfo mBaseInfo=new MBaseInfo();
		JSONObject json=new JSONObject();
        json.put("sId", mBase.getsId());
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+mBaseInfo_findDosage+params, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				mBaseInfo=jsonObject.toJavaObject(MBaseInfo.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return mBaseInfo;
	}
	
	@Override
	public MBaseInfo findDouble(MBaseInfo mBase) {
		MBaseInfo mBaseInfo=new MBaseInfo();
		JSONObject json=new JSONObject();
        json.put("sId", mBase.getsId());
        json.put("tMonth", mBase.gettMonth());
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+mBaseInfo_findDouble+params, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				mBaseInfo=jsonObject.toJavaObject(MBaseInfo.class); 
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return mBaseInfo;
	}
	
	/**
	 * 一键生成账单的操作（用于水费、电费）
	 * 
	 * @param mBaseInfoIds 前台抄表基础管理列表中选中的数据的id
	 */
	@Override
	public String saveBills(String mBaseInfoIds) {
		String result = null;
		JSONObject json = new JSONObject();
		json.put("mBaseInfoIds", mBaseInfoIds);
		try {
			String params = Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url + mBaseInfo_saveBills + params, null);
			JSONObject obj = JSON.parseObject(rlt);
			if(null != obj && obj.getIntValue("statusCode") == HttpStatus.UNAUTHORIZED.value()) {
				result=obj.getString("message");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


}