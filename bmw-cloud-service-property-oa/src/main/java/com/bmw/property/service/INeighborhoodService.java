package com.bmw.property.service;

import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.bmw.property.model.Neighborhood;


public interface INeighborhoodService {
	
	/**
     * 医院列表
     */
	JSONArray findList(Integer eId, String condition);
	
	/**
     * 获取医院详情
     */
	Neighborhood getdetail(Integer hospitalId);
	
	
	/**
     * 新增医院信息
     */
	void add(Neighborhood hospital);
	
	/**
	 * 修改医院信息
	 */
	void update(Neighborhood hospital);
	
	/**
	 * 删除医院信息
	 */
	int del(Integer hospitalId);
	
	String getNewCode(Map<String, String> map);
	
	JSONArray findAddress(String data);
	
	public Neighborhood findEid(Integer eId);
	
	JSONArray findEditAddress(String data);

}
