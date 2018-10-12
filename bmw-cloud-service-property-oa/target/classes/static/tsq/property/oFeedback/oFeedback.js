/**
 * 意见反馈管理初始化
 */
var OFeedback = {
    id: "OFeedbackTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
OFeedback.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '所属小区', field: 'nName', visible: true, align: 'center', valign: 'middle'},
            {title: '反馈人', field: 'oName', visible: true, align: 'center', valign: 'middle',},
            {title: '反馈意见', field: 'opinion', visible: true, align: 'center', valign: 'middle'},
            {title: '反馈时间', field: 'feedbackTime', visible: true, align: 'center', valign: 'middle'},
            {title: '物业处理结果', field: 'handlingResult', visible: true, align: 'center', valign: 'middle'},
            {title: '物业处理时间', field: 'handlingTime', visible: true, align: 'center', valign: 'middle'},
            {title: '物业回复用户', field: 'uName', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle',
            	formatter : function(value, row, index) {
	            	if(value == "0"){
	            		return "未回复" ;
	            	}else if(value == "1"){
	            		return "已回复" ;
	            	}
	            }	
            },
            {title: '创建人', field: 'createMan', visible: false, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: false, align: 'center', valign: 'middle'},
            {title: '修改人', field: 'editMan', visible: false, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'editTime', visible: false, align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
OFeedback.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        OFeedback.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加意见反馈
 */
OFeedback.openAddOFeedback = function () {
    var index = layer.open({
        type: 2,
        title: '添加意见反馈',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/oFeedback/oFeedback_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看意见反馈详情
 */
OFeedback.openOFeedbackUpdate = function () {
    if (this.check()) {
    	if("1"==OFeedback.seItem.status){
    		Feng.info("已回复,不能再次修改！");
    		return;
    	}
        var index = layer.open({
            type: 2,
            title: '意见反馈详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/oFeedback/oFeedback_update/' + OFeedback.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 打开回复意见反馈
 */
OFeedback.replyOFeedback = function () {
	if (this.check()) {
		if("1"==OFeedback.seItem.status){
    		Feng.info("已回复！");
    		return;
    	}
		var index = layer.open({
			type: 2,
			title: '回复意见反馈',
			area: ['800px', '420px'], //宽高
			fix: false, //不固定
			maxmin: true,
			content: Feng.ctxPath + '/oFeedback/oFeedback_reply/' + OFeedback.seItem.id
		});
		this.layerIndex = index;
	}
};

/**
 * 删除意见反馈
 */
OFeedback.delete = function () {
    if (this.check()) {
    	if("1"==OFeedback.seItem.status){
    		Feng.info("已回复，不能删除！");
    		return;
    	}
        var ajax = new $ax(Feng.ctxPath + "/oFeedback/delete", function (data) {
            Feng.success("删除成功!");
            OFeedback.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("oFeedbackId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询意见反馈列表
 */
OFeedback.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    OFeedback.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = OFeedback.initColumn();
    var table = new BSTable(OFeedback.id, "/oFeedback/list", defaultColunms);
    table.setPaginationType("server");
    OFeedback.table = table.init();
});
