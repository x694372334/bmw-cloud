/**
 * 车辆管理管理初始化
 */
var IVehicleInfo = {
    id: "IVehicleInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
IVehicleInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '小区', field: 'nName', visible: true, align: 'center', valign: 'middle'},
        {title: '住户姓名', field: 'iName', visible: true, align: 'center', valign: 'middle'},
        {title: '车辆品牌', field: 'vTrademark', visible: true, align: 'center', valign: 'middle'},
        {title: '车辆类型', field: 'vType', visible: true, align: 'center', valign: 'middle'},
        {title: '车牌号码', field: 'vNumber', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
IVehicleInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        IVehicleInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加车辆管理
 */
IVehicleInfo.openAddIVehicleInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加车辆管理',
        area: ['800px', '400px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/iVehicleInfo/iVehicleInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看车辆管理详情
 */
IVehicleInfo.openIVehicleInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '车辆管理详情',
            area: ['800px', '400px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/iVehicleInfo/iVehicleInfo_update/' + IVehicleInfo.seItem.vId
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看车辆管理详情
 */
IVehicleInfo.openIVehicleInfoDetail2 = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '车辆管理详情',
            area: ['800px', '400px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/iVehicleInfo/iVehicleInfo_detail/' + IVehicleInfo.seItem.vId
        });
        this.layerIndex = index;
    }
};

/**
 * 删除车辆管理
 */
IVehicleInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/iVehicleInfo/delete", function (data) {
            Feng.success("删除成功!");
            IVehicleInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("iVehicleInfoId",this.seItem.vId);
        ajax.start();
    }
};

/**
 * 查询车辆管理列表
 */
IVehicleInfo.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    queryData['nName'] = $("#nName").val();
    queryData['iName'] = $("#iName").val();
    IVehicleInfo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = IVehicleInfo.initColumn();
    var table = new BSTable(IVehicleInfo.id, "/iVehicleInfo/list", defaultColunms);
    table.setPaginationType("client");
    IVehicleInfo.table = table.init();
});
