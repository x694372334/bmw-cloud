/**
 * 初始化详情对话框
 */
var hospitalInfoDlg = {
    hospitalInfoData : {},
    editor: null,
    ztreeInstance: null,
    validateFields : {
		name : {
			validators : {
				notEmpty : {
					message : '请输入医院名称'
				}
			}
		},
		level : {
			validators : {
				notEmpty : {
					message : '请选择医院级别'
				}
			}
		}
	}
};

/**
 * 清除数据
 */
hospitalInfoDlg.clearData = function() {
    this.hospitalInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
hospitalInfoDlg.set = function(key, val) {
    this.hospitalInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
hospitalInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
hospitalInfoDlg.close = function() {
    parent.layer.close(window.parent.hospital.layerIndex);
}

/**
 * 收集数据
 */
hospitalInfoDlg.collectData = function() {
    this.set("id").set("mediaAreaCode").set("name").set('avatar').set("level").set("code").set("description").set("tel").set("address").set("createManId").set("createMan").set("createTime").set("editManId").set("editMan").set("editTime").set("isDelete");
    this.hospitalInfoData['description'] = hospitalInfoDlg.editor.txt.html();
    this.hospitalInfoData['medicalAreaCode'] = $("#parentCode").val();  
    this.hospitalInfoData['level'] = $("#levelId").find("option:selected").val();  
}

/**
 * 验证数据是否为空
 */
hospitalInfoDlg.validate = function() {
	$('#hospitalInfoForm').data("bootstrapValidator").resetForm();
	$('#hospitalInfoForm').bootstrapValidator('validate');
	return $("#hospitalInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
hospitalInfoDlg.addSubmit = function() {
    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
		return;
	}

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/hospital/add", function(data){
        Feng.success("添加成功!");
        window.parent.hospital.table.refresh();
        hospitalInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.hospitalInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
hospitalInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
		return;
	}

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/hospital/update", function(data){
        Feng.success("修改成功!");
        window.parent.hospital.table.refresh();
        hospitalInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.hospitalInfoData);
    ajax.start();
}

hospitalInfoDlg.showAreaSelectTree = function () {
    Feng.showInputTree("parentCode", "pcodeTreeDiv", 15, 34);
};

/**
 * 点击父级编号input框时
 */
hospitalInfoDlg.onClickDept = function (e, treeId, treeNode) {
	$("#medicalAreaCode").attr("value", hospitalInfoDlg.ztreeInstance.getSelectedVal());
    $("#parentCode").attr("value",treeNode.pcode);
};

/**
 * 获取字典表的医院级别
 */
hospitalInfoDlg.getTitle = function() {
	$.ajax({
		"type" : 'get',
		"url" : Feng.ctxPath + "/dict/getDic",
		"dataType" : "json",
		"data" : {
			"code" : "yyjb"
		},
		"success" : function(data) {
			for (var i = 0; i < data.length; i++) {
				if ($("#level").val() == data[i].name) {
					$("#levelId").append(
							"<option value='" + data[i].name
									+ "' selected='selected'>" + data[i].name
									+ "</option>");
				} else {
					$("#levelId").append(
							"<option value='" + data[i].name + "'>"
									+ data[i].name + "</option>");
				}

			}
		}
	});
}


$(function() {
	Feng.initValidator("hospitalInfoForm", hospitalInfoDlg.validateFields);
	
	//初始化编辑器
    var E = window.wangEditor;
    var editor = new E('#editor');
    editor.create();
    editor.txt.html($("#contentVal").val());
    hospitalInfoDlg.editor = editor;
    
    // 初始化头像上传
    var avatarUp = new $WebUpload("avatar");
    avatarUp.setUploadBarId("progressBar");
    avatarUp.uploadUrl=Feng.ctxPath + '/hospital/upload';
    avatarUp.init();
    
    var ztree = new $ZTree("pcodeTree", "/area/selectAreaTreeList");
    ztree.bindOnClick(hospitalInfoDlg.onClickDept);
    ztree.init();
    hospitalInfoDlg.ztreeInstance = ztree;
    
    hospitalInfoDlg.getTitle();
});
