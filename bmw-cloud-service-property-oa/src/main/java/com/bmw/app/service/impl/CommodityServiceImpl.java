package com.bmw.app.service.impl;

import com.bmw.app.model.Commodity;
import com.bmw.app.dao.CommodityMapper;
import com.bmw.app.service.ICommodityService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

/**
 * <p>
 *  商品服务实现类
 * </p>
 *
 * @author fjm123
 * @since 2018-05-17
 */
@Service
public class CommodityServiceImpl implements ICommodityService {

	
	@Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_baseservice_url="";
	
	@Value("${commodity.commodityList}")
	private String commodity_commodityList="";
	
	@Value("${commodity.commodityDetail}")
	private String commodity_commodityDetail="";
	
	@Value("${commodity.add}")
	private String commodity_add="";
	
	@Value("${commodity.update}")
	private String commodity_update="";
	
	@Value("${commodity.del}")
	private String commodity_del="";
	
	
	@Override
	public JSONArray findList(String condition) {
		JSONArray jsonArray=null;
    	try {
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+commodity_commodityList+condition, null);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}

	@Override
	public Commodity getdetail(Integer commodityId) {
		Commodity commodity=new Commodity();
    	try {
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+commodity_commodityDetail+commodityId, null);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				commodity=jsonObject.toJavaObject(Commodity.class);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return commodity;
	}

	@Override
	public void add(Commodity commodity) {
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(commodity).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+commodity_add+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Commodity commodity) {
		try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(commodity).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+commodity_update+params, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void del(Integer commodityId) {
		JSONObject json=new JSONObject();
        json.put("commodityId", commodityId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+commodity_del+params,null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
