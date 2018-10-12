package com.bmw.property.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.MBaseInfo;
import com.bmw.property.model.MRecord;
import com.bmw.property.model.ParkingInfo;

public interface IMRecordService {
	
	/**
     * 医院列表
     */
	JSONArray findList(String nName, String bName, String rRoomnum, String eId);
	
	/**
     * 获取医院详情
     */
	MRecord getdetail(Integer hospitalId);
	
	
	/**
     * 新增医院信息
     */
	void add(MRecord hospital);
	
	/**
	 * 修改医院信息
	 */
	void update(MRecord hospital);
	
	/**
	 * 删除医院信息
	 */
	void del(Integer hospitalId);
	/**
	 * 查询月份
	 * */
	String findMonth(Integer sId, String tMonth);

}
