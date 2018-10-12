package com.bmw.property.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.IService;
import com.bmw.bill.model.Bill;
import com.bmw.bill.model.BillVO;
import com.bmw.property.model.Announcement;

/**
 * <p>
 * 系统公告 服务类
 * </p>
 *
 * @author zhangt123
 * @since 2018-07-06
 */
public interface IAnnouncementService  {
	/**
     * 列表
     */
	JSONArray findList(Announcement condition);
	
	/**
     * 详情
     */
	Announcement getdetail(Integer announcementId);
	
	
	/**
     * 新增信息
     */
	void add(Announcement bill);
	
	/**
	 * 修改信息
	 */
	void update(Announcement bill);
	
	/**
	 * 删除信息
	 */
	void del(Integer announcementId);

	void updateHaveRead(Integer announcementId);

}
