/**
 * 初始化详情对话框
 */
var CommunityBulletinInfoDlg = {
    communityBulletinInfoData : {},
				editor: null,
				validateFields: {
				    title: {
				        validators: {
				            notEmpty: {
				                message: '标题不能为空'
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
CommunityBulletinInfoDlg.validate = function () {
    $('#communitybulletinForm').data("bootstrapValidator").resetForm();
    $('#communitybulletinForm').bootstrapValidator('validate');
    return $("#communitybulletinForm").data('bootstrapValidator').isValid();
};
/**
 * 清除数据
 */
CommunityBulletinInfoDlg.clearData = function() {
    this.communityBulletinInfoData = {};
}

/**
 * 设置对话框中的数据
 * 
 * @param key
 *            数据的名称
 * @param val
 *            数据的具体值
 */
CommunityBulletinInfoDlg.set = function(key, val) {
    this.communityBulletinInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 * 
 * @param key
 *            数据的名称
 * @param val
 *            数据的具体值
 */
CommunityBulletinInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CommunityBulletinInfoDlg.close = function() {
    parent.layer.close(window.parent.CommunityBulletin.layerIndex);
}

/**
 * 收集数据
 */
CommunityBulletinInfoDlg.collectData = function(status) {
	this.communityBulletinInfoData['content'] = CommunityBulletinInfoDlg.editor.txt.html();
	
	var zTree = $.fn.zTree.getZTreeObj("zTree");
	var nodes = zTree.getCheckedNodes();
	var buildingIds=new Array();
	for(var i in nodes){
		node=nodes[i];
		if(!node.isParent){	
			if(node.level==2){//楼宇那一层
				buildingIds.push(parseInt(node.pcode));
			}
		}
	}
	this.communityBulletinInfoData['buildingIds']=buildingIds;
	this.communityBulletinInfoData['status']=status; 
    this
    .set('id')
    .set('title')
    .set('pubTime');
    console.log(this.communityBulletinInfoData);
}

/**
 * 提交添加
 */
CommunityBulletinInfoDlg.addSubmit = function(status) {
    this.clearData();
    this.collectData(status);
    if (!this.validate()) {
        return;
    }
    console.log(this.communityBulletinInfoData['content']);
    if(this.communityBulletinInfoData['content']==null||this.communityBulletinInfoData['content']=='<p><br></p>'){
		Feng.alert("请输入发布的内容");
		return;
	}
    if(this.communityBulletinInfoData['buildingIds'].length==0){
		Feng.alert("请选择发送范围");
		return;
	}
    $.ajax({
    	type:"post",
    	url:Feng.ctxPath + "/communityBulletin/add",
    	contentType:"application/json",
    	data:JSON.stringify(this.communityBulletinInfoData),
    	dataType:"json",
    	success:function(data){
    		if(data.code==200){
    			Feng.success(data.message);
    		}else{
    			Feng.error(data.message);
    		}
    		window.parent.CommunityBulletin.table.refresh();
            CommunityBulletinInfoDlg.close();
    	},
    	error:function(){
    		Feng.error("后台异常，请联系管理员");
    	}
    })
}
//详情页的提交
CommunityBulletinInfoDlg.sub=function(){
		var status=$("#status").val();
		if(status!=1&&status!=4){
			Feng.error("只有草稿状态和被驳回状态可以提交审核！");
			return;
		}
		 var ajax = new $ax(Feng.ctxPath + "/communityBulletin/sub", function (data) {
			 	if(data.code==200){
	    			Feng.success(data.message);
	    		}else{
	    			Feng.error(data.message);
	    		}
	    		window.parent.CommunityBulletin.table.refresh();
	            CommunityBulletinInfoDlg.close();
	        }, function (data) {
	            Feng.error("提交审核失败!" + data.message + "!");
	        });
	        ajax.set("communityBulletinId",$("#id").val());
	        ajax.start();
}
//详情页的撤回
CommunityBulletinInfoDlg.callback=function(){
		var status=$("#status").val();
		if(status!=2){
			Feng.error("只有审核中的公告可以撤回！");
			return;
		}
		 var ajax = new $ax(Feng.ctxPath + "/communityBulletin/callback", function (data) {
			 	if(data.code==200){
	    			Feng.success(data.message);
	    		}else{
	    			Feng.error(data.message);
	    		}
	    		window.parent.CommunityBulletin.table.refresh();
	            CommunityBulletinInfoDlg.close();
	        }, function (data) {
	            Feng.error("撤回失败!" + data.message + "!");
	        });
	        ajax.set("communityBulletinId",$("#id").val());
	        ajax.start();
}
//详情页的发布
CommunityBulletinInfoDlg.pub=function(){
		var status=$("#status").val();
		if(status!=3){
			Feng.error("只有审核通过的公告可以发布！");
			return;
		}
		 var ajax = new $ax(Feng.ctxPath + "/communityBulletin/pub", function (data) {
			 	if(data.code==200){
	    			Feng.success(data.message);
	    		}else{
	    			Feng.error(data.message);
	    		}
	    		window.parent.CommunityBulletin.table.refresh();
	            CommunityBulletinInfoDlg.close();
	        }, function (data) {
	            Feng.error("发布失败!" + data.message + "!");
	        });
	        ajax.set("communityBulletinId",$("#id").val());
	        ajax.start();
}
/**
 * 提交修改
 */
CommunityBulletinInfoDlg.editSubmit = function(status) {
	this.clearData();
    this.collectData(status);
    this.communityBulletinInfoData['id']=$("#id").val(); 
    if (!this.validate()) {
        return;
    }
    console.log(this.communityBulletinInfoData['content']);
    if(this.communityBulletinInfoData['content']==null||this.communityBulletinInfoData['content']=='<p><br></p>'){
		Feng.alert("请输入发布的内容");
		return;
	}
    if(this.communityBulletinInfoData['buildingIds'].length==0){
		Feng.alert("请选择发送范围");
		return;
	}
    $.ajax({
    	type:"post",
    	url:Feng.ctxPath + "/communityBulletin/update",
    	contentType:"application/json",
    	data:JSON.stringify(this.communityBulletinInfoData),
    	dataType:"json",
    	success:function(data){
    		if(data.code==200){
    			Feng.success(data.message);
    		}else{
    			Feng.error(data.message);
    		}
    		window.parent.CommunityBulletin.table.refresh();
            CommunityBulletinInfoDlg.close();
    	},
    	error:function(){
    		Feng.error("后台异常，请联系管理员");
    	}
    })
}
CommunityBulletinInfoDlg.check=function(status){
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
	    	url:Feng.ctxPath + "/communityBulletin/submitCheck",
	    	contentType:"application/json",
	    	data:JSON.stringify(param),
	    	dataType:"json",
	    	success:function(data){
	    		if(data.code==200){
	    			Feng.success(data.message);
	    		}else{
	    			Feng.error(data.message);
	    		}
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
	  Feng.initValidator("communitybulletinForm", CommunityBulletinInfoDlg.validateFields);
	  //初始化富文本框
	  editor = new wangEditor("#editor");
	  editor.create();
	  editor.txt.html($("#contentVal").val());
	  CommunityBulletinInfoDlg.editor = editor;
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
    var param=$("#id").val()!=undefined?$("#id").val():-1;
    var ztree = new $ZTree("zTree", "/communityBulletin/queryBuildingTree/"+param);
    ztree.setSettings(setting);
    ztree.init();
}
