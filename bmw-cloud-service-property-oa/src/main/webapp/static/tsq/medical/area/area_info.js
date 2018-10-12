/**
 * 初始化详情对话框
 */
var areaInfoDlg = {
	areaInfoData : {},
	ztreeInstance : null,
	validateFields : {
		name : {
			validators : {
				notEmpty : {
					message : '请选择费项类别'
				}
			}
		}
	}
};

/**
 * 清除数据
 */
areaInfoDlg.clearData = function() {
	this.areaInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
areaInfoDlg.set = function(key, val) {
	this.areaInfoData[key] = (typeof val == "undefined") ? $("#" + key).val()
			: val;
	return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
areaInfoDlg.get = function(key) {
	return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
areaInfoDlg.close = function() {
	parent.layer.close(window.parent.area.layerIndex);
}

/**
 * 收集数据
 */
areaInfoDlg.collectData = function() {
	this.set('id').set('code').set('parentCode').set('name').set('sort').set(
			'level').set('isLeaf').set('childrenCount').set('createManId').set(
			'createMan').set('createTime').set('editManId').set('editMan').set(
			'editTime').set('isDelete');
}

/**
 * 验证数据是否为空
 */
areaInfoDlg.validate = function() {
	$('#areaInfoForm').data("bootstrapValidator").resetForm();
	$('#areaInfoForm').bootstrapValidator('validate');
	return $("#areaInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
areaInfoDlg.addSubmit = function() {
	this.clearData();
	this.collectData();

	if (!this.validate()) {
		return;
	}

	//提交信息
	var ajax = new $ax(Feng.ctxPath + "/area/add", function(data) {
		Feng.success("添加成功!");
		window.parent.area.table.refresh();
		areaInfoDlg.close();
	}, function(data) {
		Feng.error("添加失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.areaInfoData);
	ajax.start();
}

/**
 * 提交修改
 */
areaInfoDlg.editSubmit = function() {

	this.clearData();
	this.collectData();

	if (!this.validate()) {
		return;
	}

	//提交信息
	var ajax = new $ax(Feng.ctxPath + "/area/update", function(data) {
		Feng.success("修改成功!");
		window.parent.area.table.refresh();
		areaInfoDlg.close();
	}, function(data) {
		Feng.error("修改失败!" + data.responseJSON.message + "!");
	});
	ajax.set(this.areaInfoData);
	ajax.start();
}

areaInfoDlg.showAreaSelectTree = function() {
	Feng.showInputTree("parentCode", "pcodeTreeDiv", 15, 34);
};

/**
 * 点击父级编号input框时
 */
areaInfoDlg.onClickDept = function(e, treeId, treeNode) {
	$("#pcodeName").attr("value", areaInfoDlg.ztreeInstance.getSelectedVal());
	$("#parentCode").attr("value", treeNode.pcode);
};

$(function() {

	Feng.initValidator("areaInfoForm", areaInfoDlg.validateFields);

	var ztree = new $ZTree("pcodeTree", "/area/selectAreaTreeList");
	ztree.bindOnClick(areaInfoDlg.onClickDept);
	ztree.init();
	areaInfoDlg.ztreeInstance = ztree;

});
