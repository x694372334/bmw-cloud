/**
 * 收费项目明细管理初始化
 */
var PayServiceDetail = {
    id: "PayServiceDetailTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PayServiceDetail.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '科室名称', field: 'departmentName', visible: true, align: 'center', valign: 'middle'},
        {title: '医生名称', field: 'doctorName', visible: true, align: 'center', valign: 'middle'},
        {title: '创建人', field: 'createMan', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
        {title: '修改人', field: 'editMan', visible: true, align: 'center', valign: 'middle'},
        {title: '修改时间', field: 'editTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
PayServiceDetail.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PayServiceDetail.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加收费项目明细
 */
PayServiceDetail.openAddPayServiceDetail = function () {
    var index = layer.open({
        type: 2,
        title: '添加收费项目明细',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/payServiceDetail/payServiceDetail_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看收费项目明细详情
 */
PayServiceDetail.openPayServiceDetailDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '收费项目明细详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/payServiceDetail/payServiceDetail_update/' + PayServiceDetail.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除收费项目明细
 */
PayServiceDetail.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/payServiceDetail/delete", function (data) {
            Feng.success("删除成功!");
            PayServiceDetail.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("payServiceDetailId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询收费项目明细列表
 */
PayServiceDetail.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    PayServiceDetail.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = PayServiceDetail.initColumn();
    var table = new BSTable(PayServiceDetail.id, "/payServiceDetail/list", defaultColunms);
    table.setPaginationType("client");
    PayServiceDetail.table = table.init();
});
