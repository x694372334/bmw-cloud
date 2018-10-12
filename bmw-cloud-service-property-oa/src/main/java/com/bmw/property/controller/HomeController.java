package com.bmw.property.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bmw.property.service.IRepairsFormService;

/**
 * OA系统首页控制器
 *
 * @author liuwsh
 * @Date 2018-08-06 14:35:57
 */
@Controller
@RequestMapping("/home")
public class HomeController {
	private String PREFIX = "/home/";

	// 报事报修服务
	@Autowired
	private IRepairsFormService repairsFormService;

	/**
	 * 跳转到卡片页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("card")
	public String card(Model model) {
		return PREFIX + "card.html";
	}

	/**
	 * 跳转到已办信息页面
	 */
	@RequestMapping(value = "done")
	public String done() {
		return PREFIX + "hiTask.html";
	}

	/**
	 * 获取待办信息
	 */
	@RequestMapping(value = "todo")
	public String todo() {
		return PREFIX + "ruTask.html";
	}

	/**
	 * 收入统计
	 */
	@RequestMapping("incomeStatistics")
	public String incomeStatistics(Model model) {
		// 获取当月处理报修数量
		model.addAttribute("monthCount", getMonthCount());
		// 获取未完成报修单数量
		model.addAttribute("uncompletedCount", getUncompletedCount());
		return PREFIX + "incomeStatistics.html";
	}

	/**
	 * 跳转到年度收入统计报表页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("getAnnualRevenue")
	public String getAnnualRevenue(Model model) {
		return PREFIX + "annualRevenue.html";
	}

	/**
	 * 跳转到物业缴费比例报表页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("getPayProportion")
	public String getPayProportion(Model model) {
		return PREFIX + "payProportion.html";
	}

	private Integer getUncompletedCount() {
		return repairsFormService.getUncompletedCount();
	}

	private Integer getMonthCount() {
		return repairsFormService.getMonthCount();
	}

}
