/**
 * 初始化enterprise详情对话框
 */
var EnterpriseInfoInfoDlg = {
    enterpriseInfoInfoData : {},
	validateFields: {
		enterpriseName: {
	        validators: {
	            notEmpty: {
	                message: '企业名称不能为空'
	            }
	        }
	    },
	    shortName: {
            validators: {
                stringLength:{
                	 message:"最多输入5位",
                     min:"0",
                     max:"5"
                }
            }
        },
	    enterpriseAddress: {
	        validators: {
	            notEmpty: {
	                message: '企业地址不能为空'
	            }
	        }
	    },
	    enterpriseType: {
	        validators: {
	            notEmpty: {
	                message: '企业类型不能为空'
	            }
	        }
	    },
	    enterpriseLegalPerson: {
	        validators: {
	            notEmpty: {
	                message: '企业法人不能为空'
	            }
	        }
	    },
	    enterprisePhone: {
	        validators: {
	            notEmpty: {
	                message: '企业电话不能为空'
	            }
	        }
	    }
	}
};

/**
 * 清除数据
 */
EnterpriseInfoInfoDlg.clearData = function() {
    this.enterpriseInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
EnterpriseInfoInfoDlg.set = function(key, val) {
    this.enterpriseInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
EnterpriseInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
EnterpriseInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.EnterpriseInfo.layerIndex);
}

/**
 * 收集数据
 */
EnterpriseInfoInfoDlg.collectData = function() {
    this
    .set('eId')
    .set('enterpriseName')
    .set('shortName')
    .set('enterpriseAddress')
    .set('enterpriseLegalPerson')
    .set('enterprisePhone')
    .set('enterpriseType')
    .set('workScope')
    .set('enterpriseRules')
    .set('auditStatus')
    .set('parentId')
    .set('eLicense')
    .set('eWebsite')
    .set('eDescription')
    .set('createManId')
    .set('createMan')
    .set('createTime')
    .set('editManId')
    .set('editMan')
    .set('editTime')
    .set('isDelete');
}
/**
 * 数据验证
 */
EnterpriseInfoInfoDlg.validate = function () {
    $('#enterpriseInfoForm').data("bootstrapValidator").resetForm();
    $('#enterpriseInfoForm').bootstrapValidator('validate');
    return $("#enterpriseInfoForm").data('bootstrapValidator').isValid();
};
/**
 * 提交添加
 */
EnterpriseInfoInfoDlg.addSubmit = function() {
	this.clearData();
    this.collectData();
	//验证第一层级不能是物业 只能是企业
	if(""==this.enterpriseInfoInfoData.parentId&&"2"==this.enterpriseInfoInfoData.enterpriseType){
		Feng.info("第一层级不能是物业，请添加企业信息！");
		return;
	}
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/enterpriseInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.EnterpriseInfo.table.refresh();
        EnterpriseInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.enterpriseInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
EnterpriseInfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    //验证第一层级不能是物业 只能是企业
	if(""==this.enterpriseInfoInfoData.parentId&&"2"==this.enterpriseInfoInfoData.enterpriseType){
		Feng.info("第一层级不能是物业，请添加企业信息！");
		return;
	}
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/enterpriseInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.EnterpriseInfo.table.refresh();
        EnterpriseInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.enterpriseInfoInfoData);
    ajax.start();
}
EnterpriseInfoInfoDlg.showEnterpriseSelectTree = function () {
    Feng.showInputTree("parentCode", "pcodeTreeDiv", 15, 34);
};
EnterpriseInfoInfoDlg.onClickDept = function (e, treeId, treeNode) {
	$("#pcodeName").attr("value", EnterpriseInfoInfoDlg.ztreeInstance.getSelectedVal());
    $("#parentId").attr("value",treeNode.pcode);
};

$(function() {
	 Feng.initValidator("enterpriseInfoForm", EnterpriseInfoInfoDlg.validateFields);
	 
	    // 初始化头像上传
	    var avatarUp = new $WebUpload("eLicense");
	    avatarUp.setUploadBarId("progressBar");
	    avatarUp.init();
	    
	    var ztree = new $ZTree("pcodeTree", "/enterpriseInfo/selectEnterpriseParentTreeList");
	    ztree.bindOnClick(EnterpriseInfoInfoDlg.onClickDept);
	    ztree.init();
	    EnterpriseInfoInfoDlg.ztreeInstance = ztree;
	    
});
