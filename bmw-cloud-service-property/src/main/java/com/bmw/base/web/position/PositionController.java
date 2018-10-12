package com.bmw.base.web.position;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bmw.base.module.position.domain.Position;
import com.bmw.base.module.position.service.PositionService;
import com.bmw.common.model.Result;
import com.bmw.common.utils.ResultUtils;

@RestController
@RequestMapping("base/position")
public class PositionController {

	@Autowired
	PositionService positionService;

	/**
	 * 获取部门列表
	 * @author jmy
	 * 2018年05月24日
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="positionList/{jsonParams}",method=RequestMethod.GET)
	public Result positionList(@PathVariable("jsonParams") String jsonParams) {
		Position position = new Position();
		try{
			 String params = new String (Base64Utils.decode(jsonParams.getBytes("UTF-8")));
			 JSONObject object=JSONObject.parseObject(params);
			 if(object.get("name")!=null) {
				 position.setName(object.get("name").toString());
	            }
		}catch (Exception e) {
			// TODO Auto-generated catch block
		}
		List<Position> categorys = positionService.findLike(position);
		return ResultUtils.getMethodData(categorys);
	}
	
	/**
	 * 部门详情
	 * @author jmy
	 * 2018年05月24日
	 */
	@RequestMapping(value="Detail/{positionId}",method=RequestMethod.POST)
	public Result detail(@PathVariable("positionId") String positionId){
		Position categorys = new Position();
		try{
			 String params = new String (Base64Utils.decode(positionId.getBytes("UTF-8")));
			 JSONObject object=JSONObject.parseObject(params);
			 categorys = positionService.findById(Integer.parseInt(object.get("positionId").toString()));
		}catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return ResultUtils.postMethodData(categorys);
	}
	
	/**
	 * 新增部门信息
	 * @author jmy
	 * 2018年05月24日
	 */
	@RequestMapping(value = "add",method=RequestMethod.POST)
    public Object add(@RequestParam("params") String params) {
		 try {
	            params = new String (Base64Utils.decode(params.getBytes("UTF-8")));
	            JSONObject object=JSONObject.parseObject(params);
	            Position position = (Position) JSONToObj(object.toJSONString(),Position.class);
	            position.setIsDelete(Integer.parseInt("0"));
	            positionService.add(position);
	        } catch (UnsupportedEncodingException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return ResultUtils.postMethodData("添加菜单成功");
    }
	
	/**
	 * 修改部门信息
	 * @author jmy
	 * 2018年05月24日
	 */
	@RequestMapping(value="update/{jsonParams}",method=RequestMethod.POST)
	public Result update(@PathVariable("jsonParams") String jsonParams){
		try {
            String params = new String (Base64Utils.decode(jsonParams.getBytes("UTF-8")));
            JSONObject object=JSONObject.parseObject(params);
            Position position = (Position) JSONToObj(object.toJSONString(),Position.class);
            positionService.update(position);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		 return ResultUtils.postMethodData("变更菜单成功");
	}
	
	/**
	 * 删除医院信息
	 * @author jmy
	 * 2018年05月24日
	 */
	@RequestMapping(value="del/{jsonParams}",method=RequestMethod.POST)
	public Result del(@PathVariable("jsonParams") String jsonParams){
		try{
			 String params = new String (Base64Utils.decode(jsonParams.getBytes("UTF-8")));
			 JSONObject object=JSONObject.parseObject(params);
			 positionService.delete(Integer.parseInt(object.get("positionId").toString()));
		}catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return ResultUtils.postMethodData("删除菜单成功");
	}
	
    @RequestMapping(value="positionTreeList/{jsonParams}",method=RequestMethod.GET)
    public Result getPositionTreeList() {
        return ResultUtils.getMethodData(positionService.positionTreeList());
    }
    
    @RequestMapping(value="positionCountCode/{jsonParams}",method=RequestMethod.GET)
    public Result getPositionCountCode(@PathVariable("jsonParams") String jsonParams) {
    	String data =positionService.positionCountCode(jsonParams);
    	return ResultUtils.getMethodData("{\"data\":\""+data+"\"}");
    }
    
    @RequestMapping(value="positionCountIsCode/{jsonParams}",method=RequestMethod.GET)
    public Result positionCountIsCode(@PathVariable("jsonParams") String jsonParams) {
    	String data =positionService.positionCountIsCode(jsonParams);
    	return ResultUtils.getMethodData("{\"data\":\""+data+"\"}");
    }
    
    @RequestMapping(value="positionCodeSelect/{jsonParams}",method=RequestMethod.GET)
    public Result getPositionCodeSelect(@PathVariable("jsonParams") String jsonParams) {
    	String data =positionService.positionCodeSelect(jsonParams);
    	return ResultUtils.getMethodData("{\"data\":\""+data+"\"}");
    }
    
	@RequestMapping(value="positionDeleteUpdate/{code}",method=RequestMethod.POST)
	public Result positionDeleteUpdate(@PathVariable("code") String code){
		Position categorys = positionService.positionDeleteUpdate(code);
		return ResultUtils.postMethodData(categorys);
	}
	
	
	 /**
		 * JSON转Java实体
		 * @author jmy
		 * 2018年04月19日    
		 * */
		 public static<T> Object JSONToObj(String jsonStr,Class<T> obj) {
		        T t = null;
		        try {
		            ObjectMapper objectMapper = new ObjectMapper();
		            t = objectMapper.readValue(jsonStr,
		                    obj);
		        } catch (Exception e) {
		            e.printStackTrace(); 
		        }
		        return t;
		    }
	
	
}
