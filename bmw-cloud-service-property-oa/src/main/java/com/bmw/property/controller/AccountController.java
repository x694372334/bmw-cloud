package com.bmw.property.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.bmw.property.model.Account;
import com.bmw.property.service.IAccountService;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * account控制器
 *
 * @author fengshuonan
 * @Date 2018-07-09 15:19:43
 */
@Controller
@RequestMapping("/account")
public class AccountController extends BaseController {

    private String PREFIX = "/property/account/";

    @Autowired
    private IAccountService accountService;

    /**
     * 跳转到account首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "account.html";
    }

    /**
     * 跳转到添加account
     */
    @RequestMapping("/account_add")
    public String accountAdd() {
        return PREFIX + "account_add.html";
    }

    /**
     * 跳转到修改account
     */
    @RequestMapping("/account_update/{accountId}")
    public String accountUpdate(@PathVariable Integer accountId, Model model) {
        Account account = accountService.getdetail(accountId);
        model.addAttribute("item",account);
        LogObjectHolder.me().set(account);
        return PREFIX + "account_edit.html";
    }

    /**
     * 获取account列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return accountService.findList(null);
    }

    /**
     * 新增account
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Account account) {
        accountService.add(account);
        return SUCCESS_TIP;
    }

    /**
     * 删除account
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer accountId) {
        accountService.getdetail(accountId);
        return SUCCESS_TIP;
    }

    /**
     * 修改account
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Account account) {
        accountService.update(account);
        return SUCCESS_TIP;
    }

    /**
     * account详情
     */
    @RequestMapping(value = "/detail/{accountId}")
    @ResponseBody
    public Object detail(@PathVariable("accountId") Integer accountId) {
        return accountService.getdetail(accountId);
    }
}
