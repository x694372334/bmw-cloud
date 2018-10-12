package com.bmw.property.service.impl;

import com.bmw.property.model.TAppPropertyMenu;
import com.bmw.property.service.ITAppPropertyMenuService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.beetl.ShiroExt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

/**
 * <p>
 * app物业菜单表 服务实现类
 * </p>
 *
 * @author zhangt123
 * @since 2018-07-26
 */
@Service
public class TAppPropertyMenuServiceImpl  implements ITAppPropertyMenuService {

	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_baseservice_url="";

	@Value("${tAppPropertyMenu.tAppPropertyMenuList}")
	private String tAppPropertyMenu_tAppPropertyMenuList="";

	@Value("${tAppPropertyMenu.tAppPropertyMenuDetail}")
	private String tAppPropertyMenu_tAppPropertyMenuDetail="";

	@Value("${tAppPropertyMenu.add}")
	private String tAppPropertyMenu_add="";

	@Value("${tAppPropertyMenu.update}")
	private String tAppPropertyMenu_update="";

	@Value("${tAppPropertyMenu.del}")
	private String tAppPropertyMenu_del="";

	@Value("${relation.findListByRoleId}")
	private String relation_findListByRoleId="";
	@Override
	public JSONArray findList(TAppPropertyMenu condition) {
		JSONArray jsonArray=null;
		JSONObject json = (JSONObject) JSONObject.toJSON(condition);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+tAppPropertyMenu_tAppPropertyMenuList+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public TAppPropertyMenu getdetail(Integer tAppPropertyMenuId) {
		TAppPropertyMenu tAppPropertyMenu=new TAppPropertyMenu();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+tAppPropertyMenu_tAppPropertyMenuDetail+tAppPropertyMenuId);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				tAppPropertyMenu=jsonObject.toJavaObject(TAppPropertyMenu.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return tAppPropertyMenu;
	}

	@Override
	public void add(TAppPropertyMenu tAppPropertyMenu) {
		ShiroExt shiro = new ShiroExt();
		tAppPropertyMenu.setCreateMan(shiro.getUser().getName());
		tAppPropertyMenu.setCreateManId(shiro.getUser().getId());
		tAppPropertyMenu.setCreateTime(new Date());
		String[] uuid = UUID.randomUUID().toString().split("-");
		//tAppPropertyMenu.setmId(uuid);
		try {
    		JSONObject json = (JSONObject) JSONObject.toJSON(tAppPropertyMenu);
            String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			List<NameValuePair> namevalue= new ArrayList<NameValuePair>();
			NameValuePair item = new BasicNameValuePair("param", params);
			namevalue.add(item);
			String result = HttpUtils.doPost(bmw_cloud_baseservice_url+tAppPropertyMenu_add, namevalue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(TAppPropertyMenu tAppPropertyMenu) {
		try {
    		JSONObject json = (JSONObject) JSONObject.toJSON(tAppPropertyMenu);
            String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			List<NameValuePair> namevalue= new ArrayList<NameValuePair>();
			NameValuePair item = new BasicNameValuePair("param", params);
			namevalue.add(item);
			String result = HttpUtils.doPost(bmw_cloud_baseservice_url+tAppPropertyMenu_update, namevalue);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void del(Integer tAppPropertyMenuId) {
		List<NameValuePair> namevalue= new ArrayList<NameValuePair>();
		NameValuePair item = new BasicNameValuePair("param", tAppPropertyMenuId.toString());
		namevalue.add(item);
		try {
			 HttpUtils.doPost(bmw_cloud_baseservice_url+tAppPropertyMenu_del,namevalue);
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public JSONArray findListByRoleId(Integer id) {
		String rlt ="";
		JSONArray jsonArray=null;
		try {
		    rlt = HttpUtils.doGet(bmw_cloud_baseservice_url+relation_findListByRoleId+id,null);
		    jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonArray;
	}
}
