/**
 *  huanxingroup管理初始化
 */
var HuanxinGroup = {
    id: "HuanxinGroupTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
HuanxinGroup.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '环信组ID', field: 'groupId', visible: true, align: 'center', valign: 'middle'},
            {title: '环信组名称', field: 'groupName', visible: true, align: 'center', valign: 'middle'},
           /* {title: '群主ID', field: 'groupOwnerId', visible: true, align: 'center', valign: 'middle'},*/
            {title: '群主名称', field: 'groupOwnerName', visible: true, align: 'center', valign: 'middle'},
            /*{title: '所属小区ID', field: 'communityId', visible: true, align: 'center', valign: 'middle'},*/
            {title: '所属小区名称', field: 'communityName', visible: true, align: 'center', valign: 'middle'},
           /* {title: '创建人ID', field: 'createManId', visible: true, align: 'center', valign: 'middle'},*/
            {title: '创建人名称', field: 'createManName', visible: true, align: 'center', valign: 'middle'},
            {title: '群类别', field: 'groupType', visible: true, align: 'center', valign: 'middle',
            	formatter : function (value, row, index) {
                    if (value == 1) {
                        return '管家群';
                    }
                    if (value == 2) {
                        return '业主群';
                    }
                    if (value == 3) {
                        return '其他';
                    }
                }   }
           /* {title: '物业ID', field: 'eId', visible: true, align: 'center', valign: 'middle'},
            {title: '所属企业ID', field: 'parentEId', visible: true, align: 'center', valign: 'middle'}*/
    ];
};

/**
 * 检查是否选中
 */
HuanxinGroup.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        HuanxinGroup.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加 huanxingroup
 */
HuanxinGroup.openAddHuanxinGroup = function () {
    var index = layer.open({
        type: 2,
        title: '建群',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/huanxinGroup/huanxinGroup_add'
    });
    this.layerIndex = index;
};

/**
 * 分配群成员
 */
HuanxinGroup.distributionUser = function () {
	if (this.check()) {
    var index = layer.open({
        type: 2,
        title: '分配群成员',
        area: ['400px', '420px'], // 宽高
        fix: false, // 不固定
        maxmin: true,
        content: Feng.ctxPath + '/huanxinGroup/distributionUser/'+HuanxinGroup.seItem.groupId+"/"+HuanxinGroup.seItem.groupType
    });
    this.layerIndex = index;
	}
};

/**
 * 打开查看 huanxingroup详情
 */
HuanxinGroup.openHuanxinGroupDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: ' huanxingroup详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/huanxinGroup/huanxinGroup_update/' + HuanxinGroup.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 打开修改群组信息
 */
HuanxinGroup.detailgroup = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: ' 修改群组信息',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/huanxinGroup/detailgroup/' + HuanxinGroup.seItem.id 
        });
        this.layerIndex = index;
    }
};

/**
 * 删除 huanxingroup
 */
HuanxinGroup.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/huanxinGroup/delete", function (data) {
            Feng.success("删除成功!");
            HuanxinGroup.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("huanxinGroupId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 删除 huanxingroup
 */
HuanxinGroup.deletegroup = function () {
    if (this.check()) {
    	var operation = function(){
        var ajax = new $ax(Feng.ctxPath + "/huanxinGroup/deleteByGroupId", function (data) {
            Feng.success("删除成功!");
            HuanxinGroup.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("groupId",HuanxinGroup.seItem.groupId);
        ajax.set("groupType",HuanxinGroup.seItem.groupType);
        ajax.start();
    	};
    	  Feng.confirm("是否删除",operation);
    };
};


/**
 * 查询 huanxingroup列表
 */
HuanxinGroup.search = function () {
    var queryData = {};
    queryData['groupName'] = $("#groupName").val();
    HuanxinGroup.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = HuanxinGroup.initColumn();
    var table = new BSTable(HuanxinGroup.id, "/huanxinGroup/list", defaultColunms);
    table.setPaginationType("client");
    HuanxinGroup.table = table.init();
});
