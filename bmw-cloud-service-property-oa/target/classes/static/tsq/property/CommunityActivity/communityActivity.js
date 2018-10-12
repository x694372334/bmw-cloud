/**
 * 小区活动管理初始化
 */
var CommunityActivity = {
    id: "CommunityActivityTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CommunityActivity.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '活动名称', field: 'activityName', visible: true, align: 'center', valign: 'middle'},           
            {title: '发布时间', field: 'pubTime', visible: true, align: 'center', valign: 'middle'},
            {title: '发起人', field: 'initiatorName', visible: true, align: 'center', valign: 'middle'},
            {title: '审核人', field: 'verifierName', visible: true, align: 'center', valign: 'middle'},
            {title: '审核时间', field: 'verifyTime', visible: true, align: 'center', valign: 'middle'},
            {title: '活动类型', field: 'activityTypeName', visible: true, align: 'center', valign: 'middle'},
            {title: '已阅比率', field: 'readCountRate', visible: true, align: 'center', valign: 'middle',
            	formatter : function(value, row, index) {
            		return value*100+"%";
            	}
            },
            {title: '报名人数', field: 'signUpCount', visible: true, align: 'center', valign: 'middle',
            	formatter : function(value, row, index) {
            		return value+"人";
            	}
            },
            {title: '投票结果', field: '', visible: true, align: 'center', valign: 'middle',
            	formatter : function(value, row, index) {
            		return "同意"+row.voteStatusYesCount+"人/"+"反对"+row.voteStatusNoCount+"人";
            	}
            },
            {title: '状态', field: 'statusName', visible: true, align: 'center', valign: 'middle',
            	formatter : function(value, row, index) {
            		return "<span style='color:red'>"+value+"</span>";
				}
            }
    ];
};

/**
 * 检查是否选中
 */
CommunityActivity.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CommunityActivity.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加小区活动
 */
CommunityActivity.openAddCommunityActivity = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/communityActivity/communityActivity_add'
    });
    this.layerIndex = index;
};

/**
 * 打开编辑
 */
CommunityActivity.openCommunityActivityUpdate = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑',
            area: ['800px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/communityActivity/communityActivity_update/' + CommunityActivity.seItem.id
        });
        this.layerIndex = index;
    }
};
/**
 * 打开查看小区活动详情
 */
CommunityActivity.openCommunityActivityDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '小区活动详情',
            area: ['800px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/communityActivity/communityActivity_detail/' + CommunityActivity.seItem.id
        });
        this.layerIndex = index;
    }
};
//提交
CommunityActivity.sub=function(){
	if (this.check()) {
		if(this.seItem.status!=1&&this.seItem.status!=4){
			Feng.error("只有草稿状态和被驳回状态可以提交审核！");
			return;
		}
		 var ajax = new $ax(Feng.ctxPath + "/communityActivity/sub", function (data) {
	            Feng.success("提交审核成功!");
	            CommunityActivity.table.refresh();
	        }, function (data) {
	            Feng.error("提交审核失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("communityActivityId",this.seItem.id);
	        ajax.start();
    }
}
//发布
CommunityActivity.pub=function(){
	if (this.check()) {
		if(this.seItem.status!=3){
			Feng.error("只有审核通过的活动可以发布！");
			return;
		}
		 var ajax = new $ax(Feng.ctxPath + "/communityActivity/pub", function (data) {
	            Feng.success("发布成功!");
	            CommunityActivity.table.refresh();
	        }, function (data) {
	            Feng.error("发布失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("communityActivityId",this.seItem.id);
	        ajax.start();
    }
}
//撤回
CommunityActivity.callback=function(){
	if (this.check()) {
		if(this.seItem.status!=2){
			Feng.error("只有审核中的活动可以撤回！");
			return;
		}
		 var ajax = new $ax(Feng.ctxPath + "/communityActivity/callback", function (data) {
	            Feng.success("撤回成功!");
	            CommunityActivity.table.refresh();
	        }, function (data) {
	            Feng.error("撤回失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("communityActivityId",this.seItem.id);
	        ajax.start();
    }
}
/**
 * 删除小区活动
 */
CommunityActivity.delete = function () {
    if (this.check()) {
    	if(this.seItem.status!=1&&this.seItem.status!=4){
			Feng.error("只有草稿和驳回的活动可以删除！");
			return;
		}
        var ajax = new $ax(Feng.ctxPath + "/communityActivity/delete", function (data) {
            Feng.success("删除成功!");
            CommunityActivity.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("communityActivityId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询小区活动列表
 */
CommunityActivity.search = function () {
    var queryData = {
    		"activityName":$("#activityName").val(),
        	"initiatorId":$("#initiatorId").val(),
        	"verifierId":$("#verifierId").val()
    };
    CommunityActivity.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CommunityActivity.initColumn();
    var table = new BSTable(CommunityActivity.id, "/communityActivity/list", defaultColunms);
    table.setPaginationType("server");
    CommunityActivity.table = table.init();
});
