package com.bmw.bill.service;

import com.bmw.bill.model.Bill;
import com.bmw.bill.model.BillDiscountsVO;
import com.bmw.bill.model.BillVO;
import com.bmw.complaintadvice.model.ComplaintAdvice;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.core.base.tips.Tip;

/**
 * <p>
 * 账单明细表 服务类
 * </p>
 *
 * @author zhangt123
 * @since 2018-06-22
 */
public interface IBillService  {
	/**
     * 列表
     */
	JSONArray findList(Bill condition);
	
	/**
     * 详情
     */
	BillVO getdetail(Integer billId);
	
	
	/**
     * 新增信息
     */
	void add(Bill bill);
	
	/**
	 * 修改信息
	 */
	void update(Bill bill);
	
	/**
	 * 删除信息
	 * @return 
	 * @throws Exception 
	 */
	Tip del(Integer billId) throws Exception;

	/**
	 * 根据id查询列表
	 */
	JSONArray billListByIds(String ids);

	/**
	 * 提交缴费
	 */
	void changeCommit(String ids, String payMode, String remark);

	/**
	 * 获取申请优惠的订单
	 */
	JSONArray billListDiscount(Bill condition);

	/**
	 * 提交优惠申请
	 */
	void discountsCommit(String billId, String reason, String discountsAmount, String latenessOffer);

	/**
	 * 查看申请优惠账单详情
	 */
	BillDiscountsVO getBillDiscountsVO(Integer billId, Integer discountsId);

	/**
	 * 账单查询
	 */
	JSONArray findListAll(Bill condition);

	/**
	 * 导入账单
	 */
	Integer upload(String param, String neighborhoodsId);

	/**
	 * 优惠申请审批通过
	 */
	void discountsPass(Integer billId, String taskId);
	
	/**
	 * 终止优惠申请
	 */
	void discountsEnd(Integer billId, String taskId);
}
