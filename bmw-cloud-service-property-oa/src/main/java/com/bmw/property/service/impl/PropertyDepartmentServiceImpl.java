package com.bmw.property.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.Department;
import com.bmw.property.service.IDepartmentService;

@Service
public class PropertyDepartmentServiceImpl implements IDepartmentService {


	@Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_medicalservice_url="";
	
	@Value("${propertydepartment.departmentList}")
	private String department_departmentList="";
	
	@Value("${propertydepartment.departmentDetail}")
	private String department_departmentDetail="";
	
	@Value("${propertydepartment.add}")
	private String department_add="";
	
	@Value("${propertydepartment.update}")
	private String department_update="";
	
	@Value("${propertydepartment.del}")
	private String department_del="";

	@Value("${propertydepartment.tree}")
	private String department_tree="";
	
	@Value("${department.treeByParentEId}")
	private String department_treeByParentEId="";
	
	@Value("${propertydepartment.countCode}")
	private String department_countCode="";
	
	@Value("${propertydepartment.countIsCode}")
	private String department_countIsCode="";
	
	@Value("${propertydepartment.codeSelect}")
	private String department_codeSelect="";
	
	
	@Value("${propertydepartment.deleteUpdate}")
	private String department_deleteUpdate="";
	

	@Override
	public JSONArray findList(String condition) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
		int isadmin = 0;
		ShiroExt shiro = new ShiroExt();
		Integer parenteid = shiro.getUser().getParentEId();
		if (shiro.getUser().getIsAdmin()) {
			isadmin = 1;
		}
		String rlt= "";
		json.put("name", condition);
		if(1==isadmin) {
			json.put("parenteid",0);
		}else {
			json.put("parenteid",parenteid);
		}
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
    		rlt=HttpUtils.doGet(bmw_cloud_medicalservice_url+department_departmentList+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
//			if(json.size()>0){  
//				  for(int i=0;i<jsonArray.size();i++){  
//					  JSONObject job = jsonArray.getJSONObject(i);   
//				  }  
//			}  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public Department getdetail(Integer departmentId) {
		Department department=new Department();
		JSONObject json=new JSONObject();
		json.put("departmentId", departmentId);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doPost(bmw_cloud_medicalservice_url+department_departmentDetail+params, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				department=jsonObject.toJavaObject(Department.class);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return department;
	}

	@Override
	public String add(Department department) {
		String result = "1";
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(department).toString().getBytes("UTF-8"));
			List<NameValuePair> namevalue= new ArrayList<NameValuePair>();
			NameValuePair item = new BasicNameValuePair("params", params);
			namevalue.add(item);
    		String rlt = HttpUtils.doPost(bmw_cloud_medicalservice_url+department_add, namevalue);
    		String flag = JSONObject.parseObject(rlt).getString("items");
			if("ERRO".equals(flag)) {
		    	result = "0";
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String update(Department department) {
		String result = "1";
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(department).toString().getBytes("UTF-8"));
    		List<NameValuePair> namevalue= new ArrayList<NameValuePair>();
			NameValuePair item = new BasicNameValuePair("params", params);
			namevalue.add(item);
    		String rlt = HttpUtils.doPost(bmw_cloud_medicalservice_url+department_update, namevalue);
    		String flag = JSONObject.parseObject(rlt).getString("items");
			if("ERRO".equals(flag)) {
		    	result = "0";
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String del(Integer departmentId) {
		JSONObject json=new JSONObject();
        json.put("departmentId", departmentId);
        String result = "";
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doPost(bmw_cloud_medicalservice_url+department_del+params,null);
			result = JSON.parseObject(rlt).getString("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public List<ZTreeNode> departmentTreeList() {
		List<ZTreeNode> list = new ArrayList();
		int isadmin = 0;
		ShiroExt shiro = new ShiroExt();
		Integer parenteid = shiro.getUser().getParentEId();
		if (shiro.getUser().getIsAdmin()) {
			isadmin = 1;
		}
    	JSONArray jsonArray=null; 
    	String rlt= "";
    	try {
    		if(1==isadmin) {
    			rlt=HttpUtils.doGet(bmw_cloud_medicalservice_url+department_tree+"/1");
    		}else {
    			rlt=HttpUtils.doGet(bmw_cloud_medicalservice_url+department_treeByParentEId+parenteid);
    		}
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
			for(int i=0;i<jsonArray.size();i++) {
				JSONObject ob  = (JSONObject) jsonArray.get(i);//得到json对象
				jsonArray.get(i);
				ZTreeNode tree=JSONObject.toJavaObject(ob, ZTreeNode.class);
				list.add(tree);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public String departmentCountCode(String code) {
		JSONObject jsonArray=null; 
		try {
		String rlt=HttpUtils.doGet(bmw_cloud_medicalservice_url+department_countCode+code);
		jsonArray=JSON.parseObject(rlt).getJSONObject("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray.getString("data").toString();
	}
	
	@Override
	public String departmentCountIsCode(String code) {
		JSONObject jsonArray=null; 
		try {
		String rlt=HttpUtils.doGet(bmw_cloud_medicalservice_url+department_countIsCode+code);
		jsonArray=JSON.parseObject(rlt).getJSONObject("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray.getString("data").toString();
	}
	
	@Override
	public String departmentCodeSelect(String code) {
		JSONObject jsonArray=null; 
		try {
		String rlt=HttpUtils.doGet(bmw_cloud_medicalservice_url+department_codeSelect+code);
		jsonArray=JSON.parseObject(rlt).getJSONObject("items");	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray.getString("data").toString();
	}
	
	@Override
	public Department departmentDeleteUpdate(String code) {
		JSONObject obj=null;
		Department department = new Department();
		try{
			String rlt=HttpUtils.doPost(bmw_cloud_medicalservice_url+department_deleteUpdate+code , null);
			obj=JSON.parseObject(rlt).getJSONObject("items");	
			if(null!=obj) {
				department = obj.toJavaObject(Department.class);
			}
		}catch(Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return department;
	}
	
	 /**
	 * JSON转Java实体
	 * @author jmy
	 * 2018年04月19日 
	 * */
	 public static<T> Object JSONToObj(String jsonStr,Class<T> obj) {
	        T t = null;
	        try {
	            ObjectMapper objectMapper = new ObjectMapper();
	            t = objectMapper.readValue(jsonStr,
	                    obj);
	        } catch (Exception e) {
	            e.printStackTrace(); 
	        }
	        return t;
	    }
	
}
