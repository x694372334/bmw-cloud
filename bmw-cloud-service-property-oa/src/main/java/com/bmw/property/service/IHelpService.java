package com.bmw.property.service;

import com.alibaba.fastjson.JSONArray;
import com.bmw.property.model.Help;

/**
 * <p>
 * 帮助 服务类
 * </p>
 *
 * @author zhangt123
 * @since 2018-07-09
 */
public interface IHelpService {
	/**
     * 列表
     */
	JSONArray findList(Help condition);
	
	/**
     * 详情
     */
	Help getdetail(Integer helpId);
	
	
	/**
     * 新增信息
     */
	void add(Help help);
	
	/**
	 * 修改信息
	 */
	void update(Help help);
	
	/**
	 * 删除信息
	 */
	void del(Integer helpId);
}
