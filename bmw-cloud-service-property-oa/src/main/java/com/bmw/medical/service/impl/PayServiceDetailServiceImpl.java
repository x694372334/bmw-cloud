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
import com.bmw.medical.model.PayServiceDetail;
import com.bmw.medical.service.IPayServiceDetailService;

/**
 * <p>
 * 收费项目明细表 服务实现类
 * </p>
 *
 * @author liuwsh123
 * @since 2018-09-18
 */
@Service
public class PayServiceDetailServiceImpl  implements IPayServiceDetailService {

	@Value("${bmw.cloud.medicalservice.url}")
	private String bmw_cloud_baseservice_url = "";
	@Value("${payServiceDetail.findList}")
	private String payServiceDetail_findList = "";
	@Value("${payServiceDetail.getDetail}")
	private String payServiceDetail_getDetail = "";
	@Value("${payServiceDetail.add}")
	private String payServiceDetail_add = "";
	@Value("${payServiceDetail.update}")
	private String payServiceDetail_update = "";
	@Value("${payServiceDetail.del}")
	private String payServiceDetail_del = "";

	@Override
	public JSONArray selectList(PayServiceDetail payServiceDetail) {
		JSONArray jsonArray = null;
		try {
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url + payServiceDetail_findList,
					JSONObject.toJSONString(payServiceDetail).toString(), null);
			jsonArray = JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	public PayServiceDetail selectById(Integer payServiceDetailId) {
		PayServiceDetail payServiceDetail = new PayServiceDetail();
		JSONObject json = new JSONObject();
		json.put("payServiceDetailId", payServiceDetailId);
		try {
			String params = Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doGet(bmw_cloud_baseservice_url + payServiceDetail_getDetail + params, null);
			JSONObject jsonObject = JSON.parseObject(rlt).getJSONObject("items");
			if (jsonObject != null) {
				payServiceDetail = jsonObject.toJavaObject(PayServiceDetail.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return payServiceDetail;
	}

	@Override
	public void insert(PayServiceDetail payServiceDetail) {
		// 获取当前登录人信息
		ShiroExt shiro = new ShiroExt();
		Integer userId = shiro.getUser().getId();
		String userName = shiro.getUser().getName();

		payServiceDetail.setIsDelete(0);
		payServiceDetail.setCreateMan(userName);
		payServiceDetail.setCreateManId(userId);
		payServiceDetail.setCreateTime(new Date());
		payServiceDetail.setEditMan(userName);
		payServiceDetail.setEditManId(userId);
		payServiceDetail.setEditTime(new Date());

		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url + payServiceDetail_add, JSONObject.toJSONString(payServiceDetail).toString(),
					null);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(PayServiceDetail payServiceDetail) {
		// 获取当前登录人信息
		ShiroExt shiro = new ShiroExt();
		Integer userId = shiro.getUser().getId();
		String userName = shiro.getUser().getName();

		payServiceDetail.setEditMan(userName);
		payServiceDetail.setEditManId(userId);
		payServiceDetail.setEditTime(new Date());
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url + payServiceDetail_update, JSONObject.toJSONString(payServiceDetail).toString(),
					null);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteById(Integer payServiceDetailId) {
		JSONObject json = new JSONObject();
		json.put("payServiceDetailId", payServiceDetailId);
		try {
			String params = Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doDelete(bmw_cloud_baseservice_url + payServiceDetail_del + params, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
