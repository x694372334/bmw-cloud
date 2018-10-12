package com.bmw.medical.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.bmw.medical.model.DeptDoctorRelation;
import com.bmw.medical.service.IDeptDoctorRelationService;

@Service
public class DeptDoctorRelationServiceImpl implements IDeptDoctorRelationService {
	@Value("${bmw.cloud.medicalservice.url}")
	private String bmw_cloud_baseservice_url = "";
	@Value("${deptDoctorRelation.findByDeptCode}")
	private String findByDeptCode = "";
	@Value("${deptDoctorRelation.addList}")
	private String addList = "";
	@Value("${deptDoctorRelation.delDoctor}")
	private String delDoctor = "";
	@Value("${deptDoctorRelation.submitTitle}")
	private String submitTitle = "";

	public Object findByDeptCode(String deptCode, String name) throws ParseException, IOException {
		JSONObject json = new JSONObject();
		json.put("code", null == deptCode ? "" : deptCode);
		json.put("name", null == name ? "" : name);
		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
		String rlt = HttpUtils.doGet(bmw_cloud_baseservice_url + findByDeptCode + params, null);
		return JSON.parseObject(rlt).getJSONArray("items");
	}

	public Object addList(List<DeptDoctorRelation> list) {
		try {
			return HttpUtils.doPost(bmw_cloud_baseservice_url + addList, JSONObject.toJSONString(list).toString(), null);
		} catch (ParseException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void delDoctor(String[] arr) {
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url + delDoctor, JSONObject.toJSONString(arr).toString(), null);
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
	}

	public void submitTitle(String doctorIds, Integer titleId, String titleName) {
		JSONObject json = new JSONObject();
		json.put("doctorIds", null == doctorIds ? "" : doctorIds);
		json.put("titleId", null == titleId ? "" : titleId);
		json.put("titleName", null == titleName ? "" : titleName);
		String params = null;
		try {
			params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		try {
			HttpUtils.doGet(bmw_cloud_baseservice_url + submitTitle + params, null);
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
	}
}