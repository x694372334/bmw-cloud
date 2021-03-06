/**
 * 账单明细管理初始化
 */
var Bill = {
    id: "BillTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Bill.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            {title: '主键id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            /*{title: '关联费用标准主键id', field: 'standardId', visible: true, align: 'center', valign: 'middle'},
            {title: '关联费用标准主键名称', field: 'standardName', visible: true, align: 'center', valign: 'middle'},*/
            {title: '账单编号', field: 'billNo', visible: true, align: 'center', valign: 'middle'},
            {title: '小区', field: 'neighborhoodsName', visible: true, align: 'center', valign: 'middle'},
            {title: '楼宇', field: 'bname', visible: true, align: 'center', valign: 'middle',
            	formatter : function (value, row, index) {
            		if(row.relevanceId == 1 || row.relevanceId == 4 || row.relevanceId == 5){
            			return value;
            		}else{
            			return "";
            		}
                }
            },
            {title: '单元', field: 'unitnum', visible: true, align: 'center', valign: 'middle',
            	formatter : function (value, row, index) {
            		if(row.relevanceId == 1 || row.relevanceId == 4 || row.relevanceId == 5){
            			return value;
            		}else{
            			return "";
            		}
                }
            },
            {title: '业主ID', field: 'ownerId', visible: false, align: 'center', valign: 'middle'},
            {title: '房号/车位号', field: 'objectNo', visible: true, align: 'center', valign: 'middle'},
            {title: '住户', field: 'ownerName', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号', field: 'ownerPhone', visible: true, align: 'center', valign: 'middle'},
            {title: '费用开始时间', field: 'costStartTime', visible: true, align: 'center', valign: 'middle'},
            {title: '费用结束时间', field: 'costEndTime', visible: true, align: 'center', valign: 'middle'},
            {title: '应缴合计', field: 'shouldTotal', visible: true, align: 'center', valign: 'middle'},
            {title: '滞纳金', field: 'overdueFine', visible: true, align: 'center', valign: 'middle'},
           /* {title: '催缴次数', field: 'askCount', visible: true, align: 'center', valign: 'middle'},
            {title: '申请优惠状态（待审批、审批通过、已拒绝）', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '优惠比例', field: 'discountRate', visible: true, align: 'center', valign: 'middle'},*/
            {title: '优惠金额', field: 'discountAmount', visible: true, align: 'center', valign: 'middle'},
            {title: '审批类型', field: 'applicationType', visible: true, align: 'center', valign: 'middle'},
            {title: '优惠申请ID', field: 'discountsId', visible: false, align: 'center', valign: 'middle'},
            {title: '审批状态', field: 'status', visible: true, align: 'center', valign: 'middle',
            	formatter : function (value, row, index) {
                    if (value === 1) {
                        return '待审批';
                    }
                    if (value === 2) {
                        return '审批通过';
                    }
                    if (value === 3) {
                        return '已拒绝';
                    }
                }  }
           /* {title: '实缴合计', field: 'paidTotal', visible: true, align: 'center', valign: 'middle'},*/
           /* {title: '状态', field: 'isFee', visible: true, align: 'center', valign: 'middle',
            	formatter : function (value, row, index) {
                    if (value === 0) {
                        return '已缴';
                    }
                    if (value === 1) {
                        return '未缴';
                    }
                }   }*/
           /* {title: '缴费方式', field: 'payMode', visible: true, align: 'center', valign: 'middle'},
            {title: '支付方式：1、现金，2、支付宝，3、微信', field: 'payWay', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'},
            {title: '退款金额（多次退款累加和）', field: 'refundAmount', visible: true, align: 'center', valign: 'middle'}*/
    ];
};

/**
 * 检查是否选中(单选)
 */
Bill.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else if(selected.length > 1){
    	Feng.info("请先选择一条记录！");
    }else{
        Bill.seItem = selected[0];
        return true;
    }
};

/**
 * 检查是否选中(多选)
 */
Bill.checks = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Bill.seItem = selected;
        return true;
    }
};

/**
 * 点击添加账单明细
 */
Bill.openAddBill = function () {
    var index = layer.open({
        type: 2,
        title: '添加账单明细',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bill/bill_add'
    });
    this.layerIndex = index;
};


/**
 * 查看
 */
Bill.view = function () {
	 if (this.check()) {
    var index = layer.open({
        type: 2,
        title: '查看申请详情',
        area: ['1300px', '520px'], // 宽高
        fix: false, // 不固定
        maxmin: true,
        content: Feng.ctxPath + '/bill/bill_discounts_view/' + Bill.seItem.id+'/'+ Bill.seItem.discountsId
    });
    this.layerIndex = index;
	 }
};


/**
 * 打开查看账单明细详情
 */
Bill.openBillDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '账单明细详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bill/bill_update/' + Bill.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除账单明细
 */
Bill.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bill/delete", function (data) {
            Feng.success("删除成功!");
            Bill.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("billId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询账单明细列表
 */
Bill.search = function () {
    var queryData = {};
    queryData['neighborhoodsId'] = $("#neighborhoodsId").val();
    queryData['ownerName'] = $("#ownerName").val();
    queryData['objectNo'] = $("#objectNo").val();
    queryData['ownerPhone'] = $("#ownerPhone").val();
    queryData['status'] = $("#status").val();
    Bill.table.refresh({query: queryData});
};

$(function () {
	//初始化适用小区
    $.ajax({
        "type" : 'get',
        "url": Feng.ctxPath + "/neighborhood/list",
        "dataType" : "json",
        "success" : function(data) {
            for(var i = 0 ; i < data.length; i++ ){
            		$("#neighborhoodsId").append("<option value='"+data[i].nId+"'>"+data[i].nName+"</option>");
            }
        }
    });
    var defaultColunms = Bill.initColumn();
    var table = new BSTable(Bill.id, "/bill/list_discounts", defaultColunms);
    table.setPaginationType("client");
    Bill.table = table.init();
});
