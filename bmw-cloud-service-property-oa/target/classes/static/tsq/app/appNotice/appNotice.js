/**
 * 通知管理管理初始化
 */
var AppNotice = {
    id: "AppNoticeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
AppNotice.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
AppNotice.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        AppNotice.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加通知管理
 */
AppNotice.openAddAppNotice = function () {
    var index = layer.open({
        type: 2,
        title: '添加通知管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/appNotice/appNotice_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看通知管理详情
 */
AppNotice.openAppNoticeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '通知管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/appNotice/appNotice_update/' + AppNotice.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除通知管理
 */
AppNotice.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/appNotice/delete", function (data) {
            Feng.success("删除成功!");
            AppNotice.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("appNoticeId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询通知管理列表
 */
AppNotice.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    AppNotice.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = AppNotice.initColumn();
    var table = new BSTable(AppNotice.id, "/appNotice/list", defaultColunms);
    table.setPaginationType("client");
    AppNotice.table = table.init();
});
