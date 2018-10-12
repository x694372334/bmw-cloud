/**
 * 报事报修管理初始化
 */
var RepairsForm = {
    id: "RepairsFormTable_done",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
RepairsForm.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '维修地址', field: 'repairsAddr', visible: true, align: 'center', valign: 'middle'},
            {title: '业主姓名', field: 'ownerName', visible: true, align: 'center', valign: 'middle'},
            {title: '房屋号', field: 'roomNum', visible: true, align: 'center', valign: 'middle'},
            {title: '联系电话', field: 'ownerPhoneNo', visible: true, align: 'center', valign: 'middle'},
        /*    {title: '发起人', field: 'initiatorName', visible: true, align: 'center', valign: 'middle'},
            {title: '管家', field: 'stewardUserName', visible: true, align: 'center', valign: 'middle'},*/
            {title: '图片', field: 'contentImg', visible: true, align: 'center', valign: 'middle',
            	formatter : function(value, row, index) {
            		if(value==null||value==''){
            			value="<span class='btn btn-default' >未上传图片</span>";
            		}else{           			
            			value="<span class='btn btn-primary' onclick=\"javascript:showImg("+"'"+value+"'"+")\">点击查看大图</span>";
            			console.log(value);
            		}
                	return value;
                }	
            },
            {title: '预约上门查看时间', field: 'probablyArriveTime', visible: true, align: 'center', valign: 'middle'},
            {title: '实际上门查看时间', field: 'actualArriveTime', visible: true, align: 'center', valign: 'middle'},
            {title: '预计完成时间', field: 'probablyCompleteTime', visible: true, align: 'center', valign: 'middle'},
            {title: '实际完成时间', field: 'actualCompleteTime', visible: true, align: 'center', valign: 'middle'},
            {title: '订单状态', field: 'statusName', visible: true, align: 'center', valign: 'middle',
            	formatter : function(value, row, index) {
            		return "<span style='color:red'>"+value+"</span>";
				}
            },
            {title: '用户评分', field: 'appraiseScore', visible: true, align: 'center', valign: 'middle'},
            {title: '是否超时', field: 'isOvertime', visible: true, align: 'center', valign: 'middle',
            	formatter : function(value, row, index) {
            		if(value=='正常'){
            			value="<span style='color:green;'>"+value+"</span>";
            		}else{
            			value="<span style='color:red;'>"+value+"</span>";
            		}
                	return value;
                }	
            }
    ];
};
//点击放大图片
function showImg(url){
    var img=new Image();
    img.src=url;
    var useImage='<img src=\''+url+'\''+"/>";
    img.onload=function(){
    	layer.open({  
    	    type: 1,  
    	    shade: false,  
    	    title: false, //不显示标题  
    	    area: [img.width+'px',img.height+'px'],  
    	    content: useImage, //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响  
    	}); 
    }  
}
RepairsForm.showRoomSelectTree=function(){
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
RepairsForm.hideRoomSelectTree = function () {
    $("#roomContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};
RepairsForm.onClickRoom = function (e, treeId, treeNode) {
	if(treeNode.level!=3){
		Feng.alert("请选择房间");
		return;
	}
	$("#citySel").attr("value", treeNode.ext_attr);
	$("#roomId").attr("value", treeNode.pcode);
};
function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "roomContent" || $(
            event.target).parents("#roomContent").length > 0)) {
    	RepairsForm.hideRoomSelectTree();
    }
}
/**
 * 检查是否选中
 */
RepairsForm.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        RepairsForm.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加报事报修
 */
RepairsForm.openAddRepairsForm = function () {
    var index = layer.open({
        type: 2,
        title: '添加报事报修',
        area: ['800px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/repairsForm/repairsForm_add'
    });
    this.layerIndex = index;
};

/**
 * 点击详情
 */
RepairsForm.openDetailRepairsForm = function () {
	 if (this.check()){
		var index = layer.open({
	        type: 2,
	        title: '详情',
	        area: ['800px', '600px'], //宽高
	        fix: false, //不固定
	        maxmin: true,
	        content: Feng.ctxPath + '/repairsForm/repairsForm_detail/'+RepairsForm.seItem.id
	    });
		this.layerIndex = index;
	 }  
};

/**
 * 删除报事报修
 */
RepairsForm.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/repairsForm/delete", function (data) {
            Feng.success("删除成功!");
            RepairsForm.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("repairsFormId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询报事报修列表
 */
RepairsForm.search = function () {
    var queryData = {
    		"ownerName":$("#ownerName").val(),
    		"roomId":$("#roomId").val(),
    		"stewardUserId":$("#stewardUserId").val(),
    		"status":$("#status").val()
    };
    
    RepairsForm.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = RepairsForm.initColumn();
    var table = new BSTable(RepairsForm.id, "/repairsForm/done_list", defaultColunms);
    table.setPaginationType("server");
    RepairsForm.table = table.init();
    var ztree = new $ZTree("treeDemo", "/roomInfo/createNBTree/3/0");
    ztree.bindOnClick(RepairsForm.onClickRoom);
    ztree.init();
    instance = ztree;
});
