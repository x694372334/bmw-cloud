package com.bmw.app.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.bmw.app.model.Community;
import com.bmw.app.service.ICommunityService;
import com.bmw.app.warpper.CommunityWarpper;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * community控制器
 *
 * @author fengshuonan
 * @Date 2018-05-23 15:54:13
 */
@Controller
@RequestMapping("/community")
public class CommunityController extends BaseController {

    private String PREFIX = "/app/community/";

    @Autowired
    private ICommunityService communityService;

    /**
     * 跳转到community首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "community.html";
    }

    /**
     * 跳转到添加community
     */
    @RequestMapping("/community_add")
    public String communityAdd() {
        return PREFIX + "community_add.html";
    }

    /**
     * 跳转到修改community
     */
    @RequestMapping("/community_update/{communityId}")
    public String communityUpdate(@PathVariable Integer communityId, Model model) {
        Community community = communityService.getdetail(communityId);
        model.addAttribute("item",community);
        LogObjectHolder.me().set(community);
        return PREFIX + "community_edit.html";
    }

    /**
     * 获取轮播图片管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return super.warpObject(new CommunityWarpper(communityService.findList(condition)));
    }

    /**
     * 新增轮播图片管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Community community) {
    	communityService.add(community);
        return SUCCESS_TIP;
    }

    /**
     * 删除轮播图片管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer communityId) {
    	communityService.del(communityId);
        return SUCCESS_TIP;
    }

    /**
     * 修改轮播图片管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Community community) {
    	communityService.update(community);
        return SUCCESS_TIP;
    }

    /**
     * 轮播图片管理详情
     */
    @RequestMapping(value = "/detail/{communityId}")
    @ResponseBody
    public Object detail(@PathVariable("communityId") Integer communityId) {
        return communityService.getdetail(communityId);
    }
}
