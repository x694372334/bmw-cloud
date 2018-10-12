package com.bmw.app.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.bmw.app.model.Recommend;
import com.bmw.app.model.SlideShow;
import com.bmw.app.service.IRecommendService;
import com.bmw.app.warpper.RecommendWarpper;
import com.bmw.app.warpper.SlideShowWarpper;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * recommend控制器
 *
 * @author fengshuonan
 * @Date 2018-05-23 15:24:36
 */
@Controller
@RequestMapping("/recommend")
public class RecommendController extends BaseController {

    private String PREFIX = "/app/recommend/";

    @Autowired
    private IRecommendService recommendService;

    /**
     * 跳转到recommend首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "recommend.html";
    }

    /**
     * 跳转到添加recommend
     */
    @RequestMapping("/recommend_add")
    public String recommendAdd() {
        return PREFIX + "recommend_add.html";
    }

    /**
     * 跳转到修改recommend
     */
    @RequestMapping("/recommend_update/{recommendId}")
    public String recommendUpdate(@PathVariable Integer recommendId, Model model) {
        Recommend recommend = recommendService.getdetail(recommendId);
        model.addAttribute("item",recommend);
        LogObjectHolder.me().set(recommend);
        return PREFIX + "recommend_edit.html";
    }


    /**
     * 获取轮播图片管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return super.warpObject(new RecommendWarpper(recommendService.findList(condition)));
    }

    /**
     * 新增轮播图片管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Recommend slideShow) {
    	recommendService.add(slideShow);
        return SUCCESS_TIP;
    }

    /**
     * 删除轮播图片管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer slideShowId) {
    	recommendService.del(slideShowId);
        return SUCCESS_TIP;
    }

    /**
     * 修改轮播图片管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Recommend slideShow) {
    	recommendService.update(slideShow);
        return SUCCESS_TIP;
    }

    /**
     * 轮播图片管理详情
     */
    @RequestMapping(value = "/detail/{slideShowId}")
    @ResponseBody
    public Object detail(@PathVariable("slideShowId") Integer slideShowId) {
        return recommendService.getdetail(slideShowId);
    }
}
