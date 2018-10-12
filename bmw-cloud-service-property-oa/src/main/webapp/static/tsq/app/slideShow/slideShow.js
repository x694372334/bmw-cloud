/**
 * 轮播图片管理管理初始化
 */
var SlideShow = {
    id: "SlideShowTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
SlideShow.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '地址', field: 'url', visible: true, align: 'center', valign: 'middle'},
            {title: '排序', field: 'sort', visible: true, align: 'center', valign: 'middle'},
            {title: '是否启用', field: 'isOpen', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
SlideShow.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        SlideShow.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加轮播图片管理
 */
SlideShow.openAddSlideShow = function () {
    var index = layer.open({
        type: 2,
        title: '添加轮播图片管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/slideShow/slideShow_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看轮播图片管理详情
 */
SlideShow.openSlideShowDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '轮播图片管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/slideShow/slideShow_update/' + SlideShow.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除轮播图片管理
 */
SlideShow.delete = function () {
    if (this.check()) {
    	var operation = function(){
        var ajax = new $ax(Feng.ctxPath + "/slideShow/delete", function (data) {
            Feng.success("删除成功!");
            SlideShow.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("slideShowId",SlideShow.seItem.id);
        ajax.start();
    	};
    	 Feng.confirm("是否删除",operation);
    }
};

/**
 * 查询轮播图片管理列表
 */
SlideShow.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    SlideShow.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = SlideShow.initColumn();
    var table = new BSTable(SlideShow.id, "/slideShow/list", defaultColunms);
    table.setPaginationType("client");
    SlideShow.table = table.init();
});
