package com.bmw.property.service.impl;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.core.base.tips.SuccessTip;
import com.stylefeng.guns.core.base.tips.Tip;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.CommunityBulletin;
import com.bmw.property.service.ICommunityBulletinService;

@Service
public class CommunityBulletinServiceImpl implements ICommunityBulletinService {
	Logger log = LoggerFactory.getLogger(CommunityBulletinServiceImpl.class);
	@Value("${bmw.cloud.propertyservice.url}")
	private String bmw_cloud_propertyservice_url = "";
	@Value("${communityBulletin.insert}")
	private String communityBulletin_insert;
	@Value("${communityBulletin.selectListByParam}")
	private String communityBulletin_selectListByParam;
	@Value("${communityBulletin.selectById}")
	private String communityBulletin_selectById;
	@Value("${communityBulletin.queryBuildingTree.getBuildingNames}")
	private String communityBulletin_queryBuildingTree_getBuildingNames;
	@Value("${communityBulletin.queryBuildingTree.createTreeDemo}")
	private String communityBulletin_queryBuildingTree_createTreeDemo;
	@Value("${communityBulletin.sub}")
	private String communityBulletin_sub;
	@Value("${communityBulletin.pub}")
	private String communityBulletin_pub;
	@Value("${communityBulletin.callback}")
	private String communityBulletin_callback;
	@Value("${communityBulletin.updateById}")
	private String communityBulletin_updateById;
	@Value("${communityBulletin.delete}")
	private String communityBulletin_delete;
	@Value("${communityBulletin.check}")
	private String communityBulletin_check;
	
	/**
	 * 查询小区公告列表
	 */
	@Override
	public JSONObject selectListByParam(Map<String, Object> param) {
		JSONObject jsonrlt = new JSONObject();
		try {
			String rlt = HttpUtils.doPost(bmw_cloud_propertyservice_url + communityBulletin_selectListByParam,
					JSONObject.toJSONString(param), null);
			jsonrlt = JSONObject.parseObject(rlt).getJSONObject("items");
		} catch (Exception e) {
			log.error("{}查询小区公告列表报错", e);
			jsonrlt.put("total", 0);
			jsonrlt.put("result", new ArrayList<CommunityBulletin>());
		}
		return jsonrlt;
	}
	/**
	 * 新增公告
	 */
	@Override
	public Tip insert(CommunityBulletin entity) {
		return doPostMethod(bmw_cloud_propertyservice_url + communityBulletin_insert,entity,"添加小区公告失败");
	}
	/**
	 * 修改小区公告
	 */
	@Override
	public Tip updateById(CommunityBulletin entity) {
		return doPostMethod(bmw_cloud_propertyservice_url + communityBulletin_updateById,entity,"修改小区公告失败");
	}
	/**
	 * 查询发送范围的楼宇
	 */
	@Override
	public List<String> getBuildingNames(Integer cbId) {
		List<String> buildingNameList = new ArrayList<String>();
		try {
			String rlt = HttpUtils.doGet(bmw_cloud_propertyservice_url + communityBulletin_queryBuildingTree_getBuildingNames+ cbId);
			JSONObject json=JSONObject.parseObject(rlt);
			if(json.getIntValue("statusCode")==HttpStatus.OK.value()) {
				buildingNameList = json.getJSONObject("items").getJSONArray("buildingNames").toJavaList(String.class);
			}else {
				log.error(json.getString("msg"));
			}
		} catch (Exception e) {
			log.error("查询楼宇名信息失败",e);
		}
		return buildingNameList;
	}

	/**
	 * 查询公告详情
	 */
	@Override
	public CommunityBulletin selectById(Serializable id) {
		CommunityBulletin cb = new CommunityBulletin();		
		try {
			String rlt = HttpUtils.doGet(bmw_cloud_propertyservice_url + communityBulletin_selectById+ id);
			JSONObject json=JSONObject.parseObject(rlt);
			if(json.getIntValue("statusCode")==HttpStatus.OK.value()) {
				JSONObject jsonrlt = JSONObject.parseObject(rlt).getJSONObject("items");
				cb = jsonrlt.toJavaObject(CommunityBulletin.class);
			}else {
				log.error(json.getString("msg"));
			}
		} catch (Exception e) {
			log.error("查询楼宇名信息失败",e);
		}
		return cb;
	}
	/**
	 * 根据企业id、公告id（非必须，编辑功能的时候需要，用于回显）查询小区-楼宇的树
	 */
	@Override
	public List<ZTreeNode> queryBuildingTree(Integer cbId, Integer eId) {
		Map<String, Integer> paramMap = new HashMap<>();
		paramMap.put("cbId", cbId);
		paramMap.put("eId", eId);
		List<ZTreeNode> treeList = new ArrayList<>();
			// 根据企业ID查询楼宇树
			try {
				String rlt= HttpUtils.doPost( bmw_cloud_propertyservice_url + communityBulletin_queryBuildingTree_createTreeDemo,JSONObject.toJSONString(paramMap), null);
				JSONObject resultJson = JSONObject.parseObject(rlt);
				if (resultJson.getIntValue("statusCode") == HttpStatus.CREATED.value()) {
					treeList = resultJson.getJSONObject("items").getJSONArray("treeList").toJavaList(ZTreeNode.class);
				}else {
					log.error(resultJson.getString("msg"));
				}
			} catch (ParseException | IOException e) {
				log.error("{}查询小区-楼宇数据失败",e);
			}
			
		return treeList;
	}
	/**
	 * 提交小区公告审核
	 */
	@Override
	public Tip sub(CommunityBulletin entity) {
		return doPostMethod(bmw_cloud_propertyservice_url + communityBulletin_sub,entity,"提交小区公告审核失败");
	}
	/**
	 * 发布小区公告
	 */
	@Override
	public Tip pub(CommunityBulletin entity) {
		return doPostMethod(bmw_cloud_propertyservice_url + communityBulletin_pub,entity,"发布小区公告失败");
	}
	/**
	 * 撤销小区公告
	 */
	@Override
	public Tip callback(CommunityBulletin entity) {
		return doPostMethod(bmw_cloud_propertyservice_url + communityBulletin_callback,entity,"撤销小区公告审核失败");
	}
	/**
	 * 删除小区公告
	 */
	@Override
	public Tip delete(CommunityBulletin entity) {
		return doPostMethod(bmw_cloud_propertyservice_url + communityBulletin_delete,entity,"删除小区公告失败");
	}
	/**
	 * 审核小区公告
	 */
	@Override
	public Tip check(CommunityBulletin entity) {
		return doPostMethod(bmw_cloud_propertyservice_url + communityBulletin_check,entity,"审核小区公告失败");
	}

	/**
	 * 为了post调用提取的公共方法
	 * @param url
	 * @param entity
	 * @param errorMsg
	 * @return
	 */
	private Tip doPostMethod(String url,CommunityBulletin entity,String errorMsg) {
			Tip tip=null;
			try {
				String rlt = HttpUtils.doPost(url,JSONObject.toJSONString(entity),null);
				JSONObject jsonrlt = JSONObject.parseObject(rlt);
				if (jsonrlt.getIntValue("statusCode")==HttpStatus.CREATED.value()) {//返回码是201
					int count=jsonrlt.getIntValue("items");
					if(count>0) {// 更新或者插入的条数>0
						tip= new SuccessTip();
					}
				} else {
					log.error("{}"+jsonrlt.getString("msg"));
						tip= new ErrorTip(HttpStatus.NOT_IMPLEMENTED.value(),jsonrlt.getString("msg")); 
				}
			} catch (ParseException | IOException e) {
				log.error("{}"+errorMsg,e);
				tip= new ErrorTip(HttpStatus.NOT_IMPLEMENTED.value(),"后台异常，请联系管理员"); 
			}
		return tip;
	}
}
