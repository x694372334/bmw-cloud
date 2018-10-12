package com.bmw.property.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.beetl.ShiroExt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.bmw.property.model.BuildingInfo;
import com.bmw.property.service.IBuildingInfoService;
import com.bmw.property.warpper.BuildingInfoWarpper;
import com.bmw.property.warpper.NeighborhoodWarpper;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 楼宇管理控制器
 *
 * @author fengshuonan
 * @Date 2018-06-22 09:03:57
 */
@Controller
@RequestMapping("/buildingInfo")
public class BuildingInfoController extends BaseController {

    private String PREFIX = "/property/buildingInfo/";

    @Autowired
    private IBuildingInfoService buildingInfoService;

    /**
     * 跳转到楼宇管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "buildingInfo.html";
    }

    /**
     * 跳转到添加楼宇管理
     */
    @RequestMapping("/buildingInfo_add")
    public String buildingInfoAdd() {
        return PREFIX + "buildingInfo_add.html";
    }

    /**
     * 跳转到修改楼宇管理
     */
    @RequestMapping("/buildingInfo_update/{buildingInfoId}")
    public String buildingInfoUpdate(@PathVariable Integer buildingInfoId, Model model) {
        BuildingInfo buildingInfo = buildingInfoService.getdetail(buildingInfoId);
        model.addAttribute("item",buildingInfo);
        LogObjectHolder.me().set(buildingInfo);
        return PREFIX + "buildingInfo_edit.html";
    }
    
    /**
     * 跳转到修改楼宇管理
     */
    @RequestMapping("/buildingInfo_detail/{buildingInfoId}")
    public String buildingInfoDetail(@PathVariable Integer buildingInfoId, Model model) {
        BuildingInfo buildingInfo = buildingInfoService.getdetail(buildingInfoId);
        model.addAttribute("item",buildingInfo);
        LogObjectHolder.me().set(buildingInfo);
        return PREFIX + "buildingInfo_detail.html";
    }

    /**
     * 获取楼宇管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String xqName , String lyName) {
    	Integer eId = (Integer) super.getSession().getAttribute("eId");
    	return super.warpObject(new BuildingInfoWarpper(buildingInfoService.findList(xqName,lyName,eId.toString())));
    }

    /**
     * 新增楼宇管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BuildingInfo buildingInfo) {
        buildingInfoService.add(buildingInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除楼宇管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer buildingInfoId) {
        int data = buildingInfoService.del(buildingInfoId);
        if(data == 1) {
        	return SUCCESS_TIP;
        }else {
        	return "240";
        }
    }

    /**
     * 修改楼宇管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BuildingInfo buildingInfo) {
        buildingInfoService.update(buildingInfo);
        return SUCCESS_TIP;
    }

    /**
     * 楼宇管理详情
     */
    @RequestMapping(value = "/detail/{buildingInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("buildingInfoId") Integer buildingInfoId) {
        return buildingInfoService.getdetail(buildingInfoId);
    }
    
    /**
     * 查询小区管理
     */
    @RequestMapping(value = "/findNeighbor")
    @ResponseBody
    public Object findNeighbor() {
    	Integer eId = (Integer)super.getSession().getAttribute("eId");
        return buildingInfoService.findNeighbor(eId);
    }
    
    /**
     * 查询小区管理
     */
    @RequestMapping(value = "/findNeighborUpdate/{nId}")
    @ResponseBody
    public Object findNeighbor(@PathVariable("nId") Integer nId) {
        return buildingInfoService.findNeighborUpdate(nId);
    }
    
    
    
}
