package com.bmw.medical.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.medical.model.DeptDoctorRelation;
import com.bmw.medical.model.Doctor;
import com.bmw.medical.service.IDeptDoctorRelationService;
import com.bmw.medical.service.IDoctorService;
import com.bmw.medical.warpper.DoctorWarpper;

/**
 * 医生控制器
 *
 * @author fengshuonan
 * @Date 2018-06-07 13:18:17
 */
@Controller
@RequestMapping("/doctor")
public class DoctorController extends BaseController {

	private String PREFIX = "/medical/doctor/";

	@Autowired
	private IDoctorService doctorService;

	@Autowired
	private IDeptDoctorRelationService deptService;

	/**
	 * 跳转到医生首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "doctor.html";
	}

	/**
	 * 跳转到添加医生
	 */
	@RequestMapping("/doctor_add")
	public String doctorAdd() {
		return PREFIX + "doctor_add.html";
	}

	/**
	 * 跳转到修改医生
	 */
	@RequestMapping("/doctor_update/{doctorId}")
	public String doctorUpdate(@PathVariable Integer doctorId, Model model) {
		Doctor doctor = doctorService.getdetail(doctorId);
		model.addAttribute("item", doctor);
		LogObjectHolder.me().set(doctor);
		return PREFIX + "doctor_edit.html";
	}

	/**
	 * 获取医生列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(String condition) {
		return super.warpObject(new DoctorWarpper(doctorService.findList(condition)));
	}

	/**
	 * 获取医生列表
	 */
	@RequestMapping(value = "/addList/{deptCode}")
	@ResponseBody
	public Object addList(@PathVariable("deptCode") String deptCode) {
		return doctorService.findAddList(deptCode);
	}

	/**
	 * 新增医生
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(Doctor doctor) {
		String count = doctorService.findCode();
		Integer num = Integer.parseInt(count);
		num = num + 1;
		String data = "";
		if (num >= 10) {
			data = "00";
		} else if (num >= 100) {
			data = "0";
		} else {
			data = "000";
		}
		doctor.setCode(data + num);
		doctor.setIsDelete(0);
		doctor.setCreateMan("admin");
		doctor.setCreateManId(1);
		doctor.setEditMan("admin");
		doctor.setEditManId(1);
		doctor.setEditTime(new Date());
		doctor.setCreateTime(new Date());
		doctorService.add(doctor);
		
		return SUCCESS_TIP;
	}

	/**
	 * 删除医生
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam Integer doctorId) {
		doctorService.del(doctorId);
		return SUCCESS_TIP;
	}

	/**
	 * 修改医生
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(Doctor doctor) {
		// 设置修改用户 、 时间 、 Id等
		doctor.setEditMan("admin");
		doctor.setEditManId(1);
		doctor.setEditTime(new Date());
		doctorService.update(doctor);
		return SUCCESS_TIP;
	}

	/**
	 * 医生详情
	 */
	@RequestMapping(value = "/detail/{doctorId}")
	@ResponseBody
	public Object detail(@PathVariable("doctorId") Integer doctorId) {
		return doctorService.getdetail(doctorId);
	}

	/**
	 * 查看最大编码
	 */
	@RequestMapping(value = "/findCode")
	@ResponseBody
	public Object findCode() {
		return doctorService.findCode();
	}

}
