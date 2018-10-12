package com.bmw.medical.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.medical.model.Area;

public interface IAreaService {

	/**
     * 区域列表
     */
	JSONArray findList(String condition);
	
	/**
     * 获取区域详情
     */
	Area getdetail(Integer areaId);
	
	
	/**
     * 新增区域信息
     */
	void add(Area area);
	
	/**
	 * 修改区域信息
	 */
	void update(Area area);
	
	/**
	 * 删除区域信息
	 */
	void del(Integer areaId);
	/**
	 * 树形列表
	 */
	List<ZTreeNode> areaTreeList();
	/**
	 * 新增式统计code查询
	 */
	String areaCountCode(String code);
	
	String areaCountIsCode(String code);
	/**
	 * 新增时根据code查询
	 */
	String areaCodeSelect(String code);
	/**
	 * 删除层级信息
	 */
	Area areaDeleteUpdate(String code);
	
}
