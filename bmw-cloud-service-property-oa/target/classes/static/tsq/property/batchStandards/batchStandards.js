/**
 * 批量关联收费标准管理初始化
 */
var BatchStandards = {
    id: "BatchStandardsTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BatchStandards.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '所属小区', field: 'nbName', visible: true, align: 'center', valign: 'middle'},
            {title: '收费项目', field: 'costName', visible: true, align: 'center', valign: 'middle'},
            {title: '收费标准名称', field: 'standardName', visible: true, align: 'center', valign: 'middle'},
            {title: '计费开始时间', field: 'chargeableStartDate', visible: true, align: 'center', valign: 'middle'},
            {title: '计费结束时间', field: 'chargeableEndDate', visible: true, align: 'center', valign: 'middle'},
            {  
                field: 'operate',
                title: '操作',
                width: '300px',
                align: 'center',
                formatter: operateFormatter  
            }
    ];
};

function operateFormatter(value, row, index) {
    return [
    	'<button id="btn_detail" type="button" onclick="BatchStandards.openAddScopeIds('+row.id+')" class="btn button-margin btn-primary fa">添加关联范围</button>',
    	'<button id="btn_detail" type="button" onclick="BatchStandards.openBatchStandardsEdit('+row.id+')" class="btn button-margin btn-primary fa">详情</button>',
        /*'<button id="btn_detail" type="button" onclick="BatchStandards.openBatchStandardsDetail('+row.id+')" class="btn button-margin btn-primary fa">详情</button>',*/ 
        /*'<button id="btn_detail" type="button" onclick="BatchStandards.openBatchStandardsEdit('+row.id+')" class="btn button-margin btn-primary fa">修改</button>',*/
        '<button id="btn_delete" type="button" onclick="BatchStandards.delete('+row.id+')" class="btn btn-primary button-margin fa fa-remove">作废</button>'
    ].join('');  
}

/**
 * 检查是否选中
 */
BatchStandards.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BatchStandards.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加批量关联收费标准
 */
BatchStandards.openAddBatchStandards = function () {
    var index = layer.open({
        type: 2,
        title: '添加批量关联收费标准',
        area: ['800px', '620px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/batchStandards/batchStandards_add'
    });
    this.layerIndex = index;
};

/**
 * 修改
 */
BatchStandards.openBatchStandardsEdit = function (id) {
        var index = layer.open({
            type: 2,
            title: '批量关联收费标准详情',
            area: ['800px', '620px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/batchStandards/batchStandards_update/' + id
        });
        this.layerIndex = index;
};


/**
 * 详情
 */
BatchStandards.openBatchStandardsDetail = function (id) {
        var index = layer.open({
            type: 2,
            title: '批量关联收费标准详情',
            area: ['800px', '300px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/batchStandards/batchStandards_detail/' + id
        });
        this.layerIndex = index;
};



/**
 * 点击添加批量关联收费标准
 */
BatchStandards.openAddScopeIds = function (id) {
	
    var index = layer.open({
        type: 2,
        title: '添加关联范围',
        area: ['800px', '620px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/batchStandards/add_scopeIds/' + id
    });
    this.layerIndex = index;
};


/**
 * 删除批量关联收费标准
 */
BatchStandards.delete = function (id) {
        var operation = function(){
        	 var ajax = new $ax(Feng.ctxPath + "/batchStandards/delete", function (data) {
                 Feng.success(data.message);
                 BatchStandards.table.refresh();
             }, function (data) {
                 Feng.error("删除失败!" + data.responseJSON.message + "!");
             });
             ajax.set("batchStandardsId",id);
             ajax.start();
    	};
    	Feng.confirm("是否作废批量关联费用标准及相关联的账单 " + "?", operation);
};

/**
 * 查询批量关联收费标准列表
 */
BatchStandards.search = function () {
    var queryData = {};
    queryData['costId'] = $("#costId").val();
    queryData['neighborhoodsId'] = $("#neighborhoodsId").val();
    BatchStandards.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BatchStandards.initColumn();
    var table = new BSTable(BatchStandards.id, "/batchStandards/list", defaultColunms);
    table.setPaginationType("server");
    BatchStandards.table = table.init();
    
  //初始化适用小区
    $.ajax({
        "type" : 'get',
        "url": Feng.ctxPath + "/neighborhood/list",
        "dataType" : "json",
        "success" : function(data) {
        	$("#neighborhoodsId").append("<option value=''>请选择</option>");
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
        	$("#costId").append("<option value=''>请选择</option>");
            for(var i = 0 ; i < data.length; i++ ){
            		$("#costId").append("<option value='"+data[i].id+"'>"+data[i].costName+"</option>");
            }
        }
    });
});
