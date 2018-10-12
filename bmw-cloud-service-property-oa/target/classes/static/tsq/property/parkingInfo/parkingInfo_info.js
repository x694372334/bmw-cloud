/**
 * 初始化车位管理详情对话框
 */
var ParkingInfoInfoDlg = {
    parkingInfoInfoData : {},
    validateFields: {
    	pNum: {
	        validators: {
	            notEmpty: {
	                message: '车位号不能为空'
	            }
	        },
	        stringLength:{
		        min: 0,
                max: 20,
                message: '超出长度'
                }
	    },
	    pArea: {
	        validators: {
	            notEmpty: {
	                message: '面积不能为空'
	            },
	            stringLength:{
			        min: 0,
	                max: 5,
	                message: '不能超过5位数'
	                }
	        }
	    },
	    pStatus: {
	        validators: {
	            notEmpty: {
	                message: '状态不能为空'
	            },
	            stringLength:{
			        min: 0,
	                max: 1,
	                message: '不能超过1位数'
	                }
	        }
	    },
    }
    
};

/**
 * 清除数据
 */
ParkingInfoInfoDlg.clearData = function() {
    this.parkingInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ParkingInfoInfoDlg.set = function(key, val) {
    this.parkingInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ParkingInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ParkingInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.ParkingInfo.layerIndex);
}

/**
 * 收集数据
 */
ParkingInfoInfoDlg.collectData = function() {
    this
    .set("pId")
    .set("pType")
    .set("pNum")
    .set("pArea")
    .set("pStatus")
    .set("remark")
    .set("createManId")
    .set("createMan")
    .set("createTime")
    .set("editManId")
    .set("editMan")
    .set("editTime")
    .set("nId")
    .set("iId")
    .set("vId")
    .set("isDelete")
}

/**
 * 提交添加
 */
ParkingInfoInfoDlg.addSubmit = function() {
	submitSelect();
    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/parkingInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.ParkingInfo.table.refresh();
        ParkingInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.parkingInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ParkingInfoInfoDlg.editSubmit = function() {
	submitSelect();
    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/parkingInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.ParkingInfo.table.refresh();
        ParkingInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.parkingInfoInfoData);
    ajax.start();
}

/**
 * 提交绑定住户和车辆
 * */
ParkingInfoInfoDlg.vehicleSubmit = function() {
	submitSelect();
    this.clearData();
    this.collectData();
    
//    if (!this.validate()) {
//        return;
//    } 
    if($("#test2").val()==null){
    	 Feng.error("车辆信息不能为空!");
    	return ;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/parkingInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.ParkingInfo.table.refresh();
        ParkingInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.parkingInfoInfoData);
    ajax.start();
}

ParkingInfoInfoDlg.validate = function () {
    $('#parkingInfoForm').data("bootstrapValidator").resetForm();
    $('#parkingInfoForm').bootstrapValidator('validate');
    return $("#parkingInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 字典表：车位类型
 * 开发者：金明禹
 * */

function pType(){
	 $.ajax({
	        "type" : 'get',
	        "url": Feng.ctxPath + "/dict/getDic",
	        "dataType" : "json",
	        "data" : {"code" : "cwlx"},
	        "success" : function(data) {
	            for(var i = 0 ; i < data.length; i++ ){
	            	if($("#pType").val() == data[i].num){
	            		 $("#pType").append("<option value='"+data[i].num+"' selected='selected'>"+data[i].name+"</option>");
	            	}else{
	            		$("#pType").append("<option value='"+data[i].num+"'>"+data[i].name+"</option>");
	            	}
	                
	            }
	        }
	    });
}

/**
 * 字典表：车位状态
 * 开发者：金明禹
 * */

function pStatus(){
	 $.ajax({
	        "type" : 'get',
	        "url": Feng.ctxPath + "/dict/getDic",
	        "dataType" : "json",
	        "data" : {"code" : "cwzt"},
	        "success" : function(data) {
	            for(var i = 0 ; i < data.length; i++ ){
	            	if($("#pStatus").val() == data[i].num){
	            		 $("#pStatus").append("<option value='"+data[i].num+"' selected='selected'>"+data[i].name+"</option>");
	            	}else{
	            		$("#pStatus").append("<option value='"+data[i].num+"'>"+data[i].name+"</option>");
	            	}
	                
	            }
	        }
	    });
}

/**
 * 获取小区列表Select
 * 开发者：金明禹
 * */
function neighborhood2(){
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

/**
 * 获取楼宇列表Select
 * 开发者：金明禹
 * */
function neighborhood(){
	var nId=$("#test option:selected");
	 $.ajax({
			type:"POST",
			url:Feng.ctxPath+"/parkingInfo/findBuilding"+nId.val(),
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


/**
 * 住户列表
 * */
function inhabitantInfo(e){
	$("#test1").find("option").remove(); 
	var options=$("#citySel option:selected");
	var options=$("#test1 option:selected");
	var data = e;
	if(e!="0"){
	 $.ajax({
			type:"POST",
			url:Feng.ctxPath+"/parkingInfo/findInhabitant/"+data,
			dataType: "json",
			contentType: "application/json",
			catch:true,
			 success: function (data) {
				 var optionstring = "";
				 $("#test1").append("<option value='请选择' selected>请选择</option>");
	             for (var i = 0; i < data.length; i++) {
	            	 console.log(data[i].iName);
	            	 if($("#iId").val() == data[i].iId){
		                 $("#test1").append("<option value='"+data[i].iId+"'>"+ data[i].iName +"</option>");
	            	 }else{
//	            		 $("#test1").append("<option value='请选择'>请选择</option>");
	 	                $("#test1").append("<option value='"+data[i].iId+"'>"+data[i].iName+"</option>");
	            	 }
	             }
			 }
		})
}
}

function submitSelect(){
	var options=$("#test1 option:selected");
	$("#iId").val(options.val());
	var options1=$("#test option:selected");
	$("#nId").val(options1.val());
	var options2=$("#test2 option:selected");
	$("#vId").val(options2.val());
}
ParkingInfoInfoDlg.test = function(){
	roomInfo1();
	var options=$("#test2 option:selected");
}
/**
 * 绑定车辆
 * */
function roomInfo1(){
	var options=$("#test1 option:selected");
	var data = options.val();
	$("#test2").find("option").remove(); 
	 $.ajax({
			type:"POST",
			url:Feng.ctxPath+"/parkingInfo/findIVehicle/"+data+"",
			dataType: "json",
			contentType: "application/json",
			catch:true,
			 success: function (data) {
				 var optionstring = "";
	             for (var i = 0; i < data.length; i++) {
	            	 if($("#vId").val() == data[i].vId){
	            		 console.log(data[i].rRoomnum);
		                 $("#test2").append("<option value='"+data[i].vId+"' selected>"+ data[i].vNumber +"号</option>");
	            	 }else{
	 	                $("#test2").append("<option value='"+data[i].vId+"'>"+data[i].vNumber+"号</option>");
	            	 }
	             }
			 }
		})
}

ParkingInfoInfoDlg.showRoomSelectTree=function(){
	 var cityObj = $("#citySel");
	    var cityPosition = $("#citySel").position();
	    console.log(cityPosition);
	    cityPosition.top+=58;
	    cityPosition.left+=24;
	    $("#roomContent").css({
	        left: cityPosition.left + "px",
	        top: cityPosition.top + cityObj.outerHeight() + "px"
	    }).slideDown("fast");
	    $("body").bind("mousedown", onBodyDown);
}

function onBodyDown(event) {
   if (!(event.target.id == "menuBtn" || event.target.id == "roomContent" || $(
           event.target).parents("#roomContent").length > 0)) {
   	ParkingInfoInfoDlg.hideRoomSelectTree();
   }
}

ParkingInfoInfoDlg.hideRoomSelectTree = function () {
   $("#roomContent").fadeOut("fast");
   $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};
ParkingInfoInfoDlg.onClickRoom= function (e, treeId, treeNode) {
	if(treeNode.level!=3){
		Feng.alert("请选择房间");
		return;
	}
	console.log(treeId);
	console.log(treeNode);
   $("#citySel").attr("value", instance.getSelectedVal());
   $("#roomId").attr("value", treeNode.pcode);
   inhabitantInfo(treeNode.pcode);
//   alert(treeNode.pcode);
};

/**
 * Excel
 * */
ParkingInfoInfoDlg.downloadExcel = function() {
	var options=$("#roomInfo option:selected");
	if($("#roomInfo").val()==null){
		Feng.alert("清先选中小区、楼宇、房屋");
		return ;
	}else if($("#roomInfo").val()=="0"){
		Feng.alert("请选择房屋");
		return ;
	}else{
		window.location.href=Feng.ctxPath+"/parkingInfo/impExcel/"+options.val();
	}
}

/**
 * 获取房屋列表Select
 * 开发者：金明禹
 * */
function buildingInfo(e){
	 $.ajax({
			type:"POST",
			url:Feng.ctxPath+"/parkingInfo/findBuilding/"+e,
			dataType: "json",
			contentType: "application/json",
			catch:true,
			 success: function (data) {
				 var optionstring = "";
				 $("#building").append("<option value='0' selected>请选择</option>");
				 console.log(data);
	             for (var i = 0; i < data.length; i++) {
	            	 if($("#building").val() == data[i].bId){
		                 $("#building").append("<option value='"+data[i].bId+"' >"+ data[i].bName +"</option>");
	            	 }else{
	 	                $("#building").append("<option value='"+data[i].bId+"'>"+data[i].bName+"</option>");
	            	 }
	             }
			 }
		})
}

ParkingInfoInfoDlg.buildingInfoChange=function(){
	var bId=$("#reader_test option:selected");
	var data = bId.val().split(",");
	buildingInfo(data[1]);
}

/**
 * 获取房屋列表Select
 * 开发者：金明禹
 * */
function roomInfo(e){
	 $("#roomInfo").find("option").remove();
	 $.ajax({
			type:"POST",
			url:Feng.ctxPath+"/parkingInfo/findRoomInfo/"+e,
			dataType: "json",
			contentType: "application/json",
			catch:true,
			 success: function (data) {
				 var optionstring = "";
				 $("#roomInfo").append("<option value='0' selected>请选择</option>");
	             for (var i = 0; i < data.length; i++) {
	            	 if($("#rId").val() == data[i].nId){
		                 $("#roomInfo").append("<option value='"+data[i].rId+"' >"+ data[i].rRoomnum +"</option>");
	            	 }else{
	 	                $("#roomInfo").append("<option value='"+data[i].rId+"'>"+data[i].rRoomnum+"</option>");
	            	 }
	             }
			 }
		})
}

ParkingInfoInfoDlg.roomInfoChange=function(){
	var rId=$("#building option:selected");
	roomInfo(rId.val());
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
				 $("#reader_test").append("<option value='0' selected>请选择</option>");
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


$(function() {
	 Feng.initValidator("parkingInfoForm", ParkingInfoInfoDlg.validateFields);
	 pType();
	 pStatus();
	 var data = "0";
	 inhabitantInfo(data);
	 neighborhood();
	 neighborhood1();
	 neighborhood2();
	 $("#reader_test").bind("change",function(){
		 ParkingInfoInfoDlg.buildingInfoChange(this);
	 });
	 $("#building").bind("change",function(){
		 ParkingInfoInfoDlg.roomInfoChange(this);
	 });
	 $("#test1").bind("change",function(){
		 ParkingInfoInfoDlg.test(this);
	 });
	 
	 var ztree = new $ZTree("treeDemo", "/roomInfo/createNBTree/3/0");
	    ztree.bindOnClick(ParkingInfoInfoDlg.onClickRoom);
	    ztree.init();
	    instance = ztree;
	    
	    
	 
 initFileInput("input-id");
	    
	    //------------------------------文件上传----------------------------------------
	    function initFileInput(ctrlName) {
	    	var control = $('#' + ctrlName);
	    	control
	    			.fileinput({
	    				language : 'zh', //设置语言
	    				uploadUrl : "/parkingInfo/imageUpload", //上传的地址
	    				//allowedFileExtensions : [ 'xls' ],
	    				allowedFileExtensions: ['xlsx', 'xls'],//接收的文件后缀
	    				maxFilesNum : 1,//上传最大的文件数量
//	    				uploadExtraData:{nId: nId, rcode:rcode},
	    				uploadExtraData:function(){
	    		            return {"rId": $("#roomInfo").val()};
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
