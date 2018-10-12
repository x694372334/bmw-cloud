/**
 * 初始化报事报修详情对话框
 */
var RepairsFormInfoDlg2 = {
    repairsFormInfoData : {},
    submitFlag:true,
    validateFields: {
    	refuseReason: {
	        validators: {
	            notEmpty: {
	                message: '请填写驳回理由'
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
    .set('refuseReason')
}

/**
 * 提交驳回
 */
RepairsFormInfoDlg2.refuseSubmit = function() {
	if (!this.validate()) {
        return;
    }
    this.clearData();
    this.collectData();
    if($.trim(this.repairsFormInfoData['refuseReason']).length ==0){
    	Feng.error("请输入驳回理由！")
    	return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/repairsForm/refuse", function(data){
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