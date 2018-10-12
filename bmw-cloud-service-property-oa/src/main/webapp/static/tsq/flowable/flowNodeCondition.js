/**
 * flownodecondition管理初始化
 */
var FlowNodeCondition = {
    id: "FlowNodeConditionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
FlowNodeCondition.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '流程节点id', field: 'flowNodeConfigureId', visible: true, align: 'center', valign: 'middle'},
            /*{title: '0：角色
            1：部门
            2：语句', field: 'conditionType', visible: true, align: 'center', valign: 'middle'},
*/            {title: '条件值', field: 'conditionValue', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人id', field: 'createManId', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createMan', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '修改人id', field: 'editManId', visible: true, align: 'center', valign: 'middle'},
            {title: '修改人', field: 'editMan', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'editTime', visible: true, align: 'center', valign: 'middle'},
            {title: '是否删除', field: 'isDelete', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
FlowNodeCondition.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        FlowNodeCondition.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加flownodecondition
 */
FlowNodeCondition.openAddFlowNodeCondition = function () {
    var index = layer.open({
        type: 2,
        title: '添加flownodecondition',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/flowNodeCondition/flowNodeCondition_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看flownodecondition详情
 */
FlowNodeCondition.openFlowNodeConditionDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: 'flownodecondition详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/flowNodeCondition/flowNodeCondition_update/' + FlowNodeCondition.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除flownodecondition
 */
FlowNodeCondition.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/flowNodeCondition/delete", function (data) {
            Feng.success("删除成功!");
            FlowNodeCondition.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("flowNodeConditionId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询flownodecondition列表
 */
FlowNodeCondition.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    FlowNodeCondition.table.refresh({query: queryData});
};

$(function () {
    /*var defaultColunms = FlowNodeCondition.initColumn();
    var table = new BSTable(FlowNodeCondition.id, "/flowNodeCondition/list", defaultColunms);
    table.setPaginationType("client");
    FlowNodeCondition.table = table.init();*/
});
