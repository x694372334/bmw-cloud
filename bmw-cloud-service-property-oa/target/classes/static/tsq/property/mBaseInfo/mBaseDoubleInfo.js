/**
 * 抄表基础管理初始化
 */
var MBaseInfo = {
    id: "MBaseInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
MBaseInfo.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
        {title: '小区名', field: 'nName', visible: true, align: 'center', valign: 'middle'},
        {title: '楼宇名', field: 'bName', visible: true, align: 'center', valign: 'middle'},
        {title: '房间号', field: 'rRoomnum', visible: true, align: 'center', valign: 'middle'},
        {title: '设置类别', field: 'mType', visible: true, align: 'center', valign: 'middle' ,
        	formatter : function(value, row, index) {
        		var data = "";
        		if(value == "1"){
        			return "水表";
        		}else if(value == "2"){
        			return "电表";
        		}
            }		
        },
        {title: '倍率', field: 'mMultiple', visible: true, align: 'center', valign: 'middle'},
        {title: '耗损', field: 'mWastage', visible: true, align: 'center', valign: 'middle'},
        {title: '公摊', field: 'mCommonality', visible: true, align: 'center', valign: 'middle'},
        {title: '月份', field: 'tMonth', visible: true, align: 'center', valign: 'middle'},
        {title: '表数', field: 'tNum', visible: true, align: 'center', valign: 'middle'},
        {title: '用量', field: 'uAmount', visible: true, align: 'center', valign: 'middle'} ,
        {title: '表号', field: 'tableNumber', visible: true, align: 'center', valign: 'middle'},
        {title: '审核结果', field: 'isApproval', visible: true, align: 'center', valign: 'middle' ,
        	formatter : function(value, row, index) {
        		var data = "";
        		if(value == "1"){
        			return "已通过";
        		}else if(value == "2"){
        			return "已驳回";
        		}else{
        			return "未审核";
        		}
            }		
        }
//        {title: '操作', field: '', visible: true, align: 'center', valign: 'middle' ,
//        	formatter:function(value,row,index) {
//        	return [
//        	'<button class="btn btn-primary" id="cancel"  onClick="setDosage()"><i class="fa fa-eraser"></i>录入用量</button>'
//        	]}}
    ];
};

/**
 * 检查是否选中
 */
MBaseInfo.check = function () {
	var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        MBaseInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加抄表基础
 */
MBaseInfo.openAddMBaseInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加抄表基础',
        area: ['500px', '800px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/mBaseInfo/mBaseInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看抄表基础详情
 */
MBaseInfo.openMBaseInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '抄表基础详情',
            area: ['500px', '800px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/mBaseInfo/mBaseInfo_update/' + MBaseInfo.seItem.sId
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看抄表基础详情
 */
MBaseInfo.openMBaseInfoDetail2 = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '抄表基础详情',
            area: ['500px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/mBaseInfo/mBaseInfo_dosage/' + MBaseInfo.seItem.sId
        });
        this.layerIndex = index;
    }
};

function setDosage(){
	MBaseInfo.openMBaseInfoDetail2();
}

/**
 * 删除抄表基础
 */
MBaseInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/mBaseInfo/delete", function (data) {
            Feng.success("删除成功!");
            MBaseInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("mBaseInfoId",this.seItem.sId);
        ajax.start();
    }
};

/**
 * 打开查看抄表基础详情
 */
MBaseInfo.openMBaseInfoApproval = function () {
    if (this.check()) {
    	if(MBaseInfo.seItem.isApproval=="1"){
    		return  Feng.error("审核已通过");;
    	}
    	if(MBaseInfo.seItem.isApproval=="2"){
    		return  Feng.error("审核已驳回");;
    	}
        var index = layer.open({
            type: 2,
            title: '抄表基础详情',
            area: ['500px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/mBaseDoubleInfo/mBaseInfo_approval/' + MBaseInfo.seItem.sId+"/"+MBaseInfo.seItem.tMonth
        });
        this.layerIndex = index;
    }
};

/**
 * 查询抄表基础列表
 */
MBaseInfo.search = function () {
    var queryData = {};
    queryData['nName'] = $("#nName").val();
    queryData['bName'] = $("#bName").val();
    queryData['rRoomnum'] = $("#rRoomnum").val();
    MBaseInfo.table.refresh({query: queryData});
};

MBaseInfo.saveBills = function () {

	if (this.check()) {
		//获取选择的记录
		var selected = $('#' + this.id).bootstrapTable('getSelections');
		
		selected[0].isApproval
		
		var Ids = [];
		var isApproval = true;
		var isCreatBillIds=[];
		var index = 0;
		$(selected).each(function(){
			Ids.push(this.sId +"v" + (this.mRecordId==null?-1:this.mRecordId));
			if( "" == selected[0].isApproval){
				isApproval = false;
			}
			index++;
			if( 1 == this.isCreatBill){
				isCreatBillIds.push(index);
			}
		})
		
		//Ids.join(',');
		if(isCreatBillIds.join(',').length>0){
			Feng.info("选中的第"+isCreatBillIds.join(',')+"条记录中已生成账单！");
		}else{
			if(isApproval){
				
				var ajax = new $ax(Feng.ctxPath + "/mBaseInfo/saveBills/"+Ids.join(','), function (data) {
		            Feng.success(data.message);
		            MBaseInfo.table.refresh();
		        }, function (data) {
		            Feng.error("生成账单'失败!" + data.responseJSON.message + "!");
		        });
		        ajax.start();
			}else{
				 Feng.info("选中的记录中存在未审核的记录！");
			}
		}
	}
}

$(function () {
    var defaultColunms = MBaseInfo.initColumn();
    var table = new BSTable(MBaseInfo.id, "/mBaseDoubleInfo/list", defaultColunms);
    table.setPaginationType("client");
    MBaseInfo.table = table.init();
});
