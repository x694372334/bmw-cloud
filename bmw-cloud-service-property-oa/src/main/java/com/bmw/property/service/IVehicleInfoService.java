package com.bmw.property.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.IVehicleInfo;
import com.bmw.property.model.ParkingInfo;

public interface IVehicleInfoService {
	
	/**
     * 医院列表
     */
	JSONArray findList(String condition, String nName, String iName, String eId);
	
	/**
     * 获取医院详情
     */
	IVehicleInfo getdetail(Integer hospitalId);
	
	
	/**
     * 新增医院信息
     */
	void add(IVehicleInfo hospital);
	
	/**
	 * 修改医院信息
	 */
	void update(IVehicleInfo hospital);
	
	/**
	 * 删除医院信息
	 */
	void del(Integer hospitalId);
	

}
