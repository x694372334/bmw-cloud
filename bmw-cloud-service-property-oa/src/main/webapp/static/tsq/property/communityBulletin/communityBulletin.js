/**
 * 管理初始化
 */
var CommunityBulletin = {
    id: "CommunityBulletinTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CommunityBulletin.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        	{title: '主键', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '公告标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '发布时间', field: 'pubTime', visible: true, align: 'center', valign: 'middle'},
            {title: '发起人', field: 'initiatorName', visible: true, align: 'center', valign: 'middle'},
            {title: '发起时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '审核人', field: 'verifierName', visible: true, align: 'center', valign: 'middle'},
            {title: '审核时间', field: 'verifyTime', visible: true, align: 'center', valign: 'middle'},
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
CommunityBulletin.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CommunityBulletin.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
CommunityBulletin.openAddCommunityBulletin = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/communityBulletin/communityBulletin_add'
    });
    this.layerIndex = index;
};

/**
 * 打开编辑
 */
CommunityBulletin.openCommunityBulletinUpdate = function () {
    if (this.check()) {
    	if(this.seItem.status!=1&&this.seItem.status!=4){
			Feng.error("只有草稿和驳回的公告可以编辑！");
			return;
		}
        var index = layer.open({
            type: 2,
            title: '编辑',
            area: ['800px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/communityBulletin/communityBulletin_update/' + CommunityBulletin.seItem.id
        });
        this.layerIndex = index;
    }
};
/**
 * 打开查看详情
 */
CommunityBulletin.openCommunityBulletinDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/communityBulletin/communityBulletin_detail/' + CommunityBulletin.seItem.id
        });
        this.layerIndex = index;
    }
};
CommunityBulletin.sub=function(){
	if (this.check()) {
		if(this.seItem.status!=1&&this.seItem.status!=4){
			Feng.error("只有草稿状态和被驳回状态可以提交审核！");
			return;
		}
		 var ajax = new $ax(Feng.ctxPath + "/communityBulletin/sub", function (data) {
			 if(data.code==200){
				 Feng.success(data.message);
			 }else{
				 Feng.error(data.message);
			 }
	         CommunityBulletin.table.refresh();
	        }, function (data) {
	            Feng.error("提交审核失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("communityBulletinId",this.seItem.id);
	        ajax.start();
    }
}
CommunityBulletin.pub=function(){
	if (this.check()) {
		if(this.seItem.status!=3){
			Feng.error("只有审核通过的公告可以发布！");
			return;
		}
		 var ajax = new $ax(Feng.ctxPath + "/communityBulletin/pub", function (data) {
			 if(data.code==200){
				 Feng.success(data.message);
			 }else{
				 Feng.error(data.message);
			 }
	         CommunityBulletin.table.refresh();
	        }, function (data) {
	            Feng.error("发布失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("communityBulletinId",this.seItem.id);
	        ajax.start();
    }
}
CommunityBulletin.callback=function(){
	if (this.check()) {
		if(this.seItem.status!=2){
			Feng.error("只有审核中的公告可以撤回！");
			return;
		}
		 var ajax = new $ax(Feng.ctxPath + "/communityBulletin/callback", function (data) {
			 if(data.code==200){
				 Feng.success(data.message);
			 }else{
				 Feng.error(data.message);
			 }
	            CommunityBulletin.table.refresh();
	        }, function (data) {
	            Feng.error("撤回失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("communityBulletinId",this.seItem.id);
	        ajax.start();
    }
}

/**
 * 删除
 */
CommunityBulletin.delete = function (){
    if (this.check()) {
    	if(this.seItem.status!=1&&this.seItem.status!=4){
			Feng.error("只有草稿和驳回的公告可以删除！");
			return;
		}
        var ajax = new $ax(Feng.ctxPath + "/communityBulletin/delete", function (data) {
        	 if(data.code==200){
				 Feng.success(data.message);
			 }else{
				 Feng.error(data.message);
			 }
            CommunityBulletin.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("communityBulletinId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询列表
 */
CommunityBulletin.search = function () {
    var queryData = {
    	"title":$("#title").val(),
    	"initiatorId":$("#initiatorId").val(),
    	"verifierId":$("#verifierId").val()
    };
    CommunityBulletin.table.refresh({query: queryData});
};


$(function () {
    var defaultColunms = CommunityBulletin.initColumn();
    var table = new BSTable(CommunityBulletin.id, "/communityBulletin/list", defaultColunms);
    table.setPaginationType("server");
    table.init()
    CommunityBulletin.table = table;
});
