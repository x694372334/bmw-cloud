package com.bmw.medical.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.medical.model.Hospital;

public interface IHospitalService {

	/**
     * 医院列表
     */
	JSONArray findList(String condition);
	
	/**
     * 获取医院详情
     */
	Hospital getdetail(Integer hospitalId);
	
	
	/**
     * 新增医院信息
     */
	void add(Hospital hospital);
	
	/**
	 * 修改医院信息
	 */
	void update(Hospital hospital);
	
	/**
	 * 删除医院信息
	 */
	void del(Integer hospitalId);
	
	List<ZTreeNode> areaTreeList();

	String findCode();
	
}
