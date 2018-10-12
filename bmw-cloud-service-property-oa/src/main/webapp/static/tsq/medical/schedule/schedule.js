var Schedule = {
	id: "ScheduleTable",// 表格id
	seItem: null,// 选中的条目
	table: null,
	layerIndex: -1,
	treeSelectNode: {}
};

Schedule.initColumn = function() {
	return [{
		field: 'selectItem',
		radio: true
	},
	{
		title: '科室名称',
		field: 'departmentName',
		visible: true,
		align: 'center',
		valign: 'middle'
	},
	{
		title: '医生姓名',
		field: 'doctorName',
		visible: true,
		align: 'center',
		valign: 'middle'
	},
	{
		title: '排班日期',
		field: 'scheduleDate',
		visible: true,
		align: 'center',
		valign: 'middle',
		formatter: function(value, row, index) {
			return Schedule.changeDateFormat(value);
		}
	},
	{
		title: '预约人数',
		field: 'appointmentNum',
		visible: true,
		align: 'center',
		valign: 'middle'
	},
	{
		title: '是否启用',
		field: 'isOpen',
		visible: true,
		align: 'center',
		valign: 'middle',
		formatter: function(value, row, index) {
			if (value === 1) {
				return '是';
			}
			if (value === 0) {
				return '否';
			}
		}
	},
	{
		title: '显示方式', 
		field: 'approach',
		visible: true, 
		align: 'center', 
		valign: 'middle',
		formatter: function(value, row, index) {
			if (value === "1") {
				return '按小时显示';
			}
			if (value === "0") {
				return '按区间显示';
			}
		}
	}];
};

Schedule.check = function() {
	var selected = $('#' + this.id).bootstrapTable('getSelections');
	if (selected.length == 0) {
		Feng.info("请先选中排班管理表格中的某一记录！");
		return false;
	} else {
		Schedule.seItem = selected[0];
		return true;
	}
};
Schedule.openAddSchedule = function() {
	var infos = [];
	var treeNode = Schedule.treeSelectNode;
	if (JSON.stringify(treeNode) != "{}") {
		var ext_attr = treeNode.ext_attr;
		infos = ext_attr.split(",");
	}
	var medicalDepartmentCode = infos[1] ;
	var medicalDoctorCode = infos[2];
	if(!medicalDepartmentCode || !medicalDoctorCode){
		Feng.info("请先选择科室和医生！");
		return false;
	}else{
		var index = layer.open({
			type: 2,
			title: '添加排班',
			area: ['800px', '420px'],// 宽高
			fix: false,// 不固定
			maxmin: true,
			content: Feng.ctxPath + '/schedule/schedule_add/' + medicalDepartmentCode + "/" + medicalDoctorCode
		});
		this.layerIndex = index;
	}
};
Schedule.openScheduleDetail = function() {
	if (this.check()) {
		var index = layer.open({
			type: 2,
			title: '排班详情',
			area: ['800px', '420px'],// 宽高
			fix: false,// 不固定
			maxmin: true,
			content: Feng.ctxPath + '/schedule/schedule_update/' + Schedule.seItem.id
		});
		this.layerIndex = index;
	}
};
Schedule.delete = function() {
	if (this.check()) {
		var ajax = new $ax(Feng.ctxPath + "/schedule/delete",
		function(data) {
			Feng.success("删除成功!");
			Schedule.table.refresh();
		},
		function(data) {
			Feng.error("删除失败!" + data.responseJSON.message + "!");
		});
		ajax.set("scheduleId", this.seItem.id);
		ajax.start();
	}
};
Schedule.search = function() {
	var queryData = {};
    queryData['doctorName'] = $("#doctorSearch").val();
    queryData['scheduleDate'] = $("#scheduleDateSearch").val();
    Schedule.table.queryParams = {};
	Schedule.table.refresh({
		query: queryData
	});
};

//---------------------------------------------------------------------
/**
 * 检查是否选中树节点
 */
Schedule.treeCheck = function() {
	if (JSON.stringify(Schedule.treeSelectNode) == "{}") {
		Feng.info("请先选中左侧树列表中的一条医生记录！");
		return false;
	} else {
		return true;
	}
};
/**
 * 左侧树列表点击事件
 */
Schedule.onClickDept = function(e, treeId, treeNode) {
	Schedule.treeSelectNode = treeNode;
	Schedule.deptCode = treeNode.pcode;
	var ext_attr = treeNode.ext_attr;
	var infos = ext_attr.split(",");
	var queryData = {};
	queryData['medicalDepartmentCode'] = infos[1];
	queryData['medicalDoctorCode'] = infos[2];
	Schedule.table.queryParams = {};
	Schedule.table.refresh({
		query: queryData
	});
	ScheduleDetail.table.queryParams = {};
	
	ScheduleDetail.table.refresh({
		query: queryData
	});
};

//----------------------------------------------------------------------
var ScheduleDetail = {
	id: "ScheduleDetailTable",	//表格id
	seItem: null,	//选中的条目
	table: null,
	layerIndex: -1
};
ScheduleDetail.initColumn = function() {
	return [{
		field: 'selectItem',
		radio: true
	},
	{
		title: '排班日期',
		field: 'scheduleDate',
		visible: false,
		align: 'center',
		valign: 'middle'
	},
	{
		title: '科室姓名',
		field: 'departmentName',
		visible: false,
		align: 'center',
		valign: 'middle'
	},
	{
		title: '医生姓名',
		field: 'doctorName',
		visible: false,
		align: 'center',
		valign: 'middle'
	},

	{
		title: '开始时间',
		field: 'scheduleStartTime',
		visible: true,
		align: 'center',
		valign: 'middle'
	},
	{
		title: '结束时间',
		field: 'scheduleEndTime',
		visible: true,
		align: 'center',
		valign: 'middle'
	},
	{
		title: '预约人数',
		field: 'appointmentNum',
		visible: true,
		align: 'center',
		valign: 'middle'
	},
	{
		title: '是否可以预约',
		field: 'isAppointment',
		visible: true,
		align: 'center',
		valign: 'middle',
		formatter: function(value, row, index) {
			if (value === 1) {
				return '是';
			}
			if (value === 0) {
				return '否';
			}
		}
	}];
};
/**
 * 检查是否选中
 */
ScheduleDetail.check = function() {
	var selected = $('#' + this.id).bootstrapTable('getSelections');
	if (selected.length == 0) {
		Feng.info("请先选中表格中的某一记录！");
		return false;
	} else {
		ScheduleDetail.seItem = selected[0];
		return true;
	}
};
/**
 * 点击添加明细
 */
ScheduleDetail.openAddScheduleDetail = function() {
	if (Schedule.check()) {
		var index = -1;
		var schedule = Schedule.seItem;
		if (schedule) {
			index = layer.open({
				type: 2,
				title: '添加明细',
				area: ['800px', '420px'],
				fix: false,
				maxmin: true,
				content: Feng.ctxPath + '/scheduleDetail/scheduleDetail_add/' +schedule.id
			});
		} 
		//如果要求必须选择一条的话,应该是走不到这里的
		else {
			index = layer.open({
				type: 2,
				title: '添加明细',
				area: ['800px', '420px'],
				fix: false,
				maxmin: true,
				content: Feng.ctxPath + '/scheduleDetail/scheduleDetail_add'
			});
		}
		this.layerIndex = index;
	}
};
/**
 * 打开查看medical详情
 */
ScheduleDetail.openScheduleDetailDetail = function() {
	if (this.check()) {
		var index = layer.open({
			type: 2,
			title: '明细详情',
			area: ['800px', '420px'],//宽高
			fix: false,//不固定
			maxmin: true,
			content: Feng.ctxPath + '/scheduleDetail/scheduleDetail_update/' + ScheduleDetail.seItem.id
		});
		this.layerIndex = index;
	}
};
/**
 * 删除medical
 */
ScheduleDetail.delete = function() {
	if (this.check()) {
		var ajax = new $ax(Feng.ctxPath + "/scheduleDetail/delete",
			function(data) {
				Feng.success("删除成功!");
				ScheduleDetail.table.refresh();
			},
			function(data) {
				Feng.error("删除失败!" + data.responseJSON.message + "!");
			}
		);
		ajax.set("scheduleDetailId", this.seItem.id);
		ajax.start();
	}
};
$(function() {
	var ScheduleTable = new BSTable(Schedule.id, "/schedule/list", Schedule.initColumn());
	ScheduleTable.setPaginationType("client");
	Schedule.table = ScheduleTable.init();
	// 初始化左侧树列表
	var ztree = new $ZTree("showTree", "/schedule/getTreeList");
	ztree.bindOnClick(Schedule.onClickDept);
	ztree.init();
	var ScheduleDetailTable = new BSTable(ScheduleDetail.id, "/scheduleDetail/list", ScheduleDetail.initColumn());
	ScheduleDetailTable.setPaginationType("client");
	ScheduleDetail.table = ScheduleDetailTable.init();
	$('#ScheduleTable').on('click-row.bs.table',
		function(e, row, element) {
			var queryData = {};
			queryData['medicalDepartmentCode'] = row.medicalDepartmentCode;
			queryData['medicalDoctorCode'] = row.medicalDoctorCode;
			queryData['scheduleDate'] = row.scheduleDate;
			ScheduleDetail.table.queryParams = {};
			ScheduleDetail.table.refresh({
				query: queryData
			});
		}
	);
});
//换日期格式(时间戳转换为datetime格式)
Schedule.changeDateFormat = function(cellval) {
	if (cellval != null) {
		var date = new Date(cellval);
		var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
		var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
		return date.getFullYear() + "-" + month + "-" + currentDate;
	}
}