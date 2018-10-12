package com.bmw.medical.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.bmw.medical.model.ScheduleHis;
import com.bmw.medical.service.IScheduleHisService;
import com.bmw.medical.warpper.ScheduleHisWarpper;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 排班历史控制器
 *
 * @author fengshuonan
 * @Date 2018-06-07 13:18:57
 */
@Controller
@RequestMapping("/scheduleHis")
public class ScheduleHisController extends BaseController {

    private String PREFIX = "/medical/scheduleHis/";

    @Autowired
    private IScheduleHisService scheduleHisService;

    /**
     * 跳转到排班历史首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "scheduleHis.html";
    }

    /**
     * 跳转到添加排班历史
     */
    @RequestMapping("/scheduleHis_add")
    public String scheduleHisAdd() {
        return PREFIX + "scheduleHis_add.html";
    }

    /**
     * 跳转到修改排班历史
     */
    @RequestMapping("/scheduleHis_update/{scheduleHisId}")
    public String scheduleHisUpdate(@PathVariable Integer scheduleHisId, Model model) {
        ScheduleHis scheduleHis = scheduleHisService.getdetail(scheduleHisId);
        model.addAttribute("item",scheduleHis);
        LogObjectHolder.me().set(scheduleHis);
        return PREFIX + "scheduleHis_edit.html";
    }

    /**
     * 获取排班历史列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	return super.warpObject(new ScheduleHisWarpper(scheduleHisService.findList(condition)));
    }

    /**
     * 新增排班历史
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(ScheduleHis scheduleHis) {
        scheduleHisService.add(scheduleHis);
        return SUCCESS_TIP;
    }

    /**
     * 删除排班历史
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer scheduleHisId) {
        scheduleHisService.del(scheduleHisId);
        return SUCCESS_TIP;
    }

    /**
     * 修改排班历史
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(ScheduleHis scheduleHis) {
        scheduleHisService.update(scheduleHis);
        return SUCCESS_TIP;
    }

    /**
     * 排班历史详情
     */
    @RequestMapping(value = "/detail/{scheduleHisId}")
    @ResponseBody
    public Object detail(@PathVariable("scheduleHisId") Integer scheduleHisId) {
        return scheduleHisService.getdetail(scheduleHisId);
    }
}
