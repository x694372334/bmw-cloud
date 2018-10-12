package com.bmw.app.service;

import com.bmw.app.model.SlideShow;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  轮播图片服务类
 * </p>
 *
 * @author fjm123
 * @since 2018-05-17
 */
public interface ISlideShowService {
	
	/**
     * 轮播图片列表
     */
	JSONArray findList(String condition);
	
	/**
     * 获取轮播图片详情
     */
	SlideShow getdetail(Integer slideShowId);
	
	
	/**
     * 新增轮播图片信息
     */
	void add(SlideShow slideShow);
	
	/**
	 * 修改轮播图片信息
	 */
	void update(SlideShow slideShow);
	
	/**
	 * 删除轮播图片信息
	 */
	void del(Integer slideShowId);

}
