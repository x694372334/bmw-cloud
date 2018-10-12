/**
 * 初始化车辆管理详情对话框
 */
var IVehicleInfoInfoDlg = {
    iVehicleInfoInfoData : {},
    validateFields: {
    	vTrademark: {
	        validators: {
	            notEmpty: {
	                message: '车辆品牌不能为空'
	            },
	            stringLength: {
                    min: 0,
                    max: 100,
                    message: '超出长度'
		        }
	        }
	    },
	    vType: {
	        validators: {
	            notEmpty: {
	                message: '车辆类型不能为空'
	            },
	            stringLength: {
                    min: 0,
                    max: 100,
                    message: '超出长度'
		        }
	        }
	    },
	    vNumber: {
	        validators: {
	            notEmpty: {
	                message: '车牌号码不能为空'
	            },
	            stringLength: {
                    min: 0,
                    max: 50,
                    message: '超出长度'
		        }
	        }
	    },
    }
};

/**
 * 清除数据
 */
IVehicleInfoInfoDlg.clearData = function() {
    this.iVehicleInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
IVehicleInfoInfoDlg.set = function(key, val) {
    this.iVehicleInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
IVehicleInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
IVehicleInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.IVehicleInfo.layerIndex);
}

/**
 * 收集数据
 */
IVehicleInfoInfoDlg.collectData = function() {
    this
    .set("vId")
    .set("iId")
    .set("vTrademark")
    .set("vType")
    .set("vNumber")
}

/**
 * 提交添加
 */
IVehicleInfoInfoDlg.addSubmit = function() {
	selectSubmit();
    this.clearData();
    this.collectData();
    
	 $.ajax({
			type:"POST",
			url:Feng.ctxPath+"/parkingInfo/list",
			dataType: "json",
			contentType: "application/json",
			catch:true,
			 success: function (data) {
				 console.log(data)
				 var optionstring = "";
	             for (var i = 0 , len = data.length; i < len; i++) {
	            	 if(data[i].vNumber==$("#vNumber").val()){
	            		 Feng.error("车牌号码重复!");
	            		 return ;
	            	 }else{
	            		    //提交信息
	            	    var ajax = new $ax(Feng.ctxPath + "/iVehicleInfo/add", function(data){
	            	        Feng.success("添加成功!");
	            	        window.parent.IVehicleInfo.table.refresh();
	            	        IVehicleInfoInfoDlg.close();
	            	    },function(data){
	            	        Feng.error("添加失败!" + data.responseJSON.message + "!");
	            	    });
	            	    ajax.set(IVehicleInfoInfoDlg.iVehicleInfoInfoData);
	            	    ajax.start();
	            	    return ;
	            	 }
	             }
			 }
		})
}

/**
 * 提交修改
 */
IVehicleInfoInfoDlg.editSubmit = function() {
	selectSubmit();
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/iVehicleInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.IVehicleInfo.table.refresh();
        IVehicleInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.iVehicleInfoInfoData);
    ajax.start();
}

IVehicleInfoInfoDlg.showRoomSelectTree=function(){
	 var cityObj = $("#citySel");
	    var cityPosition = $("#citySel").position();
	    console.log(cityPosition);
	    cityPosition.top+=25;
	    cityPosition.left+=24;
	    $("#roomContent").css({
	        left: cityPosition.left + "px",
	        top: cityPosition.top + cityObj.outerHeight() + "px"
	    }).slideDown("fast");
	    $("body").bind("mousedown", onBodyDown);
}

function onBodyDown(event) {
  if (!(event.target.id == "menuBtn" || event.target.id == "roomContent" || $(
          event.target).parents("#roomContent").length > 0)) {
  	IVehicleInfoInfoDlg.hideRoomSelectTree();
  }
}

IVehicleInfoInfoDlg.hideRoomSelectTree = function () {
  $("#roomContent").fadeOut("fast");
  $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};
IVehicleInfoInfoDlg.onClickRoom= function (e, treeId, treeNode) {
	if(treeNode.level!=3){
		Feng.alert("请选择房间");
		return;
	}
	console.log(treeId);
	console.log(treeNode);
  $("#citySel").attr("value", instance.getSelectedVal());
  $("#roomId").attr("value", treeNode.pcode);
  inhabitantInfo(treeNode.pcode);
};

/**
 * 住户列表
 * */
function inhabitantInfo(e){
	//清空数据
	$("#test").find("option").remove(); 
	data = e;
	if(e=="0"){
		data = $("#roomId").val();
	}
	 $.ajax({
			type:"POST",
			url:Feng.ctxPath+"/parkingInfo/findInhabitant/"+data,
			dataType: "json",
			contentType: "application/json",
			catch:true,
			 success: function (data) {
				 var optionstring = "";
	             for (var i = 0; i < data.length; i++) {
	            	 console.log(data[i].iName);
	            	 if($("#iId").val() == data[i].iId){
		                 $("#test").append("<option value='"+data[i].iId+"' selected>"+ data[i].iName +"</option>");
		                 break;
	            	 }else{
	 	                $("#test").append("<option value='"+data[i].iId+"'>"+data[i].iName+"</option>");
	            	 }
	             }
			 }
		})
//}
}

function selectSubmit(){
	var options=$("#test option:selected");
	$("#iId").val(options.val());
}
	inhabitantInfo("0");
$(function() {
	var options=$("#iId option:selected");
	
	 var ztree = new $ZTree("treeDemo", "/roomInfo/createNBTree/3/0");
	    ztree.bindOnClick(IVehicleInfoInfoDlg.onClickRoom);
	    ztree.init();
	    instance = ztree;
});
