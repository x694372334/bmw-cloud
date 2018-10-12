package com.bmw.property.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.utils.ExcelUtils;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.modular.system.service.IDictService;
import com.stylefeng.guns.modular.system.service.IUserService;
import com.bmw.property.model.CommunityActivity;
import com.bmw.property.model.CommunityBulletin;
import com.bmw.property.service.ICommunityActivityService;
import com.bmw.property.warpper.CommunityBulletinWarpper;

/**
 * 小区活动控制器
 *
 * @author fengshuonan
 * @Date 2018-06-25 15:28:50
 */
@Controller
@RequestMapping("/communityActivity")
public class CommunityActivityController extends BaseController {
	// 小区活动类型的字典表code
	private static final String ACTIVITY_TYPE_DICT_CODE = "xqhdlx";

	// 草稿
	private static final Integer STATUS_SAVE = 1;
	// 审核中
	private static final Integer STATUS_CHECKING = 2;
	// 审核通过
	private static final Integer STATUS_CHECKED = 3;
	// 被驳回
	private static final Integer STATUS_REJECTED = 4;
	// 已发布
	private static final Integer STATUS_PUBED = 5;
	// 删除
	private static final Integer IS_DELETEED_YES = 0;

	private String PREFIX = "/property/communityActivity/";

	@Autowired
	private ICommunityActivityService communityActivityService;
	@Autowired
	private IUserService userService;
	@Autowired
	IDictService dictService;

	/**
	 * 跳转到小区活动首页
	 */
	@RequestMapping("")
	public String index(Model model) {
		JSONArray userList = userService.selectUsers(null, null, null, null,null);
		model.addAttribute("userList", userList);
		return PREFIX + "communityActivity.html";
	}

	/**
	 * 跳转到添加小区活动
	 */
	@RequestMapping("/communityActivity_add")
	public String communityActivityAdd(Model model) {
		JSONArray jsonArray = dictService.getDictByCode(ACTIVITY_TYPE_DICT_CODE);
		model.addAttribute("activityTypeList", jsonArray);
		return PREFIX + "communityActivity_add.html";
	}

	/**
	 * 跳转到修改小区活动
	 */
	@RequestMapping("/communityActivity_update/{communityActivityId}")
	public String communityActivityUpdate(@PathVariable Integer communityActivityId, Model model) {
		JSONArray jsonArray = dictService.getDictByCode(ACTIVITY_TYPE_DICT_CODE);
		model.addAttribute("activityTypeList", jsonArray);
		CommunityActivity communityActivity = communityActivityService.selectById(communityActivityId);
		model.addAttribute("item", communityActivity);
		LogObjectHolder.me().set(communityActivity);
		return PREFIX + "communityActivity_edit.html";
	}

	/**
	 * 跳转到修改小区活动
	 */
	@RequestMapping("/communityActivity_detail/{communityActivityId}")
	public String communityActivityDetail(@PathVariable Integer communityActivityId, Model model) {
		CommunityActivity communityActivity = communityActivityService.selectById(communityActivityId);
		model.addAttribute("item", communityActivity);
		LogObjectHolder.me().set(communityActivity);
		return PREFIX + "communityActivity_detail.html";
	}
	
	/**
	 * 跳转到修改小区活动
	 */
	@RequestMapping("/communityActivity_check/{communityActivityId}/{taskId}")
	public String communityActivityCheck(@PathVariable Integer communityActivityId,@PathVariable("taskId") String taskId,Model model) {
		CommunityActivity communityActivity = communityActivityService.selectById(communityActivityId);
		model.addAttribute("item", communityActivity);
		model.addAttribute("taskId", taskId);
		LogObjectHolder.me().set(communityActivity);
		return PREFIX + "communityActivity_check.html";
	}

	@RequestMapping(value = "/submitCheck")
    @ResponseBody
    public Object submitCheck(@RequestBody CommunityActivity communityActivity) {
    	ShiroUser shiroUser = ShiroKit.getUser();
    	communityActivity.setEditManId(shiroUser.getId());
    	communityActivity.setEditMan(shiroUser.getName());
    	communityActivity.setVerifierId(shiroUser.getId());
    	communityActivity.setVerifierName(shiroUser.getName());
    	communityActivity.setVerifyTime(new Date());
    	communityActivityService.check(communityActivity);
    	return SUCCESS_TIP;
    }
	/**
	 * 获取小区活动列表
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(@RequestParam(required = false) String activityName,
			@RequestParam(required = false) Integer initiatorId, @RequestParam(required = false) Integer verifierId) {
		Map<String, Object> param = new HashMap<String, Object>();
		Page<CommunityActivity> page = new PageFactory<CommunityActivity>().defaultPage();
		param.put("eId", ShiroKit.getUser().geteId());
		param.put("activityName", activityName);
		param.put("initiatorId", initiatorId);
		param.put("verifierId", verifierId);
		param.put("pageSize", page.getSize());
		param.put("pageNum", page.getCurrent());
		JSONObject json = communityActivityService.selectListByParam(param);
		page.setTotal(json.getInteger("total"));
		page.setRecords((List<CommunityActivity>) new CommunityBulletinWarpper(json.getJSONArray("result")).warp());
		return super.packForBT(page);
	}

	/**
	 * 生成树
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/queryNBTree")
	@ResponseBody
	public List<ZTreeNode> queryNBTree(@RequestParam(required = false) Object id) {
		// 小区活动id
		Integer id_ = null;
		if (StringUtils.isNotEmpty(id.toString())) {
			id_ = Integer.valueOf(id.toString());
		}
		List<ZTreeNode> list = new ArrayList<>();
		list = communityActivityService.queryNBTree(id_, ShiroKit.getUser().geteId());
		return list;
	}

	/**
	 * 新增小区活动
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(@RequestBody CommunityActivity communityActivity) {
		// 取得当前登录用户
		ShiroUser shiroUser = ShiroKit.getUser();
		communityActivity.seteId(shiroUser.geteId());
		communityActivity.setInitiatorId(shiroUser.getId());
		communityActivity.setCreateManId(shiroUser.getId());
		communityActivity.setCreateMan(shiroUser.getName());
		communityActivity.setCreateTime(new Date());
		communityActivityService.insert(communityActivity);
		return SUCCESS_TIP;
	}

	/**
	 * @author 张珵珺
	 * @date 2018-06-23
	 * @see提交审核
	 * @param communityId
	 * @return
	 */
	@RequestMapping(value = "/sub")
	@ResponseBody
	public Object sub(@RequestParam(required = true) Integer communityActivityId) {
		ShiroUser shiroUser = ShiroKit.getUser();
		CommunityActivity communityActivity = new CommunityActivity();
		communityActivity.setEditManId(shiroUser.getId());
		communityActivity.setEditMan(shiroUser.getName());
		communityActivity.setId(communityActivityId);
		communityActivityService.sub(communityActivity);
		return SUCCESS_TIP;
	}

	/**
	 * @author 张珵珺
	 * @date 2018-06-23
	 * @see 撤回
	 * @param communityId
	 * @return
	 */
	@RequestMapping(value = "/callback")
	@ResponseBody
	public Object callback(@RequestParam(required = true) Integer communityActivityId) {
		ShiroUser shiroUser = ShiroKit.getUser();
		CommunityActivity communityActivity = new CommunityActivity();
		communityActivity.setEditManId(shiroUser.getId());
		communityActivity.setEditMan(shiroUser.getName());
		communityActivity.setEditTime(new Date());
		communityActivity.setId(communityActivityId);
		communityActivity.setStatus(STATUS_SAVE);
		communityActivityService.callback(communityActivity);

		return SUCCESS_TIP;
	}

	/**
	 * @author 张珵珺
	 * @date 2018-06-23
	 * @see 发布
	 * @param communityId
	 * @return
	 */
	@RequestMapping(value = "/pub")
	@ResponseBody
	public Object pub(@RequestParam(required = true) Integer communityActivityId) {
		ShiroUser shiroUser = ShiroKit.getUser();
		CommunityActivity communityActivity = new CommunityActivity();
		communityActivity.setEditManId(shiroUser.getId());
		communityActivity.setEditMan(shiroUser.getName());
		communityActivity.setEditTime(new Date());
		communityActivity.setId(communityActivityId);
		communityActivity.setStatus(STATUS_PUBED);
		communityActivityService.pub(communityActivity);
		return SUCCESS_TIP;
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(@RequestParam Integer communityActivityId) {
		ShiroUser shiroUser = ShiroKit.getUser();
		CommunityActivity communityActivity = new CommunityActivity();
		communityActivity.setEditManId(shiroUser.getId());
		communityActivity.setEditMan(shiroUser.getName());
		communityActivity.setEditTime(new Date());
		communityActivity.setId(communityActivityId);
		communityActivity.setIsDelete(IS_DELETEED_YES);
		communityActivityService.delete(communityActivity);
		return SUCCESS_TIP;
	}

	/**
	 * 修改小区活动
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(@RequestBody CommunityActivity communityActivity) {
		ShiroUser shiroUser = ShiroKit.getUser();
		communityActivity.setEditManId(shiroUser.getId());
		communityActivity.setEditMan(shiroUser.getName());
		communityActivity.setEditTime(new Date());
		communityActivityService.updateById(communityActivity);
		return SUCCESS_TIP;
	}

	@RequestMapping(value = "/exportExcel/{activityId}")
	public void exportExcel(@PathVariable("activityId") Integer activityId, HttpServletResponse response)
			throws IOException {
		List<String> headers = new ArrayList<>();
		headers.add("姓名");
		headers.add("房间号");
		headers.add("电话号");
		headers.add("活动类型");
		headers.add("报名情况");
		headers.add("投票情况");
		List<Map<String,Object>> rows=new ArrayList<Map<String,Object>>();
		rows=communityActivityService.exportExcel(activityId);
		ExcelUtils.exportExcel(response, "活动名单", "活动名单", headers, rows);
	}

}
