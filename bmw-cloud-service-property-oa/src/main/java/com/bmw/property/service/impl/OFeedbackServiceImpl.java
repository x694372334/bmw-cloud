package com.bmw.property.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IUserService;
import com.bmw.property.model.Neighborhood;
import com.bmw.property.model.OFeedback;
import com.bmw.property.service.INeighborhoodService;
import com.bmw.property.service.IOFeedbackService;

/**
 * <p>
 * 意见反馈 服务实现类
 * </p>
 *
 * @author liuwsh
 * @since 2018-07-30
 */
@Service
public class OFeedbackServiceImpl implements IOFeedbackService {
	@Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_baseservice_url = "";

	@Value("${ofeedback.list}")
	private String ofeedback_list = "";

	@Value("${ofeedback.add}")
	private String ofeedback_add = "";

	@Value("${ofeedback.detail}")
	private String ofeedback_detail = "";

	@Value("${ofeedback.del}")
	private String ofeedback_del = "";

	@Value("${ofeedback.update}")
	private String ofeedback_update = "";

	@Autowired
	private IUserService userService;
	
	 @Autowired
	 private INeighborhoodService neighborhoodService;

	@Override
	public JSONObject findList(Map<String, Integer> paramMap) {
		JSONObject returnJson = null;
		try {
			String params = new String(Base64Utils.encode(JSONObject.toJSONString(paramMap).getBytes("UTF-8")));
			String rlt = HttpUtils.doGet(bmw_cloud_baseservice_url + ofeedback_list + params);
			returnJson = JSONObject.parseObject(rlt).getJSONObject("items");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnJson;
	}

	@Override
	public OFeedback getdetail(Integer ofeedbackId) {
		OFeedback ofeedback = new OFeedback();
		try {
			String rlt = HttpUtils.doGet(bmw_cloud_baseservice_url + ofeedback_detail + ofeedbackId);
			JSONObject jsonObject = JSON.parseObject(rlt).getJSONObject("items");
			if (jsonObject != null) {
				ofeedback = jsonObject.toJavaObject(OFeedback.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ofeedback;
	}

	@Override
	public void add(OFeedback ofeedback) {
		try {
			// 获取登录人的id
			Integer id = ShiroKit.getUser().getId();
			// 获取登录人名字
			String name = ShiroKit.getUser().getName();
			// 获取登录人详细
			User user = userService.detailUser(id);
			if(null != user) {
				ofeedback.setHousesId(user.getnId());
			}
			
			ofeedback.setCreateMan(name);
			ofeedback.setCreateManId(id);

			ofeedback.seteId(ShiroKit.getUser().geteId());
			JSONArray arr=neighborhoodService.findList(ShiroKit.getUser().geteId(), null);
			if(null != arr.get(0)) {
				Neighborhood nb=   JSON.parseObject(JSON.toJSONString(arr.get(0)), Neighborhood.class);
				ofeedback.setHousesId(nb.getnId());
			}
			
			ofeedback.setoId(id);// 反馈人id
			ofeedback.setUserId(id);// 反馈用户id
			ofeedback.setFeedbackTime(new Date());// 反馈时间
			ofeedback.setStatus(0);// 未回复
			ofeedback.setIsDelete(1);// 是否删除，0 删除 1 正常

			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url + ofeedback_add,
					JSONObject.toJSONString(ofeedback).toString(), null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(OFeedback ofeedback) {
		// 获取登录人的id
		Integer id = ShiroKit.getUser().getId();
		// 获取登录人名字
		String name = ShiroKit.getUser().getName();
		// 获取当前时间
		Timestamp date =  new Timestamp(new Date().getTime());
		ofeedback.setEditMan(name);
		ofeedback.setEditManId(id);
		if (StringUtils.isNotBlank(ofeedback.getHandlingResult())) {
			ofeedback.setStatus(1);// 已回复
			ofeedback.setHandlingTime(date);// 物业回复时间
			ofeedback.setuId(id);// 物业回复用户id
		}
		try {
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url + ofeedback_update,
					JSONObject.toJSONString(ofeedback).toString(), null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void del(Integer ofeedbackId) {
		JSONObject json = new JSONObject();
		json.put("id", ofeedbackId);
		try {
			String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doDelete(bmw_cloud_baseservice_url + ofeedback_del + params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
