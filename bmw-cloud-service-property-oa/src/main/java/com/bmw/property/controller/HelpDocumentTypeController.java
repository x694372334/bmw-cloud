package com.bmw.property.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.beetl.ShiroExt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.HelpDocumentType;
import com.bmw.property.service.IHelpDocumentTypeService;
import com.bmw.property.warpper.HelpDocumentTypeWarpper;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-05-28 15:45:05
 */
@Controller
@RequestMapping("/helpDocumentType")
public class HelpDocumentTypeController extends BaseController {

	  private String PREFIX = "/property/helpDocumentType/";

	    @Autowired
	    private IHelpDocumentTypeService helpDocumentTypeService;

	    /**
	     * 跳转到helpDocumentType首页
	     */
	    @RequestMapping("")
	    public String index() {
	        return PREFIX + "helpDocumentType.html";
	    }

	    /**
	     * 跳转到添加community
	     */
	    @RequestMapping("/helpDocumentType_add")
	    public String helpDocumentTypeAdd() {
	        return PREFIX + "helpDocumentType_add.html";
	    }

	    /**
	     * 跳转到修改community
	     */
	    @RequestMapping("/helpDocumentType_update/{helpDocumentTypeId}")
	    public String helpDocumentTypeUpdate(@PathVariable Integer helpDocumentTypeId, Model model) {
	    	HelpDocumentType helpDocumentType = helpDocumentTypeService.getdetail(helpDocumentTypeId);
	        model.addAttribute("item",helpDocumentType);
	        if("0".equals(helpDocumentType.getParentCode())) {
	        	model.addAttribute("pName","顶级");
	        }else{
	        	String pName =  helpDocumentTypeService.helpDocumentTypeDeleteUpdate(helpDocumentType.getParentCode()).getName();
	        	model.addAttribute("pName",pName);
	        }
	        LogObjectHolder.me().set(helpDocumentType);
	        return PREFIX + "helpDocumentType_edit.html";
	    }

	    /**
	     * 获取轮播图片管理列表
	     */
	    @RequestMapping(value = "/list")
	    @ResponseBody
	    public Object list(String condition) {
	        return super.warpObject(new HelpDocumentTypeWarpper(helpDocumentTypeService.findList(condition)));
	    }

	    /**
	     * 新增轮播图片管理
	     */
	    @RequestMapping(value = "/add")
	    @ResponseBody
	    public Object add(HelpDocumentType helpDocumentType) {
	    	//计算code值和层级 叶子数等
	    	if(helpDocumentType.getParentCode()==""||"0".equals(helpDocumentType.getParentCode())) {
	    		String count = helpDocumentTypeService.helpDocumentTypeCountCode("0");
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
	    		helpDocumentType.setIsLeaf(0);
	    		helpDocumentType.setChildrenCount(0);
	    		helpDocumentType.setCode(data+num);
	    		helpDocumentType.setParentCode("0");
	    		helpDocumentType.setLevel(Integer.valueOf("1"));
	    	}else {
	    		//新增时设置ParentCode和Code
 	    		String count = helpDocumentTypeService.helpDocumentTypeCountCode(helpDocumentType.getParentCode());
	    		//设置叶子节点总共个数
	    		String childCount = helpDocumentTypeService.helpDocumentTypeCountIsCode(helpDocumentType.getParentCode());
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
	    		if(!helpDocumentType.getParentCode().equals("0")) {
	    			helpDocumentType.setCode(helpDocumentType.getParentCode()+data+num);
	    		}
	    		helpDocumentType.setParentCode(helpDocumentType.getParentCode());
	    		//设置层级自动化
	    		Integer levelNum = Integer.valueOf(helpDocumentType.getCode().length())/4;
	    		helpDocumentType.setLevel(levelNum);
	    		//叶子节点设置
	    		helpDocumentType.setChildrenCount(0);
	    		//节点设置
	    		helpDocumentType.setIsLeaf(0);
	    		String helpDocumentTypeId=helpDocumentTypeService.helpDocumentTypeCodeSelect(helpDocumentType.getParentCode().toString());
	    		HelpDocumentType helpDocumentTypeData=new HelpDocumentType();
	    		if(!helpDocumentTypeId.equals("null")) {
	    			helpDocumentTypeData.setId(Integer.parseInt(helpDocumentTypeId));
		    		helpDocumentTypeData.setChildrenCount(Integer.parseInt(childCount)+1);
		    		helpDocumentTypeData.setIsLeaf(1);
		    		helpDocumentTypeService.update(helpDocumentTypeData);
	    		}
	    	}
	    	//创建人信息和修改人信息等
	    	ShiroExt shiro = new ShiroExt();
	    	Integer userId = shiro.getUser().getId();
	    	String userName = shiro.getUser().getName();
	    	helpDocumentType.setCreateManId(userId);
	    	helpDocumentType.setCreateMan(userName);
	    	helpDocumentType.setCreateTime(new Date());
	    	helpDocumentType.setEditTime(new Date());
	    	/*helpDocumentType.setEditMan("admin");
	    	helpDocumentType.setEditManId(1);*/
	    	helpDocumentTypeService.add(helpDocumentType);
	        return SUCCESS_TIP;
	    }
	    

	    /**
	     * 删除轮播图片管理
	     */
	    @RequestMapping(value = "/delete")
	    @ResponseBody
	    public Object delete(@RequestParam String helpDocumentTypeId) {
	    	HelpDocumentType helpDocumentType = new HelpDocumentType();
	    	helpDocumentType = helpDocumentTypeService.getdetail(Integer.valueOf(helpDocumentTypeId));
	    	if(helpDocumentType.getLevel()!=1) {
	    		HelpDocumentType helpDocumentTypeData = helpDocumentTypeService.helpDocumentTypeDeleteUpdate(helpDocumentType.getParentCode());
	    		if(null!=helpDocumentTypeData.getId()) {
	    		helpDocumentTypeData.setChildrenCount(helpDocumentTypeData.getChildrenCount()-1);
	    		if(helpDocumentTypeData.getChildrenCount()==0) {
	    			helpDocumentTypeData.setIsLeaf(0);
	    		}
	    		}
	    		helpDocumentTypeService.update(helpDocumentTypeData);
	    	}
	    	helpDocumentTypeService.del(Integer.parseInt(helpDocumentTypeId.toString()));
	        return SUCCESS_TIP;
	    }

	    /**
	     * 修改轮播图片管理
	     */
	    @RequestMapping(value = "/update")
	    @ResponseBody
	    public Object update(HelpDocumentType helpDocumentType) {
	    	if(helpDocumentType.getParentCode()==""||"0".equals(helpDocumentType.getParentCode())) {
	    		helpDocumentType.setParentCode("0");
	    		//HelpDocumentType helpDocumentTypeTest = helpDocumentTypeService.getdetail(Integer.valueOf(helpDocumentType.getId()));
	    		//HelpDocumentType helpDocumentTypeData = helpDocumentTypeService.helpDocumentTypeDeleteUpdate(helpDocumentTypeTest.getParentCode());
	    		//helpDocumentTypeData.setChildrenCount(helpDocumentTypeData.getChildrenCount()-1);
	    		helpDocumentTypeService.update(helpDocumentType);
	    	}else {
	    		//当前处理
	    		HelpDocumentType helpDocumentTypeTest = helpDocumentTypeService.getdetail(Integer.valueOf(helpDocumentType.getId()));
	    		HelpDocumentType helpDocumentTypeOne = helpDocumentTypeService.helpDocumentTypeDeleteUpdate(helpDocumentTypeTest.getParentCode().toString());
	    		if(helpDocumentTypeOne!=null) {
	    			if(helpDocumentTypeOne.getChildrenCount()!=null) {
	    				helpDocumentTypeOne.setChildrenCount(helpDocumentTypeOne.getChildrenCount()-1);
		    			helpDocumentTypeService.update(helpDocumentTypeOne);
	    			}
	    		}
	    		helpDocumentTypeTest.setCode(helpDocumentTypeTest.getParentCode()+helpDocumentTypeTest.getCode());
	    		helpDocumentTypeTest.setIsLeaf(0);
	    		helpDocumentTypeService.update(helpDocumentTypeTest);
	    		//更新上级处理
	    		HelpDocumentType helpDocumentTypeData = helpDocumentTypeService.helpDocumentTypeDeleteUpdate(helpDocumentType.getParentCode());
	    		helpDocumentTypeData.setChildrenCount(helpDocumentTypeData.getChildrenCount()+1);
	    		helpDocumentTypeService.update(helpDocumentTypeData);
	    		if(helpDocumentTypeData.getChildrenCount()==0) {
	    			helpDocumentTypeData.setIsLeaf(0);
	    		}
	    	}
	    	
	    	ShiroExt shiro = new ShiroExt();
	    	Integer userId = shiro.getUser().getId();
	    	String userName = shiro.getUser().getName();
	    	helpDocumentType.setEditTime(new Date());
	    	helpDocumentType.setEditMan(userName);
	    	helpDocumentType.setEditManId(userId);
	    	helpDocumentTypeService.update(helpDocumentType);
	        return SUCCESS_TIP;
	    }

	    /**
	     * 轮播图片管理详情
	     */
	    @RequestMapping(value = "/detail/{communityId}")
	    @ResponseBody
	    public Object detail(@PathVariable("communityId") String helpDocumentTypeId) {
	        return helpDocumentTypeService.getdetail(Integer.parseInt(helpDocumentTypeId));
	    }
	    
	    
	    /**
	     * 获取区域列表(树级父级菜单用)
	     */
	    @RequestMapping(value = "/helpDocumentTypeTreeList")
	    @ResponseBody
	    public List<ZTreeNode> helpDocumentTypeTreeList() {
	        List<ZTreeNode> roleTreeList = this.helpDocumentTypeService.helpDocumentTypeTreeList();
	        roleTreeList.add(ZTreeNode.createParent());
	        return roleTreeList;
	    }
	    
	    /**
	     * 获取区域列表(选择父级菜单用)
	     */
	    @RequestMapping(value = "/selectHelpDocumentTypeTreeList")
	    @ResponseBody
	    public List<ZTreeNode> selectHelpDocumentTypeTreeList() {
	        List<ZTreeNode> departmentTreeList = this.helpDocumentTypeService.helpDocumentTypeTreeList();
	        departmentTreeList.add(ZTreeNode.createParent());
	        return departmentTreeList;
	    }
	    
	    
}
