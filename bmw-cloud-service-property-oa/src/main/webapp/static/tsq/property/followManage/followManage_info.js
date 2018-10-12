/**
 * 初始化详情对话框
 */
var FollowManageInfoDlg = {
    followManageInfoData : {},
/*    contactMatters: null,
    remark:null,*/
    validateFields: {
    	ownerName: {
            validators: {
                notEmpty: {
                    message: '业主姓名不能为空'
                },
                stringLength:{
                	 message:"最多输入20位",
                     min:"0",
                     max:"20"
                }
            }
        },
        phone: {
            validators: {
                notEmpty: {
                    message: '联系电话不能为空'
                },
                phone:{
                	message: '只能输入手机号',
                	country: "CN"
                }
            }
        },
        receiverName: {
            validators: {
                notEmpty: {
                    message: '接待人不能为空'
                },
                stringLength:{
               	 message: "最多输入20位",
                    min: "0",
                    max: "20"
               }
            }
        }, 
        contactTime: {
            validators: {
                notEmpty: {
                    message: '联系时间不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
FollowManageInfoDlg.clearData = function() {
    this.followManageInfoData = {};
}


/**
 * 验证数据是否为空
 */
FollowManageInfoDlg.validate = function () {
    $('#followManageForm').data("bootstrapValidator").resetForm();
    $('#followManageForm').bootstrapValidator('validate');
    return $("#followManageForm").data('bootstrapValidator').isValid();
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FollowManageInfoDlg.set = function(key, val) {
    this.followManageInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FollowManageInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
FollowManageInfoDlg.close = function() {
    parent.layer.close(window.parent.FollowManage.layerIndex);
}

/**
 * 收集数据
 */
FollowManageInfoDlg.collectData = function() {
    this
    .set('id')
    .set('ownerId')
    .set('ownerName')
    .set('phone')
    .set('contact')
    .set('receiverId')
    .set('receiverName')
    .set('remark')
    .set('contactMatters');
}

/**
 * 提交添加
 */
FollowManageInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/followManage/add", function(data){
        Feng.success("添加成功!");
        window.parent.FollowManage.table.refresh();
        FollowManageInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.followManageInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
FollowManageInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/followManage/update", function(data){
        Feng.success("修改成功!");
        window.parent.FollowManage.table.refresh();
        FollowManageInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.followManageInfoData);
    ajax.start();
}

$(function() {
	 Feng.initValidator("followManageForm", FollowManageInfoDlg.validateFields);
});
