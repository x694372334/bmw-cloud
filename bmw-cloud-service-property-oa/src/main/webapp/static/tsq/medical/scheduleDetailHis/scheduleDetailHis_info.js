/**
 * 初始化排班历史明细详情对话框
 */
var ScheduleDetailHisInfoDlg = {
    scheduleDetailHisInfoData : {}
};

/**
 * 清除数据
 */
ScheduleDetailHisInfoDlg.clearData = function() {
    this.scheduleDetailHisInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ScheduleDetailHisInfoDlg.set = function(key, val) {
    this.scheduleDetailHisInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ScheduleDetailHisInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ScheduleDetailHisInfoDlg.close = function() {
    parent.layer.close(window.parent.ScheduleDetailHis.layerIndex);
}

/**
 * 收集数据
 */
ScheduleDetailHisInfoDlg.collectData = function() {
    this
}

/**
 * 提交添加
 */
ScheduleDetailHisInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/scheduleDetailHis/add", function(data){
        Feng.success("添加成功!");
        window.parent.ScheduleDetailHis.table.refresh();
        ScheduleDetailHisInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.scheduleDetailHisInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ScheduleDetailHisInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/scheduleDetailHis/update", function(data){
        Feng.success("修改成功!");
        window.parent.ScheduleDetailHis.table.refresh();
        ScheduleDetailHisInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.scheduleDetailHisInfoData);
    ajax.start();
}

$(function() {

});
