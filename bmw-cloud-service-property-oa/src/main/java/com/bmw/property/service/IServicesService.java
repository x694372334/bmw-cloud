package com.bmw.property.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.Services;

/**
 * <p>
 * 特色服务 服务类
 * </p>
 *
 * @author zhangt123
 * @since 2018-07-17
 */
public interface IServicesService {
	/**
     * 列表
     */
	JSONArray findList(Services condition);
	
	/**
     * 详情
     */
	Services getdetail(Integer servicesId);
	
	
	/**
     * 新增信息
     */
	void add(Services services);
	
	/**
	 * 修改信息
	 */
	void update(Services services);
	
	/**
	 * 删除信息
	 */
	void del(Integer servicesId);

	List<ZTreeNode> getServiceSortTree();
}
