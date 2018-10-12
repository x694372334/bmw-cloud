package com.bmw.app.service.impl;

import com.bmw.app.model.SlideShow;
import com.bmw.app.service.ISlideShowService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
public class SlideShowServiceImpl implements ISlideShowService {
	
	@Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_baseservice_url="";
	
	@Value("${slideShow.slideShowList}")
	private String slideShow_slideShowList="";
	
	@Value("${slideShow.slideShowDetail}")
	private String slideShow_slideShowDetail="";
	
	@Value("${slideShow.add}")
	private String slideShow_add="";
	
	@Value("${slideShow.update}")
	private String slideShow_update="";
	
	@Value("${slideShow.del}")
	private String slideShow_del="";

	

	@Override
	public JSONArray findList(String condition) {
		JSONArray jsonArray=null;
    	try {
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+slideShow_slideShowList+condition, null);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public SlideShow getdetail(Integer slideShowId) {
		SlideShow slideShow=new SlideShow();
    	try {
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+slideShow_slideShowDetail+slideShowId, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				slideShow=jsonObject.toJavaObject(SlideShow.class);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return slideShow;
	}

	@Override
	public void add(SlideShow slideShow) {
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(slideShow).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+slideShow_add+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(SlideShow slideShow) {
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(slideShow).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+slideShow_update+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void del(Integer slideShowId) {
		JSONObject json=new JSONObject();
        json.put("slideShowId", slideShowId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+slideShow_del+params,null);
			//缓存被删除的部门名称
//	        LogObjectHolder.me().set(ConstantFactory.me().getslideShowName(slideShowId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
