/**
 * 小区信息管理初始化
 */
var NeighborhoodInfo = {
    id: "NeighborhoodInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
NeighborhoodInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '小区名称', field: 'nName', visible: true, align: 'center', valign: 'middle' , width: '10%'},
        {title: '小区地址', field: 'nAddress', visible: true, align: 'center', valign: 'middle', width: '20%'},
        {title: '客服电话', field: 'serviceTel', visible: true, align: 'center', valign: 'middle'},
        {title: '物业公司', field: 'enterpriseName', visible: true, align: 'center', valign: 'middle'},
        {title: '物业负责人', field: 'pInCharge', visible: true, align: 'center', valign: 'middle'},
        {title: '物业负责人电话', field: 'pICTel', visible: true, align: 'center', valign: 'middle'}
        /*
        {title: '占地面积', field: 'nCoveredArea', visible: true, align: 'center', valign: 'middle',
        	formatter : function(value, row, index) {
            	return value+"平米";
            }		
        },
        {title: '建筑面积', field: 'nArchitectureArea', visible: true, align: 'center', valign: 'middle',
        	formatter : function(value, row, index) {
            	return value+"平米";
            }		
        },
        {title: '公共场所面积', field: 'nPublicArea', visible: true, align: 'center', valign: 'middle',
        	formatter : function(value, row, index) {
            	return value+"平米";
            }		
        },
        {title: '车库面积', field: 'cArea', visible: true, align: 'center', valign: 'middle',
        	formatter : function(value, row, index) {
            	return value+"平米";
            }	 	
        },
        {title: '备注', field: 'remarks', visible: true, align: 'center', valign: 'middle'}
*/
    ];
};

/**
 * 检查是否选中
 */
NeighborhoodInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        NeighborhoodInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加小区信息
 */
NeighborhoodInfo.openAddNeighborhoodInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加小区信息',
        area: ['1200px', '720px'], //宽高
        fix: false, //不固定
        maxmin: true,
        resize:false,
        content: Feng.ctxPath + '/neighborhood/neighborhoodInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看小区信息详情
 */
NeighborhoodInfo.openNeighborhoodInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '小区信息详情',
            area: ['1200px', '720px'], //宽高
            fix: false, //不固定
            maxmin: true,
            resize:false,
            content: Feng.ctxPath + '/neighborhood/neighborhoodInfo_update/' + NeighborhoodInfo.seItem.nId
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看小区信息详情
 */
NeighborhoodInfo.openNeighborhoodInfoDetail2 = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '小区信息详情',
            area: ['1200px', '720px'], //宽高
            fix: false, //不固定
            maxmin: true,
            resize:false,
            content: Feng.ctxPath + '/neighborhood/neighborhoodInfo_detail/' + NeighborhoodInfo.seItem.nId
        });
        this.layerIndex = index;
    }
};



/**
 * 删除小区信息
 */
NeighborhoodInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/neighborhood/delete", function (data) {
        	if(data=="240"){
        		Feng.error("有楼宇信息未删除，不能删除小区");
        		return;
        	}
            Feng.success("删除成功!");
            NeighborhoodInfo.table.refresh();
        }, function (data) {
        		Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("neighborhoodInfoId",this.seItem.nId);
        ajax.start();
    }
};

/**
 * 查询小区信息列表
 */
NeighborhoodInfo.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    NeighborhoodInfo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = NeighborhoodInfo.initColumn();
    var table = new BSTable(NeighborhoodInfo.id, "/neighborhood/list", defaultColunms);
    table.setPaginationType("client");
    NeighborhoodInfo.table = table.init();
});
