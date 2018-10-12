/**
 * 初始化worktast详情对话框
 */
var TaskInfoDlg = {
    taskInfoData : {},
	validateFields : {
		tName : {
			validators : {
				notEmpty : {
					message : '任务名称不能为空'
				}
			}
		}
		
	}
};

/**
 * 清除数据
 */
TaskInfoDlg.clearData = function() {
    this.taskInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TaskInfoDlg.set = function(key, val) {
    this.taskInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TaskInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TaskInfoDlg.close = function() {
    parent.layer.close(window.parent.Task.layerIndex);
}

/**
 * 收集数据
 */
TaskInfoDlg.collectData = function() {
    this
    .set('tId')
    .set('tName')
    .set('tDescribe')
    .set('tPrincipal')
    .set('tParticipationPer')
    .set('tCTime')
    .set('tSummarize')
    .set('tStatus')
    .set('createManId')
    .set('createMan')
    .set('createTime')
    .set('editManId')
    .set('editMan')
    .set('editTime')
    .set('eId')
    .set('isDelete');
    this.taskInfoData['tDescribe'] = TaskInfoDlg.editor.txt.html();
    if(null!=$("#tParticipationPer").val()){
    	 this.taskInfoData['tParticipationPer'] = $("#tParticipationPer").val().toString(); 
    }
}

/**
 * 验证数据是否为空
 */
TaskInfoDlg.validate = function () {
    $('#taskForm').data("bootstrapValidator").resetForm();
    $('#taskForm').bootstrapValidator('validate');
    return $("#taskForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
TaskInfoDlg.addSubmit = function() {
    if(""!=$("#tPrincipal").val()||null!=$("#tParticipationPer").val()){
    	$("#tStatus").val("2");
    }
    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/task/add", function(data){
        Feng.success("添加成功!");
        window.parent.Task.table.refresh();
        TaskInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.taskInfoData);
    console.log(this.taskInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TaskInfoDlg.editSubmit = function() {
	 if(""!=$("#tPrincipal").val()||null!=$("#tParticipationPer").val()){
		 if("3"!=$("#tStatus").val()){
			 $("#tStatus").val("2");
		 }
	    }
    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/task/update", function(data){
        Feng.success("修改成功!");
        window.parent.Task.table.refresh();
        TaskInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.taskInfoData);
    ajax.start();
}

$(function() {
	  Feng.initValidator("taskForm", TaskInfoDlg.validateFields);
	  //初始化文本框
      editor = new wangEditor("#tDescribe");
	  editor.create();
	  editor.txt.html($("#tDescribeVal").val());
	  TaskInfoDlg.editor = editor;
	  
	  //用户列表
	    $.ajax({
	        "type" : 'get',
	        "url": Feng.ctxPath + "/mgr/list",
	        "dataType" : "json",
	        "success" : function(data) {
	        	 var value = $("#tPrincipal1").val()// 这个值就是你获取的值;
	            for(var i = 0 ; i < data.length; i++ ){
	        				if (value == data[i].id) {
	        					$("#tPrincipal").append(
	        							"<option value='" + data[i].id + "' selected >" + data[i].name
	        									+ "</option>");
	        				}
	        				else{$("#tPrincipal").append(
	        						"<option value='" + data[i].id + "'>" + data[i].name
	        								+ "</option>");
	        				}
	            }
	        }
	    });
	    
		
	  //加载参与人列表 插件需要特殊处理
	    var optionString = "";
	    $.ajax({
	        "type" : 'get',
	        "url": Feng.ctxPath + "/mgr/list",
	        "dataType" : "json",
	        "success" : function(data) {
	        	var arr = $("#tParticipationPer1").val().split(',');
	            for(var i = 0 ; i < data.length; i++ ){
	            	   var flag = "0";
	            	   $.each(arr,function(m,n){
	  	                 if(n==data[i].id){
	  	                	flag="1";
	  	                	return false;
	  	                 }
	  	              })
	  	              if("1"==flag){
	  	            	 optionString += "<option value='"+data[i].id+"' selected>"+data[i].name+"</option>";
	  	              }else{
	  	            	optionString += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
	  	              }
	            }
	            $("#tParticipationPer").html(optionString);
	            $("#tParticipationPer").selectpicker('refresh');
	        }
	    });
	    
	    $("#comite").hide();
	    if($("#participate").val()==1&&$("#tStatusFlag").val()!=3){
	    	 $("#comite").show();
	    }
	    
});
