/**
 * 初始化版本管理详情对话框
 */
var VersionInfoDlg = {
    versionInfoData : {}
};

/**
 * 清除数据
 */
VersionInfoDlg.clearData = function() {
    this.versionInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VersionInfoDlg.set = function(key, val) {
    this.versionInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VersionInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
VersionInfoDlg.close = function() {
    parent.layer.close(window.parent.Version.layerIndex);
}

/**
 * 收集数据
 */
VersionInfoDlg.collectData = function() {
    this
    .set('id')
    .set('network')
    .set('number')
    .set('gradeNumber')
    .set('createBy')
    .set('createTime')
    .set('gradeBy')
    .set('gradeTime')
    .set('isChange');
}

/**
 * 提交添加
 */
VersionInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/version/add", function(data){
        Feng.success("添加成功!");
        window.parent.Version.table.refresh();
        VersionInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.versionInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
VersionInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/version/update", function(data){
        Feng.success("修改成功!");
        window.parent.Version.table.refresh();
        VersionInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.versionInfoData);
    ajax.start();
}

$(function() {

});
