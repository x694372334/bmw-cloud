package com.bmw.app.service.impl;

import com.bmw.app.model.AppNotice;
import com.bmw.app.dao.AppNoticeMapper;
import com.bmw.app.service.IAppNoticeService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.HttpUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fjm123
 * @since 2018-05-17
 */
@Service
public class AppNoticeServiceImpl implements IAppNoticeService {
	
	@Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_baseservice_url="";
	
	@Value("${appNotice.appNoticeList}")
	private String appNotice_appNoticeList="";
	
	@Value("${appNotice.appNoticeDetail}")
	private String appNotice_appNoticeDetail="";
	
	@Value("${appNotice.add}")
	private String appNotice_add="";
	
	@Value("${appNotice.update}")
	private String appNotice_update="";
	
	@Value("${appNotice.del}")
	private String appNotice_del="";

	@Override
	public JSONArray findList(String condition) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
        json.put("name", condition);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+appNotice_appNoticeList+params, null);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public AppNotice getdetail(Integer appNoticeId) {
		AppNotice appNotice=new AppNotice();
    	try {
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+appNotice_appNoticeDetail+appNoticeId, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				appNotice=jsonObject.toJavaObject(AppNotice.class);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return appNotice;
	}

	@Override
	public void add(AppNotice appNotice) {
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(appNotice).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+appNotice_add+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update(AppNotice appNotice) {
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(appNotice).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+appNotice_update+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void del(Integer appNoticeId) {
		JSONObject json=new JSONObject();
        json.put("appNoticeId", appNoticeId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+appNotice_del+params,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
