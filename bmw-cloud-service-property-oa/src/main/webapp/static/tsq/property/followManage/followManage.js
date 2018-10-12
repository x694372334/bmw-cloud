/**
 * 管理初始化
 */
var FollowManage = {
    id: "FollowManageTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
FollowManage.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '业主姓名', field: 'ownerName', visible: true, align: 'center', valign: 'middle'},
            {title: '联系电话', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '联系时间', field: 'contactTime', visible: true, align: 'center', valign: 'middle'},
            {title: '接待人', field: 'receiverName', visible: true, align: 'center', valign: 'middle'},
            {title: '联系事项', field: 'contactMatters', visible: true, align: 'center', valign: 'middle'},
            {title: '后期备忘', field: 'remark', visible: true, align: 'center', valign: 'middle'},
            {  
                field: 'operate',  
                title: '操作',  
                width: '250px',
                align: 'center',
                formatter: operateFormatter  
            }
    ];
};

function operateFormatter(value, row, index) {
    return [  
        '<button id="btn_detail" type="button" onclick="FollowManage.openFollowManageDetail('+row.id+')" class="btn button-margin btn-primary fa">详情</button>', 
        '<button id="btn_detail" type="button" onclick="FollowManage.openFollowManageEdit('+row.id+')" class="btn button-margin btn-primary fa fa-edit">修改</button>',
        '<button id="btn_detail" type="button" onclick="FollowManage.delete('+row.id+')" class="btn btn-primary button-margin fa fa-remove">删除</button>'
    ].join('');  
}

/**
 * 检查是否选中
 */
FollowManage.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        FollowManage.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
FollowManage.openAddFollowManage = function () {
    var index = layer.open({
        type: 2,
        title: '添加跟进记录',
        area: ['900px', '700px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/followManage/followManage_add'
    });
    this.layerIndex = index;
};

/**
 * 修改
 */
FollowManage.openFollowManageEdit = function (id) {
        var index = layer.open({
            type: 2,
            title: '修改跟进记录',
            area: ['900px', '700px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/followManage/followManage_update/' + id
        });
        this.layerIndex = index;
};

/**
 * 详情
 */
FollowManage.openFollowManageDetail = function (id) {
        var index = layer.open({
            type: 2,
            title: '跟进记录详情',
            area: ['900px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/followManage/followManage_detail/' + id
        });
        this.layerIndex = index;
};


/**
 * 删除
 */
FollowManage.delete = function (id) {
    	var operation = function(){
            var ajax = new $ax(Feng.ctxPath + "/followManage/delete", function (data) {
                Feng.success("删除成功!");
                FollowManage.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("followManageId",id);
            ajax.start();
    	};
    	Feng.confirm("是否删除跟进记录 " + "?", operation);
};

/**
 * 查询列表
 */
FollowManage.search = function () {
    var queryData = {};
    queryData['ownerName'] = $("#ownerName").val();
    queryData['phone'] = $("#phone").val();
    queryData['receiverName'] = $("#receiverName").val();
    FollowManage.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = FollowManage.initColumn();
    var table = new BSTable(FollowManage.id, "/followManage/list", defaultColunms);
    table.setPaginationType("server");
    FollowManage.table = table.init();

});
