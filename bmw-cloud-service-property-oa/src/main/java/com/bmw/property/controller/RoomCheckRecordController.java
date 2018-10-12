package com.bmw.property.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.ErrorTip;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.modular.system.service.IUserService;
import com.bmw.property.model.RoomCheckRecord;
import com.bmw.property.service.IRoomCheckRecordService;

import cn.hutool.core.date.DateUtil;


/**
 * 收房验房控制器
 *
 * @author zhangchengjun
 * @Date 2018-07-09 13:12:48
 */
@Controller
@RequestMapping("/roomCheckRecord")
public class RoomCheckRecordController extends BaseController {

    private String PREFIX = "/property/roomCheckRecord/";

    @Autowired
    private IRoomCheckRecordService roomCheckRecordService;

    @Autowired
    private IUserService userService;
    /**
     * 跳转到收房验房首页
     */
    @RequestMapping("")
    public String index(Model model) {
    	JSONArray userList = userService.selectUsers(null, null, null, null,null);
		model.addAttribute("userList", userList);
        return PREFIX + "roomCheckRecord.html";
    }

    /**
     * 跳转到添加收房验房
     */
    @RequestMapping("/roomCheckRecord_add")
    public String roomCheckRecordAdd(Model model) {
    	JSONArray userList = userService.selectUsers(null, null, null, null,null);
		model.addAttribute("userList", userList);
    	JSONArray statusList=ConstantFactory.me().findDictByCode("yfsfzt");
		model.addAttribute("statusList", statusList);
        return PREFIX + "roomCheckRecord_add.html";
    }

    /**
     * 跳转到修改收房验房
     */
    @RequestMapping("/roomCheckRecord_update/{roomCheckRecordId}")
    public String roomCheckRecordUpdate(@PathVariable Integer roomCheckRecordId, Model model) {
        RoomCheckRecord roomCheckRecord = roomCheckRecordService.detail(roomCheckRecordId);
        roomCheckRecord.setCheckTimeString(DateUtil.format(roomCheckRecord.getCheckTime(), "yyyy-MM-dd HH:mm:ss"));
        JSONArray userList = userService.selectUsers(null, null, null, null,null);
		model.addAttribute("userList", userList);
    	JSONArray statusList=ConstantFactory.me().findDictByCode("yfsfzt");
		model.addAttribute("statusList", statusList);
        model.addAttribute("item",roomCheckRecord);
        LogObjectHolder.me().set(roomCheckRecord);
        return PREFIX + "roomCheckRecord_edit.html";
    }
    
    /**
     * 收房验房详情
     */
    @RequestMapping(value = "/roomCheckRecord_detail/{roomCheckRecordId}")
    public String detail(@PathVariable("roomCheckRecordId") Integer roomCheckRecordId, Model model) {
        RoomCheckRecord roomCheckRecord = roomCheckRecordService.detail(roomCheckRecordId);
        roomCheckRecord.setCheckTimeString(DateUtil.format(roomCheckRecord.getCheckTime(), "yyyy-MM-dd HH:mm:ss"));
        model.addAttribute("item",roomCheckRecord);
        LogObjectHolder.me().set(roomCheckRecord);
        return PREFIX + "roomCheckRecord_detail.html";
    }

    /**
     * 获取收房验房列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String ownerName,
							@RequestParam(required = false) Integer roomId, 
							@RequestParam(required = false) Integer principalUserId,
							@RequestParam(required = false) String contact) {
    	Map<String,Object> param=new HashMap<>(); 
    	Page<RoomCheckRecord> page=new PageFactory<RoomCheckRecord>().defaultPage();
    	param.put("pageSize", page.getSize());
    	param.put("pageNum", page.getCurrent());
    	param.put("ownerName", ownerName);
    	param.put("roomId", roomId);
    	param.put("principalUserId",principalUserId );
    	param.put("contact", contact);
    	param.put("eId",ShiroKit.getUser().geteId());
        JSONObject json= roomCheckRecordService.selectByParams(param);
		page.setTotal(json.getInteger("total"));
		page.setRecords(json.getJSONArray("result").toJavaList(RoomCheckRecord.class));
		return super.packForBT(page);
    }

    /**
     * 新增收房验房
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(RoomCheckRecord roomCheckRecord) {
    	ShiroUser shiroUser = ShiroKit.getUser();
    	roomCheckRecord.seteId(ShiroKit.getUser().geteId());
    	roomCheckRecord.setPrincipalUserId(shiroUser.getId());
    	roomCheckRecord.setPrincipalUserName(shiroUser.getName());
    	roomCheckRecord.setCreateManId(shiroUser.getId());
    	roomCheckRecord.setCreateMan(shiroUser.getName());
    	roomCheckRecord.setCreateTime(new Date());
        roomCheckRecordService.insert(roomCheckRecord);
        return SUCCESS_TIP;
    }
    
    /**
     * 修改收房验房
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(RoomCheckRecord roomCheckRecord) {    	
    	RoomCheckRecord param=new RoomCheckRecord();
    	ShiroUser shiroUser = ShiroKit.getUser();
    	param.setEditManId(shiroUser.getId());
    	param.setEditMan(shiroUser.getName());
        Boolean flag=roomCheckRecordService.updateById(roomCheckRecord);
        if(flag) {
    		return SUCCESS_TIP;
    	}else {
    		return new ErrorTip(500,"编辑失败");
    	}
    }


    /**
     * 删除收房验房
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer roomCheckRecordId) {
    	RoomCheckRecord param=new RoomCheckRecord();
    	ShiroUser shiroUser = ShiroKit.getUser();
    	param.setEditManId(shiroUser.getId());
    	param.setEditMan(shiroUser.getName());
    	param.setId(roomCheckRecordId);
    	Boolean flag=roomCheckRecordService.deleteById(param);
    	if(flag) {
    		return SUCCESS_TIP;
    	}else {
    		return new ErrorTip(500,"删除失败");
    	}
        
    }
    
}
