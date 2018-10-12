package com.stylefeng.guns.modular.system.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.model.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 部门服务
 *
 * @author lyl
 * @date 2018-04-20
 *  */
public interface IDeptService {

	/**
     * 获取所有部门列表
     */
    JSONArray list(@Param("condition") String condition);
    
    /**
     * 获取子部门
     * @param deptId
     * @return
     */
    JSONArray listById(Integer deptId);
    
	/**
     * 获取部门详情
     */
    Dept deptDetail(Integer deptId);
    
    /**
     * 新增部门
     * @param dept
     * @return
     */
    Boolean add(Dept dept);
    
    /**
     * 修改部门
     * @param dept
     * @return
     */
    Boolean update(Dept dept);
    
    /**
     * 删除部门
     */
    void deleteDept(Integer deptId);
    
    /**
     * 获取ztree的节点列表
     */
    JSONArray tree();

    
}
