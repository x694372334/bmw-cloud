/**
 * 账单明细管理初始化
 */
var Bill = {
	id : "BillTable", // 表格id
	seItem : null, // 选中的条目
	table : null,
	layerIndex : -1,
	validateFields : {
		discountsAmount : {
			validators : {
				notEmpty : {
					message : '不能为空'
				},
				numeric : {
					message : "必须输入数字",
				},
				regexp : {
					message : "最多输入两位小数",
					regexp : "^[0-9]+(.[0-9]{1,2})?$"
				}
			}
		},
		latenessOffer : {
			validators : {
				notEmpty : {
					message : '不能为空'
				},
				numeric : {
					message : "必须输入数字",
				},
				regexp : {
					message : "最多输入两位小数",
					regexp : "^[0-9]+(.[0-9]{1,2})?$"
				}
			}
		}
	}
};

/**
 * 验证数据
 */
Bill.validate = function () {
    $('#discountsform').data("bootstrapValidator").resetForm();
    $('#discountsform').bootstrapValidator('validate');
    return $("#discountsform").data('bootstrapValidator').isValid();
};

/**
 * 通过审批
 */
Bill.pass = function() {
	var taskId = $("#taskId").val();
	var billId = $("#billId").val();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bill/discountsPass/"+taskId+"/"+billId, function(data){
        Feng.success("审批成功!");
        window.parent.RuTask.table.refresh();
        parent.layer.close(window.parent.RuTask.layerIndex);
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.start();
}

/**
 * 终止审批
 */
Bill.end = function() {
	var taskId = $("#taskId").val();
	var billId = $("#billId").val();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bill/discountsEnd/"+taskId+"/"+billId, function(data){
        Feng.success("成功!");
        window.parent.RuTask.table.refresh();
        parent.layer.close(window.parent.RuTask.layerIndex);
    },function(data){
        Feng.error("失败!" + data.responseJSON.message + "!");
    });
    ajax.start();
}



Bill.commit = function(){
	if (!this.validate()) {
        return;
    }
	$.ajax({  
        type: "get",  
        dataType: "json",  
        url: Feng.ctxPath + '/bill/discountsCommit?billId='+$("#billId").val()+"&reason="+$("#reason").val()+"&discountsAmount="+$("#discountsAmount").val()+"&latenessOffer="+$("#latenessOffer").val(),
        success: function (msg) { 
        	if("200"==msg.code){
        		Feng.success("申请成功!");
                window.parent.Bill.table.refresh();
                parent.layer.close(window.parent.Bill.layerIndex);
        	}else{
        		Feng.error("申请失败!");
        	}
        },  
        error: function () {  
        	Feng.error("申请失败!");
        }  
    }); 
	
}
    
$(function() {
	if(undefined != window.parent.RuTask){
	   if( "view"==window.parent.RuTask.type){
		   $("button").hide();
	   }
	}
	Feng.initValidator("discountsform", Bill.validateFields);
})

//小数相加
function accAdd(arg1, arg2) {  
    var r1, r2, m, c;  
    try {  
        r1 = arg1.toString().split(".")[1].length;  
    }  
    catch (e) {  
        r1 = 0;  
    }  
    try {  
        r2 = arg2.toString().split(".")[1].length;  
    }  
    catch (e) {  
        r2 = 0;  
    }  
    c = Math.abs(r1 - r2);  
    m = Math.pow(10, Math.max(r1, r2));  
    if (c > 0) {  
        var cm = Math.pow(10, c);  
        if (r1 > r2) {  
            arg1 = Number(arg1.toString().replace(".", ""));  
            arg2 = Number(arg2.toString().replace(".", "")) * cm;  
        } else {  
            arg1 = Number(arg1.toString().replace(".", "")) * cm;  
            arg2 = Number(arg2.toString().replace(".", ""));  
        }  
    } else {  
        arg1 = Number(arg1.toString().replace(".", ""));  
        arg2 = Number(arg2.toString().replace(".", ""));  
    }  
    return (arg1 + arg2) / m;  
}  

