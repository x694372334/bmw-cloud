package com.bmw.app.service.impl;

import com.bmw.app.model.AppMenu;
import com.bmw.app.dao.AppMenuMapper;
import com.bmw.app.service.IAppMenuService;
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
 *  菜单服务实现类
 * </p>
 *
 * @author fjm123
 * @since 2018-05-17
 */
@Service
public class AppMenuServiceImpl implements IAppMenuService {
	
	@Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_baseservice_url="";
	
	@Value("${appMenu.appMenuList}")
	private String appMenu_appMenuList="";
	
	@Value("${appMenu.appMenuDetail}")
	private String appMenu_appMenuDetail="";
	
	@Value("${appMenu.add}")
	private String appMenu_add="";
	
	@Value("${appMenu.update}")
	private String appMenu_update="";
	
	@Value("${appMenu.del}")
	private String appMenu_del="";

	@Override
	public JSONArray findList(String condition) {
		JSONArray jsonArray=null;
    	try {
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+appMenu_appMenuList+condition, null);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public AppMenu getdetail(Integer appMenuId) {
		AppMenu appMenu=new AppMenu();
    	try {
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+appMenu_appMenuDetail+appMenuId, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				appMenu=jsonObject.toJavaObject(AppMenu.class);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return appMenu;
	}

	@Override
	public void add(AppMenu appMenu) {
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(appMenu).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+appMenu_add+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(AppMenu appMenu) {
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(appMenu).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+appMenu_update+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void del(Integer appMenuId) {
		JSONObject json=new JSONObject();
        json.put("appMenuId", appMenuId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+appMenu_del+params,null);
			//缓存被删除的部门名称
//	        LogObjectHolder.me().set(ConstantFactory.me().getappMenuName(appMenuId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
