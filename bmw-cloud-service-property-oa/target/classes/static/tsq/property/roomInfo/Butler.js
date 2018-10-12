var RoomInfoInfoDlg = {};

$(function(){
	initZtree();
})
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
    var ztree = new $ZTree("zTree", "/roomInfo/createNBTree/2/0");    
    ztree.setSettings(setting);
    ztree.init();
}
RoomInfoInfoDlg.close=function(){
	 parent.layer.close(window.parent.RoomInfo.layerIndex);
}
RoomInfoInfoDlg.treeNodes=function(){
	var zTree = $.fn.zTree.getZTreeObj("zTree");
	var nodes = zTree.getCheckedNodes();
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
	return buildingIds;
}

RoomInfoInfoDlg.editButlerSubmit = function() {
	var nodes=RoomInfoInfoDlg.treeNodes();
	 if(nodes.length==0){
			Feng.alert("请选择楼宇");
			return;
	}
	var stewardId=$("input[name='stewardId']:checked").val();
	var stewardName=$("input[name='stewardId']:checked")[0].nextSibling.nodeValue;
	
	var param={
		"stewardId":stewardId,
		"stewardName":stewardName,
		"nodes":nodes
	}
	  $.ajax({
	    	type:"post",
	    	url:Feng.ctxPath + "/roomInfo/BatchButler",
	    	contentType:"application/json",
	    	data:JSON.stringify(param),
	    	dataType:"json",
	    	success:function(data){
	    		Feng.success(data.message);
	    		window.parent.RoomInfo.table.refresh();
	    	    RoomInfoInfoDlg.close();
	    	},
	    	error:function(){
	    		Feng.error("后台异常，请联系管理员");
	    	}
	    })
}
