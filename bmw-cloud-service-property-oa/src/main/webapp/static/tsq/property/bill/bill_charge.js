/**
 * 账单明细管理初始化
 */
var Bill = {
    id: "BillTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Bill.initColumn = function () {
    return [
        {field: 'selectItem', radio: false},
            {title: '主键id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            /*{title: '关联费用标准主键id', field: 'standardId', visible: true, align: 'center', valign: 'middle'},
            {title: '关联费用标准主键名称', field: 'standardName', visible: true, align: 'center', valign: 'middle'},
            {title: '账单编号', field: 'billNo', visible: true, align: 'center', valign: 'middle'},*/
            {title: '小区', field: 'neighborhoodsName', visible: true, align: 'center', valign: 'middle'},
            {title: '业主ID', field: 'ownerId', visible: false, align: 'center', valign: 'middle'},
            {title: '住户', field: 'ownerName', visible: true, align: 'center', valign: 'middle'},
            {title: '房号/车位号', field: 'objectNo', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号', field: 'ownerPhone', visible: true, align: 'center', valign: 'middle'},
            {title: '费用开始时间', field: 'costStartTime', visible: true, align: 'center', valign: 'middle'},
            {title: '费用结束时间', field: 'costEndTime', visible: true, align: 'center', valign: 'middle'},
            {title: '应收合计', field: 'shouldTotal', visible: true, align: 'center', valign: 'middle'},
            {title: '滞纳金', field: 'overdueFine', visible: true, align: 'center', valign: 'middle'},
           /* {title: '催缴次数', field: 'askCount', visible: true, align: 'center', valign: 'middle'},
            {title: '申请优惠状态（待审批、审批通过、已拒绝）', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '优惠比例', field: 'discountRate', visible: true, align: 'center', valign: 'middle'},*/
            {title: '优惠金额', field: 'discountAmount', visible: true, align: 'center', valign: 'middle'},
            {title: '实收合计', field: 'paidTotal', visible: true, align: 'center', valign: 'middle'},
            {title: '是否已缴', field: 'isFee', visible: true, align: 'center', valign: 'middle'}
           /* {title: '缴费方式', field: 'payMode', visible: true, align: 'center', valign: 'middle'},
            {title: '支付方式：1、现金，2、支付宝，3、微信', field: 'payWay', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'},
            {title: '退款金额（多次退款累加和）', field: 'refundAmount', visible: true, align: 'center', valign: 'middle'}*/
    ];
};



$(function () {
	var tbody=window.document.getElementById("tbody-result"); 
    $.ajax({  
        type: "get",  
        dataType: "json",  
        url: Feng.ctxPath + '/bill/list/'+$("#ids").val(),
        data: {  
        	ids : $("#ids").val()
        },  
        success: function (msg) {  
        	 var str = "";  
        	 var paidTotals = 0.00;
                for (i in msg) {  
                	$("#ownerName").val(msg[i].ownerName);
                	$("#ownerPhone").val(msg[i].ownerPhone);
                	$("#neighborhoodsName").val(msg[i].neighborhoodsName);
                	$("#objectNo").val(msg[i].objectNo);
                    str += "<tr>" +  
                    "<td align='center' valign='middle'>" + msg[i].billNo + "</td>" +  
                    "<td align='center' valign='middle'>" + msg[i].standardName + "</td>" +  
                    "<td align='center' valign='middle'>" + msg[i].costStartTime.substring(0,11)  + "</td>" +  
                    "<td align='center' valign='middle'>" + msg[i].costEndTime.substring(0,11) + "</td>" +  
                    "<td align='center' valign='middle'>" + msg[i].shouldTotal + "</td>" +  
                    "<td align='center' valign='middle'>" + msg[i].discountAmount + "</td>" +  
                    "<td align='center' valign='middle'>" + msg[i].overdueFine + "</td>" +  
                    "<td align='center' valign='middle'>" + msg[i].paidTotal + "</td>" +  
                    "</tr>";  
                    //paidTotals = paidTotals+msg[i].paidTotal;
                    //paidTotals = numAdd((paidTotals + msg[i].paidTotal).toFixed(2);
                    paidTotals = accAdd(paidTotals,msg[i].paidTotal);
                }  
                tbody.innerHTML = str;  
                document.getElementById('paidTotals').innerHTML=paidTotals;
        },  
        error: function () {  
        	Feng.info("查询失败!");
        }  
    });  
});
    

    
Bill.commit = function(){
	if(""==$("#payWay").val()){
		Feng.info("请选择支付方式");
		return;
	}
	$.ajax({  
        type: "get",  
        dataType: "json",  
        url: Feng.ctxPath + '/bill/changeCommit?ids='+$("#ids").val()+"&payWay="+$("#payWay").val()+"&remark="+$("#remark").val(),
        success: function (msg) { 
        	if("200"==msg.code){
        		Feng.success("交易成功!");
                window.parent.Bill.table.refresh();
                parent.layer.close(window.parent.Bill.layerIndex);
        	}else{
        		Feng.error("交易失败!");
        	}
        },  
        error: function () {  
        	Feng.error("交易失败!");
        }  
    }); 
	
}
    

//小数相加
function accAdd(arg1, arg2) {  
    var r1, r2, m, c;  
    try {  
        r1 = arg1.toString().split(".")[1].length;  
    }  
    catch (e) {  
        r1 = 0;  
    }  
    try {  
        r2 = arg2.toString().split(".")[1].length;  
    }  
    catch (e) {  
        r2 = 0;  
    }  
    c = Math.abs(r1 - r2);  
    m = Math.pow(10, Math.max(r1, r2));  
    if (c > 0) {  
        var cm = Math.pow(10, c);  
        if (r1 > r2) {  
            arg1 = Number(arg1.toString().replace(".", ""));  
            arg2 = Number(arg2.toString().replace(".", "")) * cm;  
        } else {  
            arg1 = Number(arg1.toString().replace(".", "")) * cm;  
            arg2 = Number(arg2.toString().replace(".", ""));  
        }  
    } else {  
        arg1 = Number(arg1.toString().replace(".", ""));  
        arg2 = Number(arg2.toString().replace(".", ""));  
    }  
    return (arg1 + arg2) / m;  
}  

