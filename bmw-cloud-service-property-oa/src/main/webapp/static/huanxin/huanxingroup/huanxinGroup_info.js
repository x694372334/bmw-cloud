/**
 * 初始化 huanxingroup详情对话框
 */
var HuanxinGroupInfoDlg = {
    huanxinGroupInfoData : {},
	validateFields : {
		groupName : {
			validators : {
				notEmpty : {
					message : '群组名称不能为空'
				}
			}
		},
		groupType : {
			validators : {
				notEmpty : {
					message : '群组类型不能为空'
				}
			}
		},
		groupOwner : {
			validators : {
				notEmpty : {
					message : '群主不能为空'
				}
			}
		},
		limit : {
			validators : {
				notEmpty : {
					message : '不能为空'
				},
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
 * 清除数据
 */
HuanxinGroupInfoDlg.clearData = function() {
    this.huanxinGroupInfoData = {};
}

/**
 * 设置对话框中的数据
 * 
 * @param key
 *            数据的名称
 * @param val
 *            数据的具体值
 */
HuanxinGroupInfoDlg.set = function(key, val) {
    this.huanxinGroupInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 验证数据是否为空
 */
HuanxinGroupInfoDlg.validate = function () {
    $('#huanxinGroupForm').data("bootstrapValidator").resetForm();
    $('#huanxinGroupForm').bootstrapValidator('validate');
    return $("#huanxinGroupForm").data('bootstrapValidator').isValid();
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
HuanxinGroupInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
HuanxinGroupInfoDlg.close = function() {
    parent.layer.close(window.parent.HuanxinGroup.layerIndex);
}

/**
 * 收集数据
 */
HuanxinGroupInfoDlg.collectData = function() {
    this
    .set('id')
    .set('groupId')
    .set('groupName')
    .set('groupOwnerId')
    .set('groupOwnerName')
    .set('communityId')
    .set('communityName')
    .set('createManId')
    .set('createManName')
    .set('groupType')
    .set('eId')
    .set('parentEId')
    .set('groupDesc')
    .set('limit');
}

/**
 * 提交添加
 */
HuanxinGroupInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/huanxinGroup/add", function(data){
		if (400 == data.code) {
			console.log(data);
			Feng.info(data.message);
		} else {
			Feng.success("添加成功!");
			window.parent.HuanxinGroup.table.refresh();
			HuanxinGroupInfoDlg.close();
		}
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.huanxinGroupInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
HuanxinGroupInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/huanxinGroup/update", function(data){
        Feng.success("修改成功!");
        window.parent.HuanxinGroup.table.refresh();
        HuanxinGroupInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.huanxinGroupInfoData);
    ajax.start();
}

$(function() {
	Feng.initValidator("huanxinGroupForm", HuanxinGroupInfoDlg.validateFields);
	//初始化适用小区
    $.ajax({
        "type" : 'get',
        "url": Feng.ctxPath + "/neighborhood/list",
        "dataType" : "json",
        "success" : function(data) {
            for(var i = 0 ; i < data.length; i++ ){
            		$("#communityId").append("<option value='"+data[i].nId+"'>"+data[i].nName+"</option>");
            }
        }
    });
    
    
    $('#groupOwner').change(function() {
    	$("#groupOwnerId").val($("#groupOwner").val());
    	$("#groupOwnerName").val($('#groupOwner').find("option:selected").text());
	});
    
    
	$('#groupType').change(function() {
		if ($('#groupType').val() == 2) {
			//获取当前登录人作为群主
			 $.ajax({
			        "type" : 'get',
			        "url": Feng.ctxPath + "/huanxinGroup/userNow",
			        "dataType" : "json",
			        "success" : function(data) {
			            	$("#groupOwnerId").val(data.id);
			            	$("#groupOwnerName").val(data.name);
			            	$("#groupOwner").val(data.id);
			            	$('#groupOwner').attr("disabled",true);
			        }
			    });
			
		}else{
			$('#groupOwner').attr("disabled",false);
		}
	});
});
