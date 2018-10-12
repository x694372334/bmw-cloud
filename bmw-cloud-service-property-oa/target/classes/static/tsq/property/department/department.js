/**
 * 管理初始化
 */
var department = {
    id: "departmentTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
department.initColumn = function () {
	var columns = [
        {field: 'selectItem', radio: true},
	        {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle' },
	        {title: '简称', field: 'shortName', visible: true, align: 'center', valign: 'middle' },
	        {title: '企业', field: 'eName', visible: true, align: 'center', valign: 'middle'},
	        /*{title: '排序', field: 'sort', visible: true, align: 'center', valign: 'middle'},
	        {title: '层级', field: 'level', visible: true, align: 'center', valign: 'middle'},
	        {title: '是否叶子结点', field: 'isLeaf', visible: true, align: 'center', valign: 'middle'},
	        {title: '结点个数', field: 'childrenCount', visible: true, align: 'center', valign: 'middle'},
	        {title: '创建人id', field: 'createManId', visible: true, align: 'center', valign: 'middle'},*/
	        {title: '创建人', field: 'createMan', visible: true, align: 'center', valign: 'middle'},
	        {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle',sortable: true, width: '10%'},
	        /*{title: '修改人id', field: 'editManId', visible: true, align: 'center', valign: 'middle'},*/
	        {title: '修改人', field: 'editMan', visible: true, align: 'center', valign: 'middle'},
	        {title: '修改时间', field: 'editTime', visible: true, align: 'center', valign: 'middle',sortable: true, width: '12%'}
	        ]
	  return columns;
};

/**
 * 检查是否选中
 */
department.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
    	department.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
department.openAddDepartment = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/propertyDepartment/department_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
department.openDepartmentDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/propertyDepartment/department_update/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
department.delete = function () {
    if (this.check()) {
    	
    	var operation = function () {
    		var ajax = new $ax(Feng.ctxPath + "/propertyDepartment/delete", function (data) {
    			if(400 == data.code){
            		Feng.info(data.message);
            	}else{
	            Feng.success("删除成功!");
	            department.table.refresh();
            	}
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("departmentId",department.seItem.id);
	        ajax.start();
        };

        Feng.confirm("是否刪除该菜单?", operation);
    }
};

/**
 * 查询列表
 */
department.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    department.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = department.initColumn();
    var table = new BSTreeTable(department.id, "/propertyDepartment/list", defaultColunms);
    table.setExpandColumn(1);
    table.setIdField("id");
    table.setCodeField("code");
    table.setParentCodeField("parentCode");
    table.setExpandAll(true);
    table.init();
    department.table = table;
});
