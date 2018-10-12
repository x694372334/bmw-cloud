$(function() {
	// 便利标题
	$.ajax({
		"type" : 'get',
		"url" : Feng.ctxPath + "/help/list",
		"dataType" : "json",
		"success" : function(data) {
			for (var i = 0; i < data.length; i++) {
				if (i == 0) {
					$("#ul").append("<li  class='active'><a onclick='clickLi(this)' id=" + data[i].id + " data-toggle='tab'>"+ data[i].title + "</a></li>");
					$("#contentArea").html(data[i].content);
				} else {
					$("#ul").append("<li ><a onclick='clickLi(this)' id=" +data[i].id +" data-toggle='tab'>"+ data[i].title + "</a></li>");
				}
			}
		}
	});
	
});



function clickLi(a) { 
	//提交信息
    var ajax = new $ax(Feng.ctxPath + "/help/detail/"+a.id, function(data){
    	
    	$("#contentArea").html(data.content);
    },function(data){
    });
    ajax.start();
}


function search() { 
	$.ajax({
		"type" : 'get',
		"url" : Feng.ctxPath + "/help/findByContent/"+$("#content").val(),
		"dataType" : "json",
		"success" : function(data) {
			$('li').remove();
			$("#contentArea").html("");
			for (var i = 0; i < data.length; i++) {
				if (i == 0) {
					$("#ul").append("<li  class='active'><a onclick='clickLi(this)' id=" + data[i].id + " data-toggle='tab'>"+ data[i].title + "</a></li>");
					$("#contentArea").html(data[i].content);
				} else {
					$("#ul").append("<li ><a onclick='clickLi(this)' id=" +data[i].id +" data-toggle='tab'>"+ data[i].title + "</a></li>");
				}
			}
		}
	});

}