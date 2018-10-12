/**
 * 初始化楼宇管理详情对话框
 */
var BuildingInfoInfoDlg = {
    buildingInfoInfoData : {},
	validateFields: {
		 bName: {
		        validators: {
		            notEmpty: {
		                message: '楼宇名称不能为空'
		            },
		            stringLength: {
	                    min: 0,
	                    max: 50,
	                    message: '楼宇名称超出长度'
			        }
		        }
		    },
		    bUnitCount: {
		        validators: {
		            notEmpty: {
		                message: '单元数量不能为空'
		            },
		            regexp: {
			            regexp: /^[0-9]*$/,
			            message: '只能输入数字'
			        },
			        stringLength:{
			        min: 0,
	                max: 5,
	                message: '不能超过五位数'
	                }
			        
		        }
		    },
		    bFloors: {
		        validators: {
		            notEmpty: {
		                message: '楼宇层数不能为空'
		            },
		            regexp: {
			            regexp: /^[0-9]*$/,
			            message: '只能输入数字'
			        },
			        stringLength:{
			        min: 0,
	                max: 2,
	                message: '不能超过2位数'
	                }
			        
		        }
		    },
		    bOrientation: {
		        validators: {
		            notEmpty: {
		                message: '楼宇朝向不能为空'
		            }
		        }
		    }
	}
};

/**
 * 清除数据
 */
BuildingInfoInfoDlg.clearData = function() {
    this.buildingInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BuildingInfoInfoDlg.set = function(key, val) {
    this.buildingInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BuildingInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BuildingInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.BuildingInfo.layerIndex);
}

/**
 * 收集数据
 */
BuildingInfoInfoDlg.collectData = function() {
    this.set("bId").set("bCode").set("nId").set("bName").set("bUnitCount").set("bFloors").set("bType").set("bOrientation").set("remark")
    .set("createManId").set("createMan").set("createTime").set("editMan").set("editTime").set("isDelete")
}

/**
 * 提交添加
 */
BuildingInfoInfoDlg.addSubmit = function() {
	submitSelect();
    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/buildingInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.BuildingInfo.table.refresh();
        BuildingInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.buildingInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BuildingInfoInfoDlg.editSubmit = function() {
	submitSelect();
    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/buildingInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.BuildingInfo.table.refresh();
        BuildingInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.buildingInfoInfoData);
    ajax.start();
}


function neighborhood(){
	var options=$("#test option:selected");
	 $.ajax({
			type:"POST",
			url:Feng.ctxPath+"/buildingInfo/findNeighbor",
			dataType: "json",
			contentType: "application/json",
			catch:true,
			 success: function (data) {
				 var optionstring = "";
	             for (var i = 0; i < data.length; i++) {
	            	 if($("#nId").val() == data[i].nId){
		                 $("#test").append("<option value='"+data[i].nCode+","+data[i].nId+"' selected>"+ data[i].nName +"</option>");
	            	 }else{
	 	                $("#test").append("<option value='"+data[i].nCode+","+data[i].nId+"'>"+data[i].nName+"</option>");
	            	 }
	             }
			 }
		})
}
//BuildingInfoInfoDlg.change = function () {
//	var options=$("#test option:selected");
//	$("#bCode").val(options.val());
//}

function submitSelect(){
	var options=$("#test option:selected");
	$("#bCode").val(options.val());
	var options1=$("#text option:selected");
	$("#bType").val(options1.val());
}


BuildingInfoInfoDlg.validate = function () {
    $('#buildingInfoForm').data("bootstrapValidator").resetForm();
    $('#buildingInfoForm').bootstrapValidator('validate');
    return $("#buildingInfoForm").data('bootstrapValidator').isValid();
};

function bType(){
	var options=$("#bType option:selected");
	 $.ajax({
	        "type" : 'get',
	        "url": Feng.ctxPath + "/dict/getDic",
	        "dataType" : "json",
	        "data" : {"code" : "lylx"},
	        "success" : function(data) {
	            for(var i = 0 ; i < data.length; i++ ){
	            	if($("#bType").val() == data[i].num){
	            		 $("#text").append("<option value='"+data[i].num+"' selected='selected'>"+data[i].name+"</option>");
	            	}else{
	            		$("#text").append("<option value='"+data[i].num+"'>"+data[i].name+"</option>");
	            	}
	                
	            }
	        }
	    });
}

$(function() {
	 Feng.initValidator("buildingInfoForm", BuildingInfoInfoDlg.validateFields);
	neighborhood();
    // 初始化头像上传
    var avatarUp = new $WebUpload("nImages");
    avatarUp.setUploadBarId("progressBar");
    avatarUp.init();
    bType();
	
});
