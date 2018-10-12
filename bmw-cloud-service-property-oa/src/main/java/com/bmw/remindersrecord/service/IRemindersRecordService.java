package com.bmw.remindersrecord.service;

import com.bmw.bill.model.Bill;
import com.bmw.bill.model.BillDiscountsVO;
import com.bmw.bill.model.BillVO;
import com.bmw.complaintadvice.model.ComplaintAdvice;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 账单明细表 服务类
 * </p>
 *
 * @author zhangt123
 * @since 2018-06-22
 */
public interface IRemindersRecordService  {
	/**
     * 列表
     */
	JSONArray findList(Bill condition);

	/**
     * 一键催缴
     */
	void urge(String ids);
	
}
