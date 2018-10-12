/**
 * 初始化收费项目明细详情对话框
 */
var PayServiceDetailInfoDlg = {
    payServiceDetailInfoData : {},
    ztreeInstance: null
};

/**
 * 清除数据
 */
PayServiceDetailInfoDlg.clearData = function() {
    this.payServiceDetailInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PayServiceDetailInfoDlg.set = function(key, val) {
    this.payServiceDetailInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PayServiceDetailInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PayServiceDetailInfoDlg.close = function() {
    if(window.parent.PayService){
		parent.layer.close(window.parent.PayService.layerIndex);
	}else{
		parent.layer.close(window.parent.PayServiceDetail.layerIndex);
	}
}

/**
 * 收集数据
 */
PayServiceDetailInfoDlg.collectData = function() {
    this
    .set('id')
    .set('departmentName')
    .set('medicalDepartmentCode')
    .set('doctorName')
    .set('medicalDoctorCode')
    .set('payServiceId')
    .set('createManId')
    .set('createMan')
    .set('createTime')
    .set('editManId')
    .set('editMan')
    .set('editTime')
    .set('isDelete');
    this.payServiceDetailInfoData['doctorName'] = $("#medicalDoctorCode").find("option:selected").text();  
}

/**
 * 提交添加
 */
PayServiceDetailInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    var url = "";
    if($("#payServiceId").val()){
    	url = Feng.ctxPath + "/payServiceDetail/add/"+$("#payServiceId").val();
    }else{
    	url = Feng.ctxPath + "/payServiceDetail/add";
    }
    //提交信息
    var ajax = new $ax(url, function(data){
        Feng.success("添加成功!");
        if(window.parent.PayServiceDetail){
			window.parent.BatchStandards.table.refresh();
		}
        PayServiceDetailInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.payServiceDetailInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PayServiceDetailInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/payServiceDetail/update", function(data){
        Feng.success("修改成功!");
        window.parent.PayServiceDetail.table.refresh();
        PayServiceDetailInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.payServiceDetailInfoData);
    ajax.start();
}

PayServiceDetailInfoDlg.showDepartmentSelectTree = function () {
    Feng.showInputTree("parentCode", "pcodeTreeDiv", 15, 34);
};

/**
 * 点击父级编号input框时
 */
PayServiceDetailInfoDlg.onClickDept = function (e, treeId, treeNode) {
	$("#departmentName").attr("value", treeNode.name);
    var medicalDepartmentCode = "";
    var ext_attr = treeNode.ext_attr;
	if(ext_attr && ext_attr.split(",")[1]){
		medicalDepartmentCode = ext_attr.split(",")[1];
	}
	$("#medicalDepartmentCode").attr("value",medicalDepartmentCode);
    PayServiceDetailInfoDlg.getDoctor(medicalDepartmentCode);
};

PayServiceDetailInfoDlg.getDoctor= function(medicalDepartmentCode) {
	//获取医生下拉列表
	var ajax = new $ax(Feng.ctxPath + "/deptDoctorRelation/doctorList", function(data){
		$("#medicalDoctorCode option").remove();
		for(var i = 0 ; i < data.length; i++ ){
        	if($("#doctorCode").val() == data[i].code){
        		$("#medicalDoctorCode").append("<option value='"+data[i].code+"' selected='selected'>"+data[i].name +"</option>");
        	}else{
        		$("#medicalDoctorCode").append("<option value='"+data[i].code+"'>"+data[i].name+"</option>");
        	}
        }
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
	ajax.set("deptCode", medicalDepartmentCode);
    ajax.start();
};


$(function() {
	Feng.initValidator("PayServiceDetailInfoForm", PayServiceDetailInfoDlg.validateFields);
	
	var ztree = new $ZTree("pcodeTree", "/deptDoctorRelation/getTreeList");
    ztree.bindOnClick(PayServiceDetailInfoDlg.onClickDept);
    ztree.init();
    PayServiceDetailInfoDlg.ztreeInstance = ztree;
	
	PayServiceDetailInfoDlg.getDoctor();
	
});
