/**
 * 排班历史管理初始化
 */
var ScheduleHis = {
    id: "ScheduleHisTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ScheduleHis.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '排班日期', field: 'scheduleDate', visible: true, align: 'center', valign: 'middle'},
        {title: '显示方式', field: 'approach', visible: true, align: 'center', valign: 'middle'},
        {title: '医生姓名', field: 'doctorName', visible: true, align: 'center', valign: 'middle'},
        {title: '医生编码', field: 'medicalDoctorCode', visible: true, align: 'center', valign: 'middle'},
        {title: '预约人数', field: 'appointmentNum', visible: true, align: 'center', valign: 'middle'},
//        {title: '操作', field: '', visible: true, align: 'center', valign: 'middle' ,
//        	formatter:function(value,row,index) {
//        	console.log(row.id);
//        
//        	return [
//        	'<button class="btn btn-primary" id="cancel"  clickFun="ScheduleHisInfoDlg.openScheduleHisDetail()"><i class="fa fa-eraser"></i>明细</button>'
//        	]}}
    ];
};

/**
 * 检查是否选中
 */
ScheduleHis.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ScheduleHis.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加排班历史
 */
ScheduleHis.openAddScheduleHis = function () {
    var index = layer.open({
        type: 2,
        title: '添加排班历史',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/scheduleHis/scheduleHis_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看排班历史详情
 */
ScheduleHis.openScheduleHisDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '排班历史详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/scheduleHis/scheduleHis_update/' + ScheduleHis.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除排班历史
 */
ScheduleHis.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/scheduleHis/delete", function (data) {
            Feng.success("删除成功!");
            ScheduleHis.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("scheduleHisId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询排班历史列表
 */
ScheduleHis.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ScheduleHis.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ScheduleHis.initColumn();
    var table = new BSTable(ScheduleHis.id, "/scheduleHis/list", defaultColunms);
    table.setPaginationType("client");
    ScheduleHis.table = table.init();
});
