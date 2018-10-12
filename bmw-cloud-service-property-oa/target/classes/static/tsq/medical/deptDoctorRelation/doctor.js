var Doctor = {
	id: "DoctorTable",
	seItem: null,
	table: null,
	layerIndex: -1
};

Doctor.initColumn = function() {
	var columns = [{
		field: 'selectItem',
		check: true
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

Doctor.check = function() {
	var selected = $('#' + this.id).bootstrapTable('getSelections');
	if (selected.length == 0) {
		Feng.info("请先选中表格中的某一记录！");
		return false;
	} else {
		Doctor.seItem = selected;
		return true;
	}
};

Doctor.OKclick = function() {
	if (this.check()) {
		var doctorCode = [];
		$(Doctor.seItem).each(function() {
			doctorCode.push(this.code);
		});
		var data = {
			deptCode: $("#deptCode").val(),
			doctorCode: doctorCode.join(",")
		};

		var ajax = new $ax(Feng.ctxPath + "/deptDoctorRelation/addDoctor",
			function(data) {
				Feng.success("添加成功!");
				window.parent.Relation.table.refresh();
				parent.layer.close(window.parent.Relation.layerIndex);
			},
			function(data) {
				Feng.error("添加失败!" + data.responseJSON.message + "!");
			}
		);
		ajax.set("deptCode", $("#deptCode").val());
		ajax.set("doctorCode", doctorCode.join(","));
		ajax.start();

	}
};

Doctor.search = function() {
	var queryData = {};
	queryData['condition'] = $("#condition").val();
	Doctor.table.refresh({
		query: queryData
	});
};

$(function() {
	var defaultColunms = Doctor.initColumn();
	var table = new BSTable(Doctor.id, "/doctor/addList/" + $("#deptCode").val(), defaultColunms);
	table.setPaginationType("client");
	Doctor.table = table.init();
});