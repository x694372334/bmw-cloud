/**
 * enterprise管理初始化
 */
var EnterpriseInfo = {
    id: "EnterpriseInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
EnterpriseInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
           /* {title: '主键id', field: 'eId', visible: true, align: 'center', valign: 'middle'},*/
            {title: '企业名称', field: 'enterpriseName', visible: true, align: 'center', valign: 'middle'},
            {title: '企业简称', field: 'shortName', visible: true, align: 'center', valign: 'middle'},
            {title: '企业地址', field: 'enterpriseAddress', visible: true, align: 'center', valign: 'middle'},
            {title: '企业法人', field: 'enterpriseLegalPerson', visible: true, align: 'center', valign: 'middle'},
            {title: '企业电话', field: 'enterprisePhone', visible: true, align: 'center', valign: 'middle'},
      /*    {title: '工作范围', field: 'workScope', visible: true, align: 'center', valign: 'middle'},
            {title: '规章制度', field: 'enterpriseRules', visible: true, align: 'center', valign: 'middle'},
            {title: '审核状态', field: 'auditStatus', visible: true, align: 'center', valign: 'middle'},
            {title: '父企业id', field: 'parentId', visible: true, align: 'center', valign: 'middle'},
            {title: '营业执照', field: 'eLicense', visible: true, align: 'center', valign: 'middle'},
            {title: '企业官网', field: 'eWebsite', visible: true, align: 'center', valign: 'middle'},
            {title: '企业简介', field: 'eDescription', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人id', field: 'createManId', visible: true, align: 'center', valign: 'middle'},
            {title: '创建人', field: 'createMan', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '修改人id', field: 'editManId', visible: true, align: 'center', valign: 'middle'},
            {title: '修改人', field: 'editMan', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'editTime', visible: true, align: 'center', valign: 'middle'},
            {title: '是否删除', field: 'isDelete', visible: true, align: 'center', valign: 'middle'}*/
    ];
};

/**
 * 检查是否选中
 */
EnterpriseInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        EnterpriseInfo.seItem = selected[0];
        console.log(selected[0]);
        return true;
    }

};

/**
 * 点击添加enterprise
 */
EnterpriseInfo.openAddEnterpriseInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加企业信息',
        area: ['900px', '520px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/enterpriseInfo/enterpriseInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看enterprise详情
 */
EnterpriseInfo.openEnterpriseInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '企业信息详情',
            area: ['900px', '520px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/enterpriseInfo/enterpriseInfo_update/' + EnterpriseInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除enterprise
 */
EnterpriseInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/enterpriseInfo/delete", function (data) {
        	if(400 == data.code){
        		Feng.info(data.message);
        	}else{
            Feng.success("删除成功!");
            EnterpriseInfo.table.refresh();
        	}
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("enterpriseInfoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询enterprise列表
 */
EnterpriseInfo.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    EnterpriseInfo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = EnterpriseInfo.initColumn();
//    var table = new BSTable(EnterpriseInfo.id, "/enterpriseInfo/list", defaultColunms);
//    table.setPaginationType("client");
//    EnterpriseInfo.table = table.init();
    var table = new BSTreeTable(EnterpriseInfo.id, "/enterpriseInfo/list", defaultColunms);
    table.setExpandColumn(1);
    table.setIdField("eId");
    table.setCodeField("eId");
    table.setParentCodeField("parentId");
    table.setExpandAll(true);
    table.init();
    EnterpriseInfo.table = table;
});
