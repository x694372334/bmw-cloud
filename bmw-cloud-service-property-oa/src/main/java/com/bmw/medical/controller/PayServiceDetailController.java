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
import com.bmw.medical.model.PayServiceDetail;
import com.bmw.medical.service.IPayServiceDetailService;

/**
 * 收费项目明细控制器
 *
 * @author fengshuonan
 * @Date 2018-09-18 14:41:20
 */
@Controller
@RequestMapping("/payServiceDetail")
public class PayServiceDetailController extends BaseController {

	private String PREFIX = "/medical/payServiceDetail/";

	@Autowired
	private IPayServiceDetailService payServiceDetailService;

	/**
	 * 跳转到收费项目明细首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "payServiceDetail.html";
	}

	/**
	 * 跳转到添加收费项目明细
	 */
	@RequestMapping("/payServiceDetail_add")
	public String payServiceDetailAdd() {
		return PREFIX + "payServiceDetail_add.html";
	}

	/**
	 * 跳转到修改收费项目明细
	 */
	@RequestMapping("/payServiceDetail_update/{payServiceDetailId}")
	public String payServiceDetailUpdate(@PathVariable Integer payServiceDetailId, Model model) {
		PayServiceDetail payServiceDetail = payServiceDetailService.selectById(payServiceDetailId);
		model.addAttribute("item", payServiceDetail);
		LogObjectHolder.me().set(payServiceDetail);
		return PREFIX + "payServiceDetail_edit.html";
	}

	/**
	 * 获取收费项目明细列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(PayServiceDetail payServiceDetail) {
		return payServiceDetailService.selectList(payServiceDetail);
	}

	/**
	 * 新增收费项目明细
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(PayServiceDetail payServiceDetail) {
		payServiceDetailService.insert(payServiceDetail);
		return SUCCESS_TIP;
	}

	/**
	 * 删除收费项目明细
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam Integer payServiceDetailId) {
		payServiceDetailService.deleteById(payServiceDetailId);
		return SUCCESS_TIP;
	}

	/**
	 * 修改收费项目明细
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(PayServiceDetail payServiceDetail) {
		payServiceDetailService.update(payServiceDetail);
		return SUCCESS_TIP;
	}

	/**
	 * 收费项目明细详情
	 */
	@RequestMapping(value = "/detail/{payServiceDetailId}")
	@ResponseBody
	public Object detail(@PathVariable("payServiceDetailId") Integer payServiceDetailId) {
		return payServiceDetailService.selectById(payServiceDetailId);
	}

	@RequestMapping("/payServiceDetail_add/{payServiceId}")
	public String add(@PathVariable Integer payServiceId, Model model) {
		model.addAttribute("payServiceId", payServiceId);
		return PREFIX + "payServiceDetail_add.html";
	}

	@RequestMapping(value = "/add/{payServiceId}")
	@ResponseBody
	public Object add(@PathVariable("payServiceId") Integer payServiceId, PayServiceDetail payServiceDetail) {
		payServiceDetail.setPayServiceId(payServiceId);
		payServiceDetailService.insert(payServiceDetail);
		return SUCCESS_TIP;
	}
	
	/**
	 * 跳转到收费项目明细首页
	 */
	@RequestMapping("/viewDetails/{payServiceId}")
	public String viewDetails(@PathVariable Integer payServiceId, Model model) {
		model.addAttribute("payServiceId", payServiceId);
		return PREFIX + "viewDetails.html";
	}
	
	/**
	 * 获取收费项目明细列表
	 */
	@RequestMapping(value = "/findByPayServiceId/{payServiceId}")
	@ResponseBody
	public Object list(@PathVariable("payServiceId") Integer payServiceId) {
		PayServiceDetail payServiceDetail = new PayServiceDetail();
		payServiceDetail.setPayServiceId(payServiceId);
		return payServiceDetailService.selectList(payServiceDetail);
	}
	
}
