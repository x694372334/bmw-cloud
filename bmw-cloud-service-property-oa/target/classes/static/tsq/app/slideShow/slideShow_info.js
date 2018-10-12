/**
 * 初始化轮播图片管理详情对话框
 */
var SlideShowInfoDlg = {
    slideShowInfoData : {}
};

/**
 * 清除数据
 */
SlideShowInfoDlg.clearData = function() {
    this.slideShowInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SlideShowInfoDlg.set = function(key, val) {
    this.slideShowInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SlideShowInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SlideShowInfoDlg.close = function() {
    parent.layer.close(window.parent.SlideShow.layerIndex);
}

/**
 * 收集数据
 */
SlideShowInfoDlg.collectData = function() {
    this
    .set('id')
    .set('title')
    .set('url')
    .set('sort')
    .set('isOpen')
    .set('isChange')
    .set('backGroundUrl');
    
    var formData=new FormData();
	formData.append("id",$("#id").val());
	formData.append("title",$("#title").val());
	formData.append("sort",$("#sort").val());
	formData.append("isOpen",$("#isOpen").val());
	formData.append("isChange",$("#isChange").val());
	formData.append("backGroundUrl",$("#backGroundUrl").val());
    var elements=document.getElementsByName('content');
    for(var i=0;i<elements.length;i++){
    	formData.append("file",elements[i].files[0]);
    }
    return formData;
}

/**
 * 提交添加
 */
SlideShowInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    var formData=SlideShowInfoDlg.collectData();
    $.ajax({
    	url: '/slideShow/add',
        type: 'POST',
        cache: false,
        data: formData,
        processData: false,
        contentType: false,
        success : function(data){
        	Feng.success("添加成功!");
    		window.parent.SlideShow.table.refresh();
    		SlideShowInfoDlg.close();
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
        	Feng.error(errorThrown);
        }
    })
}

/**
 * 提交修改
 */
SlideShowInfoDlg.editSubmit = function() {

	 this.clearData();
	    this.collectData();
	    var formData=SlideShowInfoDlg.collectData();
	    $.ajax({
	    	url: '/slideShow/update',
	        type: 'POST',
	        cache: false,
	        data: formData,
	        processData: false,
	        contentType: false,
	        success : function(data){
	        	Feng.success("添加成功!");
	    		window.parent.SlideShow.table.refresh();
	    		SlideShowInfoDlg.close();
	        },
	        error:function(XMLHttpRequest, textStatus, errorThrown){
	        	Feng.error(errorThrown);
	        }
	    })
	}

$(function() {
	$('#backGroundUrl').iColor({'x': 10, 'y': -50});
});
