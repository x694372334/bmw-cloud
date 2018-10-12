package com.bmw.property.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.medical.model.Doctor;
import com.bmw.property.model.BuildingInfo;
import com.bmw.property.model.Neighborhood;

public interface IBuildingInfoService {
	
	/**
     * 医院列表
     */
	JSONArray findList(String xqName, String lyName, String eId);
	
	/**
     * 获取医院详情
     */
	BuildingInfo getdetail(Integer hospitalId);
	
	
	/**
     * 新增医院信息
     */
	void add(BuildingInfo hospital);
	
	/**
	 * 修改医院信息
	 */
	void update(BuildingInfo hospital);
	
	/**
	 * 删除医院信息
	 */
	int del(Integer hospitalId);
	
	JSONArray findNeighbor(Integer eId);
	JSONArray findNeighborUpdate(Integer nId);
	
	String getNewCode(Map<String, String> map);

}
