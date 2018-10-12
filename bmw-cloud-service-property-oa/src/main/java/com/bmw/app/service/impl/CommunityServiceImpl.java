package com.bmw.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.HttpUtils;
import com.bmw.app.model.Community;
import com.bmw.app.service.ICommunityService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-05-23
 */
@Service
public class CommunityServiceImpl  implements ICommunityService {

	@Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_baseservice_url="";
	
	@Value("${community.communityList}")
	private String community_communityList="";
	
	@Value("${community.communityDetail}")
	private String community_communityDetail="";
	
	@Value("${community.add}")
	private String community_add="";
	
	@Value("${community.update}")
	private String community_update="";
	
	@Value("${community.del}")
	private String community_del="";

	

	@Override
	public JSONArray findList(String condition) {
		JSONArray jsonArray=null;
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+community_communityList);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public Community getdetail(Integer communityId) {
		Community community=new Community();
    	try {
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+community_communityDetail+communityId, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				community=jsonObject.toJavaObject(Community.class);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return community;
	}

	@Override
	public void add(Community community) {
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(community).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+community_add+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Community community) {
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(community).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+community_update+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void del(Integer communityId) {
		JSONObject json=new JSONObject();
        json.put("communityId", communityId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+community_del+params,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
