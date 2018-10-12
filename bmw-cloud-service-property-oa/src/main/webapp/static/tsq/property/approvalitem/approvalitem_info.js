/**
 * 初始化事项审批详情对话框
 */
var ApprovalitemInfoDlg = {
    approvalitemInfoData : {}
};

/**
 * 清除数据
 */
ApprovalitemInfoDlg.clearData = function() {
    this.approvalitemInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ApprovalitemInfoDlg.set = function(key, val) {
    this.approvalitemInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ApprovalitemInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ApprovalitemInfoDlg.close = function() {
    parent.layer.close(window.parent.Approvalitem.layerIndex);
}

/**
 * 收集数据
 */
ApprovalitemInfoDlg.collectData = function() {
    this
}

/**
 * 提交添加
 */
ApprovalitemInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/approvalitem/add", function(data){
        Feng.success("添加成功!");
        window.parent.Approvalitem.table.refresh();
        ApprovalitemInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.approvalitemInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ApprovalitemInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/approvalitem/update", function(data){
        Feng.success("修改成功!");
        window.parent.Approvalitem.table.refresh();
        ApprovalitemInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.approvalitemInfoData);
    ajax.start();
}


function neighborhood(){
	var options=$("#test option:selected");
	 $.ajax({
			type:"POST",
			url:Feng.ctxPath+"/buildingInfo/findNeighbor",
			dataType: "json",
			contentType: "application/json",
			catch:true,
			 success: function (data) {
				 var optionstring = "";
	             for (var i = 0; i < data.length; i++) {
	            	 if($("#nId").val() == data[i].nId){
		                 $("#test").append("<option value='"+data[i].nCode+","+data[i].nId+"' selected>"+ data[i].nName +"</option>");
	            	 }else{
	 	                $("#test").append("<option value='"+data[i].nCode+","+data[i].nId+"'>"+data[i].nName+"</option>");
	            	 }
	             }
			 }
		})
}

$(function() {
	neighborhood();
});
