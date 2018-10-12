package com.bmw.medical.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.bmw.medical.model.ScheduleDetailHis;
import com.bmw.medical.model.ScheduleHis;
import com.bmw.medical.service.IScheduleDetailHisService;

@Service
public class ScheduleDetailHisServiceImpl implements IScheduleDetailHisService {
	
	@Value("${bmw.cloud.medicalservice.url}")
	private String bmw_cloud_baseservice_url="";

	@Value("${scheduleDetailHis.scheduleDetailHisList}")
	private String schedule_scheduleList="";

	@Value("${scheduleDetailHis.scheduleDetailHisDetail}")
	private String schedule_scheduleDetail="";

	@Value("${scheduleDetailHis.add}")
	private String schedule_add="";

	@Value("${scheduleDetailHis.update}")
	private String schedule_update="";

	@Value("${scheduleDetailHis.del}")
	private String schedule_del="";



	@Override
	public JSONArray findList(String condition) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
		json.put("name", condition);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+schedule_scheduleList+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public ScheduleDetailHis getdetail(Integer scheduleHisId) {
		ScheduleDetailHis scheduleHis=new ScheduleDetailHis();
		JSONObject json=new JSONObject();
        json.put("scheduleId", scheduleHisId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+schedule_scheduleDetail+params, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				scheduleHis=jsonObject.toJavaObject(ScheduleDetailHis.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return scheduleHis;
	}

	@Override
	public void add(ScheduleDetailHis scheduleHis) {		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(scheduleHis).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+schedule_add+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(ScheduleDetailHis scheduleHis) {
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(scheduleHis).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+schedule_update+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void del(Integer scheduleHisId) {
		JSONObject json=new JSONObject();
        json.put("scheduleHisId", scheduleHisId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doDelete(bmw_cloud_baseservice_url+schedule_del+params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
