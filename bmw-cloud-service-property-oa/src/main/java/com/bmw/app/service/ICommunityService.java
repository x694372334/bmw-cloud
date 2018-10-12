package com.bmw.app.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.IService;
import com.bmw.app.model.Community;
import com.bmw.app.model.Recommend;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-05-23
 */
public interface ICommunityService  {
	
	/**
     * 轮播图片列表
     */
	JSONArray findList(String condition);
	
	/**
     * 获取轮播图片详情
     */
	Community getdetail(Integer slideShowId);
	
	
	/**
     * 新增轮播图片信息
     */
	void add(Community slideShow);
	
	/**
	 * 修改轮播图片信息
	 */
	void update(Community slideShow);
	
	/**
	 * 删除轮播图片信息
	 */
	void del(Integer slideShowId);

	

}
