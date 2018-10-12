

$("#sf").on('click',function(){
	var href = "/bill";
	$("#ifram").attr("src",href);
})

$("#sqjl").on('click',function(){
	var href = "/bill/bill_discounts"
	$("#ifram").attr("src",href);
})

$("#zdcx").on('click',function(){
	var href = "/bill/bill_billListAll"
	$("#ifram").attr("src",href);
})


$(function () {
	var href = "/bill";
	$("#ifram").attr("src",href);
});
