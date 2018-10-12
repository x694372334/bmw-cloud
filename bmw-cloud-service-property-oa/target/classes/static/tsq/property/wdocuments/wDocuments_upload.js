/**
 * 初始化对话框
 */
var WDocumentsUpload = {
};

/**
 * 关闭此对话框
 */
WDocumentsUpload.close = function() {
    parent.layer.close(window.parent.WDocuments.layerIndex);
}

function initFileInput(ctrlName) {
	var control = $('#' + ctrlName);
	control.fileinput({
				language : 'zh', //设置语言
				uploadUrl : "/wDocuments/upload", //上传的地址
				allowedFileExtensions: ['jpg', 'gif', 'png','doc','docx','pdf','ppt','pptx','txt','xls','xlsx'],//接收的文件后缀
				maxFilesNum : 5,//上传最大的文件数量
				uploadAsync : true, //默认异步上传
				showUpload : false, //是否显示上传按钮
				showRemove : true, //显示移除按钮
				showPreview : true, //是否显示预览
				showCaption : false,//是否显示标题
				browseClass : "btn btn-primary", //按钮样式
				maxFileSize : 0,//单位为kb，如果为0表示不限制文件大小
				enctype : 'multipart/form-data',
				uploadExtraData: function(previewId, index) {   //额外参数的关键点
					var data = {};
					data.title = $("#title").val();
					data.sharingLevel = $("#sharingLevel").val();
					data.dType = $("#dType").val();
                    return data;
                }

			})
			.on(
					'filepreupload',
					function(event, data, previewId, index) { //上传中
						var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
						console.log('文件正在上传');
					}).on(
					"fileuploaded",
					function(event, data, previewId, index) { //一个文件上传成功
						Feng.success("上传成功!");
						window.parent.WDocuments.table.refresh();
						WDocumentsUpload.close();
						console.log('文件上传成功！' );

					}).on('fileerror', function(event, data, msg) { //一个文件上传失败
				console.log('文件上传失败！' + data.id);
			})
}

WDocumentsUpload.upload = function(){
	$("#input-id").fileinput("upload");
}

$(function() {
	initFileInput("input-id");
});
