/**
 * 初始化小区信息详情对话框
 */
var NeighborhoodInfoInfoDlg = {
    neighborhoodInfoInfoData : {},
	validateFields: {
	    nName: {
	        validators: {
	            notEmpty: {
	                message: '小区名称不能为空'
	            },
	            stringLength: {
                    min: 0,
                    max: 50,
                    message: '小区名称超出长度'
		        }
	        }
	    },
	    serviceTel: {
	        validators: {
	            notEmpty: {
	                message: '客服电话不能为空'
	            },
	            regexp: {
		            regexp: /^[0-9]*$/,
		            message: '只能输入数字'
		        },
		        stringLength:{
		        min: 0,
                max: 12,
                message: '请输入0-12位座机号码'
                }
		        
	        }
	    },
	    pInCharge: {
	        validators: {
	            notEmpty: {
	                message: '物业负责人不能为空'
	            },
	            stringLength: {
                    min: 0,
                    max: 32,
                    message: '名称超出长度'
		        }
	        }
	    },
	    pICTel: {
	        validators: {
	            notEmpty: {
	                message: '物业负责人电话不能为空'
	            },
	            stringLength: {
                    min: 11,
                    max: 11,
                    message: '请输入11位手机号码'
		           },
		           regexp: {
		            regexp: /^[0-9]*$/,
		            message: '只能输入数字'
		           }
	        }
	    },
	    nRoomTime: {
	        validators: {
	            notEmpty: {
	                message: '交房时间不能为空'
	            }
	        }
	    },
	    nCoveredArea: {
	        validators: {
	            notEmpty: {
	                message: '占地面积不能为空'
	            },
	            regexp: {
		            regexp: /^[0-9]+(.[0-9]{2})?$/,
		            message: '小数点超出'
		        },
		        stringLength: {
                    min: 0,
                    max: 10,
                    message: '超出长度'
		        }
	        }
	    },
	    nArchitectureArea: {
	        validators: {
	            notEmpty: {
	                message: '建筑面积不能为空'
	            },
	            regexp: {
		            regexp: /^[0-9]+(.[0-9]{2})?$/,
		            message: '小数点超出'
		        },
		        stringLength: {
                    min: 0,
                    max: 10,
                    message: '超出长度'
		        }
	        }
	    },
	    nPublicArea: {
	        validators: {
	            notEmpty: {
	                message: '公共场所不能为空'
	            },
	            regexp: {
		            regexp: /^[0-9]+(.[0-9]{2})?$/,
		            message: '小数点超出'
		        },
		        stringLength: {
                    min: 0,
                    max: 10,
                    message: '超出长度'
		        }
	        }
	    },
	    nGreenArea: {
	        validators: {
	            notEmpty: {
	                message: '绿化面积不能为空'
	            },
	            regexp: {
		            regexp: /^[0-9]+(.[0-9]{2})?$/,
		            message: '小数点超出'
		        },
		        stringLength: {
                    min: 0,
                    max: 10,
                    message: '超出长度'
		        }
	        }
	    },
	    cArea: {
	        validators: {
	            notEmpty: {
	                message: '车库面积不能为空'
	            },
	            regexp: {
		            regexp: /^[0-9]+(.[0-9]{2})?$/,
		            message: '格式不正确 例如：12.20'
		        },
		        stringLength: {
                    min: 0,
                    max: 10,
                    message: '超出长度'
		        }
	        }
	    },
	    nAddress: {
	        validators: {
	            notEmpty: {
	                message: '小区地址不能为空'
	            },
	            stringLength: {
                    min: 0,
                    max: 255,
                    message: '超出长度'
		        }
	        }
	    }
	}
};

/**
 * 清除数据
 */
NeighborhoodInfoInfoDlg.clearData = function() {
    this.neighborhoodInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NeighborhoodInfoInfoDlg.set = function(key, val) {
    this.neighborhoodInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
NeighborhoodInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
NeighborhoodInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.NeighborhoodInfo.layerIndex);
}

/**
 * 收集数据
 */
NeighborhoodInfoInfoDlg.collectData = function() {
    this.set("nId").set("nCode").set("eId").set("nName").set("adName").set("adCode").set("nAddress").set("nDescription").set("serviceTel").set("pInCharge").set("pICTel").set("nRoomTime").set("nCoveredArea").set("nArchitectureArea").set("nPublicArea").set("nGreenArea").set("cArea").set("nImages").set("remarks").set("createManId").set("createMan").set("createTime").set("editManId").set("editMan").set("editTime").set("isDelete")
}

NeighborhoodInfoInfoDlg.validate = function () {
    $('#neighborhoodInfoForm').data("bootstrapValidator").resetForm();
    $('#neighborhoodInfoForm').bootstrapValidator('validate');
    return $("#neighborhoodInfoForm").data('bootstrapValidator').isValid();
};

function submit(){
	var options=$("#eId option:selected");
	$("#eId").val(options.val());
}

/**
 * 提交添加
 */
NeighborhoodInfoInfoDlg.adminAddSubmit = function() {
	submit();
    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/neighborhood/add", function(data){
        Feng.success("添加成功!");
        window.parent.NeighborhoodInfo.table.refresh();
        NeighborhoodInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.neighborhoodInfoInfoData);
    ajax.start();
}

/**
 * 提交添加
 */
NeighborhoodInfoInfoDlg.addSubmit = function() {
    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    if($("#data").val()=="0"){
		   	 $.ajax({
		   			type:"POST",
		   			url:Feng.ctxPath+"/buildingInfo/findNeighbor",
		   			dataType: "json",
		   			contentType: "application/json",
		   			catch:true,
		   			 success: function (data) {
		   				 var isSuccess = "0";
		   	             for (var i = 0; i < data.length; i++) {
			   	            	 if(data[i].nName==$("#nName").val()){
			   	            		isSuccess = "1"
			   	            		Feng.error("添加失败!" + "小区重名" + "!");
			   	            		return ;
			   	            	 }
		   	            	 }
		   	          //提交信息
		   	             if(isSuccess == "0"){
		   	              var ajax = new $ax(Feng.ctxPath + "/neighborhood/add", function(data){
	   	            	        Feng.success("添加成功!");
	   	            	        window.parent.NeighborhoodInfo.table.refresh();
	   	            	        NeighborhoodInfoInfoDlg.close();
	   	            	    },function(data){
	   	            	        Feng.error("添加失败!" + data.responseJSON.message + "!");
	   	            	    });
	   	            	    ajax.set(NeighborhoodInfoInfoDlg.neighborhoodInfoInfoData);
	   	            	    ajax.start(); 
		   	             }
		   	             }
		   			 })
		   		}else{
		   			Feng.error("添加失败!" + "一个物业只能添加一个小区" + "!");
		   			return ;
		   		}
    }
	
	

/**
 * 提交修改
 */
NeighborhoodInfoInfoDlg.editSubmit = function() {
	
    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
	 $.ajax({
			type:"POST",
			url:Feng.ctxPath+"/buildingInfo/findNeighborUpdate/"+ $("#nId").val(),
			dataType: "json",
			contentType: "application/json",
			catch:true,
			 success: function (data) {
				 console.log(data);
				 var isSuccess = "0";
	             for (var i = 0; i < data.length; i++) {
	   	            	 if(data[i].nName==$("#nName").val()){
	   	            		isSuccess = "1"
	   	            		Feng.error("添加失败!" + "小区重名" + "!");
	   	            		return ;
	   	            	 }
	            	 }
	          //提交信息
	             if(isSuccess == "0"){
	            	  var ajax = new $ax(Feng.ctxPath + "/neighborhood/update", function(data){
	            	        Feng.success("修改成功!");
	            	        window.parent.NeighborhoodInfo.table.refresh();
	            	        NeighborhoodInfoInfoDlg.close();
	            	    },function(data){
	            	        Feng.error("修改失败!" + data.responseJSON.message + "!");
	            	    });
	            	    ajax.set(NeighborhoodInfoInfoDlg.neighborhoodInfoInfoData);
	            	    ajax.start();
	             }
	             }
			 })
    
    //提交信息
//    var ajax = new $ax(Feng.ctxPath + "/neighborhood/update", function(data){
//        Feng.success("修改成功!");
//        window.parent.NeighborhoodInfo.table.refresh();
//        NeighborhoodInfoInfoDlg.close();
//    },function(data){
//        Feng.error("修改失败!" + data.responseJSON.message + "!");
//    });
//    ajax.set(this.neighborhoodInfoInfoData);
//    ajax.start();
}

NeighborhoodInfoInfoDlg.change=function(e){
	var options=$("#city option:selected");
	 $.ajax({
			type:"GET",
			url:Feng.ctxPath+"/neighborhood/findByAddress/{2,"+$(e).val()+"}",
			dataType: "json",
			contentType: "application/json",
			cache:true,
			 success: function (data) {
				 var optionstring = "";
	             for (var i = 0; i < data.length; i++) {
	                 optionstring += "<option value=\"" + data[i].d_code + "\" >" + data[i].d_value + "</option>";
	                 $("#test1").html("<option value='请选择' selected>请选择...</option> "+optionstring);
	             }
			 }
		})
}

function Eid(){
	var options=$("#eId option:selected");
	 $.ajax({
			type:"POST",
			url:Feng.ctxPath+"/neighborhood/findEid",
			dataType: "json",
			contentType: "application/json",
			catch:true,
			 success: function (data) {
				 console.log(data);
				 var optionstring = "";
	             for (var i = 0; i < data.length; i++) {
	            	 if($("#eId").val() == data[i].nId){
		                 $("#eId").append("<option value='"+data[i].eId+"' selected>"+ data[i].enterpriseName +"</option>");
	            	 }else{
	 	                $("#eId").append("<option value='"+data[i].eId+"'>"+data[i].enterpriseName+"</option>");
	            	 }
	             }
			 }
		})
}

NeighborhoodInfoInfoDlg.change1=function(e){
	var options=$("#city option:selected");
	 $.ajax({
			type:"GET",
			url:Feng.ctxPath+"/neighborhood/findByAddress/{3,"+$(e).val()+"}",
			dataType: "json",
			contentType: "application/json",
			catch:true,
			 success: function (data) {
				 var optionstring = "";
	             for (var i = 0; i < data.length; i++) {
	                 optionstring += "<option value=\"" + data[i].d_code + "\" >" + data[i].d_value + "</option>";
	                 $("#test2").html("<option value='请选择' selected>请选择...</option> "+optionstring);
	             }
			 }
		})
}

NeighborhoodInfoInfoDlg.change2=function(e){
	var options=$("#test option:selected");
	var options1=$("#test1 option:selected");
	var options2=$("#test2 option:selected");
	$("#adName").val(options.text()+options1.text()+options2.text())
	$("#adCode").val(options2.val())
}

/**
 * 省市区联动
 * */
function city(){
	 $.ajax({
			type:"GET",
			url:Feng.ctxPath+"/neighborhood/findByAddress/1",
			dataType: "json",
			contentType: "application/json",
			catch:true,
			 success: function (data) {
				 var optionstring = "";
	             for (var i = 0; i < data.length; i++) {
	                 optionstring += "<option value=\"" + data[i].d_code + "\" >" + data[i].d_value + "</option>";
	                 if($("#adCode").val()!=null && $("#adCode").val() != "" && $("#adCode").val()!="undefined"){
	                	var s = $("#adCode").val().substring(0,2)+"0000";
	             		var s1 = $("#adName").val().substring(0,3);
	             		$("#test").html("<option value='"+s+"' selected>"+s1+"</option>"+optionstring);
	             		var s = $("#adCode").val().substring(0,4)+"00";
	             		var s1 = $("#adName").val().substring(3,6);
	             		$("#test1").html("<option value='"+s+"' selected>"+s1+"</option>");
	             		var s = $("#adCode").val();
	             		var s1 = $("#adName").val().substring(6,9);
	             		$("#test2").html("<option value='"+s+"' selected>"+s1+"</option>");
	                 }else{
	                	 $("#test").html("<option value='请选择' selected>请选择...</option> "+optionstring);
	                 }
	             }
			 }
		})
}

$(function() {
	$("#test").bind("change",function(){
		NeighborhoodInfoInfoDlg.change(this);
	});
	 $("#test1").bind("change",function(){
			NeighborhoodInfoInfoDlg.change1(this);
	});
	 $("#test2").bind("change",function(){
			NeighborhoodInfoInfoDlg.change2(this);
	});
	 Feng.initValidator("neighborhoodInfoForm", NeighborhoodInfoInfoDlg.validateFields);
	    // 初始化头像上传
	    var avatarUp = new $WebUpload("nImages");
	    avatarUp.setUploadBarId("progressBar");
	    avatarUp.init();
	    city();
	    Eid();
});
