package com.bmw.property.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.bmw.property.model.MBaseInfo;
import com.bmw.property.model.MRecord;
import com.bmw.property.service.IMBaseInfoService;
import com.bmw.property.service.IMRecordService;
import com.bmw.property.warpper.MBaseInfoWarpper;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 楼宇管理控制器
 *
 * @author fengshuonan
 * @Date 2018-06-22 09:03:57
 */
@Controller
@RequestMapping("/mBaseDoubleInfo")
public class MBaseInfoDoubleController extends BaseController {

    private String PREFIX = "/property/mBaseInfo/";

    @Autowired
    private IMBaseInfoService mBaseInfoService;
    
    @Autowired
    private IMRecordService mRecordService;

    /**
     * 跳转到楼宇管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "mBaseDoubleInfo.html";
    }

    /**
     * 跳转到添加楼宇管理
     */
    @RequestMapping("/mBaseInfo_add")
    public String mBaseInfoAdd() {
        return PREFIX + "mBaseInfo_add.html";
    }

    /**
     * 跳转到修改楼宇管理
     */
    @RequestMapping("/mBaseInfo_update/{mBaseInfoId}")
    public String mBaseInfoUpdate(@PathVariable Integer mBaseInfoId, Model model) {
    	MBaseInfo buildingInfo = mBaseInfoService.getdetail(mBaseInfoId);
        model.addAttribute("item",buildingInfo);
        LogObjectHolder.me().set(buildingInfo);
        return PREFIX + "mBaseInfo_edit.html";
    }
    /**
     * 跳转到修改楼宇管理
     */
    @RequestMapping("/mBaseInfo_detail/{mBaseInfoId}")
    public String buildingInfoDetail(@PathVariable Integer mBaseInfoId, Model model) {
    	MBaseInfo mBaseInfo = mBaseInfoService.getdetail(mBaseInfoId);
        model.addAttribute("item",mBaseInfo);
        LogObjectHolder.me().set(mBaseInfo);
        return PREFIX + "mBaseInfo_detail.html";
    }
    
    /**
     * 跳转到录入用量
     */
    @RequestMapping("/mBaseInfo_dosage/{mBaseInfoId}")
    public String mBaseInfoDosage(@PathVariable Integer mBaseInfoId, Model model) {
    	MBaseInfo mBase = new MBaseInfo();
    	mBase.setsId(mBaseInfoId);
    	MBaseInfo buildingInfo = mBaseInfoService.findDosage(mBase);
    	if(buildingInfo.gettMonth()=="12") {
    		buildingInfo.settMonth("1");
    	}else {
    		buildingInfo.settMonth(String.valueOf(Integer.parseInt(buildingInfo.gettMonth()+1)));
    	}
    	  Calendar cale = null;  
          cale = Calendar.getInstance();  
          int year = cale.get(Calendar.YEAR);  
          int month = cale.get(Calendar.MONTH) + 1;
          buildingInfo.settMonth(year+"-0"+month);
        model.addAttribute("item",buildingInfo);
        LogObjectHolder.me().set(buildingInfo);
        return PREFIX + "mBaseInfo_dosage.html";
    }
    
    /**
     * 跳转到录入用量
     */
    @RequestMapping("/mBaseInfo_approval/{mBaseInfoId}/{tMonth}")
    public String mBaseInfoApproval(@PathVariable Integer mBaseInfoId , @PathVariable String tMonth, Model model) {
    	MBaseInfo mBase = new MBaseInfo();
    	mBase.setsId(mBaseInfoId);
    	mBase.settMonth(tMonth);
    	MBaseInfo buildingInfo = mBaseInfoService.findDouble(mBase);
    	if(buildingInfo.gettMonth()=="12") {
    		buildingInfo.settMonth("1");
    	}else {
    		buildingInfo.settMonth(String.valueOf(Integer.parseInt(buildingInfo.gettMonth()+1)));
    	}
    	  Calendar cale = null;  
          cale = Calendar.getInstance();  
          int year = cale.get(Calendar.YEAR);  
          int month = cale.get(Calendar.MONTH) + 1;
          buildingInfo.settMonth(year+"-0"+month);
        model.addAttribute("item",buildingInfo);
        LogObjectHolder.me().set(buildingInfo);
        return PREFIX + "mBaseInfo_approval.html";
    }

    /**
     * 获取楼宇管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String nName , String bName , String rRoomnum) {
    	Integer eId = (Integer) super.getSession().getAttribute("eId");
    	return super.warpObject(new MBaseInfoWarpper(mBaseInfoService.findDoubleList(nName , bName , rRoomnum,eId.toString())));
    }
    

    /**
     * 新增楼宇管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MBaseInfo mBaseInfo) {
    	MBaseInfo data = new MBaseInfo();
        mBaseInfoService.add(mBaseInfo);
//        MRecord record = new MRecord();
//        record.setsId(mBaseInfo.getsId());
//        record.setrId(mBaseInfo.getrId());
//        mRecordService.add(record);
        return SUCCESS_TIP;
    }

    /**
     * 删除楼宇管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer mBaseInfoId) {
        mBaseInfoService.del(mBaseInfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改楼宇管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MBaseInfo mBaseInfo) {
        mBaseInfoService.update(mBaseInfo);
        return SUCCESS_TIP;
    }

    /**
     * 楼宇管理详情
     */
    @RequestMapping(value = "/findMonth/{mBaseInfoId}/{tMonth}")
    @ResponseBody
    public Object findMonth(@PathVariable("mBaseInfoId") Integer mBaseInfoId , @PathVariable("tMonth") String tMonth) {
    	MBaseInfo base = new MBaseInfo();
    	base.setsId(mBaseInfoId);
    	base.settMonth(tMonth);
        return mBaseInfoService.findDouble(base);
    }
    
    /**
     * 楼宇管理详情
     */
    @RequestMapping(value = "/detail/{mBaseInfoId}")
    @ResponseBody
    public Object detail(@PathVariable("mBaseInfoId") Integer mBaseInfoId) {
        return mBaseInfoService.getdetail(mBaseInfoId);
    }
    

	/**
	 * 一键生成账单
	 * 
	 * @param mBaseInfoIds 前台抄表基础管理列表中选中的数据的id
	 */
	@RequestMapping(value = "/saveBills/{mBaseInfoIds}")
	@ResponseBody
	public Object saveBills(@PathVariable("mBaseInfoIds") String mBaseInfoIds) {
		mBaseInfoService.saveBills(mBaseInfoIds);
		return SUCCESS_TIP;
	}
    
}
