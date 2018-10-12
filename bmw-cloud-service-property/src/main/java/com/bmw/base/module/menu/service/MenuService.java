package com.bmw.base.module.menu.service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bmw.base.module.menu.domain.Menu;
import com.bmw.common.utils.oa.MenuNode;
import com.bmw.base.module.menu.mapper.MenuMapper;
import com.bmw.common.exception.RestPreconditionFailedException;
import com.bmw.common.log.ServiceOperationLog;
import com.bmw.common.utils.SpringContextUtils;
import com.bmw.common.utils.oa.ZTreeNode;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 菜单表Service类
 * 
 * @author jmy
 * @date 2018-04-19 01:23:14
 */
@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;



    /**
     * 获取当前对象实例
     * <p><b>
     * 为了记录操作日志</br>
     * 在调用内部 add()、delete()、update()方法时候请这样书写 "this.currentProxy().add()"
     * </b></p>
     * 
     */
    public MenuService currentProxy() {
        return SpringContextUtils.getBean(MenuService.class);
    }

    /**
     * 新增实体类到数据库
     * 
     */
    @Transactional
    @ServiceOperationLog(type="insert",iden="sys_menu",idenName="新增菜单",idBeanName="id")
    public int add(Menu record) {
        return menuMapper.add(record);
    }

    /**
     * 根据主键删除该记录
     * 
     */
    @Transactional
    @ServiceOperationLog(type="delete",iden="sys_menu",idenName="删除菜单",idBeanName="id")
    public int delete(Long id) {
        return menuMapper.delete(id);
    }

    /**
     * 根据主键修改该记录
     * 
     */
    @Transactional
    @ServiceOperationLog(type="update",iden="sys_menu",idenName="修改菜单",idBeanName="id")
    public int update(Menu record) {
        return menuMapper.update(record);
    }

    /**
     * 根据主键查询该记录
     * 
     */
    public List<Menu> findById(Long id) {
        return menuMapper.findById(id);
    }
    
    /**
     * 根据主键查看
     * 
     * */
    public Menu detailMenuById(Menu menu) {
    	return menuMapper.detailMenuById(menu);
    }
    

    /**
     * 根据查询条件进行模糊查询
     * 
     */
    public List<Menu> findLike(Menu condition) {
        return menuMapper.findLike(condition);
    }

    /**
     * 根据查询条件进行匹配查询
     * 
     */
    public List<Menu> findList(Menu condition) {
        return menuMapper.findList(condition);
    }
    
    /**
     * 根据查询条件查询列表
     * @author jmy
	 * 2018年04月18日 
     * */
    public List<Menu> selectMenu(Menu condition){
    	return menuMapper.selectMenu(condition);
    }
   
    /**
     * 根据查询条件查询列表
     * @author jmy
	 * 2018年04月18日 
     * */
    public List<Long> getMenuIdsByRoleId( Long roleId){
    	return menuMapper.getMenuIdsByRoleId(roleId);
    }
    
    /**
     * 增加菜单
     * @author jmy
	 * 2018年04月19日 
     * */
    public void addMenu(Menu menu){
    	menuMapper.add(menu);
    }
    
    /**
     * 删除菜单
     * @author jmy
	 * 2018年04月19日
     * */
    public void deleteMenu(JSONObject data){
    	menuMapper.delete(Long.valueOf(data.get("menuId").toString()));
    	
    	menuMapper.deleteRelationByMenu(Long.valueOf(data.get("menuId").toString()));
    }
    
    /**
     * 删除关联的relation
     * 
     * */
    
    public int deleteRelationByMenu(JSONObject data){
    	///删除菜单关联relation
    	menuMapper.deleteRelationByMenu(Long.valueOf(data.get("menuId").toString()));
    	return 1;
    }
    
    /**
     * 变更菜单
     * @author jmy
	 * 2018年04月19日
     * */
    public void editMenu(Menu menu) {
    	menuMapper.update(menu);
    }
    
    public Menu selectById(Long id) {
    	return menuMapper.selectById(id);
    }
    
    /**
     * 获取菜单列表树
     *
     * @return
     * @date 2017年2月19日 下午1:33:51
     */
    public List<ZTreeNode> menuTreeList(){
    	return menuMapper.menuTreeList();
    }
    /**
     * 获取菜单列表树
     *
     * @return
     * @date 2017年2月19日 下午1:33:51
     */
    public List<ZTreeNode> menuTreeListByMenuIds(List<Long> menuIds){
    	return menuMapper.menuTreeListByMenuIds(menuIds);
    }
    
    /**
     * 获取资源url通过角色id
     *
     * @param roleId
     * @return
     * @date 2017年2月19日 下午7:12:38
     */
    public List<String> getResUrlsByRoleId(Integer roleId){
    	return menuMapper.getResUrlsByRoleId(roleId);
    }
    
    /**
     * 查询
     * 
     * */
    
    public List<MenuNode> getMenusByRoleIds(List<Integer> roleIds){
    	return menuMapper.getMenusByRoleIds(roleIds);
    }
    
    /**
     * 判断信息验证
     * @author jmy
	 * 2018年04月19日 
     * */
    private void validate(JSONObject obj , String type) {
    	List<Menu> list = new ArrayList<>();
    	//TYPE判断是增加还是修改
    	if(type.equals("1")) {
    		Menu menu=new Menu();
    		list= menuMapper.selectMenu(menu);
    	}else if(type.equals("2")) {	
    	}else {
    		
    	}
    	for(int i=0 ; i<list.size();i++) {
			if(obj.get("name").equals(list.get(i).getName())) {
        		throw new RestPreconditionFailedException("菜单名称已存在");
        	}
        	if(obj.get("url").equals(list.get(i).getUrl())) {
        		throw new RestPreconditionFailedException("菜单url已存在");
        	}
        	if(obj.get("code").equals(list.get(i).getCode())) {
        		throw new RestPreconditionFailedException("菜单编号已存在");
        	}		
		}
    }
    
    
    /**
	 * JSON转Java实体
	 * @author jmy
	 * 2018年04月19日 
	 * */
	 public static<T> Object JSONToObj(String jsonStr,Class<T> obj) {
	        T t = null;
	        try {
	            ObjectMapper objectMapper = new ObjectMapper();
	            t = objectMapper.readValue(jsonStr,
	                    obj);
	        } catch (Exception e) {
	            e.printStackTrace(); 
	        }
	        return t;
	    }
	 
	 public Menu selectOne(Menu menu) {
		 Menu menudata = new Menu();
		 return menudata;
	 }

	public List<MenuNode> menuTreeByRoleIds(List<Integer> roleIds) {
		return menuMapper.menuTreeByRoleIds(roleIds);
	}
    
    
}