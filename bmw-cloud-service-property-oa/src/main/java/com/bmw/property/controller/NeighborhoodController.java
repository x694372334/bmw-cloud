package com.bmw.property.controller;

import com.alibaba.fastjson.JSONArray;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.beetl.ShiroExt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.bmw.medical.warpper.AreaWarpper;
import com.bmw.property.model.Neighborhood;
import com.bmw.property.service.INeighborhoodService;
import com.bmw.property.warpper.NeighborhoodWarpper;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 小区信息控制器
 *
 * @author fengshuonan
 * @Date 2018-06-19 13:29:57
 */
@Controller
@RequestMapping("/neighborhood")
public class NeighborhoodController extends BaseController {

    private String PREFIX = "/property/neighborhood/";

    @Autowired
    private INeighborhoodService neighborhoodService;

    /**
     * 跳转到小区信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "neighborhoodInfo.html";
    }

    /**
     * 跳转到添加小区信息
     */
    @RequestMapping("/neighborhoodInfo_add")
    public String neighborhoodInfoAdd(Model model) {
    	//之前管理员的方法
//    	ShiroExt shiro = new ShiroExt();
//    	int userId = shiro.getUser().getId();
//    	System.out.println(shiro.getUser().getId()+"-------------------------------=-=");
//    	if(userId==1) {
//    		return PREFIX + "neighborhoodInfo_adminAdd.html";
//    	}else {
//    		return PREFIX + "neighborhoodInfo_add.html";
//    	}
    	Integer eId = (Integer) super.getSession().getAttribute("eId");
    	Neighborhood neighborhoodInfo = neighborhoodService.findEid(eId);
    	JSONArray jsonArray = neighborhoodService.findList(eId,null);
    	List<Neighborhood> list = jsonArray.toJavaList(Neighborhood.class);
    	Integer num = list.size();
    	  model.addAttribute("item",neighborhoodInfo);
    	  model.addAttribute("data",num);
          LogObjectHolder.me().set(neighborhoodInfo);
    	return PREFIX + "neighborhoodInfo_add.html";
    }

    /**
     * 注册一个类型解析器
     * @param binder
     */
    @org.springframework.web.bind.annotation.InitBinder
    public void InitBinder(WebDataBinder binder){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    /**
     * 跳转到修改小区信息
     */
    @RequestMapping("/neighborhoodInfo_update/{neighborhoodInfoId}")
    public String neighborhoodInfoUpdate(@PathVariable Integer neighborhoodInfoId, Model model) {
        Neighborhood neighborhoodInfo = neighborhoodService.getdetail(neighborhoodInfoId);
        model.addAttribute("item",neighborhoodInfo);
        LogObjectHolder.me().set(neighborhoodInfo);
        return PREFIX + "neighborhoodInfo_edit.html";
    }
    
    /**
     * 跳转到详情查看小区信息
     */
    @RequestMapping("/neighborhoodInfo_detail/{neighborhoodInfoId}")
    public String neighborhoodInfoDetail(@PathVariable Integer neighborhoodInfoId, Model model) {
        Neighborhood neighborhoodInfo = neighborhoodService.getdetail(neighborhoodInfoId);
        model.addAttribute("item",neighborhoodInfo);
        LogObjectHolder.me().set(neighborhoodInfo);
        
        
        return PREFIX + "neighborhoodInfo_detail.html";
    }

    /**
     * 获取小区信息列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    			Integer eId = (Integer)super.getSession().getAttribute("eId");
    	return super.warpObject(new NeighborhoodWarpper(neighborhoodService.findList(eId,condition)));
    }

    /**
     * 新增小区信息
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Neighborhood neighborhoodInfo) {
    		Integer data = (Integer)super.getSession().getAttribute("eId");
        	neighborhoodInfo.seteId(data);
	        neighborhoodService.add(neighborhoodInfo);
	        return SUCCESS_TIP;
    }
    

    /**
     * 删除小区信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer neighborhoodInfoId) {
    	int data = neighborhoodService.del(neighborhoodInfoId);
    	if(data==1) {
    		 return SUCCESS_TIP;
    	}else {
    		return "240";
    	}
    }

    /**
     * 修改小区信息
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Neighborhood neighborhoodInfo) {
    	neighborhoodService.update(neighborhoodInfo);
        return SUCCESS_TIP;
    }

    /**
     * 小区信息详情
     */
    @RequestMapping(value = "/detail/{neighborhoodInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("neighborhoodInfoId") Integer neighborhoodInfoId) {
        return neighborhoodService.getdetail(neighborhoodInfoId);
    }
    
    /**
     * 楼宇管理详情
     */
    @RequestMapping(value = "/findByAddress/{data}")
    @ResponseBody
    public Object findByAddress(@PathVariable("data") String data) {
        return neighborhoodService.findAddress(data);
    }
    
    /**
     * 楼宇管理详情
     */
    @RequestMapping(value = "/findEditAddress/{data}")
    @ResponseBody
    public Object findEditAddress(@PathVariable("data") String data) {
        return neighborhoodService.findAddress(data);
    }
    
    /**
     * 查询小区管理
     */
    @RequestMapping(value = "/findEid")
    @ResponseBody
    public Object findNeighbor() {
    	Integer eId = (Integer) super.getSession().getAttribute("eId");
        return neighborhoodService.findEid(eId);
    }
    
}
