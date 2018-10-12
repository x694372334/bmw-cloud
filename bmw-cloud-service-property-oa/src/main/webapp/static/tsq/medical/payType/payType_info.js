/**
 * 初始化收费项目分类详情对话框
 */
var PayTypeInfoDlg = {
    payTypeInfoData : {},
    validateFields : {
		name : {
			validators : {
				notEmpty : {
					message : '请输入分类名称'
				}
			}
		},
		parentName : {
			validators : {
				notEmpty : {
					message : '请选择父级分类'
				}
			}
		}
	}
};

/**
 * 清除数据
 */
PayTypeInfoDlg.clearData = function() {
    this.payTypeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PayTypeInfoDlg.set = function(key, val) {
    this.payTypeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PayTypeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PayTypeInfoDlg.close = function() {
    parent.layer.close(window.parent.PayType.layerIndex);
}

/**
 * 收集数据
 */
PayTypeInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('parentCode');
}

/**
* 验证数据是否为空
*/
PayTypeInfoDlg.validate = function() {
	$('#PayTypeInfoForm').data("bootstrapValidator").resetForm();
	$('#PayTypeInfoForm').bootstrapValidator('validate');
	return $("#PayTypeInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
PayTypeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
    	return false;
	}

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/payType/add", function(data){
        Feng.success("添加成功!");
        window.parent.PayType.table.refresh();
        PayTypeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.payTypeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PayTypeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
		return false;
	}
	if ($("#parentCode").val() == $("#code").val()) {
		Feng.error("父节点不能是自己！");
		return false;
	} else {
		//提交信息
	    var ajax = new $ax(Feng.ctxPath + "/payType/update", function(data){
	        Feng.success("修改成功!");
	        window.parent.PayType.table.refresh();
	        PayTypeInfoDlg.close();
	    },function(data){
	        Feng.error("修改失败!" + data.responseJSON.message + "!");
	    });
	    ajax.set(this.payTypeInfoData);
	    ajax.start();
	}
}

/**
 * 点击父级编号input框时
 */
PayTypeInfoDlg.onClickDept = function (e, treeId, treeNode) {
    $("#parentName").attr("value", PayTypeInfoDlg.ztreeInstance.getSelectedVal());
    $("#parentCode").attr("value", treeNode.ext_attr);
};


/**
 * 显示父级菜单选择的树
 */
PayTypeInfoDlg.showPayTypeSelectTree = function () {
    Feng.showInputTree("parentName", "pcodeTreeDiv", 15, 34);
};
$(function() {
	 Feng.initValidator("PayTypeInfoForm", PayTypeInfoDlg.validateFields);

    var ztree = new $ZTree("pcodeTree", "/payType/selectPayTypeTreeList");
    ztree.bindOnClick(PayTypeInfoDlg.onClickDept);
    ztree.init();
    PayTypeInfoDlg.ztreeInstance = ztree;
});
