/**
 * 初始化收房验房详情对话框
 */
var RoomCheckRecordInfoDlg2 = {
  
};
/**
 * 关闭此对话框
 */
RoomCheckRecordInfoDlg2.close = function() {
    parent.layer.close(window.parent.RoomCheckRecord.layerIndex);
}

/**
 * 删除收房验房
 */
RoomCheckRecordInfoDlg2.delete = function () {
        var ajax = new $ax(Feng.ctxPath + "/roomCheckRecord/delete", function (data) {
        	if(data.code!=500){
        		Feng.success("删除成功!");
        		window.parent.RoomCheckRecord.table.refresh();
        		RoomCheckRecordInfoDlg2.close();
        	}else{
        		Feng.error(data.message);
        	}                     
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("roomCheckRecordId",$("#id").val());
        ajax.start();
    
};

/**
 * 打开查看收房验房详情
 */
RoomCheckRecordInfoDlg2.openRoomCheckRecordUpdate = function () {
    if ($("#id").val()!='') {
        var index = parent.layer.open({
            type: 2,
            title: '收房验房编辑',
            area: ['800px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/roomCheckRecord/roomCheckRecord_update/' + $("#id").val()
        });
        RoomCheckRecordInfoDlg2.close();
        window.parent.RoomCheckRecord.layerIndex = index;
    }
};