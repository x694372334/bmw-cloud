package com.bmw.base.web.role;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.fabric.xmlrpc.base.Array;
import com.bmw.base.module.department.domain.Department;
import com.bmw.base.module.department.service.DepartmentService;
import com.bmw.base.module.dict.domain.Dict;
import com.bmw.base.module.dict.service.DictService;
import com.bmw.base.module.role.domain.Role;
import com.bmw.base.module.role.service.RoleService;
import com.bmw.base.module.user.domain.UserRoleVO;
import com.bmw.base.module.user.service.UserService;
import com.bmw.common.exception.RestPreconditionFailedException;
import com.bmw.common.model.Result;
import com.bmw.common.utils.ResultUtils;
import com.bmw.common.utils.oa.ZTreeNode;
@RestController
@RequestMapping("base/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private  DepartmentService departmentService;
	
	@Autowired
	private UserService userService;
	/**
	 * 获取所有角色
	 * @author lyl
	 * 2018年04月20日
	 */
	@RequestMapping(value="getRole/{RoleName}/{parenteid}",method=RequestMethod.GET)
	public Result getRole(@PathVariable("RoleName") String RoleName,@PathVariable("parenteid") String parenteid){
		Role role = new Role();
		List<Role> adminRole = new ArrayList<Role>();
		if(!"null".equals(RoleName)) {
			role.setName(RoleName);
		}
		if(!"0".equals(parenteid)) {
			role.setParentEId(Integer.parseInt(parenteid));
			role.setVersion(2);
		}else {
			Role param = new Role();
			param.setId(1);
			adminRole = roleService.findLike(param);
			role.setVersion(1);
		}
		List<Role> categorys = roleService.findLike(role);
		adminRole.addAll(categorys);
		return ResultUtils.getMethodData(adminRole);
	}
	
	/**
	 * 获取所有角色
	 * @author lyl
	 * 2018年04月20日
	 */
	@RequestMapping(value="findRole",method=RequestMethod.POST)
	public Result findRole(@RequestBody Role role){
		List<Role> categorys = roleService.findList(role);
		return ResultUtils.getMethodData(categorys);
	}
	
	
	/**
     * 角色信息
     * @author lyl
	 * 2018年04月20日
     */
    @RequestMapping(value = "getRoleDetail/{roleId}",method=RequestMethod.GET)
    public Object getRoleDetail(@PathVariable("roleId") Integer roleId) {
    	if(roleId==null) {
    		throw new RestPreconditionFailedException("请输入正确的参数！");
    	}
    	Role role = roleService.findById(roleId);
        return ResultUtils.getMethodData(role);
    }
    
    
    /**
     * 角色添加
     * @author lyl
	 * 2018年04月20日
     */
    @RequestMapping(value = "add",method=RequestMethod.POST)
    public Object add(@RequestParam("jsonParams") String jsonParams) {
    	try {
			String params=new String (Base64Utils.decode(jsonParams.getBytes("UTF-8")));
			JSONObject object=JSONObject.parseObject(params);
			Role role = JSONObject.toJavaObject(object, Role.class);
			if (null != role.getDeptid()) {
				// 校验所选部门是否与上级是同一个部门
				if (0 != role.getPid()) {
					Role parentRole = roleService.findById(role.getPid());
					Integer eid1 = parentRole.getDeptid();
					Department dept = departmentService.findById(role.getDeptid());
					Integer eid2 = dept.getId();
					if (!eid1.toString().equals(eid2.toString())) {
						return ResultUtils.postMethodData("ERRO");
					}
				}
				Integer peid = role.getParentEId();
				if(null==peid) {
					Department dept = departmentService.findById(role.getDeptid());
					role.setParentEId(dept.getParentEId());
				}
			}
			roleService.add(role);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ResultUtils.postMethodData("添加权限成功");
    }
    
    
    /**
     * 角色修改
     * @author lyl
	 * 2018年04月20日
     */
    @RequestMapping(value = "edit",method=RequestMethod.POST)
    public Object edit(@RequestParam("jsonParams") String jsonParams) {
    	try {
			String params=new String (Base64Utils.decode(jsonParams.getBytes("UTF-8")));
			JSONObject object=JSONObject.parseObject(params);
			Role role = JSONObject.toJavaObject(object, Role.class);
			Department dept1 = departmentService.findById(role.getDeptid());
			if(dept1!=null) {
			role.setParentEId(dept1.getParentEId());
			//校验所选部门是否与上级是同一个部门
			if(0!=role.getPid()) {
				Role parentRole = roleService.findById(role.getPid());
				Integer eid1 = parentRole.getDeptid();
				Department dept = departmentService.findById(role.getDeptid());
				Integer eid2 = dept.getId();
				if(eid1!=eid2) {
					return ResultUtils.postMethodData("ERRO");
				}
			}
			}
			roleService.update(role);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ResultUtils.postMethodData("修改权限成功");
    }
    
    
    
    /**
     * 角色删除
     * @author lyl
	 * 2018年04月17日
     */
    @RequestMapping(value = "del/{jsonParams}",method=RequestMethod.DELETE)
    public Object del(@PathVariable("jsonParams") String jsonParams) {
    	try {
			String params=new String (Base64Utils.decode(jsonParams.getBytes("UTF-8")));
			JSONObject object=JSONObject.parseObject(params);
			 if(!"success".equals(deleteCheck(object.getInteger("roleId")))) {
		    		return ResultUtils.deleteMethodData(deleteCheck(object.getInteger("roleId")));
		    	}
			//删除角色
			roleService.delete(object.getInteger("roleId"));
			//删除角色下所有权限
			roleService.deleteRolesById(object.getInteger("roleId"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ResultUtils.postMethodData("success");
    }
    
    /**
     * 删除校验
     */
    public String deleteCheck(Integer roleId) {
    	Role condition = new Role();
		condition.setPid(roleId);
		List<Role> roleList = roleService.findList(condition);
		if(0<roleList.size()) {
			return "存在下属角色，无法删除！";
		}
		List<UserRoleVO> userRoleVOList = userService.findUserRoleViewByRoleId(roleId);
		if(0<userRoleVOList.size()) {
			return "存在角色关联用户 ，无法删除！";
		}
        return "success";
    }
    
    
    /**
     * 获取角色树
     * @author lyl
	 * 2018年04月23日
     */
    @RequestMapping(value = "roleTreeList",method=RequestMethod.GET)
    public Object roleTreeList() {
    	List<ZTreeNode> list = roleService.roleTreeList();
        return ResultUtils.getMethodData(list);
    }
    
    /**
     * 根据角色id获取角色树
     * @author lyl
	 * 2018年04月23日
     */
    @RequestMapping(value = "roleTreeListByRoleId/{jsonParams}",method=RequestMethod.GET)
    public Object roleTreeListByRoleId(@PathVariable("jsonParams") String jsonParams) {
    	List<ZTreeNode> list =new ArrayList<ZTreeNode>();
    	try {
    		String paramsStr=new String(Base64Utils.decode(jsonParams.getBytes("UTF-8")));
    		JSONArray paramsArra=JSONArray.parseArray(paramsStr);
        	String[] roleId=new String[paramsArra.size()];
        	for(int i=0; i<paramsArra.size(); i++) {
        		roleId[i]=paramsArra.get(i).toString();
        	}
        	list = roleService.roleTreeListByRoleId(roleId);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return ResultUtils.getMethodData(list);
    }
    
    /**
     * 根据企业id获取角色树
     * @author lyl
	 * 2018年04月23日
     */
    @RequestMapping(value = "roleTreeListByParentEId/{parenteid}",method=RequestMethod.GET)
    public Object roleTreeListByParentEId(@PathVariable("parenteid") String parenteid) {
    	List<ZTreeNode> list =new ArrayList<ZTreeNode>();
        list = roleService.roleTreeListByParentEId(parenteid);
        return ResultUtils.getMethodData(list);
    }
	
}
