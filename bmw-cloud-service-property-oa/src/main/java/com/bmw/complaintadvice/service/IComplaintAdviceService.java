package com.bmw.complaintadvice.service;

import com.bmw.complaintadvice.model.ComplaintAdvice;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 投诉建议 服务类
 * </p>
 *
 * @author zhangt123
 * @since 2018-06-21
 */
public interface IComplaintAdviceService  {
	/**
     * 列表
     */
	JSONArray findList(ComplaintAdvice condition);
	
	/**
     * 详情
     */
	ComplaintAdvice getdetail(Integer complaintAdviceId);
	
	
	/**
     * 新增信息
     */
	void add(ComplaintAdvice complaintAdvice);
	
	/**
	 * 修改信息
	 */
	void update(ComplaintAdvice complaintAdvice);
	
	/**
	 * 删除信息
	 */
	void del(Integer complaintAdviceId);
}
