/**
 * 管理初始化
 */
var BirthdayMsgModel = {
    id: "BirthdayMsgModelTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BirthdayMsgModel.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '模板编号', field: 'modelNo', visible: true, align: 'center', valign: 'middle'},
            {title: '内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
BirthdayMsgModel.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BirthdayMsgModel.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
BirthdayMsgModel.openAddBirthdayMsgModel = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/birthdayMsgModel/birthdayMsgModel_add'
    });
    this.layerIndex = index;
};
/**
 * 点击添加
 */
BirthdayMsgModel.openMsgSetting = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/birthdayMsgModel/birthdayMsgSetting_add'
    });
    this.layerIndex = index;
};


/**
 * 打开查看详情
 */
BirthdayMsgModel.openBirthdayMsgModelDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/birthdayMsgModel/birthdayMsgModel_update/' + BirthdayMsgModel.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
BirthdayMsgModel.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/birthdayMsgModel/delete", function (data) {
        	if(data.code==200){
        		Feng.success("删除成功!");
        		BirthdayMsgModel.table.refresh();
        	}else{
        		Feng.error(data.message);
        	}                      
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("birthdayMsgModelId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
BirthdayMsgModel.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BirthdayMsgModel.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BirthdayMsgModel.initColumn();
    var table = new BSTable(BirthdayMsgModel.id, "/birthdayMsgModel/list", defaultColunms);
    table.setPaginationType("server");
    BirthdayMsgModel.table = table.init();
});
