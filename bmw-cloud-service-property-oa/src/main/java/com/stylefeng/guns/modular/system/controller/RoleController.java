package com.stylefeng.guns.modular.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.core.base.tips.Tip;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.core.cache.CacheKit;
import com.stylefeng.guns.core.common.annotion.BussinessLog;
import com.stylefeng.guns.core.common.annotion.Permission;
import com.stylefeng.guns.core.common.constant.Const;
import com.stylefeng.guns.core.common.constant.cache.Cache;
import com.stylefeng.guns.core.common.constant.dictmap.RoleDict;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.core.util.Convert;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.system.model.Role;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IRoleService;
import com.stylefeng.guns.modular.system.service.IUserService;
import com.stylefeng.guns.modular.system.warpper.RoleWarpper;
import com.bmw.property.model.Department;
import com.bmw.property.model.TAppPropertyMenu;
import com.bmw.property.service.IDepartmentService;
import com.bmw.property.service.ITAppPropertyMenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 角色控制器
 *
 * @author lyl
 * @Date 2018年4月23日
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    private static String PREFIX = "/system/role";

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;
    
//    @Autowired
//    private ITAppPropertyMenuService appPropertyMenuService;
//
//    @Autowired
//    private IDepartmentService departmentService ;

    /**
     * 跳转到角色列表页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/role.html";
    }

    /**
     * 跳转到添加角色
     */
    @RequestMapping(value = "/role_add")
    public String roleAdd() {
    	ShiroExt shiro = new ShiroExt();
        if(shiro.getUser().isAdmin) {
        	return PREFIX + "/role_add_admin.html";
        }
        return PREFIX + "/role_add.html";
    }

    /**
     * 跳转到修改角色
     */
    @Permission
    @RequestMapping(value = "/role_edit/{roleId}")
    public String roleEdit(@PathVariable Integer roleId, Model model) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        Role role = roleService.getRoleDetail(roleId);
        model.addAttribute(role);
        model.addAttribute("pName", ConstantFactory.me().getSingleRoleName(role.getPid()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(role.getDeptid()));
        model.addAttribute("parentEName", ConstantFactory.me().getEnterpriseInfoName(role.getParentEId()));
        LogObjectHolder.me().set(role);
        ShiroExt shiro = new ShiroExt();
        if(shiro.getUser().isAdmin) {
        	return PREFIX + "/role_edit_admin.html";
        }
        return PREFIX + "/role_edit.html";
    }

    /**
     * 跳转到角色分配
     */
    @Permission
    @RequestMapping(value = "/role_assign/{roleId}")
    public String roleAssign(@PathVariable("roleId") Integer roleId, Model model) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        model.addAttribute("roleId", roleId);
        model.addAttribute("roleName", ConstantFactory.me().getSingleRoleName(roleId));
        return PREFIX + "/role_assign.html";
    }
    
   /* *//**
     * 跳转到app权限分配
     *//*
    @RequestMapping(value = "/role_appassign/{roleId}")
    public String roleAppAssign(@PathVariable("roleId") Integer roleId, Model model) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        model.addAttribute("roleId", roleId);
        model.addAttribute("roleName", ConstantFactory.me().getSingleRoleName(roleId));
        return PREFIX + "/role_appassign.html";
    }*/

    /**
     * 获取角色列表
     */
    @Permission
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String roleName) {
        JSONArray roles = roleService.findList(roleName);
        return super.warpObject(new RoleWarpper(roles));
    }

    /**
     * 角色新增
     */
    @RequestMapping(value = "/add")
   /* @BussinessLog(value = "添加角色", key = "name", dict = RoleDict.class)
    @Permission(Const.ADMIN_NAME)*/
    @ResponseBody
    public Tip add(@Valid Role role, BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        String flag = roleService.add(role);
        if("2".equals(flag)) {
        	return new ErrorTip(400,"本企业已存在管理员");
        }else if("0".equals(flag)){
        	return new ErrorTip(400,"上级角色与所选部门非同一部门");
        }else {
        	return SUCCESS_TIP;
        }
    }

    /**
     * 角色修改
     */
    @RequestMapping(value = "/edit")
    @BussinessLog(value = "修改角色", key = "name", dict = RoleDict.class)
    //@Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Tip edit(@Valid Role role, BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        String flag = this.roleService.edit(role);

        //删除缓存
        CacheKit.removeAll(Cache.CONSTANT);
        if("1".equals(flag)) {
        	return SUCCESS_TIP;
        }else {
        	return new ErrorTip(400,"上级角色与所选部门非同一部门");
        }
    }

    /**
     * 删除角色
     */
    @RequestMapping(value = "/remove")
/*    @BussinessLog(value = "删除角色", key = "roleId", dict = RoleDict.class)
    @Permission(Const.ADMIN_NAME)*/
    @ResponseBody
    public Object remove(@RequestParam Integer roleId) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }

        //不能删除超级管理员角色
        if (roleId.equals(Const.ADMIN_ROLE_ID)) {
            throw new GunsException(BizExceptionEnum.CANT_DELETE_ADMIN);
        }

        //缓存被删除的角色名称
        LogObjectHolder.me().set(ConstantFactory.me().getSingleRoleName(roleId));

        String result = this.roleService.del(roleId);
        //删除缓存
        CacheKit.removeAll(Cache.CONSTANT);
        if("success".equals(result)) {
            return SUCCESS_TIP;
    	}else {
    		 return new ErrorTip(400, result);
    	}

    }

    /**
     * 查看角色
     */
    @RequestMapping(value = "/view/{roleId}")
    @ResponseBody
    public Tip view(@PathVariable Integer roleId) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        roleService.getRoleDetail(roleId);
        return SUCCESS_TIP;
    }

    /**
     * 配置权限
     */
    @RequestMapping("/setAuthority")
   /* @BussinessLog(value = "配置权限", key = "roleId,ids", dict = RoleDict.class)
    @Permission(Const.ADMIN_NAME)*/
    @ResponseBody
    public Tip setAuthority(@RequestParam("roleId") Integer roleId, @RequestParam("ids") String ids) {
        if (ToolUtil.isOneEmpty(roleId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        this.roleService.setAuthority(roleId, ids);
        return SUCCESS_TIP;
    }

    /**
     * 获取角色列表
     */
    @RequestMapping(value = "/roleTreeList")
    @ResponseBody
    public JSONArray roleTreeList() {
    	int isadmin = 0;
		ShiroExt shiro = new ShiroExt();
		Integer parenteid = shiro.getUser().getParentEId();
		if (shiro.getUser().getIsAdmin()) {
			isadmin = 1;
		}
		JSONArray roleTreeList = new JSONArray();
		if(1==isadmin) {
        roleTreeList = this.roleService.roleTreeList();
		}else {
        roleTreeList = roleService.roleTreeListByParentEId(parenteid);
		}
		
			roleTreeList.add(ZTreeNode.createParent());
		return roleTreeList;
    }

    /**
     * 获取角色列表
     */
    @RequestMapping(value = "/roleTreeListByUserId/{userId}")
    @ResponseBody
	public JSONArray roleTreeListByUserId(@PathVariable Integer userId) {
    	JSONArray roleTreeList = new JSONArray();
		int isadmin = 0;
		ShiroExt shiro = new ShiroExt();
		Integer parenteid = shiro.getUser().getParentEId();
		if (shiro.getUser().getIsAdmin()) {
			isadmin = 1;
		}
		User theUser = this.userService.detailUser(userId);
		String roleid = theUser.getRoleid();
		if (1 == isadmin) {
			if (ToolUtil.isEmpty(roleid)) {
				roleTreeList = roleService.roleTreeList();
			} else {
				String[] strArray = Convert.toStrArray(",", roleid);
				roleTreeList = roleService.roleTreeListByRoleId(strArray);
			}
		}else {
			if (ToolUtil.isEmpty(roleid)) {
				roleTreeList = roleService.roleTreeListByParentEId(parenteid);
			} else {
				String[] strArray = Convert.toStrArray(",", roleid);
				roleTreeList = roleService.roleTreeListByParentEIdAndRoleId(parenteid,strArray);
			}
		}
		
			roleTreeList.add(ZTreeNode.createParent());
		
		return roleTreeList;
	}

    /**
     * 跳转到分配app权限页面
     */
    @RequestMapping(value = "/role_appassign/{id}")
    public String appAssign(@PathVariable("id") Integer id , Model model) {
    	TAppPropertyMenu appPropertyMenu = new TAppPropertyMenu();
//    	JSONArray appMenuList = appPropertyMenuService.findList(appPropertyMenu);
//    	JSONArray mennuIds = appPropertyMenuService.findListByRoleId(id);
//        model.addAttribute("appMenuList", appMenuList);
//        model.addAttribute("mennuIds", mennuIds);
        model.addAttribute("roleId", id);
        LogObjectHolder.me().set(id);
//        LogObjectHolder.me().set(appMenuList);
        return PREFIX + "/role_appassign.html";
    }
    
    /**
     * 配置APP权限
     */
    @RequestMapping("/setAppAuthority")
    @ResponseBody
    public Tip setAppAuthority(@RequestParam("roleId") Integer roleId, @RequestParam("menuIds") String ids) {
        if (ToolUtil.isOneEmpty(roleId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        this.roleService.setAppAuthority(roleId, ids);
        return SUCCESS_TIP;
    }
    
    /**
     * 根据父级id查询所属部门
     */
    @RequestMapping(value = "/findDept/{pid}")
    @ResponseBody
    public String findDept(@PathVariable Integer pid, Model model) {
    	Role role= roleService.getRoleDetail(pid);
//    	Department dept = departmentService.getdetail(role.getDeptid());
//    	String result = JSONObject.toJSONString(dept);
//    	return result;
        return "1";
    }

}
