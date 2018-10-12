package com.bmw.app.service;

import com.bmw.app.model.Recommend;
import com.bmw.app.model.SlideShow;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-05-23
 */
public interface IRecommendService  {

	/**
     * 轮播图片列表
     */
	JSONArray findList(String condition);
	
	/**
     * 获取轮播图片详情
     */
	Recommend getdetail(Integer slideShowId);
	
	
	/**
     * 新增轮播图片信息
     */
	void add(Recommend slideShow);
	
	/**
	 * 修改轮播图片信息
	 */
	void update(Recommend slideShow);
	
	/**
	 * 删除轮播图片信息
	 */
	void del(Integer slideShowId);

	
}
