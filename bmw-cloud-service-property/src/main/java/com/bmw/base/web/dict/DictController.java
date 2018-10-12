package com.bmw.base.web.dict;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.bmw.base.module.dict.domain.Dict;
import com.bmw.base.module.dict.service.DictService;
import com.bmw.common.exception.RestPreconditionFailedException;
import com.bmw.common.model.Result;
import com.bmw.common.utils.ResultUtils;
@RestController
@RequestMapping("base/dict")
public class DictController {
	
	@Autowired
	private DictService dictService;
	
	
	/**
	 * 获取所有字典列表
	 * @author lyl
	 * 2018年04月17日
	 */
	@RequestMapping(value="getDict/{dictName}",method=RequestMethod.GET)
	public Result getFirstLevelClassify(@PathVariable("dictName") String dictName){
		Dict dict = new Dict();
		dict.setPid(0);
		dict.setName(dictName);
		List<Dict> categorys = dictService.findLike(dict);
		return ResultUtils.getMethodData(categorys);
	}
	
	
	/**
     * 字典子信息
     * @author lyl
	 * 2018年04月17日
     */
    @RequestMapping(value = "getDictByPid/{dictId}",method=RequestMethod.GET)
    public Object getDictByPid(@PathVariable("dictId") Integer dictId) {
    	if(dictId==null) {
    		throw new RestPreconditionFailedException("请输入正确的父id");
    	}
    	Dict dict = new Dict();
		dict.setPid(dictId);
    	List<Dict> categorys = dictService.findList(dict);
        return ResultUtils.getMethodData(categorys);
    }
    
    /**
     * 字典信息
     * @author lyl
	 * 2018年04月17日
     */
    @RequestMapping(value = "detail/{dictId}",method=RequestMethod.GET)
    public Object detail(@PathVariable("dictId") Integer dictId) {
    	if(dictId==null) {
    		throw new RestPreconditionFailedException("请输入正确的父id");
    	}
    	Dict categorys = dictService.findById(dictId);
        return ResultUtils.getMethodData(categorys);
    }
    
    
    /**
     * 字典添加
     * @author lyl
	 * 2018年04月17日
     */
    @RequestMapping(value = "add",method=RequestMethod.POST)
    public Object add(@RequestBody JSONObject jsonParams) {
    	/*String params=new String (Base64Utils.decode(jsonParams.getBytes("UTF-8")));
		JSONObject object=JSONObject.parseObject(params);*/
		dictService.addDict(jsonParams);
        return ResultUtils.postMethodData("添加字典成功");
    }
    
    
    /**
     * 字典修改-逻辑为先删除原有子字典及自身，后插入
     * @author lyl
	 * 2018年04月17日
     */
    @RequestMapping(value = "edit",method=RequestMethod.POST)
    public Object edit(@RequestBody JSONObject jsonParams) {
    	/*String params=new String (Base64Utils.decode(jsonParams.getBytes("UTF-8")));
		JSONObject object=JSONObject.parseObject(params);*/
		dictService.editDict(jsonParams);
        return ResultUtils.postMethodData("修改字典成功");
    }
    
    
    
    /**
     * 字典 删除-逻辑为先删除原有子字典后删除自身
     * @author lyl
	 * 2018年04月17日
     */
    @RequestMapping(value = "del/{jsonParams}",method=RequestMethod.DELETE)
    public Object del(@PathVariable("jsonParams") String jsonParams) {
    	try {
			String params=new String (Base64Utils.decode(jsonParams.getBytes("UTF-8")));
			JSONObject object=JSONObject.parseObject(params);
			dictService.delteDict(object.getInteger("dictId"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ResultUtils.postMethodData("添加字典成功");	
    }
    
    
    /**
     * 方法名称 : getDictByPid
     * 作者 : wangliqing  
     * 方法描述 : 根基code获取字典列表
     * 创建时间 : 2018年6月22日 下午2:50:08    
     * 参数 : @param dictId
     * 参数 : @return  
     * 返回类型 : Object    
     * @throws
    */
   @RequestMapping(value = "getDictByCode/{code}",method=RequestMethod.GET)
   public Object getDictByCode(@PathVariable("code") String code) {
   	if(code==null) {
   		throw new RestPreconditionFailedException("code为空");
   	}
   	Dict dict = new Dict();
		dict.setName(code);
   	List<Dict> categorys = dictService.getDictByCode(dict);
       return ResultUtils.getMethodData(categorys);
   }
	
}
