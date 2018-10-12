package com.bmw.property.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.bmw.property.model.ServiceOrderRemark;
import com.bmw.property.model.SpecialServiceOrder;
import com.bmw.property.service.INeighborhoodService;
import com.bmw.property.service.ISpecialServiceOrderService;

/**
 * 特色服务控制器
 *
 * @author fengshuonan
 * @Date 2018-07-18 15:00:55
 */
@Controller
@RequestMapping("/specialServiceOrder")
public class SpecialServiceOrderController extends BaseController {

    private String PREFIX = "/property/specialServiceOrder/";

    @Autowired
    private ISpecialServiceOrderService specialServiceOrderService;
    @Autowired
    private INeighborhoodService neighborhoodService;
/*    @Autowired
    private IServicesService servicesService;*/

    /**
     * 跳转到特色服务首页
     */
    @RequestMapping("/doing_index")
    public String doingindex(Model model) {
    	//小区列表
    	JSONArray nList= neighborhoodService.findList(ShiroKit.getUser().geteId(),null);
    	model.addAttribute("nList", nList);
    	//服务方式
    	JSONArray fwfsList=ConstantFactory.me().findDictByCode("tsfwfwfs");
    	model.addAttribute("fwfsList", fwfsList);
    	//收费方式
    	JSONArray sffsList=ConstantFactory.me().findDictByCode("tsfwsffs");
    	model.addAttribute("sffsList", sffsList);
    	//订单状态
    	JSONArray statusList=ConstantFactory.me().findDictByCode("tsfwwyzt");
    	/*List<JSONObject> removeTemp=new ArrayList<>();
    	for(int i=0;i<statusList.size();i++) {//排除待处理和已处理的两种状态
    		JSONObject o=statusList.getJSONObject(i);
    		int status=o.getInteger("num");
    		if(status==6||status==7) {
    			removeTemp.add(o);
    		}
    	}
    	for(JSONObject o:removeTemp) {
    		statusList.remove(o);
    	}*/
    	model.addAttribute("statusList", statusList);
        return PREFIX + "specialServiceOrder_doing.html";
    }
    
    /**
     * @deprecated
     * 跳转到特色服务首页
     */
    @RequestMapping("/done_index")
    public String doneindex(Model model) {
    	//小区列表
    	JSONArray nList= neighborhoodService.findList(ShiroKit.getUser().geteId(),null);
    	model.addAttribute("nList", nList);
    	//服务方式
    	JSONArray fwfsList=ConstantFactory.me().findDictByCode("tsfwfwfs");
    	model.addAttribute("fwfsList", fwfsList);
    	//收费方式
    	JSONArray sffsList=ConstantFactory.me().findDictByCode("tsfwsffs");
    	model.addAttribute("sffsList", sffsList);
    	//订单状态
    	JSONArray statusList=ConstantFactory.me().findDictByCode("tsfwwyzt");
    	List<JSONObject> removeTemp=new ArrayList<>();
    	for(int i=0;i<statusList.size();i++) {//排除待处理和已处理的两种状态
    		JSONObject o=statusList.getJSONObject(i);
    		int status=o.getInteger("num");
    		if(status!=6&&status!=7) {
    			removeTemp.add(o);
    		}
    	}
    	for(JSONObject o:removeTemp) {
    		statusList.remove(o);
    	}
    	model.addAttribute("statusList", statusList);
        return PREFIX + "specialServiceOrder_done.html";
    }
    
    /**
     * 跳转到接单
     */
    @RequestMapping("/open_recieve/{id}")
    public String openRecieve(@PathVariable Integer id,Model model) {
        model.addAttribute("id",id);
        return PREFIX + "specialServiceOrder_recieve.html";
    }
    
    /**
     * 跳转到派单
     */
    @RequestMapping("/open_send_order/{id}")
    public String openSendOrder(@PathVariable Integer id,Model model) {
        model.addAttribute("id",id);
        return PREFIX + "specialServiceOrder_sendOrder.html";
    }
    
    /**
     * 跳转到取消订单
     */
    @RequestMapping("/open_cancel_order/{id}")
    public String openCancelOrder(@PathVariable Integer id,Model model) {
        model.addAttribute("id",id);
        return PREFIX + "specialServiceOrder_cancel_order.html";
    }
    /**
     * 跳转到取消订单
     */
    @RequestMapping("/open_complete_order/{id}")
    public String completeOrder(@PathVariable Integer id,Model model) {
        model.addAttribute("id",id);
        return PREFIX + "specialServiceOrder_complete.html";
    }
    
    
    /**
     * 跳转到详情
     */
    @RequestMapping("/open_detail/{orderId}")
    public String openDetail(@PathVariable("orderId") Integer orderId,Model model) {
    	 SpecialServiceOrder order=specialServiceOrderService.getDetail(orderId);
    	 model.addAttribute("order", order);
    //	 Services service=servicesService.getdetail(order.getServiceId());
    //	 model.addAttribute("service", service);
         return PREFIX + "specialServiceOrder_detail.html";
    }
    
    /**
     * 跳转到评价详情
     */
    @RequestMapping("/open_fevaluateDetail/{orderId}")
    public String openfevaluateDetail(@PathVariable("orderId") Integer orderId,Model model) {
    	 SpecialServiceOrder order=specialServiceOrderService.fevaluateDetail(orderId);
    	 model.addAttribute("order", order);
         return PREFIX + "specialServiceOrder_fevaluateDetail.html";
    }
    
    /**
     * 跳转到投诉详情
     */
    @RequestMapping("/open_complaintDetail/{orderId}")
    public String opencomplaintDetail(@PathVariable("orderId") Integer orderId,Model model) {
    	 SpecialServiceOrder order=specialServiceOrderService.complaintDetail(orderId);
    	 model.addAttribute("order", order);
         return PREFIX + "specialServiceOrder_complaintDetail.html";
    }
    
    /**
     * 获取特色服务列表
     */
    @RequestMapping(value = "/doinglist")
    @ResponseBody
    public Object doinglist(@RequestParam(required = false) Integer nId,
    		@RequestParam(required = false) Integer serviceType,
    		@RequestParam(required = false) Integer servicePaymentType,
    		@RequestParam(required = false) Integer sOrderStatus,
    		@RequestParam(required = false) String serviceName,
    		@RequestParam(required = false) String beginDate,
    		@RequestParam(required = false) String endDate) {
    	
    	Map<String,Object> paramsMap=new HashMap<>();
    	paramsMap.put("nId", nId);
    	paramsMap.put("eId", ShiroKit.getUser().geteId());
    	paramsMap.put("serviceType", serviceType);
    	paramsMap.put("servicePaymentType", servicePaymentType);
    	paramsMap.put("sOrderStatus", sOrderStatus);
    	paramsMap.put("serviceName", serviceName);
    	paramsMap.put("beginDate", beginDate);
    	paramsMap.put("endDate", endDate);
    	Page<SpecialServiceOrder> page=new PageFactory<SpecialServiceOrder>().defaultPage();
    	paramsMap.put("pageNum", page.getCurrent());
    	paramsMap.put("pageSize",page.getSize());
    	JSONObject json=specialServiceOrderService.selectDoingList(paramsMap);
    	page.setTotal(json.getInteger("total"));
    	page.setRecords(json.getJSONArray("result").toJavaList(SpecialServiceOrder.class));
    	return super.packForBT(page);
    }
    /**
     * @deprecated
     * 获取特色服务列表
     */
    @RequestMapping(value = "/donelist")
    @ResponseBody
    public Object donelist(@RequestParam(required = false) Integer nId,
    		@RequestParam(required = false) Integer serviceType,
    		@RequestParam(required = false) Integer servicePaymentType,
    		@RequestParam(required = false) Integer sOrderStatus,
    		@RequestParam(required = false) String serviceName,
    		@RequestParam(required = false) String beginDate,
    		@RequestParam(required = false) String endDate) {
    	
    	Map<String,Object> paramsMap=new HashMap<>();
    	paramsMap.put("nId", nId);
    	paramsMap.put("eId", ShiroKit.getUser().geteId());
    	paramsMap.put("serviceType", serviceType);
    	paramsMap.put("servicePaymentType", servicePaymentType);
    	paramsMap.put("sOrderStatus", sOrderStatus);
    	paramsMap.put("serviceName", serviceName);
    	paramsMap.put("beginDate", beginDate);
    	paramsMap.put("endDate", endDate);
    	Page<SpecialServiceOrder> page=new PageFactory<SpecialServiceOrder>().defaultPage();
    	paramsMap.put("pageNum", page.getCurrent());
    	paramsMap.put("pageSize",page.getSize());
    	JSONObject json=specialServiceOrderService.selectDoneList(paramsMap);
    	page.setTotal(json.getInteger("total"));
    	page.setRecords(json.getJSONArray("result").toJavaList(SpecialServiceOrder.class));
    	return super.packForBT(page);
    }
    /**
     * 接单
     */
    @RequestMapping(value = "/recieve")
    @ResponseBody
    public Object recieve(@RequestBody ServiceOrderRemark remark) {
    	createParam4Update(remark);
    	specialServiceOrderService.recieve(remark);
    	return SUCCESS_TIP;
    }
    
    /**
     * 派单
     */
    @RequestMapping(value = "/sendOrder")
    @ResponseBody
    public Object sendOrder(@RequestBody ServiceOrderRemark remark) {
    	createParam4Update(remark);
    	specialServiceOrderService.sendOrder(remark);
    	return SUCCESS_TIP;
    }
    
    /**
     * 完成订单
     */
    @RequestMapping(value = "/completeOrder")
    @ResponseBody
    public Object completeOrder(@RequestBody ServiceOrderRemark remark) {
    	createParam4Update(remark);
    	specialServiceOrderService.completeOrder(remark);
    	return SUCCESS_TIP;
    }
    
    
    /**
     * 取消订单
     */
    @RequestMapping(value = "/cancelOrder")
    @ResponseBody
    public Object cancelOrder(@RequestBody ServiceOrderRemark remark) {
    	createParam4Update(remark);
    	specialServiceOrderService.cancelOrder(remark);
    	return SUCCESS_TIP;
    }
    
    /**
     * 取消订单
     */
    @RequestMapping(value = "/replyComplaint")
    @ResponseBody
    public Object replyComplaint(@RequestBody ServiceOrderRemark remark) {
    	createParam4Update(remark);
    	specialServiceOrderService.replyComplaint(remark);
    	return SUCCESS_TIP;
    }
    
    
    
    private void createParam4Update(ServiceOrderRemark remark) {
    	ShiroUser shiroUser = ShiroKit.getUser();
    	remark.setCreateManId(shiroUser.getId());
    	remark.setCreateMan(shiroUser.getName());
    	remark.setUserId(shiroUser.getId());
    	remark.setUserName(shiroUser.getName());
    	remark.setUserType(Short.valueOf("2"));
    }

}
