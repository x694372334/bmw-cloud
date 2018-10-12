/**
 * 初始化recommend详情对话框
 */
var RecommendInfoDlg = {
    recommendInfoData : {}
};

/**
 * 清除数据
 */
RecommendInfoDlg.clearData = function() {
    this.recommendInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RecommendInfoDlg.set = function(key, val) {
    this.recommendInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RecommendInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
RecommendInfoDlg.close = function() {
    parent.layer.close(window.parent.Recommend.layerIndex);
}

/**
 * 收集数据
 */
RecommendInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('isshow')
    .set('address')
    .set('content')
    .set('metre')
    .set('url')
    .set('backcolor')
    .set('createtime');
}

/**
 * 提交添加
 */
RecommendInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/recommend/add", function(data){
        Feng.success("添加成功!");
        window.parent.Recommend.table.refresh();
        RecommendInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.recommendInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
RecommendInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/recommend/update", function(data){
        Feng.success("修改成功!");
        window.parent.Recommend.table.refresh();
        RecommendInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.recommendInfoData);
    ajax.start();
}

$(function() {

});
