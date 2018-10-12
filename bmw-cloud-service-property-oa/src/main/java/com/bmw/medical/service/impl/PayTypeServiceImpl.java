package com.bmw.medical.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.medical.model.PayType;
import com.bmw.medical.service.IPayTypeService;

/**
 * <p>
 * 收费项目分类 服务实现类
 * </p>
 *
 * @author liuwsh
 * @since 2018-09-18
 */
@Service
public class PayTypeServiceImpl implements IPayTypeService {
	@Value("${bmw.cloud.medicalservice.url}")
	private String bmw_cloud_baseservice_url = "";
	@Value("${payType.findList}")
	private String payType_findList = "";
	@Value("${payType.getDetail}")
	private String payType_getDetail = "";
	@Value("${payType.add}")
	private String payType_add = "";
	@Value("${payType.update}")
	private String payType_update = "";
	@Value("${payType.del}")
	private String payType_del = "";
	@Value("${payType.tree}")
	private String payType_tree = "";
	@Value("${payType.selectOne}")
	private String payType_selectOne = "";

	@Override
	public JSONArray selectList(PayType payType) {
		JSONArray jsonArray = null;
		try {
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url + payType_findList,
					JSONObject.toJSONString(payType).toString(), null);
			jsonArray = JSON.parseObject(rlt).getJSONArray("items");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	public PayType selectById(Integer payTypeId) {
		PayType payType = new PayType();
		JSONObject json = new JSONObject();
		json.put("payTypeId", payTypeId);
		try {
			String params = Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			String rlt = HttpUtils.doGet(bmw_cloud_baseservice_url + payType_getDetail + params, null);
			JSONObject jsonObject = JSON.parseObject(rlt).getJSONObject("items");
			if (jsonObject != null) {
				payType = jsonObject.toJavaObject(PayType.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return payType;
	}

	@Override
	public void insert(PayType payType) {
		// 获取当前登录人信息
		ShiroExt shiro = new ShiroExt();
		Integer userId = shiro.getUser().getId();
		String userName = shiro.getUser().getName();

		payType.setIsDelete(0);
		payType.setCreateMan(userName);
		payType.setCreateManId(userId);
		payType.setCreateTime(new Date());
		payType.setEditMan(userName);
		payType.setEditManId(userId);
		payType.setEditTime(new Date());

		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url + payType_add, JSONObject.toJSONString(payType).toString(),
					null);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(PayType payType) {
		// 获取当前登录人信息
		ShiroExt shiro = new ShiroExt();
		Integer userId = shiro.getUser().getId();
		String userName = shiro.getUser().getName();

		payType.setEditMan(userName);
		payType.setEditManId(userId);
		payType.setEditTime(new Date());
		try {
			HttpUtils.doPost(bmw_cloud_baseservice_url + payType_update, JSONObject.toJSONString(payType).toString(),
					null);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteById(Integer payTypeId) {
		JSONObject json = new JSONObject();
		json.put("payTypeId", payTypeId);
		try {
			String params = Base64Utils.encodeToString(JSON.toJSONString(json).toString().getBytes("UTF-8"));
			HttpUtils.doDelete(bmw_cloud_baseservice_url + payType_del + params, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ZTreeNode> payTypeTreeList() {
		List<ZTreeNode> list = new ArrayList<ZTreeNode>();
		JSONArray jsonArray = null;
		try {
			String rlt = HttpUtils.doGet(bmw_cloud_baseservice_url + payType_tree);
			jsonArray = JSON.parseObject(rlt).getJSONArray("items");
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject ob = (JSONObject) jsonArray.get(i);// 得到json对象
				jsonArray.get(i);
				ZTreeNode tree = JSONObject.toJavaObject(ob, ZTreeNode.class);
				list.add(tree);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public PayType selectOne(PayType payType) {
		JSONObject json = new JSONObject();
		try {
			String rlt = HttpUtils.doPost(bmw_cloud_baseservice_url + payType_selectOne,
					JSONObject.toJSONString(payType).toString(), null);
			json = JSON.parseObject(rlt).getJSONObject("items");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null == json ? null : json.toJavaObject(PayType.class);
	}
}