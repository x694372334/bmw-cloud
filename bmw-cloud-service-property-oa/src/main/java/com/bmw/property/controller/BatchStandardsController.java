package com.bmw.property.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.ParseException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.SuccessTip;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.bmw.property.model.BatchStandards;
import com.bmw.property.model.CostSet;
import com.bmw.property.model.ParkingInfo;
import com.bmw.property.service.IBatchStandardsService;
import com.bmw.property.warpper.BatchStandardsWarpper;

/**
 * 批量关联收费标准控制器
 *
 * @author fengshuonan
 * @Date 2018-06-28 08:38:30
 */
@Controller
@RequestMapping("/batchStandards")
public class BatchStandardsController extends BaseController {

    private String PREFIX = "/property/batchStandards/";

    @Autowired
    private IBatchStandardsService batchStandardsService;

    /**
     * 跳转到批量关联收费标准首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "batchStandards.html";
    }

    /**
     * 跳转到添加批量关联收费标准
     */
    @RequestMapping("/batchStandards_add")
    public String batchStandardsAdd() {
        return PREFIX + "batchStandards_add.html";
    }

    /**
     * 跳转到修改批量关联收费标准
     */
    @RequestMapping("/batchStandards_update/{batchStandardsId}")
    public String batchStandardsUpdate(@PathVariable Integer batchStandardsId, Model model) {
        BatchStandards batchStandards = batchStandardsService.selectById(batchStandardsId);
        model.addAttribute("item",batchStandards);
        LogObjectHolder.me().set(batchStandards);
        return PREFIX + "batchStandards_edit.html";
    }
    
    /**
	      * 方法名称 : addScopeIds
	      * 作者 : wangliqing  
	      * 方法描述 : 添加关联范围
	      * 创建时间 : 2018年8月2日 上午9:23:48    
	      * 参数 : @param batchStandardsId
	      * 参数 : @param model
	      * 参数 : @return  
	      * 返回类型 : String    
      * @throws
     */
    @RequestMapping("/add_scopeIds/{batchStandardsId}")
    public String addScopeIds(@PathVariable Integer batchStandardsId, Model model) {
        BatchStandards batchStandards = batchStandardsService.selectById(batchStandardsId);
        model.addAttribute("item",batchStandards);
        LogObjectHolder.me().set(batchStandards);
        return PREFIX + "add_scopeIds.html";
    }
    

    /**
     * 获取批量关联收费标准列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) Integer costId, @RequestParam(required = false) Integer neighborhoodsId) {
    	Map<String,Object> param=new HashMap<String,Object>();
    	Page<CostSet> page= new PageFactory<CostSet>().defaultPage();
    	param.put("costId",costId);
    	param.put("neighborhoodsId",neighborhoodsId);
    	param.put("pageSize", page.getSize());
    	param.put("pageNum", page.getCurrent());
    	param.put("orderByField", page.getOrderByField());
    	param.put("isAsc", page.isAsc());
    	JSONObject json = batchStandardsService.selectList(param);
    	page.setTotal(json.getInteger("total"));
    	page.setRecords((List<CostSet>) new BatchStandardsWarpper(json.getJSONArray("result")).warp());
        return super.packForBT(page);
    }

  
    /**
	      * 方法名称 : addScopeIds
	      * 作者 : wangliqing  
	      * 方法描述 : 保存关联范围
	      * 创建时间 : 2018年8月2日 下午3:46:28    
	      * 参数 : @param scopeIds
	      * 参数 : @return  
	      * 返回类型 : Object    
      * @throws
     */
    @RequestMapping(value = "/addScopeIds")
    @ResponseBody
    public Object addScopeIds(BatchStandards batchStandards) {
        return batchStandardsService.addScopeIds(batchStandards);
    }
    
    /**
      * 方法名称 : deleteBill
      * 作者 : wangliqing  
      * 方法描述 : 删除账单信息
      * 创建时间 : 2018年8月3日 上午10:38:59    
      * 参数 : @param batchStandards
      * 参数 : @return  
      * 返回类型 : Object    
      * @throws
     */
    @RequestMapping(value = "/deleteBill")
    @ResponseBody
    public Object deleteBill(BatchStandards batchStandards) {
        return batchStandardsService.deleteBill(batchStandards);
    }

    
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BatchStandards batchStandards) {
        return batchStandardsService.insert(batchStandards);
    }
    
    /**
     * 删除批量关联收费标准
     * @throws Exception 
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer batchStandardsId) throws Exception {
        return batchStandardsService.deleteById(batchStandardsId);
    }

    /**
     * 修改批量关联收费标准
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BatchStandards batchStandards) {
        batchStandardsService.updateById(batchStandards);
        return SUCCESS_TIP;
    }

    /**
     * 批量关联收费标准详情
     */
    @RequestMapping(value = "/detail/{batchStandardsId}")
    @ResponseBody
    public Object detail(@PathVariable("batchStandardsId") Integer batchStandardsId) {
        return batchStandardsService.selectById(batchStandardsId);
    }
    
    //---------------------------以下是车位管理页面的关联收费项相关代码-------------------------------------------------------
    /**
     * 跳转到添加批量关联收费标准(车位管理用)
     * @param pId 车位id
     */
	@RequestMapping("/batchStandards_add/{pId}")
	    public String batchStandardsAdd(@PathVariable Integer pId, Model model) {
		model.addAttribute("pId",pId);
		model.addAttribute("relevanceId",2);
		return PREFIX + "batchStandards_add.html";
	}
	/**
     * 车位关联收费标准(车位管理用)
     * @param pId 车位id
     */
	@RequestMapping(value = "/add/{pId}")
    @ResponseBody
    public Object add(@PathVariable("pId") Integer pId,BatchStandards batchStandards) {
		BatchStandards copy = new BatchStandards();
		BeanUtils.copyProperties(batchStandards ,copy);
		copy.setRemark(null);
		copy.setCostName(null);
		copy.setStandardName(null);
		List<BatchStandards> list= batchStandardsService.findList(copy);
		if(list.size()>0) {
			batchStandards = list.get(0);
			batchStandards.setScopeId(""+pId);
			return batchStandardsService.addScopeIds(batchStandards);
		} else {
			batchStandards.setScopeId(""+pId);
			batchStandardsService.insert(batchStandards);
			return batchStandardsService.addScopeIds(batchStandards);
		}
	}
	
	
    /**
     * 跳转到添加批量关联收费标准(房屋管理用)
     * @param rId 房间id
     */
	@RequestMapping("/batchStandards_addByRoomInfo/{rId}")
	    public String batchStandards_addByRoomInfo(@PathVariable Integer rId, Model model) {
		model.addAttribute("rId",rId);
		return PREFIX + "batchStandards_add.html";
	}
	
	/**
     * 房屋关联收费标准(房屋管理用)
     * @param rId 房间id
     */
	@RequestMapping(value = "/addByRoomInfo/{rId}")
    @ResponseBody
    public Object addByRoomInfo(@PathVariable("rId") Integer rId,BatchStandards batchStandards) {
		boolean isAssociated = batchStandardsService.isAssociated(batchStandards,rId);
		if(!isAssociated) {
			BatchStandards copy = new BatchStandards();
			BeanUtils.copyProperties(batchStandards ,copy);
			copy.setRemark(null);
			copy.setCostName(null);
			copy.setStandardName(null);
			List<BatchStandards> list= batchStandardsService.findList(copy);
			if(list.size()>0) {
				batchStandards = list.get(0);
				batchStandards.setScopeId(""+rId);
				return batchStandardsService.addScopeIds(batchStandards);
			} else {
				batchStandards.setScopeId(""+rId);
				batchStandardsService.insert(batchStandards);
				return batchStandardsService.addScopeIds(batchStandards);
			}
		}
		return SUCCESS_TIP;
	}
}
