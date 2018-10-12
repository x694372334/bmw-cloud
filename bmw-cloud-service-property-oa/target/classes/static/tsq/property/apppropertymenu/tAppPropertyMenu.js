/**
 * apppropertymenu管理初始化
 */
var TAppPropertyMenu = {
    id: "TAppPropertyMenuTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TAppPropertyMenu.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键id', field: 'mId', visible: false, align: 'center', valign: 'middle'},
            {title: '菜单名称', field: 'menuName', visible: true, align: 'center', valign: 'middle'},
            /*{title: '菜单描述', field: 'menuDescription', visible: true, align: 'center', valign: 'middle'},*/
            {title: '排序', field: 'menuSort', visible: true, align: 'center', valign: 'middle'},
            {title: '菜单状态 ', field: 'menuStatus', visible: true, align: 'center', valign: 'middle',
            	formatter : function (value, row, index) {
                    if (value === 0) {
                        return '未启用';
                    }
                    if (value === 1) {
                        return '已启用';
                    }
                } }
           /* {title: '创建人id', field: 'createManId', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createMan', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '修改人id', field: 'editManId', visible: true, align: 'center', valign: 'middle'},
            {title: '修改人', field: 'editMan', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'editTime', visible: true, align: 'center', valign: 'middle'},
            {title: '是否删除，0 删除 1 正常', field: 'isDelete', visible: true, align: 'center', valign: 'middle'}*/
    ];
};

/**
 * 检查是否选中
 */
TAppPropertyMenu.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        TAppPropertyMenu.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加apppropertymenu
 */
TAppPropertyMenu.openAddTAppPropertyMenu = function () {
    var index = layer.open({
        type: 2,
        title: '添加apppropertymenu',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/tAppPropertyMenu/tAppPropertyMenu_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看apppropertymenu详情
 */
TAppPropertyMenu.openTAppPropertyMenuDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: 'apppropertymenu详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/tAppPropertyMenu/tAppPropertyMenu_update/' + TAppPropertyMenu.seItem.mId
        });
        this.layerIndex = index;
    }
};

/**
 * 删除apppropertymenu
 */
TAppPropertyMenu.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/tAppPropertyMenu/delete", function (data) {
            Feng.success("删除成功!");
            TAppPropertyMenu.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("tAppPropertyMenuId",this.seItem.mId);
        ajax.start();
    }
};

/**
 * 查询apppropertymenu列表
 */
TAppPropertyMenu.search = function () {
    var queryData = {};
    queryData['menuName'] = $("#menuName").val();
    TAppPropertyMenu.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = TAppPropertyMenu.initColumn();
    var table = new BSTable(TAppPropertyMenu.id, "/tAppPropertyMenu/list", defaultColunms);
    table.setPaginationType("client");
    TAppPropertyMenu.table = table.init();
});
