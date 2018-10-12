package com.stylefeng.guns.modular.system.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**  
* <p>Title: IRoleService</p>  
* <p>Description: 角色服务实现</p>  
* @author lyl  
* @date 2018年4月26日  
*/  
public interface IRoleService {

    /**
     * 设置某个角色的权限
     *
     * @param roleId 角色id
     * @param ids    权限的id
     * @date 2017年2月13日 下午8:26:53
     */
    void setAuthority(Integer roleId, String ids);



    

    
    
    
    /**
     * 获取角色列表
     * @param condition
     * @return
     */
    public JSONArray findList(String condition);
    
    /**
     * 角色信息
     * @author lyl
	 * 2018年04月20日
     */
    public Role getRoleDetail(Integer roleId);
    
    /**
     * 角色添加
     * @param role
     * @return 
     */
    public String add(Role role);
    
    
    /**
     * 角色修改
     * @param role
     * @return 
     */
    public String edit(Role role);
    
    
    /**
     * 角色删除
     * @param role
     * @return 
     */
    public String del(Integer roleId);
    
    /**
     * 获取角色列表树
     *
     * @return
     * @date 2018年4月23日 
     */
    public JSONArray roleTreeList();
    
    /**
     * 获取角色列表树
     *
     * @return
     * @date 2017年4月23日
     */
    public JSONArray roleTreeListByRoleId(String[] roleId);

    /**
     * 设置某个角色App的权限
     *
     * @param roleId 角色id
     * @param ids    权限的id
     */
	void setAppAuthority(Integer roleId, String ids);

	JSONArray roleTreeListByParentEId(Integer parenteid);

	JSONArray roleTreeListByParentEIdAndRoleId(Integer parenteid, String[] roleId);

	JSONArray findRole(Role condition);
}
