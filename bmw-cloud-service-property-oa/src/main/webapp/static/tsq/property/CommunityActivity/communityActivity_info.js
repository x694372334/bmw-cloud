/**
 * 初始化小区活动详情对话框
 */
var CommunityActivityInfoDlg = {
    communityActivityInfoData : {},
    editor:null,
    validateFields: {
    	activityName: {
	        validators: {
	            notEmpty: {
	                message: '活动名称不能为空'
	            }
	        }
	    },
	    pubTime: {
	        validators: {
	            notEmpty: {
	                message: '发布时间不能为空'
	            }
	        }
	    }
	}
};
/**
 * 验证数据是否为空
 */
CommunityActivityInfoDlg.validate = function () {
    $('#communityactivityForm').data("bootstrapValidator").resetForm();
    $('#communityactivityForm').bootstrapValidator('validate');
    return $("#communityactivityForm").data('bootstrapValidator').isValid();
};
/**
 * 清除数据
 */
CommunityActivityInfoDlg.clearData = function() {
    this.communityActivityInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CommunityActivityInfoDlg.set = function(key, val) {
    this.communityActivityInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CommunityActivityInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CommunityActivityInfoDlg.close = function() {
    parent.layer.close(window.parent.CommunityActivity.layerIndex);
}
//详情页的提交
CommunityActivityInfoDlg.sub=function(){
		var status=$("#status").val();
		if(status!=1&&status!=4){
			Feng.error("只有草稿状态和被驳回状态可以提交审核！");
			return;
		}
		 var ajax = new $ax(Feng.ctxPath + "/communityActivity/sub", function (data) {
			 	Feng.success(data.message);
	    		window.parent.CommunityActivity.table.refresh();
	    		CommunityActivityInfoDlg.close();
	        }, function (data) {
	            Feng.error("提交审核失败!" + data.message + "!");
	        });
	        ajax.set("communityActivityId",$("#id").val());
	        ajax.start();
}
//详情页的撤回
CommunityActivityInfoDlg.callback=function(){
		var status=$("#status").val();
		if(status!=2){
			Feng.error("只有审核中的公告可以撤回！");
			return;
		}
		 var ajax = new $ax(Feng.ctxPath + "/communityActivity/callback", function (data) {
			 	Feng.success(data.message);
	    		window.parent.CommunityActivity.table.refresh();
	    		CommunityActivityInfoDlg.close();
	        }, function (data) {
	            Feng.error("撤回失败!" + data.message + "!");
	        });
	        ajax.set("communityActivityId",$("#id").val());
	        ajax.start();
}
//详情页的发布
CommunityActivityInfoDlg.pub=function(){
		var status=$("#status").val();
		if(status!=3){
			Feng.error("只有审核通过的公告可以发布！");
			return;
		}
		 var ajax = new $ax(Feng.ctxPath + "/communityActivity/pub", function (data) {
			 	Feng.success(data.message);
	    		window.parent.CommunityActivity.table.refresh();
	    		CommunityActivityInfoDlg.close();
	        }, function (data) {
	            Feng.error("发布失败!" + data.message + "!");
	        });
	        ajax.set("communityActivityId",$("#id").val());
	        ajax.start();
}
/**
 * 收集数据
 */
CommunityActivityInfoDlg.collectData = function(status) {
	this.communityActivityInfoData['content'] = CommunityActivityInfoDlg.editor.txt.html();	
	var zTree = $.fn.zTree.getZTreeObj("zTree");
	var nodes = zTree.getCheckedNodes();
	//neighborhoodIds
	var nbIds=new Array();
	for(var i in nodes){
		console.log(nodes[i]);
		node=nodes[i];
		if(!node.isParent){	
			if(node.level==1){//小区那一层
				nbIds.push(parseInt(node.id));
			}
		}
	}
	this.communityActivityInfoData['status']=status;
	this.communityActivityInfoData['neighborhoodIds']=nbIds;
	this.communityActivityInfoData['status']=status; 
    this
    .set('id')
    .set('activityType')
    .set('activityName')
    .set('pubTime')
   ;
}

/**
 * 提交添加
 */
CommunityActivityInfoDlg.addSubmit = function(status) {
	 	this.clearData();
	    this.collectData(status);
	    if (!this.validate()) {
	        return;
	    }
	    console.log(this.communityActivityInfoData['content']);
	    if(this.communityActivityInfoData['content']==null||this.communityActivityInfoData['content']=='<p><br></p>'){
			Feng.alert("请输入发布的内容");
			return;
		}
	    if(this.communityActivityInfoData['neighborhoodIds'].length==0){
			Feng.alert("请选择发送范围");
			return;
		}
	    $.ajax({
	    	type:"post",
	    	url:Feng.ctxPath + "/communityActivity/add",
	    	contentType:"application/json",
	    	data:JSON.stringify(this.communityActivityInfoData),
	    	dataType:"json",
	    	success:function(data){
	    		Feng.success(data.message);
	    		window.parent.CommunityActivity.table.refresh();
	    		CommunityActivityInfoDlg.close();
	    	},
	    	error:function(){
	    		Feng.error("后台异常，请联系管理员");
	    	}
	    })
}

/**
 * 提交修改
 */
CommunityActivityInfoDlg.editSubmit = function(status) {

    this.clearData();
    this.collectData(status);
    if (!this.validate()) {
        return;
    }
    if(this.communityActivityInfoData['content']==null||this.communityActivityInfoData['content']=='<p><br></p>'){
		Feng.alert("请输入发布的内容");
		return;
	}
    if(this.communityActivityInfoData['neighborhoodIds'].length==0){
		Feng.alert("请选择发送范围");
		return;
	}
    this.communityActivityInfoData['id']=$("#id").val();
    $.ajax({
    	type:"post",
    	url:Feng.ctxPath + "/communityActivity/update",
    	contentType:"application/json",
    	data:JSON.stringify(this.communityActivityInfoData),
    	dataType:"json",
    	success:function(data){
    		Feng.success(data.message);
    		window.parent.CommunityActivity.table.refresh();
    		CommunityActivityInfoDlg.close();
    	},
    	error:function(){
    		Feng.error("后台异常，请联系管理员");
    	}
    })
}

CommunityActivityInfoDlg.check=function(status){
	var param={
			"id":$("#id").val(),
			"taskId":$("#taskId").val(),
			"status":status,
			"verifyReply":$("#verifyReply").val()
	}
	if(param.verifyReply==null||param.verifyReply==''){
		Feng.alert("请输入审核回复");
		return;
	}else{
		$.ajax({
	    	type:"post",
	    	url:Feng.ctxPath + "/communityActivity/submitCheck",
	    	contentType:"application/json",
	    	data:JSON.stringify(param),
	    	dataType:"json",
	    	success:function(data){
	    		Feng.success(data.message);
	    		window.parent.RuTask.table.refresh();
	    	    parent.layer.close(window.parent.RuTask.layerIndex);
	    	},
	    	error:function(){
	    		Feng.error("后台异常，请联系管理员");
	    	}
	    })
	}
}

$(function() {
	  Feng.initValidator("communityactivityForm", CommunityActivityInfoDlg.validateFields);
	  //初始化富文本框
	  editor = new wangEditor("#editor");
	  editor.create();
	  editor.txt.html($("#contentVal").val());
	  CommunityActivityInfoDlg.editor = editor;
	  //ztree
	  initZtree();
});
/////////////////////////////////////////ztree////////////////////////////////////////////////////////
function initZtree() {
    var setting = {
        check: {
            enable: true,
            chkboxType: { "Y": "ps", "N": "ps" }
        },
        data: {
            simpleData: {
                enable: true
            }
        }
    };
    var param=$("#id").val()!=undefined?$("#id").val():'';
    var ztree = new $ZTree("zTree", "/communityActivity/queryNBTree?id="+param);
  //  var ztree = new $ZTree("zTree", "/roomInfo/createNBTree/1");
    
    ztree.setSettings(setting);
    ztree.init();
}
