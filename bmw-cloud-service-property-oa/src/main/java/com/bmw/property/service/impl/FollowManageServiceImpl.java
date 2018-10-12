package com.bmw.property.service.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.bmw.property.model.FollowManage;
import com.bmw.property.service.IFollowManageService;

/**
 * 类名: FollowManageServiceImpl  
 * 类描述: 跟进管理 服务实现类
 * 创建人: wangliqing
 * 创建时间 : 2018年6月26日 下午2:41:25    
 */
@Service
public class FollowManageServiceImpl implements IFollowManageService{

	
	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_propertyservice_url;
	
	@Value("${followManage.selectList}")
	private String followManage_selectList;
	
	@Value("${followManage.insert}")
	private String followManage_insert;
	
	@Value("${followManage.updateById}")
	private String followManage_updateById;
	
	@Value("${followManage.deleteById}")
	private String followManage_deleteById;
	
	@Value("${followManage.selectById}")
	private String followManage_selectById; 
	
	/**
	 * TODO 根据id查询跟进管理记录
	 * @see com.bmw.property.service.IFollowManageService#selectById(Integer)
	 */
	@Override
	public FollowManage selectById(Integer followManageId) {
		FollowManage followManage = new FollowManage();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_propertyservice_url+followManage_selectById+followManageId);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				followManage=jsonObject.toJavaObject(FollowManage.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return followManage;
	}

	/**
	 * TODO 获取跟进管理列表(分页)
	 * @see com.bmw.property.service.IFollowManageService#selectList(Map)
	 */
	@Override
	public JSONObject selectList(Map<String,Object> param) {
		JSONObject paramOjbect=new JSONObject();
		paramOjbect.put("pageNum", param.get("pageNum"));
		paramOjbect.put("pageSize", param.get("pageSize"));
		paramOjbect.put("ownerName", param.get("ownerName"));
		paramOjbect.put("phone", param.get("phone"));
		paramOjbect.put("receiverName", param.get("receiverName"));
		paramOjbect.put("orderByField", param.get("verifierId"));
		paramOjbect.put("isAsc", param.get("isAsc"));
		paramOjbect.put("eId", ShiroKit.getUser().geteId());
		JSONObject jsonrlt=new JSONObject();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_propertyservice_url+followManage_selectList+new String(Base64Utils.encode(paramOjbect.toString().getBytes("UTF-8"))));
			jsonrlt = JSONObject.parseObject(rlt).getJSONObject("items");
    	} catch (Exception e) {
    		e.printStackTrace();
		} 
    	return jsonrlt;
	}

	/**
	 * TODO 新增跟进管理记录
	 * @see com.bmw.property.service.IFollowManageService#insert(com.bmw.property.model.FollowManage)
	 */
	@Override
	public void insert(FollowManage followManage) {
		followManage.preInsert();
		followManage.seteId(ShiroKit.getUser().geteId());
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp contact = Timestamp.valueOf(sdf.format(followManage.getContact()));
		followManage.setContactTime(contact);
		try {
			HttpUtils.doPost(bmw_cloud_propertyservice_url+followManage_insert, HttpUtils.convertParam(followManage));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * TODO 删除跟进管理记录(逻辑删除)
	 * @see com.bmw.property.service.IFollowManageService#deleteById(Integer)
	 */
	@Override
	public void deleteById(Integer followManageId) {
		JSONObject json=new JSONObject();
        json.put("id", followManageId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doDelete(bmw_cloud_propertyservice_url+followManage_deleteById+params,null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * TODO 修改跟进管理记录
	 * @see com.bmw.property.service.IFollowManageService#updateById(com.bmw.property.model.FollowManage)
	 */
	@Override
	public void updateById(FollowManage followManage) {
		followManage.preUpdate();
		followManage.seteId(ShiroKit.getUser().geteId());
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp contact = Timestamp.valueOf(sdf.format(followManage.getContact()));
		followManage.setContactTime(contact);
		try {
			HttpUtils.doPost(bmw_cloud_propertyservice_url+followManage_updateById, HttpUtils.convertParam(followManage));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
}
