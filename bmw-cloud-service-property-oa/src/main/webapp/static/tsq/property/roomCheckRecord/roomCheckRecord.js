/**
 * 收房验房管理初始化
 */
var RoomCheckRecord = {
    id: "RoomCheckRecordTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
RoomCheckRecord.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '业主姓名', field: 'ownerName', visible: true, align: 'center', valign: 'middle'},
            {title: '房间号', field: 'roomNum', visible: true, align: 'center', valign: 'middle'},
            {title: '联系方式', field: 'contact', visible: true, align: 'center', valign: 'middle'},
            {title: '验房时间', field: 'checkTime', visible: true, align: 'center', valign: 'middle'},
            {title: '负责人用户名', field: 'principalUserName', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'statusName', visible: true, align: 'center', valign: 'middle',
            	formatter : function(value, row, index) {
            		return "<span style='color:red'>"+value+"</span>";
				}
            },
            {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
RoomCheckRecord.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        RoomCheckRecord.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加收房验房
 */
RoomCheckRecord.openAddRoomCheckRecord = function () {
    var index = layer.open({
        type: 2,
        title: '添加收房验房',
        area: ['800px', '400px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/roomCheckRecord/roomCheckRecord_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看收房验房详情
 */
RoomCheckRecord.openRoomCheckRecordUpdate = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '收房验房详情',
            area: ['800px', '400px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/roomCheckRecord/roomCheckRecord_update/' + RoomCheckRecord.seItem.id
        });
        this.layerIndex = index;
    }
};
/**
 * 打开查看收房验房详情
 */
RoomCheckRecord.openRoomCheckRecordDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '收房验房详情',
            area: ['600px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/roomCheckRecord/roomCheckRecord_detail/' + RoomCheckRecord.seItem.id
        });
        this.layerIndex = index;
    }
};
/**
 * 删除收房验房
 */
RoomCheckRecord.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/roomCheckRecord/delete", function (data) {
        	if(data.code!=500){
        		Feng.success("删除成功!");
        		RoomCheckRecord.table.refresh();
        	}else{
        		Feng.error(data.message);
        	}                     
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("roomCheckRecordId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询收房验房列表
 */
RoomCheckRecord.search = function () {
    var queryData = {
    	"ownerName":$("#ownerName").val(),
    	"roomId":$("#roomId").val(),
    	"contact":$("#contact").val(),
    	"principalUserId":$("#principalUserId").val(),
    };
    RoomCheckRecord.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = RoomCheckRecord.initColumn();
    var table = new BSTable(RoomCheckRecord.id, "/roomCheckRecord/list", defaultColunms);
    table.setPaginationType("server");
    RoomCheckRecord.table = table.init();
    var ztree = new $ZTree("treeDemo", "/roomInfo/createNBTree/3/0");
    ztree.bindOnClick(RoomCheckRecord.onClickRoom);
    ztree.init();
    instance = ztree;
});
//----------------------------tree---------------------------------------------
RoomCheckRecord.showRoomSelectTree=function(){
	 var cityObj = $("#citySel");
	    var cityPosition = $("#citySel").position();
	    cityPosition.left+=390;
	    $("#roomContent").css({
	        left: cityPosition.left + "px",
	        top: cityPosition.top + cityObj.outerHeight() + "px"
	    }).slideDown("fast");
	    $("body").bind("mousedown", onBodyDown);
}
/**
 * 隐藏部门选择的树
 */
RoomCheckRecord.hideRoomSelectTree = function () {
    $("#roomContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};
RoomCheckRecord.onClickRoom = function (e, treeId, treeNode) {
	if(treeNode.level!=3){
		Feng.alert("请选择房间");
		return;
	}
    $("#citySel").attr("value", treeNode.ext_attr);//instance.getSelectedVal()
    $("#roomId").attr("value", treeNode.pcode);
};
function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "roomContent" || $(
            event.target).parents("#roomContent").length > 0)) {
    	RoomCheckRecord.hideRoomSelectTree();
    }
}