/**
 * 管理初始化
 */
var hospital = {
    id: "hospitalTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
hospital.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '医院名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '医院级别', field: 'level', visible: true, align: 'center', valign: 'middle'},
        {title: '简介', field: 'description', visible: true, align: 'center', valign: 'middle'},
        {title: '电话', field: 'tel', visible: true, align: 'center', valign: 'middle'},
        {title: '地址', field: 'address', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
hospital.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        hospital.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
hospital.openAddhospital = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/hospital/hospital_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
hospital.openhospitalDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/hospital/hospital_update/' + hospital.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
hospital.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/hospital/delete", function (data) {
            Feng.success("删除成功!");
            hospital.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("hospitalId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
hospital.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    hospital.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = hospital.initColumn();
    var table = new BSTable(hospital.id, "/hospital/list", defaultColunms);
    table.setPaginationType("client");
    hospital.table = table.init();
});
