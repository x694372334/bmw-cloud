package com.bmw.flowable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.bmw.flowable.model.TaskVO;
import com.bmw.flowable.service.IFlowableService;
import com.bmw.flowable.service.IRuTaskService;
import com.bmw.flowable.warpper.TaskWarpper;


/**
 * services控制器
 *
 * @author fengshuonan
 * @Date 2018-07-17 10:34:48
 */
@Controller
@RequestMapping("/ruTask")
public class RuTaskController extends BaseController {
	
    private String PREFIX = "/flowable/";
    
    @Autowired
    private IRuTaskService ruTaskService;
    
    @Autowired
    private IFlowableService flowableService;

    /**
     * 跳转到services首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "ruTask.html";
    }
    
    /**
     * 获取services列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(TaskVO condition) {
        return super.warpObject(new TaskWarpper(ruTaskService.findList(condition.getName())));
    }
    
    /**
     * 查看流程图
     */
    @RequestMapping(value = "/printProcessImage/{taskId}")
    @ResponseBody
    public void printProcessImage(@PathVariable String taskId ) {
         try {
        	 ruTaskService.printProcessImage(taskId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * 删除流程实例
     */
    @RequestMapping(value = "/deletePro/{procInstId}")
    @ResponseBody
    public void deletePro(@PathVariable String procInstId ) {
        	 ruTaskService.deletePro(procInstId);
    }

}
