package com.bmw.property.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.stylefeng.guns.core.base.tips.Tip;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.medical.model.Doctor;
import com.bmw.property.model.InhabitantInfo;
import com.bmw.property.model.RoomInfo;

public interface IRoomInfoService {
	
	Tip BatchButler(Map<String, Object> paramMap);
	
	/**
     * 医院列表
     */
	JSONArray findList(String xqName, String lyName, String fjName, String eId);
	
	/**
     * 获取医院详情
     */
	RoomInfo getdetail(Integer hospitalId);
	
	
	/**
     * 新增医院信息
     */
	void add(RoomInfo hospital);
	
	/**
	 * 修改医院信息
	 */
	void update(RoomInfo hospital);
	
	/**
	 * 删除医院信息
	 */
	void del(Integer hospitalId);
	/**
	 * 生成小区、楼宇、房屋树
	 * @param nbId 
	 */
	List<ZTreeNode> createNBTree(Integer eId, Integer level, Integer nbId);

	JSONArray findBuilding(Integer roomInfoId);
	
	JSONArray findBuildingData(Integer roomInfoId);
	
	String getNewCode(Map<String, String> map);
	
	JSONArray findUser(Integer eId);

	void impExcel(MultipartFile file, String nId, String rcode, String bId);
	
	void lotAdd(RoomInfo roomInfo);
	
	JSONArray findBuilding(Integer nId, String name);

	Object selectData(Integer nbId, Integer id, Integer relevanceId, Integer costId);
	
	public List<ZTreeNode> queryNBTree(Integer eId);
	
}
