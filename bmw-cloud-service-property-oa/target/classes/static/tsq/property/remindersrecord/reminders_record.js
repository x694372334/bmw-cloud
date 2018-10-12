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
            {title: '费用类型', field: 'relevanceId', visible: false, align: 'center', valign: 'middle'},
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
            {title: '住户', field: 'ownerName', visible: true, align: 'center', valign: 'middle'},
            {title: '房号/车位号', field: 'objectNo', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号', field: 'ownerPhone', visible: true, align: 'center', valign: 'middle'},
            {title: '费用开始时间', field: 'costStartTime', visible: true, align: 'center', valign: 'middle'},
            {title: '费用结束时间', field: 'costEndTime', visible: true, align: 'center', valign: 'middle'},
            {title: '欠费天数', field: 'arrearsDay', visible: true, align: 'center', valign: 'middle'},
            {title: '欠费金额', field: 'shouldTotal', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'isUrge', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
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
 * 一键催缴
 */
Bill.urge = function() {
	if (this.checks()) {
		var ids = "";
		$.each(Bill.seItem, function(i, item) {
			ids = ids + item.id + ",";
		});
		
	    var ajax = new $ax(Feng.ctxPath + "/remindersRecord/urge/"+ids, function(data){
	        Feng.success("催缴成功!");
	        Bill.table.refresh();
	    },function(data){
	        Feng.error("催缴失败!" + data.responseJSON.message + "!");
	    });
	    ajax.start();
	    
	}
};

/**
 * 导出excel
 */
Bill.exporting = function(){
	document.getElementById("dc").click(); 
}



/**
 * 查询账单明细列表
 */
Bill.search = function () {
    var queryData = {};
    queryData['neighborhoodsId'] = $("#neighborhoodsId").val();
    queryData['costId'] = $("#costId").val();
    queryData['objectNo'] = $("#objectNo").val();
    /*queryData['ownerPhone'] = $("#ownerPhone").val();*/
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
    //获取收费项目
    $.ajax({
        "type" : 'get',
        "url": Feng.ctxPath + "/costSet/getCostSetAll",
        "dataType" : "json",
        "success" : function(data) {
            for(var i = 0 ; i < data.length; i++ ){
            		$("#costId").append("<option value='"+data[i].id+"'>"+data[i].costName+"</option>");
            }
        }
    });
    var defaultColunms = Bill.initColumn();
    var table = new BSTable(Bill.id, "/remindersRecord/list", defaultColunms);
    table.setPaginationType("client");
    Bill.table = table.init();
});
