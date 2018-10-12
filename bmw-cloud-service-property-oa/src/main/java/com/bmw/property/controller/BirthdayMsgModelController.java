package com.bmw.property.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.utils.ExcelUtils;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.bmw.property.model.BirthdayMsgHis;
import com.bmw.property.model.BirthdayMsgModel;
import com.bmw.property.model.BirthdayMsgSendSetting;
import com.bmw.property.service.IBirthdayMsgModelService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-07-12 13:47:11
 */
@Controller
@RequestMapping("/birthdayMsgModel")
public class BirthdayMsgModelController extends BaseController {
	
    private String PREFIX = "/property/birthdayMsgModel/";

    @Autowired
    private IBirthdayMsgModelService birthdayMsgModelService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "birthdayMsgModel.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/birthdayMsgModel_add")
    public String birthdayMsgModelAdd() {
        return PREFIX + "birthdayMsgModel_add.html";
    }
    /**
     * 跳转到添加
     */
    @RequestMapping("/birthdayMsgSetting_add")
    public String birthdayMsgSettingAdd(Model model) {
    	Map<String,Integer> paramMap=new HashMap<>();
    	paramMap.put("pageNum", 1);
    	paramMap.put("pageSize",99999999);
    	paramMap.put("eId", ShiroKit.getUser().geteId());
    	JSONObject json=birthdayMsgModelService.listByParam(paramMap);
    	model.addAttribute("items", json.getJSONArray("result"));
    	
    	BirthdayMsgSendSetting defaultSetting=new BirthdayMsgSendSetting();
    	//先设置默认项
    	defaultSetting.setSendDateMode(Short.valueOf("1"));
    	defaultSetting.setSendMode(Short.valueOf("2"));
    	defaultSetting.setSendTime("9:30");
    	BirthdayMsgSendSetting setting=null;
    	setting=birthdayMsgModelService.getMsgSettingByEid(ShiroKit.getUser().geteId());
    	if(setting!=null) {
    		model.addAttribute("setting", setting);
    	}else {
    		model.addAttribute("setting", defaultSetting);
    	}    		   	
        return PREFIX + "birthdayMsgModel_setting.html";
    }
    
    /**
     * 跳转到修改
     */
    @RequestMapping("/birthdayMsgModel_update/{birthdayMsgModelId}")
    public String birthdayMsgModelUpdate(@PathVariable Integer birthdayMsgModelId, Model model) {
        BirthdayMsgModel birthdayMsgModel = birthdayMsgModelService.selectById(birthdayMsgModelId);
        model.addAttribute("item",birthdayMsgModel);
        LogObjectHolder.me().set(birthdayMsgModel);
        return PREFIX + "birthdayMsgModel_edit.html";
    }
    
    /**
     * 跳转到修改
     */
    @RequestMapping("/birthdayMsgHis_Export")
    public String birthdayMsgHisExport( Model model) {
        model.addAttribute("eId",ShiroKit.getUser().geteId());
        return PREFIX + "birthdayMsgModel_exportCondition.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list() {
    	Page<BirthdayMsgModel> page=new PageFactory<BirthdayMsgModel>().defaultPage();
    	Map<String,Integer> paramMap=new HashMap<>();
    	paramMap.put("pageNum", page.getCurrent());
    	paramMap.put("pageSize",page.getSize());
    	paramMap.put("eId", ShiroKit.getUser().geteId());
    	JSONObject json=birthdayMsgModelService.listByParam(paramMap);
    	page.setTotal(json.getInteger("total"));
    	page.setRecords(json.getJSONArray("result").toJavaList(BirthdayMsgModel.class));
        return super.packForBT(page);
    }

    /**
     * 新增
     */
    @RequestMapping(value = "/addModel")
    @ResponseBody
    public Object addModel(BirthdayMsgModel birthdayMsgModel) {
    	ShiroUser shiroUser = ShiroKit.getUser();
    	birthdayMsgModel.seteId(ShiroKit.getUser().geteId());
    	birthdayMsgModel.setCreateManId(shiroUser.getId());
    	birthdayMsgModel.setCreateMan(shiroUser.getName());
    	birthdayMsgModel.setCreateTime(new Date());
        birthdayMsgModelService.insert(birthdayMsgModel);
        return SUCCESS_TIP;
    }
    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BirthdayMsgModel birthdayMsgModel) {
    	ShiroUser shiroUser = ShiroKit.getUser();
    	birthdayMsgModel.setEditMan(shiroUser.getName());
    	birthdayMsgModel.setEditManId(shiroUser.getId());
        boolean flag=birthdayMsgModelService.updateById(birthdayMsgModel);
        if(flag) {
    		return SUCCESS_TIP;
    	}else {
    		return new ErrorTip(500,"编辑失败");
    	}
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer birthdayMsgModelId) {
    	ShiroUser shiroUser = ShiroKit.getUser();
    	BirthdayMsgModel birthdayMsgModel=new BirthdayMsgModel();
    	birthdayMsgModel.setEditMan(shiroUser.getName());
    	birthdayMsgModel.setEditManId(shiroUser.getId());
    	birthdayMsgModel.setId(birthdayMsgModelId);
    	boolean flag=birthdayMsgModelService.deleteById(birthdayMsgModel);
    	if(flag) {
    		return SUCCESS_TIP;
    	}else {
    		return new ErrorTip(500,"删除失败");
    	}
        
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{birthdayMsgModelId}")
    @ResponseBody
    public Object detail(@PathVariable("birthdayMsgModelId") Integer birthdayMsgModelId) {
        return birthdayMsgModelService.selectById(birthdayMsgModelId);
    }
    
    
    /**
     * 修改短信发送配置
     */
    @RequestMapping(value = "/modifySetting")
    @ResponseBody
    public Object modifySetting(BirthdayMsgSendSetting setting) {
    	ShiroUser shiroUser = ShiroKit.getUser();
    	setting.setCreateMan(shiroUser.getName());
    	setting.setCreateManId(shiroUser.getId());
    	setting.setEditMan(shiroUser.getName());
    	setting.setEditManId(shiroUser.getId());
    	setting.seteId(ShiroKit.getUser().geteId());
    	boolean flag=birthdayMsgModelService.modifySetting(setting);
    	if(flag) {
    		return SUCCESS_TIP;
    	}else {
    		return new ErrorTip(500,"更新失败");
    	}
    }
    /**
     * 查询发送历史
     */
    @RequestMapping(value = "/hislist")
    @ResponseBody
    public Object hislist(BirthdayMsgHis his) {
    	Page<BirthdayMsgHis> page=new PageFactory<BirthdayMsgHis>().defaultPage();
    	Map<String,Object> paramMap=new HashMap<>();
    	paramMap.put("pageNum", page.getCurrent());
    	paramMap.put("pageSize",page.getSize());
    	paramMap.put("ownerName", his.getOwnerName());
    	paramMap.put("eId",ShiroKit.getUser().geteId());
    	JSONObject json=birthdayMsgModelService.hislistByParam(paramMap);
    	page.setTotal(json.getInteger("total"));
    	page.setRecords(json.getJSONArray("result").toJavaList(BirthdayMsgHis.class));
        return super.packForBT(page);
    }
    @RequestMapping(value = "/exportList")
    public void exportList(@RequestParam(required=false) String beginDate,
				    	   @RequestParam(required=false) String endDate,
				    	   @RequestParam(required=false) String ownerName,
				    		HttpServletResponse response) throws IOException {
    	Map<String,Object> paramMap=new HashMap<>();
    	paramMap.put("beginDate", beginDate);
    	paramMap.put("endDate", endDate);
    	paramMap.put("ownerName", ownerName);
    	paramMap.put("eId",ShiroKit.getUser().geteId());
    	List<String> headers = new ArrayList<>();
		headers.add("业主姓名");
		headers.add("发送内容");
		headers.add("发送时间");
		List<Map<String,Object>> rows=new ArrayList<Map<String,Object>>();
		rows=birthdayMsgModelService.export(paramMap);
		ExcelUtils.exportExcel(response, "短信发送列表", "短信发送列表", headers, rows);
    }
    
}
