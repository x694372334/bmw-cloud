/**
 * 费项设置管理初始化
 */
var CostSet = {
    id: "CostSetTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CostSet.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '费项名称', field: 'costName', visible: true, align: 'center', valign: 'middle'},
            {title: '费项类别名称', field: 'costTypeName', visible: true, align: 'center', valign: 'middle'},
            {title: '关联对象', field: 'relevanceName', visible: true, align: 'center', valign: 'middle'},
            {title: '适用小区', field: 'neighborhoodsName', visible: true, align: 'center', valign: 'middle'},
            {  
                field: 'operate',
                title: '操作',
                width: '250px',
                align: 'center',
                formatter: operateFormatter  
            }
    ];
};

function operateFormatter(value, row, index) {
    return [  
        '<button id="btn_detail" type="button" onclick="CostSet.openCostSetDetail('+row.id+')" class="btn button-margin btn-primary fa">详情</button>', 
        '<button id="btn_detail" type="button" onclick="CostSet.openCostSetEdit('+row.id+')" class="btn button-margin btn-primary fa fa-edit">修改</button>',
        '<button id="btn_detail" type="button" onclick="CostSet.delete('+row.id+')" class="btn btn-primary button-margin fa fa-remove">删除</button>'
    ].join('');  
}


/**
 * 检查是否选中
 */
CostSet.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CostSet.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加费项设置
 */
CostSet.openAddCostSet = function () {
    var index = layer.open({
        type: 2,
        title: '添加费项设置',
        area: ['800px', '300px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/costSet/costSet_add'
    });
    this.layerIndex = index;
};

/**
 * 修改
 */
CostSet.openCostSetEdit = function (id) {
	var index = layer.open({
        type: 2,
        title: '费项设置修改',
        area: ['800px', '300px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/costSet/costSet_update/' + id
    });
    this.layerIndex = index;
};


/**
 * 详情
 */
CostSet.openCostSetDetail = function (id) {
        var index = layer.open({
            type: 2,
            title: '费项设置详情',
            area: ['800px', '300px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/costSet/costSet_detail/' + id
        });
        this.layerIndex = index;
};


/**
 * 删除费项设置
 */
CostSet.delete = function (id) {
	
    	var operation = function(){
    		var ajax = new $ax(Feng.ctxPath + "/costSet/delete", function (data) {
                Feng.success(data.message);
                CostSet.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("costSetId",id);
            ajax.start();
    	};
    	Feng.confirm("是否删除费项设置 " + "?", operation);
};

/**
 * 查询费项设置列表
 */
CostSet.search = function () {
    var queryData = {};
    queryData['costTypeId'] = $("#costTypeId").val();
    CostSet.table.refresh({query: queryData});
};

CostSet.initCostType = function() {
	 $.ajax({
	        "type" : 'get',
	        "url": Feng.ctxPath + "/dict/getDic",
	        "dataType" : "json",
	        "data" : {"code" : "fxlb"},
	        "success" : function(data) {
	        	$("#costTypeId").append("<option value=''>请选择</option>");
	            for(var i = 0 ; i < data.length; i++ ){
	            	if($("#costType").val() == data[i].id){
	            		 $("#costTypeId").append("<option value='"+data[i].num+"' selected='selected'>"+data[i].name+"</option>");
	            	}else{
	            		$("#costTypeId").append("<option value='"+data[i].num+"'>"+data[i].name+"</option>");
	            	}
	                
	            }
	        }
	    });
}

$(function () {
    var defaultColunms = CostSet.initColumn();
    var table = new BSTable(CostSet.id, "/costSet/list", defaultColunms);
    table.setPaginationType("server");
    CostSet.table = table.init();
    CostSet.initCostType();
    
});
