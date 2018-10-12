package com.bmw.property.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.ACInfo;
import com.bmw.property.model.Advertising;
import com.bmw.property.model.Approvalitem;
import com.bmw.property.model.ParkingInfo;

public interface IApprovalitemService {
	
	/**
     * 事项审批关联表列表
     */
	JSONArray findList(String condition);
	
	/**
     * 获取事项审批关联表详情
     */
	Approvalitem getdetail(Integer hospitalId);
	
	
	/**
     * 新增事项审批关联表信息
     */
	void add(Approvalitem hospital);
	
	/**
	 * 修改事项审批关联表信息
	 */
	void update(Approvalitem hospital);
	
	/**
	 * 删除事项审批关联表信息
	 */
	void del(Integer hospitalId);

	
}
