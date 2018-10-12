package com.bmw.property.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.AContractor;
import com.bmw.property.model.ParkingInfo;

public interface IAContractorInfoService {
	
	/**
     * 医院列表
     */
	JSONArray findList(String condition, String nName, String eId);
	
	/**
     * 获取医院详情
     */
	AContractor getdetail(Integer hospitalId);
	
	
	/**
     * 新增医院信息
     */
	void add(AContractor hospital);
	
	/**
	 * 修改医院信息
	 */
	void update(AContractor hospital);
	
	/**
	 * 删除医院信息
	 */
	void del(Integer hospitalId);


}
