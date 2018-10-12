package com.bmw.app.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;

import org.springframework.web.bind.annotation.RequestParam;
import com.bmw.app.model.AppNotice;
import com.bmw.app.service.IAppNoticeService;
import com.bmw.app.warpper.AppNoticeWarpper;

/**
 * 通知管理控制器
 *
 * @author fengshuonan
 * @Date 2018-05-17 10:25:34
 */
@Controller
@RequestMapping("/appNotice")
public class AppNoticeController extends BaseController {

    private String PREFIX = "/app/appNotice/";

    @Autowired
    private IAppNoticeService appNoticeService;

    /**
     * 跳转到通知管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "appNotice.html";
    }

    /**
     * 跳转到添加通知管理
     */
    @RequestMapping("/appNotice_add")
    public String appNoticeAdd() {
        return PREFIX + "appNotice_add.html";
    }

    /**
     * 跳转到修改通知管理
     */
    @RequestMapping("/appNotice_update/{appNoticeId}")
    public String appNoticeUpdate(@PathVariable Integer appNoticeId, Model model) {
        AppNotice appNotice = appNoticeService.getdetail(appNoticeId);
        model.addAttribute("item",appNotice);
        LogObjectHolder.me().set(appNotice);
        return PREFIX + "appNotice_edit.html";
    }

    /**
     * 获取通知管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return super.warpObject(new AppNoticeWarpper(appNoticeService.findList(condition)));
    }

    /**
     * 新增通知管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(AppNotice appNotice) {
        appNoticeService.add(appNotice);
        return SUCCESS_TIP;
    }

    /**
     * 删除通知管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer appNoticeId) {
        appNoticeService.del(appNoticeId);
        return SUCCESS_TIP;
    }

    /**
     * 修改通知管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(AppNotice appNotice) {
        appNoticeService.update(appNotice);
        return SUCCESS_TIP;
    }

    /**
     * 通知管理详情
     */
    @RequestMapping(value = "/detail/{appNoticeId}")
    @ResponseBody
    public Object detail(@PathVariable("appNoticeId") Integer appNoticeId) {
        return appNoticeService.getdetail(appNoticeId);
    }
}
