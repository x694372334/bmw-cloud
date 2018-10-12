package com.bmw.property.service.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.core.base.tips.SuccessTip;
import com.stylefeng.guns.core.base.tips.Tip;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.bmw.property.model.BatchStandards;
import com.bmw.property.service.IBatchStandardsService;

/**
 * <p>
 * 批量关联收费标准 服务实现类
 * </p>
 *
 * @author wangliqing123
 * @since 2018-06-28
 */
@Service
public class BatchStandardsServiceImpl implements IBatchStandardsService {

	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_propertyservice_url;
	
	@Value("${batchStandards.selectList}")
	private String batchStandards_selectList;
	
	@Value("${batchStandards.insert}")
	private String batchStandards_insert;
	
	@Value("${batchStandards.addScopeIds}")
	private String batchStandards_addScopeIds;
	
	@Value("${batchStandards.deleteBill}")
	private String batchStandards_deleteBill;
	
	@Value("${batchStandards.updateById}")
	private String batchStandards_updateById;
	
	@Value("${batchStandards.deleteById}")
	private String batchStandards_deleteById;
	
	@Value("${batchStandards.selectById}")
	private String batchStandards_selectById;
	
	@Value("${batchStandards.findList}")
	private String batchStandards_findList;
	
	@Value("${batchRoomBase.getCount}")
	private String batchRoomBase_getCount;
	
	@Override
	public BatchStandards selectById(Integer batchStandardsId) {
		BatchStandards batchStandards = new BatchStandards();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_propertyservice_url+batchStandards_selectById+batchStandardsId);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				batchStandards=jsonObject.toJavaObject(BatchStandards.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return batchStandards;
	}

	@Override
	public JSONObject selectList(Map<String, Object> param) {
		JSONObject paramOjbect=new JSONObject();
		paramOjbect.put("pageNum", param.get("pageNum"));
		paramOjbect.put("pageSize", param.get("pageSize"));
		paramOjbect.put("costId", param.get("costId"));
		paramOjbect.put("neighborhoodsId", param.get("neighborhoodsId"));
		paramOjbect.put("orderByField", param.get("verifierId"));
		paramOjbect.put("isAsc", param.get("isAsc"));
		paramOjbect.put("eId", ShiroKit.getUser().geteId());
		JSONObject jsonrlt=new JSONObject();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_propertyservice_url+batchStandards_selectList+new String(Base64Utils.encode(paramOjbect.toString().getBytes("UTF-8"))));
			jsonrlt=JSONObject.parseObject(rlt).getJSONObject("items");
    	} catch (Exception e) {
    		e.printStackTrace();
		} 
    	return jsonrlt;
	}

	@Override
	public Tip insert(BatchStandards batchStandards) {
		JSONObject json=new JSONObject();
		batchStandards.preInsert();
		batchStandards.seteId(ShiroKit.getUser().geteId());
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(batchStandards.getStartDate() != null) {
			Timestamp chargeableStartDate = Timestamp.valueOf(sdf.format(batchStandards.getStartDate()));
			batchStandards.setChargeableStartDate(chargeableStartDate);
		}
		
		if(batchStandards.getEndDate() != null) {
			Timestamp chargeableEndDate = Timestamp.valueOf(sdf.format(batchStandards.getEndDate()));
			batchStandards.setChargeableEndDate(chargeableEndDate);
		}
		try {
			String URL=bmw_cloud_propertyservice_url+batchStandards_insert;
			String rlt = HttpUtils.doPost(URL, HttpUtils.convertParam(batchStandards));
			json=JSONObject.parseObject(rlt);
			
			if(json.getInteger("statusCode") != 201) {
				return new ErrorTip(1000,"系统异常，请联系管理员！");
			}else {
				return new SuccessTip(200,json.getString("items"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public Tip deleteById(Integer batchStandardsId) throws Exception {
		
		JSONObject json=new JSONObject();
        json.put("id", batchStandardsId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doDelete(bmw_cloud_propertyservice_url+batchStandards_deleteById+params,null);
			json=JSONObject.parseObject(rlt);
			
			if(json.getInteger("statusCode") != 201) {
				return new ErrorTip(1000,"系统异常，请联系管理员！");
			}else {
				return new SuccessTip(200,json.getString("items"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}

	@Override
	public void updateById(BatchStandards batchStandards) {
		
		batchStandards.preUpdate();
		batchStandards.seteId(ShiroKit.getUser().geteId());
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp chargeableStartDate = Timestamp.valueOf(sdf.format(batchStandards.getStartDate()));
		batchStandards.setChargeableStartDate(chargeableStartDate);
		if(batchStandards.getEndDate() != null) {
			Timestamp chargeableEndDate = Timestamp.valueOf(sdf.format(batchStandards.getEndDate()));
			batchStandards.setChargeableEndDate(chargeableEndDate);
		}
		try {
			String URL=bmw_cloud_propertyservice_url+batchStandards_updateById;
			HttpUtils.doPost(URL, HttpUtils.convertParam(batchStandards));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object addScopeIds(BatchStandards batchStandards) {
		batchStandards.seteId(ShiroKit.getUser().geteId());
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(batchStandards.getStartDate() != null) 
			batchStandards.setChargeableStartDate(Timestamp.valueOf(sdf.format(batchStandards.getStartDate())));
		
		if(batchStandards.getEndDate() != null)
			batchStandards.setChargeableEndDate(Timestamp.valueOf(sdf.format(batchStandards.getEndDate())));
		JSONObject json=new JSONObject();
		try {
			String URL=bmw_cloud_propertyservice_url+batchStandards_addScopeIds;
			String rlt = HttpUtils.doPost(URL, HttpUtils.convertParam(batchStandards));
			json=JSONObject.parseObject(rlt);
			
			if(json.getInteger("statusCode") != 201) {
				return new ErrorTip(1000,"系统异常，请联系管理员！");
			}else {
				return new SuccessTip(200,json.getString("items"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Object deleteBill(BatchStandards batchStandards) {
		batchStandards.seteId(ShiroKit.getUser().geteId());
		JSONObject json=new JSONObject();
		try {
			String URL=bmw_cloud_propertyservice_url+batchStandards_deleteBill;
			String rlt = HttpUtils.doPost(URL, HttpUtils.convertParam(batchStandards));
			json=JSONObject.parseObject(rlt);
			
			if(json.getInteger("statusCode") != 201) {
				return new ErrorTip(1000,"系统异常，请联系管理员！");
			}else {
				return new SuccessTip(200,json.getString("items"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<BatchStandards> findList(BatchStandards batchStandards) {
		List<BatchStandards> list = new ArrayList<BatchStandards>();
		try {
			String rlt = HttpUtils.doPost(bmw_cloud_propertyservice_url + batchStandards_findList ,JSON.toJSONString(batchStandards), null);
			JSONArray jsonArray = JSON.parseObject(rlt).getJSONArray("items");
			list = JSONObject.parseArray(jsonArray.toJSONString(), BatchStandards.class);
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean isAssociated(BatchStandards batchStandards,Integer rId) {
		JSONObject parkingInfoJson=new JSONObject();
		parkingInfoJson.put("rId", rId);
		parkingInfoJson.put("nbId", batchStandards.getNbId());
		parkingInfoJson.put("relevanceId", batchStandards.getRelevanceId());
		
		try {
			String rlt  = HttpUtils.doPost(bmw_cloud_propertyservice_url + batchRoomBase_getCount ,parkingInfoJson.toJSONString(), null);
			JSONObject rltJson=JSONObject.parseObject(rlt);
			if(null != rltJson  ) {
				String count = rltJson.getString("items");
				return Integer.valueOf(count) > 0?true :false;
			}
		} catch (ParseException | IOException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
}
