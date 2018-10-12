/**
 * 管理初始化
 */
var BirthdayMsgHis = {
    id: "BirthdayMsgHisTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BirthdayMsgHis.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '业主姓名', field: 'ownerName', visible: true, align: 'center', valign: 'middle'},
            {title: '发送内容', field: 'sendText', visible: true, align: 'center', valign: 'middle'},
            {title: '发送时间', field: 'sendTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BirthdayMsgHis.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BirthdayMsgHis.seItem = selected[0];
        return true;
    }
};

/**
 * 打开导出条件选项
 */
BirthdayMsgHis.openExportExcel = function () {

        var index = layer.open({
            type: 2,
            title: '导出条件',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/birthdayMsgModel/birthdayMsgHis_Export'
        });
        this.layerIndex = index;
    
};
 
/**
 * 关闭此对话框
 */
BirthdayMsgHis.close = function() {
    parent.layer.close(window.parent.BirthdayMsgHis.layerIndex);
}

/**
 * 查询列表
 */
BirthdayMsgHis.search = function () {
    var queryData = {
    		"ownerName":$("#ownerName").val()
    };
    BirthdayMsgHis.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BirthdayMsgHis.initColumn();
    var table = new BSTable(BirthdayMsgHis.id, "/birthdayMsgModel/hislist", defaultColunms);
    table.setPaginationType("server");
    BirthdayMsgHis.table = table.init();
});
