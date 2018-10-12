package com.bmw.flowable.service;

import com.bmw.flowable.model.FlowNodeConfigure;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 流程节点配置表 服务类
 * </p>
 *
 * @author zhangtan123
 * @since 2018-08-24
 */
public interface IFlowNodeConfigureService  {
	/**
     * 列表
     */
	JSONArray findList(FlowNodeConfigure condition);
	
	/**
     * 详情
     */
	FlowNodeConfigure getdetail(Integer flowNodeConfigureId);
	
	
	/**
     * 新增信息
     */
	String add(FlowNodeConfigure flowNodeConfigure);
	
	/**
	 * 修改信息
	 */
	String update(FlowNodeConfigure flowNodeConfigure);
	
	/**
	 * 删除信息
	 */
	String del(Integer flowNodeConfigureId);
}
