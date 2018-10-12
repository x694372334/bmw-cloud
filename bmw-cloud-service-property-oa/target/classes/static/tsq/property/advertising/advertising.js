/**
 * 广告位信息管理初始化
 */
var Advertising = {
    id: "AdvertisingTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Advertising.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '小区名', field: 'nName', visible: true, align: 'center', valign: 'middle'},
        {title: '广告位名称', field: 'aName', visible: true, align: 'center', valign: 'middle'},
        {title: '广告位类型', field: 'aType', visible: true, align: 'center', valign: 'middle' ,
        	formatter : function(value, row, index) {
	        	if(value == "1"){
					return "平面";
				}else if(value == "2"){
					return "LED多媒体";
				}
        	}
        },
        {title: '广告位状态', field: 'aStatus', visible: true, align: 'center', valign: 'middle',
        	formatter : function(value, row, index) {
	        	if(value == "1"){
					return "空闲";
				}else if(value == "2"){
					return "自用";
				}else{
					return "外包";
				}
        	}
        },
        {title: '分包商', field: 'cName', visible: true, align: 'center', valign: 'middle'},
        {title: '分包开始时间', field: 'cBTime', visible: true, align: 'center', valign: 'middle'}
//        {title: '分包结束时间', field: 'cETime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Advertising.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Advertising.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加广告位信息
 */
Advertising.openAddAdvertising = function () {
    var index = layer.open({
        type: 2,
        title: '添加广告位信息',
        area: ['800px', '400px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/advertising/advertising_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看广告位信息详情
 */
Advertising.openAdvertisingDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '广告位信息详情',
            area: ['800px', '400px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/advertising/advertising_update/' + Advertising.seItem.aId
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看广告位信息详情
 */
Advertising.openAdvertisingDetail2 = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '广告位信息详情',
            area: ['800px', '400px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/aCInfo/aCInfo_update/' + Advertising.seItem.aId
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看广告位信息详情
 */
Advertising.openAdvertisingDetail3 = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '广告位信息详情',
            area: ['800px', '400px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/advertising/advertising_detail/' + Advertising.seItem.aId
        });
        this.layerIndex = index;
    }
};

/**
 * 删除广告位信息
 */
Advertising.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/advertising/delete", function (data) {
            Feng.success("删除成功!");
            Advertising.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("advertisingId",this.seItem.aId);
        ajax.start();
    }
};

/**
 * 查询广告位信息列表
 */
Advertising.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Advertising.table.refresh({query: queryData});
};

Advertising.text = function(){
	if (this.check()) {
		if(Advertising.seItem.cName=="未分包"){
	        var index = layer.open({
	            type: 2,
	            title: '广告位关联分包商',
	            area: ['500px', '400px'], //宽高
	            fix: false, //不固定
	            maxmin: true,
	            content: Feng.ctxPath + '/advertising/advertising_contractor/' + Advertising.seItem.aId
	        });
	        this.layerIndex = index;
		}else{
			 Feng.success("请先解除分包!");
		}
    }
}

Advertising.text1 = function(){
	if (this.check()) {
		if(Advertising.seItem.cName!="未分包"){
	        var index = layer.open({
	            type: 2,
	            title: '广告位解除分包商',
	            area: ['500px', '300px'], //宽高
	            fix: false, //不固定
	            maxmin: true,
	            content: Feng.ctxPath + '/advertising/advertising_relieve/' + Advertising.seItem.aId
	        });
	        this.layerIndex = index;
		}else{
			 Feng.success("请先处理分包!");
		}
    }
}

function contractor(){
	Advertising.text();
}

$(function () {
    var defaultColunms = Advertising.initColumn();
    var table = new BSTable(Advertising.id, "/advertising/list", defaultColunms);
    table.setPaginationType("client");
    Advertising.table = table.init();
});
