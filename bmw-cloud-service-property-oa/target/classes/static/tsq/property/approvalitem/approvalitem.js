/**
 * 事项审批管理初始化
 */
var Approvalitem = {
    id: "ApprovalitemTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Approvalitem.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '项目名称', field: 'iName', visible: true, align: 'center', valign: 'middle'},
        {title: '小区名', field: 'nName', visible: true, align: 'center', valign: 'middle'},
        {title: '审批人', field: 'aUser', visible: true, align: 'center', valign: 'middle'},
        {title: '审批结果', field: 'aUser', visible: true, align: 'center', valign: 'middle'}
        ];
};

/**
 * 检查是否选中
 */
Approvalitem.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Approvalitem.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加事项审批
 */
Approvalitem.openAddApprovalitem = function () {
    var index = layer.open({
        type: 2,
        title: '添加事项审批',
        area: ['500px', '800px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/approvalitem/approvalitem_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看事项审批详情
 */
Approvalitem.openApprovalitemDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '事项审批详情',
            area: ['500px', '800px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/approvalitem/approvalitem_update/' + Approvalitem.seItem.aId
        });
        this.layerIndex = index;
    }
};

/**
 * 删除事项审批
 */
Approvalitem.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/approvalitem/delete", function (data) {
            Feng.success("删除成功!");
            Approvalitem.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("approvalitemId",this.seItem.aId);
        ajax.start();
    }
};

/**
 * 查询事项审批列表
 */
Approvalitem.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Approvalitem.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Approvalitem.initColumn();
    var table = new BSTable(Approvalitem.id, "/approvalitem/list", defaultColunms);
    table.setPaginationType("client");
    Approvalitem.table = table.init();
});
