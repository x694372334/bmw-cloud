/**
 * 抄表记录管理初始化
 */
var MRecord = {
    id: "MRecordTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MRecord.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '小区名', field: 'nName', visible: true, align: 'center', valign: 'middle'},
        {title: '楼宇名', field: 'bName', visible: true, align: 'center', valign: 'middle'},
        {title: '房间号', field: 'rRoomnum', visible: true, align: 'center', valign: 'middle'},
        {title: '表类型', field: 'mType', visible: true, align: 'center', valign: 'middle',
        	formatter : function(value, row, index) {
            	if(value == "1"){
            		return "水表" ;
            	}else if(value == "2"){
            		return "电表" ;
            	}else{
            		return "未知";
            	}
            }
        },
        {title: '月份', field: 'tMonth', visible: true, align: 'center', valign: 'middle'},
        
        {title: '表数', field: 'tNum', visible: true, align: 'center', valign: 'middle'},        
        {title: '用量', field: 'uAmount', visible: true, align: 'center', valign: 'middle'},
        {title: '表号', field: 'tableNumber', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
MRecord.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        MRecord.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加抄表记录
 */
MRecord.openAddMRecord = function () {
    var index = layer.open({
        type: 2,
        title: '添加抄表记录',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/mRecord/mRecord_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看抄表记录详情
 */
MRecord.openMRecordDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '抄表记录详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/mRecord/mRecord_update/' + MRecord.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除抄表记录
 */
MRecord.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/mRecord/delete", function (data) {
            Feng.success("删除成功!");
            MRecord.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("mRecordId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询抄表记录列表
 */
MRecord.search = function () {
    var queryData = {};
    queryData['nName'] = $("#nName").val();
    queryData['bName'] = $("#bName").val();
    queryData['rRoomnum'] = $("#rRoomnum").val();
    MRecord.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = MRecord.initColumn();
    var table = new BSTable(MRecord.id, "/mRecord/list", defaultColunms);
    table.setPaginationType("client");
    MRecord.table = table.init();
});
