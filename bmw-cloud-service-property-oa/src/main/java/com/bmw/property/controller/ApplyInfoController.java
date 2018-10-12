package com.bmw.property.controller;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.bmw.property.model.ApplyInfo;
import com.bmw.property.service.IApplyInfoService;
import com.bmw.property.warpper.ApplyInfoWarpper;

/**
 * 审批申请单控制器
 *
 * @author fengshuonan
 * @Date 2018-07-19 11:07:05
 */
@Controller
@RequestMapping("/applyInfo")
public class ApplyInfoController extends BaseController {

	private String PREFIX = "/property/applyInfo/";

	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private ProcessEngine processEngine;
	@Autowired
	private IApplyInfoService applyInfoService;

	/**
	 * 跳转到审批申请单首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "applyInfo.html";
	}

	/**
	 * 跳转到添加审批申请单
	 */
	@RequestMapping("/applyInfo_add")
	public String applyInfoAdd() {
		return PREFIX + "applyInfo_add.html";
	}

	/**
	 * 跳转到修改审批申请单
	 */
	@RequestMapping("/applyInfo_update/{applyInfoId}")
	public String applyInfoUpdate(@PathVariable Integer applyInfoId, Model model) {
		ApplyInfo applyInfo = applyInfoService.getdetail(applyInfoId);
		model.addAttribute("item", applyInfo);
		LogObjectHolder.me().set(applyInfo);
		return PREFIX + "applyInfo_edit.html";
	}

	/**
	 * 跳转到修改审批申请单
	 */
	@RequestMapping("/applyInfo_detail/{applyInfoId}")
	public String applyInfoDetail(@PathVariable Integer applyInfoId, Model model) {
		ApplyInfo applyInfo = applyInfoService.getdetail(applyInfoId);
		model.addAttribute("item", applyInfo);
		LogObjectHolder.me().set(applyInfo);
		return PREFIX + "applyInfo_detail.html";
	}

	/**
	 * 跳转到修改审批申请单
	 */
	@RequestMapping("/applyInfo_approval/{applyInfoId}")
	public String applyInfoApproval(@PathVariable Integer applyInfoId, Model model) {
		ApplyInfo applyInfo = applyInfoService.getdetail(applyInfoId);
		model.addAttribute("item", applyInfo);
		LogObjectHolder.me().set(applyInfo);
		return PREFIX + "applyInfo_approval.html";
	}

	/**
	 * 跳转到事项审批页面
	 */
	@RequestMapping("/applyInfo_discounts_approval/{id}/{taskId}")
	public String billDiscountsView(@PathVariable String taskId, @PathVariable String id, Model model) {
		String[] businessId = id.split("\\.");
		ApplyInfo applyInfo = applyInfoService.getdetail(Integer.parseInt(businessId[1]));
		model.addAttribute("item", applyInfo);
		model.addAttribute("taskId", taskId);
		LogObjectHolder.me().set(applyInfo);
		LogObjectHolder.me().set(taskId);
		return PREFIX + "applyInfo_flowable_approval.html";
	}

	/**
	 * 获取审批申请单列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(String aType, String eaResult) {
		ShiroExt shiro = new ShiroExt();
		Integer userId = shiro.getUser().getId();
		return super.warpObject(new ApplyInfoWarpper(applyInfoService.findList(aType, eaResult, userId.toString())));
	}

	/**
	 * 新增审批申请单
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(ApplyInfo applyInfo) {
		String[] data = applyInfo.getaDetails().split(";");
		String text = data[2].substring(0, data[2].length() - 4);
		applyInfo.setaDetails(text);
		applyInfoService.add(applyInfo);
		return SUCCESS_TIP;
	}

	/**
	 * 删除审批申请单
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam Integer applyInfoId) {
		applyInfoService.del(applyInfoId);
		return SUCCESS_TIP;
	}

	/**
	 * 修改审批申请单
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(ApplyInfo applyInfo) {
		applyInfoService.update(applyInfo);
		return SUCCESS_TIP;
	}

	/**
	 * 审批申请单详情
	 */
	@RequestMapping(value = "/detail/{applyInfoId}")
	@ResponseBody
	public Object detail(@PathVariable("applyInfoId") Integer applyInfoId) {
		return applyInfoService.getdetail(applyInfoId);
	}

	/**
	 * 添加流程审批认证
	 *
	 * @param ea_user
	 *            用户Id
	 * @param money
	 * @param descption
	 *            描述
	 */
	@RequestMapping(value = "/flowabled_add")
	@ResponseBody
	public Object addExpense(ApplyInfo applyInfo) {
		applyInfo.setEaResult(3);
		applyInfoService.flowabled_add(applyInfo);
		return SUCCESS_TIP;
	}

	/**
	 * 批准
	 *
	 * @param taskId
	 *            任务ID
	 */
	@RequestMapping(value = "/flowabled_apply")
	@ResponseBody
	public Object apply(String taskId, String id) {
		applyInfoService.flowabled_apply(taskId, id);
		return SUCCESS_TIP;
	}

	/**
	 * 拒绝
	 */
	@ResponseBody
	@RequestMapping(value = "/flowabled_reject")
	public Object reject(String taskId, String id) {
		applyInfoService.flowabled_reject(taskId, id);
		return SUCCESS_TIP;
	}

}
