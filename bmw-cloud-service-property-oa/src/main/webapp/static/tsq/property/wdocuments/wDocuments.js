/**
 * wdocuments管理初始化
 */
var WDocuments = {
    id: "WDocumentsTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};


/**
 * 初始化表格的列
 */
WDocuments.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '文档id', field: 'dId', visible: false, align: 'center', valign: 'middle'},
            /*{title: '小区id', field: 'nId', visible: true, align: 'center', valign: 'middle'},*/
            {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '文档名称', field: 'dName', visible: true, align: 'center', valign: 'middle'},
            {title: '文档类型', field: 'dType', visible: true, align: 'center', valign: 'middle',
            	formatter : function (value, row, index) {
            		if(row.dType == 1){
            			return "国家法律";
            		}else if(row.dType == 2){
            			return "地方法规";
            		}else if(row.dType == 3){
            			return "内部文档";
            		}
                }
            },
            {title: '共享范围', field: 'sharingLevel', visible: true, align: 'center', valign: 'middle',
            	formatter : function (value, row, index) {
            		if(row.sharingLevel == 1){
            			return "个人可见";
            		}else if(row.sharingLevel == 2){
            			return "物业可见";
            		}else if(row.sharingLevel == 3){
            			return "公司可见";
            		}
                }
            },
            /*{title: '文档url', field: 'dUrl', visible: true, align: 'center', valign: 'middle'},*/
            {title: '上传人', field: 'createMan', visible: true, align: 'center', valign: 'middle'},
            {title: '上传时间', field: 'upTime', visible: true, align: 'center', valign: 'middle'}
            /*{title: '创建人id', field: 'createManId', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createMan', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '修改人id', field: 'editManId', visible: true, align: 'center', valign: 'middle'},
            {title: '修改人', field: 'editMan', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'editTime', visible: true, align: 'center', valign: 'middle'},
            {title: '是否删除1未删除2已删除', field: 'isDelete', visible: true, align: 'center', valign: 'middle'}*/
    ];
};

/**
 * 检查是否选中
 */
WDocuments.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        WDocuments.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加wdocuments
 */
WDocuments.openAddWDocuments = function () {
    var index = layer.open({
        type: 2,
        title: '添加wdocuments',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/wDocuments/wDocuments_add'
    });
    this.layerIndex = index;
};


/**
 * 打开上传文档页面
 */
WDocuments.upload = function () {
    var index = layer.open({
        type: 2,
        title: '上传文档',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/wDocuments/wDocuments_upload'
    });
    this.layerIndex = index;
};


/**
 * 打开查看wdocuments详情
 */
WDocuments.openWDocumentsDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: 'wdocuments详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/wDocuments/wDocuments_update/' + WDocuments.seItem.dId
        });
        this.layerIndex = index;
    }
};

/**
 * 删除wdocuments
 */
WDocuments.delete = function () {
    if (this.check()) {
    	var operation = function(){
        var ajax = new $ax(Feng.ctxPath + "/wDocuments/delete", function (data) {
            Feng.success("删除成功!");
            WDocuments.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("wDocumentsId",WDocuments.seItem.dId);
        ajax.start();
    };
 	Feng.confirm("是否删除",operation);
    }
};

/**
 * 查询wdocuments列表
 */
WDocuments.search = function () {
    var queryData = {};
    queryData['dName'] = $("#dName").val();
    WDocuments.table.refresh({query: queryData});
};

/**
 * 下载文档
 */
WDocuments.download = function() {
	if (this.check()) {
		 var form = $("<form>");
		    form.attr('style', 'display:none');
		    form.attr('target', '');
		    form.attr('method', 'post');
		    form.attr('action', Feng.ctxPath + "/wDocuments/download");
		    
		    var input = $('<input>');
		    input.attr('type', 'hidden');
		    input.attr('name', 'wDocumentsId');
		    input.attr('value', this.seItem.dId);
		    
		    $('body').append(form);
		    form.append(input);
		    
		    form.submit();
		    form.remove();
	}
}


$(function () {
    var defaultColunms = WDocuments.initColumn();
    var table = new BSTable(WDocuments.id, "/wDocuments/list", defaultColunms);
    table.setPaginationType("client");
    WDocuments.table = table.init();
});
