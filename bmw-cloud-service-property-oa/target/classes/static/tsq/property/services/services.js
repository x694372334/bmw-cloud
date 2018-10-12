/**
 * services管理初始化
 */
var Services = {
    id: "ServicesTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Services.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '服务名称', field: 'serviceName', visible: true, align: 'center', valign: 'middle'},
            {title: '小区名称', field: 'nName', visible: true, align: 'center', valign: 'middle'},
            {title: '服务类别名称', field: 'serviceSortName', visible: true, align: 'center', valign: 'middle'},
            {title: '服务方式', field: 'serviceType', visible: true, align: 'center', valign: 'middle',
            	formatter : function (value, row, index) {
                    if (value === 1) {
                        return '上门服务';
                    }
                    if (value === 2) {
                        return '跑腿代办';
                    }
                }  },
            {title: '收费方式', field: 'paymentType', visible: true, align: 'center', valign: 'middle',
            	formatter : function (value, row, index) {
                    if (value === 1) {
                        return '一口价';
                    }
                    if (value === 2) {
                        return '订金';
                    }
                    if (value === 3) {
                        return '免费预约';
                    }
                } }
    ];
};

/**
 * 检查是否选中
 */
Services.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Services.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加services
 */
Services.openAddServices = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['1000px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/services/services_add'
    });
    this.layerIndex = index;
};

/**
 * 编辑页面
 */
Services.openServicesDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/services/services_update/' + Services.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 查看页面
 */
Services.openServicesView = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '查看',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/services/services_view/' + Services.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除services
 */
Services.delete = function () {
    if (this.check()) {
    	var operation = function(){
        var ajax = new $ax(Feng.ctxPath + "/services/delete", function (data) {
            Feng.success("删除成功!");
            Services.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("servicesId",Services.seItem.id);
        ajax.start();
    };
    Feng.confirm("是否删除",operation);
    }
};

/**
 * 查询services列表
 */
Services.search = function () {
    var queryData = {};
    queryData['serviceName'] = $("#serviceName").val();
    Services.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Services.initColumn();
    var table = new BSTable(Services.id, "/services/list", defaultColunms);
    table.setPaginationType("client");
    Services.table = table.init();
});
