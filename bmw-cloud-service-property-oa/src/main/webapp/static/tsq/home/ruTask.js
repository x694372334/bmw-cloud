/**
 * HomeRuTask管理初始化
 */
var HomeRuTask = {
	id : "HomeRuTaskTable", //表格id
	seItem : null, //选中的条目
	table : null,
	layerIndex : -1
};

/**
 * 初始化表格的列
 */
HomeRuTask.initColumn = function() {
	return [
		 {field: 'selectItem', radio: true},
         {title: '任务ID', field: 'id', visible: false, align: 'center', valign: 'middle'},
         {title: '流程名称', field: 'deploymentName', visible: true, align: 'center', valign: 'middle'},
         {title: '发起人', field: 'userName', visible: true, align: 'center', valign: 'middle'},
         {title: '业务类型', field: 'businessTypeName', visible: true, align: 'center', valign: 'middle'},
      /*   {title: '', field: 'rev', visible: true, align: 'center', valign: 'middle'},
         {title: '', field: 'executionId', visible: true, align: 'center', valign: 'middle'},
         {title: '', field: 'procInstId', visible: true, align: 'center', valign: 'middle'},
         {title: '', field: 'procDefId', visible: true, align: 'center', valign: 'middle'},
         {title: '', field: 'scopeId', visible: true, align: 'center', valign: 'middle'},
         {title: '', field: 'subScopeId', visible: true, align: 'center', valign: 'middle'},
         {title: '', field: 'scopeType', visible: true, align: 'center', valign: 'middle'},
         {title: '', field: 'scopeDefinitionId', visible: true, align: 'center', valign: 'middle'},*/
         {title: '任务名称', field: 'name', visible: true, align: 'center', valign: 'middle'
        	/* ,formatter : function(value, row, index) {
					return "<a href='javascript:void(0);' onclick='HomeRuTask.handle("+row.id+")'>"
							+ value + "</a>";
					return value;
				}*/
         },
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
         {title: 'formKey', field: 'formKey', visible: false, align: 'center', valign: 'middle'},
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

/**
 * 打开查看rutask详情
 */
HomeRuTask.handle = function(id) {
	var index = layer.open({
		type : 2,
		title : '办理',
		area : [ '1400px', '600px' ], //宽高
		fix : false, //不固定
		maxmin : true,
		content : Feng.ctxPath + "" + '/'
				+ id
	});
	this.layerIndex = index;
};

$(function() {
	var defaultColunms = HomeRuTask.initColumn();
	var table = new BSTable(HomeRuTask.id, "/ruTask/list", defaultColunms);
	table.setPaginationType("client");
	HomeRuTask.table = table.init();
});
