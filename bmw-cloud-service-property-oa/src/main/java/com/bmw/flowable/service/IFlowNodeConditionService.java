package com.bmw.flowable.service;

import com.bmw.flowable.model.FlowNodeCondition;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 流程节点条件表 服务类
 * </p>
 *
 * @author zhangtan123
 * @since 2018-08-24
 */
public interface IFlowNodeConditionService {
	/**
     * 列表
     */
	JSONArray findList(FlowNodeCondition condition);
	
	/**
     * 详情
     */
	FlowNodeCondition getdetail(Integer flowNodeConditionId);
	
	
	/**
     * 新增信息
     */
	String add(FlowNodeCondition flowNodeCondition);
	
	/**
	 * 修改信息
	 */
	String update(FlowNodeCondition flowNodeCondition);
	
	/**
	 * 删除信息
	 */
	String del(Integer flowNodeConditionId);
}
