/**
 * 车位管理管理初始化
 */
var ParkingInfo = {
    id: "ParkingInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ParkingInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '小区名称', field: 'nName', visible: true, align: 'center', valign: 'middle'},
        {title: '车位类型', field: 'pType', visible: true, align: 'center', valign: 'middle',
        	formatter : function(value, row, index) {
            	if(value == "1"){
            		return "租用" ;
            	}else if(value == "2"){
            		return "出售" ;
            	}
            }			
        },
        {title: '车位号', field: 'pNum', visible: true, align: 'center', valign: 'middle'},
        {title: '面积', field: 'pArea', visible: true, align: 'center', valign: 'middle'},
        {title: '车位状态', field: 'pStatus', visible: true, align: 'center', valign: 'middle',
        	formatter : function(value, row, index) {
            	if(value == "1"){
            		return "空闲" ;
            	}else if(value == "2"){
            		return "已售" ;
            	}else if(value == "3"){
            		return "已租" ;
            	}else if(value == "4"){
            		return "自用" ;
            	}
            	
            }			
        },
//        {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'},
        {title: '住户姓名', field: 'iName', visible: true, align: 'center', valign: 'middle'},
        {title: '车牌号码', field: 'vNumber', visible: true, align: 'center', valign: 'middle'},
        {title: '收费项个数', field: 'sCount', visible: true, align: 'center', valign: 'middle'},
        {title: '操作', field: '', visible: true,width: '180px', align: 'center', valign: 'middle' ,
        	formatter:function(value,row,index) {
        	return [
        	'<button class="btn btn-primary" id="cancel"  onClick="setRoomAndCar()"><i class="fa fa-eraser"></i>关联住户</button>'
        	]}}
    ];
};

/**
 * 检查是否选中
 */
ParkingInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ParkingInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加车位管理
 */
ParkingInfo.openAddParkingInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加车位管理',
        area: ['800px', '400px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/parkingInfo/parkingInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看车位管理详情
 */
ParkingInfo.openParkingInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '车位管理详情',
            area: ['800px', '400px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/parkingInfo/parkingInfo_update/' + ParkingInfo.seItem.pId
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看车位管理详情
 */
ParkingInfo.openParkingInfoDetail2 = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '车位管理详情',
            area: ['800px', '400px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/parkingInfo/parkingInfo_detail/' + ParkingInfo.seItem.pId
        });
        this.layerIndex = index;
    }
};


/**
 * 打开查看车位管理详情
 */
ParkingInfo.openParkingInfoDetail3 = function () {
        var index = layer.open({
            type: 2,
            title: '车位批量导入',
            area: ['600px', '560px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/parkingInfo/parkingInfo_reader/'
        });
        this.layerIndex = index;
};
/**
 * 删除车位管理
 */
ParkingInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/parkingInfo/delete", function (data) {
            Feng.success("删除成功!");
            ParkingInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("parkingInfoId",this.seItem.pId);
        ajax.start();
    }
};

ParkingInfo.openbatchStandards = function () {
	if (this.check()) {
	//判断是否已经关联了收费项
	var	isAssociated = false;
	var ajax = new $ax(Feng.ctxPath + "/parkingInfo/isAssociated/"+ParkingInfo.seItem.pId, 
		function (data) {
		 	isAssociated= data;	
        }
    );
    ajax.set("parkingInfoId",this.seItem.pId);
    ajax.start();
    
    if(isAssociated){
    	Feng.info("当前车位已经关联了收费项！");
    	return false;	
    }
		
	 var index = layer.open({
	        type: 2,
	        title: '关联收费项',
	        area: ['800px', '620px'], //宽高
	        fix: false, //不固定
	        maxmin: true,
	        content: Feng.ctxPath + '/batchStandards/batchStandards_add/'+ParkingInfo.seItem.pId
	    });
	    this.layerIndex = index;
	}
}

function setRoomAndCar(){
	ParkingInfo.openParkingInfoRoomAndCar();
}

ParkingInfo.openParkingInfoRoomAndCar = function(){
	 if (this.check()) {
	        var index = layer.open({
	            type: 2,
	            title: '房屋管理详情',
	            area: ['600px', '520px'], //宽高
	            fix: false, //不固定
	            maxmin: true,
	            content: Feng.ctxPath + '/parkingInfo/parkingInfo_vehicle/' + ParkingInfo.seItem.pId
	        });
	        this.layerIndex = index;
	 }
}

/**
 * 查询车位管理列表
 */
ParkingInfo.search = function () {
    var queryData = {};
    queryData['pNum'] = $("#pNum").val();
    queryData['nName'] = $("#nName").val();
    ParkingInfo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ParkingInfo.initColumn();
    var table = new BSTable(ParkingInfo.id, "/parkingInfo/list", defaultColunms);
    table.setPaginationType("client");
    ParkingInfo.table = table.init();
    
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
