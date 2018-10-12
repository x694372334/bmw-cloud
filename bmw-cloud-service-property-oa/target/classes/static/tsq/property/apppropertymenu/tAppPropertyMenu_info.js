/**
 * 初始化apppropertymenu详情对话框
 */
var TAppPropertyMenuInfoDlg = {
    tAppPropertyMenuInfoData : {},
    validateFields: {
    	menuName: {
            validators: {
                notEmpty: {
                    message: '职位名称不能为空'
                   
                },
            }
        },
        menuSort : {
			validators : {
				numeric : {
					message : "必须输入数字",
				},
				regexp : {
					message : "必须为整数",
					regexp : "^[0-9]*[1-9][0-9]*$"
				},
				stringLength : {
					message : "最多输入3位",
					min : "1",
					max : "3"
				}
			}
		}
    }
};


/**
 * 验证数据是否为空
 */
TAppPropertyMenuInfoDlg.validate = function () {
    $('#appMenuForm').data("bootstrapValidator").resetForm();
    $('#appMenuForm').bootstrapValidator('validate');
    return $("#appMenuForm").data('bootstrapValidator').isValid();
};


/**
 * 清除数据
 */
TAppPropertyMenuInfoDlg.clearData = function() {
    this.tAppPropertyMenuInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TAppPropertyMenuInfoDlg.set = function(key, val) {
    this.tAppPropertyMenuInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TAppPropertyMenuInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TAppPropertyMenuInfoDlg.close = function() {
    parent.layer.close(window.parent.TAppPropertyMenu.layerIndex);
}

/**
 * 收集数据
 */
TAppPropertyMenuInfoDlg.collectData = function() {
    this
    .set('mId')
    .set('menuName')
    .set('menuDescription')
    .set('menuSort')
    .set('menuUrl')
    .set('menuCode')
    .set('menuIcon')
    .set('menuStatus')
    .set('createManId')
    .set('createMan')
    .set('createTime')
    .set('editManId')
    .set('editMan')
    .set('editTime')
    .set('isDelete');
}

/**
 * 提交添加
 */
TAppPropertyMenuInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tAppPropertyMenu/add", function(data){
        Feng.success("添加成功!");
        window.parent.TAppPropertyMenu.table.refresh();
        TAppPropertyMenuInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tAppPropertyMenuInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TAppPropertyMenuInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tAppPropertyMenu/update", function(data){
        Feng.success("修改成功!");
        window.parent.TAppPropertyMenu.table.refresh();
        TAppPropertyMenuInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tAppPropertyMenuInfoData);
    ajax.start();
}

/**
 * 下载文档
 */
TAppPropertyMenuInfoDlg.download = function() {
		 var form = $("<form>");
		    form.attr('style', 'display:none');
		    form.attr('target', '');
		    form.attr('method', 'post');
		    form.attr('action', Feng.ctxPath + "/tAppPropertyMenu/download");
		    
		    var input = $('<input>');
		    input.attr('type', 'hidden');
		    input.attr('name', 'menuIcon');
		    input.attr('value', $("#menuIcon").val());
		    
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
				uploadUrl : "/tAppPropertyMenu/upload", //上传的地址
				allowedFileExtensions : [ 'jpg', 'gif', 'png'],
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
						$("#menuIcon").val(data.response);
						console.log('文件上传成功！' + data.id);

					}).on('fileerror', function(event, data, msg) { //一个文件上传失败
				console.log('文件上传失败！' + data.id);
			})
}


$(function() {
	Feng.initValidator("appMenuForm", TAppPropertyMenuInfoDlg.validateFields);
	initFileInput("input-id");
});
