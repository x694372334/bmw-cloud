package com.bmw.app.service;

import com.bmw.app.model.AppNotice;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  通知服务类
 * </p>
 *
 * @author fjm123
 * @since 2018-05-17
 */
public interface IAppNoticeService  {
	
	/**
     * 通知列表
     */
	JSONArray findList(String condition);
	
	/**
     * 获取通知详情
     */
	AppNotice getdetail(Integer appNoticeId);
	
	
	/**
     * 新增通知信息
     */
	void add(AppNotice appNotice);
	
	/**
	 * 修改通知信息
	 */
	void update(AppNotice appNotice);
	
	/**
	 * 删除通知信息
	 */
	void del(Integer appNoticeId);

}
