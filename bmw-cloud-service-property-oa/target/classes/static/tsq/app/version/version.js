/**
 * 版本管理管理初始化
 */
var Version = {
    id: "VersionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Version.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '版本号', field: 'number', visible: true, align: 'center', valign: 'middle'},
            {title: '版本登记', field: 'gradeNumber', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createBy', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '升级人', field: 'gradeBy', visible: true, align: 'center', valign: 'middle'},
            {title: '升级时间', field: 'gradeTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Version.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Version.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加版本管理
 */
Version.openAddVersion = function () {
    var index = layer.open({
        type: 2,
        title: '添加版本管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/version/version_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看版本管理详情
 */
Version.openVersionDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '版本管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/version/version_update/' + Version.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除版本管理
 */
Version.delete = function () {
    if (this.check()) {
    	var operation = function(){
        var ajax = new $ax(Feng.ctxPath + "/version/delete", function (data) {
            Feng.success("删除成功!");
            Version.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("versionId",Version.seItem.id);
        ajax.start();
    	};
    	Feng.confirm("是否删除",operation);
    }
};

/**
 * 查询版本管理列表
 */
Version.search = function () {
    var queryData = {};
    queryData['number'] = $("#number").val();
    Version.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Version.initColumn();
    var table = new BSTable(Version.id, "/version/list", defaultColunms);
    table.setPaginationType("client");
    Version.table = table.init();
});
