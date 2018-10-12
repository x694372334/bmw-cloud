package com.bmw.property.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.bmw.property.model.MRecord;
import com.bmw.property.service.IMRecordService;
import com.bmw.property.warpper.MRecordWarpper;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 楼宇管理控制器
 *
 * @author fengshuonan
 * @Date 2018-06-22 09:03:57
 */
@Controller
@RequestMapping("/mRecord")
public class MRecordController extends BaseController {

    private String PREFIX = "/property/mRecord/";

    @Autowired
    private IMRecordService mRecordService;

    /**
     * 跳转到楼宇管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "mRecord.html";
    }

    /**
     * 跳转到添加楼宇管理
     */
    @RequestMapping("/mRecord_add")
    public String mRecordAdd() {
        return PREFIX + "mRecord_add.html";
    }

    /**
     * 跳转到修改楼宇管理
     */
    @RequestMapping("/mRecord_update/{mRecordId}")
    public String mRecordUpdate(@PathVariable Integer mRecordId, Model model) {
    	MRecord buildingInfo = mRecordService.getdetail(mRecordId);
        model.addAttribute("item",buildingInfo);
        LogObjectHolder.me().set(buildingInfo);
        return PREFIX + "mRecord_edit.html";
    }
    /**
     * 跳转到修改楼宇管理
     */
    @RequestMapping("/mRecord_detail/{mRecordId}")
    public String buildingInfoDetail(@PathVariable Integer mRecordId, Model model) {
    	MRecord mRecord = mRecordService.getdetail(mRecordId);
        model.addAttribute("item",mRecord);
        LogObjectHolder.me().set(mRecord);
        return PREFIX + "mRecord_detail.html";
    }
    

    /**
     * 获取楼宇管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String nName , String bName , String rRoomnum) {
    	Integer eId = (Integer) super.getSession().getAttribute("eId");
    	return super.warpObject(new MRecordWarpper(mRecordService.findList(nName , bName , rRoomnum,eId.toString())));
    }

    /**
     * 新增楼宇管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(MRecord mRecord) {
        mRecordService.add(mRecord);
        return SUCCESS_TIP;
    }

    /**
     * 删除楼宇管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer mRecordId) {
        mRecordService.del(mRecordId);
        return SUCCESS_TIP;
    }

    /**
     * 修改楼宇管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(MRecord mRecord) {
        mRecordService.update(mRecord);
        return SUCCESS_TIP;
    }
    
    /**
     * 提交通过
     */
    @RequestMapping(value = "/updateApproval")
    @ResponseBody
    public Object updateApproval(MRecord mRecord) {
//    	if(null!=mRecord.getText()&&"".equals(mRecord.getText())&&""!=mRecord.getText()) {
    		BigDecimal data = new BigDecimal(mRecord.getText());
    		mRecord.setuAmount(data.subtract(mRecord.gettNum()));
//    	}
    	mRecord.setIsApproval("1");
        mRecordService.update(mRecord);
        return SUCCESS_TIP;
    }
    
    /**
     * 提交驳回
     */
    @RequestMapping(value = "/updateRelieve")
    @ResponseBody
    public Object updateRelieve(MRecord mRecord) {
    	mRecord.setIsApproval("2");
        mRecordService.update(mRecord);
        return SUCCESS_TIP;
    }

    /**
     * 楼宇管理详情
     */
    @RequestMapping(value = "/detail/{mRecordId}")
    @ResponseBody
    public Object detail(@PathVariable("mRecordId") Integer mRecordId) {
        return mRecordService.getdetail(mRecordId);
    }
    
    /**
     * 楼宇管理详情
     */
    @RequestMapping(value = "/findMonth/{sId}/{tMonth}")
    @ResponseBody
    public Object findMonth(@PathVariable("sId") Integer sId , @PathVariable("tMonth") String tMonth) {
        return mRecordService.findMonth(sId,tMonth);
    }
    

    
    
}
