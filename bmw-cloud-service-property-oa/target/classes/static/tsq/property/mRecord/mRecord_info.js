/**
 * 初始化抄表记录详情对话框
 */
var MRecordInfoDlg = {
    mRecordInfoData : {}
};

/**
 * 清除数据
 */
MRecordInfoDlg.clearData = function() {
    this.mRecordInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MRecordInfoDlg.set = function(key, val) {
    this.mRecordInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MRecordInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MRecordInfoDlg.close = function() {
    parent.layer.close(window.parent.MRecord.layerIndex);
}

/**
 * 收集数据
 */
MRecordInfoDlg.collectData = function() {
    this.set("rId").set("sId").set("tNum").set("uAmount").set("tMonth").set("tableNumber")
}

/**
 * 提交添加
 */
MRecordInfoDlg.addSubmit = function() {
	submit();
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mRecord/add", function(data){
        Feng.success("添加成功!");
        window.parent.MRecord.table.refresh();
        MRecordInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.mRecordInfoData);
    ajax.start();
}

function submit(){
	$("#uAmount").val($("#test").val());
}

/**
 * 提交修改
 */
MRecordInfoDlg.editSubmit = function() {
	submit();
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mRecord/update", function(data){
        Feng.success("修改成功!");
        window.parent.MRecord.table.refresh();
        MRecordInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.mRecordInfoData);
    ajax.start();
}

$(function() {

});
