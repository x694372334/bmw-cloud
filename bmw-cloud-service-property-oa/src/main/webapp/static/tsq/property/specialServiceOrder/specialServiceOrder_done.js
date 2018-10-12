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
            {title: '订单编码', field: 'orderCode', visible: true, align: 'center', valign: 'middle'},
            {title: '预订人', field: 'appUserRealname', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号', field: 'userPhoneNo', visible: true, align: 'center', valign: 'middle'},
            {title: '下单时间', field: 'orderTime', visible: true, align: 'center', valign: 'middle'},
            {title: '服务名称', field: 'serviceName', visible: true, align: 'center', valign: 'middle'},
            {title: '服务类别', field: 'serviceSortName', visible: true, align: 'center', valign: 'middle'},
            {title: '服务方式', field: 'serviceTypeName', visible: true, align: 'center', valign: 'middle'},
            {title: '收费方式', field: 'servicePaymentTypeName', visible: true, align: 'center', valign: 'middle'},
            {title: '实收款', field: 'realIncome', visible: true, align: 'center', valign: 'middle'},
            {title: '订单状态', field: 'sOrderStatusName', visible: true, align: 'center', valign: 'middle'},
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
 * 点击添加特色服务
 */
SpecialServiceOrder.openAddSpecialServiceOrder = function () {
    var index = layer.open({
        type: 2,
        title: '添加特色服务',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/specialServiceOrder/specialServiceOrder_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看特色服务详情
 */
SpecialServiceOrder.openSpecialServiceOrderDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '特色服务详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/specialServiceOrder/specialServiceOrder_update/' + SpecialServiceOrder.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除特色服务
 */
SpecialServiceOrder.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/specialServiceOrder/delete", function (data) {
            Feng.success("删除成功!");
            SpecialServiceOrder.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("specialServiceOrderId",this.seItem.id);
        ajax.start();
    }
};

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
    var table = new BSTable(SpecialServiceOrder.id, "/specialServiceOrder/donelist", defaultColunms);
    table.setPaginationType("server");
    SpecialServiceOrder.table = table.init();
});
