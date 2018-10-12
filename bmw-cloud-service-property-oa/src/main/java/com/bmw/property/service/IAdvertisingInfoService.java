package com.bmw.property.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.Advertising;
import com.bmw.property.model.ParkingInfo;

public interface IAdvertisingInfoService {
	
	/**
     * 医院列表
     */
	JSONArray findList(String condition, String eId);
	
	/**
     * 获取医院详情
     */
	Advertising getdetail(Integer hospitalId);
	
	
	/**
     * 新增医院信息
     */
	void add(Advertising hospital);
	
	/**
	 * 修改医院信息
	 */
	void update(Advertising hospital);
	
	/**
	 * 删除医院信息
	 */
	void del(Integer hospitalId);

	/**
	  * 方法名称 : createGGWTree
	  * 作者 : wangliqing  
	  * 方法描述 : 创建广告位Tree
	  * 创建时间 : 2018年7月5日 下午2:45:37    
	  * 参数 : @param eId
	  * 参数 : @param nbId
	  * 返回类型 : List<ZTreeNode>    
	 */
	List<ZTreeNode> createGGWTree(Integer eId, Integer nbId);
	
	JSONArray findContractor(Integer condition);

	Object selectData(Integer nbId, Integer id, Integer relevanceId);
	
	
	
	
}
