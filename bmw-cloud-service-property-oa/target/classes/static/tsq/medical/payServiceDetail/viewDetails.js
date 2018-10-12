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

$(function () {
    var defaultColunms = PayServiceDetail.initColumn();
    var table = new BSTable(PayServiceDetail.id, "/payServiceDetail/findByPayServiceId/"+$("#payServiceId").val(), defaultColunms);
    table.setPaginationType("client");
    PayServiceDetail.table = table.init();
});
