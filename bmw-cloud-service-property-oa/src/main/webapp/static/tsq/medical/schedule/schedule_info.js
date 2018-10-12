/**
 * 初始化排班详情对话框
 */
var ScheduleInfoDlg = {
    scheduleInfoData : {},
    ztreeInstance: null,
     validateFields : {
    	 medicalDepartmentName : {
			validators : {
				notEmpty : {
					message : '请选择科室'
				}
			}
		},
		medicalDoctor : {
			validators : {
				notEmpty : {
					message : '请选择医生'
				}
			}
		},
		scheduleDate : {
			validators : {
				notEmpty : {
					message : '请选择排班日期'
				}
			}
		}
	}
};

/**
 * 清除数据
 */
ScheduleInfoDlg.clearData = function() {
    this.scheduleInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ScheduleInfoDlg.set = function(key, val) {
    this.scheduleInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ScheduleInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ScheduleInfoDlg.close = function() {
    parent.layer.close(window.parent.Schedule.layerIndex);
}

/**
 * 收集数据
 */
ScheduleInfoDlg.collectData = function() {
    this.set("id").set("scheduleDate").set("approach").set("doctorName").set("medicalDoctorCode").set("appointmentNum").set("isOpen").set("createManId").set("createMan").set("createTime").set("editManId").set("editMan").set("editTime").set("isDelete")
    this.scheduleInfoData['medicalDepartmentCode'] =  $("#parentCode").val()|| $("#medicalDeptCode").val();   
    this.scheduleInfoData['departmentName'] =  $("#medicalDepartmentName").val();   
    this.scheduleInfoData['medicalDoctorCode'] =  $("#medicalDoctor").val();   
    this.scheduleInfoData['doctorName'] =  $("#medicalDoctor").find("option:selected").text();   
}

/**
 * 验证数据是否为空
 */
ScheduleInfoDlg.validate = function() {
	$('#ScheduleInfoForm').data("bootstrapValidator").resetForm();
	$('#ScheduleInfoForm').bootstrapValidator('validate');
	return $("#ScheduleInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
ScheduleInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
		return;
	}

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/schedule/add", function(data){
        Feng.success("添加成功!");
        window.parent.Schedule.table.refresh();
        ScheduleInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.scheduleInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ScheduleInfoDlg.editSubmit = function() {
    this.clearData();
    this.collectData();

	if (!this.validate()) {
		return;
	}
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/schedule/update", function(data){
        Feng.success("修改成功!");
        window.parent.Schedule.table.refresh();
        ScheduleInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.scheduleInfoData);
    ajax.start();
}
ScheduleInfoDlg.showSelectTree = function () {
    Feng.showInputTree("parentCode", "pcodeTreeDiv", 15, 34);
};

/**
 * 点击父级编号input框时
 */
ScheduleInfoDlg.onClickDept = function (e, treeId, treeNode) {
	$("#medicalDepartmentName").attr("value", ScheduleInfoDlg.ztreeInstance.getSelectedVal());
    $("#parentCode").attr("value",treeNode.pcode);
    ScheduleInfoDlg.getDoctor($("#parentCode").val());
};

ScheduleInfoDlg.getDoctor= function (e) {
	//获取医生下拉列表
	var ajax = new $ax(Feng.ctxPath + "/deptDoctorRelation/doctorList", function(data){
		$("#medicalDoctor option").remove();
		$("#medicalDoctor").append('<option value="" selected>请选择</option>');
		for(var i = 0 ; i < data.length; i++ ){
        	if($("#medicalDoctorCode").val() == data[i].code){
        		$("#medicalDoctor").append("<option value='"+data[i].code+"' selected='selected'>"+data[i].name +"</option>");
        	}else{
        		$("#medicalDoctor").append("<option value='"+data[i].code+"'>"+data[i].name+"</option>");
        	}
        }
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
	ajax.set("deptCode", e);
    ajax.start();
};

$(function() {
	Feng.initValidator("ScheduleInfoForm", ScheduleInfoDlg.validateFields);
	
	var ztree = new $ZTree("pcodeTree", "/deptDoctorRelation/getDepartmentTreeList");
    ztree.bindOnClick(ScheduleInfoDlg.onClickDept);
    ztree.init();
    ScheduleInfoDlg.ztreeInstance = ztree;
    
    
	ScheduleInfoDlg.getDoctor($("#medicalDeptCode").val());
    
});
