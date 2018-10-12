package com.stylefeng.guns.modular.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.modular.system.model.Relation;
import com.stylefeng.guns.modular.system.service.IRelationService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;


/**  
* <p>Title: RelationServiceImpl</p>  
* <p>Description: 角色关联实现类</p>  
* @author lyl  
* @date 2018年4月26日  
*/  
@Service
public class RelationServiceImpl implements IRelationService {
	
	@Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_baseservice_url="";

	
	@Value("${relation.add}")
	private String relation_add="";
	
	@Value("${relation.addAppAssign}")
	private String relation_addAppAssign="";
	
	@Value("${relation.delByMenuId}")
	private String relation_delByMenuId="";
	
	@Value("${relation.delByRoleId}")
	private String relation_delByRoleId="";
	
	
	@Override
	public void deleteRelationByMenu(Long menuId) {
		try {
			HttpUtils.doDelete(bmw_cloud_baseservice_url+relation_delByMenuId+menuId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	@Override
	public void deleteRelationByRole(Integer roleId) {
		try {
			HttpUtils.doDelete(bmw_cloud_baseservice_url+relation_delByRoleId+roleId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	@Override
	public void add(Relation relation) {
		try {
			String jsonStr=JSONObject.toJSONString(relation);
			String params=new String(Base64Utils.encode(jsonStr.getBytes("UTF-8")));
			HttpUtils.doPost(bmw_cloud_baseservice_url+relation_add+params,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	public void addAppAssign(Integer roleId, String ids) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		NameValuePair nameValuePair = new BasicNameValuePair("roleId", roleId.toString());
		NameValuePair nameValuePair2 = new BasicNameValuePair("menuIds", ids);
		params.add(nameValuePair);
		params.add(nameValuePair2);
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url+relation_addAppAssign,params);
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
