package com.bmw.medical.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.medical.model.Schedule;
import com.bmw.medical.service.IScheduleService;

@Service
public class ScheduleServiceImpl implements IScheduleService {

	@Value("${bmw.cloud.medicalservice.url}")
	private String bmw_cloud_baseservice_url = "";

	@Value("${schedule.scheduleList}")
	private String schedule_scheduleList = "";

	@Value("${schedule.scheduleDetail}")
	private String schedule_scheduleDetail = "";

	@Value("${schedule.add}")
	private String schedule_add = "";

	@Value("${schedule.update}")
	private String schedule_update = "";

	@Value("${schedule.del}")
	private String schedule_del = "";
	
	@Value("${schedule.getTreeList}")
	private String schedule_getTreeList = "";

	public JSONArray findList(Schedule schedule) {
		JSONArray jsonArray = null;
		try {
			String params = Base64Utils.encodeToString(JSONObject.toJSONString(schedule).toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doGet(bmw_cloud_baseservice_url + schedule_scheduleList + params);
			jsonArray = JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	public Schedule getdetail(Integer scheduleId) {
		Schedule schedule = new Schedule();
		JSONObject json = new JSONObject();
		json.put("scheduleId", scheduleId);
		try {
			String params = Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url + schedule_scheduleDetail + params, null);
			JSONObject jsonObject = JSON.parseObject(rlt).getJSONObject("items");
			if (jsonObject != null) {
				schedule = jsonObject.toJavaObject(Schedule.class);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return schedule;
	}

	@Override
	public void add(Schedule schedule) {
		schedule.setCreateMan("admin");
		schedule.setCreateManId(1);
		schedule.setEditMan("admin");
		schedule.setEditManId(1);
		schedule.setEditTime(new Date());
		schedule.setCreateTime(new Date());
		schedule.setIsDelete(0);
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url + schedule_add,JSONObject.toJSONString(schedule).toString(), null);
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Schedule schedule) {
		schedule.setEditMan("admin");
		schedule.setEditManId(1);
		schedule.setEditTime(new Date());
		schedule.setIsDelete(0);
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url + schedule_update, JSONObject.toJSONString(schedule).toString(),
					null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void del(Integer scheduleId) {
		JSONObject json = new JSONObject();
		json.put("scheduleId", scheduleId);
		try {
			String params = Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doDelete(bmw_cloud_baseservice_url + schedule_del + params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ZTreeNode> getTreeList() {
		List<ZTreeNode> treeList=new ArrayList<>();
		 try {
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url + schedule_getTreeList, "", null);
			treeList=JSONObject.parseObject(rlt).getJSONArray("items").toJavaList(ZTreeNode.class);
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		 return treeList;
	}

}
