package com.bmw.property.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.Department;

public interface IDepartmentService {

	/**
     * 区域列表
     */
	JSONArray findList(String condition);
	
	/**
     * 获取区域详情
     */
	Department getdetail(Integer departmentId);
	
	
	/**
     * 新增区域信息
	 * @return 
     */
	String add(Department department);
	
	/**
	 * 修改区域信息
	 * @return 
	 */
	String update(Department department);
	
	/**
	 * 删除区域信息
	 * @return 
	 */
	String del(Integer departmentId);
	/**
	 * 树形列表
	 */
	List<ZTreeNode> departmentTreeList();
	/**
	 * 新增式统计code查询
	 */
	String departmentCountCode(String code);
	
	String departmentCountIsCode(String code);
	/**
	 * 新增时根据code查询
	 */
	String departmentCodeSelect(String code);
	/**
	 * 删除层级信息
	 */
	Department departmentDeleteUpdate(String code);
	
}
