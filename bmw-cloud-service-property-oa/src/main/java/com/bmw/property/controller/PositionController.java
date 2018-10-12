package com.bmw.property.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.Department;
import com.bmw.property.model.Position;
import com.bmw.property.service.IDepartmentService;
import com.bmw.property.service.IPositionService;
import com.bmw.property.warpper.DepartmentWarpper;
import com.bmw.property.warpper.PositionWarpper;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-05-28 15:45:05
 */
@Controller
@RequestMapping("/position")
public class PositionController extends BaseController {

	  private String PREFIX = "/property/position/";

	    @Autowired
	    private IPositionService positionService;

	    /**
	     * 跳转到position首页
	     */
	    @RequestMapping("")
	    public String index() {
	        return PREFIX + "position.html";
	    }

	    /**
	     * 跳转到添加community
	     */
	    @RequestMapping("/position_add")
	    public String positionAdd() {
	        return PREFIX + "position_add.html";
	    }

	    /**
	     * 跳转到修改community
	     */
	    @RequestMapping("/position_update/{positionId}")
	    public String positionUpdate(@PathVariable Integer positionId, Model model) {
	    	Position position = positionService.getdetail(positionId);
	    	Position position11 = positionService.positionDeleteUpdate(position.getParentCode());
	    	String pname = positionService.positionDeleteUpdate(position.getParentCode()).getName();
	        model.addAttribute("pname",pname);
	        model.addAttribute("item",position);
	        LogObjectHolder.me().set(position);
	        return PREFIX + "position_edit.html";
	    }

	    /**
	     * 获取轮播图片管理列表
	     */
	    @RequestMapping(value = "/list")
	    @ResponseBody
	    public Object list(String condition) {
	        return super.warpObject(new PositionWarpper(positionService.findList(condition)));
	    }

	    /**
	     * 新增轮播图片管理
	     */
	    @RequestMapping(value = "/add")
	    @ResponseBody
	    public Object add(Position position) {
	    	//计算code值和层级 叶子数等
	    	if(position.getParentCode()==""||"0".equals(position.getParentCode())) {
	    		String count = positionService.positionCountCode("0");
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
	    		position.setIsLeaf(0);
	    		position.setChildrenCount(0);
	    		position.setCode(data+num);
	    		position.setParentCode("0");
	    		position.setLevel(Integer.valueOf("1"));
	    	}else {
	    		//新增时设置ParentCode和Code
 	    		String count = positionService.positionCountCode(position.getParentCode());
	    		//设置叶子节点总共个数
	    		String childCount = positionService.positionCountIsCode(position.getParentCode());
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
	    		if(!position.getParentCode().equals("0")) {
	    			position.setCode(position.getParentCode()+data+num);
	    		}
	    		position.setParentCode(position.getParentCode());
	    		//设置层级自动化
	    		Integer levelNum = Integer.valueOf(position.getCode().length())/4;
	    		position.setLevel(levelNum);
	    		//叶子节点设置
	    		position.setChildrenCount(0);
	    		//节点设置
	    		position.setIsLeaf(0);
	    		String positionId=positionService.positionCodeSelect(position.getParentCode().toString());
	    		Position positionData=new Position();
	    		if(!positionId.equals("null")) {
	    			positionData.setId(Integer.parseInt(positionId));
		    		positionData.setChildrenCount(Integer.parseInt(childCount)+1);
		    		positionData.setIsLeaf(1);
		    		positionService.update(positionData);
	    		}
	    	}
	    	//创建人信息和修改人信息等
	    	position.setCreateManId(1);
	    	position.setCreateMan("admin");
	    	position.setCreateTime(new Date());
	    	position.setEditTime(new Date());
	    	position.setEditMan("admin");
	    	position.setEditManId(1);
	    	positionService.add(position);
	        return SUCCESS_TIP;
	    }
	    

	    /**
	     * 删除轮播图片管理
	     */
	    @RequestMapping(value = "/delete")
	    @ResponseBody
	    public Object delete(@RequestParam String positionId) {
	    	Position position = new Position();
	    	position = positionService.getdetail(Integer.valueOf(positionId));
	    	if(position.getLevel()!=1) {
	    		Position positionData = positionService.positionDeleteUpdate(position.getParentCode());
	    		if(null!=positionData.getId()) {
	    		positionData.setChildrenCount(positionData.getChildrenCount()-1);
	    		if(positionData.getChildrenCount()==0) {
	    			positionData.setIsLeaf(0);
	    		}
	    		}
	    		positionService.update(positionData);
	    	}
	    	positionService.del(Integer.parseInt(positionId.toString()));
	        return SUCCESS_TIP;
	    }

	    /**
	     * 修改轮播图片管理
	     */
	    @RequestMapping(value = "/update")
	    @ResponseBody
	    public Object update(Position position) {
	    	if(position.getParentCode()==""||"0".equals(position.getParentCode())) {
	    		position.setParentCode("0");
	    		//Position positionTest = positionService.getdetail(Integer.valueOf(position.getId()));
	    		//Position positionData = positionService.positionDeleteUpdate(positionTest.getParentCode());
	    		//positionData.setChildrenCount(positionData.getChildrenCount()-1);
	    		positionService.update(position);
	    	}else {
	    		//当前处理
	    		Position positionTest = positionService.getdetail(Integer.valueOf(position.getId()));
	    		Position positionOne = positionService.positionDeleteUpdate(positionTest.getParentCode().toString());
	    		if(positionOne!=null) {
	    			positionOne.setChildrenCount(positionOne.getChildrenCount()-1);
	    			positionService.update(positionOne);
	    		}
	    		positionTest.setCode(positionTest.getParentCode()+positionTest.getCode());
	    		positionTest.setIsLeaf(0);
	    		positionService.update(positionTest);
	    		//更新上级处理
	    		Position positionData = positionService.positionDeleteUpdate(position.getParentCode());
	    		positionData.setChildrenCount(positionData.getChildrenCount()+1);
	    		positionService.update(positionData);
	    		if(positionData.getChildrenCount()==0) {
	    			positionData.setIsLeaf(0);
	    		}
	    	}
	    	
	    	
	    	position.setEditTime(new Date());
	    	position.setEditMan("admin");
	    	position.setEditManId(1);
	    	positionService.update(position);
	        return SUCCESS_TIP;
	    }

	    /**
	     * 轮播图片管理详情
	     */
	    @RequestMapping(value = "/detail/{communityId}")
	    @ResponseBody
	    public Object detail(@PathVariable("communityId") String positionId) {
	        return positionService.getdetail(Integer.parseInt(positionId));
	    }
	    
	    
	    /**
	     * 获取区域列表(树级父级菜单用)
	     */
	    @RequestMapping(value = "/positionTreeList")
	    @ResponseBody
	    public List<ZTreeNode> positionTreeList() {
	        List<ZTreeNode> roleTreeList = this.positionService.positionTreeList();
	        roleTreeList.add(ZTreeNode.createParent());
	        return roleTreeList;
	    }
	    
	    /**
	     * 获取区域列表(选择父级菜单用)
	     */
	    @RequestMapping(value = "/selectPositionTreeList")
	    @ResponseBody
	    public List<ZTreeNode> selectPositionTreeList() {
	        List<ZTreeNode> departmentTreeList = this.positionService.positionTreeList();
	        departmentTreeList.add(ZTreeNode.createParent());
	        return departmentTreeList;
	    }
	    
	    
}
