package com.bmw.property.service;

import com.alibaba.fastjson.JSONArray;
import com.bmw.property.model.SelectBulter;

public interface ISelectBulterService {
	
	/**
     * 医院列表
     */
	JSONArray findList(String condition, Integer ss, String data);
	
	/**
     * 获取医院详情
     */
	SelectBulter getdetail(Integer hospitalId);
}
