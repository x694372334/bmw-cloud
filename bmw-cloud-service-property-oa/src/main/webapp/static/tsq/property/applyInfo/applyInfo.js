/**
 * 审批申请单管理初始化
 */
var ApplyInfo = {
    id: "ApplyInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ApplyInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        	{title: '申请名称', field: 'aName', visible: true, align: 'center', valign: 'middle' , weight:'5%'},
	        {title: '申请类型', field: 'aType', visible: true, align: 'center', valign: 'middle' , 
	        	formatter : function(value, row, index) {
		        	if(value == "1"){
						return "请假申请";
					}else if(value == "2"){
						return "采购申请";
					}else if(value == "3"){
						return "退费申请";
					}else if(value == "4"){
						return "优惠申请";
					}else if(value == "5"){
						return "其他申请";
					}
	        	}	
	        },
	        {title: '申请内容', field: 'aDetails', visible: true, align: 'center', valign: 'middle'},
	        {title: '申请人', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '发起时间', field: 'aTime', visible: true, align: 'center', valign: 'middle' },
            {title: '审批状态', field: 'eaResult', visible: true, align: 'center', valign: 'middle' ,
            	formatter : function(value, row, index) {
		        	if(value == "1"){
						return "通过";
					}else if(value == "0"){
						return "未提交";
					}else if(value == "2"){
						return "驳回";
					}else{
						return "待审批";
					}
	        	}		
            },
            {title: '审批人', field: 'eaUserName', visible: true, align: 'center', valign: 'middle'},
            {title: '审批意见', field: 'eaOpinion', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ApplyInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ApplyInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加审批申请单
 */
ApplyInfo.openAddApplyInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加审批申请单',
        area: ['800px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/applyInfo/applyInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看审批申请单详情
 */
ApplyInfo.openApplyInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '审批申请单详情',
            area: ['500px', '400px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/applyInfo/applyInfo_update/' + ApplyInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看审批申请单详情
 */
ApplyInfo.openApplyInfoDetail2 = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '审批申请单详情',
            area: ['500px', '400px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/applyInfo/applyInfo_detail/' + ApplyInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看审批申请单详情
 */
ApplyInfo.openApplyInfoDetail3 = function () {
    if (this.check()) {
    	if(ApplyInfo.seItem.eaResult!="1"){
	        var index = layer.open({
	            type: 2,
	            title: '审批申请单详情',
	            area: ['500px', '400px'], //宽高
	            fix: false, //不固定
	            maxmin: true,
	            content: Feng.ctxPath + '/applyInfo/applyInfo_approval/' + ApplyInfo.seItem.id
	        });
	        this.layerIndex = index;
    	}else{
    		 Feng.success("已经通过！请不要重复审核");
    	}
    }
};

/**
 * 删除审批申请单
 */
ApplyInfo.delete = function () {
    if (this.check()) {
    	if(ApplyInfo.seItem.eaResult==3){
    		return  Feng.error("待审批不能删除");
    	}
        var ajax = new $ax(Feng.ctxPath + "/applyInfo/delete", function (data) {
            Feng.success("删除成功!");
            ApplyInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("applyInfoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询审批申请单列表
 */
ApplyInfo.search = function () {
    var queryData = {};
    queryData['aType'] = $("#aType").val();
    queryData['eaResult'] = $("#eaResult").val();
    ApplyInfo.table.refresh({query: queryData});
};

/**
 * 打开查看审批申请单详情
 */
ApplyInfo.openApplyInfoDetail2 = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '审批申请单详情',
            area: ['800px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/applyInfo/applyInfo_detail/' + ApplyInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

function detailFlowabled(){
	ApplyInfo.openApplyInfoDetail2();
}

$(function () {
    var defaultColunms = ApplyInfo.initColumn();
    var table = new BSTable(ApplyInfo.id, "/applyInfo/list", defaultColunms);
    table.setPaginationType("client");
    ApplyInfo.table = table.init();
});
