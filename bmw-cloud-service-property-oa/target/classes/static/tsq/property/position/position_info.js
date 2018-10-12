/**
 * 初始化详情对话框
 */
var positionInfoDlg = {
    positionInfoData : {} ,
//    ztreeInstance: null,
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '职位名称不能为空'
                   
                },
            }
        },
        pcodeName: {
            validators: {
                notEmpty: {
                    message: '父节点不能为空'
                   
                },
            }
        }
    }
};

/**
 * 清除数据
 */
positionInfoDlg.clearData = function() {
    this.positionInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
positionInfoDlg.set = function(key, val) {
    this.positionInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
positionInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
positionInfoDlg.close = function() {
    parent.layer.close(window.parent.position.layerIndex);
}

/**
 * 验证数据是否为空
 */
positionInfoDlg.validate = function () {
    $('#positionfrom').data("bootstrapValidator").resetForm();
    $('#positionfrom').bootstrapValidator('validate');
    return $("#positionfrom").data('bootstrapValidator').isValid();
};


/**
 * 收集数据
 */
positionInfoDlg.collectData = function() {
    this.set('id').set('code').set('parentCode').set('name').set('sort').set('level').set('isLeaf').set('childrenCount').set('createManId').set('createMan').set('createTime').set('editManId').set('editMan').set('editTime').set('isDelete');
}

/**
 * 提交添加
 */
positionInfoDlg.addSubmit = function() {
    this.clearData();
    this.collectData();
//    alert();
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/position/add", function(data){
        Feng.success("添加成功!");
        window.parent.position.table.refresh();
        positionInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.positionInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
positionInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/position/update", function(data){
        Feng.success("修改成功!");
        window.parent.position.table.refresh();
        positionInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.positionInfoData);
    ajax.start();
}

positionInfoDlg.showpositionSelectTree = function () {
    Feng.showInputTree("parentCode", "pcodeTreeDiv", 15, 34);
};

/**
 * 点击父级编号input框时
 */
positionInfoDlg.onClickDept = function (e, treeId, treeNode) {
	$("#pcodeName").attr("value", positionInfoDlg.ztreeInstance.getSelectedVal());
    $("#parentCode").attr("value",treeNode.pcode);
};

$(function() {
	
	 Feng.initValidator("positionfrom", positionInfoDlg.validateFields);

	    var ztree = new $ZTree("pcodeTree", "/position/selectPositionTreeList");
	    ztree.bindOnClick(positionInfoDlg.onClickDept);
	    ztree.init();
	    positionInfoDlg.ztreeInstance = ztree;
	    
	    if("0"==$("#parentCode").val()){
	    	$("#pcodeName").val("顶级");
	    }

});
