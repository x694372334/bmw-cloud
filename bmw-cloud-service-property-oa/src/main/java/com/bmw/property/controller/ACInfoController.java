package com.bmw.property.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.bmw.property.model.ACInfo;
import com.bmw.property.model.AContractor;
import com.bmw.property.model.Advertising;
import com.bmw.property.service.IACInfoService;
import com.bmw.property.service.IAContractorInfoService;
import com.bmw.property.service.IAdvertisingInfoService;
import com.bmw.property.warpper.ACInfoWarpper;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 赞助商管理控制器
 *
 * @author fengshuonan
 * @Date 2018-06-22 09:03:57
 */
@Controller
@RequestMapping("/aCInfo")
public class ACInfoController extends BaseController {

    private String PREFIX = "/property/aCInfo/";

    @Autowired
    private IACInfoService aCInfoService;
    
    @Autowired
    private IAdvertisingInfoService advertisingService;
    
    @Autowired
    private IAContractorInfoService contractorInfoService;

    /**
     * 跳转到赞助商管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "ACInfo.html";
    }

    /**
     * 跳转到添加赞助商管理
     */
    @RequestMapping("/ACInfo_add")
    public String ACInfoInfoAdd() {
        return PREFIX + "ACInfo_add.html";
    }

    /**
     * 跳转到修改赞助商管理
     */
    @RequestMapping("/ACInfo_update/{ACInfoInfoId}")
    public String ACInfoInfoUpdate(@PathVariable Integer ACInfoInfoId, Model model) {
    	ACInfo buildingInfo = aCInfoService.getdetail(ACInfoInfoId);
        model.addAttribute("item",buildingInfo);
        LogObjectHolder.me().set(buildingInfo);
        return PREFIX + "ACInfo_edit.html";
    }
    /**
     * 跳转到修改赞助商管理
     */
    @RequestMapping("/ACInfo_detail/{ACInfoInfoId}")
    public String buildingInfoDetail(@PathVariable Integer ACInfoInfoId, Model model) {
    	ACInfo ACInfoInfo = aCInfoService.getdetail(ACInfoInfoId);
        model.addAttribute("item",ACInfoInfo);
        LogObjectHolder.me().set(ACInfoInfo);
        return PREFIX + "ACInfo_detail.html";
    }


    /**
     * 获取赞助商管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	return super.warpObject(new ACInfoWarpper(aCInfoService.findList(condition)));
    }

    /**
     * 新增赞助商管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ACInfo ACInfoInfo) {
    	aCInfoService.add(ACInfoInfo);
    	//修改广告位状态
    	Advertising hospital = new Advertising();
    	hospital.setaId(ACInfoInfo.getaId());
    	hospital.setaStatus(Integer.parseInt(ACInfoInfo.getIsSuccess()));
    	advertisingService.update(hospital);
    	//修改分包商状态
    	AContractor acon = new AContractor();
    	acon.setcId(ACInfoInfo.getcId());
    	acon.setcStatus(1);
    	contractorInfoService.update(acon);
        return SUCCESS_TIP;
    }

    /**
     * 删除赞助商管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer ACInfoInfoId) {
    	aCInfoService.del(ACInfoInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改赞助商管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ACInfo ACInfoInfo) {
    	//修改分包商状态
    	AContractor acon = new AContractor();
    	acon.setcId(ACInfoInfo.getcId());
    	acon.setcStatus(2);
    	contractorInfoService.update(acon);
    	ACInfoInfo.setcId(null);
    	//修改中间表
    	LocalDateTime dateTime = LocalDateTime.now();
    	ACInfoInfo.setcETime(Timestamp.valueOf(dateTime));
    	ACInfoInfo.setIsDelete(0);
    	aCInfoService.update(ACInfoInfo);
    	//修改广告位状态
    	Advertising hospital = new Advertising();
    	hospital.setaId(ACInfoInfo.getaId());
    	hospital.setaStatus(Integer.parseInt(ACInfoInfo.getIsSuccess()));
    	advertisingService.update(hospital);
        return SUCCESS_TIP;
    }
    
    
    

    /**
     * 赞助商管理详情
     */
    @RequestMapping(value = "/detail/{ACInfoInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("ACInfoInfoId") Integer ACInfoInfoId) {
        return aCInfoService.getdetail(ACInfoInfoId);
    }

    
}
