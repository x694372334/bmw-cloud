/**
 * 初始化住户管理详情对话框
 */
var InhabitantInfoInfoDlg = {
    inhabitantInfoInfoData : {},
    validateFields: {
       	iName: {
	        validators: {
	            notEmpty: {
	                message: '住户姓名不能为空'
	            }
	        }
	    },
	    iIdcardno: {
	        validators: {
	            notEmpty: {
	                message: '业主身份证号不能为空'
	            },
	            regexp: {
		            regexp: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
		            message: '请输入15位或18位证件号码'
		        },
		        stringLength:{
		        min: 0,
                max: 18,
                message: '不能超过18位数'
                }
		        
	        }
	    },
	    iPhoneno: {
	        validators: {
	            notEmpty: {
	                message: '住户手机号码不能为空'
	            },
		        stringLength:{
		        min: 0,
                max: 11,
                message: '不能超过11位数'
                }
	        }
	    },
	    citySel: {
	        validators: {
	            notEmpty: {
	                message: '房屋号不能为空'
	            }
	        }
	    }
    }
};

/**
 * 清除数据
 */
InhabitantInfoInfoDlg.clearData = function() {
    this.inhabitantInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
InhabitantInfoInfoDlg.set = function(key, val) {
    this.inhabitantInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
InhabitantInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
InhabitantInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.InhabitantInfo.layerIndex);
}

/**
 * 关闭此对话框
 */
InhabitantInfoInfoDlg.flowableClose = function() {
    parent.layer.close(window.parent.RuTask.layerIndex);
}

/**
 * 收集数据
 */
InhabitantInfoInfoDlg.collectData = function() {
    this
    .set("iId")
    .set("iName")
    .set("iGender")
    .set("iIdcardno")
    .set("iPhoneno")
    .set("iIdentity")
    .set("oStatus")
    .set("iInterest")
    .set("iProfession")
    .set("iCompany")
    .set("iPet")
    .set("rId")
    .set("ifCertification")
    .set("createManId")
    .set("createMan")
    .set("createTime")
    .set("editManId")
    .set("editMan")
    .set("editTime")
    .set("isDelete")
    .set("taskId")
    .set("isHOwner")
    .set("user_id")
}

/**
 * 提交添加
 */
InhabitantInfoInfoDlg.addSubmit = function() {
	submitSelect();
    this.clearData();
    this.collectData();
    if (!this.validate()) {
	  return;
  	}
	 $.ajax({
	        "type" : 'get',
	        "url": Feng.ctxPath + "/inhabitantInfo/findByAllInha/" +$("#roomId").val(),
	        "dataType" : "json",
	        "success" : function(data) {
	        	console.log(data);
	        	var isHOwner = "0" ;//是否已经存在房主
	        	var iIdcardno = "0" ;//同一房间内是否存在相同证件号码
	        	var iName = "0";//同一房间内是否存在相同姓名
	        	var iPhoneno = "0";//电话号码是否已存在
	        	for(var i = 0 , len = data.length ; i < len; i++ ){
	        		if(data[i].isHOwner=="1" && $("#isHOwner").val()=="1"){
	        			isHOwner = "1";
	        		}
	        		if(data[i].iIdcardno == $("#iIdcardno").val()){
	        			iIdcardno = "1";
	        		}
	        		if(data[i].iName == $("#iName").val()){
	        			iName = "1";
	        		}
	        		if(data[i].iPhoneno == $("#iPhoneno").val()){
	        			iPhoneno = "1";
	        		}
	        	}
	        	if(isHOwner == "1"){
	        		  Feng.error("此房间已有房主!");
	        		  return;
	        	}else if(iIdcardno== "1"){
	        		  Feng.error("此房间内证件号码存在相同!");
	        		  return;
	        	}else if(iName== "1"){
	        		  Feng.error("此房间姓名存在相同!");
	        		  return;
	        	}else if(iPhoneno== "1"){
	        		  Feng.error("电话号码不可相同!");
	        		  return;
	        	}else{
	        	    //提交信息
	        	    var ajax = new $ax(Feng.ctxPath + "/inhabitantInfo/add", function(data){
	        	        Feng.success("添加成功!");
	        	        window.parent.InhabitantInfo.table.refresh();
	        	        InhabitantInfoInfoDlg.close();
	        	    },function(data){
	        	        Feng.error("添加失败!" + data.responseJSON.message + "!");
	        	    });
	        	    ajax.set(InhabitantInfoInfoDlg.inhabitantInfoInfoData);
	        	    ajax.start();
	        	}
	            }
	    });

}

/**
 * 提交修改
 */
InhabitantInfoInfoDlg.editSubmit = function() {
	submitSelect();
    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/inhabitantInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.InhabitantInfo.table.refresh();
        InhabitantInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.inhabitantInfoInfoData);
    ajax.start();
}


/**
 * 提交添加
 */
InhabitantInfoInfoDlg.flowabledApply = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/inhabitantInfo/flowabled_apply", function(data){
        Feng.success("成功通过!");
        window.parent.RuTask.table.refresh();
        InhabitantInfoInfoDlg.flowableClose();
    },function(data){
        Feng.error("通过失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.inhabitantInfoInfoData);
    ajax.start();
}


/**
 * 提交添加
 */
InhabitantInfoInfoDlg.flowabledReject = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/inhabitantInfo/flowabled_reject", function(data){
        Feng.success("驳回成功!");
        window.parent.RuTask.table.refresh();
        InhabitantInfoInfoDlg.flowableClose();
    },function(data){
        Feng.error("驳回失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.inhabitantInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
InhabitantInfoInfoDlg.flowabledAddSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/inhabitantInfo/flowabled_add", function(data){
        Feng.success("提交申请成功!");
        window.parent.InhabitantInfo.table.refresh();
        InhabitantInfoInfoDlg.close();
    },function(data){
        Feng.error("提交申请失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.inhabitantInfoInfoData);
    ajax.start();
}


/**
 * 字典表：住户性别
 * 开发者：金明禹
 * */

function iGender(){
	 $.ajax({
	        "type" : 'get',
	        "url": Feng.ctxPath + "/dict/getDic",
	        "dataType" : "json",
	        "data" : {"code" : "xb"},
	        "success" : function(data) {
	            for(var i = 0 ; i < data.length; i++ ){
	            	if($("#iGender").val() == data[i].num){
	            		 $("#iGender").append("<option value='"+data[i].num+"' selected='selected'>"+data[i].name+"</option>");
	            	}else{
	            		$("#iGender").append("<option value='"+data[i].num+"'>"+data[i].name+"</option>");
	            	}
	                
	            }
	        }
	    });
}

function oStatus(){
	 $.ajax({
	        "type" : 'get',
	        "url": Feng.ctxPath + "/dict/getDic",
	        "dataType" : "json",
	        "data" : {"code" : "rzzt"},
	        "success" : function(data) {
	            for(var i = 0 ; i < data.length; i++ ){
	            	if($("#oStatus").val() == data[i].num){
	            		 $("#oStatus").append("<option value='"+data[i].num+"' selected='selected'>"+data[i].name+"</option>");
	            	}else{
	            		$("#oStatus").append("<option value='"+data[i].num+"'>"+data[i].name+"</option>");
	            	}
	                
	            }
	        }
	    });
}

function iIdentity(){
	 $.ajax({
	        "type" : 'get',
	        "url": Feng.ctxPath + "/dict/getDic",
	        "dataType" : "json",
	        "data" : {"code" : "zhsf"},
	        "success" : function(data) {
	            for(var i = 0 ; i < data.length; i++ ){
	            	if($("#iIdentity").val() == data[i].num){
	            		 $("#iIdentity").append("<option value='"+data[i].num+"' selected='selected'>"+data[i].name+"</option>");
	            	}else{
	            		$("#iIdentity").append("<option value='"+data[i].num+"'>"+data[i].name+"</option>");
	            	}
	                
	            }
	        }
	    });
}


function isHOwner(){
	 $.ajax({
	        "type" : 'get',
	        "url": Feng.ctxPath + "/dict/getDic",
	        "dataType" : "json",
	        "data" : {"code" : "sf"},
	        "success" : function(data) {
	            for(var i = 0 ; i < data.length; i++ ){
	            	if($("#isHOwner").val() == data[i].num){
	            		 $("#isHOwner").append("<option value='"+data[i].num+"' selected='selected'>"+data[i].name+"</option>");
	            	}else{
	            		$("#isHOwner").append("<option value='"+data[i].num+"'>"+data[i].name+"</option>");
	            	}
	            }
	        }
	    });
}

/**
 * Excel下载
 */
InhabitantInfoInfoDlg.downloadExcel = function() {
	var options=$("#reader_test option:selected");
	var options1=$("#reader_test1 option:selected");
	window.location.href=Feng.ctxPath+"/inhabitantInfo/impExcel/"+options.val()+"/"+options1.val();
}

function ifCertification(){
	 $.ajax({
	        "type" : 'get',
	        "url": Feng.ctxPath + "/dict/getDic",
	        "dataType" : "json",
	        "data" : {"code" : "rxzt"},
	        "success" : function(data) {
	            for(var i = 0 ; i < data.length; i++ ){
	            	if($("#ifCertification").val() == data[i].num){
	            		 $("#ifCertification").append("<option value='"+data[i].num+"' selected='selected'>"+data[i].name+"</option>");
	            	}else{
	            		$("#ifCertification").append("<option value='"+data[i].num+"'>"+data[i].name+"</option>");
	            	}
	                
	            }
	        }
	    });
}

function roomInfo(){
	var options=$("#test option:selected");
	 $.ajax({
			type:"POST",
			url:Feng.ctxPath+"/inhabitantInfo/findRoomInfo",
			dataType: "json",
			contentType: "application/json",
			catch:true,
			 success: function (data) {
				 var optionstring = "";
	             for (var i = 0; i < data.length; i++) {
	            	 if($("#rId").val() == data[i].rId){
		                 $("#test").append("<option value='"+data[i].rId+"' selected>"+ data[i].rRoomnum +"号</option>");
	            	 }else{
	 	                $("#test").append("<option value='"+data[i].rId+"'>"+data[i].rRoomnum+"号</option>");
	            	 }
	             }
			 }
		})
}

function submitSelect(){
	var options=$("#test option:selected");
	$("#rId").val($("#roomId").val());
}

InhabitantInfoInfoDlg.validate = function () {
    $('#inhabitantInfoForm').data("bootstrapValidator").resetForm();
    $('#inhabitantInfoForm').bootstrapValidator('validate');
    return $("#inhabitantInfoForm").data('bootstrapValidator').isValid();
};

InhabitantInfoInfoDlg.showRoomSelectTree=function(){
	 var cityObj = $("#citySel");
	    var cityPosition = $("#citySel").position();
	    console.log(cityPosition);
	    cityPosition.top+=330;
	    cityPosition.left+=440;
	    $("#roomContent").css({
	        left: cityPosition.left + "px",
	        top: cityPosition.top + cityObj.outerHeight() + "px"
	    }).slideDown("fast");
	    $("body").bind("mousedown", onBodyDown);
}

function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "roomContent" || $(
            event.target).parents("#roomContent").length > 0)) {
    	InhabitantInfoInfoDlg.hideRoomSelectTree();
    }
}

InhabitantInfoInfoDlg.hideRoomSelectTree = function () {
    $("#roomContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};
InhabitantInfoInfoDlg.onClickRoom= function (e, treeId, treeNode) {
	if(treeNode.level!=3){
		Feng.alert("请选择房间");
		return;
	}
	console.log(treeId);
	console.log(treeNode);
    $("#citySel").attr("value", instance.getSelectedVal());
    $("#roomId").attr("value", treeNode.pcode);
};

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

InhabitantInfoInfoDlg.buildingInfo = function(e){
	buildingInfo();
}

function XianzInha(){
}



function buildingInfo(){
	var options=$("#reader_test option:selected");
	var text = options.val();
	var data = 0;
	if(text!=null){
		data = text.split(",")
	}
	//清空数据
	$("#reader_test1").find("option").remove(); 
	 $.ajax({
			type:"POST",
			url:Feng.ctxPath+"/roomInfo/findBuilding/"+data[1]+"",
			dataType: "json",
			contentType: "application/json",
			catch:true,
			 success: function (data) {
	             for (var i = 0; i < data.length; i++) {
	            	 if(data!=0){
		 	                $("#reader_test1").append("<option value='"+data[i].bId+"'>"+data[i].bName+"</option>");
	            	 }else{
	            		 $("#reader_test1").append("<option value='0'>请选择</option>");
	            		 break;
	            	 }
	             }
			 }
		})
}

$(function() {
	 Feng.initValidator("inhabitantInfoForm", InhabitantInfoInfoDlg.validateFields);
	iGender();
	isHOwner();
	oStatus();
	iIdentity();
	ifCertification();
	neighborhood1();
	 $("#reader_test").bind("change",function(){
		 InhabitantInfoInfoDlg.buildingInfo(this);
	});
//	roomInfo();
	 var ztree = new $ZTree("treeDemo", "/roomInfo/createNBTree/3/0");
	    ztree.bindOnClick(InhabitantInfoInfoDlg.onClickRoom);
	    ztree.init();
	    instance = ztree;
	    
	    
	    initFileInput("input-id");
	    
	    //------------------------------文件上传----------------------------------------
	    function initFileInput(ctrlName) {
	    	var control = $('#' + ctrlName);
	    	control
	    			.fileinput({
	    				language : 'zh', //设置语言
	    				uploadUrl : "/inhabitantInfo/imageUpload", //上传的地址
	    				//allowedFileExtensions : [ 'xls' ],
	    				allowedFileExtensions: ['xlsx', 'xls'],//接收的文件后缀
	    				maxFilesNum : 1,//上传最大的文件数量
//	    				uploadExtraData:{nId: nId, rcode:rcode},
	    				uploadExtraData:function(){
	    					var options=$("#reader_test option:selected");
	    					var data = options.val();
	    					var data1 = data.split("\\,");
	    					var options1=$("#reader_test1 option:selected");
	    		            return {"nId": data1[1] , "bId":options1.val()};
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
