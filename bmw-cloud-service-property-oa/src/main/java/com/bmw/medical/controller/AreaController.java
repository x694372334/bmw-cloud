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
import com.bmw.medical.model.Area;
import com.bmw.medical.service.IAreaService;
import com.bmw.medical.warpper.AreaWarpper;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-05-28 15:45:05
 */
@Controller
@RequestMapping("/area")
public class AreaController extends BaseController {

	private String PREFIX = "/medical/area/";

	@Autowired
	private IAreaService areaService;

	/**
	 * 跳转到area首页
	 */
	@RequestMapping("")
	public String index() {
		return PREFIX + "area.html";
	}

	/**
	 * 跳转到添加community
	 */
	@RequestMapping("/area_add")
	public String areaAdd() {
		return PREFIX + "area_add.html";
	}

	/**
	 * 跳转到修改community
	 */
	@RequestMapping("/area_update/{areaId}")
	public String areaUpdate(@PathVariable Integer areaId, Model model) {
		Area area = areaService.getdetail(areaId);
		Area p_area = areaService.areaDeleteUpdate(area.getParentCode());
		String pname = p_area.getName();
		model.addAttribute("pname", pname);
		model.addAttribute("item", area);
		LogObjectHolder.me().set(area);
		return PREFIX + "area_edit.html";
	}

	/**
	 * 获取区域管理列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(String condition) {
		return super.warpObject(new AreaWarpper(areaService.findList(condition)));
	}

	/**
	 * 新增区域管理
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(Area area) {
		// 计算code值和层级 叶子数等
		if (area.getParentCode() == "" || "0".equals(area.getParentCode())) {
			String count = areaService.areaCountCode("0");
			int num = Integer.valueOf(count);
			String data = null;
			num = num + 1;
			if (num >= 10) {
				data = "00";
			} else if (num >= 100) {
				data = "0";
			} else {
				data = "000";
			}
			area.setIsLeaf(0);
			area.setChildrenCount(0);
			area.setCode(data + num);
			area.setParentCode("0");
			area.setLevel(Integer.valueOf("1"));
		} else {
			// 新增时设置ParentCode和Code
			String count = areaService.areaCountCode(area.getParentCode());
			// 设置叶子节点总共个数
			String childCount = areaService.areaCountIsCode(area.getParentCode());
			// 设置编码code
			int num = Integer.valueOf(count);
			num = num + 1;
			String data;
			if (num >= 10) {
				data = "00";
			} else if (num >= 100) {
				data = "0";
			} else {
				data = "000";
			}
			if (!area.getParentCode().equals("0")) {
				area.setCode(area.getParentCode() + data + num);
			}
			area.setParentCode(area.getParentCode());
			// 设置层级自动化
			Integer levelNum = Integer.valueOf(area.getCode().length()) / 4;
			area.setLevel(levelNum);
			// 叶子节点设置
			area.setChildrenCount(0);
			// 节点设置
			area.setIsLeaf(0);
			String areaId = areaService.areaCodeSelect(area.getParentCode().toString());
			Area areaData = new Area();
			if (!areaId.equals("null")) {
				areaData.setId(Integer.parseInt(areaId));
				areaData.setChildrenCount(Integer.parseInt(childCount) + 1);
				areaData.setIsLeaf(1);
				areaService.update(areaData);
			}
		}
		// 创建人信息和修改人信息等
		area.setCreateManId(1);
		area.setCreateMan("admin");
		area.setCreateTime(new Date());
		area.setEditTime(new Date());
		area.setEditMan("admin");
		area.setEditManId(1);
		area.setIsDelete(Integer.parseInt("0"));
		areaService.add(area);
		return SUCCESS_TIP;
	}

	/**
	 * 删除区域管理
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam String areaId) {
		Area area = new Area();
		area = areaService.getdetail(Integer.valueOf(areaId));
		if (area.getLevel() != 1) {
			Area areaData = areaService.areaDeleteUpdate(area.getParentCode());
			areaData.setChildrenCount(areaData.getChildrenCount() - 1);
			if (areaData.getChildrenCount() == 0) {
				areaData.setIsLeaf(0);
			}
			areaService.update(areaData);
		}
		areaService.del(Integer.parseInt(areaId.toString()));
		return SUCCESS_TIP;
	}

	/**
	 * 修改区域管理
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(Area area) {
		if (area.getParentCode() == "") {
			area.setParentCode("0");
			Area areaTest = areaService.getdetail(Integer.valueOf(area.getId()));
			Area areaData = areaService.areaDeleteUpdate(areaTest.getParentCode());
			areaData.setChildrenCount(areaData.getChildrenCount() - 1);
			areaService.update(areaData);
		} else {
			// 当前处理
			Area areaTest = areaService.getdetail(Integer.valueOf(area.getId()));
			Area areaOne = areaService.areaDeleteUpdate(areaTest.getParentCode().toString());
			if (areaOne != null) {
				areaOne.setChildrenCount(areaOne.getChildrenCount() - 1);
				areaService.update(areaOne);
			}
			areaTest.setCode(areaTest.getParentCode() + areaTest.getCode());
			areaTest.setIsLeaf(0);
			areaService.update(areaTest);
			// 更新上级处理
			Area areaData = areaService.areaDeleteUpdate(area.getParentCode());
			areaData.setChildrenCount(areaData.getChildrenCount() + 1);
			areaService.update(areaData);
			if (areaData.getChildrenCount() == 0) {
				areaData.setIsLeaf(0);
			}
		}

		area.setEditTime(new Date());
		area.setEditMan("admin");
		area.setEditManId(1);
		areaService.update(area);
		return SUCCESS_TIP;
	}

	/**
	 * 区域管理详情
	 */
	@RequestMapping(value = "/detail/{communityId}")
	@ResponseBody
	public Object detail(@PathVariable("communityId") String areaId) {
		return areaService.getdetail(Integer.parseInt(areaId));
	}

	/**
	 * 获取区域列表(树级父级菜单用)
	 */
	@RequestMapping(value = "/areaTreeList")
	@ResponseBody
	public List<ZTreeNode> areaTreeList() {
		List<ZTreeNode> roleTreeList = this.areaService.areaTreeList();
		roleTreeList.add(ZTreeNode.createParent());
		return roleTreeList;
	}

	/**
	 * 获取区域列表(选择父级菜单用)
	 */
	@RequestMapping(value = "/selectAreaTreeList")
	@ResponseBody
	public List<ZTreeNode> selectAreaTreeList() {
		List<ZTreeNode> roleTreeList = this.areaService.areaTreeList();
		roleTreeList.add(ZTreeNode.createParent());
		return roleTreeList;
	}

}
