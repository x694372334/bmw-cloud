/**
 * worktast管理初始化
 */
var Task = {
    id: "TaskTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Task.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            /*{title: '任务id', field: 'tId', visible: true, align: 'center', valign: 'middle'},*/
            {title: '任务名称', field: 'tName', visible: true, align: 'center', valign: 'middle'},
            /*{title: '任务描述', field: 'tDescribe', visible: true, align: 'center', valign: 'middle'},*/
            {title: '主负责人', field: 'tPrincipalView', visible: true, align: 'center', valign: 'middle'},
           {title: '参与人', field: 'tParticipationPerView', visible: true, align: 'center', valign: 'middle'},
           /*  {title: '完成时间', field: 'tCTime', visible: true, align: 'center', valign: 'middle'},*/
           /* {title: '任务总结', field: 'tSummarize', visible: true, align: 'center', valign: 'middle'},
            {title: '任务状态', field: 'tStatus', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人id', field: 'createManId', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createMan', visible: true, align: 'center', valign: 'middle'},*/
            {title: '创建人id', field: 'createManId', visible: false, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createMan', visible: true, align: 'center', valign: 'middle'},
            {title: '发布时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '任务状态', field: 'tStatus', visible: true, align: 'center', valign: 'middle',
            	formatter : function (value, row, index) {
                    if (value === 1) {
                        return '待分派';
                    }
                    if (value === 2) {
                        return '待完成';
                    }
                    if (value === 3) {
                        return '已完成';
                    }
                } }
            /*{title: '修改人id', field: 'editManId', visible: true, align: 'center', valign: 'middle'},
            {title: '修改人', field: 'editMan', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'editTime', visible: true, align: 'center', valign: 'middle'},
            {title: '是否删除1未删除2已删除', field: 'isDelete', visible: true, align: 'center', valign: 'middle'}*/
    ];
};

/**
 * 检查是否选中
 */
Task.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Task.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加worktast
 */
Task.openAddTask = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '660px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/task/task_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看worktast详情
 */
Task.openTaskDetail = function () {
    if (this.check()) {
    	if($("#uId").val()!=Task.seItem.createManId){
    		Feng.info("非创建人不可删除!");
    		return;
    	}
        var index = layer.open({
            type: 2,
            title: '修改',
            area: ['800px', '660px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/task/task_update/' + Task.seItem.tId
        });
        this.layerIndex = index;
    }
};


/**
 * 打开查看页面
 */
Task.openTaskView = function () {
    if (this.check()) {
    	if("1"==Task.seItem.tStatus){
   		 Feng.info("待分派无法完成!");
   		 return;
   	}
        var index = layer.open({
            type: 2,
            title: '查看并执行',
            area: ['800px', '660px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/task/task_view/' + Task.seItem.tId
        });
        this.layerIndex = index;
    }
};

/**
 * 删除worktast
 */
Task.delete = function () {
    if (this.check()) {
    	if($("#uId").val()!=Task.seItem.createManId){
    		Feng.info("非创建人不可删除!");
    		return;
    	}
    	
    	var operation = function(){
        var ajax = new $ax(Feng.ctxPath + "/task/delete", function (data) {
            Feng.success("删除成功!");
            Task.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("taskId",Task.seItem.tId);
        ajax.start();
    	};
    	Feng.confirm("是否删除",operation);
    }
};

/**
 * 查询worktast列表
 */
Task.search = function () {
    var queryData = {};
    queryData['tName'] = $("#tName").val();
    queryData['tStatus'] = $("#tStatus").val();
    queryData['createTimeStart'] = $("#createTimeStart").val();
    queryData['createTimeEnd'] = $("#createTimeEnd").val();
    Task.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Task.initColumn();
    var table = new BSTable(Task.id, "/task/list", defaultColunms);
    table.setPaginationType("client");
    Task.table = table.init();
});
