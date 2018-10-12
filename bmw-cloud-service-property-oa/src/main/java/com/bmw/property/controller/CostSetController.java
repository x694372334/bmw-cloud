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
import com.stylefeng.guns.core.base.tips.Tip;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.bmw.property.model.CostSet;
import com.bmw.property.service.ICostSetService;
import com.bmw.property.warpper.CostSetWarpper;

/**
 * 类名: CostSetController  
 * 类描述: 费项设置处理类
 * 创建人: wangliqing
 * 创建时间 : 2018年6月26日 下午2:02:28    
 */
@Controller
@RequestMapping("/costSet")
public class CostSetController extends BaseController {

	//HTML页面路径前缀
    private String PREFIX = "/property/costSet/";

    //注入服务接口
    @Autowired
    private ICostSetService costSetService;

    /**
      * 方法名称 : index
      * 作者 : wangliqing  
      * 方法描述 : 跳转到费项设置首页
      * 创建时间 : 2018年6月26日 下午2:03:46    
      * 返回类型 : String    
      */
    @RequestMapping("")
    public String index() {
        return PREFIX + "costSet.html";
    }

    /**
      * 方法名称 : costSetAdd
      * 作者 : wangliqing  
      * 方法描述 : 跳转到添加费项设置
      * 创建时间 : 2018年6月26日 下午2:04:12    
      * 参数 : @param model
      * 返回类型 : String    
      */
    @RequestMapping("/costSet_add")
    public String costSetAdd(Model model) {
        return PREFIX + "costSet_add.html";
    }
    
    
    /**
      * 方法名称 : costSetUpdate
      * 作者 : wangliqing  
      * 方法描述 : 跳转到费项设置修改页面
      * 创建时间 : 2018年6月26日 下午2:04:42    
      * 参数 : @param costSetId
      * 参数 : @param model
      * 返回类型 : String    
     * @throws Exception 
      */
    @RequestMapping("/costSet_update/{costSetId}")
    public String costSetUpdate(@PathVariable Integer costSetId, Model model) throws Exception {
        CostSet costSet = costSetService.selectById(costSetId);
        model.addAttribute("item",costSet);
        Tip tip = costSetService.isEdit(costSetId);
        model.addAttribute("isEdit", tip.getMessage());
        LogObjectHolder.me().set(costSet);
        return PREFIX + "costSet_edit.html";
    }
    
    /**
      * 方法名称 : costSetDetail
      * 作者 : wangliqing  
      * 方法描述 : 跳转到费项设置详情页面
      * 创建时间 : 2018年6月26日 下午2:05:04    
      * 参数 : @param costSetId
      * 参数 : @param model
      * 返回类型 : String    
      */
    @RequestMapping("/costSet_detail/{costSetId}")
    public String costSetDetail(@PathVariable Integer costSetId, Model model) {
        CostSet costSet = costSetService.selectById(costSetId);
        model.addAttribute("item",costSet);
        LogObjectHolder.me().set(costSet);
        return PREFIX + "costSet_detail.html";
    }
    

    /**
      * 方法名称 : list
      * 作者 : wangliqing  
      * 方法描述 : 获取费项设置列表(分页)
      * 创建时间 : 2018年6月26日 下午2:05:33    
      * 参数 : @param costTypeId
      * 返回类型 : Object    
      */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Integer costTypeId) {
    	Map<String,Object> param=new HashMap<String,Object>();
    	Page<CostSet> page= new PageFactory<CostSet>().defaultPage();
    	param.put("costTypeId",costTypeId);
    	param.put("pageSize", page.getSize());
    	param.put("pageNum", page.getCurrent());
    	param.put("orderByField", page.getOrderByField());
    	param.put("isAsc", page.isAsc());
    	JSONObject json = costSetService.selectList(param);
    	page.setTotal(json.getInteger("total"));
    	page.setRecords((List<CostSet>) new CostSetWarpper(json.getJSONArray("result")).warp());
        return super.packForBT(page);
    }
    
    /**
      * 方法名称 : getCostSetAll
      * 作者 : wangliqing  
      * 方法描述 : 获取所有费项设置记录（用于下拉列表）
      * 创建时间 : 2018年6月26日 下午2:06:16    
      * 返回类型 : Object    
      */
    @RequestMapping(value = "/getCostSetAll")
    @ResponseBody
    public Object getCostSetAll() {
    	JSONArray jsonArray = costSetService.getCostSetAll(null);
    	String jsonStr = JSONObject.toJSONString(jsonArray);
    	List<CostSet>  costList = JSONObject.parseArray(jsonStr,CostSet.class);
        return costList;
    }
    
    @ResponseBody
    @RequestMapping(value={"/getCostSetAll/{relevanceId}"})
    public Object getCostSetAll(@PathVariable Integer relevanceId) {
    	JSONArray jsonArray = costSetService.getCostSetAll(relevanceId);
    	String jsonStr = JSONObject.toJSONString(jsonArray);
    	List<CostSet>  costList = JSONObject.parseArray(jsonStr,CostSet.class);
        return costList;
    }
    
    /**
      * 方法名称 : add
      * 作者 : wangliqing  
      * 方法描述 : 新增费项设置
      * 创建时间 : 2018年6月26日 下午2:06:58    
      * 参数 : @param costSet
      * 返回类型 : Object    
      */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CostSet costSet) {
        costSetService.insert(costSet);
        return SUCCESS_TIP;
    }

    /**
      * 方法名称 : delete
      * 作者 : wangliqing  
      * 方法描述 : 删除费项设置(逻辑删除)
      * 创建时间 : 2018年6月26日 下午2:07:15    
      * 参数 : @param costSetId
      * 返回类型 : Object    
     * @throws Exception 
      */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer costSetId) throws Exception {
        return costSetService.deleteById(costSetId);
    }
    
    /**
      * 方法名称 : isEdit
      * 作者 : wangliqing  
      * 方法描述 : 判断是否能够删修改
      * 创建时间 : 2018年8月1日 下午1:34:59    
      * 参数 : @param costSetId
      * 参数 : @throws Exception  
      * 返回类型 : Object    
      * @throws
     */
    @RequestMapping(value = "/isEdit")
    @ResponseBody
    public Object isEdit(@RequestParam Integer costSetId) throws Exception {
        return costSetService.isEdit(costSetId);
    }
    
    

    /**
      * 方法名称 : update
      * 作者 : wangliqing  
      * 方法描述 : 修改费项设置
      * 创建时间 : 2018年6月26日 下午2:08:07    
      * 参数 : @param costSet
      * 返回类型 : Object    
      */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CostSet costSet) {
        costSetService.updateById(costSet);
        return SUCCESS_TIP;
    }

}
