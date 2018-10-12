package com.bmw.medical.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.medical.model.Doctor;

public interface IDoctorService {

	/**
	 * 医院列表
	 */
	JSONArray findList(String condition);

	/**
	 * 获取医院详情
	 */
	Doctor getdetail(Integer hospitalId);

	/**
	 * 新增医院信息
	 */
	void add(Doctor hospital);

	/**
	 * 修改医院信息
	 */
	void update(Doctor hospital);

	/**
	 * 删除医院信息
	 */
	void del(Integer hospitalId);

	String findCode();

	Object findAddList(String deptCode);

}
