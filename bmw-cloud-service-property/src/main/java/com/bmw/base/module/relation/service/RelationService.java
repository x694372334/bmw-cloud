package com.bmw.base.module.relation.service;

import com.bmw.common.log.ServiceOperationLog;
import com.bmw.common.utils.SpringContextUtils;
import com.bmw.base.module.relation.domain.Relation;
import com.bmw.base.module.relation.mapper.RelationMapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 角色和菜单关联表Service类
 * 
 * @author lyl
 * @date 2018-04-26 10:30:27
 */
@Service
public class RelationService {

    @Autowired
    private RelationMapper relationMapper;



    /**
     * 获取当前对象实例
     * <p><b>
     * 为了记录操作日志</br>
     * 在调用内部 add()、delete()、update()方法时候请这样书写 "this.currentProxy().add()"
     * </b></p>
     * 
     */
    public RelationService currentProxy() {
        return SpringContextUtils.getBean(RelationService.class);
    }

    /**
     * 新增实体类到数据库
     * 
     */
    @Transactional
    @ServiceOperationLog(type="update",iden="sys_role",idenName="修改角色",idBeanName="id")
    public int add(Relation record) {
        return relationMapper.add(record);
    }

    /**
     * 根据主键删除该记录
     * 
     */
    @Transactional
    @ServiceOperationLog(type="update",iden="sys_role",idenName="修改角色",idBeanName="id")
    public int delete(Integer id) {
        return relationMapper.delete(id);
    }

    /**
     * 根据主键修改该记录
     * 
     */
    @Transactional
    @ServiceOperationLog(type="update",iden="sys_role",idenName="修改角色",idBeanName="id")
    public int update(Relation record) {
        return relationMapper.update(record);
    }

    /**
     * 根据主键查询该记录
     * 
     */
    public Relation findById(Integer id) {
        return relationMapper.findById(id);
    }

    /**
     * 根据查询条件进行模糊查询
     * 
     */
    public List<Relation> findLike(Relation condition) {
        return relationMapper.findLike(condition);
    }

    /**
     * 根据查询条件进行匹配查询
     * 
     */
    public List<Relation> findList(Relation condition) {
        return relationMapper.findList(condition);
    }
    
    @Transactional
    @ServiceOperationLog(type="delete",iden="sys_role",idenName="根据菜单id删除角色",idBeanName="id")
    public void delByMenuId(@Param("meanId")Integer meanId) {
    	relationMapper.delByMenuId(meanId);
    }
    
    @Transactional
    @ServiceOperationLog(type="delete",iden="sys_role",idenName="根据角色id删除角色",idBeanName="id")
    public void delByRoleId(@Param("roleId")Integer roleId) {
    	relationMapper.delByRoleId(roleId);
    }

    @Transactional
	public void addApp(String roleId, String menuIds) {
		String[] menuids = menuIds.split(",");
		relationMapper.deleteAppByRoleId(roleId);
		if (!"".equals(menuIds)) {
			for (String menuid : menuids) {
				relationMapper.addApp(roleId, menuid);
			}
		}
	}

	public List<Integer> findListByRoleId(Integer roleId) {
		List<Integer> result= relationMapper.findListByRoleId(roleId);
		return result;
	}
}