var Department = {
	id: "DepartmentTable",
	seItem: null,
	table: null,
	layerIndex: -1
};
Department.initColumn = function() {
	var columns = [{
		field: 'selectItem',
		radio: true
	},
	{
		title: '名称',
		field: 'name',
		visible: true,
		align: 'center',
		valign: 'middle'
	},
	{
		title: '编码',
		field: 'code',
		visible: true,
		align: 'center',
		valign: 'middle'
	},
	{
		title: '医院编码code',
		field: 'medicalHospitalCode',
		visible: true,
		align: 'center',
		valign: 'middle'
	},
	{
		title: '父节点code',
		field: 'parentCode',
		visible: true,
		align: 'center',
		valign: 'middle'
	},
	{
		title: '排序',
		field: 'sort',
		visible: true,
		align: 'center',
		valign: 'middle'
	},
	{
		title: '层级',
		field: 'level',
		visible: true,
		align: 'center',
		valign: 'middle'
	},
	{
		title: '是否叶子结点',
		field: 'isLeaf',
		visible: true,
		align: 'center',
		valign: 'middle'
	},
	{
		title: '结点个数',
		field: 'childrenCount',
		visible: true,
		align: 'center',
		valign: 'middle'
	},
	{
		title: '创建人id',
		field: 'createManId',
		visible: true,
		align: 'center',
		valign: 'middle'
	},
	{
		title: '创建人',
		field: 'createMan',
		visible: true,
		align: 'center',
		valign: 'middle'
	},
	{
		title: '创建时间',
		field: 'createTime',
		visible: true,
		align: 'center',
		valign: 'middle'
	},
	{
		title: '修改人id',
		field: 'editManId',
		visible: true,
		align: 'center',
		valign: 'middle'
	},
	{
		title: '修改人',
		field: 'editMan',
		visible: true,
		align: 'center',
		valign: 'middle'
	},
	{
		title: '修改时间',
		field: 'editTime',
		visible: true,
		align: 'center',
		valign: 'middle'
	}];
	return columns;
};
Department.check = function() {
	var selected = $('#' + this.id).bootstrapTable('getSelections');
	if (selected.length == 0) {
		Feng.info("请先选中表格中的某一记录！");
		return false;
	} else {
		Department.seItem =  selected[0];
		return true;
	}
};
Department.openAddDepartment = function() {
	var index = layer.open({
		type: 2,
		title: '添加科室管理',
		area: ['500px', '420px'],
		fix: false,
		maxmin: true,
		content: Feng.ctxPath + '/department/department_add'
	});
	this.layerIndex = index;
};
Department.openDepartmentDetail = function() {
	if (this.check()) {
		var index = layer.open({
			type: 2,
			title: '科室管理详情',
			area: ['500px', '420px'],
			fix: false,
			maxmin: true,
			content: Feng.ctxPath + '/department/department_update/' + Department.seItem.id
		});
		this.layerIndex = index;
	}
};
Department.delete = function() {
	if (this.check()) {
		var operation = function() {
			var ajax = new $ax(Feng.ctxPath + "/department/delete",
				function(data) {
					Feng.success("删除成功!");
					Department.table.refresh();
				},
				function(data) {
					Feng.error("删除失败!" + data.responseJSON.message + "!");
				}
			);
			ajax.set("departmentId", Department.seItem.id);
			ajax.start()
		};
		Feng.confirm("是否刪除该科室?", operation);
	}
};
Department.search = function() {
	var queryData = {};
	queryData['name'] = $("#name").val();
	Department.table.refresh({
		query: queryData
	})
};
Department.resetSearch = function() {
	$("#name").val("");
	Department.search();
}
$(function() {
	var defaultColunms = Department.initColumn();
	 var table = new BSTreeTable(Department.id, "/department/list", defaultColunms);
    table.setIdField("id");
    table.setCodeField("code");
    table.setParentCodeField("parentCode");
    table.setExpandAll(true);
	Department.table = table.init();
});