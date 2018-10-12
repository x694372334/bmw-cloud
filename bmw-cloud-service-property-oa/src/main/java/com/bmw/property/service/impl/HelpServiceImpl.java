package com.bmw.property.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.bmw.property.model.Announcement;
import com.bmw.property.model.Help;
import com.bmw.property.service.IHelpService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

/**
 * <p>
 * 帮助 服务实现类
 * </p>
 *
 * @author zhangt123
 * @since 2018-07-09
 */
@Service
public class HelpServiceImpl  implements IHelpService {
	
	@Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_baseservice_url="";
	
	@Value("${help.helpList}")
	private String help_helpList="";
	
	@Value("${help.helpDetail}")
	private String help_helpDetail="";

	@Override
	public JSONArray findList(Help condition) {
		JSONArray jsonArray=null;
		JSONObject json = (JSONObject) JSONObject.toJSON(condition);
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+help_helpList+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public Help getdetail(Integer helpId) {
		Help help=new Help();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+help_helpDetail+helpId);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				help=jsonObject.toJavaObject(Help.class);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return help;
	}

	@Override
	public void add(Help help) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Help help) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void del(Integer helpId) {
		// TODO Auto-generated method stub
		
	}

}
