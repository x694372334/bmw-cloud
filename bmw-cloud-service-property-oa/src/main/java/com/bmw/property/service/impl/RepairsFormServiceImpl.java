package com.bmw.property.service.impl;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.bmw.property.model.RepairsForm;
import com.bmw.property.service.IRepairsFormService;

import cn.hutool.http.HttpStatus;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-07-03
 */
@Service
public class RepairsFormServiceImpl  implements IRepairsFormService {
	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_propertyservice_url="";
	@Value("${RepairsForm.selectDoingList}")
	private String RepairsForm_selectDoingList="";
	@Value("${RepairsForm.selectDoneList}")
	private String RepairsForm_selectDoneList="";
	@Value("${RepairsForm.insert}")
	private String RepairsForm_insert="";
	@Value("${RepairsForm.isIdentified}")
	private String RepairsForm_isIdentified="";
	@Value("${RepairsForm.refuse}")
	private String RepairsForm_refuse="";
	@Value("${RepairsForm.receive}")
	private String RepairsForm_receive="";
	@Value("${RepairsForm.door2see}")
	private String RepairsForm_door2see="";
	@Value("${RepairsForm.complete}")
	private String RepairsForm_complete="";
	@Value("${RepairsForm.detail}")
	private String RepairsForm_detail="";
	@Value("${RepairsForm.getMonthCount}")
	private String RepairsForm_getMonthCount="";
	@Value("${RepairsForm.getUncompletedCoun}")
	private String RepairsForm_getUncompletedCount="";
	@Value("${RepairsForm.delete}")
	private String RepairsForm_delete="";
	@Value("${RepairsForm.detail4Update}")
	private String RepairsForm_detail4Update="";
	@Value("${RepairsForm.update}")
	private String RepairsForm_update="";
	
	
	/**
	 * 查询正在执行的订单
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 * @throws ParseException 
	 */
	@Override
	public JSONObject selectDoingList(Map<String, Object> param)  {
		String jsonParam=new JSONObject().toJSONString(param);
		JSONObject json=null;
		List<RepairsForm> list=new ArrayList<>();
		try {
			String retrun=HttpUtils.doGet(bmw_cloud_propertyservice_url+RepairsForm_selectDoingList+new String(Base64Utils.encode(jsonParam.getBytes("utf-8"))));
			json=new JSONObject().parseObject(retrun).getJSONObject("items");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return json;
	}
	/**
	 * 查询执行完成的订单
	 */
	@Override
	public JSONObject selectDoneList(Map<String, Object> param) {
		String jsonParam=new JSONObject().toJSONString(param);
		JSONObject json=null;
		List<RepairsForm> list=new ArrayList<>();
		try {
			String retrun=HttpUtils.doGet(bmw_cloud_propertyservice_url+RepairsForm_selectDoneList+new String(Base64Utils.encode(jsonParam.getBytes("utf-8"))));
			json=new JSONObject().parseObject(retrun).getJSONObject("items");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return json;
	}
	/**
	 * 添加新的报事报修单
	 */
	@Override
	public boolean insert(RepairsForm entity) {
		String jsonrlt="";
		Map<String,String> httpSetting=new HashMap<>();
		httpSetting.put("contentType", "application/json");
    	try {
			String rlt=HttpUtils.doPost(bmw_cloud_propertyservice_url+RepairsForm_insert,JSONObject.toJSONString(entity).toString(),null);
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
	/**
	 * 根据房间号，校验一下是否被认证过，如果没被人认证过，则单据无法继续
	 */
	@Override
	public Boolean isIdentified(Integer roomId) {
		String result="";
		Boolean flag=null;
		try {
			result=HttpUtils.doGet(bmw_cloud_propertyservice_url+RepairsForm_isIdentified+new String(Base64Utils.encode(roomId.toString().getBytes("utf-8"))));
			flag=JSONObject.parseObject(result).getBoolean("items");
		}catch(Exception e) {
			e.printStackTrace();
			flag=false;
		}
		return flag;
	}
	/**
	 * 驳回
	 */
	@Override
	public Boolean refuse(Map<String, Object> sendMap) {
		String jsonrlt="";
		try {
			String rlt=HttpUtils.doPost(bmw_cloud_propertyservice_url+RepairsForm_refuse,JSONObject.toJSONString(sendMap).toString(),null);
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
	/**
	 * 接单
	 */
	@Override
	public Boolean receive(Map<String, Object> sendMap) {
		String jsonrlt="";
	
		try {
			String rlt=HttpUtils.doPost(bmw_cloud_propertyservice_url+RepairsForm_receive,JSONObject.toJSONString(sendMap).toString(),null);
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
	/**
	 * 上门查看
	 */
	@Override
	public Boolean door2see(Map<String, Object> sendMap) {
		String jsonrlt="";

		try {
			String rlt=HttpUtils.doPost(bmw_cloud_propertyservice_url+RepairsForm_door2see,JSONObject.toJSONString(sendMap).toString(),null);
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
	/**
	 * 完成
	 */
	@Override
	public Boolean complete(Map<String, Object> sendMap) {
		String jsonrlt="";
		try {
			String rlt=HttpUtils.doPost(bmw_cloud_propertyservice_url+RepairsForm_complete,JSONObject.toJSONString(sendMap).toString(),null);
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
	public RepairsForm selectById(Serializable id) {
		RepairsForm returnForm=new RepairsForm ();
		try {
			String rlt=HttpUtils.doGet(bmw_cloud_propertyservice_url+RepairsForm_detail+id);
			returnForm=JSONObject.parseObject(rlt).getJSONObject("items").toJavaObject(RepairsForm.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnForm;
	}
	
	
	
	/**
	 * 查询正在执行的订单
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 * @throws ParseException 
	 */
	@Override
	public Integer getMonthCount()  {
		Integer count = 0;
		try {
			String retrun=HttpUtils.doGet(bmw_cloud_propertyservice_url+RepairsForm_getMonthCount);
			count= (Integer) new JSONObject().parseObject(retrun).get("items");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return count;
	}
	@Override
	public Integer getUncompletedCount() {
		Integer count = 0;
		try {
			String retrun=HttpUtils.doGet(bmw_cloud_propertyservice_url+RepairsForm_getUncompletedCount);
			count= (Integer) new JSONObject().parseObject(retrun).get("items");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return count;
	}
	@Override
	public Boolean delete(RepairsForm rf) {
		try {
			String result=HttpUtils.doPost(bmw_cloud_propertyservice_url+RepairsForm_delete, JSONObject.toJSONString(rf), null);
			int statusCode=JSONObject.parseObject(result).getIntValue("statusCode");
			if(statusCode!=HttpStatus.HTTP_OK) {
				return false;
			}else {
				return true;
			}
		} catch (ParseException | IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public RepairsForm detail4Update(Integer id) {
		RepairsForm returnForm=new RepairsForm ();
		try {
			String rlt=HttpUtils.doGet(bmw_cloud_propertyservice_url+RepairsForm_detail4Update+id);
			returnForm=JSONObject.parseObject(rlt).getJSONObject("items").toJavaObject(RepairsForm.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnForm;
	}
	@Override
	public Boolean updateById(RepairsForm rf) {
		try {
			String result=HttpUtils.doPost(bmw_cloud_propertyservice_url+RepairsForm_update, JSONObject.toJSONString(rf), null);
			int statusCode=JSONObject.parseObject(result).getIntValue("statusCode");
			if(statusCode!=HttpStatus.HTTP_OK) {
				return false;
			}else {
				return true;
			}
		} catch (ParseException | IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
