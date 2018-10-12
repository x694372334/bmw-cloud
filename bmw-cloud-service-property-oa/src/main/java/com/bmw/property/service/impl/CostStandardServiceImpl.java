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
import com.bmw.property.model.CostStandard;
import com.bmw.property.service.ICostStandardService;

/**
 * 类名: CostStandardServiceImpl  
 * 类描述: 费用标准 服务实现类
 * 创建人: wangliqing
 * 创建时间 : 2018年6月26日 下午2:38:59    
 */
@Service
public class CostStandardServiceImpl implements ICostStandardService {

	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_propertyservice_url;

	@Value("${costStandard.selectList}")
	private String costStandard_selectList;

	@Value("${costStandard.insert}")
	private String costStandard_insert;

	@Value("${costStandard.updateById}")
	private String costStandard_updateById;

	@Value("${costStandard.deleteById}")
	private String costStandard_deleteById;

	@Value("${costStandard.selectById}")
	private String costStandard_selectById;
	
	@Value("${costStandard.getCostStandardsByCostId}")
	private String costStandard_getCostStandardsByCostId;

	/**
	 * TODO 根据id查询费用标准记录
	 * @see com.bmw.property.service.ICostStandardService#selectById(Integer)
	 */
	@Override
	public CostStandard selectById(Integer costStandardId) {

		CostStandard costStandard = new CostStandard();
		try {
			String rlt = HttpUtils.doGet(bmw_cloud_propertyservice_url + costStandard_selectById + costStandardId);
			JSONObject jsonObject = JSON.parseObject(rlt).getJSONObject("items");
			if (jsonObject != null) {
				costStandard = jsonObject.toJavaObject(CostStandard.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return costStandard;
	}

	/**
	 * TODO 获取费用标准列表(分页)
	 * @see com.bmw.property.service.ICostStandardService#selectList(Map)
	 */
	@Override
	public JSONObject selectList(Map<String,Object> param) {

		
		JSONObject paramOjbect=new JSONObject();
		paramOjbect.put("pageNum", param.get("pageNum"));
		paramOjbect.put("pageSize", param.get("pageSize"));
		paramOjbect.put("standardName", param.get("condition"));
		paramOjbect.put("isDelete", param.get("isDelete"));
		paramOjbect.put("orderByField", param.get("verifierId"));
		paramOjbect.put("isAsc", param.get("isAsc"));
		paramOjbect.put("eId", ShiroKit.getUser().geteId());
		JSONObject jsonrlt=new JSONObject();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_propertyservice_url+costStandard_selectList+new String(Base64Utils.encode(paramOjbect.toString().getBytes("UTF-8"))));
			jsonrlt=JSONObject.parseObject(rlt).getJSONObject("items");
    	} catch (Exception e) {
    		e.printStackTrace();
		} 
    	return jsonrlt;
	}

	/**
	 * TODO 新增费用标准记录
	 * @see com.bmw.property.service.ICostStandardService#insert(com.bmw.property.model.CostStandard)
	 */
	@Override
	public void insert(CostStandard costStandard) {
		costStandard.preInsert();
		costStandard.seteId(ShiroKit.getUser().geteId());
		try {
			String URL=bmw_cloud_propertyservice_url+costStandard_insert;
			HttpUtils.doPost(URL, HttpUtils.convertParam(costStandard));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * TODO 删除费用标准记录(逻辑删除)
	 * @throws Exception 
	 * @see com.bmw.property.service.ICostStandardService#deleteById(Integer)
	 */
	@Override
	public Tip deleteById(Integer costStandardId) throws Exception {

		JSONObject json = new JSONObject();
		json.put("id", costStandardId);
		try {
			String params = Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doDelete(bmw_cloud_propertyservice_url + costStandard_deleteById + params, null);
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
	 * TODO 修改费用标准记录
	 * @see com.bmw.property.service.ICostStandardService#updateById(com.bmw.property.model.CostStandard)
	 */
	@Override
	public void updateById(CostStandard costStandard) {
		costStandard.preUpdate();
		costStandard.seteId(ShiroKit.getUser().geteId());
		try {
			String URL=bmw_cloud_propertyservice_url+costStandard_updateById;
			HttpUtils.doPost(URL, HttpUtils.convertParam(costStandard));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * TODO 根据费项设置主键查询费用标准列表
	 * @see com.bmw.property.service.ICostStandardService#getCostStandardsByCostId(Integer)
	 */
	@Override
	public JSONArray getCostStandardsByCostId(Integer costId) {
		JSONArray jsonArray=null;
		JSONObject json=new JSONObject();
		json.put("costId", costId);
		json.put("eId", ShiroKit.getUser().geteId());
    	try {
    		String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doGet(bmw_cloud_propertyservice_url+costStandard_getCostStandardsByCostId+params);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return jsonArray;
	}
}
