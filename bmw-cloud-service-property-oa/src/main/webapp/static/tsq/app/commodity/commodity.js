/**
 * 商品维护管理初始化
 */
var Commodity = {
    id: "CommodityTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Commodity.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '商品名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '规格', field: 'specification', visible: true, align: 'center', valign: 'middle'},
            {title: '单位', field: 'unit', visible: true, align: 'center', valign: 'middle'},
            {title: '价格', field: 'price', visible: true, align: 'center', valign: 'middle'},
            {title: '类别', field: 'category', visible: true, align: 'center', valign: 'middle'},
            {title: '是否推荐', field: 'isRecommend', visible: true, align: 'center', valign: 'middle'},
            {title: '背景色', field: 'backGroundUrl', visible: true, align: 'center', valign: 'middle'},
            {title: '图片', field: 'commodityUrl', visible: true, align: 'center', valign: 'middle'},
            {title: '是否发生改变', field: 'isChange', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Commodity.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Commodity.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加商品维护
 */
Commodity.openAddCommodity = function () {
    var index = layer.open({
        type: 2,
        title: '添加商品维护',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/commodity/commodity_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看商品维护详情
 */
Commodity.openCommodityDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '商品维护详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/commodity/commodity_update/' + Commodity.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除商品维护
 */
Commodity.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/commodity/delete", function (data) {
            Feng.success("删除成功!");
            Commodity.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("commodityId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询商品维护列表
 */
Commodity.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Commodity.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Commodity.initColumn();
    var table = new BSTable(Commodity.id, "/commodity/list", defaultColunms);
    table.setPaginationType("client");
    Commodity.table = table.init();
});
