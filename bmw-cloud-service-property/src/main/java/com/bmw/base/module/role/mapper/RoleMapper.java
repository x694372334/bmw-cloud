package com.bmw.base.module.role.mapper;

import com.bmw.base.module.role.domain.Role;
import com.bmw.common.utils.oa.ZTreeNode;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;


/**
 * 角色表Mapper类
 * 
 * @author lyl
 * @date 2018-04-20 01:16:16
 */
@Mapper
public interface RoleMapper {

    /**
     * 新增实体类到数据库
     * 
     */
    int add(Role record);

    /**
     * 根据主键删除该记录
     * 
     */
    int delete(Integer id);

    /**
     * 根据主键修改该记录
     * 
     */
    int update(Role record);

    /**
     * 根据主键查询该记录
     * 
     */
    Role findById(Integer id);

    /**
     * 根据查询条件进行模糊查询
     * 
     */
    List<Role> findLike(Role condition);

    /**
     * 根据查询条件进行匹配查询
     * 
     */
    List<Role> findList(Role condition);
    
    
    /**
     * 删除某个角色的所有权限
     *
     * @param roleId 角色id
     * @return
     * @date 2018年4月23日
     */
    void deleteRolesById(Integer roleId);
    
    
    /**
     * 获取角色树集合
     * @date 2018年4月23日
     */
    List<ZTreeNode> roleTreeList();
    
    
    /**
     * 根据角色id获取角色树集合
     * @date 2018年4月23日
     */
    List<ZTreeNode> roleTreeListByRoleId(String[] roleId);
    
    /**
     * 根据企业ID查询角色
     * @date 2018年4月23日
     */
    List<ZTreeNode> roleTreeListByParentEId(String parenteid);
}