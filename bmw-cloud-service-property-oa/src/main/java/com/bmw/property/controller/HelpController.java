package com.bmw.property.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.bmw.property.model.Help;
import com.bmw.property.service.IHelpService;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * help控制器
 *
 * @author fengshuonan
 * @Date 2018-07-09 10:10:02
 */
@Controller
@RequestMapping("/help")
public class HelpController extends BaseController {

    private String PREFIX = "/property/help/";

    @Autowired
    private IHelpService helpService;

    /**
     * 跳转到help首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "help.html";
    }
    
    /**
     * 跳转到help_tab首页
     */
    @RequestMapping("help_tab")
    public String tab() {
        return PREFIX + "help_tab.html";
    }

    /**
     * 跳转到添加help
     */
    @RequestMapping("/help_add")
    public String helpAdd() {
        return PREFIX + "help_add.html";
    }

    /**
     * 跳转到修改help
     */
    @RequestMapping("/help_update/{helpId}")
    public String helpUpdate(@PathVariable Integer helpId, Model model) {
        Help help = helpService.getdetail(helpId);
        model.addAttribute("item",help);
        LogObjectHolder.me().set(help);
        return PREFIX + "help_edit.html";
    }

    /**
     * 获取help列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Help condition) {
        return helpService.findList(condition);
    }

    /**
     * 新增help
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Help help) {
        helpService.add(help);
        return SUCCESS_TIP;
    }

    /**
     * 删除help
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer helpId) {
        helpService.del(helpId);
        return SUCCESS_TIP;
    }

    /**
     * 修改help
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Help help) {
        helpService.update(help);
        return SUCCESS_TIP;
    }

    /**
     * help详情
     */
    @RequestMapping(value = "/detail/{helpId}")
    @ResponseBody
    public Object detail(@PathVariable("helpId") Integer helpId) {
        return helpService.getdetail(helpId);
    }
    
    /**
     * 根据关键字查询
     */
    @RequestMapping(value = "/findByContent/{content}")
    @ResponseBody
    public Object findByContent(@PathVariable("content")  String content) {
    	Help condition = new Help();
    	condition.setContent(content);
        return helpService.findList(condition);
    }
}
