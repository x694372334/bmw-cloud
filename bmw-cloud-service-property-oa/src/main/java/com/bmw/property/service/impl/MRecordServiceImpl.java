package com.bmw.property.service.impl;

import java.math.BigDecimal;
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
import com.bmw.property.model.MBaseInfo;
import com.bmw.property.model.MRecord;
import com.bmw.property.service.IMRecordService;


@Service
public class MRecordServiceImpl implements IMRecordService {

	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_baseservice_url="";

	@Value("${mRecord.mRecordList}")
	private String mRecord_mRecordList="";

	@Value("${mRecord.mRecordDetail}")
	private String mRecord_mRecordDetail="";

	@Value("${mRecord.add}")
	private String mRecord_add="";

	@Value("${mRecord.update}")
	private String mRecord_update="";

	@Value("${mRecord.del}")
	private String mRecord_del="";
	
	@Value("${mRecord.findMonth}")
	private String findMonth="";
	



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
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+mRecord_mRecordList+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}
	


	@Override
	public MRecord getdetail(Integer mRecordId) {
		MRecord mRecord=new MRecord();
		JSONObject json=new JSONObject();
        json.put("mRecordId", mRecordId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+mRecord_mRecordDetail+params, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				mRecord=jsonObject.toJavaObject(MRecord.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return mRecord;
	}

	@Override
	public void add(MRecord mRecord) {
			String ces = mRecord.getText().toString();
			String[] text = ces.split("\\|");
			if(mRecord.gettNum()==null) {
				mRecord.settNum(BigDecimal.valueOf(Long.valueOf(text[1])));
				mRecord.setuAmount(BigDecimal.valueOf(Long.valueOf(text[1])));
			}else {
				//计算用量
//				BigDecimal data = new BigDecimal(text[1]);
//				mRecord.setuAmount(data.subtract(mRecord.gettNum()));
				mRecord.settNum(BigDecimal.valueOf(Long.valueOf(text[1])));
			}
			String data[] = mRecord.gettMonth().split("-");
			mRecord.settMonth(data[0]+data[1]);
			//新增当前月份
			ShiroExt shiro = new ShiroExt();
			mRecord.setCreateMan(shiro.getUser().getName());
			mRecord.setCreateManId(shiro.getUser().getId());
			try {
	    		String params=Base64Utils.encodeToString(JSON.toJSONString(mRecord).toString().getBytes("UTF-8"));
				HttpUtils.doPost(bmw_cloud_baseservice_url+mRecord_add+params, null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		}
	}
	
	@Override
	public String findMonth(Integer sId , String tMonth) {
		MBaseInfo mBase = new MBaseInfo();
		mBase.setsId(sId);
		mBase.settMonth(tMonth);
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(mBase).toString().getBytes("UTF-8"));
    		String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+findMonth+params, null);
    		JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
    		if(jsonObject!=null) {
    			mBase=jsonObject.toJavaObject(MBaseInfo.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mBase.gettMonth();
	}

	@Override
	public void update(MRecord mRecord) {
		ShiroExt shiro = new ShiroExt();
		mRecord.setEditMan(shiro.getUser().getName());
		mRecord.setEditManId(shiro.getUser().getId());
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(mRecord).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+mRecord_update+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void del(Integer mRecordId) {
		JSONObject json=new JSONObject();
        json.put("mRecordId", mRecordId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doDelete(bmw_cloud_baseservice_url+mRecord_del+params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	


}