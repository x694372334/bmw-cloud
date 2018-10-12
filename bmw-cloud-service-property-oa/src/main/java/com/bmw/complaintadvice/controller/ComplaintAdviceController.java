package com.bmw.complaintadvice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.bmw.complaintadvice.model.ComplaintAdvice;
import com.bmw.complaintadvice.service.IComplaintAdviceService;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 投诉建议控制器
 *
 * @author fengshuonan
 * @Date 2018-06-21 14:21:49
 */
@Controller
@RequestMapping("/complaintAdvice")
public class ComplaintAdviceController extends BaseController {

    private String PREFIX = "/property/complaintAdvice/";

    @Autowired
    private IComplaintAdviceService complaintAdviceService;

    /**
     * 跳转到投诉建议首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "complaintAdvice.html";
    }

    /**
     * 跳转到添加投诉建议
     */
    @RequestMapping("/complaintAdvice_add")
    public String complaintAdviceAdd() {
        return PREFIX + "complaintAdvice_add.html";
    }

    /**
     * 跳转到修改投诉建议
     */
    @RequestMapping("/complaintAdvice_update/{complaintAdviceId}")
    public String complaintAdviceUpdate(@PathVariable Integer complaintAdviceId, Model model) {
        ComplaintAdvice complaintAdvice = complaintAdviceService.getdetail(complaintAdviceId);
        model.addAttribute("item",complaintAdvice);
        LogObjectHolder.me().set(complaintAdvice);
        return PREFIX + "complaintAdvice_edit.html";
    }
    
    /**
     * 跳转到修改投诉建议（不可编辑）
     */
    @RequestMapping("/complaintAdvice_view/{complaintAdviceId}")
    public String complaintAdviceView(@PathVariable Integer complaintAdviceId, Model model) {
        ComplaintAdvice complaintAdvice = complaintAdviceService.getdetail(complaintAdviceId);
        model.addAttribute("item",complaintAdvice);
        LogObjectHolder.me().set(complaintAdvice);
        return PREFIX + "complaintAdvice_view.html";
    }
    

    /**
     * 获取投诉建议列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(ComplaintAdvice condition) {
        return complaintAdviceService.findList(condition);
    }

    /**
     * 新增投诉建议
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ComplaintAdvice complaintAdvice) {
        complaintAdviceService.add(complaintAdvice);
        return SUCCESS_TIP;
    }

    /**
     * 删除投诉建议
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer complaintAdviceId) {
        complaintAdviceService.del(complaintAdviceId);
        return SUCCESS_TIP;
    }

    /**
     * 修改投诉建议
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ComplaintAdvice complaintAdvice) {
    	complaintAdvice.setReplyTime(new Date());
        complaintAdviceService.update(complaintAdvice);
        return SUCCESS_TIP;
    }

    /**
     * 投诉建议详情
     */
    @RequestMapping(value = "/detail/{complaintAdviceId}")
    @ResponseBody
    public Object detail(@PathVariable("complaintAdviceId") Integer complaintAdviceId) {
        return complaintAdviceService.getdetail(complaintAdviceId);
    }
}
