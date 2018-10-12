package com.bmw.flowable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.bmw.flowable.model.FlowConfigure;
import com.bmw.flowable.service.IFlowConfigureService;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * flowconfigure控制器
 *
 * @author fengshuonan
 * @Date 2018-08-24 09:45:50
 */
@Controller
@RequestMapping("/flowConfigure")
public class FlowConfigureController extends BaseController {

    private String PREFIX = "/flowable/flowConfigure/";

    @Autowired
    private IFlowConfigureService flowConfigureService;

    /**
     * 跳转到flowconfigure首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "flowConfigure.html";
    }

    /**
     * 跳转到添加flowconfigure
     */
    @RequestMapping("/flowConfigure_add")
    public String flowConfigureAdd() {
        return PREFIX + "flowConfigure_add.html";
    }

    /**
     * 跳转到修改flowconfigure
     */
    @RequestMapping("/flowConfigure_update/{flowConfigureId}")
    public String flowConfigureUpdate(@PathVariable Integer flowConfigureId, Model model) {
        FlowConfigure flowConfigure = flowConfigureService.getdetail(flowConfigureId);
        model.addAttribute("item",flowConfigure);
        LogObjectHolder.me().set(flowConfigure);
        return PREFIX + "flowConfigure_edit.html";
    }

    /**
     * 获取flowconfigure列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(FlowConfigure condition) {
        return flowConfigureService.findList(condition);
    }

    /**
     * 新增flowconfigure
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(FlowConfigure flowConfigure) {
        flowConfigureService.add(flowConfigure);
        return SUCCESS_TIP;
    }

    /**
     * 删除flowconfigure
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer flowConfigureId) {
        flowConfigureService.del(flowConfigureId);
        return SUCCESS_TIP;
    }

    /**
     * 修改flowconfigure
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(FlowConfigure flowConfigure) {
        flowConfigureService.update(flowConfigure);
        return SUCCESS_TIP;
    }

    /**
     * flowconfigure详情
     */
    @RequestMapping(value = "/detail/{flowConfigureId}")
    @ResponseBody
    public Object detail(@PathVariable("flowConfigureId") Integer flowConfigureId) {
        return flowConfigureService.getdetail(flowConfigureId);
    }
}
