/**
 * 批量关联收费标准管理初始化
 */
var ScopeIdsDlg = {
    id: "addScopeIdsTable",	//表格id
    batchStandardsInfoData : {},
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

ScopeIdsDlg.clearData = function() {
    this.batchStandardsInfoData = {};
}


ScopeIdsDlg.collectData = function() {
	//所有没有选中的值
	var nonselecteds = $("#bootstrap-duallistbox-nonselected-list_scopeIds option").map(function(){
	     return $(this).val();
	 }).get().join(",");
	
	var unselectedScopeIds = "";
	if(nonselecteds.indexOf(",") != -1){
		$.each(nonselecteds.split(","),function(index,value){  
			if(value.indexOf("#") != -1){
				unselectedScopeIds += value.replace("#","") + ",";
			}
		}); 
	}else{
		if(nonselecteds.indexOf("#") != -1){
			unselectedScopeIds = nonselecteds.replace("#","") + ",";
		}
	}
	
	
	//所有选中的值
	var selecteds = $("#bootstrap-duallistbox-selected-list_scopeIds option").map(function(){
		return $(this).val();
	}).get().join(",");
	
	var selectedScopeIds = "";
	if(selecteds.indexOf(",") != -1){
		$.each(selecteds.split(","),function(index,value){  
			if(value.indexOf("@") != -1){
				selectedScopeIds += value.replace("@","") + ",";
			}
		}); 
	}else{
		if(selecteds.indexOf("@") != -1){
			selectedScopeIds = selecteds.replace("@","") + ",";
		}
	}
	this.batchStandardsInfoData['relevanceId'] = $("#relevanceId").val();
	this.batchStandardsInfoData['scopeId']=selectedScopeIds.substring(0,selectedScopeIds.length - 1);
	this.batchStandardsInfoData['unScopeIds']=unselectedScopeIds.substring(0,unselectedScopeIds.length - 1);
	this.batchStandardsInfoData['id']=$("#id").val();
	this.batchStandardsInfoData['nbId'] = $("#nbId").val();
	
	this.batchStandardsInfoData['standardId'] = $("#standardId").val();
	this.batchStandardsInfoData['standardName'] = $("#standardName").val();
	this.batchStandardsInfoData['nbName'] = $("#nbName").val();
	this.batchStandardsInfoData['costId'] = $("#costId").val();
	this.batchStandardsInfoData['eId'] = $("#eId").val();
	this.batchStandardsInfoData['startDate'] = $("#chargeableStartDate").val();
	this.batchStandardsInfoData['endDate'] = $("#chargeableEndDate").val();
	
}

ScopeIdsDlg.close = function() {
    parent.layer.close(window.parent.BatchStandards.layerIndex);
}

ScopeIdsDlg.addSubmit = function() {

	this.clearData();
	this.collectData();
	
	  var ajax = new $ax(Feng.ctxPath + "/batchStandards/addScopeIds", function(data){
		  
		  parent.layer.alert('保存成功!', 1, function(index){
			  parent.layer.close(index);
			  if(ScopeIdsDlg.batchStandardsInfoData.unScopeIds != ""){
				  layer.confirm('是否作废账单信息？', {
					  btn: ['确定','取消'] //按钮
					}, function(){
						ScopeIdsDlg.operation();
					}, function(){
						ScopeIdsDlg.close();
					});
			  }else{
				  ScopeIdsDlg.close();
			  }
			});   

	    },function(data){
	        Feng.error("添加失败!" + data.responseJSON.message + "!");
	    });
	    ajax.set(this.batchStandardsInfoData);
	    ajax.start();
}

ScopeIdsDlg.operation = function(){
	ScopeIdsDlg.clearData();
	ScopeIdsDlg.collectData();
	var ajax = new $ax(Feng.ctxPath + "/batchStandards/deleteBill", function (data) {
		if(data.message == 'true'){
			  Feng.success("账单作废成功!");
		}
		 ScopeIdsDlg.close();
     }, function (data) {
         Feng.error("删除失败!" + data.responseJSON.message + "!");
     });
	 ajax.set(ScopeIdsDlg.batchStandardsInfoData);
     ajax.start();
}

init = function(){
	var url = "";
	var relevance = $("#relevanceId").val();
	if(relevance == 1 || relevance == 4 || relevance==5){
		url = "/roomInfo/selectData";
	}else if(relevance == 2){
		url = "/parkingInfo/selectData";
	}else if(relevance == 3){
		url = "/advertising/selectData";
	}
	
	
	var ajax = new $ax(Feng.ctxPath + url, function (data) {
		 var scopeIds = $('#scopeIds').doublebox({
	         nonSelectedListLabel: '请选择',
	         selectedListLabel: '已选择',
	         preserveSelectionOnMove: 'moved',
	         moveOnSelect: false,
	         nonSelectedList:data.uncheckedMap,
	         selectedList:data.checkedMap,
	         optionValue:"rId",
	         optionText:"rRoomnum",
	         doubleMove:true,
	       });
    }, function (data) {
        Feng.error(data.responseJSON.message + "!");
    });
	ajax.set("nbId",$("#nbId").val());
    ajax.set("id",$("#id").val());
    ajax.set("relevanceId",$("#relevanceId").val());
    ajax.set("costId",$("#costId").val());
    ajax.start();

}

/**************************************时间格式化处理************************************/
function dateFtt(fmt,date)   
{ //author: meizz   
  var o = {   
    "M+" : date.getMonth()+1,                 //月份   
    "d+" : date.getDate(),                    //日   
    "h+" : date.getHours(),                   //小时   
    "m+" : date.getMinutes(),                 //分   
    "s+" : date.getSeconds(),                 //秒   
    "q+" : Math.floor((date.getMonth()+3)/3), //季度   
    "S"  : date.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
}

$(function () {
	
	 init();
/*	 if($('#chargeableEndDate').val() < dateFtt("yyyy-MM-dd",new Date())) {
		 $("#ensure").hide();
	 }*/
	 
});
