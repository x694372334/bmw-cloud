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
import com.bmw.property.model.IVehicleInfo;
import com.bmw.property.service.IVehicleInfoService;
import com.bmw.property.warpper.IVehicleInfoWarpper;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 车辆管理控制器
 *
 * @author fengshuonan
 * @Date 2018-06-22 09:03:57
 */
@Controller
@RequestMapping("/iVehicleInfo")
public class IVehicleInfoController extends BaseController {

    private String PREFIX = "/property/iVehicleInfo/";

    @Autowired
    private IVehicleInfoService iVehicleInfoService;

    /**
     * 跳转到车辆管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "iVehicleInfo.html";
    }

    /**
     * 跳转到添加车辆管理
     */
    @RequestMapping("/iVehicleInfo_add")
    public String iVehicleInfoAdd() {
        return PREFIX + "iVehicleInfo_add.html";
    }

    /**
     * 跳转到修改车辆管理
     */
    @RequestMapping("/iVehicleInfo_update/{iVehicleInfoId}")
    public String iVehicleInfoUpdate(@PathVariable Integer iVehicleInfoId, Model model) {
    	IVehicleInfo buildingInfo = iVehicleInfoService.getdetail(iVehicleInfoId);
        model.addAttribute("item",buildingInfo);
        LogObjectHolder.me().set(buildingInfo);
        return PREFIX + "iVehicleInfo_edit.html";
    }
    /**
     * 跳转到修改车辆管理
     */
    @RequestMapping("/iVehicleInfo_detail/{iVehicleInfoId}")
    public String buildingInfoDetail(@PathVariable Integer iVehicleInfoId, Model model) {
    	IVehicleInfo iVehicleInfo = iVehicleInfoService.getdetail(iVehicleInfoId);
        model.addAttribute("item",iVehicleInfo);
        LogObjectHolder.me().set(iVehicleInfo);
        return PREFIX + "iVehicleInfo_detail.html";
    }

    /**
     * 获取车辆管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition,String nName,String iName) {
    	Integer eId = (Integer) super.getSession().getAttribute("eId");
    	return super.warpObject(new IVehicleInfoWarpper(iVehicleInfoService.findList(condition, nName, iName,eId.toString())));
    }

    /**
     * 新增车辆管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(IVehicleInfo iVehicleInfo) {
        iVehicleInfoService.add(iVehicleInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除车辆管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer iVehicleInfoId) {
        iVehicleInfoService.del(iVehicleInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改车辆管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(IVehicleInfo iVehicleInfo) {
        iVehicleInfoService.update(iVehicleInfo);
        return SUCCESS_TIP;
    }

    /**
     * 车辆管理详情
     */
    @RequestMapping(value = "/detail/{iVehicleInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("iVehicleInfoId") Integer iVehicleInfoId) {
        return iVehicleInfoService.getdetail(iVehicleInfoId);
    }

    
    
}
