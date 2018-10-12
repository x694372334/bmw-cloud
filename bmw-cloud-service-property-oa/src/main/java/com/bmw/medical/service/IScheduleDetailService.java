package com.bmw.medical.service;

import com.alibaba.fastjson.JSONArray;
import com.bmw.medical.model.ScheduleDetail;

public interface IScheduleDetailService {
	
	/**
     * 医院列表
     */
	JSONArray findList(ScheduleDetail scheduleDetail);
	
	/**
     * 获取医院详情
     */
	ScheduleDetail getdetail(Integer scheduleDetailId);
	
	
	/**
     * 新增医院信息
     */
	void add(ScheduleDetail scheduleDetail);
	
	/**
	 * 修改医院信息
	 */
	void update(ScheduleDetail scheduleDetail);
	
	/**
	 * 删除医院信息
	 */
	void del(Integer scheduleDetailId);

	JSONArray findAllList(ScheduleDetail scheduleDetail);

}
