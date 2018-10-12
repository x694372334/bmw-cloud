/**
 * 初始化详情对话框
 */
var BirthdayMsgModelInfoDlg = {
    birthdayMsgModelInfoData : {}
};

/**
 * 清除数据
 */
BirthdayMsgModelInfoDlg.clearData = function() {
    this.birthdayMsgModelInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BirthdayMsgModelInfoDlg.set = function(key, val) {
    this.birthdayMsgModelInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BirthdayMsgModelInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BirthdayMsgModelInfoDlg.close = function() {
    parent.layer.close(window.parent.BirthdayMsgModel.layerIndex);
}

/**
 * 收集数据
 */
BirthdayMsgModelInfoDlg.collectData = function() {
	this.birthdayMsgModelInfoData['sendMode']=$("input[name='sendMode']:checked").val();
	this.birthdayMsgModelInfoData['sendDateMode']=$("input[name='sendDateMode']:checked").val();
    this
    .set('modelId')
    .set('sendTime')
}

/**
 * 提交添加
 */
BirthdayMsgModelInfoDlg.addSubmit = function() {
    this.clearData();
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/birthdayMsgModel/modifySetting", function(data){
        Feng.success("配置修改成功!");
        window.parent.BirthdayMsgModel.table.refresh();
        BirthdayMsgModelInfoDlg.close();
    },function(data){
        Feng.error("配置修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.birthdayMsgModelInfoData);
    ajax.start();
}

//切换页面模式
$("input[name='sendMode']").click(function(){
	if(this.value==1){//固定模板
		$("#modelId").attr("disabled","disabled");
		$("input[name='sendDateMode']").removeAttr("disabled");
		$("#sendTime").removeAttr("disabled");
	}else if(this.value==2){//随机模板
		$("#modelId").removeAttr("disabled");
		$("input[name='sendDateMode']").removeAttr("disabled");
		$("#sendTime").removeAttr("disabled");
	}else{//关闭此功能
		$("#modelId").attr("disabled","disabled");
		$("input[name='sendDateMode']").attr("disabled","disabled");
		$("#sendTime").attr("disabled","disabled");
	}
})
$(function() {
	$('.clockpicker').clockpicker();
});
