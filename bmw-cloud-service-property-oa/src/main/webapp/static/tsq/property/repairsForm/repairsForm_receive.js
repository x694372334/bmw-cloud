/**
 * 初始化报事报修详情对话框
 */
var RepairsFormInfoDlg2 = {
    repairsFormInfoData : {},
    submitFlag:true,
    validateFields: {
    	probablyCompleteTime: {
	        validators: {
	            notEmpty: {
	                message: '请填写预计完成时间'
	            }
	        }
	    },
	    desc: {
	        validators: {
	            notEmpty: {
	                message: '备注不能为空'
	            }
	        }
	    }
	}
};

/**
 * 清除数据
 */
RepairsFormInfoDlg2.clearData = function() {
    this.repairsFormInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RepairsFormInfoDlg2.set = function(key, val) {
    this.repairsFormInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RepairsFormInfoDlg2.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
RepairsFormInfoDlg2.close = function() {
    parent.layer.close(window.parent.RepairsForm.layerIndex);
}

/**
 * 收集数据
 */
RepairsFormInfoDlg2.collectData = function() {
    this
    .set('id')
    .set('probablyCompleteTime')
    .set('desc')
}
/**
 * 提交接单
 */
RepairsFormInfoDlg2.receiveSubmit = function() {
  if (!this.validate()) {
        return;
    }
    this.clearData();
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/repairsForm/receive", function(data){
        Feng.success("修改成功!");
        window.parent.RepairsForm.table.refresh();
        RepairsFormInfoDlg2.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.repairsFormInfoData);
    ajax.start();
}

RepairsFormInfoDlg2.validate = function () {
    $('#repairsFormForm').data("bootstrapValidator").resetForm();
    $('#repairsFormForm').bootstrapValidator('validate');
    return $("#repairsFormForm").data('bootstrapValidator').isValid();
};
$(function() {
		Feng.initValidator("repairsFormForm", RepairsFormInfoDlg2.validateFields);
});