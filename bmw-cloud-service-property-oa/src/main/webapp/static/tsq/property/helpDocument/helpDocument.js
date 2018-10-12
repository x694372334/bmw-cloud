/**
 * helpDocument管理初始化
 */
var HelpDocument = {
    id: "HelpDocumentTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
HelpDocument.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
           /* {title: '主键id', field: 'id', visible: false, align: 'center', valign: 'middle'},*/
            {title: '分类名称', field: 'helpDocumentType', visible: true, align: 'center', valign: 'middle'},
            /*{title: '分类ID', field: 'helpDocumentTypeId', visible: true, align: 'center', valign: 'middle'},*/
            {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
           /* {title: '内容', field: 'content', visible: true, align: 'center', valign: 'middle'}*/
           /* {title: '创建人ID', field: 'createId', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createMan', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '修改人id', field: 'editManId', visible: true, align: 'center', valign: 'middle'},
            {title: '修改人', field: 'editMan', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'editTime', visible: true, align: 'center', valign: 'middle'},
            {title: '附件文件名', field: 'fileName', visible: true, align: 'center', valign: 'middle'},
            {title: '附件文件路径', field: 'filePath', visible: true, align: 'center', valign: 'middle'},
            {title: '附件文件格式', field: 'fileFormat', visible: true, align: 'center', valign: 'middle'},*/
            {title: '关键字', field: 'keyword', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
HelpDocument.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        HelpDocument.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加helpDocument
 */
HelpDocument.openAddHelpDocument = function () {
    var index = layer.open({
        type: 2,
        title: '添加帮助文档',
        area: ['800px', '680px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/helpDocument/helpDocument_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看helpDocument详情
 */
HelpDocument.openHelpDocumentDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '帮助详情',
            area: ['800px', '650px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/helpDocument/helpDocument_update/' + HelpDocument.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 查看
 */
HelpDocument.openHelpDocumentView = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '帮助文档查看',
            area: ['800px', '650px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/helpDocument/helpDocument_view/' + HelpDocument.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除helpDocument
 */
HelpDocument.delete = function () {
    if (this.check()) {
    	var operation = function(){
        var ajax = new $ax(Feng.ctxPath + "/helpDocument/delete", function (data) {
            Feng.success("删除成功!");
            HelpDocument.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("helpDocumentId",HelpDocument.seItem.id);
        ajax.start();
    	};
    	Feng.confirm("是否删除",operation);
    }
};

/**
 * 查询helpDocument列表
 */
HelpDocument.search = function () {
    var queryData = {};
    queryData['helpDocumentTypeId'] = $("#helpDocumentTypeId").val();
    queryData['keyword'] = $("#keyword").val();
    HelpDocument.table.refresh({query: queryData});
};

HelpDocument.onClickType = function (e, treeId, treeNode) {
	$("#helpDocumentTypeId").val(treeNode.id);
	//HelpDocument.helpDocumentTypeId = treeNode.id;
	HelpDocument.search();
};

$(function () {
	//分类树
	var ztree = new $ZTree("typeTree", "/helpDocumentType/selectHelpDocumentTypeTreeList");
    ztree.bindOnClick(HelpDocument.onClickType);
    ztree.init();
    
    var defaultColunms = HelpDocument.initColumn();
    var table = new BSTable(HelpDocument.id, "/helpDocument/list", defaultColunms);
    table.setPaginationType("client");
    HelpDocument.table = table.init();
});
