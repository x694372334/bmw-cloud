package com.bmw.property.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.bmw.property.model.OFeedback;
import com.bmw.property.service.IOFeedbackService;

/**
 * 意见反馈控制器
 *
 * @author fengshuonan
 * @Date 2018-07-31 10:22:40
 */
@Controller
@RequestMapping("/oFeedback")
public class OFeedbackController extends BaseController {

    private String PREFIX = "/property/oFeedback/";

    @Autowired
    private IOFeedbackService oFeedbackService;

    /**
     * 跳转到意见反馈首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "oFeedback.html";
    }

    /**
     * 跳转到添加意见反馈
     */
    @RequestMapping("/oFeedback_add")
    public String oFeedbackAdd() {
        return PREFIX + "oFeedback_add.html";
    }

    /**
     * 跳转到修改意见反馈
     */
    @RequestMapping("/oFeedback_update/{oFeedbackId}")
    public String oFeedbackUpdate(@PathVariable Integer oFeedbackId, Model model) {
        OFeedback oFeedback = oFeedbackService.getdetail(oFeedbackId);
        model.addAttribute("item",oFeedback);
        LogObjectHolder.me().set(oFeedback);
        return PREFIX + "oFeedback_edit.html";
    }
    
    /**
     * 跳转到回复意见反馈
     */
    @RequestMapping("/oFeedback_reply/{oFeedbackId}")
    public String oFeedbackReply(@PathVariable Integer oFeedbackId, Model model) {
        OFeedback oFeedback = oFeedbackService.getdetail(oFeedbackId);
        model.addAttribute("item",oFeedback);
        LogObjectHolder.me().set(oFeedback);
        return PREFIX + "oFeedback_reply.html";
    }

    /**
     * 获取意见反馈列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(OFeedback condition) {
    	Page<OFeedback> page=new PageFactory<OFeedback>().defaultPage();
    	Map<String,Integer> paramMap=new HashMap<>();
    	paramMap.put("pageNum", page.getCurrent());
    	paramMap.put("pageSize",page.getSize());
//    	paramMap.put("oId",ShiroKit.getUser().getId());
    	paramMap.put("eId",ShiroKit.getUser().geteId());
    	JSONObject json=oFeedbackService.findList(paramMap);
    	page.setTotal(json.getInteger("total"));
    	page.setRecords(json.getJSONArray("result").toJavaList(OFeedback.class));
        return super.packForBT(page);
    }
    /**
     * 新增意见反馈
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(OFeedback oFeedback) {
        oFeedbackService.add(oFeedback);
        return SUCCESS_TIP;
    }

    /**
     * 删除意见反馈
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer oFeedbackId) {
        oFeedbackService.del(oFeedbackId);
        return SUCCESS_TIP;
    }

    /**
     * 修改意见反馈
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(OFeedback oFeedback) {
        oFeedbackService.update(oFeedback);
        return SUCCESS_TIP;
    }

    /**
     * 意见反馈详情
     */
    @RequestMapping(value = "/detail/{oFeedbackId}")
    @ResponseBody
    public Object detail(@PathVariable("oFeedbackId") Integer oFeedbackId) {
        return oFeedbackService.getdetail(oFeedbackId);
    }
}
