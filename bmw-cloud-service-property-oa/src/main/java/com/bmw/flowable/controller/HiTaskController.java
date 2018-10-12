package com.bmw.flowable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import org.springframework.beans.factory.annotation.Autowired;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.bmw.flowable.model.TaskVO;
import com.bmw.flowable.service.IHiTaskService;
import com.bmw.flowable.warpper.TaskWarpper;
import com.bmw.property.model.Services;
import org.springframework.ui.Model;

/**
 * services控制器
 *
 * @author fengshuonan
 * @Date 2018-07-17 10:34:48
 */
@Controller
@RequestMapping("/hiTask")
public class HiTaskController extends BaseController {
	
    private String PREFIX = "/flowable/";
    
    @Autowired
    private IHiTaskService hiTaskService;

    /**
     * 跳转到services首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "hiTask.html";
    }
    
    /**
     * 获取流程历史记录
     */
    @RequestMapping(value = "/historyView/{pid}")
    public String historyView(@PathVariable String pid,Model model) {
    	model.addAttribute("pid", pid);
        return PREFIX + "history.html";
    }
    
    /**
     * 获取services列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(TaskVO condition) {
        return super.warpObject(new TaskWarpper(hiTaskService.findList(condition.getName())));
    }

    /**
     * 获取流程历史记录
     */
    @RequestMapping(value = "/history/{pid}")
    @ResponseBody
    public Object history(@PathVariable String pid) {
        return hiTaskService.history(pid);
    }
}
