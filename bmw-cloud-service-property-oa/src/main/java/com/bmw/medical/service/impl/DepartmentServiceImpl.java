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
import com.bmw.medical.model.Department;
import com.bmw.medical.model.Schedule;
import com.bmw.medical.service.IDepartmentService;

@Service
public class DepartmentServiceImpl implements IDepartmentService {
	@Value("${bmw.cloud.medicalservice.url}")
	private String bmw_cloud_baseservice_url = "";
	@Value("${department.departmentList}")
	private String department_departmentList = "";
	@Value("${department.departmentDetail}")
	private String department_departmentDetail = "";
	@Value("${department.add}")
	private String department_add = "";
	@Value("${department.update}")
	private String department_update = "";
	@Value("${department.del}")
	private String department_del = "";
	@Value("${department.tree}")
	private String department_tree = "";
	@Value("${department.countCode}")
	private String department_countCode = "";
	@Value("${department.findByCode}")
	private String department_findByCode = "";
	@Value("${department.updateChildren}")
	private String updateChildren = "";
	@Value("${department.getTreeList}")
	private String department_getTreeList = "";

	@Override
	public JSONArray findList(Department department) {
		JSONArray jsonArray = null;
		try {
			String params = Base64Utils.encodeToString(JSONObject.toJSONString(department).toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doGet(bmw_cloud_baseservice_url + department_departmentList + params);
			jsonArray = JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	public Department selectById(Integer departmentId) {
		Department department = new Department();
		JSONObject json = new JSONObject();
		json.put("departmentId", departmentId);
		try {
			String params = Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url + department_departmentDetail + params, null);
			JSONObject jsonObject = JSON.parseObject(rlt).getJSONObject("items");
			if (jsonObject != null) {
				department = jsonObject.toJavaObject(Department.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return department;
	}

	@Override
	public void add(Department department) {
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url + department_add, JSONObject.toJSONString(department).toString(),
					null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Department department) {
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url + department_update,
					JSONObject.toJSONString(department).toString(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void del(Integer departmentId) {
		JSONObject json = new JSONObject();
		json.put("departmentId", departmentId);
		try {
			String params = Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url + department_del + params, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ZTreeNode> departmentTreeList(String hospitalCode) {
		List<ZTreeNode> list = new ArrayList<ZTreeNode>();
		JSONArray jsonArray = null;
		try {
			String rlt = HttpUtils.doGet(bmw_cloud_baseservice_url + department_tree + "/" + hospitalCode);
			jsonArray = JSON.parseObject(rlt).getJSONArray("items");
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject ob = (JSONObject) jsonArray.get(i);
				// 得到json对象
				if (0 == ob.getLong("pId")) {
					// 如果是顶级节点，就在名称后面加上医院的名字
					ob.replace("name", ob.getString("name") + "(" + ob.getString("ext_attr") + ")");
				}
				ZTreeNode tree = JSONObject.toJavaObject(ob, ZTreeNode.class);
				list.add(tree);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<ZTreeNode> getTreeList() {
		List<ZTreeNode> treeList=new ArrayList<>();
		 try {
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url + department_getTreeList, "", null);
			treeList=JSONObject.parseObject(rlt).getJSONArray("items").toJavaList(ZTreeNode.class);
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		 return treeList;
	}
	
	

	@Override
	public void updateChildren(String oldPcode, String newPcode) {
		JSONObject json = new JSONObject();
		json.put("oldPcode", oldPcode);
		json.put("newPcode", newPcode);
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url + updateChildren,
					json.toJSONString(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String departmentCountCode(String code) {
		JSONObject jsonArray = null;
		try {
			String rlt = HttpUtils.doGet(bmw_cloud_baseservice_url + department_countCode + code);
			jsonArray = JSON.parseObject(rlt).getJSONObject("items");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String count = jsonArray.getString("data").toString();
		
		return String.valueOf(Integer.valueOf(count)+1);
	}

	@Override
	public Department findByCode(String code) {
		Department department = null;
		try {
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url + department_findByCode + code, null);
			JSONObject obj = JSON.parseObject(rlt).getJSONObject("items");
			department = JSON.parseObject(obj.toJSONString(), Department.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return department;
	}
}