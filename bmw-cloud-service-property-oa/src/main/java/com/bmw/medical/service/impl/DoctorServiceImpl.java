package com.bmw.medical.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.medical.model.Doctor;
import com.bmw.medical.service.IDoctorService;

@Service
public class DoctorServiceImpl implements IDoctorService {

	@Value("${bmw.cloud.medicalservice.url}")
	private String bmw_cloud_baseservice_url = "";

	@Value("${doctor.doctorList}")
	private String doctor_doctorList = "";

	@Value("${doctor.doctorDetail}")
	private String doctor_doctorDetail = "";

	@Value("${doctor.add}")
	private String doctor_add = "";

	@Value("${doctor.update}")
	private String doctor_update = "";

	@Value("${doctor.del}")
	private String doctor_del = "";

	@Value("${doctor.findCode}")
	private String doctor_findCode = "";

	@Value("${doctor.tree}")
	private String doctor_tree = "";

	@Value("${doctor.findAddList}")
	private String findAddList = "";

	@Override
	public JSONArray findList(String condition) {
		JSONArray jsonArray = null;
		JSONObject json = new JSONObject();
		json.put("name", condition);
		try {
			String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doGet(bmw_cloud_baseservice_url + doctor_doctorList + params);
			jsonArray = JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	public Doctor getdetail(Integer doctorId) {
		Doctor doctor = new Doctor();
		JSONObject json = new JSONObject();
		json.put("doctorId", doctorId);
		try {
			String params = Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url + doctor_doctorDetail + params, null);
			JSONObject jsonObject = JSON.parseObject(rlt).getJSONObject("items");
			if (jsonObject != null) {
				doctor = jsonObject.toJavaObject(Doctor.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctor;
	}

	@Override
	public void add(Doctor doctor) {
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url + doctor_add,
					JSONObject.toJSONString(doctor).toString(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Doctor doctor) {
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url + doctor_update,
					JSONObject.toJSONString(doctor).toString(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void del(Integer doctorId) {
		JSONObject json = new JSONObject();
		json.put("doctorId", doctorId);
		try {
			String params = Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doDelete(bmw_cloud_baseservice_url + doctor_del + params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String findCode() {
		String count = "";
		try {
			String rlt = HttpUtils.doGet(bmw_cloud_baseservice_url + doctor_findCode);
			JSONObject jsonObject = JSON.parseObject(rlt).getJSONObject("items");
			count = jsonObject.get("count").toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public Object findAddList(String deptCode) {
		JSONArray jsonArray = null;
		JSONObject json = new JSONObject();
		json.put("name", deptCode);
		try {
			String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doGet(bmw_cloud_baseservice_url + findAddList + params);
			jsonArray = JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}
}