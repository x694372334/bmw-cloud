package com.stylefeng.guns.modular.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.annotion.BussinessLog;
import com.stylefeng.guns.core.common.annotion.Permission;
import com.stylefeng.guns.core.common.constant.Const;
import com.stylefeng.guns.core.common.constant.dictmap.DictMap;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.system.model.Dict;
import com.stylefeng.guns.modular.system.service.impl.DictServiceImpl;
import com.stylefeng.guns.modular.system.warpper.DictWarpper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 字典控制器
 *
 * @author lyl
 * @Date 2018年4月18日 
 */
@Controller
@RequestMapping("/dict")
public class DictController extends BaseController {
	
	@Autowired
	private DictServiceImpl dictService;

    private String PREFIX = "/system/dict/";


    /**
     * 跳转到字典管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "dict.html";
    }

    /**
     * 跳转到添加字典
     */
    @RequestMapping("/dict_add")
    public String deptAdd() {
        return PREFIX + "dict_add.html";
    }
    
    /**
     * 获取所有字典列表
     */
    @RequestMapping(value = "/list")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object list(String condition) {
		return super.warpObject(new DictWarpper(dictService.findList(condition)));
    }
    
    /**
     * 字典详情
     */
    @RequestMapping(value = "/detail/{dictId}")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object detail(@PathVariable("dictId") Integer dictId) {
        return dictService.getdetail(dictId);
    }
    
    /**
     * 跳转到修改字典
     */
    @Permission(Const.ADMIN_NAME)
    @RequestMapping("/dict_edit/{dictId}")
    public String deptUpdate(@PathVariable Integer dictId, Model model) {
    	Dict dict=dictService.getdetail(dictId);
        model.addAttribute("dict", dict);
        model.addAttribute("subDicts", dictService.getDictByPid(dictId));
        LogObjectHolder.me().set(dict);
        return PREFIX + "dict_edit.html";
    }
    
    /**
      * 方法名称 : getDic
      * 作者 : wangliqing  
      * 方法描述 : 根据code获取字典
      * 创建时间 : 2018年6月22日 下午2:17:32    
      * 参数 : @param code
      * 返回类型 : List<Dict>    
      * @throws
     */
  
    @RequestMapping("/getDic")
    @ResponseBody
    public List<Dict> getDic(@RequestParam String code) {
    	JSONArray jsonArray = ConstantFactory.me().findDictByCode(code);
    	String jsonStr = JSONObject.toJSONString(jsonArray);
    	List<Dict>  dicList = JSONObject.parseArray(jsonStr,Dict.class);
        return dicList;
    }
    

    /**
     * 新增字典
     *
     * @param dictValues 格式例如   "1:启用;2:禁用;3:冻结"
     */
    @BussinessLog(value = "添加字典记录", key = "dictName,dictValues", dict = DictMap.class)
    @RequestMapping(value = "/add")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object add(String dictName, String dictValues) {
        dictService.add(dictName, dictValues);
        return SUCCESS_TIP;
    }

    

    

    /**
     * 修改字典
     */
    @BussinessLog(value = "修改字典", key = "dictName,dictValues", dict = DictMap.class)
    @RequestMapping(value = "/update")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object update(Integer dictId, String dictName, String dictValues) {
    	dictService.update(dictId, dictName, dictValues);
        return SUCCESS_TIP;
    }

    /**
     * 删除字典记录
     */
    @BussinessLog(value = "删除字典记录", key = "dictId", dict = DictMap.class)
    @RequestMapping(value = "/delete")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object delete(@RequestParam Integer dictId) {
    	dictService.del(dictId);
        //缓存被删除的名称
        LogObjectHolder.me().set(ConstantFactory.me().getDictName(dictId));
        return SUCCESS_TIP;
    }

}
