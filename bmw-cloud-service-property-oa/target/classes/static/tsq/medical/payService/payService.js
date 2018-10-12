/**
 * 收费项目管理管理初始化
 */
var PayService = {
    id: "PayServiceTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PayService.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '收费项目名称', field: 'itemName', visible: true, align: 'center', valign: 'middle'},
        {title: '收费标准', field: 'chargeStandard', visible: true, align: 'center', valign: 'middle'},
        {title: '创建人', field: 'createMan', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
        {title: '修改人', field: 'editMan', visible: true, align: 'center', valign: 'middle'},
        {title: '修改时间', field: 'editTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
PayService.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PayService.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加收费项目管理
 */
PayService.openAddPayService = function () {
    var index = layer.open({
        type: 2,
        title: '添加收费项目管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/payService/payService_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看收费项目管理详情
 */
PayService.openPayServiceDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '收费项目管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/payService/payService_update/' + PayService.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 添加收费项目明细
 */
PayService.addDetails = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '添加收费项目明细',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/payServiceDetail/payServiceDetail_add/' + PayService.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 查看收费项目明细
 */
PayService.viewDetails = function () {
	if (this.check()) {
		var index = layer.open({
			type: 2,
			title: '查看收费项目明细',
			area: ['800px', '420px'], //宽高
			fix: false, //不固定
			maxmin: true,
			content: Feng.ctxPath + '/payServiceDetail/viewDetails/' + PayService.seItem.id
		});
		this.layerIndex = index;
	}
};

/**
 * 删除收费项目管理
 */
PayService.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/payService/delete", function (data) {
            Feng.success("删除成功!");
            PayService.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("payServiceId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询收费项目管理列表
 */
PayService.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    PayService.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = PayService.initColumn();
    var table = new BSTable(PayService.id, "/payService/list", defaultColunms);
    table.setPaginationType("client");
    PayService.table = table.init();
});
