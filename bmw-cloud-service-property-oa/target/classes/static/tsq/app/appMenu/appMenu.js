/**
 * 菜单管理管理初始化
 */
var AppMenu = {
    id: "AppMenuTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
AppMenu.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '编码', field: 'code', visible: true, align: 'center', valign: 'middle'},
            {title: '菜单名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '图片', field: 'icon', visible: true, align: 'center', valign: 'middle'},
            {title: '地址', field: 'url', visible: true, align: 'center', valign: 'middle'},
            {title: '是否启用', field: 'isOpen', visible: true, align: 'center', valign: 'middle'},
            {title: '背景色', field: 'backGroundUrl', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
AppMenu.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        AppMenu.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加菜单管理
 */
AppMenu.openAddAppMenu = function () {
    var index = layer.open({
        type: 2,
        title: '添加菜单管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/appMenu/appMenu_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看菜单管理详情
 */
AppMenu.openAppMenuDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '菜单管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/appMenu/appMenu_update/' + AppMenu.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除菜单管理
 */
AppMenu.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/appMenu/delete", function (data) {
            Feng.success("删除成功!");
            AppMenu.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("appMenuId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询菜单管理列表
 */
AppMenu.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    AppMenu.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = AppMenu.initColumn();
    var table = new BSTable(AppMenu.id, "/appMenu/list", defaultColunms);
    table.setPaginationType("client");
    AppMenu.table = table.init();
});
