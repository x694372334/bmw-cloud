package com.bmw.medical.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.medical.model.Schedule;

public interface IScheduleService {

	/**
     * 医院列表
     */
	JSONArray findList(Schedule schedule);
	
	/**
     * 获取医院详情
     */
	Schedule getdetail(Integer scheduleId);
	
	
	/**
     * 新增医院信息
     */
	void add(Schedule schedule);
	
	/**
	 * 修改医院信息
	 */
	void update(Schedule schedule);
	
	/**
	 * 删除医院信息
	 */
	void del(Integer scheduleId);

	List<ZTreeNode> getTreeList();
	
}
