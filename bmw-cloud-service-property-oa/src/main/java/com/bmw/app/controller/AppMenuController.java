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
import com.bmw.app.model.AppMenu;
import com.bmw.app.service.IAppMenuService;
import com.bmw.app.warpper.AppMenuWarpper;

/**
 * 菜单管理控制器
 *
 * @author fengshuonan
 * @Date 2018-05-17 10:25:21
 */
@Controller
@RequestMapping("/appMenu")
public class AppMenuController extends BaseController {

    private String PREFIX = "/app/appMenu/";

    @Autowired
    private IAppMenuService appMenuService;

    /**
     * 跳转到菜单管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "appMenu.html";
    }

    /**
     * 跳转到添加菜单管理
     */
    @RequestMapping("/appMenu_add")
    public String appMenuAdd() {
        return PREFIX + "appMenu_add.html";
    }

    /**
     * 跳转到修改菜单管理
     */
    @RequestMapping("/appMenu_update/{appMenuId}")
    public String appMenuUpdate(@PathVariable Integer appMenuId, Model model) {
        AppMenu appMenu = appMenuService.getdetail(appMenuId);
        model.addAttribute("item",appMenu);
        LogObjectHolder.me().set(appMenu);
        return PREFIX + "appMenu_edit.html";
    }

    /**
     * 获取菜单管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return super.warpObject(new AppMenuWarpper(appMenuService.findList(condition)));
    }

    /**
     * 新增菜单管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(AppMenu appMenu) {
        appMenuService.add(appMenu);
        return SUCCESS_TIP;
    }

    /**
     * 删除菜单管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer appMenuId) {
        appMenuService.del(appMenuId);
        return SUCCESS_TIP;
    }

    /**
     * 修改菜单管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(AppMenu appMenu) {
        appMenuService.update(appMenu);
        return SUCCESS_TIP;
    }

    /**
     * 菜单管理详情
     */
    @RequestMapping(value = "/detail/{appMenuId}")
    @ResponseBody
    public Object detail(@PathVariable("appMenuId") Integer appMenuId) {
        return appMenuService.getdetail(appMenuId);
    }
}
