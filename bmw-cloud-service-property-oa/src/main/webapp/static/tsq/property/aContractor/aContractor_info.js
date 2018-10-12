/**
 * 初始化广告位承包商详情对话框
 */
var AContractorInfoDlg = {
    aContractorInfoData : {}
};

/**
 * 清除数据
 */
AContractorInfoDlg.clearData = function() {
    this.aContractorInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AContractorInfoDlg.set = function(key, val) {
    this.aContractorInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AContractorInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AContractorInfoDlg.close = function() {
    parent.layer.close(window.parent.AContractor.layerIndex);
}

/**
 * 收集数据
 */
AContractorInfoDlg.collectData = function() {
    this
    .set("cName")
    .set("cPersion")
    .set("cBusiness")
    .set("cStatus")
    .set("nId")
    .set("cId")
}

/**
 * 提交添加
 */
AContractorInfoDlg.addSubmit = function() {
	submit();
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/aContractor/add", function(data){
        Feng.success("添加成功!");
        window.parent.AContractor.table.refresh();
        AContractorInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.aContractorInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AContractorInfoDlg.editSubmit = function() {
	submit();
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/aContractor/update", function(data){
        Feng.success("修改成功!");
        window.parent.AContractor.table.refresh();
        AContractorInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.aContractorInfoData);
    ajax.start();
}


function neighborhood(){
	var options=$("#test option:selected");
	 $.ajax({
			type:"POST",
			url:Feng.ctxPath+"/buildingInfo/findNeighbor",
			dataType: "json",
			contentType: "application/json",
			catch:true,
			 success: function (data) {
				 var optionstring = "";
	             for (var i = 0; i < data.length; i++) {
	            	 if($("#nId").val() == data[i].nId){
		                 $("#test").append("<option value='"+data[i].nId+"' selected>"+ data[i].nName +"</option>");
	            	 }else{
	 	                $("#test").append("<option value='"+data[i].nId+"'>"+data[i].nName+"</option>");
	            	 }
	             }
			 }
		})
}

function submit(){
	var options=$("#test option:selected");
	$("#nId").val(options.val());
}

/**
 * 字典表：广告位类型
 * 开发者：金明禹
 * */

function cStatus(){
	 $.ajax({
	        "type" : 'get',
	        "url": Feng.ctxPath + "/dict/getDic",
	        "dataType" : "json",
	        "data" : {"code" : "fbzt"},
	        "success" : function(data) {
	            for(var i = 0 ; i < data.length; i++ ){
	            	if($("#cStatus").val() == data[i].num){
	            		 $("#text1").append("<option value='"+data[i].num+"' selected='selected'>"+data[i].name+"</option>");
	            	}else{
	            		$("#text1").append("<option value='"+data[i].num+"'>"+data[i].name+"</option>");
	            	}
	                
	            }
	        }
	    });
}

$(function() {
	Feng.initValidator("aContractorInfoForm", AContractorInfoDlg.validateFields);
	neighborhood();
	cStatus();
});
