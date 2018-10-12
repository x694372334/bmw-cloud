package com.bmw.medical.controller;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.bmw.medical.model.Schedule;
import com.bmw.medical.model.ScheduleDetail;
import com.bmw.medical.service.IScheduleDetailService;
import com.bmw.medical.service.IScheduleService;

/**
 * 排班明细控制器
 *
 * @author fengshuonan
 * @Date 2018-06-07 13:18:42
 */
@Controller
@RequestMapping("/scheduleDetail")
public class ScheduleDetailController extends BaseController {

	private String PREFIX = "/medical/scheduleDetail/";

	@Autowired
	private IScheduleDetailService scheduleDetailService;
	
	 @Autowired
	    private IScheduleService scheduleService;

	/**
	 * 跳转到排班明细首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "scheduleDetail.html";
	}

	/**
	 * 跳转到添加排班明细
	 */
	@RequestMapping("/scheduleDetail_add")
	public String scheduleDetailAdd() {
		return PREFIX + "scheduleDetail_add.html";
	}
	
	/**
	 * 跳转到添加排班明细
	 */
	@RequestMapping("/scheduleDetail_add/{scheduleId}")
	public String scheduleDetailAdd(@PathVariable Integer scheduleId,Model model) {
		Schedule schedule = scheduleService.getdetail(scheduleId);
		if(null !=schedule) {
			ScheduleDetail scheduleDetail =new ScheduleDetail();
			scheduleDetail.setMedicalScheduleId(schedule.getId());
			scheduleDetail.setMedicalDepartmentCode(schedule.getMedicalDepartmentCode());
			scheduleDetail.setDepartmentName(schedule.getDepartmentName());
			scheduleDetail.setMedicalDoctorCode(schedule.getMedicalDoctorCode());
			scheduleDetail.setDoctorName(schedule.getDoctorName());
			model.addAttribute("item", scheduleDetail);
			String formatDate = DateFormatUtils.format(schedule.getScheduleDate(), "yyyy-MM-dd HH:mm:ss");
			model.addAttribute("scheduleDate", formatDate);
			model.addAttribute("approach", schedule.getApproach());
		}
		return PREFIX + "scheduleDetail_add.html";
	}

	/**
	 * 跳转到修改排班明细
	 */
	@RequestMapping("/scheduleDetail_update/{scheduleDetailId}")
	public String scheduleDetailUpdate(@PathVariable Integer scheduleDetailId, Model model) {
		ScheduleDetail scheduleDetail = scheduleDetailService.getdetail(scheduleDetailId);
		model.addAttribute("item", scheduleDetail);
		
		Schedule schedule = scheduleService.getdetail(scheduleDetail.getMedicalScheduleId());
		model.addAttribute("approach", schedule.getApproach());
		LogObjectHolder.me().set(scheduleDetail);
		return PREFIX + "scheduleDetail_edit.html";
	}

	/**
	 * 获取排班明细列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(ScheduleDetail scheduleDetail) {
		return scheduleDetailService.findList(scheduleDetail);
	}
	
	@RequestMapping(value = "/allList")
	@ResponseBody
	public Object allList(ScheduleDetail scheduleDetail) {
		return scheduleDetailService.findAllList(scheduleDetail);
	}

	/**
	 * 新增排班明细
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(ScheduleDetail scheduleDetail) {
		scheduleDetailService.add(scheduleDetail);
		return SUCCESS_TIP;
	}

	/**
	 * 删除排班明细
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam Integer scheduleDetailId) {
		scheduleDetailService.del(scheduleDetailId);
		return SUCCESS_TIP;
	}

	/**
	 * 修改排班明细
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(ScheduleDetail scheduleDetail) {
		scheduleDetailService.update(scheduleDetail);
		return SUCCESS_TIP;
	}

	/**
	 * 排班明细详情
	 */
	@RequestMapping(value = "/detail/{scheduleDetailId}")
	@ResponseBody
	public Object detail(@PathVariable("scheduleDetailId") Integer scheduleDetailId) {
		return scheduleDetailService.getdetail(scheduleDetailId);
	}
}
