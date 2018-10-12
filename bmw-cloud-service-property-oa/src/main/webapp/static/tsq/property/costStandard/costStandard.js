/**
 * 收费标准管理初始化
 */
var CostStandard = {
    id: "CostStandardTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CostStandard.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '费项名称', field: 'costName', visible: true, align: 'center', valign: 'middle'},
            {title: '收费标准名称', field: 'standardName', visible: true, align: 'center', valign: 'middle'},
            {title: '计算公式', field: 'amountFormulaName', visible: true, align: 'center', valign: 'middle'},
            {title: '计费单价', field: 'price', visible: true, align: 'center', valign: 'middle'},
            {title: '计量方式', field: 'meteringName', visible: true, align: 'center', valign: 'middle'},
            {title: '收费周期(月)', field: 'period', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'isDelete', visible: true, align: 'center', valign: 'middle',
            	formatter : function (value, row, index) {
            		if(value == 1){
            			return "未作废";
            		}else{
            			return '已作废';
            		}
                }
            },
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
        '<button id="btn_detail" type="button" onclick="CostStandard.openCostStandardDetail('+row.id+')" class="btn button-margin btn-primary fa">详情</button>', 
       /* '<button id="btn_detail" type="button" onclick="CostStandard.openCostStandardEdit('+row.id+')" class="btn button-margin btn-primary fa fa-edit">修改</button>',*/
        '<button id="btn_detail" type="button" onclick="CostStandard.delete('+row.id+')" class="btn btn-primary button-margin fa fa-remove">作废</button>'
    ].join('');  
}

/**
 * 检查是否选中
 */
CostStandard.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CostStandard.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加收费标准
 */
CostStandard.openAddCostStandard = function () {
    var index = layer.open({
        type: 2,
        title: '添加收费标准',
        area: ['1000px', '615px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/costStandard/costStandard_add'
    });
    this.layerIndex = index;
};

/**
 * 修改
 */
CostStandard.openCostStandardEdit = function (id) {
        var index = layer.open({
            type: 2,
            title: '收费标准修改',
            area: ['1000px', '615px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/costStandard/costStandard_update/' + id
        });
        this.layerIndex = index;
};


/**
 * 详情
 */
CostStandard.openCostStandardDetail = function (id) {
        var index = layer.open({
            type: 2,
            title: '收费标准详情',
            area: ['1000px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/costStandard/costStandard_detail/' + id
        });
        this.layerIndex = index;
};

/**
 * 删除收费标准
 */
CostStandard.delete = function (id) {
    	var operation = function(){
    		var ajax = new $ax(Feng.ctxPath + "/costStandard/delete", function (data) {
                Feng.success(data.message);
                CostStandard.table.refresh();
            }, function (data) {
                Feng.error("作废失败!" + data.responseJSON.message + "!");
            });
            ajax.set("costStandardId",id);
            ajax.start();
    	};
    	Feng.confirm("确定作废费项标准 " + "?", operation);
};

/**
 * 查询收费标准列表
 */
CostStandard.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    queryData['isDelete'] = $("#isDelete").val();
    CostStandard.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CostStandard.initColumn();
    var table = new BSTable(CostStandard.id, "/costStandard/list", defaultColunms);
    table.setPaginationType("server");
    CostStandard.table = table.init();
});
