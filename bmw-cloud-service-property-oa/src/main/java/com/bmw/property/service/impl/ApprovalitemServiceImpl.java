package com.bmw.property.service.impl;

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
import com.bmw.property.model.Approvalitem;
import com.bmw.property.service.IApprovalitemService;


@Service
public class ApprovalitemServiceImpl implements IApprovalitemService {

	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_baseservice_url="";

	@Value("${approvalitem.approvalitemList}")
	private String approvalitem_approvalitemList="";

	@Value("${approvalitem.approvalitemDetail}")
	private String approvalitem_approvalitemDetail="";

	@Value("${approvalitem.add}")
	private String approvalitem_add="";

	@Value("${approvalitem.update}")
	private String approvalitem_update="";

	@Value("${approvalitem.del}")
	private String approvalitem_del="";



	@Override
	public JSONArray findList(String condition) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
//		json.put("aName", condition);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+approvalitem_approvalitemList+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public Approvalitem getdetail(Integer approvalitemId) {
		Approvalitem approvalitem=new Approvalitem();
		JSONObject json=new JSONObject();
        json.put("approvalitemId", approvalitemId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+approvalitem_approvalitemDetail+params, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				approvalitem=jsonObject.toJavaObject(Approvalitem.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return approvalitem;
	}

	@Override
	public void add(Approvalitem approvalitem) {
		ShiroExt shiro = new ShiroExt();
		approvalitem.setCreateMan(shiro.getUser().getName());
		approvalitem.setCreateManId(shiro.getUser().getId());
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url+approvalitem_add, HttpUtils.convertParam(approvalitem));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(Approvalitem approvalitem) {
		ShiroExt shiro = new ShiroExt();
		approvalitem.setEditMan(shiro.getUser().getName());
		approvalitem.setEditManId(shiro.getUser().getId());
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url+approvalitem_update, HttpUtils.convertParam(approvalitem));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void del(Integer approvalitemId) {
		JSONObject json=new JSONObject();
        json.put("approvalitemId", approvalitemId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doDelete(bmw_cloud_baseservice_url+approvalitem_del+params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}