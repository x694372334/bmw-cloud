/**
 * 初始化医生详情对话框
 */
var DoctorInfoDlg = {
	doctorInfoData : {},
	validateFields : {
		name : {
			validators : {
				notEmpty : {
					message : '请输入医生姓名！'
				}
			}
		}
	},
	geniusEditor: null,
	synopsisEditor: null
};


/**
 * 清除数据
 */
DoctorInfoDlg.clearData = function() {
    this.doctorInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DoctorInfoDlg.set = function(key, val) {
    this.doctorInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DoctorInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DoctorInfoDlg.close = function() {
    parent.layer.close(window.parent.Doctor.layerIndex);
}

/**
 * 收集数据
 */
DoctorInfoDlg.collectData = function() {
    this.set("id").set("code").set("name").set("dutiesId").set("createManId").set("createMan").set("createTime").set("editManId").set("editMan").set("editTime").set("isDelete").set("department")
    this.doctorInfoData['genius'] = DoctorInfoDlg.geniusEditor.txt.html();
    this.doctorInfoData['synopsis'] = DoctorInfoDlg.synopsisEditor.txt.html();
}


/**
 * 验证数据是否为空
 */
DoctorInfoDlg.validate = function() {
	$('#DoctorInfoForm').data("bootstrapValidator").resetForm();
	$('#DoctorInfoForm').bootstrapValidator('validate');
	return $("#DoctorInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
DoctorInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
		return;
	}
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/doctor/add", function(data){
        Feng.success("添加成功!");
        window.parent.Doctor.table.refresh();
        DoctorInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.doctorInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
DoctorInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
		return;
	}
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/doctor/update", function(data){
        Feng.success("修改成功!");
        window.parent.Doctor.table.refresh();
        DoctorInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.doctorInfoData);
    ajax.start();
}


$(function() {
	Feng.initValidator("DoctorInfoForm", DoctorInfoDlg.validateFields);
    
  //初始化编辑器
    var E = window.wangEditor;
    var synopsisEditor = new E('#synopsisEditor');
    synopsisEditor.create();
    synopsisEditor.txt.html($("#synopsisVal").val());
    DoctorInfoDlg.synopsisEditor = synopsisEditor;
    
    var geniusEditor = new E('#geniusEditor');
    geniusEditor.create();
    geniusEditor.txt.html($("#geniusVal").val());
    DoctorInfoDlg.geniusEditor = geniusEditor;
});
