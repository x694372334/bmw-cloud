/**
 * 投诉建议管理初始化
 */
var ComplaintAdvice = {
    id: "ComplaintAdviceTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ComplaintAdvice.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '业主姓名', field: 'ownerName', visible: true, align: 'center', valign: 'middle'},
            {title: '房屋号', field: 'neighborhoodsNo', visible: true, align: 'center', valign: 'middle'},
            {title: '联系电话', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '投诉内容', field: 'complaintContent', visible: true, align: 'center', valign: 'middle'},
            /*{title: '投诉时间', field: 'complaintTime', visible: false, align: 'center', valign: 'middle'},*/
            {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle',
            	 formatter : function (value, row, index) {
                     if (value === 1) {
                         return '已回复';
                     }
                     if (value === 0) {
                         return '未回复';
                     }
                    // return value;
                 }  },
                 
            {title: '回复时间', field: 'replyTime', visible: true, align: 'center', valign: 'middle'},
          /* {title: '回复人id', field: 'replyUserId', visible: false, align: 'center', valign: 'middle'},
            {title: '回复人姓名', field: 'replyUserName', visible: false, align: 'center', valign: 'middle'},*/
            /* {title: '回复内容', field: 'replyContent', visible: true, align: 'center', valign: 'middle'}
            {title: '创建人ID', field: 'createManId', visible: false, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createMan', visible: false, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: false, align: 'center', valign: 'middle'},
            {title: '修改人id', field: 'editManId', visible: false, align: 'center', valign: 'middle'},
            {title: '修改人', field: 'editMan', visible: false, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'editTime', visible: false, align: 'center', valign: 'middle'},
            {title: '是否删除', field: 'isDelete', visible: false, align: 'center', valign: 'middle'}*/
    ];
};

/**
 * 检查是否选中
 */
ComplaintAdvice.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ComplaintAdvice.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加投诉建议
 */
ComplaintAdvice.openAddComplaintAdvice = function () {
    var index = layer.open({
        type: 2,
        title: '添加投诉建议',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/complaintAdvice/complaintAdvice_add'
    });
    this.layerIndex = index;
};

/**
 * 回复
 */
ComplaintAdvice.openComplaintAdviceDetail = function () {
    if (this.check()) {
    	if("1"==ComplaintAdvice.seItem.status){
    		Feng.info("已回复！");
    		return;
    	}
        var index = layer.open({
            type: 2,
            title: '回复',
            area: ['800px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/complaintAdvice/complaintAdvice_update/' + ComplaintAdvice.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看投诉建议详情（不可编辑）
 */
ComplaintAdvice.openComplaintAdviceView = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '投诉建议详情',
            area: ['800px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/complaintAdvice/complaintAdvice_view/' + ComplaintAdvice.seItem.id
        });
        this.layerIndex = index;
    }
};


/**
 * 删除投诉建议
 */
ComplaintAdvice.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/complaintAdvice/delete", function (data) {
            Feng.success("删除成功!");
            ComplaintAdvice.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("complaintAdviceId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询投诉建议列表
 */
ComplaintAdvice.search = function () {
    var queryData = {};
    
    queryData['ownerName'] = $("#ownerName").val();
    queryData['neighborhoodsNo'] = $("#neighborhoodsNo").val();
    queryData['phone'] = $("#phone").val();
    queryData['status'] = $("#status").val();
    
    ComplaintAdvice.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ComplaintAdvice.initColumn();
    var table = new BSTable(ComplaintAdvice.id, "/complaintAdvice/list", defaultColunms);
    table.setPaginationType("client");
    ComplaintAdvice.table = table.init();
});
