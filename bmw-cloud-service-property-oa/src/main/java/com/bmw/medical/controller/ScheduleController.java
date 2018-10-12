package com.bmw.medical.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.medical.model.Area;
import com.bmw.medical.model.Department;
import com.bmw.medical.model.Hospital;
import com.bmw.medical.model.Schedule;
import com.bmw.medical.service.IDepartmentService;
import com.bmw.medical.service.IScheduleService;
import com.bmw.medical.warpper.ScheduleWarpper;
import com.bmw.property.model.CommunityBulletin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 排班控制器
 *
 * @author fengshuonan
 * @Date 2018-06-07 13:18:32
 */
@Controller
@RequestMapping("/schedule")
public class ScheduleController extends BaseController {

    private String PREFIX = "/medical/schedule/";

    @Autowired
    private IScheduleService scheduleService;
    
 	@Autowired
 	private IDepartmentService departmentService;

    /**
     * 跳转到排班首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "schedule.html";
    }

    /**
     * 跳转到添加排班
     */
    @RequestMapping("/schedule_add/{medicalDepartmentCode}/{medicalDoctorCode}")
    public String scheduleAdd(@PathVariable String medicalDepartmentCode, @PathVariable String medicalDoctorCode,Model model) {
    	Department department = departmentService.findByCode(medicalDepartmentCode);
    	if(null != department) {
    		model.addAttribute("medicalDepartmentName",department.getName());
    		model.addAttribute("medicalDepartmentCode",medicalDepartmentCode);
    	}
    	model.addAttribute("medicalDoctorCode",medicalDoctorCode);
        return PREFIX + "schedule_add.html";
    }

    /**
     * 跳转到修改排班
     */
    @RequestMapping("/schedule_update/{scheduleId}")
    public String scheduleUpdate(@PathVariable Integer scheduleId, Model model) {
        Schedule schedule = scheduleService.getdetail(scheduleId);
        model.addAttribute("item",schedule);
        Department department = departmentService.findByCode(schedule.getMedicalDepartmentCode());
		model.addAttribute("medicalDepartmentName", department.getName());
        LogObjectHolder.me().set(schedule);
        return PREFIX + "schedule_edit.html";
    }

    /**
     * 获取排班列表
     */
    @RequestMapping(value = "/list") 
    @ResponseBody
    public Object list(Schedule schedule) {
    	return scheduleService.findList(schedule);
    }

    /**
     * 新增排班
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Schedule schedule) {
        scheduleService.add(schedule);
        return SUCCESS_TIP;
    }

    /**
     * 删除排班
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer scheduleId) {
        scheduleService.del(scheduleId);
        return SUCCESS_TIP;
    }

    /**
     * 修改排班
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Schedule schedule) {
        scheduleService.update(schedule);
        return SUCCESS_TIP;
    }

    /**
     * 排班详情
     */
    @RequestMapping(value = "/detail/{scheduleId}")
    @ResponseBody
    public Object detail(@PathVariable("scheduleId") Integer scheduleId) {
        return scheduleService.getdetail(scheduleId);
    }
    
    @RequestMapping(value = "/getTreeList")
    @ResponseBody
    public List<ZTreeNode> getTreeList() {
        return scheduleService.getTreeList();
    }
}
