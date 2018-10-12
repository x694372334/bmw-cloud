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
import com.bmw.app.model.Commodity;
import com.bmw.app.service.ICommodityService;
import com.bmw.app.warpper.CommodityWarpper;

/**
 * 商品维护控制器
 *
 * @author fjm
 * @Date 2018-05-17 08:53:20
 */
@Controller
@RequestMapping("/commodity")
public class CommodityController extends BaseController {

    private String PREFIX = "/app/commodity/";

    @Autowired
    private ICommodityService commodityService;

    /**
     * 跳转到商品维护首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "commodity.html";
    }

    /**
     * 跳转到添加商品维护
     */
    @RequestMapping("/commodity_add")
    public String commodityAdd() {
        return PREFIX + "commodity_add.html";
    }

    /**
     * 跳转到修改商品维护
     */
    @RequestMapping("/commodity_update/{commodityId}")
    public String commodityUpdate(@PathVariable Integer commodityId, Model model) {
        Commodity commodity = commodityService.getdetail(commodityId);
        model.addAttribute("item",commodity);
        LogObjectHolder.me().set(commodity);
        return PREFIX + "commodity_edit.html";
    }

    /**
     * 获取商品维护列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return super.warpObject(new CommodityWarpper(commodityService.findList(condition)));
    }

    /**
     * 新增商品维护
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Commodity commodity) {
        commodityService.add(commodity);
        return SUCCESS_TIP;
    }

    /**
     * 删除商品维护
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer commodityId) {
        commodityService.del(commodityId);
        return SUCCESS_TIP;
    }

    /**
     * 修改商品维护
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Commodity commodity) {
        commodityService.update(commodity);
        return SUCCESS_TIP;
    }

    /**
     * 商品维护详情
     */
    @RequestMapping(value = "/detail/{commodityId}")
    @ResponseBody
    public Object detail(@PathVariable("commodityId") Integer commodityId) {
        return commodityService.getdetail(commodityId);
    }
}
