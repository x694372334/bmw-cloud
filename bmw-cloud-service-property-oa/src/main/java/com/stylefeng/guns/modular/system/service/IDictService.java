package com.stylefeng.guns.modular.system.service;

import com.alibaba.fastjson.JSONArray;
import com.stylefeng.guns.modular.system.model.Dict;

/**
 * 字典服务
 * @author lyl
 * @date 2018-04-19
 */
public interface IDictService{

	/**
     * 字典列表
     */
	JSONArray findList(String condition);
	
	/**
     * 获取字典详情
     */
	Dict getdetail(Integer dictId);
	
	
	/**
     * 获取子字典信息
     */
	JSONArray getDictByPid(Integer dictId);
	
	/**
     * 新增字典信息
     */
	void add(String dictName, String dictValues);
	
	/**
	 * 修改字典信息
	 */
	void update(Integer dictId, String dictName, String dictValues);
	
	/**
	 * 删除字典信息
	 */
	void del(Integer dictId);
	
	public JSONArray getDictByCode(String code);
}
