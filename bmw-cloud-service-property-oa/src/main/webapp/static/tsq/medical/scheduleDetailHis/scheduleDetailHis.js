/**
 * 排班历史明细管理初始化
 */
var ScheduleDetailHis = {
    id: "ScheduleDetailHisTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ScheduleDetailHis.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
    ];
};

/**
 * 检查是否选中
 */
ScheduleDetailHis.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ScheduleDetailHis.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加排班历史明细
 */
ScheduleDetailHis.openAddScheduleDetailHis = function () {
    var index = layer.open({
        type: 2,
        title: '添加排班历史明细',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/scheduleDetailHis/scheduleDetailHis_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看排班历史明细详情
 */
ScheduleDetailHis.openScheduleDetailHisDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '排班历史明细详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/scheduleDetailHis/scheduleDetailHis_update/' + ScheduleDetailHis.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除排班历史明细
 */
ScheduleDetailHis.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/scheduleDetailHis/delete", function (data) {
            Feng.success("删除成功!");
            ScheduleDetailHis.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("scheduleDetailHisId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询排班历史明细列表
 */
ScheduleDetailHis.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ScheduleDetailHis.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ScheduleDetailHis.initColumn();
    var table = new BSTable(ScheduleDetailHis.id, "/scheduleDetailHis/list", defaultColunms);
    table.setPaginationType("client");
    ScheduleDetailHis.table = table.init();
});
