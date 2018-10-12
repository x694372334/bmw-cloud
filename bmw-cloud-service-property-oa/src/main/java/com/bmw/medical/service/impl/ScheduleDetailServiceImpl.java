package com.bmw.medical.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.bmw.medical.model.ScheduleDetail;
import com.bmw.medical.service.IScheduleDetailService;

@Service
public class ScheduleDetailServiceImpl implements IScheduleDetailService {

	@Value("${bmw.cloud.medicalservice.url}")
	private String bmw_cloud_baseservice_url = "";

	@Value("${scheduleDetail.scheduleDetailList}")
	private String scheduleDetail_scheduleList = "";
	
	@Value("${scheduleDetail.scheduleDetailAllList}")
	private String scheduleDetail_scheduleAllList = "";

	@Value("${scheduleDetail.scheduleDetailDetail}")
	private String scheduleDetail_scheduleDetail = "";

	@Value("${scheduleDetail.add}")
	private String scheduleDetail_add = "";

	@Value("${scheduleDetail.update}")
	private String scheduleDetail_update = "";

	@Value("${scheduleDetail.del}")
	private String scheduleDetail_del = "";

	@Override
	public JSONArray findAllList(ScheduleDetail scheduleDetail ) {
		JSONArray returnArray=new JSONArray();
		JSONArray jsonArray = null;
		
		try {
			String params = Base64Utils.encodeToString(JSON.toJSONString(scheduleDetail).toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doGet(bmw_cloud_baseservice_url + scheduleDetail_scheduleAllList + params);
			jsonArray = JSON.parseObject(rlt).getJSONArray("items");
			
			JSONObject json = new JSONObject();
			for (Iterator iterator = jsonArray.iterator(); iterator.hasNext();) {
			      JSONObject jsonObject = (JSONObject) iterator.next();
			      json=new JSONObject();
			      json.put("departmentName", jsonObject.getString("departmentName"));
			      json.put("doctorName", jsonObject.getString("doctorName"));
			      JSONArray scheduleDetails= jsonObject.getJSONArray("scheduleDetails");
			      for (Iterator iterator1= scheduleDetails.iterator(); iterator1.hasNext();) {
				      JSONObject details= (JSONObject) iterator1.next();
				      json.putAll(details); 
			      }
			      returnArray.add(json);
			    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnArray;
	}
	
	public JSONArray findList(ScheduleDetail scheduleDetail ) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
		json.put("medicalDepartmentCode", scheduleDetail.getMedicalDepartmentCode());
		json.put("medicalDoctorCode", scheduleDetail.getMedicalDoctorCode());
		json.put("scheduleDate", scheduleDetail.getScheduleDate());
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+scheduleDetail_scheduleList+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public ScheduleDetail getdetail(Integer scheduleId) {
		ScheduleDetail scheduleDetail = new ScheduleDetail();
		JSONObject json = new JSONObject();
		json.put("scheduleDetailId", scheduleId);
		try {
			String params = Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url + scheduleDetail_scheduleDetail + params, null);
			JSONObject jsonObject = JSON.parseObject(rlt).getJSONObject("items");
			if (jsonObject != null) {
				scheduleDetail = jsonObject.toJavaObject(ScheduleDetail.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scheduleDetail;
	}

	@Override
	public void add(ScheduleDetail scheduleDetail) {
		scheduleDetail.setCreateMan("admin");
		scheduleDetail.setCreateManId(1);
		scheduleDetail.setEditMan("admin");
		scheduleDetail.setEditManId(1);
		scheduleDetail.setEditTime(new Date());
		scheduleDetail.setCreateTime(new Date());
		scheduleDetail.setIsDelete(0);
		
		String scheduleStartTime = scheduleDetail.getScheduleStartTime()+":00";
		scheduleDetail.setScheduleStartTime(scheduleStartTime);
		
		if(StringUtils.isBlank(scheduleDetail.getScheduleEndTime())) {
			
			SimpleDateFormat formatter=new SimpleDateFormat("HH:mm:ss"); 
			try {
				String scheduleEndTime = formatter.format(new Date(formatter.parse(scheduleStartTime).getTime()+ 60*60*1000L));
				scheduleDetail.setScheduleEndTime(scheduleEndTime);
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			} 
		}
		
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url + scheduleDetail_add,JSONObject.toJSONString(scheduleDetail).toString(), null);
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(ScheduleDetail scheduleDetail) {
		scheduleDetail.setEditMan("admin");
		scheduleDetail.setEditManId(1);
		scheduleDetail.setEditTime(new Date());
		scheduleDetail.setIsDelete(0);
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url + scheduleDetail_update, JSONObject.toJSONString(scheduleDetail).toString(),
					null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void del(Integer scheduleDetailId) {
		JSONObject json = new JSONObject();
		json.put("scheduleDetailId", scheduleDetailId);
		try {
			String params = Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doDelete(bmw_cloud_baseservice_url + scheduleDetail_del + params);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
