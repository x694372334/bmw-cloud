/**
 * 初始化费项设置详情对话框
 */
var CostSetInfoDlg = {
    costSetInfoData : {},
    validateFields: {
    	costName: {
            validators: {
                notEmpty: {
                    message: '费项名称不能为空'
                },
                stringLength:{
                	 message:"最多输入20位",
                     min:"0",
                     max:"20"
                }
            }
        },
        costTypeId: {
            validators: {
                notEmpty: {
                    message: '请选择费项类别'
                }
            }
        },
        neighborhoodsId: {
            validators: {
                notEmpty: {
                    message: '请选择适用小区'
                }
            }
        },
        relevanceId: {
            validators: {
                notEmpty: {
                    message: '请选择关联对象'
                }
            }
        }
        
    }
};

/**
 * 清除数据
 */
CostSetInfoDlg.clearData = function() {
    this.costSetInfoData = {};
}

/**
 * 验证数据是否为空
 */
CostSetInfoDlg.validate = function () {
    $('#costSetForm').data("bootstrapValidator").resetForm();
    $('#costSetForm').bootstrapValidator('validate');
    return $("#costSetForm").data('bootstrapValidator').isValid();
};


/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CostSetInfoDlg.set = function(key, val) {
    this.costSetInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CostSetInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CostSetInfoDlg.close = function() {
    parent.layer.close(window.parent.CostSet.layerIndex);
}

/**
 * 收集数据
 */
CostSetInfoDlg.collectData = function() {
    this
    .set('id')
    .set('costName')
    .set('costTypeId')
    .set('relevanceId')
    .set('neighborhoodsId');
    this.costSetInfoData['costTypeName'] = $("#costTypeId").find("option:selected").text();
    this.costSetInfoData['relevanceName'] = $("#relevanceId").find("option:selected").text();
    this.costSetInfoData['neighborhoodsName'] = $("#neighborhoodsId").find("option:selected").text();

}

/**
 * 提交添加
 */
CostSetInfoDlg.addSubmit = function() {

	
    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/costSet/add", function(data){
        Feng.success("添加成功!");
        window.parent.CostSet.table.refresh();
        CostSetInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.costSetInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CostSetInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    
    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/costSet/update", function(data){
        Feng.success("修改成功!");
        window.parent.CostSet.table.refresh();
        CostSetInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.costSetInfoData);
    ajax.start();
}


$(function() {
	Feng.initValidator("costSetForm", CostSetInfoDlg.validateFields);
	
	//初始化费项类别
    $.ajax({
        "type" : 'get',
        "url": Feng.ctxPath + "/dict/getDic",
        "dataType" : "json",
        "data" : {"code" : "fxlb"},
        "success" : function(data) {
            for(var i = 0 ; i < data.length; i++ ){
            	if($("#costType").val() == data[i].num){
            		 $("#costTypeId").append("<option value='"+data[i].num+"' selected='selected'>"+data[i].name+"</option>");
            	}else{
            		$("#costTypeId").append("<option value='"+data[i].num+"'>"+data[i].name+"</option>");
            	}
                
            }
        }
    });
    
  //初始化适用小区
    $.ajax({
        "type" : 'get',
        "url": Feng.ctxPath + "/neighborhood/list",
        "dataType" : "json",
        "success" : function(data) {
            for(var i = 0 ; i < data.length; i++ ){
            	if($("#neighborhoods").val() == data[i].nId){
            		 $("#neighborhoodsId").append("<option value='"+data[i].nId+"' selected='selected'>"+data[i].nName+"</option>");
            	}else{
            		$("#neighborhoodsId").append("<option value='"+data[i].nId+"'>"+data[i].nName+"</option>");
            	}
                
            }
        }
    });
    
    //关联对象选中
    $("#relevanceId").val($("#relevance").val() == undefined ? "1" : $("#relevance").val());
    
    /**
     * 判断关联对象、适用小区是否能够修改
     */
	if($("#isEdit").val() == 'false'){
		$("#relevanceId").attr("disabled",true);
		$("#neighborhoodsId").attr("disabled",true);
    }
});

