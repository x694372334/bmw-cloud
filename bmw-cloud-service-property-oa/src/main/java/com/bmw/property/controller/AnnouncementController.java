package com.bmw.property.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.bmw.property.model.Announcement;
import com.bmw.property.service.IAnnouncementService;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * announcement控制器
 *
 * @author fengshuonan
 * @Date 2018-07-06 14:08:52
 */
@Controller
@RequestMapping("/announcement")
public class AnnouncementController extends BaseController {

    private String PREFIX = "/property/announcement/";

    @Autowired
    private IAnnouncementService announcementService;

    /**
     * 跳转到announcement首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "announcement.html";
    }

    /**
     * 跳转到添加announcement
     */
    @RequestMapping("/announcement_add")
    public String announcementAdd() {
        return PREFIX + "announcement_add.html";
    }
    
    /**
     * 跳转到查看announcement
     */
    @RequestMapping("/announcement_view/{announcementId}")
    public String announcementView(@PathVariable Integer announcementId, Model model) { 
         announcementService.updateHaveRead(announcementId);
    	 Announcement announcement = announcementService.getdetail(announcementId);
         model.addAttribute("item",announcement);
         LogObjectHolder.me().set(announcement);
        return PREFIX + "announcement_view.html";
    }

    /**
     * 跳转到修改announcement
     */
    @RequestMapping("/announcement_update/{announcementId}")
    public String announcementUpdate(@PathVariable Integer announcementId, Model model) {
        Announcement announcement = announcementService.getdetail(announcementId);
        model.addAttribute("item",announcement);
        LogObjectHolder.me().set(announcement);
        return PREFIX + "announcement_edit.html";
    }

    /**
     * 获取announcement列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Announcement condition) {
        return announcementService.findList(condition);
    }

    /**
     * 新增announcement
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Announcement announcement) {
        announcementService.add(announcement);
        return SUCCESS_TIP;
    }

    /**
     * 删除announcement
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer announcementId) {
        announcementService.del(announcementId);
        return SUCCESS_TIP;
    }

    /**
     * 修改announcement
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Announcement announcement) {
        announcementService.update(announcement);
        return SUCCESS_TIP;
    }

    /**
     * announcement详情
     */
    @RequestMapping(value = "/detail/{announcementId}")
    @ResponseBody
    public Object detail(@PathVariable("announcementId") Integer announcementId) {
        return announcementService.getdetail(announcementId);
    }
}
