package com.bmw.property.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.Position;

public interface IPositionService {

	/**
     * 区域列表
     */
	JSONArray findList(String condition);
	
	/**
     * 获取区域详情
     */
	Position getdetail(Integer positionId);
	
	
	/**
     * 新增区域信息
     */
	void add(Position position);
	
	/**
	 * 修改区域信息
	 */
	void update(Position position);
	
	/**
	 * 删除区域信息
	 */
	void del(Integer positionId);
	/**
	 * 树形列表
	 */
	List<ZTreeNode> positionTreeList();
	/**
	 * 新增式统计code查询
	 */
	String positionCountCode(String code);
	
	String positionCountIsCode(String code);
	/**
	 * 新增时根据code查询
	 */
	String positionCodeSelect(String code);
	/**
	 * 删除层级信息
	 */
	Position positionDeleteUpdate(String code);
	
}
