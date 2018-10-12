/**
 * 医生管理初始化
 */
var Doctor = {
    id: "DoctorTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Doctor.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '医生姓名', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '职务', field: 'dutiesId', visible: true, align: 'center', valign: 'middle'},
        {title: '擅长', field: 'genius', visible: true, align: 'center', valign: 'middle'},
        {title: '简介', field: 'synopsis', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Doctor.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Doctor.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加医生
 */
Doctor.openAddDoctor = function () {
    var index = layer.open({
        type: 2,
        title: '添加医生',
        area: ['800px', '580px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/doctor/doctor_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看医生详情
 */
Doctor.openDoctorDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '医生详情',
            area: ['800px', '580px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/doctor/doctor_update/' + Doctor.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除医生
 */
Doctor.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/doctor/delete", function (data) {
            Feng.success("删除成功!");
            Doctor.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("doctorId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询医生列表
 */
Doctor.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Doctor.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Doctor.initColumn();
    var table = new BSTable(Doctor.id, "/doctor/list", defaultColunms);
    table.setPaginationType("client");
    Doctor.table = table.init();
});
