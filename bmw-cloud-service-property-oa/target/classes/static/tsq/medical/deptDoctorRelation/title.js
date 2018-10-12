var TitleInfoDlg = {};

TitleInfoDlg.getTitle = function() {
	$.ajax({
		"type": 'get',
		"url": Feng.ctxPath + "/dict/getDic",
		"dataType": "json",
		"data": {
			"code": "yszc"
		},
		"success": function(data) {
			for (var i = 0; i < data.length; i++) {
				if ($("#title").val() == data[i].num) {
					$("#title").append("<option value='" + data[i].num + "' selected='selected'>" + data[i].name + "</option>");
				} else {
					$("#title").append("<option value='" + data[i].num + "'>" + data[i].name + "</option>");
				}

			}
		}
	});
}

TitleInfoDlg.submitTitle = function() {
	// 提交信息
	var ajax = new $ax(Feng.ctxPath + "/deptDoctorRelation/submitTitle",
		function(data) {
			Feng.success("关联成功!");
			window.parent.Relation.table.refresh();
			parent.layer.close(window.parent.Relation.layerIndex);
		},
		function(data) {
			Feng.error("关联失败!" + data.responseJSON.message + "!");
		}
	);
	ajax.set("doctorIds", $("#doctorIds").val());
	ajax.set("titleId", $("#title").find("option:selected").val());
	ajax.set("titleName", $("#title").find("option:selected").text());
	ajax.start();
}

$(function() {
	Feng.initValidator("TitleInfoForm", TitleInfoDlg.validateFields);
	TitleInfoDlg.getTitle();
});