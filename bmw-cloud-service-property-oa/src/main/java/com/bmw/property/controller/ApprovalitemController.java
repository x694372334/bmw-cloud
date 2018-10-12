package com.bmw.property.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.Approvalitem;
import com.bmw.property.service.IApprovalitemService;
import com.bmw.property.warpper.ApprovalitemWarpper;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 广告位管理控制器
 *
 * @author fengshuonan
 * @Date 2018-06-22 09:03:57
 */
@Controller
@RequestMapping("/approvalitem")
public class ApprovalitemController extends BaseController {

    private String PREFIX = "/property/approvalitem/";

    @Autowired
    private IApprovalitemService approvalitemService;

    /**
     * 跳转到广告位管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "approvalitem.html";
    }

    /**
     * 跳转到添加广告位管理
     */
    @RequestMapping("/approvalitem_add")
    public String approvalitemInfoAdd() {
        return PREFIX + "approvalitem_add.html";
    }

    /**
     * 跳转到修改广告位管理
     */
    @RequestMapping("/approvalitem_update/{approvalitemId}")
    public String approvalitemInfoUpdate(@PathVariable Integer approvalitemId, Model model) {
    	Approvalitem buildingInfo = approvalitemService.getdetail(approvalitemId);
        model.addAttribute("item",buildingInfo);
        LogObjectHolder.me().set(buildingInfo);
        return PREFIX + "approvalitem_edit.html";
    }
    /**
     * 跳转到修改广告位管理
     */
    @RequestMapping("/approvalitem_detail/{approvalitemId}")
    public String buildingInfoDetail(@PathVariable Integer approvalitemId, Model model) {
    	Approvalitem approvalitemInfo = approvalitemService.getdetail(approvalitemId);
        model.addAttribute("item",approvalitemInfo);
        LogObjectHolder.me().set(approvalitemInfo);
        return PREFIX + "approvalitem_detail.html";
    }
    
    /**
     * 跳转到修改广告位管理
     */
    @RequestMapping("/approvalitem_contractor/{approvalitemId}")
    public String buildingInfoContractor(@PathVariable Integer approvalitemId, Model model) {
    	Approvalitem approvalitemInfo = approvalitemService.getdetail(approvalitemId);
        model.addAttribute("item",approvalitemInfo);
        LogObjectHolder.me().set(approvalitemInfo);
        return PREFIX + "approvalitem_contractor.html";
    }


    /**
     * 获取广告位管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	return super.warpObject(new ApprovalitemWarpper(approvalitemService.findList(condition)));
    }

    /**
     * 新增广告位管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Approvalitem approvalitemInfo) {
    	approvalitemService.add(approvalitemInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除广告位管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer approvalitemId) {
    	approvalitemService.del(approvalitemId);
        return SUCCESS_TIP;
    }

    /**
     * 修改广告位管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Approvalitem approvalitem) {
    	approvalitemService.update(approvalitem);
        return SUCCESS_TIP;
    }

    /**
     * 广告位管理详情
     */
    @RequestMapping(value = "/detail/{approvalitemId}")
    @ResponseBody
    public Object detail(@PathVariable("approvalitemId") Integer approvalitemId) {
        return approvalitemService.getdetail(approvalitemId);
    }
    
    
}
