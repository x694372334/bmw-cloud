/**
 * 初始化报事报修详情对话框
 */
var RepairsFormDetailDlg = {
   
};
//点击放大图片
RepairsFormDetailDlg.showImg=function(url){
   /* var img=new Image();
    img.src=url;
    var useImage='<img src=\''+url+'\''+"/>";
    img.onload=function(){
    	parent.layer.open({  
    	    type: 1,  
    	    shade: false,  
    	    title: false, //不显示标题  
    	    area: [img.width+'px',img.height+'px'],  
    	    content: useImage, //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响  
    	}); 
    }  */
	
	
}

RepairsFormDetailDlg.loadImages=function(repairsFormId){
	 var index = parent.layer.open({
	        type: 2,
	        title: '报修图片',
	        area: ['800px', '600px'], //宽高
	        fix: false, //不固定
	        maxmin: true,
	        content: Feng.ctxPath + '/repairsForm/repairsForm_loadImages/'+repairsFormId
	    });	
}

RepairsFormDetailDlg.close = function() {
    parent.layer.close(window.parent.RepairsForm.layerIndex);
}

RepairsFormDetailDlg.refuse = function (){
	  var index = parent.layer.open({
          type: 2,
          title: '驳回',
          area: ['800px', '420px'], //宽高
          fix: false, //不固定
          maxmin: true,
          content: Feng.ctxPath + '/repairsForm/repairsForm_Refuse/' + $("#formId").val()
      });
	  RepairsFormDetailDlg.close();
      window.parent.RepairsForm.layerIndex = index;
}
RepairsFormDetailDlg.complete = function (){
	  var index = parent.layer.open({
        type: 2,
        title: '完成订单',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/repairsForm/repairsForm_complete/' + $("#formId").val()
    });
	  RepairsFormDetailDlg.close();
    window.parent.RepairsForm.layerIndex = index;
}
RepairsFormDetailDlg.receive = function (){
	  var index = parent.layer.open({
        type: 2,
        title: '接受订单',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/repairsForm/repairsForm_receive/' + $("#formId").val()
    });
	  RepairsFormDetailDlg.close();
    window.parent.RepairsForm.layerIndex = index;
}
RepairsFormDetailDlg.door2see = function (){
	  var index = parent.layer.open({
        type: 2,
        title: '上门查看',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/repairsForm/repairsForm_door2see/' + $("#formId").val()
    });
	  RepairsFormDetailDlg.close();
    window.parent.RepairsForm.layerIndex = index;
}