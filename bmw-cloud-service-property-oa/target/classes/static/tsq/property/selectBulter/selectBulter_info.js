/**
 * 初始化广告位分包信息详情对话框
 */
var selectBulterDlg = {
    selectBulterData : {},
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
selectBulterDlg.clearData = function() {
    this.selectBulterData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
selectBulterDlg.set = function(key, val) {
    this.selectBulterData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
selectBulterDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
selectBulterDlg.close = function() {
    parent.layer.close(window.parent.Advertising.layerIndex);
}

/**
 * 收集数据
 */
selectBulterDlg.collectData = function() {
    this.set("aId").set("nId").set("cId").set("cBTime").set("cETime")
}

/**
 * 提交添加
 */
selectBulterDlg.addSubmit = function() {
	submit();
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/aCInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.ACInfo.table.refresh();
        selectBulterDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.selectBulterData);
    ajax.start();
}

/**
 * 提交修改
 */
selectBulterDlg.editSubmit = function() {
	submit();
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/aCInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.ACInfo.table.refresh();
        selectBulterDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.selectBulterData);
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
selectBulterDlg.contractorSubmit = function() {
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
	        selectBulterDlg.close();
	    },function(data){
	        Feng.error("添加失败!" + data.responseJSON.message + "!");
	    });
	    ajax.set(this.selectBulterData);
	    ajax.start();
}


/**
 * 解除赞助商
 */
selectBulterDlg.relieveSubmit = function() {
	 submit();
	 this.clearData();
	 this.collectData();
	 
	  if (!this.validate()) {
	        return;
	  }
	  
	//提交信息
	    var ajax = new $ax(Feng.ctxPath + "/aCInfo/update", function(data){
	        Feng.success("解除成功!");
	        window.parent.Advertising.table.refresh();
	        selectBulterDlg.close();
	    },function(data){
	        Feng.error("解除失败!" + data.responseJSON.message + "!");
	    });
	    ajax.set(this.selectBulterData);
	    ajax.start();
}

selectBulterDlg.validate = function () {
    $('#SelectBulterFrom').data("bootstrapValidator").resetForm();
    $('#SelectBulterFrom').bootstrapValidator('validate');
    return $("#SelectBulterFrom").data('bootstrapValidator').isValid();
};

$(function() {
	Feng.initValidator("SelectBulterFrom", selectBulterDlg.validateFields);
	contractor();
});
