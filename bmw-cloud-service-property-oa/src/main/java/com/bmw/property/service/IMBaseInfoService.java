package com.bmw.property.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.MBaseInfo;
import com.bmw.property.model.ParkingInfo;

public interface IMBaseInfoService {
	
	/**
     * 医院列表
     */
	JSONArray findList(String nName, String bName, String rRoomnum, String eId);
	
	/**
     * 医院列表
     */
	JSONArray findDoubleList(String nName, String bName, String rRoomnum, String eId);
	
	/**
     * 获取医院详情
     */
	MBaseInfo getdetail(Integer hospitalId);
	
	
	/**
     * 新增医院信息
     */
	void add(MBaseInfo hospital);
	
	/**
	 * 修改医院信息
	 */
	void update(MBaseInfo hospital);
	
	/**
	 * 删除医院信息
	 */
	void del(Integer hospitalId);
	/**
     * 获取医院详情
     */
	MBaseInfo findDosage(MBaseInfo mBase);
	
	MBaseInfo findDouble(MBaseInfo mBase);

	/**
	 * 一键生成账单
	 * 
	 * @param mBaseInfoIds 前台抄表基础管理列表中选中的数据的id
	 * @return 
	 */
	String saveBills(String mBaseInfoIds);

}
