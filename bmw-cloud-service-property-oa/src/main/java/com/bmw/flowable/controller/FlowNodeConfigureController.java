package com.bmw.flowable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.bmw.flowable.model.FlowNodeConfigure;
import com.bmw.flowable.service.IFlowNodeConfigureService;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * flowNodeConfigure控制器
 *
 * @author fengshuonan
 * @Date 2018-08-24 13:23:17
 */
@Controller
@RequestMapping("/flowNodeConfigure")
public class FlowNodeConfigureController extends BaseController {

    private String PREFIX = "/flowable/flowNodeConfigure/";

    @Autowired
    private IFlowNodeConfigureService flowNodeConfigureService;

    /**
     * 跳转到flowNodeConfigure首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "flowNodeConfigure.html";
    }

    /**
     * 跳转到添加flowNodeConfigure
     */
    @RequestMapping("/flowNodeConfigure_add/{flowConfigureId}")
    public String flowNodeConfigureAdd(@PathVariable Integer flowConfigureId,Model model) {
    	model.addAttribute("flowConfigureId",flowConfigureId);
        return PREFIX + "flowNodeConfigure_add.html";
    }

    /**
     * 跳转到修改flowNodeConfigure
     */
    @RequestMapping("/flowNodeConfigure_update/{flowNodeConfigureId}")
    public String flowNodeConfigureUpdate(@PathVariable Integer flowNodeConfigureId, Model model) {
        FlowNodeConfigure flowNodeConfigure = flowNodeConfigureService.getdetail(flowNodeConfigureId);
        model.addAttribute("item",flowNodeConfigure);
        LogObjectHolder.me().set(flowNodeConfigure);
        return PREFIX + "flowNodeConfigure_edit.html";
    }

    /**
     * 获取flowNodeConfigure列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(FlowNodeConfigure condition) {
        return flowNodeConfigureService.findList(condition);
    }

    /**
     * 新增flowNodeConfigure
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(FlowNodeConfigure flowNodeConfigure) {
        flowNodeConfigureService.add(flowNodeConfigure);
        return SUCCESS_TIP;
    }

    /**
     * 删除flowNodeConfigure
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer flowNodeConfigureId) {
        flowNodeConfigureService.del(flowNodeConfigureId);
        return SUCCESS_TIP;
    }

    /**
     * 修改flowNodeConfigure
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(FlowNodeConfigure flowNodeConfigure) {
        flowNodeConfigureService.update(flowNodeConfigure);
        return SUCCESS_TIP;
    }

    /**
     * flowNodeConfigure详情
     */
    @RequestMapping(value = "/detail/{flowNodeConfigureId}")
    @ResponseBody
    public Object detail(@PathVariable("flowNodeConfigureId") Integer flowNodeConfigureId) {
        return flowNodeConfigureService.getdetail(flowNodeConfigureId);
    }
}
