/**
 * 初始化flownodecondition详情对话框
 */
var FlowNodeConditionInfoDlg = {
    flowNodeConditionInfoData : {}
};

/**
 * 清除数据
 */
FlowNodeConditionInfoDlg.clearData = function() {
    this.flowNodeConditionInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FlowNodeConditionInfoDlg.set = function(key, val) {
    this.flowNodeConditionInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
FlowNodeConditionInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
FlowNodeConditionInfoDlg.close = function() {
	 parent.layer.close(window.parent.FlowNodeConfigure.layerIndex);
}

/**
 * 收集数据
 */
FlowNodeConditionInfoDlg.collectData = function() {
    this
    .set('id')
    .set('flowNodeConfigureId')
    .set('conditionType')
    .set('conditionValue')
    .set('createManId')
    .set('createMan')
    .set('createTime')
    .set('editManId')
    .set('editMan')
    .set('editTime')
    .set('conditionValueName')
    .set('isDelete')
    .set('parentEId');
}

/**
 * 提交添加
 */
FlowNodeConditionInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/flowNodeCondition/add", function(data){
        Feng.success("添加成功!");
        //window.parent.FlowConfigure.table.refresh();
        FlowNodeConditionInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    if($("#sql").val()!=""&&$("#sql").val()!=null){
    	this.flowNodeConditionInfoData.conditionValue=$("#sql").val();
    	this.flowNodeConditionInfoData.conditionValueName=$("#sql").val();
    }
    ajax.set(this.flowNodeConditionInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
FlowNodeConditionInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/flowNodeCondition/update", function(data){
        Feng.success("修改成功!");
        //window.parent.FlowConfigure.table.refresh();
        FlowNodeConditionInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    if($("#sql").val()!=""&&$("#sql").val()!=null){
    	this.flowNodeConditionInfoData.conditionValue=$("#sql").val();
    	this.flowNodeConditionInfoData.conditionValueName=$("#sql").val();
    }
    ajax.set(this.flowNodeConditionInfoData);
    ajax.start();
}

/**
 * 显示父级菜单的树
 *
 * @returns
 */
FlowNodeConditionInfoDlg.showPNameSelectTree = function () {
    Feng.showInputTree("pName", "pNameContent");
};

/**
 * 显示部门选择的树
 *
 * @returns
 */
FlowNodeConditionInfoDlg.showDeptSelectTree = function () {
    Feng.showInputTree("deptName", "deptContent");
};



function zTreeOnCheck(event, treeId, treeNode) {
	if("pNameTree"==treeId){
		refreshLayers("pNameTree","#pName");
	}else{
		refreshLayers("deptTree","#deptName");
	}
	 
};

//刷新图层的显示情况
var layers;
function refreshLayers(treeName , valId) {
	var zTree = $.fn.zTree.getZTreeObj(treeName);
	var changedNodes = zTree.getChangeCheckedNodes();
	var ids = Feng.zTreeCheckedNodes(treeName);
	//$("#conditionValue").attr("value", ids);
	$("#conditionValue").val(ids);
	var name = "";
	if(changedNodes.length==0){
		$(valId).val("");
	}
	for ( var i=0 ; i < changedNodes.length ; i++ ){
		var treeNode = changedNodes[i];
		
		if(name==""){
			name = treeNode.name;
		}else{
			name = name+","+treeNode.name;
		}
		$(valId).val(name);
		$("#conditionValueName").val(name);
	}
}

function showOrDisabled() {
	 var va = $("#conditionValueName").val();
	 if($("#conditionType").val()=="0"){
  	   $("#deptName").attr("disabled", true);
  	   $("#pName").attr("disabled", false);
  	   $("#pName").val(va);
  	   $("#sql").attr("disabled", true);
     }else if($("#conditionType").val()=="1"){
  	   $("#pName").attr("disabled", true);
  	   $("#deptName").attr("disabled", false);
  	   $("#deptName").val(va);
  	   $("#sql").attr("disabled", true);
     }else if($("#conditionType").val()=="2"){
  	   $("#pName").attr("disabled", true);
  	   $("#deptName").attr("disabled", true);
  	   $("#sql").attr("disabled", false);
  	   $("#sql").val($("#conditionValue").val());
     }else{
  	   $("#pName").attr("disabled", true);
  	   $("#deptName").attr("disabled", true);
  	   $("#sql").attr("disabled", true);
     }
}

$(function() {
		var setting = {
            check: {
                enable: true,
                chkboxType: {
                    "Y": "",
                    "N": ""
                }
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
				onCheck: zTreeOnCheck
			},

        };
		var pNameTree = new $ZTree("pNameTree", "/role/roleTreeList");
		pNameTree.setSettings(setting);
	    pNameTree.init();
	    FlowNodeConditionInfoDlg.pNameZtree = pNameTree;
	    
	    var deptTree = new $ZTree("deptTree", "/propertyDepartment/selectdepartmentTreeList",$("#conditionValue").val());
	    deptTree.setSettings(setting);
	    deptTree.init();
	    FlowNodeConditionInfoDlg.deptZtree = deptTree;
	    
	    showOrDisabled();
	    $("#conditionType").change(function(){
	    	showOrDisabled();
	       $("#conditionValueName").val("");
	       $("#deptName").val("");
	       $("#pName").val("");
	       $("#conditionValue").val("");
	       $("#sql").val("");
	       $("#conditionValueName").val("");
	     });
	    
	    String.prototype.replaceAll  = function(s1,s2){     
            return this.replace(new RegExp(s1,"gm"),s2);     
        }   
	    
	    var deptName =$("#deptName").val();
	    deptName=deptName.replaceAll("& #40;","(");
	    deptName=deptName.replaceAll("& #41;",")");
	    $("#deptName").val(deptName);
});
