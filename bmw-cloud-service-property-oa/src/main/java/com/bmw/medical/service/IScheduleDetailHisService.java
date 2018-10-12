package com.bmw.medical.service;

import com.alibaba.fastjson.JSONArray;
import com.bmw.medical.model.Schedule;
import com.bmw.medical.model.ScheduleDetailHis;

public interface IScheduleDetailHisService {
	
	/**
     * 医院列表
     */
	JSONArray findList(String condition);
	
	/**
     * 获取医院详情
     */
	ScheduleDetailHis getdetail(Integer hospitalId);
	
	
	/**
     * 新增医院信息
     */
	void add(ScheduleDetailHis ScheduleDetailHis);
	
	/**
	 * 修改医院信息
	 */
	void update(ScheduleDetailHis ScheduleDetailHis);
	
	/**
	 * 删除医院信息
	 */
	void del(Integer hospitalId);
	

}
