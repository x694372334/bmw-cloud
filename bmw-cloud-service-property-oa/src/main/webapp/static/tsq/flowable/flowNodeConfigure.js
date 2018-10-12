/**
 * flowNodeConfigure管理初始化
 */
var FlowNodeConfigure = {
    id: "FlowNodeConfigureTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
FlowNodeConfigure.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '流程配置id', field: 'flowConfigureId', visible: false, align: 'center', valign: 'middle'},
            {title: '节点名称', field: 'nodeName', visible: true, align: 'center', valign: 'middle'},
            {title: '节点标识码', field: 'nodeCode', visible: true, align: 'center', valign: 'middle'},
            {title: '顺序', field: 'sort', visible: true, align: 'center', valign: 'middle'},
            {title: '是否为开始节点', field: 'isStart', visible: true, align: 'center', valign: 'middle',
            	formatter : function (value, row, index) {
                    if (value == 1) {
                        return '是';
                    }
                    if (value == 2) {
                        return '否';
                    }
                } },
            {title: '是否为结束节点', field: 'isEnd', visible: true, align: 'center', valign: 'middle',
                	formatter : function (value, row, index) {
                        if (value == 1) {
                            return '是';
                        }
                        if (value == 2) {
                            return '否';
                        }
                    } }
          /*  {title: '创建人id', field: 'createManId', visible: true, align: 'center', valign: 'middle'},
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
FlowNodeConfigure.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        FlowNodeConfigure.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加flowNodeConfigure
 */
FlowNodeConfigure.openAddFlowNodeConfigure = function () {
    var index = layer.open({
        type: 2,
        title: '添加flowNodeConfigure',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/flowNodeConfigure/flowNodeConfigure_add/' 
    });
    this.layerIndex = index;
};


/**
 * 点击添加flownodecondition
 */
FlowNodeConfigure.openAddFlowNodeCondition = function () {
	 if (this.check()) {
    var index = layer.open({
        type: 2,
        title: '添加流程节点条件',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/flowNodeCondition/flowNodeCondition_add/' + FlowNodeConfigure.seItem.id
    });
    this.layerIndex = index;
	 }
};



/**
 * 打开查看flowNodeConfigure详情
 */
FlowNodeConfigure.openFlowNodeConfigureDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改流程节点配置',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/flowNodeConfigure/flowNodeConfigure_update/' + FlowNodeConfigure.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除flowNodeConfigure
 */
FlowNodeConfigure.delete = function () {
    if (this.check()) {
    	var operation = function(){
        var ajax = new $ax(Feng.ctxPath + "/flowNodeConfigure/delete", function (data) {
            Feng.success("删除成功!");
            FlowNodeConfigure.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("flowNodeConfigureId",FlowNodeConfigure.seItem.id);
        ajax.start();
    	};
    	Feng.confirm("是否删除",operation);
    }
};

/**
 * 查询flowNodeConfigure列表
 */
FlowNodeConfigure.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    FlowNodeConfigure.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = FlowNodeConfigure.initColumn();
    var table = new BSTable(FlowNodeConfigure.id, "/flowNodeConfigure/list", defaultColunms);
    table.setPaginationType("client");
    FlowNodeConfigure.table = table.init();
    $("#nodediv").hide();
});
