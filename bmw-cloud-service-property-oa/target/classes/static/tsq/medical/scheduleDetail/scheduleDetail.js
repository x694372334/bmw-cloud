/**
 * 排班明细管理初始化
 */
var ScheduleDetail = {
    id: "ScheduleDetailTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ScheduleDetail.initColumn = function () {
	 var defaultColunms=[];
	 var obj =[
		 {field: 'selectItem', radio: true},
		 {title: '科室名称', field: 'departmentName', visible: true, align: 'center', valign: 'middle'},
	     {title: '医生姓名', field: 'doctorName', visible: true, align: 'center', valign: 'middle'}
	];
    return ScheduleDetail.allWeek(new Date(),defaultColunms.concat(obj));
};

ScheduleDetail.allWeek= function(dateTime,defaultColunms){
    var me=this;
    var time = dateTime.getTime();
    var dateStrList=[];
    for(var i=0;i<14;i++){
        var date=new Date(time + i * 24 * 3600 * 1000);
        var weekTime=me.formatDate(date);
        var splitArr=weekTime.split(" ");
        var dateStr=splitArr[0];
        var weekStr=splitArr[1];
        var columnObj={};
        
        columnObj.title=dateStr+"</br>"+weekStr;
        columnObj.field=dateStr;
        columnObj.visible=true;
        columnObj.align="center";
        columnObj.valign="middle";
        defaultColunms.push(columnObj);
    }
    return defaultColunms;
}
ScheduleDetail.formatDate = function(date){
      var year = date.getFullYear();
      var month = (date.getMonth()+1);
      month=month<=9?'0'+month:month;
      var day = date.getDate();
      day=day<=9?'0'+day:day;  
      var datetime = year+'-'+month+'-'+day;
      var week = ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'][date.getDay()];
      return datetime+' '+week;
}


/**
 * 检查是否选中
 */
ScheduleDetail.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ScheduleDetail.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加排班明细
 */
ScheduleDetail.openAddScheduleDetail = function () {
    var index = layer.open({
        type: 2,
        title: '添加排班明细',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/scheduleDetail/scheduleDetail_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看排班明细详情
 */
ScheduleDetail.openScheduleDetailDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '排班明细详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/scheduleDetail/scheduleDetail_update/' + ScheduleDetail.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除排班明细
 */
ScheduleDetail.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/scheduleDetail/delete", function (data) {
            Feng.success("删除成功!");
            ScheduleDetail.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("scheduleDetailId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询排班明细列表
 */
ScheduleDetail.search = function () {
    var queryData = {};
    queryData['departmentName'] = $("#departmentSearch").val();
    queryData['doctorName'] = $("#doctorSearch").val();
    ScheduleDetail.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ScheduleDetail.initColumn();
    var table = new BSTable(ScheduleDetail.id, "/scheduleDetail/allList", defaultColunms);
    table.setPaginationType("client");
    ScheduleDetail.table = table.init();
});
