package com.bmw.property.service;

import com.bmw.property.model.Account;
import com.bmw.property.model.Announcement;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 个人账户表 服务类
 * </p>
 *
 * @author zhangt123
 * @since 2018-07-09
 */
public interface IAccountService  {
	/**
     * 列表
     */
	JSONArray findList(Account condition);
	
	/**
     * 详情
     */
	Account getdetail(Integer accountId);
	
	
	/**
     * 新增信息
     */
	void add(Account account);
	
	/**
	 * 修改信息
	 */
	void update(Account account);
	
	/**
	 * 删除信息
	 */
	void del(Integer accountId);
}
