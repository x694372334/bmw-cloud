package com.bmw.flowable.service;

import com.alibaba.fastjson.JSONArray;
import com.bmw.flowable.model.FlowConfigure;

/**
 * <p>
 * 流程配置表 服务类
 * </p>
 *
 * @author zhangtan123
 * @since 2018-08-24
 */
public interface IFlowConfigureService  {
	/**
     * 列表
     */
	JSONArray findList(FlowConfigure condition);
	
	/**
     * 详情
     */
	FlowConfigure getdetail(Integer flowConfigureId);
	
	
	/**
     * 新增信息
     */
	String add(FlowConfigure flowConfigure);
	
	/**
	 * 修改信息
	 */
	String update(FlowConfigure flowConfigure);
	
	/**
	 * 删除信息
	 */
	String del(Integer flowConfigureId);
}
