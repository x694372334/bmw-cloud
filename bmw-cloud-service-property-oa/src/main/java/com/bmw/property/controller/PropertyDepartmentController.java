package com.bmw.property.controller;

import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.Department;
import com.bmw.property.model.EnterpriseInfo;
import com.bmw.property.service.IDepartmentService;
import com.bmw.property.service.IEnterpriseInfoService;
import com.bmw.property.warpper.DepartmentWarpper;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-05-28 15:45:05
 */
@Controller
@RequestMapping("/propertyDepartment")
public class PropertyDepartmentController extends BaseController {

	  private String PREFIX = "/property/department/";

	    @Autowired
	    private IDepartmentService departmentService;

	    @Autowired
	    private IEnterpriseInfoService  enterpriseInfoService;
	    /**
	     * 跳转到department首页
	     */
	    @RequestMapping("")
	    public String index() {
	        return PREFIX + "department.html";
	    }

	    /**
	     * 跳转到添加community
	     */
	    @RequestMapping("/department_add")
	    public String departmentAdd() {
	        return PREFIX + "department_add.html";
	    }

	    /**
	     * 跳转到修改community
	     */
	    @RequestMapping("/department_update/{departmentId}")
	    public String departmentUpdate(@PathVariable Integer departmentId, Model model) {
	    	Department department = departmentService.getdetail(departmentId);
	        model.addAttribute("item",department);
	        String parentName = departmentService.departmentDeleteUpdate(department.getParentCode()).getName();
	        model.addAttribute("parentName",parentName);
	        LogObjectHolder.me().set(parentName);
	        LogObjectHolder.me().set(department);
	        return PREFIX + "department_edit.html";
	    }

	    /**
	     * 获取轮播图片管理列表
	     */
	    @RequestMapping(value = "/list")
	    @ResponseBody
	    public Object list(String condition) {
	        return super.warpObject(new DepartmentWarpper(departmentService.findList(condition)));
	    }

	    /**
	     * 新增轮播图片管理
	     */
	    @RequestMapping(value = "/add")
	    @ResponseBody
	    public Object add(Department department) {
	    	//获取物业所属企业
	    	Integer parentId = enterpriseInfoService.getdetail(department.geteId()).getParentId();
	    	department.setParentEId(parentId);
	    	department.setParentEName(ConstantFactory.me().getEnterpriseInfoName(parentId));
	    	//计算code值和层级 叶子数等
	    	if(department.getParentCode()==""||"0".equals(department.getParentCode())) {
	    		String count = departmentService.departmentCountCode("0");
	    		int num = Integer.valueOf(count);
	    		String data = null ;
	    		num=num+1;
		    	if(num>=10) {
	    			data = "00";
	    		}else if(num>=100) {
	    			data = "0";
	    		}else {
	    			data = "000";
	    		}
	    		department.setIsLeaf(0);
	    		department.setChildrenCount(0);
	    		department.setCode(data+num);
	    		department.setParentCode("0");
	    		department.setLevel(Integer.valueOf("1"));
	    	}else {
	    		//新增时设置ParentCode和Code
 	    		String count = departmentService.departmentCountCode(department.getParentCode());
	    		//设置叶子节点总共个数
	    		String childCount = departmentService.departmentCountIsCode(department.getParentCode());
	    		//设置编码code
	    		int num = Integer.valueOf(count);
	    		num=num+1;
	    		String data ;
	    		if(num>=10) {
	    			data = "00";
	    		}else if(num>=100) {
	    			data = "0";
	    		}else {
	    			data = "000";
	    		}
	    		if(!department.getParentCode().equals("0")) {
	    			department.setCode(department.getParentCode()+data+num);
	    		}
	    		department.setParentCode(department.getParentCode());
	    		//设置层级自动化
	    		Integer levelNum = Integer.valueOf(department.getCode().length())/4;
	    		department.setLevel(levelNum);
	    		//叶子节点设置
	    		department.setChildrenCount(0);
	    		//节点设置
	    		department.setIsLeaf(0);
	    		String departmentId=departmentService.departmentCodeSelect(department.getParentCode().toString());
	    		Department departmentData=new Department();
	    		if(!departmentId.equals("null")) {
	    			departmentData.setId(Integer.parseInt(departmentId));
		    		departmentData.setChildrenCount(Integer.parseInt(childCount)+1);
		    		departmentData.setIsLeaf(1);
		    		departmentService.update(departmentData);
	    		}
	    	}
	    	//创建人信息和修改人信息等
	    	department.setCreateManId(1);
	    	department.setCreateMan("admin");
	    	department.setCreateTime(new Date());
	    	department.setEditTime(new Date());
	    	department.setEditMan("admin");
	    	department.setEditManId(1);
	    	String flag = departmentService.add(department);
	    	 if("1".equals(flag)) {
	         	return SUCCESS_TIP;
	         }else {
	         	return new ErrorTip(400,"上级部门与所选物业非同一物业");
	         }
	    }
	    

	    /**
	     * 删除轮播图片管理
	     */
	    @RequestMapping(value = "/delete")
	    @ResponseBody
	    public Object delete(@RequestParam String departmentId) {
	    	Department department = new Department();
	    	department = departmentService.getdetail(Integer.valueOf(departmentId));
	    	String result = departmentService.del(Integer.parseInt(departmentId.toString()));
	    	if("success".equals(result)) {
	    		if(department.getLevel()!=1) {
		    		Department departmentData = departmentService.departmentDeleteUpdate(department.getParentCode());
		    		if(null!=departmentData.getId()) {
		    		departmentData.setChildrenCount(departmentData.getChildrenCount()-1);
		    		if(departmentData.getChildrenCount()==0) {
		    			departmentData.setIsLeaf(0);
		    		}
		    		}
		    		departmentService.update(departmentData);
		    	}
		        return SUCCESS_TIP;
	    	}else {
	    		 return new ErrorTip(400, result);
	    	}
	    	
	    }

	    /**
	     * 修改轮播图片管理
	     */
	    @RequestMapping(value = "/update")
	    @ResponseBody
	    public Object update(Department department) {
	    	//获取物业所属企业
	    	Integer parentId = enterpriseInfoService.getdetail(department.geteId()).getParentId();
	    	department.setParentEId(parentId);
	    	department.setParentEName(ConstantFactory.me().getEnterpriseInfoName(parentId));
	    	if(department.getParentCode()==""||"0".equals(department.getParentCode())) {
	    		department.setParentCode("0");
	    		//Department departmentTest = departmentService.getdetail(Integer.valueOf(department.getId()));
	    		//Department departmentData = departmentService.departmentDeleteUpdate(departmentTest.getParentCode());
	    		//departmentData.setChildrenCount(departmentData.getChildrenCount()-1);
	    		departmentService.update(department);
	    	}else {
	    		//当前处理
	    		Department departmentTest = departmentService.getdetail(Integer.valueOf(department.getId()));
	    		Department departmentOne = departmentService.departmentDeleteUpdate(departmentTest.getParentCode().toString());
	    		if(departmentOne.getId()!=null) {
	    			departmentOne.setChildrenCount(departmentOne.getChildrenCount()-1);
	    			departmentService.update(departmentOne);
	    		}
	    		departmentTest.setCode(departmentTest.getParentCode()+departmentTest.getCode());
	    		departmentTest.setIsLeaf(0);
	    		departmentService.update(departmentTest);
	    		//更新上级处理
	    		Department departmentData = departmentService.departmentDeleteUpdate(department.getParentCode());
	    		departmentData.setChildrenCount(departmentData.getChildrenCount()+1);
	    		departmentService.update(departmentData);
	    		if(departmentData.getChildrenCount()==0) {
	    			departmentData.setIsLeaf(0);
	    		}
	    	}
	    	
	    	
	    	department.setEditTime(new Date());
	    	department.setEditMan("admin");
	    	department.setEditManId(1);
	    	String flag = departmentService.update(department);
	    	 if("1".equals(flag)) {
		         	return SUCCESS_TIP;
		         }else {
		         	return new ErrorTip(400,"上级部门与所选物业非同一物业");
		         }
	    }

	    /**
	     * 轮播图片管理详情
	     */
	    @RequestMapping(value = "/detail/{communityId}")
	    @ResponseBody
	    public Object detail(@PathVariable("communityId") String departmentId) {
	        return departmentService.getdetail(Integer.parseInt(departmentId));
	    }
	    
	    
	    /**
	     * 获取区域列表(树级父级菜单用)
	     */
	    @RequestMapping(value = "/departmentTreeList")
	    @ResponseBody
	    public List<ZTreeNode> departmentTreeList() {
	    	ShiroExt shiro = new ShiroExt();
	        List<ZTreeNode> roleTreeList = this.departmentService.departmentTreeList();
				roleTreeList.add(ZTreeNode.createParent());
	        return roleTreeList;
	    }
	    
	    /**
	     * 获取区域列表(选择父级菜单用)
	     */
	    @RequestMapping(value = "/selectdepartmentTreeList")
	    @ResponseBody
	    public List<ZTreeNode> selectDepartmentTreeList() {
	    	ShiroExt shiro = new ShiroExt();
	        List<ZTreeNode> departmentTreeList = this.departmentService.departmentTreeList();
	        	departmentTreeList.add(ZTreeNode.createParent());
	        return departmentTreeList;
	    }
	    
	    /**
	     * 验证企业类型
	     */
	    @RequestMapping(value = "/verification")
	    @ResponseBody
	    public Object verification(@RequestParam Integer enterpriseInfoId) {
	    	EnterpriseInfo enterpriseInfo = enterpriseInfoService.getdetail(enterpriseInfoId);
	    	if("2".equals(enterpriseInfo.getEnterpriseType())) {
	    		 return SUCCESS_TIP;
	    	}
	        return ERROR;
	    }
	    
	    /**
	     * 跳转到修改community
	     */
	    @RequestMapping("/findEnter/{parentCode}")
	    @ResponseBody
	    public String findEnter(@PathVariable String parentCode) {
	    	Department department = departmentService.departmentDeleteUpdate(parentCode);
	    	EnterpriseInfo enter = enterpriseInfoService.getdetail(department.geteId());
	    	String result = JSONObject.toJSONString(enter);
	    	return result;
	    }

}
