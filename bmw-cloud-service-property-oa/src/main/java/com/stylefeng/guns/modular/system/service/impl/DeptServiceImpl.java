package com.stylefeng.guns.modular.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.model.Dept;
import com.stylefeng.guns.modular.system.model.Dict;
import com.stylefeng.guns.modular.system.service.IDeptService;

import org.apache.commons.collections.map.HashedMap;
import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.annotation.Resource;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DeptServiceImpl implements IDeptService {
	
	@Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_baseservice_url="";
	
	@Value("${dept.deptList}")
	private String dept_deptList="";
	
	@Value("${dept.deptListById}")
	private String dept_deptListById="";
	
	@Value("${dept.deptDetail}")
	private String dept_deptDetail="";
	
	@Value("${dept.deptTree}")
	private String dept_deptTree="";
	
	@Value("${dept.add}")
	private String dept_add="";
	
	@Value("${dept.update}")
	private String dept_update="";
	
	@Value("${dept.del}")
	private String dept_del="";

    @Override
    public void deleteDept(Integer deptId) {
    	JSONObject json=new JSONObject();
        json.put("deptId", deptId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+dept_del+params,null);
			//缓存被删除的部门名称
	        LogObjectHolder.me().set(ConstantFactory.me().getDeptName(deptId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public JSONArray tree() {
    	JSONArray jsonArray=null;
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+dept_deptTree);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
    }

    @Override
    public JSONArray list(String condition) {
    	JSONArray jsonArray=null;
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+dept_deptList+condition);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
    }
    
    
    @Override
    public JSONArray listById(Integer deptId) {
    	JSONArray jsonArray=null;
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+dept_deptListById+deptId);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
    }
    
    @Override
    public Dept deptDetail(Integer deptId) {
    	Dept dept=new Dept();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+dept_deptDetail+deptId);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				dept=jsonObject.toJavaObject(Dept.class);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return dept;
    }
    
    @Override
    public Boolean add(Dept dept) {
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(dept).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+dept_add+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return true;
    }
    
    @Override
    public Boolean update(Dept dept) {
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(dept).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+dept_update+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return true;
    }
}
