package com.bmw.remindersrecord.model;


//import com.common.utils.excel.annotation.ExcelField;
import com.bmw.bill.model.Bill;



/**
 * 账单明细表Condition类
 * 
 * @author zhangt
 * @date 2018-06-22 02:57:18
 */
public class RemindersRecordBillVO extends Bill {

	    /**
	     * 欠费天数
	     */
	    private String arrearsDay;
	    
	    /**
	     * 是否已催缴
	     */
	    private String isUrge;

//	    @ExcelField(title="欠费天数", align=2, sort=13,type=1,groups= {2})
		public String getArrearsDay() {
			return arrearsDay;
		}

		public void setArrearsDay(String arrearsDay) {
			this.arrearsDay = arrearsDay;
		}

//		@ExcelField(title="状态", align=2, sort=14,type=1,groups= {2})
		public String getIsUrge() {
			return isUrge;
		}

		public void setIsUrge(String isUrge) {
			this.isUrge = isUrge;
		}
	    
}