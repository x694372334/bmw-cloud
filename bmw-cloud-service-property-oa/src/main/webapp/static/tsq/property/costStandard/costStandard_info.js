/**
 * 初始化收费标准详情对话框
 */
var CostStandardInfoDlg = {
    costStandardInfoData : {},
    validateFields: {
    	standardName: {
            validators: {
                notEmpty: {
                    message: '费用标准名称不能为空'
                },
                stringLength:{
                	 message:"最多输入20位",
                     min:"0",
                     max:"20"
                }
            }
        },
        costId: {
            validators: {
                notEmpty: {
                    message: '请选择费项名称'
                }
            }
        },
        amountFormulaId: {
            validators: {
                notEmpty: {
                    message: '请选择金额计算公式'
                }
            }
        },
        price: {
            validators: {
                notEmpty: {
                    message: '计费单价不能为空'
                },
                numeric:{
                	message:"必须输入数字",
                },
                regexp:{
               	 message:"最多输入两位小数",
               	 regexp:"^[0-9]+(.[0-9]{1,2})?$"
               },
               stringLength:{
              	 message:"最多输入12位",
                   min:"1",
                   max:"12"
              }
            }
        },
        fixedAmount:{
            validators: {
                notEmpty: {
                    message: '固定金额不能为空'
                },
                numeric:{
                	message:"必须输入数字",
                },
                regexp:{
               	 message:"最多输入两位小数",
               	 regexp:"^[0-9]+(.[0-9]{1,2})?$"
               },
               stringLength:{
              	 message:"最多输入12位",
                   min:"1",
                   max:"12"
              }
            }
        },
        meteringId: {
            validators: {
                notEmpty: {
                    message: '请选择计量方式'
                }
            }
        },
        period: {
            validators: {
                notEmpty: {
                    message: '收费周期不能为空'
                },
                regexp:{
                	 message:"月份不能大于12",
                	 regexp:"^(0?[1-9]|1[0-2])$"
                }
            }
        },
        lateFee: {
            validators: {
                numeric:{
                	message:"必须输入数字",
                },
               stringLength:{
              	 message:"最多输入3位",
                   min:"0",
                   max:"3"
              }
            }
        },
        scale: {
            validators: {
                numeric:{
                	message:"必须输入数字",
                },
                regexp:{
               	 message:"最多输入两位小数",
               	 regexp:"^[0-9]+(.[0-9]{1,2})?$"
               },
               stringLength:{
              	 message:"最多输入5位",
                   min:"0",
                   max:"5"
              }
            }
        }
    }
};

/**
 * 清除数据
 */
CostStandardInfoDlg.clearData = function() {
    this.costStandardInfoData = {};
}


/**
 * 验证数据是否为空
 */
CostStandardInfoDlg.validate = function () {
    $('#costStandardForm').data("bootstrapValidator").resetForm();
    $('#costStandardForm').bootstrapValidator('validate');
    return $("#costStandardForm").data('bootstrapValidator').isValid();
};


/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CostStandardInfoDlg.set = function(key, val) {
    this.costStandardInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CostStandardInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CostStandardInfoDlg.close = function() {
    parent.layer.close(window.parent.CostStandard.layerIndex);
}

/**
 * 收集数据
 */
CostStandardInfoDlg.collectData = function() {
	  this.costStandardInfoData['amountFormulaName'] = $("#amountFormulaId").find("option:selected").text();
	  this.costStandardInfoData['meteringName'] = $("#meteringId").find("option:selected").text();
	
	  if($("#costId").val()){
		  this.costStandardInfoData['costName'] = $("#costId").find("option:selected").text();
		  this.costStandardInfoData['costId'] = $("#costId").val().split(",")[0];
	  }
	  
    this
    .set('id')
    .set('standardName')
    .set('amountFormulaId')
    .set('meteringId')
    .set('fixedAmount')
    .set('price')
    .set('period')
    .set('lateFee')
    .set('scale');
    this.costStandardInfoData['defaultFeeId'] = $("input[name='defaultFeeId']:checked").val();
    this.costStandardInfoData['otherSet'] = $("input[name='otherSet']:checked").val();
}

/**
 * 提交添加
 */
CostStandardInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/costStandard/add", function(data){
        Feng.success("添加成功!");
        window.parent.CostStandard.table.refresh();
        CostStandardInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.costStandardInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CostStandardInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/costStandard/update", function(data){
        Feng.success("修改成功!");
        window.parent.CostStandard.table.refresh();
        CostStandardInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.costStandardInfoData);
    ajax.start();
}



var is_edit = true;
CostStandardInfoDlg.relevanceChange = function(){
	$("#amountFormulaId").empty();
	$.ajax({
	    "type" : 'get',
	    "url": Feng.ctxPath + "/dict/getDic",
	    "dataType" : "json",
	    "data" : {"code" : "jejsgs"},
	    "async" : false,
	    "success" : function(data) {
	        for(var i = 0 ; i < data.length; i++ ){
	        	$("#amountFormulaId").append("<option value='"+data[i].num+"'>"+data[i].name+"</option>");
	        }
	    }
	});
	
	if($("#costId").val()){
		var object_no = $("#costId").val().split(",")[1];
		if(object_no == 1){
			$("#amountFormulaId option[value='1']").remove();
		}else if(object_no == 2){
			$("#amountFormulaId option[value='1']").remove();
		}else if(object_no == 3){
			$("#amountFormulaId option[value='0']").remove();
			$("#amountFormulaId option[value='1']").remove();
		}else if(object_no == 4){
			$("#amountFormulaId option[value='0']").remove();
		}else if(object_no == 5){
			$("#amountFormulaId option[value='0']").remove();
		}
	}
	var options = $("#amountFormulaId").find("option");
	options.first().attr("selected", true);
	
	if($("#amountFormula").val() && is_edit){
		$("#amountFormulaId").val($("#amountFormula").val());
		is_edit =false;
	}
		
	CostStandardInfoDlg.change();
}



CostStandardInfoDlg.change = function(){
	$("#meteringId").empty();
	$.ajax({
        "type" : 'get',
        "url": Feng.ctxPath + "/dict/getDic",
        "dataType" : "json",
        "data" : {"code" : "jlfs"},
        "async" : false,
        "success" : function(data) {
        	$("#meteringId").empty();
            for(var i = 0 ; i < data.length; i++ ){
           		$("#meteringId").append("<option value='"+data[i].num+"'>"+data[i].name+"</option>");
            }
        }
    });
	var amountFormula = $("#amountFormulaId").val();
	if(amountFormula){
		if(amountFormula == 0){
			if($("#costId").val()){
				var object_no = $("#costId").val().split(",")[1];
				if(object_no == 1){
					$("#meteringId option[value='4']").remove();
					$("#meteringId option[value='5']").remove();
				}else if(object_no == 2){
					$("#meteringId option[value='1']").remove();
					$("#meteringId option[value='2']").remove();
					$("#meteringId option[value='3']").remove();
					$("#meteringId option[value='5']").remove();
				}
			}
		}
		if(amountFormula == 1){
			$("#meteringId option[value='1']").remove();
			$("#meteringId option[value='2']").remove();
			$("#meteringId option[value='3']").remove();
			$("#meteringId option[value='4']").remove();
		}
		if(amountFormula == 2){
			$("#meteringId").empty();
			 $("#isShow").hide();
			 $("#isShowPrice").hide();
			 $("#isShowFixedAmount").show();
		 }else{
			 $("#isShow").show();
			 $("#isShowPrice").show();
			 $("#isShowFixedAmount").hide();
		 }
	}
	var options = $("#meteringId").find("option");
	options.first().attr("selected", true);
	if($("#metering").val() && is_edit){
		$("#meteringId").val($("#metering").val());
	}
	
}

$(function() {
	Feng.initValidator("costStandardForm", CostStandardInfoDlg.validateFields);
	
	//tip提示
	$("[data-toggle='tooltip']").tooltip();
	CostStandardInfoDlg.change($("#amountFormulaId"));
	//金额计算方式绑定事件
	$("#amountFormulaId").bind("change",function(){
		CostStandardInfoDlg.change();
	});
	
	$("#costId").bind("change",function(){
		CostStandardInfoDlg.relevanceChange();
	});
	
	
			
	//选中radio
	$(":radio[name='otherSet'][value='" + $("#otherSetTemp").val() + "']").prop("checked", "checked");
	$(":radio[name='defaultFeeId'][value='" + $("#defaultFee").val() + "']").prop("checked", "checked");
      
	//获取费项设置下拉列表
	$.ajax({
        "type" : 'get',
        "url": Feng.ctxPath + "/costSet/getCostSetAll",
        "dataType" : "json",
        "async" : false,
        "success" : function(data) {
            for(var i = 0 ; i < data.length; i++ ){
            	if($("#cost").val() == data[i].id){
            		$("#costId").append("<option value='"+data[i].id+","+data[i].relevanceId+"' selected='selected'>"+data[i].costName+"("+ data[i].neighborhoodsName +")" +"</option>");
            	}else{
            		$("#costId").append("<option value='"+data[i].id+","+data[i].relevanceId+"'>"+data[i].costName+"("+ data[i].neighborhoodsName +")" +"</option>");
            	}
                
            }
        }
    });
	
	//获取金额计算公司字典
	 $.ajax({
	        "type" : 'get',
	        "url": Feng.ctxPath + "/dict/getDic",
	        "dataType" : "json",
	        "data" : {"code" : "jejsgs"},
	        "async" : false,
	        "success" : function(data) {
	            for(var i = 0 ; i < data.length; i++ ){
	            		$("#amountFormulaId").append("<option value='"+data[i].num+"'>"+data[i].name+"</option>");
	            }
	        }
	    });
	 
	 //获取计量方式下拉列表
	 $.ajax({
	        "type" : 'get',
	        "url": Feng.ctxPath + "/dict/getDic",
	        "dataType" : "json",
	        "data" : {"code" : "jlfs"},
	        "async" : false,
	        "success" : function(data) {
	        	$("#meteringId").empty();
	            for(var i = 0 ; i < data.length; i++ ){
	            		$("#meteringId").append("<option value='"+data[i].num+"'>"+data[i].name+"</option>");
	            }
	        }
	    });
	 
	 CostStandardInfoDlg.relevanceChange();
	
});
