package com.bmw.flowable.service;


import java.io.IOException;

import com.alibaba.fastjson.JSONArray;


/**
 * <p>
 * 工作流通用类
 * </p>
 *
 * @author zhangt123
 * @since 2018-07-17
 */
public interface IRuTaskService {

	/**
	 * 查询代办任务
	 */
	public JSONArray findList(String name);
	
	/**
	 * 查询流程图
	 * @param 任务ID
	 */
	void printProcessImage(String taskId) throws IOException;

	 /**
     * 删除流程实例
     */
	public void deletePro(String procInstId);

}
