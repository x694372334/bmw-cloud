/**
 * 抄表基础管理初始化
 */
var MBaseInfo = {
    id: "MBaseInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MBaseInfo.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
        {title: '小区名', field: 'nName', visible: true, align: 'center', valign: 'middle'},
        {title: '楼宇名', field: 'bName', visible: true, align: 'center', valign: 'middle'},
        {title: '房间号', field: 'rRoomnum', visible: true, align: 'center', valign: 'middle'},
        {title: '设置类别', field: 'mType', visible: true, align: 'center', valign: 'middle' ,
        	formatter : function(value, row, index) {
        		var data = "";
        		if(value == "1"){
        			return "水表";
        		}else if(value == "2"){
        			return "电表";
        		}
            }		
        },
        {title: '倍率', field: 'mMultiple', visible: true, align: 'center', valign: 'middle'},
        {title: '耗损', field: 'mWastage', visible: true, align: 'center', valign: 'middle'},
        {title: '公摊', field: 'mCommonality', visible: true, align: 'center', valign: 'middle'},
        {title: '月份', field: 'tMonth', visible: true, align: 'center', valign: 'middle'},
        {title: '表数', field: 'tNum', visible: true, align: 'center', valign: 'middle'},
        {title: '用量', field: 'uAmount', visible: true, align: 'center', valign: 'middle'},
        {title: '表号', field: 'tableNumber', visible: true, align: 'center', valign: 'middle'},
        {title: '审核', field: 'isApproval', visible: true, align: 'center', valign: 'middle' ,
        	formatter : function(value, row, index) {
        		var data = "";
        		if(value == "1"){
        			return "审核通过";
        		}else if(value == "2"){
        			return "审核不通过";
        		}else{
        			return "未审核";
        		}
            }			
        }
//        {title: '操作', field: '', visible: true, align: 'center', valign: 'middle' ,
//        	formatter:function(value,row,index) {
//        	return [
//        	'<button class="btn btn-primary" id="cancel"  onClick="setDosage()"><i class="fa fa-eraser"></i>录入用量</button>'
//        	]}}
        
    ];
};

/**
 * 检查是否选中
 */
MBaseInfo.check = function () {
	var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        MBaseInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加抄表基础
 */
MBaseInfo.openAddMBaseInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加抄表基础',
        area: ['500px', '700px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/mBaseInfo/mBaseInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看抄表基础详情
 */
MBaseInfo.openMBaseInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '抄表基础详情',
            area: ['500px', '800px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/mBaseInfo/mBaseInfo_update/' + MBaseInfo.seItem.sId
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看抄表基础详情
 */
MBaseInfo.openMBaseInfoDetail2 = function () {
    if (this.check()) {
    	if(null==MBaseInfo.seItem.tableNumber||""==MBaseInfo.seItem.tableNumber){
    		Feng.error("清先录入表号!");
    		return ;
    	}
        var index = layer.open({
            type: 2,
            title: '抄表基础详情',
            area: ['500px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/mBaseInfo/mBaseInfo_dosage/' + MBaseInfo.seItem.sId
        });
        this.layerIndex = index;
    }
};



function setDosage(){
	MBaseInfo.openMBaseInfoDetail2();
}

/**
 * 删除抄表基础
 */
MBaseInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/mBaseInfo/delete", function (data) {
            Feng.success("删除成功!");
            MBaseInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("mBaseInfoId",this.seItem.sId);
        ajax.start();
    }
};

/**
 * 查询抄表基础列表
 */
MBaseInfo.search = function () {
    var queryData = {};
    queryData['nName'] = $("#nName").val();
    queryData['bName'] = $("#bName").val();
    queryData['rRoomnum'] = $("#rRoomnum").val();
    MBaseInfo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = MBaseInfo.initColumn();
    var table = new BSTable(MBaseInfo.id, "/mBaseInfo/list", defaultColunms);
    table.setPaginationType("client");
    MBaseInfo.table = table.init();
});
