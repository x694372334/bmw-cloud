package com.bmw.property.controller;

import com.bmw.property.model.Task;
import com.bmw.property.model.TaskVO;
import com.bmw.property.service.ITaskService;
import com.bmw.property.warpper.NeighborhoodWarpper;
import com.bmw.property.warpper.TaskWarpper;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * worktast控制器
 *
 * @author fengshuonan
 * @Date 2018-07-18 13:16:42
 */
@Controller
@RequestMapping("/task")
public class TaskController extends BaseController {

	private String PREFIX = "/property/worktask/";

    @Autowired
    private ITaskService taskService;

    /**
     * 跳转到task首页
     */
    @RequestMapping("")
    public String index(Model model) {
    	ShiroExt shiro = new ShiroExt();
    	Integer uId  = shiro.getUser().getId();
    	 model.addAttribute("uId",uId);
         LogObjectHolder.me().set(uId);
        return PREFIX + "task.html";
    }

    /**
     * 跳转到添加task
     */
    @RequestMapping("/task_add")
    public String taskAdd( Model model) {
    	ShiroExt shiro = new ShiroExt();
    	Integer eId  = shiro.getUser().geteId();
    	String eName=ConstantFactory.me().getEnterpriseInfoName(eId);
    	 model.addAttribute("eId",eId);
         LogObjectHolder.me().set(eId);
         model.addAttribute("eName",eName);
         LogObjectHolder.me().set(eName);
        return PREFIX + "task_add.html";
    }

    /**
     * 跳转到修改task
     */
    @RequestMapping("/task_update/{taskId}")
    public String taskUpdate(@PathVariable Integer taskId, Model model) {
        Task task = taskService.getdetail(taskId);
        String eName=ConstantFactory.me().getEnterpriseInfoName(task.geteId());
        model.addAttribute("eName",eName);
        model.addAttribute("item",task);
        LogObjectHolder.me().set(task);
        LogObjectHolder.me().set(eName);
        return PREFIX + "task_edit.html";
    }
    
    
    /**
     * 跳转到查看task
     */
    @RequestMapping("/task_view/{taskId}")
    public String taskView(@PathVariable Integer taskId, Model model) {
    	ShiroExt shiro = new ShiroExt();
    	Integer uId  = shiro.getUser().getId();
        Task task = taskService.getdetail(taskId);
        String eName=ConstantFactory.me().getEnterpriseInfoName(task.geteId());
        model.addAttribute("eName",eName);
        LogObjectHolder.me().set(eName);
        model.addAttribute("item",task);
        LogObjectHolder.me().set(task);
        Integer participate = 0;
        //判断登录人是否是负责人与参与人
        Integer tPrincipal = task.gettPrincipal();
        String tParticipationPer = task.gettParticipationPer();
        if(tPrincipal!=null&&tPrincipal.equals(uId)) {
        	participate = 1;
        }else {
        	 if(!"".equals(tParticipationPer)) {
             	String[] tParti = tParticipationPer.split(",");
             	boolean flag = Arrays.asList(tParti).contains(uId.toString());
             	if(flag) {
             		participate = 1;
             	}
             }
        }
        model.addAttribute("participate",participate);
        return PREFIX + "task_view.html";
    }

    /**
     * 获取task列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(TaskVO condition) {
    	ShiroExt shiro = new ShiroExt();
    	Integer eId = shiro.getUser().geteId();
    	condition.seteId(eId);
    	return super.warpObject(new TaskWarpper(taskService.findList(condition)));
    }

    /**
     * 新增task
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Task task) {
        taskService.add(task);
        return SUCCESS_TIP;
    }

    /**
     * 删除task
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer taskId) {
        taskService.del(taskId);
        return SUCCESS_TIP;
    }

    /**
     * 修改task
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Task task) {
        taskService.update(task);
        return SUCCESS_TIP;
    }

    /**
     * task详情
     */
    @RequestMapping(value = "/detail/{taskId}")
    @ResponseBody
    public Object detail(@PathVariable("taskId") Integer taskId) {
        return taskService.getdetail(taskId);
    }
    
}
