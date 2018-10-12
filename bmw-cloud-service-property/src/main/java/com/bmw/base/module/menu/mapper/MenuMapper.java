package com.bmw.base.module.menu.mapper;

import com.alibaba.fastjson.JSONObject;
import com.bmw.base.module.menu.domain.Menu;
import com.bmw.common.utils.oa.MenuNode;
import com.bmw.common.utils.oa.ZTreeNode;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;


/**
 * 菜单表Mapper类
 * 
 * @author jmy
 * @date 2018-04-19 01:23:14
 */
@Mapper
public interface MenuMapper {

    /**
     * 新增实体类到数据库
     * 
     */
    int add(Menu record);

    /**
     * 根据主键删除该记录
     * 
     */
    int delete(Long id);

    /**
     * 根据主键修改该记录
     * 
     */
    int update(Menu record);

    /**
     * 根据主键查询该记录
     * 
     */
    List<Menu> findById(Long id);
    
    /**
     * 根据主键查询
     * 
     * */
    Menu selectById(Long id);
    
    /**
     * 查询菜单
     * 
     */
    Menu detailMenuById(Menu menu);
    
    /**
     * 根据条件查询菜单
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    List<Long> getMenuIdsByRoleId(Long roleId);

    /**
     * 根据查询条件进行模糊查询
     * 
     */
    List<Menu> findLike(Menu condition);

    /**
     * 根据查询条件进行匹配查询
     * 
     */
    List<Menu> findList(Menu condition);
    
    
    /**
     * 根据条件查询进行匹配
     * 
     * */
    
    List<Menu> selectMenu(Menu condition);
    
    
    /**
     * 删除关联的relation 
     * */
    int deleteRelationByMenu(Long id);
    
    
    /**
     * 获取菜单列表树
     *
     * @return
     * @date 2017年2月19日 下午1:33:51
     */
    List<ZTreeNode> menuTreeList();
    
    
    /**
     * 获取菜单列表树
     *
     * @return
     * @date 2017年2月19日 下午1:33:51
     */
    List<ZTreeNode> menuTreeListByMenuIds(List<Long> menuIds);
    
    
    /**
     * 获取资源url通过角色id
     *
     * @param roleId
     * @return
     * @date 2017年2月19日 下午7:12:38
     */
    List<String> getResUrlsByRoleId(Integer roleId);
    
    
    /**
     * 根据角色获取菜单
     *
     * @param roleIds
     * @return
     * @date 2017年2月19日 下午10:35:40
     */
    List<MenuNode> getMenusByRoleIds(List<Integer> roleIds);
    
    /**
     * 获取查询条件
     * 
     * */
    Menu selectOne(Menu menu);

	List<MenuNode> menuTreeByRoleIds(List<Integer> roleIds);
    
    
    
    
    
}