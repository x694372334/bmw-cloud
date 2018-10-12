/**
 * 房屋管理管理初始化
 */
var RoomInfo = {
    id: "RoomInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
RoomInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '小区', field: 'nName', visible: true, align: 'center', valign: 'middle'},
        {title: '楼宇', field: 'bName', visible: true, align: 'center', valign: 'middle'},
        {title: '单元', field: 'rUnitnum', visible: true, align: 'center', valign: 'middle',
        	formatter : function(value, row, index) {
            	return value+"单元";
            }		
        },
        {title: '楼层', field: 'rFloor', visible: true, align: 'center', valign: 'middle' , 
        	formatter : function(value, row, index) {
            	return value+"层";
            }	
        },
        {title: '房间号', field: 'rRoomnum', visible: true, align: 'center', valign: 'middle', 
        	formatter : function(value, row, index) {
            	return value+"号";
            }	
        },
        {title: '房屋类型', field: 'rRoomType', visible: true, align: 'center', valign: 'middle' , 
        	formatter : function(value, row, index) {
        		if(value == "1"){
        			return "住宅" ;
        		}else if(value == "2"){
        			return "公寓" ;
        		}else if(value == "3"){
        			return "办公" ;
        		}else if(value == "4"){
        			return "商铺" ;
        		}else if(value == "5"){
        			return "厂房" ;
        		}else if(value == "6"){
        			return "仓库" ;
        		}else if(value == "7"){
        			return "酒店" ;
        		}else if(value == "8"){
        			return "别墅" ;
        		}else{
        			return "其他" ;
        		}
        	}
        },
        {title: '户型', field: 'houseType', visible: true, align: 'center', valign: 'middle'},
        {title: '入住状态', field: 'liveStatus', visible: true, align: 'center', valign: 'middle',
        	formatter : function(value, row, index) {
        		if(value == "1"){
        			return "未迁入" ;
        		}else if(value == "2"){
        			return "已迁入" ;
        		}else if(value == "3"){
        			return "已迁出" ;
        		}
        	}			
        },
        {title: '住户人数', field: 'iCount', visible: true, align: 'center', valign: 'middle' },
        {title: '收费项个数', field: 'sCount', visible: true, align: 'center', valign: 'middle' },
        {title: '管家姓名', field: 'stewardName', visible: true, align: 'center', valign: 'middle'},
        {title: '操作', field: '', visible: true, align: 'center', valign: 'middle' ,
    	formatter:function(value,row,index) {
    	return [
    	'<button class="btn btn-primary" id="cancel"  onClick="setgj()"><i class="fa fa-eraser"></i>关联管家</button>'
    	]}}
    ];
};

/**
 * 检查是否选中
 */
RoomInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        RoomInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加房屋管理
 */
RoomInfo.openAddRoomInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加房屋管理',
        area: ['800px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/roomInfo/roomInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看房屋管理详情
 */
RoomInfo.openRoomInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '房屋管理详情',
            area: ['800px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/roomInfo/roomInfo_update/' + RoomInfo.seItem.rId
        });
        this.layerIndex = index;
    }
};

RoomInfo.openbatchStandards = function () {
	 if (this.check()) {
		 var index = layer.open({
	        type: 2,
	        title: '添加`批量关联收费标准',
	        area: ['800px', '620px'], //宽高
	        fix: false, //不固定
	        maxmin: true,
	        content: Feng.ctxPath + '/batchStandards/batchStandards_addByRoomInfo/' + RoomInfo.seItem.rId
	    });
	    this.layerIndex = index;
	 }
}


/**
 * 打开查看房屋管理详情
 */
RoomInfo.openRoomInfoDetail2 = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '房屋管理详情',
            area: ['800px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/roomInfo/roomInfo_detail/' + RoomInfo.seItem.rId
        });
        this.layerIndex = index;
    }
};

/**
 * 删除房屋管理
 */
RoomInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/roomInfo/delete", function (data) {
            Feng.success("删除成功!");
            RoomInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("roomInfoId",this.seItem.rId);
        ajax.start();
    }
};

RoomInfo.openReader = function () {
        var index = layer.open({
            type: 2,
            title: '房屋批量导入',
            area: ['650px', '480px'], //宽高
            fix: true, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/roomInfo/roomInfo_reader'
        });
        this.layerIndex = index;
};

/**
 * 打开查看房屋管理详情
 */
RoomInfo.openRoomInfoButler = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '房屋管理详情',
            area: ['600px', '260px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/roomInfo/roomInfo_butler/' + RoomInfo.seItem.rId
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看房屋管理详情
 */
RoomInfo.openbatchBulter = function () {
        var index = layer.open({
            type: 2,
            title: '房屋管理详情',
            area: ['600px', '350px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/roomInfo/roomInfo_batchButler'
        });
        this.layerIndex = index;
};

function setgj(){
	RoomInfo.openRoomInfoButler();
}


/**
 * 查询房屋管理列表
 */
RoomInfo.search = function () {
    var queryData = {};
    queryData['xqName'] = $("#xqName").val();
    queryData['lyName'] = $("#lyName").val();
    queryData['fjName'] = $("#fjName").val();
    RoomInfo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = RoomInfo.initColumn();
    var table = new BSTable(RoomInfo.id, "/roomInfo/list", defaultColunms);
    table.setPaginationType("client");
    RoomInfo.table = table.init();
});
