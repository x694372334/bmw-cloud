/**
 * 初始化报事报修详情对话框
 */
var RepairsFormInfoDlg = {
    repairsFormInfoData : {},
    submitFlag:true,
    validateFields: {
    	repairsAddr: {
	        validators: {
	            notEmpty: {
	                message: '维修地址不能为空'
	            }
	        }
	    },
	    contentText: {
	        validators: {
	            notEmpty: {
	                message: '报修描述不能为空'
	            }
	        }
	    },
	    roomId: {
	        validators: {
	            notEmpty: {
	                message: '请选择维修的房间号'
	            }
	        }
	    },
	    ownerName: {
	        validators: {
	            notEmpty: {
	                message: '业主姓名不能为空'
	            }
	        }
	    },
	    ownerPhoneNo: {
	        validators: {
	            notEmpty: {
	                message: '联系方式不能 为空'
	            },
	            stringLength:{
	            	 min: 0,
	                 max: 15,
	                 message: '超出长度'
	            },
	            regexp: {
		            regexp: /^[0-9]*$/,
		            message: '只能输入数字'
		        }
	        }
	    }
	}
};
RepairsFormInfoDlg.showRoomSelectTree=function(){
	 var cityObj = $("#citySel");
	    var cityPosition = $("#citySel").position();
	    cityPosition.left+=490;
	    cityPosition.top+=120;
	    $("#roomContent").css({
	        left: cityPosition.left + "px",
	        top: cityPosition.top + cityObj.outerHeight() + "px"
	    }).slideDown("fast");
	    $("body").bind("mousedown", onBodyDown);
}
/**
 * 隐藏部门选择的树
 */
RepairsFormInfoDlg.hideRoomSelectTree = function () {
    $("#roomContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};

RepairsFormInfoDlg.onClickRoom = function (e, treeId, treeNode) {
	if(treeNode.level!=3){
		Feng.alert("请选择房间");
		return;
	}
    $("#citySel").attr("value", treeNode.ext_attr);
    $("#roomId").attr("value", treeNode.pcode);
};
function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "roomContent" || $(
            event.target).parents("#roomContent").length > 0)) {
    	RepairsFormInfoDlg.hideRoomSelectTree();
    }
}
/**
 * 清除数据
 */
RepairsFormInfoDlg.clearData = function() {
    this.repairsFormInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RepairsFormInfoDlg.set = function(key, val) {
    this.repairsFormInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RepairsFormInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
RepairsFormInfoDlg.close = function() {
    parent.layer.close(window.parent.RepairsForm.layerIndex);
}

/**
 * 收集数据
 */
RepairsFormInfoDlg.collectData = function() {
	var formData=new FormData();
	formData.append("repairsAddr",$("#repairsAddr").val());
	formData.append("contentText",$("#contentText").val());
	formData.append("contentImg",$("#contentImg").val());
	formData.append("roomId",$("#roomId").val());
	formData.append("ownerName",$("#ownerName").val());
	formData.append("ownerPhoneNo",$("#ownerPhoneNo").val());
    formData.append("initiatorType",3);
    var elements=document.getElementsByName('contentImgList');
    if($("#id").val()!='undefined'){
    	formData.append("id",$("#id").val());
    }
    if($("#imgServerUrl").val()!='undefined'){
    	formData.append("imgServerUrl",$("#imgServerUrl").val());
    }
    if($("input[name='oldImgUrl']").size()!=0){
    	$("input[name='oldImgUrl']").each(function(i){
    		formData.append("oldImgUrl",$(this).val());
    	})
    }
    for(var i=0;i<elements.length;i++){
    	formData.append("file",elements[i].files[0]);
    }
    return formData;
}

/**
 * 提交添加
 */
RepairsFormInfoDlg.addSubmit = function() {
    if (!this.validate()) {
        return;
    }
    var formData=RepairsFormInfoDlg.collectData();
    
    if($.trim($("#contentText").val()).length ==0){
    	Feng.error("请输入报修描述！")
    	return;
    }
    $.ajax({
    	url: '/repairsForm/add',
        type: 'POST',
        cache: false,
        data: formData,
        processData: false,
        contentType: false,
        success : function(data){
        	Feng.success(data.message);
        	window.parent.RepairsForm.table.refresh();
        	RepairsFormInfoDlg.close();
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
        	Feng.error(errorThrown);
        }
    })
}

/**
 * 提交添加
 */
RepairsFormInfoDlg.editSubmit = function() {
    if (!this.validate()) {
        return;
    }
    var formData=RepairsFormInfoDlg.collectData();
    
    if($.trim($("#contentText").val()).length ==0){
    	Feng.error("请输入报修描述！")
    	return;
    }
    $.ajax({
    	url: '/repairsForm/update',
        type: 'POST',
        cache: false,
        data: formData,
        processData: false,
        contentType: false,
        success : function(data){
        	Feng.success(data.message);
        	window.parent.RepairsForm.table.refresh();
        	RepairsFormInfoDlg.close();
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
        	Feng.error(errorThrown);
        }
    })
}

RepairsFormInfoDlg.validate = function () {
    $('#repairsFormForm').data("bootstrapValidator").resetForm();
    $('#repairsFormForm').bootstrapValidator('validate');
    return $("#repairsFormForm").data('bootstrapValidator').isValid();
};
$(function() {
		Feng.initValidator("repairsFormForm", RepairsFormInfoDlg.validateFields);
		var ztree = new $ZTree("treeDemo", "/roomInfo/createNBTree/3/0");
	    ztree.bindOnClick(RepairsFormInfoDlg.onClickRoom);
	    ztree.init();
	    instance = ztree;
});
//------------------------------文件上传----------------------------------------
//检查上传文件大小及类型是否合法
function checkFile(tag) {
	var flag = false; // 状态
	var arr = [ "jpg", "png", "gif","jpeg" ];
	// 取出上传文件的扩展名
	var index = tag.value.lastIndexOf(".");
	var ext = tag.value.substr(index + 1);
	// 循环比较
	for (var i = 0; i < arr.length; i++) {
		if (ext == arr[i]) {
			flag = true; // 一旦找到合适的，立即退出循环
			break;
		}
	}
	var size=tag.files[0].size/1024/1024;
	// 条件判断
	if (!flag) {
		tag.value="";
		Feng.error("文件类型不合法");
		return;
	}
	if(size>2){//如果大于两M
		Feng.error("文件大小不合法");
		return;
	}
}
//-----------------------------------------------------------------------------------------
 function changeImg(obj,imgId,oldImgUrl){
	if(typeof oldImgUrl != 'undefined'){
		$("#"+oldImgUrl).val("");
	}
	  //判断浏览器是否支持FileReader接口
    if (typeof FileReader == 'undefined') {
     //   document.getElementById("xmTanDiv").InnerHTML = "<h1>当前浏览器不支持FileReader接口</h1>";
        //使选择控件不可操作
     //   document.getElementById("xdaTanFileImg").setAttribute("disabled", "disabled");
    	Feng.error("当前浏览器不支持FileReader");
    }
    //选择图片，马上预览
        var file = obj.files[0]; 
    //    console.log(obj);console.log(file);
    //    console.log("file.size = " + file.size);  //file.size 单位为byte
        var reader = new FileReader();
        //读取文件过程方法
        reader.onloadstart = function (e) {
   //         console.log("开始读取....");
        }
        reader.onprogress = function (e) {
   //         console.log("正在读取中....");
        }
        reader.onabort = function (e) {
   //         console.log("中断读取....");
        }
        reader.onerror = function (e) {
   //         console.log("读取异常....");
        }
        reader.onload = function (e) {
   //         console.log("成功读取....");
            var img = document.getElementById(imgId);
            img.src = e.target.result;
            //或者 img.src = this.result;  //e.target == this
        }
        reader.readAsDataURL(file)
}
