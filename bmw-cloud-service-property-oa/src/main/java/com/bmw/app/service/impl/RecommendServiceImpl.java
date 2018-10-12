package com.bmw.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.HttpUtils;
import com.bmw.app.model.Recommend;
import com.bmw.app.service.IRecommendService;

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
public class RecommendServiceImpl  implements IRecommendService {


	@Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_baseservice_url="";
	
	@Value("${recommend.recommendList}")
	private String recommend_recommendList="";
	
	@Value("${recommend.recommendDetail}")
	private String recommend_recommendDetail="";
	
	@Value("${recommend.add}")
	private String recommend_add="";
	
	@Value("${recommend.update}")
	private String recommend_update="";
	
	@Value("${recommend.del}")
	private String recommend_del="";

	

	@Override
	public JSONArray findList(String condition) {
		JSONArray jsonArray=null;
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+recommend_recommendList);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public Recommend getdetail(Integer recommendId) {
		Recommend slideShow=new Recommend();
    	try {
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+recommend_recommendDetail+recommendId, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				slideShow=jsonObject.toJavaObject(Recommend.class);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return slideShow;
	}

	@Override
	public void add(Recommend recommend) {
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(recommend).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+recommend_add+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Recommend recommend) {
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(recommend).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+recommend_update+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void del(Integer recommendId) {
		JSONObject json=new JSONObject();
        json.put("recommendId", recommendId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+recommend_del+params,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
