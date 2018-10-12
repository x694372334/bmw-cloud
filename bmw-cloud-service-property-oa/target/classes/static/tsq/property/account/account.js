/**
 * account管理初始化
 */
var Account = {
    id: "AccountTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Account.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '姓名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '账户名', field: 'accountName', visible: true, align: 'center', valign: 'middle'},
            {title: '昵称', field: 'petName', visible: true, align: 'center', valign: 'middle'},
            {title: '密码', field: 'pwd', visible: true, align: 'center', valign: 'middle'},
            {title: '分公司', field: 'company', visible: true, align: 'center', valign: 'middle'},
            {title: '部门ID', field: 'deptId', visible: true, align: 'center', valign: 'middle'},
            {title: '职位ID', field: 'positionId', visible: true, align: 'center', valign: 'middle'},
            {title: '职位名称', field: 'positionName', visible: true, align: 'center', valign: 'middle'},
            {title: '电话', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '个性签名', field: 'signature', visible: true, align: 'center', valign: 'middle'},
            {title: '部门名称', field: 'deptName', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人ID', field: 'createId', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createMan', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '修改人id', field: 'editManId', visible: true, align: 'center', valign: 'middle'},
            {title: '修改人', field: 'editMan', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'editTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Account.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Account.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加account
 */
Account.openAddAccount = function () {
    var index = layer.open({
        type: 2,
        title: '添加account',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/account/account_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看account详情
 */
Account.openAccountDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: 'account详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/account/account_update/' + Account.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除account
 */
Account.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/account/delete", function (data) {
            Feng.success("删除成功!");
            Account.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("accountId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询account列表
 */
Account.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Account.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Account.initColumn();
    var table = new BSTable(Account.id, "/account/list", defaultColunms);
    table.setPaginationType("client");
    Account.table = table.init();
});
