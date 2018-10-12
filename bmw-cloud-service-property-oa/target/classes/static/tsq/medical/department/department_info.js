var DepartmentInfoDlg = {
	departmentInfoData: {},
	ztreeInstance: null,
	validateFields: {
		name: {
			validators: {
				notEmpty: {
					message: '请输入科室名称'
				}
			}
		},
		medicalHospitalCode: {
			validators: {
				notEmpty: {
					message: '请选择所属医院'
				}
			}
		}
	}
};
DepartmentInfoDlg.clearData = function() {
	this.departmentInfoData = {};
}
DepartmentInfoDlg.set = function(key, val) {
	this.departmentInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
	return this;
}
DepartmentInfoDlg.get = function(key) {
	return $("#" + key).val();
}
DepartmentInfoDlg.close = function() {
	parent.layer.close(window.parent.Department.layerIndex);
}
DepartmentInfoDlg.collectData = function() {
	this.set("id").set("code").set("medicalHospitalCode").set("parentCode").set("name").set("sort").set("level").set("isLeaf").set("childrenCount").set("createManId").set("createMan").set("createTime").set("editManId").set("editMan").set("editTime").set("isDelete");
}
DepartmentInfoDlg.validate = function() {
	$('#departmentInfoForm').data("bootstrapValidator").resetForm();
	$('#departmentInfoForm').bootstrapValidator('validate');
	return $("#departmentInfoForm").data('bootstrapValidator').isValid();
};
DepartmentInfoDlg.addSubmit = function() {
	this.clearData();
	this.collectData();
	if (!this.validate()) {
		return false;
	}
	var ajax = new $ax(Feng.ctxPath + "/department/add",
		function(data) {
			Feng.success("添加成功!");
			window.parent.Department.table.refresh();
			DepartmentInfoDlg.close();
		},
		function(data) {
			Feng.error("添加失败!" + data.responseJSON.message + "!");
		}
	);
	ajax.set(this.departmentInfoData);
	ajax.start();
}
DepartmentInfoDlg.editSubmit = function() {
	this.clearData();
	this.collectData();
	if (!this.validate()) {
		return false;
	}
	if ($("#parentCode").val() == $("#code").val()) {
		Feng.error("父节点不能是自己！");
		return false;
	} else {
		var ajax = new $ax(Feng.ctxPath + "/department/update",
			function(data) {
				Feng.success("修改成功!");
				window.parent.Department.table.refresh();
				DepartmentInfoDlg.close();
			},
			function(data) {
				Feng.error("修改失败!" + data.responseJSON.message + "!");
			}
		);
		ajax.set(this.departmentInfoData);
		ajax.start();
	}
}
DepartmentInfoDlg.showDepartmentSelectTree = function() {
	Feng.showInputTree("parentCode", "pcodeTreeDiv", 15, 34);
};
DepartmentInfoDlg.onClickDept = function(e, treeId, treeNode) {
	$("#pcodeName").attr("value", DepartmentInfoDlg.ztreeInstance.getSelectedVal());
	$("#parentCode").attr("value", treeNode.pcode);
};
DepartmentInfoDlg.hospitalCodeChange = function(e) {
	var ztree = new $ZTree("pcodeTree", "/department/selectDepartmentTreeList?hospitalCode=" + $(e).val());
	ztree.bindOnClick(DepartmentInfoDlg.onClickDept);
	ztree.init();
	DepartmentInfoDlg.ztreeInstance = ztree;
};
DepartmentInfoDlg.pcodeNameChange = function(e) {
	$.ajax({
		"type": 'get',
		"url": Feng.ctxPath + "/hospital/list",
		"dataType": "json",
		"async": false,
		"success": function(data) {
			for (var i = 0; i < data.length; i++) {
				if ($("#parentCode").val() == data[i].code) {
					$("#medicalHospitalCode").append("<option value='" + data[i].code + "' selected='selected'>" + data[i].name + "</option>");
				} else {
					$("#medicalHospitalCode").append("<option value='" + data[i].code + "'>" + data[i].name + "</option>");
				}
			}
		}
	})
};
$(function() {
	Feng.initValidator("departmentInfoForm", DepartmentInfoDlg.validateFields);
	var url = "";
	if ($("#hospitalCode").val()) {
		url = "/department/selectDepartmentTreeList?hospitalCode=" + $("#hospitalCode").val();
	} else {
		url = "/department/selectDepartmentTreeList";
	}
	var ztree = new $ZTree("pcodeTree", url);
	ztree.bindOnClick(DepartmentInfoDlg.onClickDept);
	ztree.init();
	DepartmentInfoDlg.ztreeInstance = ztree;
	$("#medicalHospitalCode").bind("change",
	function() {
		DepartmentInfoDlg.hospitalCodeChange(this);
	});
	$("#pcodeName").bind("change",
	function() {
		DepartmentInfoDlg.pcodeNameChange(this);
	});
	$.ajax({
		"type": 'get',
		"url": Feng.ctxPath + "/hospital/list",
		"dataType": "json",
		"async": false,
		"success": function(data) {
			for (var i = 0; i < data.length; i++) {
				if ($("#hospitalCode").val() == data[i].code) {
					$("#medicalHospitalCode").append("<option value='" + data[i].code + "' selected='selected'>" + data[i].name + "</option>");
				} else {
					$("#medicalHospitalCode").append("<option value='" + data[i].code + "'>" + data[i].name + "</option>");
				}
			}
		}
	})
});