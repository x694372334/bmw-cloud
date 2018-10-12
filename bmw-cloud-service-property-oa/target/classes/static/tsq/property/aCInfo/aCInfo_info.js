/**
 * 初始化广告位分包信息详情对话框
 */
var ACInfoInfoDlg = {
    aCInfoInfoData : {},
    validateFields: {
    	aContractor: {
	        validators: {
	            notEmpty: {
	                message: '小区名称不能为空'
	            }
	        }
	    }
       ,cBTime: {
	        validators: {
	            notEmpty: {
	                message: '开始时间不能为空'
	            }
	        }
	    },
	    cETime: {
	        validators: {
	            notEmpty: {
	                message: '结束时间不能为空'
	            }
	        }
	    },
    }
};

/**
 * 清除数据
 */
ACInfoInfoDlg.clearData = function() {
    this.aCInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ACInfoInfoDlg.set = function(key, val) {
    this.aCInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ACInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ACInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.Advertising.layerIndex);
}

/**
 * 收集数据
 */
ACInfoInfoDlg.collectData = function() {
    this.set("aId").set("nId").set("cId").set("cBTime").set("cETime").set("isSuccess")
}

/**
 * 提交添加
 */
ACInfoInfoDlg.addSubmit = function() {
	submit();
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/aCInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.ACInfo.table.refresh();
        ACInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.aCInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ACInfoInfoDlg.editSubmit = function() {
	submit();
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/aCInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.ACInfo.table.refresh();
        ACInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.aCInfoInfoData);
    ajax.start();
}

function contractor(){
	$("#aContractor").find("option").remove(); 
	data = $("#nId").val();
	 $.ajax({
			type:"POST",
			url:Feng.ctxPath+"/advertising/findContractor/"+data,
			dataType: "json",
			contentType: "application/json",
			catch:true,
			 success: function (data) {
	             for (var i = 0; i < data.length; i++) {
	            	 console.log(data);
	            	 if($("#cId").val() == data[i].cId){
		                 $("#aContractor").append("<option value='"+data[i].cId+"' selected>"+ data[i].cName +"</option>");
	            	 }else{
	 	                $("#aContractor").append("<option value='"+data[i].cId+"'>"+data[i].cName+"</option>");
	            	 }
	             }
			 }
		})
}

function submit(){
	var options=$("#aContractor option:selected");
	$("#cId").val(options.val());
}

/**
 * 提交赞助商
 */
ACInfoInfoDlg.contractorSubmit = function() {
	 submit();
	 this.clearData();
	 this.collectData();
	 
	  if (!this.validate()) {
	        return;
	  }
	  
	//提交信息
	    var ajax = new $ax(Feng.ctxPath + "/aCInfo/add", function(data){
	        Feng.success("添加成功!");
	        window.parent.Advertising.table.refresh();
	        ACInfoInfoDlg.close();
	    },function(data){
	        Feng.error("添加失败!" + data.responseJSON.message + "!");
	    });
	    ajax.set(this.aCInfoInfoData);
	    ajax.start();
}


function submitCId(){
	$("#cId").val($("#aContractor").val());
}

/**
 * 解除赞助商
 */
ACInfoInfoDlg.relieveSubmit = function() {
	 submit();
	 submitCId();
	 this.clearData();
	 this.collectData();
	 
	  if (!this.validate()) {
	        return;
	  }
	  
	//提交信息
	    var ajax = new $ax(Feng.ctxPath + "/aCInfo/update", function(data){
	        Feng.success("解除成功!");
	        window.parent.Advertising.table.refresh();
	        ACInfoInfoDlg.close();
	    },function(data){
	        Feng.error("解除失败!" + data.responseJSON.message + "!");
	    });
	    ajax.set(this.aCInfoInfoData);
	    ajax.start();
}

ACInfoInfoDlg.validate = function () {
    $('#ACInfoFrom').data("bootstrapValidator").resetForm();
    $('#ACInfoFrom').bootstrapValidator('validate');
    return $("#ACInfoFrom").data('bootstrapValidator').isValid();
};

$(function() {
	Feng.initValidator("ACInfoFrom", ACInfoInfoDlg.validateFields);
	contractor();
});
