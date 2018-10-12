package com.bmw.app.service;

import com.bmw.app.model.Version;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  版本服务类
 * </p>
 *
 * @author fjm123
 * @since 2018-05-17
 */
public interface IVersionService {
	
	/**
     * 版本列表
     */
	JSONArray findList(String condition);
	
	/**
     * 获取版本详情
     */
	Version getdetail(Integer versionId);
	
	
	/**
     * 新增版本信息
     */
	void add(Version version);
	
	/**
	 * 修改版本信息
	 */
	void update(Version version);
	
	/**
	 * 删除版本信息
	 */
	void del(Integer versionId);

}
