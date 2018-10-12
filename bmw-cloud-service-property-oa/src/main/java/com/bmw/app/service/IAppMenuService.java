package com.bmw.app.service;

import com.bmw.app.model.AppMenu;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  菜单服务类
 * </p>
 *
 * @author fjm123
 * @since 2018-05-17
 */
public interface IAppMenuService {
	
	/**
     * 商品列表
     */
	JSONArray findList(String condition);
	
	/**
     * 获取商品详情
     */
	AppMenu getdetail(Integer appMenuId);
	
	
	/**
     * 新增商品信息
     */
	void add(AppMenu appMenu);
	
	/**
	 * 修改商品信息
	 */
	void update(AppMenu appMenu);
	
	/**
	 * 删除商品信息
	 */
	void del(Integer appMenuId);

}
