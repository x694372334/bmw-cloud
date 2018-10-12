/**
 * 广告位分包信息管理初始化
 */
var ACInfo = {
    id: "ACInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ACInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
    ];
};

/**
 * 检查是否选中
 */
ACInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ACInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加广告位分包信息
 */
ACInfo.openAddACInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加广告位分包信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/aCInfo/aCInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看广告位分包信息详情
 */
ACInfo.openACInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '广告位分包信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/aCInfo/aCInfo_update/' + ACInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除广告位分包信息
 */
ACInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/aCInfo/delete", function (data) {
            Feng.success("删除成功!");
            ACInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("aCInfoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询广告位分包信息列表
 */
ACInfo.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ACInfo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ACInfo.initColumn();
    var table = new BSTable(ACInfo.id, "/aCInfo/list", defaultColunms);
    table.setPaginationType("client");
    ACInfo.table = table.init();
});
