/**
 * 初始化意见反馈详情对话框
 */
var OFeedbackInfoDlg = {
    oFeedbackInfoData : {}
};

/**
 * 清除数据
 */
OFeedbackInfoDlg.clearData = function() {
    this.oFeedbackInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OFeedbackInfoDlg.set = function(key, val) {
    this.oFeedbackInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OFeedbackInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
OFeedbackInfoDlg.close = function() {
    parent.layer.close(window.parent.OFeedback.layerIndex);
}

/**
 * 收集数据
 */
OFeedbackInfoDlg.collectData = function() {
    this
    .set('id')
    .set('housesId')
    .set('oId')
    .set('opinion')
    .set('userId')
    .set('feedbackTime')
    .set('handlingResult')
    .set('handlingTime')
    .set('uId')
    .set('status')
    .set('createManId')
    .set('createMan')
    .set('createTime')
    .set('editManId')
    .set('editMan')
    .set('editTime')
    .set('isDelete');
}

/**
 * 提交添加
 */
OFeedbackInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/oFeedback/add", function(data){
        Feng.success("添加成功!");
        window.parent.OFeedback.table.refresh();
        OFeedbackInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.oFeedbackInfoData);
    ajax.start();
}

/**
 * 提交回复
 */
OFeedbackInfoDlg.replySubmit = function() {
	
	this.clearData();
	this.collectData();
	
	//提交信息
	var ajax = new $ax(Feng.ctxPath + "/oFeedback/update", function(data){
		Feng.success("回复成功!");
		window.parent.OFeedback.table.refresh();
		OFeedbackInfoDlg.close();
	},function(data){
		Feng.error("回复失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.oFeedbackInfoData);
	ajax.start();
}

/**
 * 提交修改
 */
OFeedbackInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/oFeedback/update", function(data){
        Feng.success("修改成功!");
        window.parent.OFeedback.table.refresh();
        OFeedbackInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.oFeedbackInfoData);
    ajax.start();
}

$(function() {

});
