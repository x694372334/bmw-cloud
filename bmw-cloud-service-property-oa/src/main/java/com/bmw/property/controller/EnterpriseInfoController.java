package com.bmw.property.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.EnterpriseInfo;
import com.bmw.property.service.IEnterpriseInfoService;

import org.springframework.web.bind.annotation.RequestParam;


/**
 * enterprise控制器
 *
 * @author zw
 * @Date 2018-06-25 16:55:36
 */
@Controller
@RequestMapping("/enterpriseInfo")
public class EnterpriseInfoController extends BaseController {

    private String PREFIX = "/property/enterpriseInfo/";

    @Autowired
    private IEnterpriseInfoService enterpriseInfoService;

    /**
     * 跳转到enterprise首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "enterpriseInfo.html";
    }

    /**
     * 跳转到添加enterprise
     */
    @RequestMapping("/enterpriseInfo_add")
    public String enterpriseInfoAdd() {
        return PREFIX + "enterpriseInfo_add.html";
    }

    /**
     * 跳转到修改enterprise
     */
    @RequestMapping("/enterpriseInfo_update/{enterpriseInfoId}")
    public String enterpriseInfoUpdate(@PathVariable Integer enterpriseInfoId, Model model) {
        EnterpriseInfo enterpriseInfo = enterpriseInfoService.getdetail(enterpriseInfoId);
        model.addAttribute("parentName",ConstantFactory.me().getEnterpriseInfoName(enterpriseInfo.getParentId()));
        model.addAttribute("item",enterpriseInfo);
        LogObjectHolder.me().set(enterpriseInfo);
        return PREFIX + "enterpriseInfo_edit.html";
    }

    /**
     * 获取enterprise列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return enterpriseInfoService.findList(condition);
    }

    /**
     * 新增enterprise
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(EnterpriseInfo enterpriseInfo) {
    	if(enterpriseInfo.getParentId()==null)
    		enterpriseInfo.setParentId(0);
        enterpriseInfoService.add(enterpriseInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除enterprise
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer enterpriseInfoId) {
        //enterpriseInfoService.del(enterpriseInfoId);
    	//EnterpriseInfo enterpriseInfo = enterpriseInfoService.getdetail(enterpriseInfoId);
    	//enterpriseInfo.setIsDelete(0);
    	String result = enterpriseInfoService.del(enterpriseInfoId);
    	if("success".equals(result)) {
    		 return SUCCESS_TIP;
    	}else {
    		 return new ErrorTip(400, result);
    	}
    }

    /**
     * 修改enterprise
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(EnterpriseInfo enterpriseInfo) {
        enterpriseInfoService.update(enterpriseInfo);
        return SUCCESS_TIP;
    }

    /**
     * enterprise详情
     */
    @RequestMapping(value = "/detail/{enterpriseInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("enterpriseInfoId") Integer enterpriseInfoId) {
        return enterpriseInfoService.getdetail(enterpriseInfoId);
    }
    /**
     * 获取列表(树级父菜单用)
     */
    @RequestMapping(value = "/enterpriseTreeList")
    @ResponseBody
    public List<ZTreeNode> enterpriseTreeList() {
        List<ZTreeNode> treeList = this.enterpriseInfoService.findTreeList();
        treeList.add(ZTreeNode.createParent());
        return treeList;
    }
    
    /**
     * 获取列表
     */
    @RequestMapping(value = "/selectEnterpriseTreeList")
    @ResponseBody
    public List<ZTreeNode> selectEnterpriseTreeList() {
        List<ZTreeNode> treeList = this.enterpriseInfoService.findTreeList();
        treeList.add(ZTreeNode.createParent());
        return treeList;
    }
    
    /**
     * 获取列表(选择父级菜单用)
     */
    @RequestMapping(value = "/selectEnterpriseParentTreeList")
    @ResponseBody
    public List<ZTreeNode> selectEnterpriseParentTreeList() {
        List<ZTreeNode> treeList = this.enterpriseInfoService.findParentTreeList();
        treeList.add(ZTreeNode.createParent());
        return treeList;
    }
}
