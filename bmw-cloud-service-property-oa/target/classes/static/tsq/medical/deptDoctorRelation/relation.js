var Relation = {
	id: "RelationTable",
	// 表格id
	seItem: null,
	// 选中的条目
	table: null,
	layerIndex: -1,
	deptCode: '',
	treeSelectNode: {}
};

Relation.initColumn = function() {
	var columns = [{
		field: 'selectItem',
		check: true
	},
	{
		title: '职称',
		field: 'medicalDoctorTitle',
		visible: true,
		align: 'center',
		valign: 'middle'
	},
	{
		title: '医生姓名',
		field: 'name',
		visible: true,
		align: 'center',
		valign: 'middle'
	},
	{
		title: '擅长',
		field: 'genius',
		visible: true,
		align: 'center',
		valign: 'middle'
	},
	{
		title: '简介',
		field: 'synopsis',
		visible: true,
		align: 'center',
		valign: 'middle'
	},
	{
		title: '职务',
		field: 'dutiesId',
		visible: true,
		align: 'center',
		valign: 'middle'
	}];
	return columns;
};

Relation.check = function() {
	var selected = $('#' + this.id).bootstrapTable('getSelections');
	if (selected.length == 0) {
		Feng.info("请先选中医生列表中的某一记录！");
		return false;
	} else {
		Relation.seItem = selected;
		return true;
	}
};

Relation.treeCheck = function() {

	if (JSON.stringify(Relation.treeSelectNode) == "{}") {
		Feng.info("请先选中左侧科室树列表中的某一记录！");
		return false;
	} else {
		return true;
	}
};

Relation.search = function() {
	var queryData = {};
	queryData['name'] = $("#name").val();
	queryData['deptCode'] = Relation.deptCode;
	Relation.table.refresh({
		query: queryData
	});
};

Relation.resetSearch = function() {
	$("#name").val("");
	Relation.search();
}

Relation.onClickDept = function(e, treeId, treeNode) {
	Relation.treeSelectNode = treeNode;
	var ext_attr = treeNode.ext_attr;
	if(ext_attr && ext_attr.split(",")[1]){
		Relation.deptCode = ext_attr.split(",")[1];
		Relation.search();
	}
};

$(function() {
	var defaultColunms = Relation.initColumn();
	var table = new BSTable(Relation.id, "/deptDoctorRelation/doctorList", defaultColunms);
	table.setPaginationType("client");
	Relation.table = table.init();

	// 初始化左侧树列表
	var ztree = new $ZTree("deptTree", "/deptDoctorRelation/getTreeList");
	ztree.bindOnClick(Relation.onClickDept);
	ztree.init();
});

Relation.addDoctor = function() {
	if (this.treeCheck()) {
		var index = layer.open({
			type: 2,
			title: '添加医生',
			area: ['800px', '600px'],
			fix: false,
			maxmin: true,
			content: Feng.ctxPath + '/deptDoctorRelation/doctor_add/' + Relation.deptCode
		});
		this.layerIndex = index;
	}
};

Relation.updateTitle = function() {
	if (this.check() && this.treeCheck()) {
		var ids = [];
		$(Relation.seItem).each(function() {
			ids.push(this.id);
		});
		var index = layer.open({
			type: 2,
			title: '科室管理详情',
			area: ['400px', '280px'],
			fix: false,
			maxmin: true,
			content: Feng.ctxPath + '/deptDoctorRelation/title/' + ids.join(",")
		});
		this.layerIndex = index;
	}
};

Relation.delDoctor = function() {
	if (this.check() && this.treeCheck()) {
		var ids = [];
		$(Relation.seItem).each(function() {
			ids.push(this.id);
		});

		var ajax = new $ax(Feng.ctxPath + "/deptDoctorRelation/delDoctor/",
			function(data) {
				Feng.success("删除成功!");
				Relation.table.refresh();
			},
			function(data) {
				Feng.error("删除失败!" + data.responseJSON.message + "!");
			}
		);
		ajax.set("Ids", ids.join(","));
		ajax.start();
	}
};