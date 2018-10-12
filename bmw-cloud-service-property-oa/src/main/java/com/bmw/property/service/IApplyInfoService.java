package com.bmw.property.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.IService;
import com.bmw.property.model.ApplyInfo;
import com.bmw.property.model.Approvalitem;

/**
 * <p>
 * 审批申请单 服务类
 * </p>
 *
 * @author Jinmy123
 * @since 2018-07-19
 */
public interface IApplyInfoService  {
	
	/**
     * 赞助商关联表列表
     */
	JSONArray findList(String aType, String eaResult, String uesrId);
	
	/**
     * 获取赞助商关联表详情
     */
	ApplyInfo getdetail(Integer hospitalId);
	
	
	/**
     * 新增赞助商关联表信息
     */
	void add(ApplyInfo hospital);
	
	/**
	 * 修改赞助商关联表信息
	 */
	void update(ApplyInfo hospital);
	
	/**
	 * 删除赞助商关联表信息
	 */
	void del(Integer hospitalId);
	/**
	 * 
	 * */
	void flowabled_add(ApplyInfo hospital);
	
	void flowabled_apply(String taskId, String id);
	
	void flowabled_reject(String taskId, String id);

}
