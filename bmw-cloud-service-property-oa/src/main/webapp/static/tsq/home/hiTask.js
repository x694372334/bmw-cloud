/**
 * rutask管理初始化
 */
var HomeHiTask = {
    id: "HomeHiTaskTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    type: "view"
};

/**
 * 初始化表格的列
 */
HomeHiTask.initColumn = function () {
    return [
    	{field: 'selectItem', radio: true},
        {title: '任务ID', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '流程名称', field: 'deploymentName', visible: true, align: 'center', valign: 'middle'},
        {title: '发起人', field: 'userName', visible: true, align: 'center', valign: 'middle'},
        {title: '业务类型', field: 'businessTypeName', visible: true, align: 'center', valign: 'middle'},
        {title: '执行实例ID', field: 'procInstId', visible: true, align: 'center', valign: 'middle'},
     /*   {title: '', field: 'rev', visible: true, align: 'center', valign: 'middle'},
        {title: '', field: 'executionId', visible: true, align: 'center', valign: 'middle'},
        {title: '', field: 'procInstId', visible: true, align: 'center', valign: 'middle'},
        {title: '', field: 'procDefId', visible: true, align: 'center', valign: 'middle'},
        {title: '', field: 'scopeId', visible: true, align: 'center', valign: 'middle'},
        {title: '', field: 'subScopeId', visible: true, align: 'center', valign: 'middle'},
        {title: '', field: 'scopeType', visible: true, align: 'center', valign: 'middle'},
        {title: '', field: 'scopeDefinitionId', visible: true, align: 'center', valign: 'middle'},*/
        {title: '任务名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
       /* {title: '', field: 'parentTaskId', visible: true, align: 'center', valign: 'middle'},
        {title: '', field: 'description', visible: true, align: 'center', valign: 'middle'},
        {title: '', field: 'taskDefKey', visible: true, align: 'center', valign: 'middle'},
        {title: '', field: 'owner', visible: true, align: 'center', valign: 'middle'},
        {title: '', field: 'assignee', visible: true, align: 'center', valign: 'middle'},
        {title: '', field: 'delegation', visible: true, align: 'center', valign: 'middle'},
        {title: '', field: 'priority', visible: true, align: 'center', valign: 'middle'},*/
        {title: '业务编码', field: 'businessKey', visible: false, align: 'center', valign: 'middle'},
        {title: 'url', field: 'url', visible: false, align: 'center', valign: 'middle'},
        {title: '业务ID', field: 'businessId', visible: false, align: 'center', valign: 'middle'},
        {title: '', field: 'formKey', visible: false, align: 'center', valign: 'middle'},
        {title: '发起时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'}
        /* {title: '', field: 'dueDate', visible: true, align: 'center', valign: 'middle'},
        {title: '', field: 'category', visible: true, align: 'center', valign: 'middle'},
        {title: '', field: 'suspensionState', visible: true, align: 'center', valign: 'middle'},
        {title: '', field: 'tenantId', visible: true, align: 'center', valign: 'middle'},
        {title: '', field: 'formKey', visible: true, align: 'center', valign: 'middle'},
        {title: '', field: 'claimTime', visible: true, align: 'center', valign: 'middle'},
        {title: '', field: 'isCountEnabled', visible: true, align: 'center', valign: 'middle'},
        {title: '', field: 'varCount', visible: true, align: 'center', valign: 'middle'},
        {title: '', field: 'idLinkCount', visible: true, align: 'center', valign: 'middle'}*/
    ];
};

$(function () {
    var defaultColunms = HomeHiTask.initColumn();
    var table = new BSTable(HomeHiTask.id, "/hiTask/list", defaultColunms);
    table.setPaginationType("client");
    HomeHiTask.table = table.init();
});
