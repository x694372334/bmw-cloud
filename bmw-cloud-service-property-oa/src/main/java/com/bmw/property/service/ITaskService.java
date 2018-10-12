package com.bmw.property.service;

import com.alibaba.fastjson.JSONArray;
import com.bmw.property.model.Task;
import com.bmw.property.model.TaskVO;

/**
 * <p>
 * 工作任务 服务类
 * </p>
 *
 * @author zhangt123
 * @since 2018-07-18
 */
public interface ITaskService  {
	/**
     * 列表
     */
	JSONArray findList(TaskVO condition);
	
	/**
     * 详情
     */
	Task getdetail(Integer taskId);
	
	
	/**
     * 新增信息
     */
	void add(Task task);
	
	/**
	 * 修改信息
	 */
	void update(Task task);
	
	/**
	 * 删除信息
	 */
	void del(Integer taskId);

}
