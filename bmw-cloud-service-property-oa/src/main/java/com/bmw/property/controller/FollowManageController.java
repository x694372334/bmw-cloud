package com.bmw.property.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.node.TreeViewNode;
import com.stylefeng.guns.core.util.DateUtil;
import com.bmw.property.model.FollowManage;
import com.bmw.property.service.IFollowManageService;
import com.bmw.property.warpper.FollowManageWarpper;

/**
 * 类名: FollowManageController  
 * 类描述: 跟进管理控制器
 * 创建人: wangliqing
 * 创建时间 : 2018年6月26日 下午2:15:12    
 */
@Controller
@RequestMapping("/followManage")
public class FollowManageController extends BaseController {

	//HTML页面路径前缀
    private String PREFIX = "/property/followManage/";

    //注入服务接口
    @Autowired
    private IFollowManageService followManageService;

    /**
      * 方法名称 : index
      * 作者 : wangliqing  
      * 方法描述 : 跳转到首页
      * 创建时间 : 2018年6月26日 下午2:16:28    
      * 返回类型 : String    
      */
    @RequestMapping("")
    public String index() {
        return PREFIX + "followManage.html";
    }

    /**
      * 方法名称 : followManageAdd
      * 作者 : wangliqing  
      * 方法描述 :跳转到添加页面
      * 创建时间 : 2018年6月26日 下午2:16:58    
      * 返回类型 : String    
      */
    @RequestMapping("/followManage_add")
    public String followManageAdd() {
        return PREFIX + "followManage_add.html";
    }

    /**
      * 方法名称 : followManageUpdate
      * 作者 : wangliqing  
      * 方法描述 :跳转到修改页面
      * 创建时间 : 2018年6月26日 下午2:17:20    
      * 参数 : @param followManageId
      * 参数 : @param model
      * 返回类型 : String    
      */
    @RequestMapping("/followManage_update/{followManageId}")
    public String followManageUpdate(@PathVariable Integer followManageId, Model model) {
        FollowManage followManage = followManageService.selectById(followManageId);
        if(null != followManage.getContactTime()) {
        	followManage.setContactTimeStr(DateUtil.format(followManage.getContactTime(), "yyyy-MM-dd HH:mm"));
        }
        model.addAttribute("item",followManage);
        LogObjectHolder.me().set(followManage);
        return PREFIX + "followManage_edit.html";
    }
    
    /**
      * 方法名称 : followManageDetail
      * 作者 : wangliqing  
      * 方法描述 : 跳转到详情页面
      * 创建时间 : 2018年6月26日 下午2:17:43    
      * 参数 : @param followManageId
      * 参数 : @param model
      * 返回类型 : String    
      */
    @RequestMapping("/followManage_detail/{followManageId}")
    public String followManageDetail(@PathVariable Integer followManageId, Model model) {
        FollowManage followManage = followManageService.selectById(followManageId);
        if(null != followManage.getContactTime()) {
        	followManage.setContactTimeStr(DateUtil.format(followManage.getContactTime(), "yyyy-MM-dd HH:mm"));
        }
        model.addAttribute("item",followManage);
        LogObjectHolder.me().set(followManage);
        return PREFIX + "followManage_detail.html";
    }
    
    

    /**
      * 方法名称 : list
      * 作者 : wangliqing  
      * 方法描述 : 获取跟进管理列表(分页)
      * 创建时间 : 2018年6月26日 下午2:18:09    
      * 参数 : @param ownerName
      * 参数 : @param phone
      * 参数 : @param receiverName
      * 返回类型 : Object    
      */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String ownerName, @RequestParam(required = false) String phone,  @RequestParam(required = false) String receiverName) {
    	
    	Map<String,Object> param=new HashMap<String,Object>();
    	Page<FollowManage> page= new PageFactory<FollowManage>().defaultPage();
    	param.put("ownerName",ownerName);
    	param.put("phone",phone);
    	param.put("receiverName",receiverName);
    	param.put("pageSize", page.getSize());
    	param.put("pageNum", page.getCurrent());
    	param.put("orderByField", page.getOrderByField());
    	param.put("isAsc", page.isAsc());
    	JSONObject json = followManageService.selectList(param);
    	page.setTotal(json.getInteger("total"));
    	page.setRecords((List<FollowManage>) new FollowManageWarpper(json.getJSONArray("result")).warp());
        return super.packForBT(page);
    	
    }
    
    @RequestMapping(value = "/getTreeNode")
    @ResponseBody
    public Object getTreeNode() {
    	
    	return TreeViewNode.getTreeNode();
    	
    }
    
    
    

    /**
      * 方法名称 : add
      * 作者 : wangliqing  
      * 方法描述 : 跟进管理新增方法
      * 创建时间 : 2018年6月26日 下午2:18:54    
      * 参数 : @param followManage
      * 返回类型 : Object    
      */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(FollowManage followManage) {
    	followManageService.insert(followManage);
        return  SUCCESS_TIP;
    }

    /**
      * 方法名称 : delete
      * 作者 : wangliqing  
      * 方法描述 : 跟进管理删除方法
      * 创建时间 : 2018年6月26日 下午2:19:19    
      * 参数 : @param followManageId
      * 返回类型 : Object    
      */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer followManageId) {
        followManageService.deleteById(followManageId);
        return SUCCESS_TIP;
    }

    /**
      * 方法名称 : update
      * 作者 : wangliqing  
      * 方法描述 : 跟进管理修改方法
      * 创建时间 : 2018年6月26日 下午2:19:43    
      * 参数 : @param followManage
      * 返回类型 : Object    
      */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(FollowManage followManage) {
        followManageService.updateById(followManage);
        return SUCCESS_TIP;
    }
}
