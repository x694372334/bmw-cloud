/**
 * 特色服务管理初始化
 */
var SpecialServiceOrder = {
    id: "SpecialServiceOrderTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
SpecialServiceOrder.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        	{title: '小区名称', field: 'nName', visible: true, align: 'center', valign: 'middle'},
        	{title: '付费类型', field: 'servicePaymentType', visible: true, align: 'center', valign: 'middle'},      	
            {title: '订单编码', field: 'orderCode', visible: true, align: 'center', valign: 'middle'},
            {title: '预订人', field: 'appUserRealname', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号', field: 'userPhoneNo', visible: true, align: 'center', valign: 'middle'},
            {title: '下单时间', field: 'orderTime', visible: true, align: 'center', valign: 'middle'},
            {title: '服务名称', field: 'serviceName', visible: true, align: 'center', valign: 'middle'},
            {title: '服务类别', field: 'serviceSortName', visible: true, align: 'center', valign: 'middle'},
            {title: '服务方式', field: 'serviceTypeName', visible: true, align: 'center', valign: 'middle'},
            {title: '收费方式', field: 'servicePaymentTypeName', visible: true, align: 'center', valign: 'middle'},
            {title: '实收款', field: 'realIncome', visible: true, align: 'center', valign: 'middle'},
            {title: '订单状态', field: 'sOrderStatusName', visible: true, align: 'center', valign: 'middle',
            	formatter : function(value, row, index) {
            		return "<span style='color:red'>"+value+"</span>";
				}	
            },
            {title: '评价情况', field: 'ifevaluate', visible: true, align: 'center', valign: 'middle',
            	formatter : function(value, row, index) {
            		if(value==2){
            			return "<span class='fa fa-star text-danger'>已评价</span>";
            		}else{
            			return "<span class='fa fa-star '>未评价</span>";
            		}           		
				}	
            },
            {title: '投诉情况', field: 'iscomplaint', visible: true, align: 'center', valign: 'middle',
            	formatter : function(value, row, index) {
            		if(value==2&&row.complaintStatus==1){
            			return "<span class='fa fa-bell text-danger'>投诉待处理</span>";
            		}else if(value==2&&row.complaintStatus==2){
            			return "<span class='fa fa-bell text-info'>投诉已处理</span>";
            		}else{
            			return "<span class='fa fa-thumbs-o-up text-primary'>没有投诉</span>";
            		}         		
				}	
            }
            
    ];
};

/**
 * 检查是否选中
 */
SpecialServiceOrder.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        SpecialServiceOrder.seItem = selected[0];
        return true;
    }
};

/**
 * 打开接单
 */
SpecialServiceOrder.openRecieve = function () {
	if(this.check()){
		if(SpecialServiceOrder.seItem.sOrderStatus!=1){
			Feng.error("只有待接单的订单可以接单操作");
			return;
		}
		 var index = layer.open({
		        type: 2,
		        title: '接单',
		        area: ['800px', '420px'], //宽高
		        fix: false, //不固定
		        maxmin: true,
		        content: Feng.ctxPath + '/specialServiceOrder/open_recieve/'+SpecialServiceOrder.seItem.orderId
		    });
		    this.layerIndex = index;
	}
};

/**
 * 打开派单
 */
SpecialServiceOrder.openSendOrder = function () {
	if(this.check()){
		if(SpecialServiceOrder.seItem.sOrderStatus!=2){
			Feng.error("只有待派单的订单可以派单操作");
			return;
		}
		 var index = layer.open({
		        type: 2,
		        title: '派单',
		        area: ['800px', '420px'], //宽高
		        fix: false, //不固定
		        maxmin: true,
		        content: Feng.ctxPath + '/specialServiceOrder/open_send_order/'+SpecialServiceOrder.seItem.orderId
		    });
		    this.layerIndex = index;
	}
};
/**
 * 打开完成订单
 */
SpecialServiceOrder.openCompleteOrder = function () {
	if(this.check()){
		if(SpecialServiceOrder.seItem.sOrderStatus!=3||
						SpecialServiceOrder.seItem.servicePaymentType!=1){
			Feng.error("只有一口价且为待服务状态的订单可以直接完成！");
			return;
		}
		 var index = layer.open({
		        type: 2,
		        title: '完成订单',
		        area: ['800px', '420px'], //宽高
		        fix: false, //不固定
		        maxmin: true,
		        content: Feng.ctxPath + '/specialServiceOrder/open_complete_order/'+SpecialServiceOrder.seItem.orderId
		    });
		    this.layerIndex = index;
	}
};
/**
 * 打开取消订单
 */
SpecialServiceOrder.openCanelOrder = function () {
	if(this.check()){
		if(SpecialServiceOrder.seItem.sOrderStatus>4){
			Feng.error("当前状态下订单不可取消");
			return;
		}
		 var index = layer.open({
		        type: 2,
		        title: '取消订单',
		        area: ['800px', '420px'], //宽高
		        fix: false, //不固定
		        maxmin: true,
		        content: Feng.ctxPath + '/specialServiceOrder/open_cancel_order/'+SpecialServiceOrder.seItem.orderId
		    });
		    this.layerIndex = index;
	}
};
//打开详情
SpecialServiceOrder.openDetail = function(){
	if(this.check()){
		 var index = layer.open({
		        type: 2,
		        title: '订单详情',
		        area: ['800px', '600px'], //宽高
		        fix: false, //不固定
		        maxmin: true,
		        content: Feng.ctxPath + '/specialServiceOrder/open_detail/'+SpecialServiceOrder.seItem.orderId
		    });
		    this.layerIndex = index;
	}
}
//打开评价详情
SpecialServiceOrder.openfevaluateDetail = function(){
	if(this.check()){
		if(SpecialServiceOrder.seItem.ifevaluate!=2){
			Feng.error("该订单尚未评价");
			return;
		}
		 var index = layer.open({
		        type: 2,
		        title: '评价详情',
		        area: ['600px', '600px'], //宽高
		        fix: false, //不固定
		        maxmin: true,
		        content: Feng.ctxPath + '/specialServiceOrder/open_fevaluateDetail/'+SpecialServiceOrder.seItem.orderId
		    });
		    this.layerIndex = index;
	}
}
//打开评价详情
SpecialServiceOrder.opencomplaintDetail = function(){
	if(this.check()){
		if(SpecialServiceOrder.seItem.iscomplaint!=2){
			Feng.error("该订单无投诉");
			return;
		}
		 var index = layer.open({
		        type: 2,
		        title: '投诉详情',
		        area: ['800px', '600px'], //宽高
		        fix: false, //不固定
		        maxmin: true,
		        content: Feng.ctxPath + '/specialServiceOrder/open_complaintDetail/'+SpecialServiceOrder.seItem.orderId
		    });
		    this.layerIndex = index;
	}
}
/**
 * 查询特色服务列表
 */
SpecialServiceOrder.search = function () {
    var queryData = {
    		"nId":$("#nId").val(),
    		"serviceType":$("#serviceType").val(),
    		"servicePaymentType":$("#servicePaymentType").val(),
    		"sOrderStatus":$("#sOrderStatus").val(),
    		"serviceName":$("#serviceName").val(),
    		"beginDate":$("#beginDate").val(),
    		"endDate":$("#endDate").val()
    };
    SpecialServiceOrder.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = SpecialServiceOrder.initColumn();
    var table = new BSTable(SpecialServiceOrder.id, "/specialServiceOrder/doinglist", defaultColunms);
    table.setPaginationType("server");
    SpecialServiceOrder.table = table.init();
});
