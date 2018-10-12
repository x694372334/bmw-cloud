package com.bmw.property.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.HelpDocumentType;

public interface IHelpDocumentTypeService {

	/**
     * 区域列表
     */
	JSONArray findList(String condition);
	
	/**
     * 获取区域详情
     */
	HelpDocumentType getdetail(Integer helpDocumentTypeId);
	
	
	/**
     * 新增区域信息
     */
	void add(HelpDocumentType helpDocumentType);
	
	/**
	 * 修改区域信息
	 */
	void update(HelpDocumentType helpDocumentType);
	
	/**
	 * 删除区域信息
	 */
	void del(Integer helpDocumentTypeId);
	/**
	 * 树形列表
	 */
	List<ZTreeNode> helpDocumentTypeTreeList();
	/**
	 * 新增式统计code查询
	 */
	String helpDocumentTypeCountCode(String code);
	
	String helpDocumentTypeCountIsCode(String code);
	/**
	 * 新增时根据code查询
	 */
	String helpDocumentTypeCodeSelect(String code);
	/**
	 * 删除层级信息
	 */
	HelpDocumentType helpDocumentTypeDeleteUpdate(String code);
	
}
