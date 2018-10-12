package com.bmw.base.web.department;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bmw.base.module.department.domain.Department;
import com.bmw.base.module.department.service.DepartmentService;
import com.bmw.base.module.role.domain.Role;
import com.bmw.base.module.role.service.RoleService;
import com.bmw.common.model.Result;
import com.bmw.common.utils.ResultUtils;
import com.bmw.property.module.enterprise.domain.EnterpriseInfo;
import com.bmw.property.module.enterprise.service.EnterpriseInfoService;

@RestController
@RequestMapping("base/department")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;
	
	@Autowired
	EnterpriseInfoService enterpriseInfoService;
	
	@Autowired
	RoleService roleService;
	/**
	 * 获取部门列表
	 * @author jmy
	 * 2018年05月24日
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="departmentList/{jsonParams}",method=RequestMethod.GET)
	public Result departmentList(@PathVariable("jsonParams") String jsonParams) {
		Department departmen = new Department();
		try{
			 String params = new String (Base64Utils.decode(jsonParams.getBytes("UTF-8")));
			 JSONObject object=JSONObject.parseObject(params);
			 if(object.get("name")!=null) {
				 departmen.setName(object.get("name").toString());
	            }
			 if(0!=(object.getInteger("parenteid"))) {
				 departmen.setParentEId(object.getInteger("parenteid"));
	            }
		}catch (Exception e) {
			// TODO Auto-generated catch block
		}
		List<Department> categorys = departmentService.findLike(departmen);
		return ResultUtils.getMethodData(categorys);
	}
	
	/**
	 * 部门详情
	 * @author jmy
	 * 2018年05月24日
	 */
	@RequestMapping(value="Detail/{departmenId}",method=RequestMethod.POST)
	public Result detail(@PathVariable("departmenId") String departmenId){
		Department categorys = new Department();
		try{
			 String params = new String (Base64Utils.decode(departmenId.getBytes("UTF-8")));
			 JSONObject object=JSONObject.parseObject(params);
			 categorys = departmentService.findById(Integer.parseInt(object.get("departmentId").toString()));
		}catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return ResultUtils.postMethodData(categorys);
	}
	
	/**
	 * 新增部门信息
	 * @author jmy
	 * 2018年05月24日
	 */
	@RequestMapping(value = "add",method=RequestMethod.POST)
    public Object add(@RequestParam("params") String params) {
		 try {
	            params = new String (Base64Utils.decode(params.getBytes("UTF-8")));
	            JSONObject object=JSONObject.parseObject(params);
	            Department departmen = (Department) JSONToObj(object.toJSONString(),Department.class);
	            
	          //校验所选物业是否与上级是同一个物业
				if(!"0".equals(departmen.getParentCode())) {
					Department condition = new Department();
					condition.setCode(departmen.getParentCode());
					Department dept = departmentService.findList(condition).get(0);
					EnterpriseInfo enterpriseInfo = enterpriseInfoService.findById(departmen.geteId());
					if(dept.geteId()!=enterpriseInfo.geteId()) {
						return ResultUtils.postMethodData("ERRO");
					}
				}
				
	            departmen.setIsDelete(Integer.parseInt("0"));
	            departmentService.add(departmen);
	        } catch (UnsupportedEncodingException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return ResultUtils.postMethodData("添加菜单成功");
    }
	
	/**
	 * 修改部门信息
	 * @author jmy
	 * 2018年05月24日
	 */
	@RequestMapping(value="update",method=RequestMethod.POST)
	public Result update(@RequestParam("params") String params){
		try {
            params = new String (Base64Utils.decode(params.getBytes("UTF-8")));
            JSONObject object=JSONObject.parseObject(params);
            Department departmen = (Department) JSONToObj(object.toJSONString(),Department.class);
            
            //校验所选物业是否与上级是同一个物业
			if(!"0".equals(departmen.getParentCode())) {
				Department condition = new Department();
				condition.setCode(departmen.getParentCode());
				Department dept = departmentService.findList(condition).get(0);
				EnterpriseInfo enterpriseInfo = enterpriseInfoService.findById(departmen.geteId());
				if(dept.geteId()!=enterpriseInfo.geteId()) {
					return ResultUtils.postMethodData("ERRO");
				}
			}
			
            departmentService.update(departmen);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		 return ResultUtils.postMethodData("变更菜单成功");
	}
	
	/**
	 * 删除医院信息
	 * @author jmy
	 * 2018年05月24日
	 */
	@RequestMapping(value="del/{jsonParams}",method=RequestMethod.POST)
	public Result del(@PathVariable("jsonParams") String jsonParams){
		try{
			 String params = new String (Base64Utils.decode(jsonParams.getBytes("UTF-8")));
			 JSONObject object=JSONObject.parseObject(params);
			 if(!"success".equals(deleteCheck(object.getInteger("departmentId")))) {
		    		return ResultUtils.deleteMethodData(deleteCheck(object.getInteger("departmentId")));
		    	}
			 departmentService.delete(Integer.parseInt(object.get("departmentId").toString()));
		}catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return ResultUtils.postMethodData("success");
	}
	
	 /**
     * 删除校验
     */
    public String deleteCheck(Integer departmentId) {
    	Department dept = departmentService.findById(departmentId);
    	Department condition = new Department();
		condition.setParentCode(dept.getCode());
		List<Department> deptList = departmentService.findList(condition);
		if(0<deptList.size()) {
			return "存在下属部门 ，无法删除！";
		}
		Role role = new Role();
		role.setDeptid(dept.getId());
		List<Role> roleList = roleService.findList(role);
		if(0<roleList.size()) {
			return "存在部门关联角色 ，无法删除！";
		}
        return "success";
    }
    
	
    @RequestMapping(value="departmentTreeList/{jsonParams}",method=RequestMethod.GET)
    public Result getDepartmentTreeList() {
        return ResultUtils.getMethodData(departmentService.departmentTreeList());
    }
    
    @RequestMapping(value="departmentTreeListByParentEId/{parenteid}",method=RequestMethod.GET)
    public Result getDepartmentTreeListByParentEId(@PathVariable("parenteid") String parenteid) {
        return ResultUtils.getMethodData(departmentService.getDepartmentTreeListByParentEId(parenteid));
    }
    
    @RequestMapping(value="departmentCountCode/{jsonParams}",method=RequestMethod.GET)
    public Result getDepartmentCountCode(@PathVariable("jsonParams") String jsonParams) {
    	String data =departmentService.departmentCountCode(jsonParams);
    	return ResultUtils.getMethodData("{\"data\":\""+data+"\"}");
    }
    
    @RequestMapping(value="departmentCountIsCode/{jsonParams}",method=RequestMethod.GET)
    public Result departmentCountIsCode(@PathVariable("jsonParams") String jsonParams) {
    	String data =departmentService.departmentCountIsCode(jsonParams);
    	return ResultUtils.getMethodData("{\"data\":\""+data+"\"}");
    }
    
    @RequestMapping(value="departmentCodeSelect/{jsonParams}",method=RequestMethod.GET)
    public Result getDepartmentCodeSelect(@PathVariable("jsonParams") String jsonParams) {
    	String data =departmentService.departmentCodeSelect(jsonParams);
    	return ResultUtils.getMethodData("{\"data\":\""+data+"\"}");
    }
    
	@RequestMapping(value="departmentDeleteUpdate/{code}",method=RequestMethod.POST)
	public Result departmentDeleteUpdate(@PathVariable("code") String code){
		Department categorys = departmentService.departmentDeleteUpdate(code);
		return ResultUtils.postMethodData(categorys);
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
	
	
}
