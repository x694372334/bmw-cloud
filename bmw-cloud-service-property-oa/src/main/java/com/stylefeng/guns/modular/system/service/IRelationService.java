package com.stylefeng.guns.modular.system.service;

import com.stylefeng.guns.modular.system.model.Relation;

/**  
* <p>Title: RelationServiceImpl</p>  
* <p>Description: 角色关联实现类</p>  
* @author lyl  
* @date 2018年4月26日  
*/  
public interface IRelationService{
	
	
	/**  
	* <p>Description: 按照菜单id删除角色关联</p>  
	* @author lyl  
	* @date 2018年4月26日  
	*/  
	void deleteRelationByMenu(Long menuId);
	
	
	/**  
	* <p>Description: 按照角色id删除角色关联 </p>  
	* @author lyl  
	* @date 2018年4月26日  
	*/  
	void deleteRelationByRole(Integer roleId);
	
	
	/**  
	* <p>Description: 新增角色关联</p>  
	* @author lyl  
	* @date 2018年4月26日  
	*/  
	void add(Relation relation);
}
