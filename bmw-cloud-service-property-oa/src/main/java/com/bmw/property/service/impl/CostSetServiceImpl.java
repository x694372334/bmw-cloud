package com.bmw.property.service.impl;

import java.util.Map;

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
import com.bmw.property.model.CostSet;
import com.bmw.property.service.ICostSetService;

/**
 * 类名: CostSetServiceImpl  
 * 类描述: 费项设置 服务接口实现类
 * 创建人: wangliqing
 * 创建时间 : 2018年6月26日 下午2:37:39    
 */
@Service
public class CostSetServiceImpl implements ICostSetService {

	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_propertyservice_url;
	
	@Value("${costSet.selectList}")
	private String costSet_selectList;
	
	@Value("${costSet.insert}")
	private String costSet_insert;
	
	@Value("${costSet.updateById}")
	private String costSet_updateById;
	
	@Value("${costSet.deleteById}")
	private String costSet_deleteById;
	
	@Value("${costSet.selectById}")
	private String costSet_selectById;
	
	@Value("${costSet.getCostSetAll}")
	private String costSet_getCostSetAll;
	
	@Value("${costSet.isEdit}")
	private String costSet_isEdit;
	
	
	
	/**
	 * TODO 根据id查询费项设置记录
	 * @see com.bmw.property.service.ICostSetService#selectById(Integer)
	 */
	@Override
	public CostSet selectById(Integer costSetId) {
		CostSet costSet = new CostSet();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_propertyservice_url+costSet_selectById+costSetId);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				costSet=jsonObject.toJavaObject(CostSet.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return costSet;
	}

	/**
	 * TODO 获取费项设置列表(分页)
	 * @see com.bmw.property.service.ICostSetService#selectList(Map)
	 */
	@Override
	public JSONObject selectList(Map<String,Object> param) {
		JSONObject paramOjbect=new JSONObject();
		paramOjbect.put("pageNum", param.get("pageNum"));
		paramOjbect.put("pageSize", param.get("pageSize"));
		paramOjbect.put("costTypeId", param.get("costTypeId"));
		paramOjbect.put("orderByField", param.get("verifierId"));
		paramOjbect.put("isAsc", param.get("isAsc"));
		paramOjbect.put("eId", ShiroKit.getUser().geteId());
		JSONObject jsonrlt=new JSONObject();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_propertyservice_url+costSet_selectList+new String(Base64Utils.encode(paramOjbect.toString().getBytes("UTF-8"))));
			jsonrlt=JSONObject.parseObject(rlt).getJSONObject("items");
    	} catch (Exception e) {
    		e.printStackTrace();
		} 
    	return jsonrlt;
	}

	/**
	 * TODO 新增费项设置记录
	 * @see com.bmw.property.service.ICostSetService#insert(com.bmw.property.model.CostSet)
	 */
	@Override
	public void insert(CostSet costSet) {
		costSet.preInsert();
		costSet.seteId(ShiroKit.getUser().geteId());
		try {
			String URL=bmw_cloud_propertyservice_url+costSet_insert;
			HttpUtils.doPost(URL, HttpUtils.convertParam(costSet));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * TODO 删除费项设置记录(逻辑删除)
	 * @throws Exception 
	 * @see com.bmw.property.service.ICostSetService#deleteById(Integer)
	 */
	@Override
	public Tip deleteById(Integer costSetId) throws Exception {
		JSONObject json=new JSONObject();
        json.put("id", costSetId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
    		String rlt = HttpUtils.doDelete(bmw_cloud_propertyservice_url+costSet_deleteById+params,null);
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

	/**
	 * TODO 修改费项设置记录
	 * @see com.bmw.property.service.ICostSetService#updateById(com.bmw.property.model.CostSet)
	 */
	@Override
	public void updateById(CostSet costSet) {
		costSet.preUpdate();
		costSet.seteId(ShiroKit.getUser().geteId());
		try {
			String URL=bmw_cloud_propertyservice_url+costSet_updateById;
			HttpUtils.doPost(URL, HttpUtils.convertParam(costSet));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * TODO 获取所有费项设置记录(无分页)
	 * @see com.bmw.property.service.ICostSetService#getCostSetAll()
	 */
	@Override
	public JSONArray getCostSetAll(Integer relevanceId) {
		JSONArray jsonArray=null;
		JSONObject paramOjbect=new JSONObject();
		paramOjbect.put("eId", ShiroKit.getUser().geteId());
		paramOjbect.put("relevanceId", relevanceId);
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_propertyservice_url+costSet_getCostSetAll+new String(Base64Utils.encode(paramOjbect.toString().getBytes("UTF-8"))));
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	public Tip isEdit(Integer costSetId) throws Exception {
		JSONObject json=new JSONObject();
        json.put("id", costSetId);
    	try {
    		String params=Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
    		String rlt = HttpUtils.doDelete(bmw_cloud_propertyservice_url+costSet_isEdit+params,null);
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

	
	
	
}
