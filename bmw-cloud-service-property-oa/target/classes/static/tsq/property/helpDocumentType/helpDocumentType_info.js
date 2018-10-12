/**
 * 初始化详情对话框
 */
var helpDocumentTypeInfoDlg = {
    helpDocumentTypeInfoData : {} ,
//    ztreeInstance: null,
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '名称不能为空'
                   
                },
            }
        },
        pcodeName: {
            validators: {
                notEmpty: {
                    message: '父节点不能为空'
                   
                },
            }
        },
        sort : {
			validators : {
				numeric : {
					message : "必须输入数字",
				},
				regexp : {
					message : "必须为整数",
					regexp : "^[0-9]*[1-9][0-9]*$"
				},
				stringLength : {
					message : "最多输入3位",
					min : "1",
					max : "3"
				}
			}
		}
    }
};

/**
 * 验证数据是否为空
 */
helpDocumentTypeInfoDlg.validate = function () {
    $('#helpDocumentTypeForm').data("bootstrapValidator").resetForm();
    $('#helpDocumentTypeForm').bootstrapValidator('validate');
    return $("#helpDocumentTypeForm").data('bootstrapValidator').isValid();
};

/**
 * 清除数据
 */
helpDocumentTypeInfoDlg.clearData = function() {
    this.helpDocumentTypeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
helpDocumentTypeInfoDlg.set = function(key, val) {
    this.helpDocumentTypeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
helpDocumentTypeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
helpDocumentTypeInfoDlg.close = function() {
    parent.layer.close(window.parent.helpDocumentType.layerIndex);
}

/**
 * 收集数据
 */
helpDocumentTypeInfoDlg.collectData = function() {
    this.set('id').set('code').set('parentCode').set('name').set('sort').set('level').set('isLeaf').set('childrenCount').set('createManId').set('createMan').set('createTime').set('editManId').set('editMan').set('editTime').set('isDelete');
}

/**
 * 提交添加
 */
helpDocumentTypeInfoDlg.addSubmit = function() {
	
    this.clearData();
    this.collectData();
	
	if (!this.validate()) {
        return;
    }
//    alert();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/helpDocumentType/add", function(data){
        Feng.success("添加成功!");
        window.parent.helpDocumentType.table.refresh();
        helpDocumentTypeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.helpDocumentTypeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
helpDocumentTypeInfoDlg.editSubmit = function() {
	
    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/helpDocumentType/update", function(data){
        Feng.success("修改成功!");
        window.parent.helpDocumentType.table.refresh();
        helpDocumentTypeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.helpDocumentTypeInfoData);
    ajax.start();
}

helpDocumentTypeInfoDlg.showhelpDocumentTypeSelectTree = function () {
    Feng.showInputTree("parentCode", "pcodeTreeDiv", 15, 34);
};

/**
 * 点击父级编号input框时
 */
helpDocumentTypeInfoDlg.onClickDept = function (e, treeId, treeNode) {
	$("#pcodeName").attr("value", helpDocumentTypeInfoDlg.ztreeInstance.getSelectedVal());
    $("#parentCode").attr("value",treeNode.pcode);
};

$(function() {
	
	 Feng.initValidator("helpDocumentTypeForm", helpDocumentTypeInfoDlg.validateFields);

	    var ztree = new $ZTree("pcodeTree", "/helpDocumentType/selectHelpDocumentTypeTreeList");
	    ztree.bindOnClick(helpDocumentTypeInfoDlg.onClickDept);
	    ztree.init();
	    helpDocumentTypeInfoDlg.ztreeInstance = ztree;

});
