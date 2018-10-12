package com.bmw.medical.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.medical.model.Department;

public interface IDepartmentService {
	/**
	 * 科室列表
	 */
	JSONArray findList(Department department);

	/**
	 * 获取科室详情
	 */
	Department selectById(Integer departmentId);

	/**
	 * 新增科室信息
	 */
	void add(Department department);

	/**
	 * 修改科室信息
	 */
	void update(Department department);

	/**
	 * 删除科室信息
	 */
	void del(Integer departmentId);

	/**
	 * 树形列表
	 */
	List<ZTreeNode> departmentTreeList(String hospitalCode);
	
	List<ZTreeNode> getTreeList();

	/**
	 * 新增式统计code查询
	 */
	String departmentCountCode(String code);

	/**
	 * 删除层级信息
	 */
	Department findByCode(String code);

	void updateChildren(String oldPcode, String newPcode);
}