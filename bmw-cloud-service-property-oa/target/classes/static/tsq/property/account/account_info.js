/**
 * 初始化account详情对话框
 */
var AccountInfoDlg = {
    accountInfoData : {}
};

/**
 * 清除数据
 */
AccountInfoDlg.clearData = function() {
    this.accountInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AccountInfoDlg.set = function(key, val) {
    this.accountInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AccountInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AccountInfoDlg.close = function() {
    parent.layer.close(window.parent.Account.layerIndex);
}

/**
 * 收集数据
 */
AccountInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('accountName')
    .set('petName')
    .set('pwd')
    .set('company')
    .set('deptId')
    .set('positionId')
    .set('positionName')
    .set('phone')
    .set('signature')
    .set('deptName')
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
AccountInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/account/add", function(data){
        Feng.success("添加成功!");
        window.parent.Account.table.refresh();
        AccountInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.accountInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AccountInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/account/update", function(data){
        Feng.success("修改成功!");
        window.parent.Account.table.refresh();
        AccountInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.accountInfoData);
    ajax.start();
}

$(function() {

});
