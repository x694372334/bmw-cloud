package com.bmw.medical.service;

import com.alibaba.fastjson.JSONArray;
import com.bmw.medical.model.ScheduleHis;

public interface IScheduleHisService {
	
	/**
     * 医院列表
     */
	JSONArray findList(String condition);
	
	/**
     * 获取医院详情
     */
	ScheduleHis getdetail(Integer hospitalId);
	
	
	/**
     * 新增医院信息
     */
	void add(ScheduleHis hospital);
	
	/**
	 * 修改医院信息
	 */
	void update(ScheduleHis hospital);
	
	/**
	 * 删除医院信息
	 */
	void del(Integer hospitalId);
	

}
