package com.bmw.app.service;

import com.bmw.app.model.Commodity;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.Dict;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fjm123
 * @since 2018-05-17
 */
public interface ICommodityService {
	
	/**
     * 商品列表
     */
	JSONArray findList(String condition);
	
	/**
     * 获取商品详情
     */
	Commodity getdetail(Integer commodityId);
	
	
	/**
     * 新增商品信息
     */
	void add(Commodity commodity);
	
	/**
	 * 修改商品信息
	 */
	void update(Commodity commodity);
	
	/**
	 * 删除商品信息
	 */
	void del(Integer commodityId);

}
