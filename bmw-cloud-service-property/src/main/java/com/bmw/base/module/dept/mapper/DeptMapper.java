package com.bmw.base.module.dept.mapper;

import com.bmw.base.module.dept.domain.Dept;
import com.bmw.common.utils.oa.ZTreeNode;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;


/**
 * 部门表Mapper类
 * 
 * @author lyl
 * @date 2018-04-19 01:15:52
 */
@Mapper
public interface DeptMapper {

    /**
     * 新增实体类到数据库
     * 
     */
    int add(Dept record);

    /**
     * 根据主键删除该记录
     * 
     */
    int delete(Integer id);

    /**
     * 根据主键修改该记录
     * 
     */
    int update(Dept record);

    /**
     * 根据主键查询该记录
     * 
     */
    Dept findById(Integer id);

    /**
     * 根据查询条件进行模糊查询
     * 
     */
    List<Dept> findLike(Dept condition);

    /**
     * 根据查询条件进行匹配查询
     * 
     */
    List<Dept> findList(Dept condition);
    
    
    /**
     * 获取ztree的节点列表
     */
    List<ZTreeNode> tree();
}