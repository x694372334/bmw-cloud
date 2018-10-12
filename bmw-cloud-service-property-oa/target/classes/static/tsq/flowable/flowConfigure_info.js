/**
 * 初始化flowconfigure详情对话框
 */
var FlowConfigureInfoDlg = {
    flowConfigureInfoData : {},
	validateFields : {
		flowName : {
			validators : {
				notEmpty : {
					message : '名称不能为空'
				}
			}
		},
		flowCode : {
			validators : {
				notEmpty : {
					message : '编码不能为空'
				}
			}
		}
		
	}
};

/**
 * 验证数据是否为空
 */
FlowConfigureInfoDlg.validate = function () {
    $('#flowConfigureForm').data("bootstrapValidator").resetForm();
    $('#flowConfigureForm').bootstrapValidator('validate');
    return $("#flowConfigureForm").data('bootstrapValidator').isValid();
};


/**
 * 清除数据
 */
FlowConfigureInfoDlg.clearData = function() {
    this.flowConfigureInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FlowConfigureInfoDlg.set = function(key, val) {
    this.flowConfigureInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FlowConfigureInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
FlowConfigureInfoDlg.close = function() {
    parent.layer.close(window.parent.FlowConfigure.layerIndex);
}

/**
 * 收集数据
 */
FlowConfigureInfoDlg.collectData = function() {
    this
    .set('id')
    .set('flowName')
    .set('flowCode')
    .set('isDefaultFlow')
    .set('createManId')
    .set('createMan')
    .set('createTime')
    .set('editManId')
    .set('editMan')
    .set('editTime')
    .set('isDelete');
}

/**
 * 提交添加
 */
FlowConfigureInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/flowConfigure/add", function(data){
        Feng.success("添加成功!");
        window.parent.FlowConfigure.table.refresh();
        FlowConfigureInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.flowConfigureInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
FlowConfigureInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/flowConfigure/update", function(data){
        Feng.success("修改成功!");
        window.parent.FlowConfigure.table.refresh();
        FlowConfigureInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.flowConfigureInfoData);
    ajax.start();
}

$(function() {
	Feng.initValidator("flowConfigureForm", FlowConfigureInfoDlg.validateFields);
});
