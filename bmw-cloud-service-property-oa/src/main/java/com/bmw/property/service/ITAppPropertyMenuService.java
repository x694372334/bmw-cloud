package com.bmw.property.service;

import com.bmw.property.model.TAppPropertyMenu;
import com.alibaba.fastjson.JSONArray;

/**
 * <p>
 * app物业菜单表 服务类
 * </p>
 *
 * @author zhangt123
 * @since 2018-07-26
 */
public interface ITAppPropertyMenuService {
	/**
     * 列表
     */
	JSONArray findList(TAppPropertyMenu condition);
	
	/**
     * 详情
     */
	TAppPropertyMenu getdetail(Integer tAppPropertyMenuId);
	
	
	/**
     * 新增信息
     */
	void add(TAppPropertyMenu tAppPropertyMenu);
	
	/**
	 * 修改信息
	 */
	void update(TAppPropertyMenu tAppPropertyMenu);
	
	/**
	 * 删除信息
	 */
	void del(Integer tAppPropertyMenuId);

	/**
	 * 查询此角色已有的App权限
	 */
	JSONArray findListByRoleId(Integer id);
}
