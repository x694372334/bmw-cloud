/**
 * rutask管理初始化
 */
var RuTask = {
    id: "RuTaskTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
RuTask.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '任务ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '流程名称', field: 'deploymentName', visible: true, align: 'center', valign: 'middle'},
            {title: '流程描述', field: 'descr', visible: true, align: 'center', valign: 'middle'},
            {title: '发起人', field: 'userName', visible: true, align: 'center', valign: 'middle'},
            {title: '业务类型', field: 'businessTypeName', visible: true, align: 'center', valign: 'middle'},
            {title: '流程实例ID', field: 'procInstId', visible: true, align: 'center', valign: 'middle'},
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
 * 检查是否选中
 */
RuTask.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        RuTask.seItem = selected[0];
        return true;
    }
};


/**
 * 删除流程实例
 */
RuTask.deletePro = function () {
	 if (this.check()) {
	    	var operation = function(){
	        var ajax = new $ax(Feng.ctxPath + "/ruTask/deletePro/"+ RuTask.seItem.procInstId, function (data) {
	            Feng.success("删除成功!");
	            RuTask.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.start();
	    	};
	    	  Feng.confirm("是否删除",operation);
	    };
};



/**
 * 打开查看rutask详情
 */
RuTask.handle = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '办理',
            area: ['1400px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + RuTask.seItem.formKey +'/'+ RuTask.seItem.id
        });
        this.layerIndex = index;
    }
};


/**
 * 查看流程图
 */
RuTask.view = function () {
	if (this.check()) {
	window.open(Feng.ctxPath + "ruTask/printProcessImage/"+ RuTask.seItem.id,'_blank','width=1300,height=800,top=100px,left=300px')
	}
};

/**
 * 查询rutask列表
 */
RuTask.search = function () {
    var queryData = {};
    queryData['name'] = $("#name").val();
    RuTask.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = RuTask.initColumn();
    var table = new BSTable(RuTask.id, "/ruTask/list", defaultColunms);
    table.setPaginationType("client");
    RuTask.table = table.init();
});
