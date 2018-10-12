/**
 * 初始化flowNodeConfigure详情对话框
 */
var FlowNodeConfigureInfoDlg = {
    flowNodeConfigureInfoData : {},
	validateFields : {
		nodeName : {
			validators : {
				notEmpty : {
					message : '名称不能为空'
				}
			}
		},
		nodeCode : {
			validators : {
				notEmpty : {
					message : '编号不能为空'
				}
			}
		}
	}
};

/**
 * 清除数据
 */
FlowNodeConfigureInfoDlg.clearData = function() {
    this.flowNodeConfigureInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FlowNodeConfigureInfoDlg.set = function(key, val) {
    this.flowNodeConfigureInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FlowNodeConfigureInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
FlowNodeConfigureInfoDlg.close = function() {
    parent.layer.close(window.parent.FlowNodeConfigure.layerIndex);
}

/**
 * 收集数据
 */
FlowNodeConfigureInfoDlg.collectData = function() {
    this
    .set('id')
    .set('flowConfigureId')
    .set('nodeName')
    .set('nodeCode')
    .set('sort')
    .set('isStart')
    .set('isEnd')
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
FlowNodeConfigureInfoDlg.validate = function () {
    $('#flowNodeConfigureForm').data("bootstrapValidator").resetForm();
    $('#flowNodeConfigureForm').bootstrapValidator('validate');
    return $("#flowNodeConfigureForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
FlowNodeConfigureInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/flowNodeConfigure/add", function(data){
        Feng.success("添加成功!");
        window.parent.FlowNodeConfigure.table.refresh();
        parent.layer.close(window.parent.FlowConfigure.layerIndex);
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.flowNodeConfigureInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
FlowNodeConfigureInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/flowNodeConfigure/update", function(data){
        Feng.success("修改成功!");
        window.parent.FlowNodeConfigure.table.refresh();
        parent.layer.close(window.parent.FlowNodeConfigure.layerIndex);
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.flowNodeConfigureInfoData);
    ajax.start();
}

$(function() {
	Feng.initValidator("flowNodeConfigureForm", FlowNodeConfigureInfoDlg.validateFields);
});
