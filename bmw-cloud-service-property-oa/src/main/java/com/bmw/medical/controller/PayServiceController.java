package com.bmw.medical.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.bmw.medical.model.PayService;
import com.bmw.medical.model.PayType;
import com.bmw.medical.service.IPayServiceService;
import com.bmw.medical.service.IPayTypeService;

/**
 * 收费项目管理控制器
 *
 * @author liuwsh
 * @Date 2018-09-18 13:54:40
 */
@Controller
@RequestMapping("/payService")
public class PayServiceController extends BaseController {

	private String PREFIX = "/medical/payService/";

	@Autowired
	private IPayServiceService payServiceService;

	@Autowired
	private IPayTypeService payTypeService;

	/**
	 * 跳转到收费项目分类首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "payService.html";
	}

	/**
	 * 跳转到添加收费项目分类
	 */
	@RequestMapping("/payService_add")
	public String payServiceAdd() {
		return PREFIX + "payService_add.html";
	}

	/**
	 * 跳转到修改收费项目分类
	 */
	@RequestMapping("/payService_update/{payServiceId}")
	public String payServiceUpdate(@PathVariable Integer payServiceId, Model model) {
		PayService payService = payServiceService.selectById(payServiceId);
		model.addAttribute("item", payService);
		PayType payType = payTypeService.selectById(payService.getPayTypeId());
		String payTypeName = "";
		if (null != payType) {
			payTypeName = payType.getName();
		}
		model.addAttribute("payTypeName", payTypeName);
		LogObjectHolder.me().set(payService);
		return PREFIX + "payService_edit.html";
	}

	/**
	 * 获取收费项目分类列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(PayService payService) {
		return payServiceService.selectList(payService);
	}

	/**
	 * 新增收费项目分类
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(PayService payService) {
		payServiceService.insert(payService);
		return SUCCESS_TIP;
	}

	/**
	 * 删除收费项目分类
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam Integer payServiceId) {
		payServiceService.deleteById(payServiceId);
		return SUCCESS_TIP;
	}

	/**
	 * 修改收费项目分类
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(PayService payService) {
		payServiceService.update(payService);
		return SUCCESS_TIP;
	}

	/**
	 * 收费项目分类详情
	 */
	@RequestMapping(value = "/detail/{payServiceId}")
	@ResponseBody
	public Object detail(@PathVariable("payServiceId") Integer payServiceId) {
		return payServiceService.selectById(payServiceId);
	}
}
