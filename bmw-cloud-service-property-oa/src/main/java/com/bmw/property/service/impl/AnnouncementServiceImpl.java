package com.bmw.property.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.bmw.bill.model.BillVO;
import com.bmw.property.model.Announcement;
import com.bmw.property.service.IAnnouncementService;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

/**
 * <p>
 * 系统公告 服务实现类
 * </p>
 *
 * @author zhangt123
 * 
 * @since 2018-07-06
 */
@Service
public class AnnouncementServiceImpl implements IAnnouncementService {

	@Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_baseservice_url="";
	
	@Value("${announcement.announcementList}")
	private String announcement_announcementList="";
	
	@Value("${announcement.add}")
	private String announcement_add="";
	
	@Value("${announcement.announcementDetail}")
	private String announcement_announcementDetail="";
	
	@Value("${announcement.del}")
	private String announcement_del="";
	
	@Value("${announcement.update}")
	private String announcement_update="";
	
	@Value("${announcement.updateHaveRead}")
	private String announcement_updateHaveRead="";
	
	@Override
	public JSONArray findList(Announcement condition) {
		JSONArray jsonArray=null;
		JSONObject json = (JSONObject) JSONObject.toJSON(condition);
		ShiroExt shiro = new ShiroExt();
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+announcement_announcementList+params+"/"+shiro.getUser().getId());
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public Announcement getdetail(Integer announcementId) {
		Announcement announcement=new Announcement();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+announcement_announcementDetail+announcementId);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				announcement=jsonObject.toJavaObject(Announcement.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return announcement;
	}

	@Override
	public void add(Announcement announcement) {
    	try {
    		JSONObject json = (JSONObject) JSONObject.toJSON(announcement);
            String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			List<NameValuePair> namevalue= new ArrayList<NameValuePair>();
			NameValuePair item = new BasicNameValuePair("param", params);
			namevalue.add(item);
			String result = HttpUtils.doPost(bmw_cloud_baseservice_url+announcement_add, namevalue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Announcement announcement) {
		try {
    		JSONObject json = (JSONObject) JSONObject.toJSON(announcement);
            String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			List<NameValuePair> namevalue= new ArrayList<NameValuePair>();
			NameValuePair item = new BasicNameValuePair("param", params);
			namevalue.add(item);
			String result = HttpUtils.doPost(bmw_cloud_baseservice_url+announcement_update, namevalue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void del(Integer announcementId) {
    		List<NameValuePair> namevalue= new ArrayList<NameValuePair>();
			NameValuePair item = new BasicNameValuePair("param", announcementId.toString());
			namevalue.add(item);
			try {
				HttpUtils.doPost(bmw_cloud_baseservice_url+announcement_del,namevalue);
			} catch (ParseException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public void updateHaveRead(Integer announcementId) {
		ShiroExt shiro = new ShiroExt();
		try {
			HttpUtils.doGet(bmw_cloud_baseservice_url+announcement_updateHaveRead+announcementId+"/"+shiro.getUser().getId());
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
