package com.bmw.remindersrecord.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
//import com.common.utils.excel.ExportExcel;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.bmw.bill.model.Bill;
import com.bmw.remindersrecord.model.RemindersRecordBillVO;
import com.bmw.remindersrecord.service.IRemindersRecordService;

/**
 * 账单明细控制器
 *
 * @author fengshuonan
 * @Date 2018-06-22 15:32:05
 */
@Controller
@RequestMapping("/remindersRecord")
public class RemindersRecordController extends BaseController {

    private String PREFIX = "/property/remindersrecord/";

    @Autowired
    private IRemindersRecordService remindersRecordService;
    
    /**
     * 跳转到缴费提醒首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "reminders_record.html";
    }
    
    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Bill condition) {
        return remindersRecordService.findList(condition);
    }
    
    /**
     * 一键催缴
     */
    @RequestMapping(value = "/urge/{ids}")
    @ResponseBody
    public Object urge(@PathVariable("ids")  String ids) {
    	ids = ids.substring(0,ids.length()-1);
    	remindersRecordService.urge(ids);
    	return SUCCESS_TIP;
    }
    
    /**
     * 导出所有账单
     * @throws Exception 
     */
    @RequestMapping("/urge_export")
    public void billExportAll( Bill condition ,HttpServletResponse response) throws Exception {
    	
    	try {
	    	JSONArray result = remindersRecordService.findList(condition);
	    	String jsonStr = JSONObject.toJSONString(result);
	    	List<RemindersRecordBillVO> list = JSONObject.parseArray(jsonStr,  RemindersRecordBillVO.class);
	    	String fileName = "催缴账单.xlsx";
//	 		new ExportExcel("催缴账单", RemindersRecordBillVO.class,1, 2).setDataList(list).write(response, fileName).dispose();
    	} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
    	
    	/*List<String> headers = new ArrayList<>();
		headers.add("小区名称");
		headers.add("账单编号");
		headers.add("住户");
		headers.add("房号/车位号");
		headers.add("手机号");
		headers.add("费用开始时间");
		headers.add("费用结束时间");
		headers.add("欠费天数");
		headers.add("欠费金额");
		headers.add("状态");
    	List<Map<String,Object>> rows = new ArrayList<Map<String,Object>>();
    	if(result.size()>0){
    		for(int i=0;i<result.size();i++){
    			 // 遍历 jsonarray 数组，把每一个对象转成 json 对象
    			JSONObject job = result.getJSONObject(i);
    			Map<String,Object> map = new LinkedHashMap<String,Object>();
    			map.put("neighborhoodsName", job.get("neighborhoodsName"));
    			map.put("billNo",job.get("billNo"));
    			map.put("ownerName",job.get("ownerName"));
    			map.put("0bjectNo",job.get("objectNo"));
    			map.put("ownerPhone",job.get("ownerPhone"));
    			map.put("costStartTime",job.get("costStartTime"));
    			map.put("costEndTime",job.get("costEndTime"));
    			map.put("arrearsDay",job.get("arrearsDay"));
    			map.put("shouldTotal",job.get("shouldTotal"));
    			map.put("isUrge",job.get("isUrge"));
    			rows.add(map);
    		}
    		}
	    ExcelUtils.exportExcel( response,"催缴账单","催缴账单",headers,rows);*/
    }

    
}
