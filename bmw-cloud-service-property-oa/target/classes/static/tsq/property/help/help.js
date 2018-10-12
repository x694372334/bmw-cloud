/**
 * help管理初始化
 */
var Help = {
    id: "HelpTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Help.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人ID', field: 'createId', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createMan', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '修改人id', field: 'editManId', visible: true, align: 'center', valign: 'middle'},
            {title: '修改人', field: 'editMan', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'editTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Help.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Help.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加help
 */
Help.openAddHelp = function () {
    var index = layer.open({
        type: 2,
        title: '添加help',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/help/help_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看help详情
 */
Help.openHelpDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: 'help详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/help/help_update/' + Help.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除help
 */
Help.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/help/delete", function (data) {
            Feng.success("删除成功!");
            Help.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("helpId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询help列表
 */
Help.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Help.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Help.initColumn();
    var table = new BSTable(Help.id, "/help/list", defaultColunms);
    table.setPaginationType("client");
    Help.table = table.init();
});
