/**
 * 楼宇管理管理初始化
 */
var BuildingInfo = {
    id: "BuildingInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BuildingInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '小区名', field: 'nName', visible: true, align: 'center', valign: 'middle'},
        {title: '楼宇号/楼宇名', field: 'bName', visible: true, align: 'center', valign: 'middle'},
        {title: '单元数量', field: 'bUnitCount', visible: true, align: 'center', valign: 'middle', 
        	formatter : function(value, row, index) {
        		return value+"单元";
        	}		
        },
        {title: '楼宇层数', field: 'bFloors', visible: true, align: 'center', valign: 'middle', 
        	formatter : function(value, row, index) {
        		return value+"层";
        	}	
        },
        {title: '楼宇类型', field: 'bType', visible: true, align: 'center', valign: 'middle' , 
        	formatter : function(value, row, index) {
        		var data = "";
        		if(value == "1"){
        			return "多层";
        		}else if(value == "2"){
        			return "高层";
        		}
        	}
        },
        {title: '楼宇朝向', field: 'bOrientation', visible: true, align: 'center', valign: 'middle'},
        {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BuildingInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BuildingInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加楼宇管理
 */
BuildingInfo.openAddBuildingInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加楼宇管理',
        area: ['800px', '520px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/buildingInfo/buildingInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看楼宇管理详情
 */
BuildingInfo.openBuildingInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '楼宇管理详情',
            area: ['800px', '520px'], //宽高
            fix: false, //不固定
            maxmin: true,
            resize:false,
            content: Feng.ctxPath + '/buildingInfo/buildingInfo_update/' + BuildingInfo.seItem.bId
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看楼宇管理详情
 */
BuildingInfo.openBuildingInfoDetail2 = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '楼宇管理详情',
            area: ['800px', '520px'], //宽高
            fix: false, //不固定
            maxmin: true,
            resize:false,
            content: Feng.ctxPath + '/buildingInfo/buildingInfo_detail/' + BuildingInfo.seItem.bId
        });
        this.layerIndex = index;
    }
};

/**
 * 删除楼宇管理
 */
BuildingInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/buildingInfo/delete", function (data) {
        	if(data == "240"){
        		Feng.error("有房间号没有删除，不能删除楼宇");
        		return ;
        	}
            Feng.success("删除成功!");
            BuildingInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("buildingInfoId",this.seItem.bId);
        ajax.start();
    }
};

/**
 * 查询楼宇管理列表
 */
BuildingInfo.search = function () {
    var queryData = {};
    queryData['xqName'] = $("#xqName").val();
    queryData['lyName'] = $("#lyName").val();
    BuildingInfo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BuildingInfo.initColumn();
    var table = new BSTable(BuildingInfo.id, "/buildingInfo/list", defaultColunms);
    table.setPaginationType("client");
    BuildingInfo.table = table.init();
});
