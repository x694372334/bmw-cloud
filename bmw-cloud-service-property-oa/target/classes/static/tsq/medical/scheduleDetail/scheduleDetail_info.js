/**
 * 初始化medical详情对话框
 */
var ScheduleDetailInfoDlg = {
    scheduleDetailInfoData : {},
    validateFields : {
    	scheduleStartTime : {
			validators : {
				notEmpty : {
					message : '请输入开始时间'
				}
			}
		},
		scheduleEndTime : {
			validators : {
				notEmpty : {
					message : '请输入结束时间'
				}
			}
		}
	}
};

/**
 * 清除数据
 */
ScheduleDetailInfoDlg.clearData = function() {
    this.scheduleDetailInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ScheduleDetailInfoDlg.set = function(key, val) {
    this.scheduleDetailInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ScheduleDetailInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ScheduleDetailInfoDlg.close = function() {
    parent.layer.close(window.parent.ScheduleDetail.layerIndex);
}

/**
 * 收集数据
 */
ScheduleDetailInfoDlg.collectData = function() {
    this
    .set('id')
    .set('medicalScheduleId')
    .set('scheduleDate')
    .set('scheduleStartTime')
    .set('scheduleEndTime')
    .set('departmentName')
    .set('medicalDepartmentCode')
    .set('doctorName')
    .set('medicalDoctorCode')
    .set('isAppointment')
    .set('appointmentNum')
    .set('createManId')
    .set('createMan')
    .set('createTime')
    .set('editManId')
    .set('editMan')
    .set('editTime')
    .set('isDelete');
}
/**
 * 验证数据是否为空
 */
ScheduleDetailInfoDlg.validate = function() {
	$('#ScheduleDetailInfoForm').data("bootstrapValidator").resetForm();
	$('#ScheduleDetailInfoForm').bootstrapValidator('validate');
	return $("#ScheduleDetailInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
ScheduleDetailInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
		return;
	}

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/scheduleDetail/add", function(data){
        Feng.success("添加成功!");
        window.parent.ScheduleDetail.table.refresh();
        ScheduleDetailInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.scheduleDetailInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ScheduleDetailInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
		return;
	}

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/scheduleDetail/update", function(data){
        Feng.success("修改成功!");
        window.parent.ScheduleDetail.table.refresh();
        ScheduleDetailInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.scheduleDetailInfoData);
    ajax.start();
}


$(function() {
	Feng.initValidator("ScheduleDetailInfoForm", ScheduleDetailInfoDlg.validateFields);
	if("1"===$("#approach").val()){
		//如果是按小时展示的，不控制结束时间必填
		$("#ScheduleDetailInfoForm").bootstrapValidator('removeField','scheduleEndTime');
		$('#scheduleEndTime').attr("disabled",true);
	}
});
