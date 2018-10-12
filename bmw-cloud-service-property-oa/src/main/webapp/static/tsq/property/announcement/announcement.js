/**
 * announcement管理初始化
 */
var Announcement = {
    id: "AnnouncementTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Announcement.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
            /*{title: '内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人ID', field: 'createId', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createMan', visible: true, align: 'center', valign: 'middle'},*/
            {title: '公告时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
           /* {title: '修改人id', field: 'editManId', visible: true, align: 'center', valign: 'middle'},
            {title: '修改人', field: 'editMan', visible: true, align: 'center', valign: 'middle'},*/
            {title: '状态', field: 'haveRead', visible: true, align: 'center', valign: 'middle',
            	formatter : function (value, row, index) {
                    if (value == "0") {
                    	return'<span style="color:red">未读</span>';
                    }
                    if (value == "1") {
                        return '已读';
                    }
                }  }
    ];
};

/**
 * 检查是否选中
 */
Announcement.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Announcement.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加announcement
 */
Announcement.openAddAnnouncement = function () {
    var index = layer.open({
        type: 2,
        title: '添加系统公告',
        area: ['600px', '350px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/announcement/announcement_add'
    });
    this.layerIndex = index;
};

/**
 * 查看
 */
Announcement.view = function () {
	 if (this.check()) {
    var index = layer.open({
        type: 2,
        title: '查看公告',
        area: ['600px', '350px'], // 宽高
        fix: false, // 不固定
        maxmin: true,
        content: Feng.ctxPath + '/announcement/announcement_view/' + Announcement.seItem.id
    });
    this.layerIndex = index;
    //Announcement.table.refresh();
	 }
};


/**
 * 打开查看announcement详情
 */
Announcement.openAnnouncementDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: 'announcement详情',
            area: ['600px', '350px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/announcement/announcement_update/' + Announcement.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除announcement
 */
Announcement.delete = function () {
    if (this.check()) {
    	var operation = function(){
        var ajax = new $ax(Feng.ctxPath + "/announcement/delete", function (data) {
            Feng.success("删除成功!");
            Announcement.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("announcementId",Announcement.seItem.id);
        ajax.start();
    	};
    	Feng.confirm("是否删除",operation);
    }
};

/**
 * 查询announcement列表
 */
Announcement.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Announcement.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Announcement.initColumn();
    var table = new BSTable(Announcement.id, "/announcement/list", defaultColunms);
    table.setPaginationType("client");
    Announcement.table = table.init();
});
