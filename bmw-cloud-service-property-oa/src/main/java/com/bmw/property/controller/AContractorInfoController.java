package com.bmw.property.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;


import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.bmw.property.model.AContractor;
import com.bmw.property.service.IAContractorInfoService;
import com.bmw.property.warpper.AContractorInfoWarpper;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 赞助商管理控制器
 *
 * @author fengshuonan
 * @Date 2018-06-22 09:03:57
 */
@Controller
@RequestMapping("/aContractor")
public class AContractorInfoController extends BaseController {

    private String PREFIX = "/property/aContractor/";

    @Autowired
    private IAContractorInfoService acontractorInfoService;

    /**
     * 跳转到赞助商管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "aContractor.html";
    }

    /**
     * 跳转到添加赞助商管理
     */
    @RequestMapping("/aContractor_add")
    public String acontractorInfoAdd() {
        return PREFIX + "aContractor_add.html";
    }

    /**
     * 跳转到修改赞助商管理
     */
    @RequestMapping("/aContractor_update/{acontractorInfoId}")
    public String acontractorInfoUpdate(@PathVariable Integer acontractorInfoId, Model model) {
    	AContractor buildingInfo = acontractorInfoService.getdetail(acontractorInfoId);
        model.addAttribute("item",buildingInfo);
        LogObjectHolder.me().set(buildingInfo);
        return PREFIX + "aContractor_edit.html";
    }
    /**
     * 跳转到修改赞助商管理
     */
    @RequestMapping("/aContractor_detail/{acontractorInfoId}")
    public String buildingInfoDetail(@PathVariable Integer acontractorInfoId, Model model) {
    	AContractor acontractorInfo = acontractorInfoService.getdetail(acontractorInfoId);
        model.addAttribute("item",acontractorInfo);
        LogObjectHolder.me().set(acontractorInfo);
        return PREFIX + "aContractor_detail.html";
    }


    /**
     * 获取赞助商管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition,String nName) {
    	Integer eId = (Integer) super.getSession().getAttribute("eId");
    	return super.warpObject(new AContractorInfoWarpper(acontractorInfoService.findList(condition,nName,eId.toString())));
    }

    /**
     * 新增赞助商管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(AContractor acontractorInfo) {
        acontractorInfoService.add(acontractorInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除赞助商管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer acontractorInfoId) {
        acontractorInfoService.del(acontractorInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改赞助商管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(AContractor acontractorInfo) {
        acontractorInfoService.update(acontractorInfo);
        return SUCCESS_TIP;
    }
    
    

    /**
     * 赞助商管理详情
     */
    @RequestMapping(value = "/detail/{acontractorInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("acontractorInfoId") Integer acontractorInfoId) {
        return acontractorInfoService.getdetail(acontractorInfoId);
    }

    
}
