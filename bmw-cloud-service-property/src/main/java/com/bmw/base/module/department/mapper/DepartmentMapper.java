package com.bmw.base.module.department.mapper;

import com.bmw.base.module.department.domain.Department;
import com.bmw.common.utils.oa.ZTreeNode;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;


/**
 * 部门表Mapper类
 * 
 * @author zhangt
 * @date 2018-06-29 01:11:38
 */
@Mapper
public interface DepartmentMapper {

    /**
     * 新增实体类到数据库
     * 
     */
    int add(Department record);

    /**
     * 根据主键删除该记录
     * 
     */
    int delete(Integer id);

    /**
     * 根据主键修改该记录
     * 
     */
    int update(Department record);

    /**
     * 根据主键查询该记录
     * 
     */
    Department findById(Integer id);

    /**
     * 根据查询条件进行模糊查询
     * 
     */
    List<Department> findLike(Department condition);

    /**
     * 根据查询条件进行匹配查询
     * 
     */
    List<Department> findList(Department condition);
    
    List<ZTreeNode> departmentTreeList();
    
    String departmentCountCode(String code);
    
    String departmentCountIsCode(String code);
    
    String departmentCodeSelect(String code);
    
    Department departmentDeleteUpdate(String code);

    List<ZTreeNode> getDepartmentTreeListByParentEId(String parenteid);
}