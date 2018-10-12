package com.bmw.medical.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.medical.model.DeptDoctorRelation;
import com.bmw.medical.service.IDepartmentService;
import com.bmw.medical.service.impl.DeptDoctorRelationServiceImpl;

/**
 * 批量关联科室医生
 *
 * @author liuwsh
 * @Date 2018-08-28 10:28:26
 */
@Controller
@RequestMapping("/deptDoctorRelation")
public class DeptDoctorRelationController extends BaseController {
	private String PREFIX = "/medical/deptDoctorRelation/";
	// 科室-医生-职称关系类服务
	@Autowired
	private DeptDoctorRelationServiceImpl relationService;
	@Autowired
	private IDepartmentService departmentService;

	/**
	 * 跳转到批量关联医生科室管理首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "relation.html";
	}

	/**
	 * 获取科室列表
	 */
	@RequestMapping(value = "/getTreeList")
	@ResponseBody
	 public List<ZTreeNode> getTreeList() {
        return departmentService.getTreeList();
    }

	/**
	 * 获取科室医生列表
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping("/doctorList")
	@ResponseBody
	public Object findByDeptCode(@RequestParam(required = false) String deptCode,
			@RequestParam(required = false) String name) throws ParseException, IOException {
		return relationService.findByDeptCode(deptCode, name);
	}

	/**
	 * 跳转到添加医生页面
	 */
	@RequestMapping("/doctor_add/{deptCode}")
	public String doctorAdd(@PathVariable("deptCode") String deptCode, Model model) {
		model.addAttribute("deptCode", deptCode);
		return PREFIX + "addDoctor.html";
	}

	/**
	 * 添加医生
	 * 
	 * @return
	 */
	@RequestMapping("/addDoctor")
	@ResponseBody
	public Object addDoctor(@RequestParam String deptCode, @RequestParam String doctorCode) {
		String[] doctorCodeArray = doctorCode.split(",");
		DeptDoctorRelation rel = null;
		List<DeptDoctorRelation> list = new ArrayList<DeptDoctorRelation>();
		for (String sss : doctorCodeArray) {
			rel = new DeptDoctorRelation();
			rel.setMedicalDepartmentCode(deptCode);
			rel.setMedicalDoctorCode(sss);
			list.add(rel);
		}
		relationService.addList(list);
		return SUCCESS_TIP;
	}

	/**
	 * 删除医生
	 * 
	 * @param Ids
	 * @return
	 */
	@RequestMapping("/delDoctor")
	@ResponseBody
	public Object delDoctor(@RequestParam String Ids) {
		relationService.delDoctor(Ids.split(","));
		return SUCCESS_TIP;
	}

	/**
	 * 跳转到关联职称页面
	 */
	@RequestMapping("/title/{doctorIds}")
	public String updateTitle(@PathVariable("doctorIds") String doctorIds, Model model) {
		model.addAttribute("doctorIds", doctorIds);
		return PREFIX + "title.html";
	}

	/**
	 * 提交职称
	 * 
	 * @param doctorIds 关联表ids
	 * @param titleId   职称id
	 * @param titleName 职称名称
	 * @return
	 */
	@RequestMapping("/submitTitle")
	@ResponseBody
	public Object submitTitle(@RequestParam String doctorIds, @RequestParam Integer titleId,
			@RequestParam String titleName) {
		relationService.submitTitle(doctorIds, titleId, titleName);
		return SUCCESS_TIP;
	}
}