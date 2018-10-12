/**
 * 初始化helpDocument详情对话框
 */
var HelpDocumentInfoDlg = {
    helpDocumentInfoData : {},
	validateFields : {
		title : {
			validators : {
				notEmpty : {
					message : '标题不能为空'
				}
			}
		},
		helpDocumentType : {
			validators : {
				notEmpty : {
					message : '请选择分类'
				}
			}
		}

	},
    editor: null
};

var fileId = "";
/**
 * 清除数据
 */
HelpDocumentInfoDlg.clearData = function() {
    this.helpDocumentInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HelpDocumentInfoDlg.set = function(key, val) {
    this.helpDocumentInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HelpDocumentInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
HelpDocumentInfoDlg.close = function() {
    parent.layer.close(window.parent.HelpDocument.layerIndex);
}

/**
 * 收集数据
 */
HelpDocumentInfoDlg.collectData = function() {
    this
    .set('id')
    .set('helpDocumentType')
    .set('helpDocumentTypeId')
    .set('title')
    .set('content')
    .set('createId')
    .set('createMan')
    .set('createTime')
    .set('editManId')
    .set('editMan')
    .set('editTime')
    .set('fileId')
    .set('fileName')
    .set('filePath')
    .set('fileFormat')
    .set('keyword');
    this.helpDocumentInfoData['content'] = HelpDocumentInfoDlg.editor.txt.html();
}


/**
 * 验证数据是否为空
 */
HelpDocumentInfoDlg.validate = function () {
    $('#helpDocumentForm').data("bootstrapValidator").resetForm();
    $('#helpDocumentForm').bootstrapValidator('validate');
    return $("#helpDocumentForm").data('bootstrapValidator').isValid();
};


/**
 * 提交添加
 */
HelpDocumentInfoDlg.addSubmit = function() {
	
	  if(0==$("#helpDocumentTypeId").val()){
	    	Feng.info("请选择类别!");
	    	return;
	    }

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/helpDocument/add", function(data){
        Feng.success("添加成功!");
        window.parent.HelpDocument.table.refresh();
        HelpDocumentInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.helpDocumentInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
HelpDocumentInfoDlg.editSubmit = function() {

	  if(0==$("#helpDocumentTypeId").val()){
	    	Feng.info("请选择类别!");
	    	return;
	    }
	  
    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/helpDocument/update", function(data){
        Feng.success("修改成功!");
        window.parent.HelpDocument.table.refresh();
        HelpDocumentInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.helpDocumentInfoData);
    ajax.start();
}

HelpDocumentInfoDlg.showhelpDocumentTypeSelectTree = function () {
    Feng.showInputTree("helpDocumentTypeId", "pcodeTreeDiv", 15, 34);
};

/**
 * 点击父级编号input框时
 */
HelpDocumentInfoDlg.onClickType = function (e, treeId, treeNode) {
	$("#helpDocumentType").attr("value", HelpDocumentInfoDlg.ztreeInstance.getSelectedVal());
    $("#helpDocumentTypeId").attr("value",treeNode.id);
};

function initFileInput(ctrlName) {
	var control = $('#' + ctrlName);
	control.fileinput({
				language : 'zh', //设置语言
				uploadUrl : "/helpDocument/upload", //上传的地址
				//allowedFileExtensions : [ 'xls' ],
				allowedFileExtensions: ['jpg', 'gif', 'png','doc','docx','pdf','ppt','pptx','txt','xls','xlsx'],//接收的文件后缀
				maxFilesNum : 5,//上传最大的文件数量
				//uploadExtraData:{"id": 1, "fileName":'123.mp3'},
				uploadAsync : true, //默认异步上传
				showUpload : true, //是否显示上传按钮
				showRemove : true, //显示移除按钮
				showPreview : true, //是否显示预览
				showCaption : false,//是否显示标题
				browseClass : "btn btn-primary", //按钮样式
				//dropZoneEnabled: true,//是否显示拖拽区域
				//minImageWidth: 50, //图片的最小宽度
				//minImageHeight: 50,//图片的最小高度
				//maxImageWidth: 1000,//图片的最大宽度
				//maxImageHeight: 1000,//图片的最大高度
				maxFileSize : 0,//单位为kb，如果为0表示不限制文件大小
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
						var form = data.form, files = data.files, extra = data.extra, response = data.response, reader = data.reader;
						console.log('文件正在上传');
					}).on(
					"fileuploaded",
					function(event, data, previewId, index) { //一个文件上传成功
						fileId = fileId +  data.response +",";
						console.log(fileId);
						console.log(data.response);
						console.log('文件上传成功！' );
						$("#fileId").val(fileId);
					}).on('fileerror', function(event, data, msg) { //一个文件上传失败
				console.log('文件上传失败！' + data.id);
			})
}

//点击附件下载
function clickFile(a) {
    var form = $("<form>");
    form.attr('style', 'display:none');
    form.attr('target', '');
    form.attr('method', 'post');
    form.attr('action', Feng.ctxPath + "/helpDocument/downloadFile");
    
    var input1 = $('<input>');
    input1.attr('type', 'hidden');
    input1.attr('name', 'filePath');
    input1.attr('value', a.name);
    
    var input2 = $('<input>');
    input2.attr('type', 'hidden');
    input2.attr('name', 'fileName');
    input2.attr('value', a.innerText);

    $('body').append(form);
    form.append(input1);
    form.append(input2);
    
    form.submit();
    form.remove();

}


$(function() {
	Feng.initValidator("helpDocumentForm", HelpDocumentInfoDlg.validateFields);
	
	fileId=$("#fileId").val();
	
	initFileInput("input-id");
	// 分类树
	var ztree = new $ZTree("pcodeTree", "/helpDocumentType/selectHelpDocumentTypeTreeList");
    ztree.bindOnClick(HelpDocumentInfoDlg.onClickType);
    ztree.init();
    HelpDocumentInfoDlg.ztreeInstance = ztree;
    
  //初始化编辑器
    var E = window.wangEditor;
    var editor = new E('#editor');
    editor.create();
    editor.txt.html($("#contentVal").val());
    HelpDocumentInfoDlg.editor = editor;
    if(null!=$("#id").val()&&""!=$("#id").val()){
    // 查询附件
    var ajax = new $ax(Feng.ctxPath + "/helpDocument/findFileById/"+$("#id").val(), function(data){
    	console.log(data);
    	if(null!=data){
    	for (var i = 0; i < data.length; i++) {
    	$("#fileList").append("<a   href=" + data[i].filePath + " >"+ data[i].fileName + "</a>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
    	}
    	}
    },function(data){
    });
    ajax.start();
}
});
