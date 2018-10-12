/**
 * 初始化收费项目管理详情对话框
 */
var PayServiceInfoDlg = {
    payServiceInfoData : {},
    ztreeInstance: null, 
    validateFields : {
    	itemName : {
			validators : {
				notEmpty : {
					message : '请输入项目名称'
				}
			}
		},
		payTypeName : {
			validators : {
				notEmpty : {
					message : '请选择收费项目分类'
				}
			}
		}
	}
};

/**
 * 清除数据
 */
PayServiceInfoDlg.clearData = function() {
    this.payServiceInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PayServiceInfoDlg.set = function(key, val) {
    this.payServiceInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PayServiceInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PayServiceInfoDlg.close = function() {
    parent.layer.close(window.parent.PayService.layerIndex);
}

/**
 * 收集数据
 */
PayServiceInfoDlg.collectData = function() {
    this
    .set('id')
    .set('payTypeId')
    .set('itemName')
    .set('chargeStandard')
    .set('createManId')
    .set('createMan')
    .set('createTime')
    .set('editManId')
    .set('editMan')
    .set('editTime')
    .set('isDelete');
}

/**
 * 验证数据是否为空
 */
PayServiceInfoDlg.validate = function() {
	$('#PayServiceInfoForm').data("bootstrapValidator").resetForm();
	$('#PayServiceInfoForm').bootstrapValidator('validate');
	return $("#PayServiceInfoForm").data('bootstrapValidator').isValid();
};


/**
 * 提交添加
 */
PayServiceInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
    	return false;
	}

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/payService/add", function(data){
        Feng.success("添加成功!");
        window.parent.PayService.table.refresh();
        PayServiceInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.payServiceInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PayServiceInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
    	return false;
	}

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/payService/update", function(data){
        Feng.success("修改成功!");
        window.parent.PayService.table.refresh();
        PayServiceInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.payServiceInfoData);
    ajax.start();
}

PayServiceInfoDlg.showPayTypeSelectTree = function () {
    Feng.showInputTree("parentCode", "pcodeTreeDiv", 15, 34);
};

/**
 * 点击父级编号input框时
 */
PayServiceInfoDlg.onClickDept = function (e, treeId, treeNode) {
	$("#payTypeName").attr("value", treeNode.name);
    $("#payTypeId").attr("value",treeNode.id);
};

$(function() {
	Feng.initValidator("PayServiceInfoForm", PayServiceInfoDlg.validateFields);
	
	var ztree = new $ZTree("pcodeTree", "/payType/selectPayTypeTreeList");
    ztree.bindOnClick(PayServiceInfoDlg.onClickDept);
    ztree.init();
    PayServiceInfoDlg.ztreeInstance = ztree;
});
