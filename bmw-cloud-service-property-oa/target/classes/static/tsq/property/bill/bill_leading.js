/**
 * 初始化账单明细详情对话框
 */
var BillLeading = {
		id: "BillLeadingTable",	//表格id
		billLeadingData : {}
};

var jsondata ;
/**
 * 初始化表格的列
 */
BillLeading.initColumn = function () {
    return [
        /*{field: 'selectItem', checkbox: false ,radio: false},*/
            /*{title: '主键id', field: 'id', visible: false, align: 'center', valign: 'middle'},*/
            {title: '账单编号', field: 'billNo', visible: false, align: 'center', valign: 'middle'},
            {title: '小区', field: 'neighborhoodsName', visible: true, align: 'center', valign: 'middle'},
            {title: '关联费用标准主键id', field: 'standardId', visible: false, align: 'center', valign: 'middle'},
            {title: '业主ID', field: 'ownerId', visible: false, align: 'center', valign: 'middle'},
            {title: '住户', field: 'ownerName', visible: true, align: 'center', valign: 'middle'},
            {title: '房号/车位号', field: 'objectNo', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号', field: 'ownerPhone', visible: true, align: 'center', valign: 'middle'},
            {title: '费用名称', field: 'standardName', visible: true, align: 'center', valign: 'middle'},
            {title: '费用开始时间', field: 'costStartTime', visible: true, align: 'center', valign: 'middle'},
            {title: '费用结束时间', field: 'costEndTime', visible: true, align: 'center', valign: 'middle'},
            {title: '应缴合计', field: 'shouldTotal', visible: true, align: 'center', valign: 'middle'},
            {title: '滞纳金', field: 'overdueFine', visible: true, align: 'center', valign: 'middle'},
           /* {title: '催缴次数', field: 'askCount', visible: true, align: 'center', valign: 'middle'},
            {title: '申请优惠状态（待审批、审批通过、已拒绝）', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '优惠比例', field: 'discountRate', visible: true, align: 'center', valign: 'middle'},*/
            {title: '优惠金额', field: 'discountAmount', visible: true, align: 'center', valign: 'middle'},
            {title: '实缴合计', field: 'paidTotal', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'isFee', visible: true, align: 'center', valign: 'middle',
            	formatter : function (value, row, index) {
                    if (value === 0) {
                        return '已缴';
                    }
                    if (value === 1) {
                        return '未缴';
                    }
                }   }
           /* {title: '缴费方式', field: 'payMode', visible: true, align: 'center', valign: 'middle'},
            {title: '支付方式：1、现金，2、支付宝，3、微信', field: 'payWay', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'},
            {title: '退款金额（多次退款累加和）', field: 'refundAmount', visible: true, align: 'center', valign: 'middle'}*/
    ];
};

/**
 * 关闭此对话框
 */
BillLeading.close = function() {
    parent.layer.close(window.parent.Bill.layerIndex);
}

BillLeading.downloadExcel = function(){
	window.location.href = Feng.ctxPath + '/static/bmw/property/roomInfo/zddr.xls';
}

BillLeading.download = function(){

	    //提交信息
	    var ajax = new $ax(Feng.ctxPath + "/bill/load/ddd", function(data){
	        Feng.success("下载成功!");
	        //window.parent.Bill.table.refresh();
	        BillLeading.close();
	    },function(data){
	        Feng.error("下载失败!" + data.responseJSON.message + "!");
	    });
	    ajax.start();
}

fodderType = function() {
    return $("#neighborhoodsId").val();
};

function initFileInput(ctrlName) {
	var control = $('#' + ctrlName);
	control
			.fileinput({
				language : 'zh', //设置语言
				uploadUrl : Feng.ctxPath + "/bill/readModel", //上传的地址
				allowedFileExtensions : [ 'xls','xlsx' ],
				//allowedFileExtensions: ['jpg', 'gif', 'png','doc','docx','pdf','ppt','pptx','txt'],//接收的文件后缀
				maxFilesNum : 1,//上传最大的文件数量
				//uploadExtraData:{"id": 1, "fileName":'123.mp3'},
				uploadAsync : true, //默认异步上传
				showUpload : false, //是否显示上传按钮
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
				uploadExtraData: function(previewId, index) {   //额外参数的关键点
                    var obj = {};
                    obj.neighborhoodsId = fodderType();
                    return obj;
                }
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
						console.log(data.response);
						jsondata = data.response;
					/*	var defaultColunms = BillLeading.initColumn();
						var table = new BSTable(BillLeading.id, "",
								defaultColunms);
						table.setPaginationType("client");
						BillLeading.table = table.init();
						$('#BillLeadingTable').bootstrapTable('load',
								data.response);

						console.log('文件上传成功！' + data.id);*/
						 if(200==data.response.code){
							   Feng.success("导入成功!");
							   window.parent.Bill.table.refresh();
							   BillLeading.close();
						   }else{
							   Feng.error("导入失败!" );
						   }

					}).on('fileerror', function(event, data, msg) { //一个文件上传失败
				console.log('文件上传失败！' + data.id);
			})
}

BillLeading.upload = function(){
	if(""==$("#neighborhoodsId").val()){
		Feng.info("请选择小区!" );
		return;
	}
	$("#input-id").fileinput("upload");
}


$(function() {
	initFileInput("input-id");
	//初始化适用小区
    $.ajax({
        "type" : 'get',
        "url": Feng.ctxPath + "/neighborhood/list",
        "dataType" : "json",
        "success" : function(data) {
            for(var i = 0 ; i < data.length; i++ ){
            		$("#neighborhoodsId").append("<option value='"+data[i].nId+"'>"+data[i].nName+"</option>");
            }
        }
    });
});
