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
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.bmw.property.model.Advertising;
import com.bmw.property.service.IAdvertisingInfoService;
import com.bmw.property.warpper.AdvertisingInfoWarpper;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 广告位管理控制器
 *
 * @author fengshuonan
 * @Date 2018-06-22 09:03:57
 */
@Controller
@RequestMapping("/advertising")
public class AdvertisingInfoController extends BaseController {

    private String PREFIX = "/property/advertising/";

    @Autowired
    private IAdvertisingInfoService advertisingService;

    /**
     * 跳转到广告位管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "advertising.html";
    }

    /**
     * 跳转到添加广告位管理
     */
    @RequestMapping("/advertising_add")
    public String advertisingInfoAdd() {
        return PREFIX + "advertising_add.html";
    }

    /**
     * 跳转到修改广告位管理
     */
    @RequestMapping("/advertising_update/{advertisingId}")
    public String advertisingInfoUpdate(@PathVariable Integer advertisingId, Model model) {
    	Advertising buildingInfo = advertisingService.getdetail(advertisingId);
        model.addAttribute("item",buildingInfo);
        LogObjectHolder.me().set(buildingInfo);
        return PREFIX + "advertising_edit.html";
    }
    /**
     * 跳转到修改广告位管理
     */
    @RequestMapping("/advertising_detail/{advertisingId}")
    public String buildingInfoDetail(@PathVariable Integer advertisingId, Model model) {
    	Advertising advertisingInfo = advertisingService.getdetail(advertisingId);
        model.addAttribute("item",advertisingInfo);
        LogObjectHolder.me().set(advertisingInfo);
        return PREFIX + "advertising_detail.html";
    }
    
    /**
     * 跳转到广告位关联
     */
    @RequestMapping("/advertising_contractor/{advertisingId}")
    public String buildingInfoContractor(@PathVariable Integer advertisingId, Model model) {
    	Advertising advertisingInfo = advertisingService.getdetail(advertisingId);
        model.addAttribute("item",advertisingInfo);
        LogObjectHolder.me().set(advertisingInfo);
        return PREFIX + "advertising_contractor.html";
    }
    
    /**
     * 跳转到广告位解除
     */
    @RequestMapping("/advertising_relieve/{advertisingId}")
    public String buildingInfoRelieve(@PathVariable Integer advertisingId, Model model) {
    	Advertising advertisingInfo = advertisingService.getdetail(advertisingId);
        model.addAttribute("item",advertisingInfo);
        LogObjectHolder.me().set(advertisingInfo);
        return PREFIX + "advertising_relieve.html";
    }


    /**
     * 获取广告位管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	Integer eId = (Integer) super.getSession().getAttribute("eId");
    	return super.warpObject(new AdvertisingInfoWarpper(advertisingService.findList(condition,eId.toString())));
    }

    /**
     * 新增广告位管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Advertising advertisingInfo) {
    	advertisingService.add(advertisingInfo);
        return SUCCESS_TIP;
    }

    @RequestMapping(value = "/selectData")
    @ResponseBody
    public Object selectData(Integer nbId,Integer id,Integer relevanceId) {
    	return advertisingService.selectData(nbId,id,relevanceId);
    }
    
    /**
     * 删除广告位管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer advertisingId) {
    	advertisingService.del(advertisingId);
        return SUCCESS_TIP;
    }

    /**
     * 修改广告位管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Advertising advertising) {
    	advertisingService.update(advertising);
        return SUCCESS_TIP;
    }

    /**
     * 广告位管理详情
     */
    @RequestMapping(value = "/detail/{advertisingId}")
    @ResponseBody
    public Object detail(@PathVariable("advertisingId") Integer advertisingId) {
        return advertisingService.getdetail(advertisingId);
    }
    
    /**
     * 广告位管理详情
     */
    @RequestMapping(value = "/findContractor/{advertisingId}")
    @ResponseBody
    public Object findContractor(@PathVariable("advertisingId") Integer advertisingId) {
        return advertisingService.findContractor(advertisingId);
    }
    
    /**
	  * 方法名称 : createGGWTree
	  * 作者 : wangliqing  
	  * 方法描述 : 创建广告位Tree
	  * 创建时间 : 2018年7月5日 下午2:46:03    
	  * 参数 : @param nbId
	  * 返回类型 : List<ZTreeNode>    
     */
    @RequestMapping(value="/createGGWTree/{nbId}")
    @ResponseBody
    public List<ZTreeNode> createGGWTree(@PathVariable("nbId") Integer nbId) {
    	//TODO 后续从系统中取得eId，此处测试暂时放1
        return advertisingService.createGGWTree(ShiroKit.getUser().geteId(),nbId);
    }
    
    
}
