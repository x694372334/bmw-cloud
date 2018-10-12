package com.bmw.flowable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.bmw.flowable.model.FlowNodeCondition;
import com.bmw.flowable.service.IFlowNodeConditionService;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * flownodecondition控制器
 *
 * @author fengshuonan
 * @Date 2018-08-24 15:25:38
 */
@Controller
@RequestMapping("/flowNodeCondition")
public class FlowNodeConditionController extends BaseController {

    private String PREFIX = "/flowable/flowNodeCondition/";

    @Autowired
    private IFlowNodeConditionService flowNodeConditionService;

    /**
     * 跳转到flownodecondition首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "flowNodeCondition.html";
    }

    /**
     * 跳转到添加flownodecondition
     */
    @RequestMapping("/flowNodeCondition_add/{flowNodeConfigureId}")
    public String flowNodeConditionAdd(@PathVariable Integer flowNodeConfigureId, Model model) {
    	ShiroExt shiro = new ShiroExt();
    	Integer parentEId = shiro.getUser().getParentEId();
    	String parentEName = shiro.getUser().getParentEName();
    	 model.addAttribute("parentEId",parentEId);
    	 model.addAttribute("parentEName",parentEName);
    	FlowNodeCondition condition =new FlowNodeCondition();
    	condition.setFlowNodeConfigureId(flowNodeConfigureId);
    	condition.setParentEId(parentEId);
    	if(flowNodeConditionService.findList(condition).size()>0) {
    		JSONObject rlt = (JSONObject)flowNodeConditionService.findList(condition).get(0);
    		  FlowNodeCondition flowNodeCondition = flowNodeConditionService.getdetail(rlt.getInteger("id"));
    	      model.addAttribute("item",flowNodeCondition);
    	      LogObjectHolder.me().set(flowNodeCondition);
    	      return PREFIX + "flowNodeCondition_edit.html";
    	}
    	model.addAttribute("flowNodeConfigureId",flowNodeConfigureId);
        return PREFIX + "flowNodeCondition_add.html";
    }

    /**
     * 跳转到修改flownodecondition
     */
    @RequestMapping("/flowNodeCondition_update/{flowNodeConditionId}")
    public String flowNodeConditionUpdate(@PathVariable Integer flowNodeConditionId, Model model) {
        FlowNodeCondition flowNodeCondition = flowNodeConditionService.getdetail(flowNodeConditionId);
        model.addAttribute("item",flowNodeCondition);
        LogObjectHolder.me().set(flowNodeCondition);
        return PREFIX + "flowNodeCondition_edit.html";
    }

    /**
     * 获取flownodecondition列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(FlowNodeCondition condition) {
        return flowNodeConditionService.findList(condition);
    }

    /**
     * 新增flownodecondition
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(FlowNodeCondition flowNodeCondition) {
        flowNodeConditionService.add(flowNodeCondition);
        return SUCCESS_TIP;
    }

    /**
     * 删除flownodecondition
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer flowNodeConditionId) {
        flowNodeConditionService.del(flowNodeConditionId);
        return SUCCESS_TIP;
    }

    /**
     * 修改flownodecondition
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(FlowNodeCondition flowNodeCondition) {
        flowNodeConditionService.update(flowNodeCondition);
        return SUCCESS_TIP;
    }

    /**
     * flownodecondition详情
     */
    @RequestMapping(value = "/detail/{flowNodeConditionId}")
    @ResponseBody
    public Object detail(@PathVariable("flowNodeConditionId") Integer flowNodeConditionId) {
        return flowNodeConditionService.getdetail(flowNodeConditionId);
    }
}
