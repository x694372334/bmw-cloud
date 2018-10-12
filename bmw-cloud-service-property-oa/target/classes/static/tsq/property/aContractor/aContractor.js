/**
 * 广告位承包商管理初始化
 */
var AContractor = {
    id: "AContractorTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
AContractor.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '小区名称', field: 'nName', visible: true, align: 'center', valign: 'middle'},
        {title: '承包商名称', field: 'cName', visible: true, align: 'center', valign: 'middle'},
        {title: '负责人', field: 'cPersion', visible: true, align: 'center', valign: 'middle'},
        {title: '分包业务', field: 'cBusiness', visible: true, align: 'center', valign: 'middle'},
        {title: '分包状态', field: 'cStatus', visible: true, align: 'center', valign: 'middle' , 
        	formatter : function(value, row, index) {
	        	if(value == "1"){
					return "合作中";
				}else if(value == "2"){
					return "终止合作";
				}
        	}	
        }
    ];
};

/**
 * 检查是否选中
 */
AContractor.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        AContractor.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加广告位承包商
 */
AContractor.openAddAContractor = function () {
    var index = layer.open({
        type: 2,
        title: '添加广告位承包商',
        area: ['800px', '400px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/aContractor/aContractor_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看广告位承包商详情
 */
AContractor.openAContractorDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '广告位承包商详情',
            area: ['800px', '400px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/aContractor/aContractor_update/' + AContractor.seItem.cId
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看广告位承包商详情
 */
AContractor.openAContractorDetail2 = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '广告位承包商详情',
            area: ['800px', '400px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/aContractor/aContractor_detail/' + AContractor.seItem.cId
        });
        this.layerIndex = index;
    }
};

/**
 * 删除广告位承包商
 */
AContractor.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/aContractor/delete", function (data) {
            Feng.success("删除成功!");
            AContractor.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("acontractorInfoId",this.seItem.cId);
        ajax.start();
    }
};

/**
 * 查询广告位承包商列表
 */
AContractor.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    queryData['nName'] = $("#nName").val();
    AContractor.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = AContractor.initColumn();
    var table = new BSTable(AContractor.id, "/aContractor/list", defaultColunms);
    table.setPaginationType("client");
    AContractor.table = table.init();
});
