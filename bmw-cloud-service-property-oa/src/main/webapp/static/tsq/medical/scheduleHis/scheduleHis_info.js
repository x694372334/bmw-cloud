/**
 * 初始化排班历史详情对话框
 */
var ScheduleHisInfoDlg = {
    scheduleHisInfoData : {}
};

/**
 * 清除数据
 */
ScheduleHisInfoDlg.clearData = function() {
    this.scheduleHisInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ScheduleHisInfoDlg.set = function(key, val) {
    this.scheduleHisInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ScheduleHisInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ScheduleHisInfoDlg.close = function() {
    parent.layer.close(window.parent.ScheduleHis.layerIndex);
}

/**
 * 收集数据
 */
ScheduleHisInfoDlg.collectData = function() {
    this
}

/**
 * 提交添加
 */
ScheduleHisInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/scheduleHis/add", function(data){
        Feng.success("添加成功!");
        window.parent.ScheduleHis.table.refresh();
        ScheduleHisInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.scheduleHisInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ScheduleHisInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/scheduleHis/update", function(data){
        Feng.success("修改成功!");
        window.parent.ScheduleHis.table.refresh();
        ScheduleHisInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.scheduleHisInfoData);
    ajax.start();
}

$(function() {

});
