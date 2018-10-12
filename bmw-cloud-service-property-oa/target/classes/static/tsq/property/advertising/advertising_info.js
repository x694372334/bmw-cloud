/**
 * 初始化广告位信息详情对话框
 */
var AdvertisingInfoDlg = {
    advertisingInfoData : {},
    validateFields: {
    	aName: {
	        validators: {
	            notEmpty: {
	                message: '广告位名称不能为空'
	            },
	            stringLength: {
                    min: 0,
                    max: 50,
                    message: '超出长度'
		        }
	        }
	    },
	    aType: {
	        validators: {
	            notEmpty: {
	                message: '单元不能为空'
	            }
	        }
	    },
	    aStatus: {
	        validators: {
	            notEmpty: {
	                message: '广告位状态不能为空'
	            },
	            stringLength: {
                    min: 0,
                    max: 11,
                    message: '超出长度'
		        }
	        }
	    },
	    aPhone: {
	        validators: {
	            notEmpty: {
	                message: '电话号码不能为空'
	            },
	            stringLength: {
                    min: 0,
                    max: 10,
                    message: '单元超出长度'
		        }
	        }
	    }
    }
};

/**
 * 清除数据
 */
AdvertisingInfoDlg.clearData = function() {
    this.advertisingInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AdvertisingInfoDlg.set = function(key, val) {
    this.advertisingInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AdvertisingInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AdvertisingInfoDlg.close = function() {
    parent.layer.close(window.parent.Advertising.layerIndex);
}

/**
 * 收集数据
 */
AdvertisingInfoDlg.collectData = function() {
    this
    .set("aId")
    .set("nId")
    .set("aName")
    .set("aType")
    .set("aStatus")
    .set("aContractor")
    .set("aPhone")
}

function submit(){
	var options=$("#test option:selected");
	$("#nId").val(options.val())
}

/**
 * 提交添加
 */
AdvertisingInfoDlg.addSubmit = function() {
	submit();
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/advertising/add", function(data){
        Feng.success("添加成功!");
        window.parent.Advertising.table.refresh();
        AdvertisingInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.advertisingInfoData);
    ajax.start();
}

AdvertisingInfoDlg.contractorSubmit = function (){
}

/**
 * 字典表：广告位类型
 * 开发者：金明禹
 * */

function rRoomType(){
	 $.ajax({
	        "type" : 'get',
	        "url": Feng.ctxPath + "/dict/getDic",
	        "dataType" : "json",
	        "data" : {"code" : "ggwlz"},
	        "success" : function(data) {
	            for(var i = 0 ; i < data.length; i++ ){
	            	if($("#aType").val() == data[i].num){
	            		 $("#aType").append("<option value='"+data[i].num+"' selected='selected'>"+data[i].name+"</option>");
	            	}else{
	            		$("#aType").append("<option value='"+data[i].num+"'>"+data[i].name+"</option>");
	            	}
	                
	            }
	        }
	    });
}
/**
 * 字典表：广告位状态
 * 开发者：金明禹
 * */

function aStatus(){
	 $.ajax({
	        "type" : 'get',
	        "url": Feng.ctxPath + "/dict/getDic",
	        "dataType" : "json",
	        "data" : {"code" : "ggwzt"},
	        "success" : function(data) {
	            for(var i = 0 ; i < data.length; i++ ){
	            	if($("#aStatus").val() == data[i].num){
	            		 $("#aStatus").append("<option value='"+data[i].num+"' selected='selected'>"+data[i].name+"</option>");
	            	}else{
	            		$("#aStatus").append("<option value='"+data[i].num+"'>"+data[i].name+"</option>");
	            	}
	                
	            }
	        }
	    });
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
		                 $("#test").append("<option value='"+data[i].nId+"' selected>"+ data[i].nName +"</option>");
	            	 }else{
	 	                $("#test").append("<option value='"+data[i].nId+"'>"+data[i].nName+"</option>");
	            	 }
	             }
			 }
		})
}



AdvertisingInfoDlg.validate = function () {
    $('#advertisingInfoForm').data("bootstrapValidator").resetForm();
    $('#advertisingInfoForm').bootstrapValidator('validate');
    return $("#advertisingInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交修改
 */
AdvertisingInfoDlg.editSubmit = function() {
	submit();
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/advertising/update", function(data){
        Feng.success("修改成功!");
        window.parent.AdvertisingInfoDlg.table.refresh();
        AdvertisingInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.advertisingInfoData);
    ajax.start();
}

/**
 * 提交赞助商
 */
AdvertisingInfoDlg.contractorSubmit = function() {
}

$(function() {
	 Feng.initValidator("advertisingInfoForm", AdvertisingInfoDlg.validateFields);
	rRoomType();
	neighborhood();
	aStatus();
	contractor();

});
