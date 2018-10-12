package com.bmw.medical.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.bmw.medical.service.IScheduleDetailHisService;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 排班历史明细控制器
 *
 * @author fengshuonan
 * @Date 2018-06-07 13:18:52
 */
@Controller
@RequestMapping("/scheduleDetailHis")
public class ScheduleDetailHisController extends BaseController {

    private String PREFIX = "/medical/scheduleDetailHis/";

    @Autowired
    private IScheduleDetailHisService scheduleDetailHisService;

    /**
     * 跳转到排班历史明细首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "scheduleDetailHis.html";
    }

    /**
     * 跳转到添加排班历史明细
     */
    @RequestMapping("/scheduleDetailHis_add")
    public String scheduleDetailHisAdd() {
        return PREFIX + "scheduleDetailHis_add.html";
    }

    /**
     * 跳转到修改排班历史明细
     */
    @RequestMapping("/scheduleDetailHis_update/{scheduleDetailHisId}")
    public String scheduleDetailHisUpdate(@PathVariable Integer scheduleDetailHisId, Model model) {
//        ScheduleDetailHis scheduleDetailHis = scheduleDetailHisService.selectById(scheduleDetailHisId);
//        model.addAttribute("item",scheduleDetailHis);
//        LogObjectHolder.me().set(scheduleDetailHis);
        return PREFIX + "scheduleDetailHis_edit.html";
    }

    /**
     * 获取排班历史明细列表
     */
//    @RequestMapping(value = "/list")
//    @ResponseBody
//    public Object list(String condition) {
//        return scheduleDetailHisService.findById(null);
//    }

    /**
     * 新增排班历史明细
     */
//    @RequestMapping(value = "/add")
//    @ResponseBody
//    public Object add(ScheduleDetailHis scheduleDetailHis) {
//        scheduleDetailHisService.insert(scheduleDetailHis);
//        return SUCCESS_TIP;
//    }

    /**
     * 删除排班历史明细
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer scheduleDetailHisId) {
//        scheduleDetailHisService.deleteById(scheduleDetailHisId);
        return SUCCESS_TIP;
    }

    /**
     * 修改排班历史明细
     */
//    @RequestMapping(value = "/update")
//    @ResponseBody
//    public Object update(ScheduleDetailHis scheduleDetailHis) {
//        scheduleDetailHisService.updateById(scheduleDetailHis);
//        return SUCCESS_TIP;
//    }

    /**
     * 排班历史明细详情
     */
//    @RequestMapping(value = "/detail/{scheduleDetailHisId}")
//    @ResponseBody
//    public Object detail(@PathVariable("scheduleDetailHisId") Integer scheduleDetailHisId) {
//        return scheduleDetailHisService.selectById(scheduleDetailHisId);
//    }
}
