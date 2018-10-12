/**
 * 初始化批量关联收费标准详情对话框
 */
var BatchStandardsInfoDlg = {
    batchStandardsInfoData : {},
	remark:null,
	validateFields: {
		treeid: {
            validators: {
                notEmpty: {
                    message: '请选择关联范围'
                }
            }
        },
        relevanceId: {
            validators: {
                notEmpty: {
                    message: '请选择关联对象'
                }
            }
        },
        costId: {
            validators: {
                notEmpty: {
                    message: '请选择费项名称'
                }
            }
        },
        standardId: {
            validators: {
                notEmpty: {
                    message: '请选择收费标准'
                }
            }
        },
        chargeableStartDate: {
            validators: {
                notEmpty: {
                    message: '请选择计费开始时间'
                }
            }
        },
        chargeableEndDate: {
            validators: {
                notEmpty: {
                    message: '请选择计费结束时间'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
BatchStandardsInfoDlg.clearData = function() {
    this.batchStandardsInfoData = {};
}



/**
 * 验证数据是否为空
 */
BatchStandardsInfoDlg.validate = function () {
    $('#batchStandardsForm').data("bootstrapValidator").resetForm();
    $('#batchStandardsForm').bootstrapValidator('validate');
    return $("#batchStandardsForm").data('bootstrapValidator').isValid();
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BatchStandardsInfoDlg.set = function(key, val) {
    this.batchStandardsInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BatchStandardsInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BatchStandardsInfoDlg.close = function() {
	if(window.parent.ParkingInfo){
		parent.layer.close(window.parent.ParkingInfo.layerIndex);
	}else if(window.parent.RoomInfo){
		parent.layer.close(window.parent.RoomInfo.layerIndex);
	}else{
		parent.layer.close(window.parent.BatchStandards.layerIndex);
	}
}

/**
 * 收集数据
 */
BatchStandardsInfoDlg.collectData = function() {
	
	this.batchStandardsInfoData['relevanceId'] = $("#costId").val().split(",")[2];
	this.batchStandardsInfoData['costId'] = $("#costId").val().split(",")[0];
    this.batchStandardsInfoData['costName'] = $("#costId").find("option:selected").text();
    this.batchStandardsInfoData['standardName'] = $("#standardId").find("option:selected").text();
    this.batchStandardsInfoData['remark'] = BatchStandardsInfoDlg.remark.txt.html();
    this.batchStandardsInfoData['startDate'] = $("#chargeableStartDate").val();
    if(!$('#isChecked').is(":checked")) this.batchStandardsInfoData['endDate'] = $("#chargeableEndDate").val();
    
    this
    .set('id')
  //  .set('relevanceId')
    .set('standardId');
   /* var zTree = $.fn.zTree.getZTreeObj("scopeTree");
	var nodes = zTree.getCheckedNodes();
	var scopeIds = "";//范围ID
	var nbId = "";
	var nbName = "";
	
	for(var i in nodes){
		node=nodes[i];
		
		var relevanceId = $("#costId").val().split(",")[2];
		if(relevanceId == 1 || relevanceId ==4 ||relevanceId == 5){
			if(node.level==3){//房屋
				scopeIds += node.pcode+","
				nbId = node.getParentNode().getParentNode().pcode;
				nbName = node.getParentNode().getParentNode().name;
			}
		}else{
			if(node.level==2){//车位、广告位
				scopeIds += node.pcode+","
				nbId = node.getParentNode().pcode;
				nbName = node.getParentNode().name;
			}
		}
	}*/
	//this.batchStandardsInfoData['scopeId']=scopeIds.substring(0,scopeIds.length - 1);
	this.batchStandardsInfoData['nbId'] = $("#costId").val().split(",")[1];
	//this.batchStandardsInfoData['nbName'] = nbName;
}

/**
 * 提交添加
 */
BatchStandardsInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }
    var url = "";
    if($("#pId").val()){
    	url = Feng.ctxPath + "/batchStandards/add/"+$("#pId").val();
    }else if($("#rId").val()){
    	url = Feng.ctxPath + "/batchStandards/addByRoomInfo/"+$("#rId").val();
    }else{
    	url = Feng.ctxPath + "/batchStandards/add";
    }
	 //提交信息
    var ajax = new $ax(url, function(data){
    	if(data.message == -1){
    		Feng.info("保存失败！相同收费项目和收费标准只能保存一条！");
    		return;
    	}else if(data.message == 0){
    		Feng.info("保存失败！原因为账单生成失败！");
    		return;
    	}else{
    		Feng.success("添加成功!");
    		if(window.parent.BatchStandards){
    			window.parent.BatchStandards.table.refresh();
    		}
	        BatchStandardsInfoDlg.close();
    	}
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.batchStandardsInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BatchStandardsInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/batchStandards/update", function(data){
        Feng.success("修改成功!");
        window.parent.BatchStandards.table.refresh();
        BatchStandardsInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.batchStandardsInfoData);
    ajax.start();
}

/**
 * 隐藏部门选择的树
 */
/*BatchStandardsInfoDlg.hideDeptSelectTree = function () {
    $("#treeContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};


BatchStandardsInfoDlg.showDeptSelectTree = function () {
    var cityObj = $("#treeid");
    var cityOffset = $("#treeid").offset();
    $("#treeContent").css({
        left: cityOffset.left + "px",
        top: cityOffset.top + cityObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
};

function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "treeContent" || $(
            event.target).parents("#treeContent").length > 0)) {
    	BatchStandardsInfoDlg.hideDeptSelectTree();
    }
}*/


BatchStandardsInfoDlg.costChange = function(e){
	if($(e).val().split(",")[2] == 4 || $(e).val().split(",")[2] == 5){
		for(var i = 1; i < 3; i++){
			$("#isShow"+i).hide();
		}
	}else{
		for(var i = 1; i < 3; i++){
			$("#isShow"+i).show();
		}
	}
	//获取费项设置下拉列表
	$.ajax({
        "type" : 'get',
        "url": Feng.ctxPath + "/costStandard/getCostStandardsByCostId",
        "dataType" : "json",
        "data":{"costId":$(e).val().split(",")[0]},
        "success" : function(data) {
        	$("#standardId").empty();
            for(var i = 0 ; i < data.length; i++ ){
            	if($("#standard").val() == data[i].id){
            		 $("#standardId").append("<option value='"+data[i].id+"' selected='selected'>"+data[i].standardName+"</option>");
            	}else{
            		$("#standardId").append("<option value='"+data[i].id+"'>"+data[i].standardName+"</option>");
            	}
            }
        }
    });
	
	//BatchStandardsInfoDlg.relevanceChange($("#costId").val().split(",")[2]);
}

//关联对象绑定事件，处理tree
/*BatchStandardsInfoDlg.relevanceChange = function(relevanceId){
	 var setting = {
		        check: {
		            enable: true,
		            chkboxType: { "Y": "ps", "N": "ps" }
		        },
		        data: {
		            simpleData: {
		                enable: true
		            }
		        },
		        callback: {
		    		onCheck: zTreeOnCheck
		    	}
			 };
			var url = "/roomInfo/createNBTree/3";;
			if(relevanceId){
				if(relevanceId == 1 || relevanceId == 4 || relevanceId == 5){
					url = "/roomInfo/createNBTree/3/"+$("#costId").val().split(",")[1];
				}else if(relevanceId == 2){
					url = "/parkingInfo/createCWTree/"+$("#costId").val().split(",")[1];
				}else{
					url = "/advertising/createGGWTree/"+$("#costId").val().split(",")[1];
				}
			}
		    
			var ztree = new $ZTree("scopeTree", url);
		    ztree.setSettings(setting);
		    ztree.init();
		    $("#treeid").val("");
}*/

//回显tree
/*function selectTree(){
	var etree = $.fn.zTree.getZTreeObj("scopeTree");
    var scope = $("#scope").val();
    var scopeNames = new Array();
    var isMultiple = true;//是否选中多个
    if(scope){
    	if(scope.indexOf(",") == -1){
        	isMultiple = false;
        }
        var scopes = scope.split(",");
        etree.cancelSelectedNode();//先取消所有的选中状态
        if(isMultiple){
    		for(var j=0;j<scopes.length;j++){
    			var nodes = etree.getNodesByParam("pcode", scopes[j], null);
    			for(var i in nodes){
    				node=nodes[i];
    				
    				var relevanceId = $("#costId").val().split(",")[2]
    				if(relevanceId == 1 || relevanceId ==4 ||relevanceId == 5){
    					if(node.level==3){//房屋
    						scopeNames.push(node.name);
    					}
    				}else{
    					if(node.level==2){//车位、广告位
    						scopeNames.push(node.name);
    					}
    				}
        				etree.expandNode(node, true, false);//将指定ID节点展开
        				etree.checkNode(node,true,true);
    			}
    		 }
    	}else{
    		var nodes = etree.getNodesByParam("pcode", scope, null);
    		for(var i in nodes){
    			node=nodes[i];
    			var relevanceId = $("#costId").val().split(",")[2]
				if(relevanceId == 1 || relevanceId ==4 ||relevanceId == 5){
					if(node.level==3){//房屋
						scopeNames.push(node.name);
					}
				}else{
					if(node.level==2){//车位、广告位
						scopeNames.push(node.name);
					}
				}
        			etree.expandNode(node, true, false);//将指定ID节点展开
        			etree.checkNode(node,true,true);
    		}
    	}
        $("#treeid").val("");
        $("#treeid").val(scopeNames.toString());
    }
    
}*/


//选中tree触发事件
/*function zTreeOnCheck(event, treeId, treeNode) {
	
	var zTree = $.fn.zTree.getZTreeObj("scopeTree");
	var nodes = zTree.getCheckedNodes();
	var scopeNames = "";
	for(var i in nodes){
		node=nodes[i];
		var relevanceId = $("#costId").val().split(",")[2];
		if(relevanceId == 1 || relevanceId ==4 ||relevanceId == 5){
			if(node.level==3){//房屋
				scopeNames += node.name+",";
			}
		}else{
			if(node.level==2){//车位、广告位
				scopeNames += node.name+",";
			}
		}
	}
	$("#treeid").val("");
	$("#treeid").val(scopeNames == "" ? "" : scopeNames.substring(0,scopeNames.length - 1));
};*/

//初始化tree
/*function initZtree() {
	var relevanceId = $("#costId").val().split(",")[2];
	 var setting = {
        check: {
            enable: true,
            chkboxType: { "Y": "ps", "N": "ps" }
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
    		onCheck: zTreeOnCheck
    	}
	 };
	var url = "/roomInfo/createNBTree/3/"+$("#costId").val().split(",")[1];
	if(relevanceId){
		if(relevanceId == 1 || relevanceId == 4 || relevanceId == 5){
			url = "/roomInfo/createNBTree/3/"+$("#costId").val().split(",")[1];
		}else if(relevanceId == 2){
			url = "/parkingInfo/createCWTree/"+$("#costId").val().split(",")[1];
		}else{
			url = "/advertising/createGGWTree/"+$("#costId").val().split(",")[1];
		}
	}
    
	var ztree = new $ZTree("scopeTree", url);
    ztree.setSettings(setting);
    ztree.init();
    selectTree();
    
}*/

//验证开始日期和结束日期
function validateDate(){
	var start = {
		    elem: '#chargeableStartDate',
		    format: 'YYYY-MM-DD',
		   // min: laydate.now(), //设定最小日期为当前日期
		    max: '2099-06-16', //最大日期
		    istime: false,
		    istoday: true,
		    choose: function(datas){
		         end.min = datas; //开始日选好后，重置结束日的最小日期
		        // end.start = datas //将结束日的初始值设定为开始日
		    }
		};
		var end = {
		    elem: '#chargeableEndDate',
		    format: 'YYYY-MM-DD',
		   // min: start.max,
		    max: '2099-06-16',
		    istime: false,
		    istoday: true,
		    choose: function(datas){
		    	if($("#chargeableStartDate").val() == ''){
		    		Feng.info("请先设置开始时间!");
		    		$("#chargeableEndDate").val('');
		    		return;
		    	}
		    	start.max = datas; //结束日选好后，重置开始日的最大日期
		    }
		};
		laydate(start);
		laydate(end);
}


////获取费项设置下拉列表
function getCost(){
	var url = "";
	if($("#relevanceId").val()){
		url = Feng.ctxPath + "/costSet/getCostSetAll/"+$("#relevanceId").val();
	}else{
		url = Feng.ctxPath + "/costSet/getCostSetAll";
	}
	$.ajax({
        "type" : 'get',
        "url": url,
        "dataType" : "json",
        "async" : false,
        "success" : function(data) {
            for(var i = 0 ; i < data.length; i++ ){
            	if($("#cost").val() == data[i].id){
            		 $("#costId").append("<option value='"+data[i].id+","+data[i].neighborhoodsId+","+data[i].relevanceId+"' selected='selected'>"+data[i].costName+"("+ data[i].neighborhoodsName +")" +"</option>");
            	}else{
            		$("#costId").append("<option value='"+data[i].id+","+data[i].neighborhoodsId+","+data[i].relevanceId+"'>"+data[i].costName+"("+ data[i].neighborhoodsName +")" +"</option>");
            	}
            }
            BatchStandardsInfoDlg.costChange($("#costId"));
        }
    });
}

//判断checkbox是否选中
function isCheck(){
	var isChecked = true;
	//默认选中
	$('#isChecked').bootstrapSwitch('state', true);
	if($("#chargeableEndDate").val() != ""){
		$('#isChecked').bootstrapSwitch('state', false);
		isChecked = false;
	}
	isHiddenEndDate(isChecked);
}

//checkbox绑定事件
function isCheckedClick(e){
	isHiddenEndDate($(e).is(":checked"));
}

//判断是否隐藏结束时间
function isHiddenEndDate(isChecked){
	if(isChecked){
		//$("#chargeableEndDate").val("");
		$("#chargeableEndDate").hide();
	}else{
		$("#chargeableEndDate").show();
	}
}


$(function() {
	
	Feng.initValidator("batchStandardsForm", BatchStandardsInfoDlg.validateFields);
	
	//初始化富文本控件
	var E = window.wangEditor;
    var remarkEditor = new E('#remark');
    remarkEditor.create();
    remarkEditor.txt.html($("#remarkVal").val());
    BatchStandardsInfoDlg.remark = remarkEditor;
	    
	//费项设置绑定change事件，用于获取费用标准
	$("#costId").bind("change",function(){
		BatchStandardsInfoDlg.costChange(this);
	});
	
	
	//获取费项设置下拉列表
	getCost();
	
	//初始化tree 根据关联对象去初始化不同类型的tree
	//1、选择房屋：则此范围以小区、楼宇、单元、房屋树状展示
	//2、选择车位，则此范围以小区、车位树状展示
	//3、选择水电表：则此范围以小区、楼宇、单元、房屋树状展示
	//initZtree();
	
	//验证开始日期和结束日期
	validateDate();
	
	
	$("#isChecked").bootstrapSwitch({
		onText:"是",
		offText:"否",
		onColor:"success",
		offColor:"danger",
		size:"small",
		onSwitchChange:function(event,state){
			isCheckedClick($("#isChecked"));
		}
	});
	
	isCheck();
	
	
	
});
