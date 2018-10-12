/**
 * 初始化投诉建议详情对话框
 */
var ComplaintAdviceInfoDlg = {
    complaintAdviceInfoData : {}
};

/**
 * 清除数据
 */
ComplaintAdviceInfoDlg.clearData = function() {
    this.complaintAdviceInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ComplaintAdviceInfoDlg.set = function(key, val) {
    this.complaintAdviceInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ComplaintAdviceInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ComplaintAdviceInfoDlg.close = function() {
    parent.layer.close(window.parent.ComplaintAdvice.layerIndex);
}

/**
 * 收集数据
 */
ComplaintAdviceInfoDlg.collectData = function() {
    this
    .set('id')
    .set('ownerName')
    .set('neighborhoodsNo')
    .set('phone')
    .set('complaintContent')
    .set('complaintTime')
    .set('status')
    .set('replyTime')
    .set('replyUserId')
    .set('replyUserName')
    .set('replyContent')
    .set('createManId')
    .set('createMan')
    .set('createTime')
    .set('editManId')
    .set('editMan')
    .set('editTime')
    .set('isDelete');
}

/**
 * 提交添加
 */
ComplaintAdviceInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/complaintAdvice/add", function(data){
        Feng.success("添加成功!");
        window.parent.ComplaintAdvice.table.refresh();
        ComplaintAdviceInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.complaintAdviceInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ComplaintAdviceInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/complaintAdvice/update", function(data){
        Feng.success("回复成功!");
        window.parent.ComplaintAdvice.table.refresh();
        ComplaintAdviceInfoDlg.close();
    },function(data){
        Feng.error("回复失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.complaintAdviceInfoData);
    ajax.start();
}

$(function() {
	//格式化时间
	 var replyTime = $("input[name='replyTime']").val();
	 var datetime = new Date(replyTime);
	 var month = datetime.getMonth()+1;
	 $("input[name='replyTime']").val(datetime.getFullYear()+"年"+month+"月"+datetime.getDate()+"日");
	 //状态转码
	 var status = $("input[name='status']").val();
	 if('1'==status){
		 $("input[name='status']").val("已回复");
	 }else{
		 $("input[name='status']").val("未回复");
	 }
});
