/**
 * flowconfigure管理初始化
 */
var FlowConfigure = {
    id: "FlowConfigureTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
FlowConfigure.initColumn = function () {
    return [
        {field: 'selectItem', radio: true },
            {title: '主键', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '流程名称', field: 'flowName', visible: true, align: 'center', valign: 'middle'},
            {title: '流程标识码', field: 'flowCode', visible: true, align: 'center', valign: 'middle'},
            {title: '是否默认流程', field: 'isDefaultFlow', visible: true, align: 'center', valign: 'middle',
            	formatter : function (value, row, index) {
                    if (value == 1) {
                        return '是';
                    }
                    if (value == 2) {
                        return '否';
                    }
                }   }
           /* {title: '创建人id', field: 'createManId', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createMan', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '修改人id', field: 'editManId', visible: true, align: 'center', valign: 'middle'},
            {title: '修改人', field: 'editMan', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'editTime', visible: true, align: 'center', valign: 'middle'},
            {title: '是否删除', field: 'isDelete', visible: true, align: 'center', valign: 'middle'}*/
    ];
};



/**
 * 检查是否选中
 */
FlowConfigure.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        FlowConfigure.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加flowconfigure
 */
FlowConfigure.openAddFlowConfigure = function () {
    var index = layer.open({
        type: 2,
        title: '添加流程配置',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/flowConfigure/flowConfigure_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看flowconfigure详情
 */
FlowConfigure.openFlowConfigureDetail = function () {
    if (FlowConfigure.check()) {
        var index = layer.open({
            type: 2,
            title: '修改流程配置',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/flowConfigure/flowConfigure_update/' + FlowConfigure.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 点击添加flowNodeConfigure
 */
FlowConfigure.openAddFlowNodeConfigure = function () {
	if (FlowConfigure.check()) {
    var index = layer.open({
        type: 2,
        title: '添加流程节点配置',
        area: ['800px', '420px'], // 宽高
        fix: false, // 不固定
        maxmin: true,
        content: Feng.ctxPath + '/flowNodeConfigure/flowNodeConfigure_add/' + FlowConfigure.seItem.id
    });
    this.layerIndex = index;
	}
};

/**
 * 删除flowconfigure
 */
FlowConfigure.delete = function () {
    if (FlowConfigure.check()) {
    	var operation = function(){
        var ajax = new $ax(Feng.ctxPath + "/flowConfigure/delete", function (data) {
            Feng.success("删除成功!");
            FlowConfigure.table.refresh();
            FlowNodeConfigure.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("flowConfigureId",FlowConfigure.seItem.id);
        ajax.start();
    	};
    	Feng.confirm("是否删除",operation);
    }
};

/**
 * 查询flowconfigure列表
 */
FlowConfigure.search = function () {
    var queryData = {};
    queryData['flowName'] = $("#flowName").val();
    FlowConfigure.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = FlowConfigure.initColumn();
    var table = new BSTable(FlowConfigure.id, "/flowConfigure/list", defaultColunms);
    table.setPaginationType("client");
    FlowConfigure.table = table.init();
    
    $('#FlowConfigureTable').on('check.bs.table', function (e, row, element) {
    	  $("#nodediv").show();
    	  var queryData = {};
    	  queryData['flowConfigureId'] = row.id;
    	  FlowNodeConfigure.table.refresh({query: queryData});
    });

    
});
