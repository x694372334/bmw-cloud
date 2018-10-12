/**
 * 初始化wdocuments详情对话框
 */
var WDocumentsInfoDlg = {
    wDocumentsInfoData : {}
};

/**
 * 清除数据
 */
WDocumentsInfoDlg.clearData = function() {
    this.wDocumentsInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WDocumentsInfoDlg.set = function(key, val) {
    this.wDocumentsInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WDocumentsInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
WDocumentsInfoDlg.close = function() {
    parent.layer.close(window.parent.WDocuments.layerIndex);
}

/**
 * 收集数据
 */
WDocumentsInfoDlg.collectData = function() {
    this
    .set('dId')
    .set('nId')
    .set('dName')
    .set('dUrl')
    .set('upUser')
    .set('upTime')
    .set('createManId')
    .set('createMan')
    .set('createTime')
    .set('editManId')
    .set('editMan')
    .set('editTime')
    .set('title')
    .set('sharingLevel')
    .set('dType')
    .set('isDelete');
}

/**
 * 提交添加
 */
WDocumentsInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/wDocuments/add", function(data){
        Feng.success("添加成功!");
        window.parent.WDocuments.table.refresh();
        WDocumentsInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wDocumentsInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
WDocumentsInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/wDocuments/update", function(data){
        Feng.success("修改成功!");
        window.parent.WDocuments.table.refresh();
        WDocumentsInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wDocumentsInfoData);
    ajax.start();
}

$(function() {

});
