/**
 * community管理初始化
 */
var Community = {
    id: "CommunityTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Community.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '1', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '2', field: 'url', visible: true, align: 'center', valign: 'middle'},
            {title: '3', field: 'isshow', visible: true, align: 'center', valign: 'middle'},
            {title: '4', field: 'createtime', visible: true, align: 'center', valign: 'middle'},
            {title: '序号', field: 'num', visible: true, align: 'center', valign: 'middle'},
            {title: '5', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: '6', field: 'content', visible: true, align: 'center', valign: 'middle'},
            {title: '7', field: 'metre', visible: true, align: 'center', valign: 'middle'},
            {title: '8', field: 'backcolor', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Community.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Community.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加community
 */
Community.openAddCommunity = function () {
    var index = layer.open({
        type: 2,
        title: '添加community',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/community/community_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看community详情
 */
Community.openCommunityDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: 'community详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/community/community_update/' + Community.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除community
 */
Community.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/community/delete", function (data) {
            Feng.success("删除成功!");
            Community.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("communityId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询community列表
 */
Community.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Community.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Community.initColumn();
    var table = new BSTable(Community.id, "/community/list", defaultColunms);
    table.setPaginationType("client");
    Community.table = table.init();
});
