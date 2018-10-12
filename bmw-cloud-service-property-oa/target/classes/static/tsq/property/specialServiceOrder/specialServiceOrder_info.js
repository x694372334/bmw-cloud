/**
 * 初始化特色服务详情对话框
 */
var SpecialServiceOrderInfoDlg = {
    specialServiceOrderInfoData : {}
};

/**
 * 清除数据
 */
SpecialServiceOrderInfoDlg.clearData = function() {
    this.specialServiceOrderInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SpecialServiceOrderInfoDlg.set = function(key, val) {
    this.specialServiceOrderInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SpecialServiceOrderInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SpecialServiceOrderInfoDlg.close = function() {
    parent.layer.close(window.parent.SpecialServiceOrder.layerIndex);
}

/**
 * 收集数据
 */
SpecialServiceOrderInfoDlg.collectData = function() {
    this
    .set();
}

/**
 * 接单
 */
SpecialServiceOrderInfoDlg.recieve = function() {
	
	var url="/specialServiceOrder/recieve";	
	send4Update(url);
}

/**
 * 派单
 */
SpecialServiceOrderInfoDlg.sendOrder = function() {
	var url="/specialServiceOrder/sendOrder";	
	send4Update(url);
	
}

/**
 * 完成订单
 */
SpecialServiceOrderInfoDlg.completeOrder = function() {
	var url="/specialServiceOrder/completeOrder";	
	send4Update(url);
	
}
/**
 * 取消订单
 */
SpecialServiceOrderInfoDlg.cancelOrder = function() {
	var url="/specialServiceOrder/cancelOrder";	
	send4Update(url);
	
}

//接单和派单时使用
function send4Update(url){
	var remarkContent=$("#remarkContent").val();
	if(remarkContent==null||remarkContent==''){
		Feng.error("请输入备注信息");
		return;
	}
	var param={
			"orderId":$("#id").val(),
			"remarkContent":$("#remarkContent").val()
	}
   $.ajax({
	   type:"post",
       url:Feng.ctxPath + url,
       contentType:"application/json",
       data:JSON.stringify(param),
       dataType:"json",
       success:function(data){
    	   Feng.success(data.message);
    	   window.parent.SpecialServiceOrder.table.refresh();
    	   SpecialServiceOrderInfoDlg.close();
       }
   })
}

$(function() {

});
