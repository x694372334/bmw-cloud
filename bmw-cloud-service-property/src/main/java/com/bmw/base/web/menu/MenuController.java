package com.bmw.base.web.menu;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.Base64Utils;

import com.bmw.base.module.menu.domain.Menu;
import com.bmw.common.utils.oa.MenuNode;
import com.bmw.base.module.menu.service.MenuService;
import com.bmw.common.exception.RestPreconditionFailedException;
import com.bmw.common.model.Result;
import com.bmw.common.utils.ResultUtils;
import com.bmw.common.utils.oa.ZTreeNode;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.primitives.Ints;
@RestController
@RequestMapping("base/menu")
public class MenuController {
	
	static Logger log=LoggerFactory.getLogger(MenuController.class);
	
    @Autowired
    private MenuService menuService;
    
    /**
     * 根据条件查询菜单列表
     * @author jmy
     * 2018年04月18日
     */
    @RequestMapping(value="getMenu/{jsonParams}",method=RequestMethod.GET)
    public Result getSelectMenu(@PathVariable("jsonParams") String jsonParams){
        Menu menu = new Menu();
        try {
            String params = new String (Base64Utils.decode(jsonParams.getBytes("UTF-8")));
            JSONObject object=JSONObject.parseObject(params);
            if(object.get("name")!=null) {
                menu.setName(object.get("name").toString());
            }
            if(object.get("level")!=null) {
                menu.setLevels(Integer.parseInt(object.get("level").toString()));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<Menu> categorys = menuService.selectMenu(menu);
        return ResultUtils.getMethodData(categorys);
    }
    
    /**
     * 根据主键Id查询菜单列表
     * @author jmy
     * 2018年04月18日
     */
    @RequestMapping(value="selectById/{jsonParams}",method=RequestMethod.GET)
    public Result getSelectById(@PathVariable("jsonParams") String jsonParams){
        Menu menu = new Menu();
        try {
            String params = new String (Base64Utils.decode(jsonParams.getBytes("UTF-8")));
            JSONObject object=JSONObject.parseObject(params);
            menu.setId(Long.parseLong(object.get("id").toString()));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<Menu> categorys = menuService.findById(menu.getId());
        return ResultUtils.getMethodData(categorys);
    }
    
    
    
    /**
     * 增加菜单
     * @author jmy
     * 2018年04月18日
     */
    @RequestMapping(value="addMenu",method=RequestMethod.POST)
    public Object getAddMenu(@RequestBody Menu menu){
        menuService.addMenu(menu);
        return ResultUtils.postMethodData("添加菜单成功");
    }
    
    
    /**
     * 删除菜单
     * @author jmy
     * 2018年04月18日
     */
    @RequestMapping(value="delMenu/{jsonParams}",method=RequestMethod.POST)
    public Result getDeleteMenu(@PathVariable("jsonParams") String jsonParams){
        try {
            String params = new String (Base64Utils.decode(jsonParams.getBytes("UTF-8")));
            JSONObject object=JSONObject.parseObject(params);
            menuService.deleteMenu(object);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResultUtils.postMethodData("删除菜单成功");
    }
    
    
    /**
     * 删除菜单关联的relation
     * @author jmy
     * 2018年04月18日
     */
    @RequestMapping(value="delRelationMenu/{jsonParams}",method=RequestMethod.POST)
    public Result getDeleteRelationMenu(@PathVariable("jsonParams") String jsonParams){
        try {
            String params = new String (Base64Utils.decode(jsonParams.getBytes("UTF-8")));
            JSONObject object=JSONObject.parseObject(params);
            menuService.deleteRelationByMenu(object);
            
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResultUtils.postMethodData("删除菜单成功");
    }
    
    
    /**
     * 修改菜单
     * @author jmy
     * 2018年04月18日
     */
    @RequestMapping(value="editMenu",method=RequestMethod.POST)
    public Result getUpdateMenu(@RequestBody Menu menu){
        menuService.editMenu(menu);
        return ResultUtils.postMethodData("变更菜单成功");
    }
    /**
     * 查看菜单
     * */
    @RequestMapping(value="detailMenu/{menuId}",method=RequestMethod.GET)
    public Result detailMenu(@PathVariable("menuId") Integer menuId) {
        if(menuId==null) {
            throw new RestPreconditionFailedException("请输入正确的id");
        }
        Menu menu = new Menu();
        menu.setId(Long.valueOf(menuId));
        Menu categorys = menuService.detailMenuById(menu);
        return ResultUtils.getMethodData(categorys);
    }
    /**
     * 根据id查询
     * 
     * */
    @RequestMapping(value="selectMenuById/{menuId}",method=RequestMethod.GET)
    public Result selectMenuById(@PathVariable("menuId") Integer menuId) {
        if(menuId==null) {
            throw new RestPreconditionFailedException("请输入正确的id");
        }
        Menu categorys = menuService.selectById(Long.valueOf(menuId));
        return ResultUtils.getMethodData(categorys);
    }
    
    /**
     * 查询菜单列表树
     * 
     * */
    
    @RequestMapping(value="treeListMenu/{jsonParams}",method=RequestMethod.GET)
    public Result getTreeListMenu() {
    	
        return ResultUtils.getMethodData(menuService.menuTreeList());
    }
    

    
    /**
     * 查询菜单树形图
     * 
     * */
    
    @RequestMapping(value="menuTreeList/{jsonParams}",method=RequestMethod.GET)
    public Result menuTreeListByMenuIds(@PathVariable("jsonParams") String jsonParams) {
        List<Long> list = new ArrayList();
        try {
            String params = new String (Base64Utils.decode(jsonParams.getBytes("UTF-8")));
            JSONObject object=JSONObject.parseObject(params);
            list.add(Long.valueOf(object.get("id").toString()));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        List<ZTreeNode> categorys = menuService.menuTreeListByMenuIds(list);
        return ResultUtils.getMethodData(categorys);
    }
    
    /**
     * 根据url查询
     * 
     * */
    
    @RequestMapping(value="getUrlRoleId/{jsonParams}",method=RequestMethod.POST)
    public Result getResUrlsByRoleId(@PathVariable("jsonParams") String jsonParams) {
        List<String> categorys = null;
        try {
            String params = new String (Base64Utils.decode(jsonParams.getBytes("UTF-8")));
            JSONObject object=JSONObject.parseObject(params);
             categorys = menuService.getResUrlsByRoleId(Integer.parseInt(object.get("roleId").toString()));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResultUtils.getMethodData(categorys);
    }
    
    /**
     * 根据id获取菜单
     * 
     * */
    
    @RequestMapping(value="selectByRoleId/{menuId}",method=RequestMethod.POST)
    public Result getselectByIdMenu(@PathVariable("menuId") String menuId){
        JSONObject object=null;
        try {
                String params = new String (Base64Utils.decode(menuId.getBytes("UTF-8")));
                object=JSONObject.parseObject(params);
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        List<Long> categorys = menuService.getMenuIdsByRoleId(Long.parseLong(object.get("rodeId").toString()));
        return ResultUtils.getMethodData(categorys);
    }
    
    /**
     * 根据roleid获取菜单
     * 
     * */
    
    @RequestMapping(value="findByRoleId/{roleId}",method=RequestMethod.POST)
    public Result findByRoleId(@PathVariable("roleId") String roleId){
        List<Long> categorys = menuService.getMenuIdsByRoleId(Long.parseLong(roleId));
        return ResultUtils.getMethodData(categorys);
    }
    
    /**
     * 查询
     * 
     * */
    @RequestMapping(value="menusByRoleIds/{menuId}",method=RequestMethod.POST)
    public Result getMenusByRoleIds(@PathVariable("menuId") String menuId){
        log.info(menuId);
        List<MenuNode> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        String[] array =  menuId.split(",");
        for(String id : array) {
        	list1.add(Integer.parseInt(id));
        }
        list = menuService.getMenusByRoleIds(list1);
        return ResultUtils.postMethodData(list);
    }
    
    /**
     * 查询
     * 
     * */
    @RequestMapping(value="menuTreeByRoleIds/{menuId}",method=RequestMethod.POST)
    public Result menuTreeByRoleIds(@PathVariable("menuId") String menuId){
        log.info(menuId);
        List<MenuNode> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        String[] array =  menuId.split(",");
        for(String id : array) {
        	list1.add(Integer.parseInt(id));
        }
        list = menuService.menuTreeByRoleIds(list1);
        return ResultUtils.postMethodData(list);
    }
    
    /**
     * 根据条件查询菜单值
     * 
     * */
    @RequestMapping(value="selectOne/{jsonParams}",method=RequestMethod.POST)
    public Result getSelectOne(@PathVariable("jsonParams") String jsonParams){
        Menu menu = new Menu();
        try {
            String rlt = new String (Base64Utils.decode(jsonParams.getBytes("UTF-8")));
            Menu menu1=JSON.parseObject(rlt).toJavaObject(Menu.class);
            menu.setCode(menu1.getCode());
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Menu categorys = menuService.selectOne(menu);
        return ResultUtils.getMethodData(categorys);
    }
}
