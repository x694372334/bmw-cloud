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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.bmw.property.model.CostSet;
import com.bmw.property.model.CostStandard;
import com.bmw.property.service.ICostStandardService;
import com.bmw.property.warpper.CostStandardWarpper;

/**
 * 类名: CostStandardController  
 * 类描述: 收费标准处理类 
 * 创建人: wangliqing
 * 创建时间 : 2018年6月26日 下午2:09:15    
 */
@Controller
@RequestMapping("/costStandard")
public class CostStandardController extends BaseController {

	//HTML页面路径前缀
    private String PREFIX = "/property/costStandard/";

    //注入服务接口
    @Autowired
    private ICostStandardService costStandardService;

    /**
      * 方法名称 : index
      * 作者 : wangliqing  
      * 方法描述 : 跳转到收费标准首页
      * 创建时间 : 2018年6月26日 下午2:10:20    
      * 返回类型 : String    
      */
    @RequestMapping("")
    public String index() {
        return PREFIX + "costStandard.html";
    }

    /**
      * 方法名称 : costStandardAdd
      * 作者 : wangliqing  
      * 方法描述 : 跳转到收费标准添加页面
      * 创建时间 : 2018年6月26日 下午2:10:59    
      * 返回类型 : String    
      */
    @RequestMapping("/costStandard_add")
    public String costStandardAdd() {
        return PREFIX + "costStandard_add.html";
    }

    /**
      * 方法名称 : costStandardUpdate
      * 作者 : wangliqing  
      * 方法描述 : 跳转到收费标准修改页面
      * 创建时间 : 2018年6月26日 下午2:11:14    
      * 参数 : @param costStandardId
      * 参数 : @param model
      * 返回类型 : String    
      */
    @RequestMapping("/costStandard_update/{costStandardId}")
    public String costStandardUpdate(@PathVariable Integer costStandardId, Model model) {
        CostStandard costStandard = costStandardService.selectById(costStandardId);
        model.addAttribute("item",costStandard);
        LogObjectHolder.me().set(costStandard);
        return PREFIX + "costStandard_edit.html";
    }
    
    /**
      * 方法名称 : costStandardDetail
      * 作者 : wangliqing  
      * 方法描述 : 跳转到收费标准详情页面
      * 创建时间 : 2018年6月26日 下午2:11:30    
      * 参数 : @param costStandardId
      * 参数 : @param model
      * 返回类型 : String    
      */
    @RequestMapping("/costStandard_detail/{costStandardId}")
    public String costStandardDetail(@PathVariable Integer costStandardId, Model model) {
        CostStandard costStandard = costStandardService.selectById(costStandardId);
        model.addAttribute("item",costStandard);
        LogObjectHolder.me().set(costStandard);
        return PREFIX + "costStandard_detail.html";
    }

    /**
      * 方法名称 : list
      * 作者 : wangliqing  
      * 方法描述 : 获取收费标准列表(分页)
      * 创建时间 : 2018年6月26日 下午2:12:21    
      * 参数 : @param condition
      * 参数 : @return  
      * 返回类型 : Object    
      * @throws
      */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition,Integer isDelete) {
    	
    	if(isDelete == null) {
    		isDelete = 1;
    	}
    	Map<String,Object> param=new HashMap<String,Object>();
    	Page<CostStandard> page= new PageFactory<CostStandard>().defaultPage();
    	param.put("condition",condition);
    	param.put("isDelete",isDelete);
    	param.put("pageSize", page.getSize());
    	param.put("pageNum", page.getCurrent());
    	param.put("orderByField", page.getOrderByField());
    	param.put("isAsc", page.isAsc());
    	JSONObject json = costStandardService.selectList(param);
    	page.setTotal(json.getInteger("total"));
    	page.setRecords((List<CostStandard>) new CostStandardWarpper(json.getJSONArray("result")).warp());
        return super.packForBT(page);
    }

    /**
      * 方法名称 : add
      * 作者 : wangliqing  
      * 方法描述 : 新增收费标准
      * 创建时间 : 2018年6月26日 下午2:12:40    
      * 参数 : @param costStandard 主键ID
      * 返回类型 : Object    
      */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CostStandard costStandard) {
        costStandardService.insert(costStandard);
        return SUCCESS_TIP;
    }

    /**
      * 方法名称 : delete
      * 作者 : wangliqing  
      * 方法描述 : 删除收费标准
      * 创建时间 : 2018年6月26日 下午2:13:17    
      * 参数 : @param costStandardId 主键ID
      * 返回类型 : Object    
     * @throws Exception 
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer costStandardId) throws Exception {
        return costStandardService.deleteById(costStandardId);
    }

    /**
      * 方法名称 : update
      * 作者 : wangliqing  
      * 方法描述 :修改收费标准
      * 创建时间 : 2018年6月26日 下午2:13:47    
      * 参数 : @param costStandard 主键ID
      * 返回类型 : Object    
      */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CostStandard costStandard) {
        costStandardService.updateById(costStandard);
        return SUCCESS_TIP;
    }
    
    /**
      * 方法名称 : getCostStandardsByCostId
      * 作者 : wangliqing  
      * 方法描述 : 根据费项设置主键查询费用标准列表
      * 创建时间 : 2018年6月28日 上午10:18:37    
      * 参数 : @param costId
      * 返回类型 : Object    
      */
    @RequestMapping(value = "/getCostStandardsByCostId")
    @ResponseBody
    public Object getCostStandardsByCostId(@RequestParam Integer costId) {
    	JSONArray jsonArray = costStandardService.getCostStandardsByCostId(costId);
    	String jsonStr = JSONObject.toJSONString(jsonArray);
    	List<CostStandard>  costStandardList = JSONObject.parseArray(jsonStr,CostStandard.class);
        return costStandardList;
    }

}
