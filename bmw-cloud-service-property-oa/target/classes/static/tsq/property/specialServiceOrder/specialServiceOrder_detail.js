SpecialServiceOrderDetail={
		
}
SpecialServiceOrderDetail.close = function() {
    parent.layer.close(window.parent.SpecialServiceOrder.layerIndex);
}
SpecialServiceOrderDetail.openService=function(serviceId){
	var index = layer.open({
        type: 2,
        title: '服务详情',
        area: ['500px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/services/services_view/'+serviceId
    });
}
/**
 * 打开接单
 */
SpecialServiceOrderDetail.openRecieve = function () {
	var index = parent.layer.open({
        type: 2,
        title: '接单',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/specialServiceOrder/open_recieve/'+$("#orderId").val()
    });
	 SpecialServiceOrderDetail.close();
	 window.parent.SpecialServiceOrder.layerIndex = index;
};

/**
 * 打开派单
 */
SpecialServiceOrderDetail.openSendOrder = function () {
	 var index = parent.layer.open({
	        type: 2,
	        title: '派单',
	        area: ['800px', '420px'], //宽高
	        fix: false, //不固定
	        maxmin: true,
	        content: Feng.ctxPath + '/specialServiceOrder/open_send_order/'+$("#orderId").val()
	    });
	 SpecialServiceOrderDetail.close();
	 window.parent.SpecialServiceOrder.layerIndex = index;
};

/**
 * 打开取消订单
 */
SpecialServiceOrderDetail.openCanelOrder = function () {
	var index = parent.layer.open({
        type: 2,
        title: '取消订单',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/specialServiceOrder/open_cancel_order/'+$("#orderId").val()
    });
	 SpecialServiceOrderDetail.close();
	 window.parent.SpecialServiceOrder.layerIndex = index;
};

/**
 * 打开完成订单
 */
SpecialServiceOrderDetail.openCompleteOrder = function () {
	var index = parent.layer.open({
        type: 2,
        title: '完成订单',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/specialServiceOrder/open_complete_order/'+$("#orderId").val()
    });
	 SpecialServiceOrderDetail.close();
	 window.parent.SpecialServiceOrder.layerIndex = index;
};
/**
 * 回复投诉
 */
SpecialServiceOrderDetail.replyComplaint=function() {
	var remarkContent=$("#remarkContent").val();
	if(remarkContent==null||remarkContent==''){
		Feng.error("请输入回复信息");
		return;
	}
	var param={
			"orderId":$("#orderId").val(),
			"remarkContent":remarkContent
	}
	$.ajax({
		   type:"post",
	       url:Feng.ctxPath + "/specialServiceOrder/replyComplaint",
	       contentType:"application/json",
	       data:JSON.stringify(param),
	       dataType:"json",
	       success:function(data){
	    	   Feng.success(data.message);
	    	   window.parent.SpecialServiceOrder.table.refresh();
	    	   SpecialServiceOrderDetail.close();
	       }
	   })
}