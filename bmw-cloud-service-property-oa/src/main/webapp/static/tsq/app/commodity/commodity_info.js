/**
 * 初始化商品维护详情对话框
 */
var CommodityInfoDlg = {
    commodityInfoData : {}
};

/**
 * 清除数据
 */
CommodityInfoDlg.clearData = function() {
    this.commodityInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CommodityInfoDlg.set = function(key, val) {
    this.commodityInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CommodityInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CommodityInfoDlg.close = function() {
    parent.layer.close(window.parent.Commodity.layerIndex);
}

/**
 * 收集数据
 */
CommodityInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('type')
    .set('specification')
    .set('unit')
    .set('price')
    .set('category')
    .set('isRecommend')
    .set('backGroundUrl')
    .set('commodityUrl')
    .set('detailLink')
    .set('isChange');
}

/**
 * 提交添加
 */
CommodityInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/commodity/add", function(data){
        Feng.success("添加成功!");
        window.parent.Commodity.table.refresh();
        CommodityInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.commodityInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CommodityInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/commodity/update", function(data){
        Feng.success("修改成功!");
        window.parent.Commodity.table.refresh();
        CommodityInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.commodityInfoData);
    ajax.start();
}

$(function() {

});
