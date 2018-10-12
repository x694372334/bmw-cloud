package com.bmw.property.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.bmw.property.model.SelectBulter;
import com.bmw.property.service.ISelectBulterService;
import com.bmw.property.warpper.SelectBulterWarpper;

/**
 * 楼宇管理控制器
 *
 * @author fengshuonan
 * @Date 2018-06-22 09:03:57
 */
@Controller
@RequestMapping("/selectBulter")
public class SelectBulterController extends BaseController {

    private String PREFIX = "/property/selectBulter/";

	@Value("${bmw.cloud.fileservice.url}")
	public String fileServerUrl;
    @Autowired
    private ISelectBulterService selectBulterService;

    /**
     * 跳转到房屋管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "selectBulter.html";
    }

    /**
     * 跳转到添加房屋管理
     */
    @RequestMapping("/selectBulter_add")
    public String selectBulterAdd() {
        return PREFIX + "selectBulter_add.html";
    }

    /**
     * 跳转到修改房屋管理
     */
    @RequestMapping("/selectBulter_update/{selectBulterId}")
    public String selectBulterUpdate(@PathVariable Integer selectBulterId, Model model) {
    	SelectBulter buildingInfo = selectBulterService.getdetail(selectBulterId);
        model.addAttribute("item",buildingInfo);
        LogObjectHolder.me().set(buildingInfo);
        return PREFIX + "selectBulter_edit.html";
    }
    /**
     * 跳转到修改房屋管理
     */
    @RequestMapping("/selectBulter_detail/{selectBulterId}")
    public String buildingInfoDetail(@PathVariable Integer selectBulterId, Model model) {
    	SelectBulter selectBulter = selectBulterService.getdetail(selectBulterId);
        model.addAttribute("item",selectBulter);
        LogObjectHolder.me().set(selectBulter);
        return PREFIX + "selectBulter_detail.html";
    }
    

    /**
     * 获取房屋管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	Integer ss =(Integer) super.getSession().getAttribute("eId");
    	ShiroExt shiro = new ShiroExt();
    	return super.warpObject(new SelectBulterWarpper(selectBulterService.findList(condition, ss , shiro.getUser().getId().toString())));
    }
    
    /**
     * 房屋管理详情
     */
    @RequestMapping(value = "/detail/{selectBulterId}")
    @ResponseBody
    public Object detail(@PathVariable("selectBulterId") Integer selectBulterId) {
        return selectBulterService.getdetail(selectBulterId);
    }
}
