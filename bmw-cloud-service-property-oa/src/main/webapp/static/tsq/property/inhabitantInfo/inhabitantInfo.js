/**
 * 住户管理管理初始化
 */
var InhabitantInfo = {
    id: "InhabitantInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
InhabitantInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '小区', field: 'nName', visible: true, align: 'center', valign: 'middle'},
        {title: '楼宇', field: 'bName', visible: true, align: 'center', valign: 'middle'},
        {title: '单元', field: 'rUnitnum', visible: true, align: 'center', valign: 'middle',
        	formatter : function(value, row, index) {
            	return value+"单元";
            }
        },
        {title: '房间号', field: 'rRoomnum', visible: true, align: 'center', valign: 'middle'},
        {title: '住户', field: 'iName', visible: true, align: 'center', valign: 'middle'},
        {title: '住户身份', field: 'iIdentity', visible: true, align: 'center', valign: 'middle', 
        	formatter : function(value, row, index) {
            	if(value == "1"){
            		return "业主" ;
            	}else if(value == "2"){
            		return "业主家人" ;
            	}else if(value == "3"){
            		return "业主亲属";
            	}else if(value == "4"){
            		return "租户";
            	}
            }				
        },
        {title: 'APP认证状态', field: 'ifCertification', visible: true, align: 'center', valign: 'middle',
        	formatter : function(value, row, index) {
            	if(value == "0"){
            		return "未认证" ;
            	}else if(value == "1"){
            		return "已认证" ;
            	}else if(value == "2"){
            		return "未通过";
            	}
            }			
        }
    ];
};

/**
 * 检查是否选中
 */
InhabitantInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        InhabitantInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加住户管理
 */
InhabitantInfo.openAddInhabitantInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加住户管理',
        area: ['800px', '600px'], //宽高
        fix: true, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/inhabitantInfo/inhabitantInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看住户管理详情
 */
InhabitantInfo.openInhabitantInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '住户管理详情',
            area: ['800px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/inhabitantInfo/inhabitantInfo_update/' + InhabitantInfo.seItem.iId
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看审批申请单详情
 */
InhabitantInfo.openInhabitantInfoDetail4 = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '住户认证审批',
            area: ['800px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/inhabitantInfo/inhabitantInfo_approval/' + InhabitantInfo.seItem.iId
        });
        this.layerIndex = index;
    }
};

/**
 * 查看详情页面
 */
InhabitantInfo.openInhabitantInfoDetail2 = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '住户管理详情',
            area: ['800px', '600px'], //宽高
            fix: true, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/inhabitantInfo/inhabitantInfo_detail/' + InhabitantInfo.seItem.iId
        });
        this.layerIndex = index;
    }
};

/**
 * 查看详情页面
 */
InhabitantInfo.openInhabitantInfoDetail3 = function () {
        var index = layer.open({
            type: 2,
            title: '住户管理详情',
            area: ['800px', '600px'], //宽高
            fix: true, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/inhabitantInfo/inhabitantInfo_reader'
        });
        this.layerIndex = index;
};

/**
 * 删除住户管理
 */
InhabitantInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/inhabitantInfo/delete", function (data) {
            Feng.success("删除成功!");
            InhabitantInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("inhabitantInfoId",this.seItem.iId);
        ajax.start();
    }
};


/**
 * 查询住户管理列表
 */
InhabitantInfo.search = function () {
    var queryData = {};
    queryData['xqName'] = $("#xqName").val();
    queryData['lyName'] = $("#lyName").val();
    queryData['fjName'] = $("#fjName").val();
    queryData['zhName'] = $("#zhName").val();
    queryData['phoneNo'] = $("#phoneNo").val();
    InhabitantInfo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = InhabitantInfo.initColumn();
    var table = new BSTable(InhabitantInfo.id, "/inhabitantInfo/list", defaultColunms);
    table.setPaginationType("client");
    InhabitantInfo.table = table.init();
});
