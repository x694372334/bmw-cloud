package com.bmw.medical.service.impl;

import java.io.IOException;
import java.util.Date;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.bmw.medical.model.PayService;
import com.bmw.medical.service.IPayServiceService;

/**
 * <p>
 * 收费项目 服务实现类
 * </p>
 *
 * @author liuwsh123
 * @since 2018-09-18
 */
@Service
public class PayServiceServiceImpl  implements IPayServiceService {
	@Value("${bmw.cloud.medicalservice.url}")
	private String bmw_cloud_baseservice_url = "";
	@Value("${payService.findList}")
	private String payService_findList = "";
	@Value("${payService.getDetail}")
	private String payService_getDetail = "";
	@Value("${payService.add}")
	private String payService_add = "";
	@Value("${payService.update}")
	private String payService_update = "";
	@Value("${payService.del}")
	private String payService_del = "";

	@Override
	public JSONArray selectList(PayService payService) {
		JSONArray jsonArray = null;
		try {
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url + payService_findList,
					JSONObject.toJSONString(payService).toString(), null);
			jsonArray = JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	public PayService selectById(Integer payServiceId) {
		PayService payService = new PayService();
		JSONObject json = new JSONObject();
		json.put("payServiceId", payServiceId);
		try {
			String params = Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doGet(bmw_cloud_baseservice_url + payService_getDetail + params, null);
			JSONObject jsonObject = JSON.parseObject(rlt).getJSONObject("items");
			if (jsonObject != null) {
				payService = jsonObject.toJavaObject(PayService.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return payService;
	}

	@Override
	public void insert(PayService payService) {
		// 获取当前登录人信息
		ShiroExt shiro = new ShiroExt();
		Integer userId = shiro.getUser().getId();
		String userName = shiro.getUser().getName();

		payService.setIsDelete(0);
		payService.setCreateMan(userName);
		payService.setCreateManId(userId);
		payService.setCreateTime(new Date());
		payService.setEditMan(userName);
		payService.setEditManId(userId);
		payService.setEditTime(new Date());

		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url + payService_add, JSONObject.toJSONString(payService).toString(),
					null);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(PayService payService) {
		// 获取当前登录人信息
		ShiroExt shiro = new ShiroExt();
		Integer userId = shiro.getUser().getId();
		String userName = shiro.getUser().getName();

		payService.setEditMan(userName);
		payService.setEditManId(userId);
		payService.setEditTime(new Date());
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url + payService_update, JSONObject.toJSONString(payService).toString(),
					null);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteById(Integer payServiceId) {
		JSONObject json = new JSONObject();
		json.put("payServiceId", payServiceId);
		try {
			String params = Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doDelete(bmw_cloud_baseservice_url + payService_del + params, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
