package com.bmw.base.module.role.service;

import com.bmw.common.log.ServiceOperationLog;
import com.bmw.common.utils.SpringContextUtils;
import com.bmw.common.utils.oa.ZTreeNode;
import com.bmw.base.module.role.domain.Role;
import com.bmw.base.module.role.mapper.RoleMapper;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 角色表Service类
 * 
 * @author lyl
 * @date 2018-04-20
 */
@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;



    /**
     * 获取当前对象实例
     * <p><b>
     * 为了记录操作日志</br>
     * 在调用内部 add()、delete()、update()方法时候请这样书写 "this.currentProxy().add()"
     * </b></p>
     * 
     */
    public RoleService currentProxy() {
        return SpringContextUtils.getBean(RoleService.class);
    }

    /**
     * 新增实体类到数据库
     * 
     */
    @Transactional
    @ServiceOperationLog(type="insert",iden="sys_role",idenName="添加角色",idBeanName="id")
    public int add(Role record) {
        return roleMapper.add(record);
    }

    /**
     * 根据主键删除该记录
     * 
     */
    @Transactional
    @ServiceOperationLog(type="delete",iden="sys_role",idenName="删除角色",idBeanName="id")
    public int delete(Integer id) {
        return roleMapper.delete(id);
    }

    /**
     * 根据主键修改该记录
     * 
     */
    @Transactional
    @ServiceOperationLog(type="update",iden="sys_role",idenName="修改角色",idBeanName="id")
    public int update(Role record) {
        return roleMapper.update(record);
    }

    /**
     * 根据主键查询该记录
     * 
     */
    public Role findById(Integer id) {
        return roleMapper.findById(id);
    }

    /**
     * 根据查询条件进行模糊查询
     * 
     */
    public List<Role> findLike(Role condition) {
        return roleMapper.findLike(condition);
    }

    /**
     * 根据查询条件进行匹配查询
     * 
     */
    public List<Role> findList(Role condition) {
        return roleMapper.findList(condition);
    }
    
    
    /**
     * 删除某个角色的所有权限
     *
     * @param roleId 角色id
     * @date 2018年4月23日
     */
    @Transactional
    @ServiceOperationLog(type="update",iden="sys_role",idenName="删除某个角色的所有权限",idBeanName="id")
    public void deleteRolesById(Integer roleId) {
    	roleMapper.deleteRolesById(roleId);
    }
    
    /**
     * 获取角色树集合
     * @date 2018年4月23日
     */
    public List<ZTreeNode> roleTreeList(){
    	return roleMapper.roleTreeList();
    }
    
    
    
    /**
     * 根据角色id获取角色树集合
     * @date 2018年4月23日
     */
    public List<ZTreeNode> roleTreeListByRoleId(String[] roleId){
    	return roleMapper.roleTreeListByRoleId(roleId);
    }
    
    /**
     * 根据角色id获取角色树集合
     * @date 2018年4月23日
     */
    public List<ZTreeNode> roleTreeListByParentEId(String parenteid){
    	return roleMapper.roleTreeListByParentEId(parenteid);
    }

	public String checkRoleIds(String roleIds) {
		String[] roleArray = roleIds.split(",");
		List<Integer> roleParentEIdList = new ArrayList<Integer>();
		for(String roleId : roleArray) {
			Integer parentEId =  roleMapper.findById(Integer.parseInt(roleId)).getParentEId();
			if(!roleParentEIdList.contains(parentEId)&&0!=roleParentEIdList.size()) {
				return "0";
			}else {
				roleParentEIdList.add(parentEId);
			}
		}
		return "1";
	}
    
}