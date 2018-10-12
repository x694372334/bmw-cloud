/**
 * 广告位分包信息管理初始化
 */
var SelectBulter = {
    id: "SelectBulterTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
SelectBulter.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '管家姓名', field: 'uname', visible: true, align: 'center', valign: 'middle'},
        {title: '管家性别', field: 'iGender', visible: true, align: 'center', valign: 'middle',
        	formatter : function(value, row, index) {	
        		if(value=="1"){
        			return "男";
        		}else if(value=="2"){
        			return "女";
        		}else{
        			return "其他";
        		}
        	}
        },
        {title: '管家电话', field: 'iPhoneno', visible: true, align: 'center', valign: 'middle' , 
        	formatter : function(value, row, index) {
        		if(value == ""){
        			return "未填电话" ;
        		}else{
        			return value;
        		}
            }			
        },
        {title: '所在小区', field: 'nName', visible: true, align: 'center', valign: 'middle'},
        {title: '所在楼宇', field: 'bName', visible: true, align: 'center', valign: 'middle'},
        {title: '管家房屋', field: 'rRoomnum', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
SelectBulter.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        SelectBulter.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加广告位分包信息
 */
SelectBulter.openAddSelectBulter = function () {
    var index = layer.open({
        type: 2,
        title: '添加广告位分包信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/SelectBulter/SelectBulter_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看广告位分包信息详情
 */
SelectBulter.openSelectBulterDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '广告位分包信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/SelectBulter/SelectBulter_update/' + SelectBulter.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除广告位分包信息
 */
SelectBulter.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/SelectBulter/delete", function (data) {
            Feng.success("删除成功!");
            SelectBulter.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("SelectBulterId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询广告位分包信息列表
 */
SelectBulter.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    SelectBulter.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = SelectBulter.initColumn();
    var table = new BSTable(SelectBulter.id, "/selectBulter/list", defaultColunms);
    table.setPaginationType("client");
    SelectBulter.table = table.init();
});
