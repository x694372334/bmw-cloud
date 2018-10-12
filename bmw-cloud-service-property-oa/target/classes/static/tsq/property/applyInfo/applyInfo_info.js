/**
 * 初始化审批申请单详情对话框
 */
var ApplyInfoInfoDlg = {
    applyInfoInfoData : {}
};

/**
 * 清除数据
 */
ApplyInfoInfoDlg.clearData = function() {
    this.applyInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ApplyInfoInfoDlg.set = function(key, val) {
    this.applyInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ApplyInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ApplyInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.ApplyInfo.layerIndex);
}

/**
 * 关闭此对话框
 */
ApplyInfoInfoDlg.flowableClose = function() {
    parent.layer.close(window.parent.RuTask.layerIndex);
}

/**
 * 收集数据
 */
ApplyInfoInfoDlg.collectData = function() {
    this.applyInfoInfoData['aDetails'] = ApplyInfoInfoDlg.editor.txt.html();
    this
    .set('id')
    .set('aName')
    .set('aCode')
    .set('aType')
    .set('aId')
    .set('aUser')
    .set('aTime')
    .set('eaUser')
    .set('eaTime')
    .set('eaResult')
    .set('eaOpinion')
    .set('createManId')
    .set('createMan')
    .set('createTime')
    .set('editManId')
    .set('editMan')
    .set('editTime')
    .set('isDelete')
    .set("taskId")
}

/**
 * 提交添加
 */
ApplyInfoInfoDlg.addSubmit = function() {
	submitxxx();
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/applyInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.ApplyInfo.table.refresh();
        ApplyInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.applyInfoInfoData);
    ajax.start();
}

/**
 * 提交添加
 */
ApplyInfoInfoDlg.flowabledApply = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/applyInfo/flowabled_apply", function(data){
        Feng.success("成功通过!");
        window.parent.RuTask.table.refresh();
        ApplyInfoInfoDlg.flowableClose();
    },function(data){
        Feng.error("通过失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.applyInfoInfoData);
    ajax.start();
}


/**
 * 提交添加
 */
ApplyInfoInfoDlg.flowabledReject = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/applyInfo/flowabled_reject", function(data){
        Feng.success("驳回成功!");
        window.parent.RuTask.table.refresh();
        ApplyInfoInfoDlg.flowableClose();
    },function(data){
        Feng.error("驳回失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.applyInfoInfoData);
    ajax.start();
}

function submitxxx(){
	var options=$("#text option:selected");
	$("#aType").val(options.val());
}

function aType(){
	var options=$("#aType option:selected");
//	alert(options.val());
//	alert($("#aType").val());
	 $.ajax({
	        "type" : 'get',
	        "url": Feng.ctxPath + "/dict/getDic",
	        "dataType" : "json",
	        "data" : {"code" : "sxlx"},
	        "success" : function(data) {
	            for(var i = 0 ; i < data.length; i++ ){
	            	if($("#aType").val() == data[i].num){
	            		 $("#text").append("<option value='"+data[i].num+"' selected='selected'>"+data[i].name+"</option>");
	            	}else{
	            		$("#text").append("<option value='"+data[i].num+"'>"+data[i].name+"</option>");
	            	}
	                
	            }
	        }
	    });
}


/**
 * 提交修改
 */
ApplyInfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/applyInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.ApplyInfo.table.refresh();
        ApplyInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.applyInfoInfoData);
    ajax.start();
}

function setflowabled(){
	ApplyInfoInfoDlg.flowabledAddSubmit();
}

/**
 * 提交修改
 */
ApplyInfoInfoDlg.flowabledAddSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/applyInfo/flowabled_add", function(data){
        Feng.success("提交申请成功!");
        window.parent.ApplyInfo.table.refresh();
        ApplyInfoInfoDlg.close();
    },function(data){
        Feng.error("提交申请失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.applyInfoInfoData);
    ajax.start();
}

$(function() {
	  //初始化富文本框
	editor = new wangEditor("#aDetails");
	editor.create();
	editor.txt.html($("#aDetailsVal").val());
	ApplyInfoInfoDlg.editor = editor;
	
	aType();

});
