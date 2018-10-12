/**
 * 初始化services详情对话框
 */
var ServicesInfoDlg = {
	servicesInfoData : {},
	validateFields : {
		serviceName : {
			validators : {
				notEmpty : {
					message : '服务名称不能为空'
				}
			}
		},
		
		nId : {
			validators : {
				notEmpty : {
					message : '请选择小区'
				}
			}
		},

		serviceSortName : {
			validators : {
				notEmpty : {
					message : '请选择服务类别'
				}
			}
		},
		serviceType : {
			validators : {
				notEmpty : {
					message : '请选择服务方式'
				}
			}
		}
	}
};

/**
 * 清除数据
 */
ServicesInfoDlg.clearData = function() {
	this.servicesInfoData = {};
}

/**
 * 设置对话框中的数据
 * 
 * @param key
 *            数据的名称
 * @param val
 *            数据的具体值
 */
ServicesInfoDlg.set = function(key, val) {
	this.servicesInfoData[key] = (typeof val == "undefined") ? $("#" + key)
			.val() : val;
	return this;
}

/**
 * 设置对话框中的数据
 * 
 * @param key
 *            数据的名称
 * @param val
 *            数据的具体值
 */
ServicesInfoDlg.get = function(key) {
	return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ServicesInfoDlg.close = function() {
	parent.layer.close(window.parent.Services.layerIndex);
}

/**
 * 收集数据
 */
ServicesInfoDlg.collectData = function() {
	this.set('id').set('eId').set('nId').set('nName').set('serviceSortCode')
			.set('serviceSortName').set('serviceName').set('serviceCode').set(
					'serviceType').set('paymentType').set('price').set(
					'serviceDetail').set('cover').set('createManId').set(
					'createMan').set('createTime').set('editManId').set(
					'editTime').set('isDelete').set('deposit');
	this.servicesInfoData['serviceDetail'] = ServicesInfoDlg.editor.txt.html();
	
	var formData=new FormData();
	//formData.append("id",$("#id").val());
	//formData.append("eId",$("#eId").val());
	formData.append("nId",$("#nId").val());
	formData.append("nName",$("#nName").val());
	formData.append("serviceSortCode",$("#serviceSortCode").val());
	formData.append("serviceSortName",$("#serviceSortName").val());
	formData.append("serviceName",$("#serviceName").val());
	formData.append("serviceCode",$("#serviceCode").val());
	formData.append("serviceType",$("#serviceType").val());
	formData.append("paymentType",$("#paymentType").val());
	formData.append("price",$("#price").val());
	formData.append("deposit",$("#deposit").val());
	formData.append("serviceDetail",ServicesInfoDlg.editor.txt.html());
    var elements=document.getElementsByName('content');
    for(var i=0;i<elements.length;i++){
    	formData.append("file",elements[i].files[0]);
    }
    return formData;
}

/**
 * 验证数据是否为空
 */
ServicesInfoDlg.validate = function () {
    $('#servicesForm').data("bootstrapValidator").resetForm();
    $('#servicesForm').bootstrapValidator('validate');
    return $("#servicesForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
ServicesInfoDlg.addSubmit = function() {

	    var formData=ServicesInfoDlg.collectData();
	    
	    if (!this.validate()) {
	        return;
	    }
	    
	    $.ajax({
	    	url: '/services/add',
	        type: 'POST',
	        cache: false,
	        data: formData,
	        processData: false,
	        contentType: false,
	        success : function(data){
	        	Feng.success("添加成功!");
	    		window.parent.Services.table.refresh();
	    		ServicesInfoDlg.close();
	        },
	        error:function(XMLHttpRequest, textStatus, errorThrown){
	        	Feng.error(errorThrown);
	        }
	    })
}

/**
 * 提交修改
 */
ServicesInfoDlg.editSubmit = function() {

	this.clearData();
	this.collectData();
	
	 if (!this.validate()) {
	        return;
	    }
	// 提交信息
	var ajax = new $ax(Feng.ctxPath + "/services/update", function(data) {
		Feng.success("修改成功!");
		window.parent.Services.table.refresh();
		ServicesInfoDlg.close();
	}, function(data) {
		Feng.error("修改失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.servicesInfoData);
	ajax.start();
}

ServicesInfoDlg.showTree = function () {
    Feng.showInputTree("serviceSortCode", "serviceSortTreeDiv", 15, 34);
};

/**
 * 下载文档
 */
ServicesInfoDlg.download = function() {
		 var form = $("<form>");
		    form.attr('style', 'display:none');
		    form.attr('target', '');
		    form.attr('method', 'post');
		    form.attr('action', Feng.ctxPath + "/services/download");
		    
		    var input = $('<input>');
		    input.attr('type', 'hidden');
		    input.attr('name', 'cover');
		    input.attr('value', $("#cover").val());
		    
		    $('body').append(form);
		    form.append(input);
		    
		    form.submit();
		    form.remove();
}


function initFileInput(ctrlName) {
	var control = $('#' + ctrlName);
	control
			.fileinput({
				language : 'zh', //设置语言
				uploadUrl : "/services/upload", //上传的地址
				allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
				maxFilesNum : 5,//上传最大的文件数量
				uploadAsync : true, //默认异步上传
				showUpload : true, //是否显示上传按钮
				showRemove : true, //显示移除按钮
				showPreview : true, //是否显示预览
				showCaption : false,//是否显示标题
				browseClass : "btn btn-primary", //按钮样式
				maxFileSize : 0,//单位为kb，如果为0表示不限制文件大小
				enctype : 'multipart/form-data',

			})
			.on(
					'filepreupload',
					function(event, data, previewId, index) { //上传中
						var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
						console.log('文件正在上传');
					}).on(
					"fileuploaded",
					function(event, data, previewId, index) { //一个文件上传成功
						console.log(data.response);
						$("#cover").val(data.response);
						console.log('文件上传成功！' );

					}).on('fileerror', function(event, data, msg) { //一个文件上传失败
				console.log('文件上传失败！' + data.id);
			})
}

/**
 * 点击服务类别input框时
 */
ServicesInfoDlg.onClickServiceSort = function (e, treeId, treeNode) {
	$("#serviceSortName").attr("value", ServicesInfoDlg.ztreeInstance.getSelectedVal());
    $("#serviceSortCode").attr("value",treeNode.pcode);
};

$(function() {
	Feng.initValidator("servicesForm", ServicesInfoDlg.validateFields);
	initFileInput("input-id");
	//加载树
	var ztree = new $ZTree("serviceSortTree", "/services/getServiceSortTree");
    ztree.bindOnClick(ServicesInfoDlg.onClickServiceSort);
    ztree.init();
    ServicesInfoDlg.ztreeInstance = ztree;

    //初始化文本框
      editor = new wangEditor("#serviceDetail");
	  editor.create();
	  editor.txt.html($("#serviceDetailVal").val());
	  ServicesInfoDlg.editor = editor;
	// 初始化封面上传
    var avatarUp = new $WebUpload("cover");
    avatarUp.setUploadBarId("progressBar");
    avatarUp.init();
	$("#nId").change(function() {
		var options = $("#nId option:selected");
		if (null != options.val() && "" != options.val()) {
			$("#nName").val(options.text());
		} else {
			$("#nName").val("");
		}
	});
	// 初始化适用小区
	$.ajax({
		"type" : 'get',
		"url" : Feng.ctxPath + "/neighborhood/list",
		"dataType" : "json",
		"success" : function(data) {
			var value = $("#nId1").val()// 这个值就是你获取的值;
			for (var i = 0; i < data.length; i++) {
				if (value == data[i].nId) {
					$("#nId").append(
							"<option value='" + data[i].nId + "' selected >" + data[i].nName
									+ "</option>");
				}
				else{$("#nId").append(
						"<option value='" + data[i].nId + "'>" + data[i].nName
								+ "</option>");
				}
			}
		}
	});
	
	

	// 设置服务类别select回显
	var opts = document.getElementById("serviceType");
	var value = $("#serviceType1").val()
	if (value != "") {
		for (var i = 0; i < opts.options.length; i++) {
			if (value == opts.options[i].value) {
				opts.options[i].selected = 'selected';
				break;
			}
		}
	}
	
	// 设置收费方式select回显
	var opts = document.getElementById("paymentType");
	var value = $("#paymentType1").val()
	if (value != "") {
		for (var i = 0; i < opts.options.length; i++) {
			if (value == opts.options[i].value) {
				opts.options[i].selected = 'selected';
				break;
			}
		}
	}
	
	var options = $("#paymentType option:selected");
	if("1"==options.val()){
		 $('#price').attr('disabled',false);
		 $('#deposit').val(0);
		 $('#deposit').attr('disabled',true);
	}else if("2"==options.val()){
		 $('#price').attr('disabled',true);
		 $('#deposit').attr('disabled',false);
		 $('#price').val(0);
	}else if("3"==options.val()){
		 $('#price').attr('disabled',true);
		 $('#price').val(0);
		 $('#deposit').attr('disabled',true);
		 $('#deposit').val(0);
	}else{
		 $('#price').attr('disabled',false);
		 $('#deposit').attr('disabled',false);
	}
	
	
	$("#paymentType").change(function() {
		var options = $("#paymentType option:selected");
		if("1"==options.val()){
			 $('#price').attr('disabled',false);
			 $('#deposit').val(0);
			 $('#deposit').attr('disabled',true);
		}else if("2"==options.val()){
			 $('#price').attr('disabled',true);
			 $('#deposit').attr('disabled',false);
			 $('#price').val(0);
		}else if("3"==options.val()){
			 $('#price').attr('disabled',true);
			 $('#price').val(0);
			 $('#deposit').attr('disabled',true);
			 $('#deposit').val(0);
		}else{
			 $('#price').attr('disabled',false);
			 $('#deposit').attr('disabled',false);
		}
			
	});
	
});
