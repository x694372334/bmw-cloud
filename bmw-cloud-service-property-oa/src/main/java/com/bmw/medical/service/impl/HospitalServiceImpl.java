package com.bmw.medical.service.impl;

import java.io.IOException;
import java.util.ArrayList;
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
import com.bmw.medical.model.Hospital;
import com.bmw.medical.service.IHospitalService;

@Service
public class HospitalServiceImpl implements IHospitalService {

	@Value("${bmw.cloud.medicalservice.url}")
	private String bmw_cloud_baseservice_url = "";

	@Value("${hospital.hospitalList}")
	private String hospital_hospitalList = "";

	@Value("${hospital.hospitalDetail}")
	private String hospital_hospitalDetail = "";

	@Value("${hospital.add}")
	private String hospital_add = "";

	@Value("${hospital.update}")
	private String hospital_update = "";

	@Value("${hospital.del}")
	private String hospital_del = "";

	@Value("${hospital.tree}")
	private String area_tree = "";
	
	@Value("${hospital.findCode}")
	private String hospital_findCode = "";

	@Override
	public JSONArray findList(String condition) {
		JSONArray jsonArray = null;
		Hospital hospital = new Hospital();
		hospital.setName(condition);
		try {
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url + hospital_hospitalList ,JSON.toJSONString(hospital), null);
			jsonArray = JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	public Hospital getdetail(Integer hospitalId) {
		Hospital hospital = new Hospital();
		JSONObject json = new JSONObject();
		json.put("hospitalId", hospitalId);
		try {
			String params = Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url + hospital_hospitalDetail + params, null);
			JSONObject jsonObject = JSON.parseObject(rlt).getJSONObject("items");
			if (jsonObject != null) {
				hospital = jsonObject.toJavaObject(Hospital.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hospital;
	}

	@Override
	public void add(Hospital hospital) {
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url + hospital_add,
					JSONObject.toJSONString(hospital).toString(), null);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Hospital hospital) {
			 try {
				HttpUtils.doPost(bmw_cloud_baseservice_url + hospital_update,
						JSONObject.toJSONString(hospital).toString(), null);
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	@Override
	public void del(Integer hospitalId) {
		JSONObject json = new JSONObject();
		json.put("hospitalId", hospitalId);
		try {
			String params = Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url + hospital_del + params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<ZTreeNode> areaTreeList() {
		List<ZTreeNode> list = new ArrayList();
		JSONArray jsonArray = null;
		try {
			String rlt = HttpUtils.doGet(bmw_cloud_baseservice_url + area_tree + "/1");
			jsonArray = JSON.parseObject(rlt).getJSONArray("items");
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject ob = (JSONObject) jsonArray.get(i);// 得到json对象
				jsonArray.get(i);
				ZTreeNode tree = JSONObject.toJavaObject(ob, ZTreeNode.class);
				list.add(tree);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public String findCode() {
		String count = "";
		try {
			String rlt = HttpUtils.doGet(bmw_cloud_baseservice_url + hospital_findCode);
			JSONObject jsonObject = JSON.parseObject(rlt).getJSONObject("items");
			count = jsonObject.get("count").toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}


}
