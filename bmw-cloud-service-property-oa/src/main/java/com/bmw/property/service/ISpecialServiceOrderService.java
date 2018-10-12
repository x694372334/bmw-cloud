package com.bmw.property.service;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.bmw.property.model.ServiceOrderRemark;
import com.bmw.property.model.SpecialServiceOrder;

/**
 * <p>
 * 特色服务订单 服务类
 * </p>
 *
 * @author zhangchengjun123
 * @since 2018-07-18
 */
public interface ISpecialServiceOrderService  {
	
	public JSONObject selectDoingList(Map<String, Object> paramsMap);
	
	public JSONObject selectDoneList(Map<String, Object> paramsMap);
	
	public boolean recieve(ServiceOrderRemark remark);
	
	public boolean sendOrder(ServiceOrderRemark remark);
	
	public boolean cancelOrder(ServiceOrderRemark remark);
	
	public boolean completeOrder(ServiceOrderRemark remark);
	
	public SpecialServiceOrder getDetail(Integer orderId);
	
	public SpecialServiceOrder fevaluateDetail(Integer orderId);
	
	public SpecialServiceOrder complaintDetail(Integer orderId);
	/**
	 * 回复投诉
	 * @param remark
	 * @return
	 */
	public boolean replyComplaint(ServiceOrderRemark remark);
	
	
	
	
}
