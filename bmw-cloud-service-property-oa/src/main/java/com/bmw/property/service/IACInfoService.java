package com.bmw.property.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.ACInfo;
import com.bmw.property.model.Advertising;
import com.bmw.property.model.ParkingInfo;

public interface IACInfoService {
	
	/**
     * 赞助商关联表列表
     */
	JSONArray findList(String condition);
	
	/**
     * 获取赞助商关联表详情
     */
	ACInfo getdetail(Integer hospitalId);
	
	
	/**
     * 新增赞助商关联表信息
     */
	void add(ACInfo hospital);
	
	/**
	 * 修改赞助商关联表信息
	 */
	void update(ACInfo hospital);
	
	/**
	 * 删除赞助商关联表信息
	 */
	void del(Integer hospitalId);

	
}
