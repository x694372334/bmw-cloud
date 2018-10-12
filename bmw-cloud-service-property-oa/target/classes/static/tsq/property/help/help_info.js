/**
 * 初始化help详情对话框
 */
var HelpInfoDlg = {
    helpInfoData : {}
};

/**
 * 清除数据
 */
HelpInfoDlg.clearData = function() {
    this.helpInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HelpInfoDlg.set = function(key, val) {
    this.helpInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HelpInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
HelpInfoDlg.close = function() {
    parent.layer.close(window.parent.Help.layerIndex);
}

/**
 * 收集数据
 */
HelpInfoDlg.collectData = function() {
    this
    .set('id')
    .set('title')
    .set('content')
    .set('createId')
    .set('createMan')
    .set('createTime')
    .set('editManId')
    .set('editMan')
    .set('editTime');
}

/**
 * 提交添加
 */
HelpInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/help/add", function(data){
        Feng.success("添加成功!");
        window.parent.Help.table.refresh();
        HelpInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.helpInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
HelpInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/help/update", function(data){
        Feng.success("修改成功!");
        window.parent.Help.table.refresh();
        HelpInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.helpInfoData);
    ajax.start();
}

$(function() {

});
