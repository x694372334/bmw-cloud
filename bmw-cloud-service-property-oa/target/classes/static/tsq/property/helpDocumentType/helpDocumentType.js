/**
 * 管理初始化
 */
var helpDocumentType = {
    id: "helpDocumentTypeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
helpDocumentType.initColumn = function () {
	var columns = [
        {field: 'selectItem', radio: true},
	        {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle' },
	        {title: '创建人', field: 'createMan', visible: true, align: 'center', valign: 'middle'},
	        {title: '创建时间', field: 'createTime', visible: false, align: 'center', valign: 'middle',sortable: true},
	        {title: '修改人', field: 'editMan', visible: true, align: 'center', valign: 'middle'},
	        {title: '修改时间', field: 'editTime', visible: false, align: 'center', valign: 'middle',sortable: true},
	        ]
	  return columns;
};

/**
 * 检查是否选中
 */
helpDocumentType.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        helpDocumentType.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
helpDocumentType.openAddhelpDocumentType = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/helpDocumentType/helpDocumentType_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
helpDocumentType.openhelpDocumentTypeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/helpDocumentType/helpDocumentType_update/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
helpDocumentType.delete = function () {
    if (this.check()) {
    	
    	var operation = function () {
    		var ajax = new $ax(Feng.ctxPath + "/helpDocumentType/delete", function (data) {
	            Feng.success("删除成功!");
	            helpDocumentType.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("helpDocumentTypeId",helpDocumentType.seItem.id);
	        ajax.start();
        };

        Feng.confirm("是否刪除该菜单?", operation);
    }
};

/**
 * 查询列表
 */
helpDocumentType.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    helpDocumentType.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = helpDocumentType.initColumn();
    var table = new BSTreeTable(helpDocumentType.id, "/helpDocumentType/list", defaultColunms);
    table.setExpandColumn(1);
    table.setIdField("id");
    table.setCodeField("code");
    table.setParentCodeField("parentCode");
    table.setExpandAll(true);
    table.init();
    helpDocumentType.table = table;
});
