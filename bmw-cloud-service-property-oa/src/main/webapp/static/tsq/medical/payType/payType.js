/**
 * 收费项目分类管理初始化
 */
var PayType = {
    id: "PayTypeTable",	
    seItem: null,
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PayType.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '创建人', field: 'createMan', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
        {title: '修改人', field: 'editMan', visible: true, align: 'center', valign: 'middle'},
        {title: '修改时间', field: 'editTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
PayType.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
    	PayType.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加收费项目分类
 */
PayType.openAddPayType = function () {
    var index = layer.open({
        type: 2,
        title: '添加收费项目分类',
        area: ['800px', '420px'], 
        fix: false, 
        maxmin: true,
        content: Feng.ctxPath + '/payType/payType_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看收费项目分类详情
 */
PayType.openPayTypeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '收费项目分类详情',
            area: ['800px', '420px'], 
            fix: false, 
            maxmin: true,
            content: Feng.ctxPath + '/payType/payType_update/' + PayType.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除收费项目分类
 */
PayType.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/payType/delete", function (data) {
            Feng.success("删除成功!");
            PayType.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("payTypeId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询收费项目分类列表
 */
PayType.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    PayType.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = PayType.initColumn();
    
    var table = new BSTreeTable(PayType.id, "/payType/list", defaultColunms);
    table.setIdField("id");
    table.setCodeField("code");
    table.setParentCodeField("parentCode");
    table.setExpandAll(true);
    PayType.table = table.init();
});
