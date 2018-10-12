/**
 * recommend管理初始化
 */
var Recommend = {
    id: "RecommendTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Recommend.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '是否主页显示 ', field: 'isshow', visible: true, align: 'center', valign: 'middle'},
            {title: '地址', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: '文字简介', field: 'content', visible: true, align: 'center', valign: 'middle'},
            {title: '平米', field: 'metre', visible: true, align: 'center', valign: 'middle'},
            {title: '图片路径', field: 'url', visible: true, align: 'center', valign: 'middle'},
            {title: '背景色', field: 'backcolor', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createtime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Recommend.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Recommend.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加recommend
 */
Recommend.openAddRecommend = function () {
    var index = layer.open({
        type: 2,
        title: '添加recommend',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/recommend/recommend_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看recommend详情
 */
Recommend.openRecommendDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: 'recommend详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/recommend/recommend_update/' + Recommend.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除recommend
 */
Recommend.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/recommend/delete", function (data) {
            Feng.success("删除成功!");
            Recommend.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("recommendId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询recommend列表
 */
Recommend.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Recommend.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Recommend.initColumn();
    var table = new BSTable(Recommend.id, "/recommend/list", defaultColunms);
    table.setPaginationType("client");
    Recommend.table = table.init();
});
