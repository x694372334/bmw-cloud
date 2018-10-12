package com.bmw.property.service;

import com.alibaba.fastjson.JSONArray;
import com.bmw.property.model.WDocuments;

/**
 * <p>
 * 工作文档 服务类
 * </p>
 *
 * @author zhangt123
 * @since 2018-07-19
 */
public interface IWDocumentsService  {
	/**
     * 列表
     */
	JSONArray findList(WDocuments condition);
	
	/**
     * 详情
     */
	WDocuments getdetail(Integer sDocumentsId);
	
	
	/**
     * 新增信息
     */
	void add(WDocuments sDocuments);
	
	/**
	 * 修改信息
	 */
	void update(WDocuments sDocuments);
	
	/**
	 * 删除信息
	 */
	void del(Integer sDocumentsId);

}
