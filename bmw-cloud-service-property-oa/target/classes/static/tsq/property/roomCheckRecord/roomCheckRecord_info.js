/**
 * 初始化收房验房详情对话框
 */
var RoomCheckRecordInfoDlg = {
    roomCheckRecordInfoData : {},
    validateFields: {
    	ownerName: {
	        validators: {
	            notEmpty: {
	                message: '业主姓名不能为空'
	            }
	        }
	    },
	    roomId: {
	        validators: {
	            notEmpty: {
	                message: '请选择房屋'
	            }
	        }
	    },
	    contact: {
	        validators: {
	            notEmpty: {
	                message: '联系方式不能为空'
	            },
	            regexp: {
		            regexp: /^\d{1,13}$/,
		            message: '请输入正确电话号(最多13位数字)'
		        },    
	        }
	    },
	    checkTime: {
	        validators: {
	            notEmpty: {
	                message: '时间不能为空'
	            }
	        }
	    }
	}
};

/**
 * 清除数据
 */
RoomCheckRecordInfoDlg.clearData = function() {
    this.roomCheckRecordInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RoomCheckRecordInfoDlg.set = function(key, val) {
    this.roomCheckRecordInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RoomCheckRecordInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
RoomCheckRecordInfoDlg.close = function() {
    parent.layer.close(window.parent.RoomCheckRecord.layerIndex);
}

/**
 * 收集数据
 */
RoomCheckRecordInfoDlg.collectData = function() {
    this
    .set('id')
    .set('ownerName')
    .set('roomId')
    .set('contact')
    .set('checkTime')
    .set('principalUserId')
    .set('remark')
    .set('status')
}

/**
 * 提交添加
 */
RoomCheckRecordInfoDlg.addSubmit = function() {
	 if (!this.validate()) {
	        return;
	    }
	 if($("#roomId").val()==''){
		 Feng.alert("请选择房屋");
		 return ;
	 }
    this.clearData();
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/roomCheckRecord/add", function(data){
    	
        Feng.success("添加成功!");
        window.parent.RoomCheckRecord.table.refresh();
        RoomCheckRecordInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.roomCheckRecordInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
RoomCheckRecordInfoDlg.editSubmit = function() {
	 if (!this.validate()) {
	        return;
	 }
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/roomCheckRecord/update", function(data){
        Feng.success("修改成功!");
        window.parent.RoomCheckRecord.table.refresh();
        RoomCheckRecordInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.roomCheckRecordInfoData);
    ajax.start();
}
RoomCheckRecordInfoDlg.validate = function () {
    $('#RoomCheckRecordForm').data("bootstrapValidator").resetForm();
    $('#RoomCheckRecordForm').bootstrapValidator('validate');
    return $("#RoomCheckRecordForm").data('bootstrapValidator').isValid();
};
$(function() {
	Feng.initValidator("RoomCheckRecordForm", RoomCheckRecordInfoDlg.validateFields);
	var ztree = new $ZTree("treeDemo", "/roomInfo/createNBTree/3/0");
    ztree.bindOnClick(RoomCheckRecordInfoDlg.onClickRoom);
    ztree.init();
    instance = ztree;
});

RoomCheckRecordInfoDlg.showRoomSelectTree=function(){
	 var cityObj = $("#citySel");
	 
	    var cityPosition = $("#citySel").position();
	      cityPosition.top+=23;
	      cityPosition.left+=467;
	    $("#roomContent").css({
	        left: cityPosition.left + "px",
	        top: cityPosition.top + cityObj.outerHeight() + "px"
	    }).slideDown("fast");
	    $("body").bind("mousedown", onBodyDown);
}
/**
* 隐藏部门选择的树
*/
RoomCheckRecordInfoDlg.hideRoomSelectTree = function () {
   $("#roomContent").fadeOut("fast");
   $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};
RoomCheckRecordInfoDlg.onClickRoom = function (e, treeId, treeNode) {
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
	   RoomCheckRecordInfoDlg.hideRoomSelectTree();
   }
}
