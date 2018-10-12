package com.bmw.flowable.service;


import com.alibaba.fastjson.JSONArray;


/**
 * <p>
 * 工作流通用类
 * </p>
 *
 * @author zhangt123
 * @since 2018-07-17
 */
public interface IHiTaskService {

	/**
	 * 查询代办任务
	 */
	public JSONArray findList(String name);

	public JSONArray  history(String pid);

}
