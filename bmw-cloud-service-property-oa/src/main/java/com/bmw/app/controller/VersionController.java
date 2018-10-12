package com.bmw.app.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.beetl.ShiroExt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.system.warpper.DictWarpper;

import org.springframework.web.bind.annotation.RequestParam;
import com.bmw.app.model.Version;
import com.bmw.app.service.IVersionService;
import com.bmw.app.warpper.VersionWarpper;

/**
 * 版本管理控制器
 *
 * @author fengshuonan
 * @Date 2018-05-17 10:24:57
 */
@Controller
@RequestMapping("/version")
public class VersionController extends BaseController {

    private String PREFIX = "/app/version/";

    @Autowired
    private IVersionService versionService;

    /**
     * 跳转到版本管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "version.html";
    }

    /**
     * 跳转到添加版本管理
     */
    @RequestMapping("/version_add")
    public String versionAdd() {
        return PREFIX + "version_add.html";
    }

    /**
     * 跳转到修改版本管理
     */
    @RequestMapping("/version_update/{versionId}")
    public String versionUpdate(@PathVariable Integer versionId, Model model) {
        Version version = versionService.getdetail(versionId);
        model.addAttribute("item",version);
        LogObjectHolder.me().set(version);
        return PREFIX + "version_edit.html";
    }

    /**
     * 获取版本管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return versionService.findList(condition);
    }

    /**
     * 新增版本管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Version version) {
    	ShiroExt shiro = new ShiroExt();
		String userName = shiro.getUser().getName();
		version.setCreateBy(userName);
		version.setCreateTime(new Date());
        versionService.add(version);
        return SUCCESS_TIP;
    }

    /**
     * 删除版本管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer versionId) {
        versionService.del(versionId);
        return SUCCESS_TIP;
    }

    /**
     * 修改版本管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Version version) {
    	ShiroExt shiro = new ShiroExt();
    	String userName = shiro.getUser().getName();
		version.setGradeBy(userName);
		version.setGradeTime(new Date());
        versionService.update(version);
        return SUCCESS_TIP;
    }

    /**
     * 版本管理详情
     */
    @RequestMapping(value = "/detail/{versionId}")
    @ResponseBody
    public Object detail(@PathVariable("versionId") Integer versionId) {
        return versionService.getdetail(versionId);
    }
}
