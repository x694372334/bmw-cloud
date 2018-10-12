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
    this
    .set("id")
    .set('content')
}

/**
 * 提交添加
 */
BirthdayMsgModelInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if(this.birthdayMsgModelInfoData.content==null||this.birthdayMsgModelInfoData.content==''){
    	Feng.error("请输入模板信息");
    	return;
    }else if(this.birthdayMsgModelInfoData.content.length>75){
    	Feng.error("字数最多75个字");
    	return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/birthdayMsgModel/addModel", function(data){
        Feng.success("添加成功!");
        window.parent.BirthdayMsgModel.table.refresh();
        BirthdayMsgModelInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.birthdayMsgModelInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BirthdayMsgModelInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if(this.birthdayMsgModelInfoData.content==null||this.birthdayMsgModelInfoData.content==''){
    	Feng.error("请输入模板信息");
    	return;
    }else if(this.birthdayMsgModelInfoData.content.length>75){
    	Feng.error("字数最多75个字");
    	return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/birthdayMsgModel/update", function(data){
    	if(data.code==200){
    		Feng.success("修改成功!");
    		window.parent.BirthdayMsgModel.table.refresh();
    	}else{
    		Feng.error(data.message);
    	}                 
        BirthdayMsgModelInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.birthdayMsgModelInfoData);
    ajax.start();
}

$(function() {

});
