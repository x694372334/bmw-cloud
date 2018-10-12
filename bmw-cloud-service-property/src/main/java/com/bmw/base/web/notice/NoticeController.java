package com.bmw.base.web.notice;//package com.bmw.base.web.notice;
//
//import java.io.UnsupportedEncodingException;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.Base64Utils;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.alibaba.fastjson.JSONObject;
//import com.bmw.base.module.notice.domain.Notice;
//import com.bmw.base.module.notice.service.NoticeService;
//import com.bmw.common.model.Result;
//import com.bmw.common.utils.ResultUtils;
//
///**  
//* <p>Title: NoticeController</p>  
//* <p>Description: 系统通知服务</p>  
//* @author lyl  
//* @date 2018年4月26日  
//*/  
//@RestController
//@RequestMapping("base/notice")
//public class NoticeController {
//	
//	@Autowired
//	private NoticeService noticeService;
//	
//	
//	
//	/**    
//	* <p>Description: 获取查询列表</p>  
//	* @author lyl  
//	* @date 2018年4月26日  
//	*/  
//	@RequestMapping(value="list/{condition}",method=RequestMethod.GET)
//	public Result list(@PathVariable("condition") String condition){
//		List<Map<String, Object>> categorys = noticeService.list(condition);
//		return ResultUtils.getMethodData(categorys);
//	}
//	
//	
//	/**    
//	* <p>Description: 根据id获取通知详情</p>  
//	* @author lyl  
//	* @date 2018年4月26日  
//	*/  
//	@RequestMapping(value="deltial/{noticeId}",method=RequestMethod.GET)
//	public Result deltial(@PathVariable("noticeId") Integer noticeId){
//		Notice categorys = noticeService.findById(noticeId);
//		return ResultUtils.getMethodData(categorys);
//	}
//	
//	
//	/**    
//	* <p>Description: 添加通知</p>  
//	* @author lyl  
//	* @date 2018年4月26日  
//	*/  
//	@RequestMapping(value="add/{jsonParams}",method=RequestMethod.POST)
//	public Result add(@PathVariable("jsonParams") String jsonParams){
//		try {
//			String params = new String (Base64Utils.decode(jsonParams.getBytes("UTF-8")));
//			JSONObject object=JSONObject.parseObject(params);
//			noticeService.add(JSONObject.toJavaObject(object, Notice.class));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return ResultUtils.postMethodData("添加通知成功");
//	}
//	
//	
//	/**    
//	* <p>Description: 更新通知</p>  
//	* @author lyl  
//	* @date 2018年4月26日  
//	*/  
//	@RequestMapping(value="update/{jsonParams}",method=RequestMethod.POST)
//	public Result update(@PathVariable("jsonParams") String jsonParams){
//		try {
//			String params = new String (Base64Utils.decode(jsonParams.getBytes("UTF-8")));
//			JSONObject object=JSONObject.parseObject(params);
//			noticeService.update(JSONObject.toJavaObject(object, Notice.class));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return ResultUtils.postMethodData("更新通知成功");
//	}
//	
//	
//	/**    
//	* <p>Description: 删除通知</p>  
//	* @author lyl  
//	* @date 2018年4月26日  
//	*/  
//	@RequestMapping(value="del/{noticeId}",method=RequestMethod.DELETE)
//	public Result del(@PathVariable("noticeId") Integer noticeId){
//		noticeService.delete(noticeId);
//		return ResultUtils.deleteMethodData("删除通知成功");
//	}
//	
//}
