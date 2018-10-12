package com.bmw.medical.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.medical.model.Department;
import com.bmw.medical.service.IDepartmentService;
import com.bmw.medical.warpper.DepartmentWarpper;

/**
 * 科室管理
 *
 * @author liuwsh
 * @Date 2018-09-12 08:51:10
 */
@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController {
	private String PREFIX = "/medical/department/";
	@Autowired
	private IDepartmentService departmentService;

	/**
	 * 跳转到科室管理首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "department.html";
	}

	/**
	 * 跳转到添加科室管理
	 */
	@RequestMapping("/department_add")
	public String departmentAdd() {
		return PREFIX + "department_add.html";
	}

	/**
	 * 跳转到修改科室管理
	 */
	@RequestMapping("/department_update/{departmentId}")
	public String departmentUpdate(@PathVariable Integer departmentId, Model model) {
		Department department = departmentService.selectById(departmentId);
		Department parent = departmentService.findByCode(department.getParentCode());
		String pname = "";
		if (null != parent) {
			pname = parent.getName();
		}
		model.addAttribute("pname", pname);
		model.addAttribute("item", department);
		LogObjectHolder.me().set(department);
		return PREFIX + "department_edit.html";
	}

	/**
	 * 获取科室管理列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(Department department) {
		return super.warpObject(new DepartmentWarpper(departmentService.findList(department)));
	}

	/**
	 * 新增科室管理
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(Department department) {
		String parentCode = department.getParentCode();
		if (null == parentCode || "".equals(parentCode)  || "0".equals(parentCode)) {
			// 自身为顶级节点，没有父节点
			department.setParentCode("0");
			department.setLevel(Integer.valueOf("1"));
			department.setChildrenCount(0);
			department.setIsLeaf(0);
			
			// 获取数据库中所有顶级节点的数量
			String count = departmentService.departmentCountCode("0");
			department.setSort(count );
			department.setCode(String.format("%04d", Integer.parseInt(count)));
		} else {
			// 自身为子节点，存在父节点
			// 获取新的父级对象
			Department parent = departmentService.findByCode(department.getParentCode().toString());
			if (parent != null) {
				// 更新父的isLeaf属性
				parent.setIsLeaf(1);
				// 更新父的子节点数量
				int childrenCount = null == parent.getChildrenCount() ? 0 : parent.getChildrenCount();
				parent.setChildrenCount(childrenCount + 1);
				// 更新自己的code
				department.setCode(parent.getCode() + String.format("%04d", parent.getChildrenCount()));
				// 更新自己的层级
				department.setLevel(parent.getLevel() + 1);
				// 更新自己的排序
				department.setSort("" + parent.getChildrenCount());
				departmentService.update(parent);
			}
		}
		department.setIsLeaf(0);
		department.setChildrenCount(0);
		// 获取当前登录人信息
		ShiroExt shiro = new ShiroExt();
		Integer userId = shiro.getUser().getId();
		String userName = shiro.getUser().getName();
		// 创建人信息和修改人信息等
		department.setCreateManId(userId);
		department.setCreateMan(userName);
		department.setCreateTime(new Date());
		department.setEditTime(new Date());
		department.setEditMan(userName);
		department.setEditManId(userId);
		departmentService.add(department);
		return SUCCESS_TIP;
	}

	/**
	 * 删除科室管理(未考虑子科室)
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam Integer departmentId) {
		Department department = new Department();
		department = departmentService.selectById(departmentId);
		if (department.getLevel() != 1) {
			Department parent = departmentService.findByCode(department.getParentCode());
			if (null != parent) {
				parent.setChildrenCount(parent.getChildrenCount() - 1);
				if (parent.getChildrenCount() == 0) {
					parent.setIsLeaf(0);
				}
			}
			departmentService.update(parent);
		}
		departmentService.del(departmentId);
		return SUCCESS_TIP;
	}

	/**
	 * 修改科室管理(未考虑被修改对象的子科室)
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(Department department) {
		String oldCode = department.getCode();
		String parentCode = department.getParentCode() ;
		if (null == parentCode || "".equals(parentCode)  || "0".equals(parentCode)) {
			department.setParentCode("0");
		} else {
			// 获取新的父级对象
			Department parent = departmentService.findByCode(department.getParentCode().toString());
			if (parent != null) {
				// 更新父的isLeaf属性
				parent.setIsLeaf(1);
				// 更新父的子节点数量
				parent.setChildrenCount(parent.getChildrenCount() + 1);
				// 更新自己的code
				department.setCode(parent.getCode() + String.format("%04d", parent.getChildrenCount()));
				// 更新自己的层级
				department.setLevel(parent.getLevel() + 1);
				// 更新自己的排序
				department.setSort("" + parent.getChildrenCount());
				departmentService.update(parent);
			}
		}
		// 获取当前登录人信息
		ShiroExt shiro = new ShiroExt();
		Integer userId = shiro.getUser().getId();
		String userName = shiro.getUser().getName();
		// 创建人信息和修改人信息等
		department.setEditTime(new Date());
		department.setEditMan(userName);
		department.setEditManId(userId);
		departmentService.update(department);
		//转移自己的子节点的parentcode为新的值
		departmentService.updateChildren(oldCode,department.getCode());
		return SUCCESS_TIP;
	}

	/**
	 * 科室管理详情
	 */
	@RequestMapping(value = "/detail/{departmentId}")
	@ResponseBody
	public Object detail(@PathVariable("departmentId") Integer departmentId) {
		return departmentService.selectById(departmentId);
	}

	/**
	 * 获取区域列表(选择父级菜单用)
	 */
	@RequestMapping(value = "selectDepartmentTreeList")
	@ResponseBody
	public List<ZTreeNode> selectDepartmentTreeList(@RequestParam Map<String, Object> map) {
		List<ZTreeNode> departmentTreeList = null;
		if (map.get("hospitalCode") != null) {
			departmentTreeList = this.departmentService.departmentTreeList((String) map.get("hospitalCode"));
		} else {
			departmentTreeList = this.departmentService.departmentTreeList(null);
		}
		departmentTreeList.add(ZTreeNode.createParent());
		return departmentTreeList;
	}
	
	@RequestMapping(value = "/getTreeList")
    @ResponseBody
    public List<ZTreeNode> getTreeList() {
        return departmentService.getTreeList();
    }
}