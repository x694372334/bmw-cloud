package com.bmw.property.service.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.bmw.property.model.ServiceOrderRemark;
import com.bmw.property.model.SpecialServiceOrder;
import com.bmw.property.service.ISpecialServiceOrderService;

/**
 * <p>
 * 特色服务订单 服务实现类
 * </p>
 *
 * @author zhangchengjun123
 * @since 2018-07-18
 */
@Service
public class SpecialServiceOrderServiceImpl  implements ISpecialServiceOrderService {

	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_propertyservice_url;
	
	@Value("${SpecialServiceOrder.selectDoingList}")
	private String SpecialServiceOrder_selectDoingList;
	
	@Value("${SpecialServiceOrder.selectDoneList}")
	private String SpecialServiceOrder_selectDoneList;
	
	@Value("${SpecialServiceOrder.recieve}")
	private String SpecialServiceOrder_recieve;
	
	@Value("${SpecialServiceOrder.sendOrder}")
	private String SpecialServiceOrder_sendOrder;
	
	@Value("${SpecialServiceOrder.cancelOrder}")
	private String SpecialServiceOrder_cancelOrder;
	
	@Value("${SpecialServiceOrder.getDetail}")
	private String SpecialServiceOrder_getDetail;
	
	@Value("${SpecialServiceOrder.completeOrder}")
	private String SpecialServiceOrder_completeOrder;
	
	@Value("${SpecialServiceOrder.fevaluateDetail}")
	private String SpecialServiceOrder_fevaluateDetail;
	
	@Value("${SpecialServiceOrder.complaintDetail}")
	private String SpecialServiceOrder_complaintDetail;
	
	@Value("${SpecialServiceOrder.replyComplaint}")
	private String SpecialServiceOrder_replyComplaint;
	

	
	@Override
	public JSONObject selectDoingList(Map<String, Object> paramsMap) {
		JSONObject returnJson=null;
		try {
			String rlt=HttpUtils.doGet(bmw_cloud_propertyservice_url+SpecialServiceOrder_selectDoingList+new String(Base64Utils.encode(JSONObject.toJSONString(paramsMap).getBytes("UTF-8"))));
			returnJson=JSONObject.parseObject(rlt).getJSONObject("items");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return returnJson;
	}

	@Override
	public JSONObject selectDoneList(Map<String, Object> paramsMap) {
		JSONObject returnJson=null;
		try {
			String rlt=HttpUtils.doGet(bmw_cloud_propertyservice_url+SpecialServiceOrder_selectDoneList+new String(Base64Utils.encode(JSONObject.toJSONString(paramsMap).getBytes("UTF-8"))));
			returnJson=JSONObject.parseObject(rlt).getJSONObject("items");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return returnJson;
	}

	@Override
	public boolean recieve(ServiceOrderRemark remark) {
		String jsonrlt="";
		System.out.println(JSONObject.toJSONString(remark));
    	try {
			String rlt=HttpUtils.doPost(bmw_cloud_propertyservice_url+SpecialServiceOrder_recieve,JSONObject.toJSONString(remark).toString(),null);
			jsonrlt=JSONObject.parseObject(rlt).getString("statusCode");
			if(!StringUtils.isEmpty(jsonrlt)&&jsonrlt.equals("201")) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean sendOrder(ServiceOrderRemark remark) {
		String jsonrlt="";
    	try {
			String rlt=HttpUtils.doPost(bmw_cloud_propertyservice_url+SpecialServiceOrder_sendOrder,JSONObject.toJSONString(remark).toString(),null);
			jsonrlt=JSONObject.parseObject(rlt).getString("statusCode");
			if(!StringUtils.isEmpty(jsonrlt)&&jsonrlt.equals("201")) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean completeOrder(ServiceOrderRemark remark) {
		String jsonrlt="";
    	try {
			String rlt=HttpUtils.doPost(bmw_cloud_propertyservice_url+SpecialServiceOrder_completeOrder,JSONObject.toJSONString(remark).toString(),null);
			jsonrlt=JSONObject.parseObject(rlt).getString("statusCode");
			if(!StringUtils.isEmpty(jsonrlt)&&jsonrlt.equals("201")) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean cancelOrder(ServiceOrderRemark remark) {
		String jsonrlt="";
    	try {
			String rlt=HttpUtils.doPost(bmw_cloud_propertyservice_url+SpecialServiceOrder_cancelOrder,JSONObject.toJSONString(remark).toString(),null);
			jsonrlt=JSONObject.parseObject(rlt).getString("statusCode");
			if(!StringUtils.isEmpty(jsonrlt)&&jsonrlt.equals("201")) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean replyComplaint(ServiceOrderRemark remark) {
		String jsonrlt="";
		System.out.println(JSONObject.toJSONString(remark));
    	try {
			String rlt=HttpUtils.doPost(bmw_cloud_propertyservice_url+SpecialServiceOrder_replyComplaint,JSONObject.toJSONString(remark).toString(),null);
			jsonrlt=JSONObject.parseObject(rlt).getString("statusCode");
			if(!StringUtils.isEmpty(jsonrlt)&&jsonrlt.equals("201")) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public SpecialServiceOrder getDetail(Integer orderId) {
		JSONObject returnJson=null;
		SpecialServiceOrder order=new SpecialServiceOrder();
		try {
			String rlt=HttpUtils.doGet(bmw_cloud_propertyservice_url+SpecialServiceOrder_getDetail+orderId);
			returnJson=JSONObject.parseObject(rlt).getJSONObject("items");
			order=returnJson.toJavaObject(SpecialServiceOrder.class);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return order;
	}	
	@Override
	public SpecialServiceOrder fevaluateDetail(Integer orderId) {
		JSONObject returnJson=null;
		SpecialServiceOrder order=new SpecialServiceOrder();
		try {
			String rlt=HttpUtils.doGet(bmw_cloud_propertyservice_url+SpecialServiceOrder_fevaluateDetail+orderId);
			returnJson=JSONObject.parseObject(rlt).getJSONObject("items");
			order=returnJson.toJavaObject(SpecialServiceOrder.class);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return order;
	}
	@Override
	public SpecialServiceOrder complaintDetail(Integer orderId) {
		JSONObject returnJson=null;
		SpecialServiceOrder order=new SpecialServiceOrder();
		try {
			String rlt=HttpUtils.doGet(bmw_cloud_propertyservice_url+SpecialServiceOrder_complaintDetail+orderId);
			returnJson=JSONObject.parseObject(rlt).getJSONObject("items");
			order=returnJson.toJavaObject(SpecialServiceOrder.class);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return order;
	}


}
