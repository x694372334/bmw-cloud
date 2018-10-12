/**
 * 初始化抄表基础详情对话框
 */
var MBaseInfoInfoDlg = {
    mBaseInfoInfoData : {},
    validateFields: {
    	test: {
		        validators: {
		            notEmpty: {
		                message: '不能为空'
		            }
		        }
		    },
		    mType: {
		        validators: {
		            notEmpty: {
		                message: '类别不能为空'
		            },
		            stringLength: {
	                    min: 0,
	                    max: 1,
	                    message: '超出长度'
			        }
		        }
		    },
		    mMultiple: {
		        validators: {
		            notEmpty: {
		                message: '倍率不能为空'
		            },
		            stringLength: {
	                    min: 0,
	                    max: 5,
	                    message: '超出长度'
			        }
		        }
		    },
		    mWastage: {
		        validators: {
		            notEmpty: {
		                message: '倍率不能为空'
		            },
		            stringLength: {
	                    min: 0,
	                    max: 5,
	                    message: '超出长度'
			        }
		        }
		    },
		    mCommonality: {
		        validators: {
		            notEmpty: {
		                message: '倍率不能为空'
		            },
		            stringLength: {
	                    min: 0,
	                    max: 5,
	                    message: '超出长度'
			        }
		        }
		    }
    }
};

/**
 * 清除数据
 */
MBaseInfoInfoDlg.clearData = function() {
    this.mBaseInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MBaseInfoInfoDlg.set = function(key, val) {
    this.mBaseInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MBaseInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MBaseInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.MBaseInfo.layerIndex);
}

/**
 * 收集数据
 */
MBaseInfoInfoDlg.collectData = function() {
    this.set("rId").set("sId").set("mType").set("mMultiple").set("mWastage").set("mCommonality").set("uAmount").set("tMonth").set("tNum")
    .set("text").set("id").set("tableNumber")
}

/**
 * 提交添加
 */
MBaseInfoInfoDlg.addSubmit = function() {
	submit();
    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mBaseInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.MBaseInfo.table.refresh();
        MBaseInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.mBaseInfoInfoData);
    ajax.start();
}

MBaseInfoInfoDlg.dosageSubmit = function() {
	if($("#test").val()<$("#tNum").val()){//判断表数不可小于上期表数
		return Feng.error("本期表数不可小于上期表数");
	}
	//****************************************判断月份不可大于当前月份
	var result=$("#tMonth").val().split("-");
	var myDate = new Date();
	var year =myDate.getFullYear();
	var monthOne = myDate.getMonth()+1;
	if(monthOne<10){
		monthOne = "0"+(myDate.getMonth()+1);
	}
	var monthTwo = year+monthOne;
	monthThree= result[0]+result[1];
	if(monthThree>monthTwo){
		return Feng.error("月份不可大于当月");
	}
	//*******************************************判断当前月份如添加过则不可再次添加
	var month = "0";
	 $.ajax({
	        "type" : 'get',
	        "url": Feng.ctxPath + "/mRecord/findMonth/"+$("#sId").val()+"/"+monthThree,
	        "dataType" : "json",
	        "success" : function(data) {
	        	var result=$("#tMonth").val().split("-");
	        	if(monthThree==data){
	        		month="1";
	        	}
	        	if(month=="1"){
	        		return Feng.error("不可重复添加相同月份");
	        	}else{
	        		submit2();
	        		
	        		MBaseInfoInfoDlg.clearData();
	        		MBaseInfoInfoDlg.collectData();
	        		console.log(MBaseInfoInfoDlg.mBaseInfoInfoData);	
	        	    if (!MBaseInfoInfoDlg.validate()) {
	        	        return;
	        	    }
	        	    
//	        	    //提交信息
	        	    var ajax = new $ax(Feng.ctxPath + "/mRecord/add", function(data){
	        	        Feng.success("添加成功!");
	        	        window.parent.MBaseInfo.table.refresh();
	        	        MBaseInfoInfoDlg.close();
	        	    },function(data){
	        	        Feng.error("添加失败!" + data.responseJSON.message + "!");
	        	    });
	        	    ajax.set(MBaseInfoInfoDlg.mBaseInfoInfoData);
	        	    ajax.start();
	        	}
	        }
	    });
	
	//********************************************
}

/**
 * 提交修改
 */
MBaseInfoInfoDlg.editSubmit = function() {
	submit();
    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mBaseInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.MBaseInfo.table.refresh();
        MBaseInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.mBaseInfoInfoData);
    ajax.start();
}

/**
 * 提交通过
 */
MBaseInfoInfoDlg.approvalSubmit = function() {
	$("#text").val($("#test").val());
	submit();
    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mRecord/updateApproval", function(data){
        Feng.success("通过成功!");
        window.parent.MBaseInfo.table.refresh();
        MBaseInfoInfoDlg.close();
    },function(data){
        Feng.error("通过失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.mBaseInfoInfoData);
    ajax.start();
}

/**
 * 提交驳回
 */
MBaseInfoInfoDlg.relieveSubmit = function() {
	submit();
    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mRecord/updateRelieve", function(data){
        Feng.success("修改成功!");
        window.parent.MBaseInfo.table.refresh();
        MBaseInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.mBaseInfoInfoData);
    ajax.start();
}

MBaseInfoInfoDlg.showRoomSelectTree=function(){
	 var cityObj = $("#citySel");
	    var cityPosition = $("#citySel").position();
	    console.log(cityPosition);
	    cityPosition.top+=56;
	    cityPosition.left+=22;
	    $("#roomContent").css({
	        left: cityPosition.left + "px",
	        top: cityPosition.top + cityObj.outerHeight() + "px"
	    }).slideDown("fast");
	    $("body").bind("mousedown", onBodyDown);
}

function onBodyDown(event) {
   if (!(event.target.id == "menuBtn" || event.target.id == "roomContent" || $(
           event.target).parents("#roomContent").length > 0)) {
   	MBaseInfoInfoDlg.hideRoomSelectTree();
   }
}

MBaseInfoInfoDlg.hideRoomSelectTree = function () {
   $("#roomContent").fadeOut("fast");
   $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};
MBaseInfoInfoDlg.onClickRoom= function (e, treeId, treeNode) {
	if(treeNode.level!=3){
		Feng.alert("请选择房间");
		return;
	}
	console.log(treeId);
	console.log(treeNode);
   $("#citySel").attr("value", instance.getSelectedVal());
   $("#roomId").attr("value", treeNode.pcode);
};

MBaseInfoInfoDlg.validate = function () {
    $('#mBaseInfoForm').data("bootstrapValidator").resetForm();
    $('#mBaseInfoForm').bootstrapValidator('validate');
    return $("#mBaseInfoForm").data('bootstrapValidator').isValid();
};

function mType(){
	if($("#tMonth").val()!=null){
		var month = $("#tMonth").val().split("-");
		var text = month[1]-1;
		var monthOne = "";
		if(text<10){
			monthOne = month[0]+"0"+text;
		}else{
			monthOne = month[0]+text;
		}
		 $.ajax({
		        "type" : 'get',
		        "url": Feng.ctxPath + "/mBaseDoubleInfo/findMonth/"+$("#sId").val()+"/"+monthOne,
		        "dataType" : "json",
		        "success" : function(data) {
		        	console.log(data)
		        	$("#tNum").val(data.tNum)
		        }
		    });
	}else{
		 $.ajax({
		        "type" : 'get',
		        "url": Feng.ctxPath + "/dict/getDic",
		        "dataType" : "json",
		        "data" : {"code" : "cblb"},
		        "success" : function(data) {
		            for(var i = 0 ; i < data.length; i++ ){
		            	if($("#mType").val() == data[i].num){
		            		 $("#mType").append("<option value='"+data[i].num+"' selected='selected'>"+data[i].name+"</option>");
		            	}else{
		            		$("#mType").append("<option value='"+data[i].num+"'>"+data[i].name+"</option>");
		            	}
		                
		            }
		        }
		    });
	}
}


function mTypeT(){
}


function submit(){
	var options=$("#test option:selected");
	$("#rId").val($("#roomId").val());
//	$("#uAmount").val($("#test").val());
}

function submit2(){
	var options=$("#test option:selected");
	var data = $("#text").val();
	data = data + "|" + $("#test").val();
	$("#text").val(data);
}
$(function() {
	mType();
//	mTypeT();
	 Feng.initValidator("mBaseInfoForm", MBaseInfoInfoDlg.validateFields);
	
	 var ztree = new $ZTree("treeDemo", "/roomInfo/createNBTree/3/0");
	    ztree.bindOnClick(MBaseInfoInfoDlg.onClickRoom);
	    ztree.init();
	    instance = ztree;

});
