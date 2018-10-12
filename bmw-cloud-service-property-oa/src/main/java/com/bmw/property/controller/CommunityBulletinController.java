package com.bmw.property.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.modular.system.service.IUserService;
import com.bmw.property.model.CommunityBulletin;
import com.bmw.property.service.ICommunityBulletinService;

/**
 * 小区公告控制器
 *
 * @author 张珵珺
 * @Date 2018-06-19 16:13:47
 */
@Controller
@RequestMapping("/communityBulletin")
public class CommunityBulletinController extends BaseController {

	//草稿
	private static final Integer STATUS_SAVE=1;
	//审核中
	private static final Integer STATUS_CHECKING=2;
	//已发布
	private static final Integer STATUS_PUBED=5;
	//删除
	private static final Integer IS_DELETEED_YES=0;
	
    private String PREFIX = "/property/communityBulletin/";

    @Autowired
    private ICommunityBulletinService communityBulletinService;
    @Autowired
    private IUserService userService;
    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index(Model model) {
    	JSONArray userList=userService.selectUsers(null,null, null, null,null);
    	model.addAttribute("userList", userList);
        return PREFIX + "communityBulletin.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/communityBulletin_add")
    public String communityBulletinAdd() {
        return PREFIX + "communityBulletin_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/communityBulletin_update/{communityBulletinId}")
    public String communityBulletinUpdate(@PathVariable Integer communityBulletinId, Model model) {
        CommunityBulletin communityBulletin = communityBulletinService.selectById(communityBulletinId);
        model.addAttribute("item",communityBulletin);
        LogObjectHolder.me().set(communityBulletin);
        return PREFIX + "communityBulletin_edit.html";
    }
    
    /**
     * 详情
     */
    @RequestMapping(value = "/communityBulletin_detail/{communityBulletinId}")
    public String detail(@PathVariable("communityBulletinId") Integer communityBulletinId,Model model) {
    	 CommunityBulletin communityBulletin = communityBulletinService.selectById(communityBulletinId);
    	 List<String> buildingNameList=communityBulletinService.getBuildingNames(communityBulletinId);
    	 communityBulletin.setBuildingNames(buildingNameList);
         model.addAttribute("item",communityBulletin);
         LogObjectHolder.me().set(communityBulletin);
         return PREFIX + "communityBulletin_detail.html";
    }
    
    /**
     * 审核
     */
    @RequestMapping(value = "/communityBulletin_check/{communityBulletinId}/{taskId}")
    public String check(@PathVariable("communityBulletinId") Integer communityBulletinId,@PathVariable("taskId") String taskId,Model model) {
    	 CommunityBulletin communityBulletin = communityBulletinService.selectById(communityBulletinId);
    	 List<String> buildingNameList=communityBulletinService.getBuildingNames(communityBulletinId);
    	 communityBulletin.setBuildingNames(buildingNameList);
         model.addAttribute("item",communityBulletin);
         model.addAttribute("taskId", taskId);
         LogObjectHolder.me().set(communityBulletin);
         return PREFIX + "communityBulletin_check.html";
    }
    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(CommunityBulletin cb) {
    	Page<CommunityBulletin> page= new PageFactory<CommunityBulletin>().defaultPage();
    	Map<String,Object> param=new HashMap<>();
    	param.put("pageSize", page.getSize());
    	param.put("pageNum", page.getCurrent());
    	param.put("eId", ShiroKit.getUser().geteId());
    	param.put("title", cb.getTitle());
    	param.put("initiatorId", cb.getInitiatorId());
    	param.put("verifierId", cb.getVerifierId());
    	JSONObject json=communityBulletinService.selectListByParam(param);
    	page.setTotal(json.getInteger("total"));        
        page.setRecords(json.getJSONArray("result").toJavaList(CommunityBulletin.class));
        return super.packForBT(page);
    }

    /**
     * 注册一个类型解析器
     * @param binder
     */
    @org.springframework.web.bind.annotation.InitBinder
    public void InitBinder(WebDataBinder binder){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    
    @RequestMapping(value = "/queryBuildingTree/{id}")
    @ResponseBody
    public List<ZTreeNode> queryBuildingTree(@PathVariable("id") Integer id){
    	List<ZTreeNode> list=new ArrayList<>();
    	list=communityBulletinService.queryBuildingTree(id,ShiroKit.getUser().geteId());
    	return list;
    }
    
    /**
     * @author 张珵珺
     * @date 2018-06-23
     * 新增
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(@RequestBody CommunityBulletin communityBulletin) {
    	//取得当前登录用户
    	ShiroUser shiroUser = ShiroKit.getUser();
    	communityBulletin.setInitiatorId(shiroUser.getId());
    	communityBulletin.setCreateManId(shiroUser.getId());
    	communityBulletin.setCreateMan(shiroUser.getName());
    	communityBulletin.seteId(ShiroKit.getUser().geteId());
        return communityBulletinService.insert(communityBulletin);
    }
    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(@RequestBody CommunityBulletin communityBulletin) {
    	ShiroUser shiroUser = ShiroKit.getUser();
    	communityBulletin.setEditManId(shiroUser.getId());
    	communityBulletin.setEditMan(shiroUser.getName());
    	communityBulletin.setEditTime(new Date());
        return communityBulletinService.updateById(communityBulletin);
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
    public Object sub(@RequestParam(required=true) Integer communityBulletinId) {
    	ShiroUser shiroUser = ShiroKit.getUser();
    	CommunityBulletin communityBulletin=new CommunityBulletin();
    	communityBulletin.setEditManId(shiroUser.getId());
    	communityBulletin.setEditMan(shiroUser.getName());
    	communityBulletin.setEditTime(new Date());
    	communityBulletin.setId(communityBulletinId);
    	communityBulletin.setStatus(STATUS_CHECKING);
    	return communityBulletinService.sub(communityBulletin);
    }
    
    /**
     * @author 张珵珺
     * @date 2018-06-23
     * @see 审核提交
     * @param communityId
     * @return
     */
    @RequestMapping(value = "/submitCheck")
    @ResponseBody
    public Object submitCheck(@RequestBody CommunityBulletin communityBulletin) {
    	ShiroUser shiroUser = ShiroKit.getUser();
    	communityBulletin.setEditManId(shiroUser.getId());
    	communityBulletin.setEditMan(shiroUser.getName());
    	communityBulletin.setVerifierId(shiroUser.getId());
    	communityBulletin.setVerifierName(shiroUser.getName());
    	communityBulletin.setVerifyTime(new Date());
    	return communityBulletinService.check(communityBulletin);
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
    public Object callback(@RequestParam(required=true) Integer communityBulletinId) {
    	ShiroUser shiroUser = ShiroKit.getUser();
    	CommunityBulletin communityBulletin=new CommunityBulletin();
    	communityBulletin.setEditManId(shiroUser.getId());
    	communityBulletin.setEditMan(shiroUser.getName());
    	communityBulletin.setEditTime(new Date());
    	communityBulletin.setId(communityBulletinId);
    	communityBulletin.setStatus(STATUS_SAVE);
    	return communityBulletinService.callback(communityBulletin);
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
    public Object pub(@RequestParam(required=true) Integer communityBulletinId) {
    	ShiroUser shiroUser = ShiroKit.getUser();
    	CommunityBulletin communityBulletin=new CommunityBulletin();
    	communityBulletin.setEditManId(shiroUser.getId());
    	communityBulletin.setEditMan(shiroUser.getName());
    	communityBulletin.setEditTime(new Date());
    	communityBulletin.setId(communityBulletinId);
    	communityBulletin.setStatus(STATUS_PUBED);
    	return communityBulletinService.pub(communityBulletin);
    }
    
    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer communityBulletinId) {
    	ShiroUser shiroUser = ShiroKit.getUser();
    	CommunityBulletin communityBulletin=new CommunityBulletin();
    	communityBulletin.setEditManId(shiroUser.getId());
    	communityBulletin.setEditMan(shiroUser.getName());
    	communityBulletin.setEditTime(new Date());
    	communityBulletin.setId(communityBulletinId);
    	communityBulletin.setIsDelete(IS_DELETEED_YES);
        return communityBulletinService.delete(communityBulletin);
    }
}
