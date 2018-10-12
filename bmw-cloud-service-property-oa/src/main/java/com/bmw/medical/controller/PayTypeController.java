package com.bmw.medical.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.model.Menu;
import com.bmw.medical.model.PayType;
import com.bmw.medical.service.IPayTypeService;

/**
 * 收费项目分类控制器
 *
 * @author liuwsh
 * @Date 2018-09-18 10:03:14
 */
@Controller
@RequestMapping("/payType")
public class PayTypeController extends BaseController {

    private String PREFIX = "/medical/payType/";

    @Autowired
    private IPayTypeService payTypeService;

    /**
     * 跳转到收费项目分类首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "payType.html";
    }

    /**
     * 跳转到添加收费项目分类
     */
    @RequestMapping("/payType_add")
    public String payTypeAdd() {
        return PREFIX + "payType_add.html";
    }

    /**
     * 跳转到修改收费项目分类
     */
    @RequestMapping("/payType_update/{payTypeId}")
    public String payTypeUpdate(@PathVariable Integer payTypeId, Model model) {
        PayType payType = payTypeService.selectById(payTypeId);
        model.addAttribute("item",payType);
        PayType temp = new PayType();
        temp.setCode(payType.getParentCode());
        temp = payTypeService.selectOne(temp);
        if(null!=temp) {
        	model.addAttribute("parentName",temp.getName());
        }
        LogObjectHolder.me().set(payType);
        return PREFIX + "payType_edit.html";
    }

    /**
     * 获取收费项目分类列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(PayType payType) {
        return payTypeService.selectList(payType);
    }

    /**
     * 新增收费项目分类
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(PayType payType) {
        payTypeService.insert(payType);
        return SUCCESS_TIP;
    }

    /**
     * 删除收费项目分类
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer payTypeId) {
        payTypeService.deleteById(payTypeId);
        return SUCCESS_TIP;
    }

    /**
     * 修改收费项目分类
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(PayType payType) {
        payTypeService.update(payType);
        return SUCCESS_TIP;
    }

    /**
     * 收费项目分类详情
     */
    @RequestMapping(value = "/detail/{payTypeId}")
    @ResponseBody
    public Object detail(@PathVariable("payTypeId") Integer payTypeId) {
        return payTypeService.selectById(payTypeId);
    }
    
    /**
     * 获取菜单列表(选择父级菜单用)
     */
    @RequestMapping(value = "/selectPayTypeTreeList")
    @ResponseBody
    public List<ZTreeNode> selectPayTypeTreeList() {
        List<ZTreeNode> ZTreeList = this.payTypeService.payTypeTreeList();
        ZTreeList.add(ZTreeNode.createParent());
        return ZTreeList;
    }
}
