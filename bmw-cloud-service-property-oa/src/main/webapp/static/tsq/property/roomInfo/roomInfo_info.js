/**
 * 初始化房屋管理详情对话框
 */
var RoomInfoInfoDlg = {
    roomInfoInfoData : {},
    validateFields: {
    	rUnitnum: {
		        validators: {
		            notEmpty: {
		                message: '单元不能为空'
		            },
		            regexp: {
			            regexp: /^[0-9]*$/,
			            message: '只能输入数字'
			        }
//			        between:{
//			        	min:1,
//			        	max:5,
//			        	message:"超出单元数量"
//			        }
//		            stringLength: {
//	                    min: 0,
//	                    max: 10,
//	                    message: '单元超出长度'
//			        }
		        }
		    },
		    rFloor: {
		        validators: {
		            notEmpty: {
		                message: '楼层不能为空'
		            },
		            regexp: {
			            regexp: /^[0-9]*$/,
			            message: '只能输入数字'
			        },
//			        between:{
//			        	min:1,
//			        	max:12,
//			        	message:"超出楼层层数"
//			        }
			        stringLength:{
			        min: 0,
	                max: 2,
	                message: '不能超过2位数'
	                }
			        
		        }
		    },
		    rRoomnum: {
		        validators: {
		            notEmpty: {
		                message: '房间号不能为空'
		            },
			        stringLength:{
			        min: 0,
	                max: 10,
	                message: '不能超过2位数'
	                }
		        }
		    },
		    rCoveredArea: {
		        validators: {
		            notEmpty: {
		                message: '建筑面积不能为空'
		            }
		        }
		    },
		    rUsearea: {
		        validators: {
		            notEmpty: {
		                message: '套内面积不能为空'
		            }
		        }
		    },
		    rPublicArea: {
		        validators: {
		            notEmpty: {
		                message: '公摊面积不能为空'
		            }
		        }
		    },
/*
		    rHeight: {
		        validators: {
		            notEmpty: {
		                message: '房屋举架不能为空'
		            },
			        stringLength:{
			        min: 0,
	                max: 2,
	                message: '不能超过3位数'
	                }
		        }
		    },
*/
		    houseType: {
		        validators: {
		            notEmpty: {
		                message: '户型不能为空'
		            },
			        stringLength:{
			        min: 0,
	                max: 50,
	                message: '长度超出'
	                }
		        }
		    }
    }
};

/**
 * 清除数据
 */
RoomInfoInfoDlg.clearData = function() {
    this.roomInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RoomInfoInfoDlg.set = function(key, val) {
    this.roomInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RoomInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
RoomInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.RoomInfo.layerIndex);
}

/**
 * 收集数据
 */
RoomInfoInfoDlg.collectData = function() {
    this.set("rId")
    .set("rCode")
    .set("nId")
    .set("bId")
    .set("rUnitnum")
    .set("rFloor")
    .set("rRoomnum")
    .set("rCoveredArea")
    .set("rUsearea")
    .set("rPublicArea")
    .set("rRoomType")
    .set("rHeight")
    .set("houseType")
    .set("liveStatus")
    .set("stewardId")
    .set("stewardName")
    .set("createManId")
    .set("createMan")
    .set("createTime")
    .set("editManId")
    .set("editMan")
    .set("editTime")
    .set("isDelete")
    .set("taskId")
}

/**
 * 提交添加
 */
RoomInfoInfoDlg.addSubmit = function() {
	submitSelect();
    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }
    
    var options=$("#test option:selected");
    var data = options.val().split(",");
    $.ajax({
		type:"POST",
		url:Feng.ctxPath+"/roomInfo/findBuildingData/"+data[1]+"",
		dataType: "json",
		contentType: "application/json",
		catch:true,
		 success: function (data) {
			 console.log(data);
//			 for(var i = 0 , len = data.length ; i < len; i++ ){
				 if($("#rUnitnum").val()>data[0].bUnitCount){
					 Feng.error("单元数量超出楼宇设定数量，当前单元数量："+data[0].bUnitCount+"单元!");
					 return ;
				 }else if($("#rFloor").val()>data[0].bFloors){
					 Feng.error("楼层数量超出层数，当前楼层最高层数："+data[0].bFloors+"层!");
					 return ;
				 }else{
					    //提交信息
					    var ajax = new $ax(Feng.ctxPath + "/roomInfo/add", function(data){
					        Feng.success("添加成功!");
					        window.parent.RoomInfo.table.refresh();
					        RoomInfoInfoDlg.close();
					    },function(data){
					        Feng.error("添加失败!" + data.responseJSON.message + "!");
					    });
					    ajax.set(RoomInfoInfoDlg.roomInfoInfoData);
					    ajax.start();
				 }
//			 }
		 }
	})
    
}

/**
 * 提交修改
 */
RoomInfoInfoDlg.editSubmit = function() {
	submitSelect();
    this.clearData();
    this.collectData();
    
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/roomInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.RoomInfo.table.refresh();
        RoomInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.roomInfoInfoData);
    ajax.start();
}

/**
 * Excel下载
 */
RoomInfoInfoDlg.downloadExcel = function() {
	var options=$("#reader_test option:selected");
	window.location.href=Feng.ctxPath+"/roomInfo/impExcel/"+options.val();
}

/**
 * 提交管家
 */
RoomInfoInfoDlg.editButlerSubmit = function() {
	var options=$("#butler option:selected");
    $("#stewardId").val(options.val());
    $("#stewardName").val(options.text());
	
    this.clearData();
    this.collectData();
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/roomInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.RoomInfo.table.refresh();
        RoomInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.roomInfoInfoData);
    ajax.start();
}

RoomInfoInfoDlg.openReader = function() {
$.ajax({
	type:"POST",
	url:Feng.ctxPath+"/neighborhood/findByAddress/{}",
	dataType: "json",
	contentType: "application/json",
	cache:true,
	success: function (data) {
	 }
})
}

/**
 * 字典表：房屋类型
 * 开发者：金明禹
 * */

function rRoomType(){
	 $.ajax({
	        "type" : 'get',
	        "url": Feng.ctxPath + "/dict/getDic",
	        "dataType" : "json",
	        "data" : {"code" : "fwlx"},
	        "success" : function(data) {
	            for(var i = 0 ; i < data.length; i++ ){
	            	if($("#rRoomType").val() == data[i].num){
	            		 $("#rRoomType").append("<option value='"+data[i].num+"' selected='selected'>"+data[i].name+"</option>");
	            	}else{
	            		$("#rRoomType").append("<option value='"+data[i].num+"'>"+data[i].name+"</option>");
	            	}
	                
	            }
	        }
	    });
}

/**
 * 字典表：入住状态获取
 * 开发者：金明禹
 * */

function liveStatus(){
	 $.ajax({
	        "type" : 'get',
	        "url": Feng.ctxPath + "/dict/getDic",
	        "dataType" : "json",
	        "data" : {"code" : "rzzt"},
	        "success" : function(data) {
	            for(var i = 0 ; i < data.length; i++ ){
	            	if($("#liveStatus").val() == data[i].num){
	            		 $("#liveStatus").append("<option value='"+data[i].num+"' selected='selected'>"+data[i].name+"</option>");
	            	}else{
	            		$("#liveStatus").append("<option value='"+data[i].num+"'>"+data[i].name+"</option>");
	            	}
	                
	            }
	        }
	    });
}
/**
 * 获取小区列表Select
 * 开发者：金明禹
 * */
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
				 $("#test1").append("<option value='' >请选择</option>");
	             for (var i = 0; i < data.length; i++) {
	            	 if($("#nId").val() == data[i].nId){
		                 $("#test1").append("<option value='"+data[i].nId+"' selected>"+ data[i].nName +"</option>");
	            	 }else{
	 	                $("#test1").append("<option value='"+data[i].nId+"'>"+data[i].nName+"</option>");
	            	 }
	             }
			 }
		})
}


/**
 * 获取小区列表Select
 * 开发者：金明禹
 * */
function neighborhood1(){
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
		                 $("#reader_test").append("<option value='"+data[i].nCode+","+data[i].nId+"' selected>"+ data[i].nName +"</option>");
	            	 }else{
	 	                $("#reader_test").append("<option value='"+data[i].nCode+","+data[i].nId+"'>"+data[i].nName+"</option>");
	            	 }
	             }
			 }
		})
}

/**
 * 获取楼宇列表Select
 * 开发者：金明禹
 * */
function buildingInfo(){
	var options=$("#test1 option:selected");
	var data = "1" ;
	var test = "";
	
	if(options.val()==undefined){
		if($("#nId").val()==""){
			test = "0" ;
		}else{
			test = "1" ;
			data = $("#nId").val();	
		}
	}else{
		data = options.val();
		test ="1";
	}
	
	//清空数据
	$("#test").find("option").remove(); 
	 $.ajax({
			type:"POST",
			url:Feng.ctxPath+"/roomInfo/findBuilding/"+data+"",
			dataType: "json",
			contentType: "application/json",
			catch:true,
			 success: function (data) {
				 if(null!=$("#buildingtest").val()||""!=$("#buildingtest").val()){
					 ces = data[0].bId;
		             for (var i = 0; i < data.length; i++) {
		            	 if(test!=0){
		            		 if($("#bId").val() == data[i].bId){
				                 $("#test").append("<option value='"+data[i].bCode+","+data[i].bId+"' selected>"+ data[i].bName +"</option>");
				                 break;
			            	 }else{
			 	                $("#test").append("<option value='"+data[i].bCode+","+data[i].bId+"'>"+data[i].bName+"</option>");
			            	 }
		            	 }else{
		            		 $("#test").append("<option value='0'>请选择</option>");
		            		 break;
		            	 }
		             }
				 }else{
					 ces = data[0].bId;
					 $("#test").append("<option value='0'>请选择</option>");
		             for (var i = 0; i < data.length; i++) {
		            	 if(test!=0){
		            		 if($("#bId").val() == data[i].bId){
				                 $("#test").append("<option value='"+data[i].bCode+","+data[i].bId+"' selected>"+ data[i].bName +"</option>");
				                 break;
			            	 }else{
			 	                $("#test").append("<option value='"+data[i].bCode+","+data[i].bId+"'>"+data[i].bName+"</option>");
			            	 }
		            	 }else{
		            		 $("#test").append("<option value='0'>请选择</option>");
		            		 break;
		            	 }
		             }
				 }
			 }
		})
}

RoomInfoInfoDlg.test  =  function(){
	buildingInfo();
}


function submitSelect(){
	var options=$("#test option:selected");
	var arr = options.val().split(",");
	if(arr[1]!=$("#bId").val()){
		$("#rCode").val(arr[0]);
		$("#bId").val(arr[1]);
	}
	var options1=$("#test1 option:selected");
	$("#nId").val(options1.val());
}

//bulter获取管家用户

function butler(){
	var options=$("#butler option:selected");
	 $.ajax({
			type:"POST",
			url:Feng.ctxPath+"/roomInfo/findUser",
			dataType: "json",
			contentType: "application/json",
			catch:true,
			 success: function (data) {
				 var optionstring = "";
	             for (var i = 0; i < data.length; i++) {
	            	 if($("#butler").val() == data[i].id){
		                 $("#butler").append("<option value='"+data[i].id+"' selected>"+ data[i].name +"</option>");
	            	 }else{
	 	                $("#butler").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
	            	 }
	             }
			 }
		})
}

RoomInfoInfoDlg.excelReader = function () {
	var neighbor = $("nId").val();
		var data = $("#input-id").val();
		var result = Base.encode(data);
}


RoomInfoInfoDlg.validate = function () {
    $('#roomInfoForm').data("bootstrapValidator").resetForm();
    $('#roomInfoForm').bootstrapValidator('validate');
    return $("#roomInfoForm").data('bootstrapValidator').isValid();
};

(function(root, factory) {
	  if (typeof define === "function" && define.amd) {
	    define([], factory);
	  } else if (typeof module === "object" && module.exports) {
	    module.exports = factory();
	  } else {
	    root.Base = factory();
	  }
	}(this, function() {
	   'use strict';
	   
	    function Base64() {
	        // private property
	        this._keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
	    }
	    //public method for encoding
	    Base64.prototype.encode = function (input) {
	        var output = "", chr1, chr2, chr3, enc1, enc2, enc3, enc4, i = 0;
	        input = this._utf8_encode(input);
	        while (i < input.length) {
	            chr1 = input.charCodeAt(i++);
	            chr2 = input.charCodeAt(i++);
	            chr3 = input.charCodeAt(i++);
	            enc1 = chr1 >> 2;
	            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
	            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
	            enc4 = chr3 & 63;
	            if (isNaN(chr2)) {
	                enc3 = enc4 = 64;
	            } else if (isNaN(chr3)) {
	                enc4 = 64;
	            }
	            output = output +
	            this._keyStr.charAt(enc1) + this._keyStr.charAt(enc2) +
	            this._keyStr.charAt(enc3) + this._keyStr.charAt(enc4);
	        }
	        return output;
	    }

	    // public method for decoding
	    Base64.prototype.decode = function (input) {
	        var output = "", chr1, chr2, chr3, enc1, enc2, enc3, enc4, i = 0;
	        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
	        while (i < input.length) {
	            enc1 = this._keyStr.indexOf(input.charAt(i++));
	            enc2 = this._keyStr.indexOf(input.charAt(i++));
	            enc3 = this._keyStr.indexOf(input.charAt(i++));
	            enc4 = this._keyStr.indexOf(input.charAt(i++));
	            chr1 = (enc1 << 2) | (enc2 >> 4);
	            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
	            chr3 = ((enc3 & 3) << 6) | enc4;
	            output = output + String.fromCharCode(chr1);
	            if (enc3 != 64) {
	                output = output + String.fromCharCode(chr2);
	            }
	            if (enc4 != 64) {
	                output = output + String.fromCharCode(chr3);
	            }
	        }
	        output = this._utf8_decode(output);
	        return output;
	    }

	    // private method for UTF-8 encoding
	    Base64.prototype._utf8_encode = function (string) {
	        string = string.replace(/\r\n/g,"\n");
	        var utftext = "";
	        for (var n = 0; n < string.length; n++) {
	            var c = string.charCodeAt(n);
	            if (c < 128) {
	                utftext += String.fromCharCode(c);
	            } else if((c > 127) && (c < 2048)) {
	                utftext += String.fromCharCode((c >> 6) | 192);
	                utftext += String.fromCharCode((c & 63) | 128);
	            } else {
	                utftext += String.fromCharCode((c >> 12) | 224);
	                utftext += String.fromCharCode(((c >> 6) & 63) | 128);
	                utftext += String.fromCharCode((c & 63) | 128);
	            }
	    
	        }
	        return utftext;
	    }

	    // private method for UTF-8 decoding
	    Base64.prototype._utf8_decode = function (utftext) {
	        var string = "", i = 0, c = 0, c1 = 0, c2 = 0, c3 = 0;
	        while ( i < utftext.length ) {
	            c = utftext.charCodeAt(i);
	            if (c < 128) {
	                string += String.fromCharCode(c);
	                i++;
	            } else if((c > 191) && (c < 224)) {
	                c2 = utftext.charCodeAt(i+1);
	                string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
	                i += 2;
	            } else {
	                c2 = utftext.charCodeAt(i+1);
	                c3 = utftext.charCodeAt(i+2);
	                string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
	                i += 3;
	            }
	        }
	        return string;
	    }
	    
	    var Base = new Base64();
	    
	    return Base;
	}));


RoomInfoInfoDlg.test1  =  function(){
	var options=$("#reader_test option:selected");
	$("#nId").val(options.val());
}

function initZtree() {
    var setting = {
        check: {
            enable: true,
            chkboxType: { "Y": "ps", "N": "ps" }
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    };
    var param=$("#id").val()!=undefined?$("#id").val():'';
    var ztree = new $ZTree("zTree", "/roomInfo/queryNBTree");
  //  var ztree = new $ZTree("zTree", "/roomInfo/createNBTree/1");
    
    ztree.setSettings(setting);
    ztree.init();
}


$(function() {
	initZtree();
	var nId = "" ; 
	$("#test1").bind("change",function(){
		RoomInfoInfoDlg.test(this);
	});
	
//	$("#test").bind("change",function(){
//		RoomInfoInfoDlg.test2(this);
//	});
	
	$("#input-id").bind("change",function(){
		RoomInfoInfoDlg.test1(this);
	});
	initFileInput("input-id");
	 Feng.initValidator("roomInfoForm", RoomInfoInfoDlg.validateFields);
	 //获取用户
	butler();
	//字典表
	liveStatus();
	rRoomType();
	//获取小区列表Select
	neighborhood();
	neighborhood1();
	//获取楼宇列表Select
	buildingInfo();
	
    
  //------------------------------文件上传----------------------------------------
    function initFileInput(ctrlName) {
    	var control = $('#' + ctrlName);
    	control
    			.fileinput({
    				language : 'zh', //设置语言
    				uploadUrl : "/roomInfo/imageUpload", //上传的地址
    				//allowedFileExtensions : [ 'xls' ],
    				allowedFileExtensions: ['xlsx', 'xls'],//接收的文件后缀
    				maxFilesNum : 1,//上传最大的文件数量
//    				uploadExtraData:{nId: nId, rcode:rcode},
    				uploadExtraData:function(){
    		            return {"nId": $("#nId").val()};
    		        },
    				uploadAsync : true, //默认异步上传
    				showUpload : true, //是否显示上传按钮
    				showRemove : true, //显示移除按钮
    				showPreview : true, //是否显示预览
    				showCaption : false,//是否显示标题
    				browseClass : "btn btn-primary", //按钮样式
    				//dropZoneEnabled: true,//是否显示拖拽区域
    				minImageWidth: 50, //图片的最小宽度
    				minImageHeight: 50,//图片的最小高度
    				maxImageWidth: 1000,//图片的最大宽度
    				maxImageHeight: 1000,//图片的最大高度
    				maxFileSize : 5000,//单位为kb，如果为0表示不限制文件大小
    				//minFileCount: 0,
    				//maxFileCount: 10, //表示允许同时上传的最大文件个数
    				enctype : 'multipart/form-data',
    			//validateInitialCount:true,
    			//previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
    			//msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",

    			})
    			.on(
    					'filepreupload',
    					function(event, data, previewId, index) { //上传中
    						console.log(event);
    						console.log(data);
    						console.log(previewId);
    						console.log(index);
    						var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
    						console.log('文件正在上传');
    					}).on( 
    					"fileuploaded",
    					function(event, data, previewId, index) { //一个文件上传成功
    						if(data.response.code==1){
    							$("#contentImg").val(data.response.msg);
    						}else{
    							Feng.alert(data.response.msg);
    						}
    						
    					}).on('fileerror', function(event, data, msg) { //一个文件上传失败
    				console.log('文件上传失败！' + data.id);
    			})
    }
    
	
});
