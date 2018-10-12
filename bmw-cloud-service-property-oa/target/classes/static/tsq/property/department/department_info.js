/**
 * 初始化详情对话框
 */
var departmentInfoDlg = {
		departmentInfoData : {} ,
//    ztreeInstance: null,
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '部门名称不能为空'
                   
                },
            }
        },
        eName: {
            validators: {
                notEmpty: {
                    message: '物业不能为空'
                   
                },
            }
        }
    }
};

/**
 * 清除数据
 */
departmentInfoDlg.clearData = function() {
    this.departmentInfoData = {};
}

/**
 * 验证数据是否为空
 */
departmentInfoDlg.validate = function () {
    $('#departmentInfoForm').data("bootstrapValidator").resetForm();
    $('#departmentInfoForm').bootstrapValidator('validate');
    return $("#departmentInfoForm").data('bootstrapValidator').isValid();
};


/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
departmentInfoDlg.set = function(key, val) {
    this.departmentInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
departmentInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
departmentInfoDlg.close = function() {
    parent.layer.close(window.parent.department.layerIndex);
}

/**
 * 收集数据
 */
departmentInfoDlg.collectData = function() {
    this.set('id').set('code').set('parentCode').set('name').set('shortName').set('sort').set('level').set('isLeaf').set('childrenCount').set('createManId').set('createMan').set('createTime').set('editManId').set('editMan').set('editTime').set('isDelete').set('eId').set('eName');
}

/**
 * 提交添加
 */
departmentInfoDlg.addSubmit = function() {
    this.clearData();
    this.collectData();
    if(0==$("#eId").val()){
    	Feng.info("请选择物业!");
    	return;
    }
    if (!this.validate()) {
        return;
    }


    var flag = 0;
    //验证选择的是否是物业
    var ajax1 = new $ax(Feng.ctxPath + "/propertyDepartment/verification", function(data){
       console.log(data);
       if("ERROR"==data){
    	   Feng.error("请选择物业类型的企业!");
    	   flag = 1;
    	   return;
       }
       
    },function(data){
        Feng.error("验证失败!" + data.responseJSON.message + "!");
        return;
    });
    ajax1.set("enterpriseInfoId",this.departmentInfoData.eId);
    ajax1.start();
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/propertyDepartment/add", function(data){
    	if(data.code==400){
    		Feng.info(data.message);
    		return;
    	}
        Feng.success("添加成功!");
        window.parent.department.table.refresh();
        departmentInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.departmentInfoData);
    if(flag==0){
    ajax.start();
    }
}

/**
 * 提交修改
 */
departmentInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
   
    if(0==$("#eId").val()){
    	Feng.info("请选择物业!");
    	return;
    }
    
    if (!this.validate()) {
        return;
    }

    var flag = 0;
    //验证选择的是否是物业
    var ajax1 = new $ax(Feng.ctxPath + "/propertyDepartment/verification", function(data){
       console.log(data);
       if("ERROR"==data){
    	   Feng.error("请选择物业类型的企业!");
    	   flag = 1;
    	   return;
       }
       
    },function(data){
        Feng.error("验证失败!" + data.responseJSON.message + "!");
        return;
    });
    ajax1.set("enterpriseInfoId",this.departmentInfoData.eId);
    ajax1.start();
    
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/propertyDepartment/update", function(data){
    	if(data.code==400){
    		Feng.info(data.message);
    		return;
    	}
        Feng.success("修改成功!");
        window.parent.department.table.refresh();
        departmentInfoDlg.close();
    },function(data){
        Feng.info("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.departmentInfoData);
    if(flag==0){
    ajax.start();
    }
}

departmentInfoDlg.showDepartmentSelectTree = function () {
    Feng.showInputTree("parentCode", "pcodeTreeDiv", 15, 34);
};

departmentInfoDlg.showEnterpriseSelectTree = function () {
	if("0"==$("#parentCode").val()){
    Feng.showInputTree("eId", "enterTreeDiv", 15, 34);
	}
};

/**
 * 点击父级编号input框时
 */
departmentInfoDlg.onClickDept = function (e, treeId, treeNode) {
	$("#pcodeName").attr("value", departmentInfoDlg.ztreeInstance.getSelectedVal());
    $("#parentCode").attr("value",treeNode.pcode);
    
    //查询父级所属物业
    var ajax = new $ax(Feng.ctxPath + "/propertyDepartment/findEnter/"+$("#parentCode").val(), function (data) {
    	var obj = JSON.parse(data);
    	$("#eName").attr("value", obj.enterpriseName);
        $("#eId").attr("value",obj.eId);
    }, function (data) {
        Feng.error("查询物业失败!" + data.responseJSON.message + "!");
    });
    ajax.start();
};

/**
 * 点击父级编号input框时
 */
departmentInfoDlg.onClickEnter = function (e, treeId, treeNode) {
	$("#eName").attr("value", departmentInfoDlg.enterztreeInstance.getSelectedVal());
    $("#eId").attr("value",treeNode.pcode);
};

$(function() {
	
	 Feng.initValidator("departmentInfoForm", departmentInfoDlg.validateFields);

	    var ztree = new $ZTree("pcodeTree", "/propertyDepartment/selectdepartmentTreeList");
	    ztree.bindOnClick(departmentInfoDlg.onClickDept);
	    ztree.init();
	    departmentInfoDlg.ztreeInstance = ztree;
	    
	    //初始化企业树
	    var enterztree = new $ZTree("enterpriseTree", "/enterpriseInfo/selectEnterpriseTreeList");
	    enterztree.bindOnClick(departmentInfoDlg.onClickEnter);
	    enterztree.init();
	    departmentInfoDlg.enterztreeInstance = enterztree;

	    $("#eName").attr("placeholder","请先选择父节点");
	    
	    if("0"==$("#parentCode").val()){
	    	$("#pcodeName").val("顶级");
	    }
});
