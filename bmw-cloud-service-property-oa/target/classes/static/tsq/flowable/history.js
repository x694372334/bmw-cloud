/**
 * rutask管理初始化
 */
var RuTask = {
    id: "historyTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    type: "view"
};

/**
 * 初始化表格的列
 */
RuTask.initColumn = function () {
    return [
    	 {field: 'selectItem', radio: false, checkbox: false},
    	 {title: '历史任务节点', field: 'name', visible: true, align: 'center', valign: 'middle'},
         {title: '历史经办人', field: 'assignee', visible: true, align: 'center', valign: 'middle'}
    ];
};



$(function () {
    var defaultColunms = RuTask.initColumn();
    var table = new BSTable(RuTask.id,"/hiTask/history/"+$("#pid").val(), defaultColunms);
    table.setPaginationType("client");
    RuTask.table = table.init();
});
