package com.bmw.base.module.dict.service;

import com.bmw.common.exception.RestPreconditionFailedException;
import com.bmw.common.log.ServiceOperationLog;
import com.bmw.common.utils.SpringContextUtils;
import com.bmw.common.utils.oa.MutiStrFactory;
import com.alibaba.fastjson.JSONObject;
import com.bmw.base.module.dict.domain.Dict;
import com.bmw.base.module.dict.mapper.DictMapper;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 字典表Service类
 * 
 * @author lyl
 * @date 2018-04-16 04:33:03
 */
@Service
public class DictService {

    @Autowired
    private DictMapper dictMapper;



    /**
     * 获取当前对象实例
     * <p><b>
     * 为了记录操作日志</br>
     * 在调用内部 add()、delete()、update()方法时候请这样书写 "this.currentProxy().add()"
     * </b></p>
     * 
     */
    public DictService currentProxy() {
        return SpringContextUtils.getBean(DictService.class);
    }

    /**
     * 新增实体类到数据库
     * 
     */
    @Transactional
    @ServiceOperationLog(type="insert",iden="sys_dict",idenName="登录记录",idBeanName="id")
    public int add(Dict record) {
        return dictMapper.add(record);
    }

    /**
     * 根据主键删除该记录
     * 
     */
    @Transactional
    @ServiceOperationLog(type="delete",iden="sys_dict",idenName="登录记录",idBeanName="id")
    public int delete(Integer id) {
        return dictMapper.delete(id);
    }

    /**
     * 根据主键修改该记录
     * 
     */
    @Transactional
    @ServiceOperationLog(type="update",iden="sys_dict",idenName="登录记录",idBeanName="id")
    public int update(Dict record) {
        return dictMapper.update(record);
    }

    /**
     * 根据主键查询该记录
     * 
     */
    public Dict findById(Integer id) {
        return dictMapper.findById(id);
    }

    /**
     * 根据查询条件进行模糊查询
     * 
     */
    public List<Dict> findLike(Dict condition) {
        return dictMapper.findLike(condition);
    }

    /**
     * 根据查询条件进行匹配查询
     * 
     */
    public List<Dict> findList(Dict condition) {
        return dictMapper.findList(condition);
    }
    
    
    
    /**
     * 添加字典-oa调用
     * @author lyl
     * @param jsonParam
     */
    public void addDict(JSONObject jsonParam) {
    	Dict dict =new Dict();
    	dict.setName(jsonParam.getString("dictName"));
        //判断有没有该字典
        List<Dict> dicts = dictMapper.findList(dict);
        if (dicts != null && dicts.size() > 0) {
            throw new RestPreconditionFailedException("字典名称已存在！");
        }

        //解析dictValues
        List<Map<String, String>> items = MutiStrFactory.parseKeyValue(jsonParam.getString("dictValues"));

        //添加字典-并将自增逐渐放入模型中
        dict.setNum(0);
        dict.setPid(0);
        dictMapper.add(dict);

        //添加字典条目
        for (Map<String, String> item : items) {
            String num = item.get("KEY");
            String name = item.get("VALUE");
            Dict itemDict = new Dict();
            itemDict.setPid(dict.getId());
            itemDict.setName(name);
            try {
                itemDict.setNum(Integer.valueOf(num));
            } catch (NumberFormatException e) {
                throw new NumberFormatException("请联系管理员");
            }
            this.currentProxy().add(itemDict);
        }
    }
    
    public void editDict(JSONObject jsonParam) {
    	int dictId = jsonParam.getInteger("dictId");
        //删除之前的字典
        delteDict(dictId);

        //重新添加新的字典
        addDict(jsonParam);
    }

    public void delteDict(Integer dictId) {
    	//删除这个字典的子词典
    	Dict condition =new Dict();
    	condition.setPid(dictId);
    	List<Dict> list=findList(condition);
    	for(Dict dict:list) {
    		this.currentProxy().delete(dict.getId());
    	}
        
        //删除这个词典
    	this.currentProxy().delete(dictId);
    }
    
    
	public List<Dict> getDictByCode(Dict dict) {
		
		return dictMapper.getDictByCode(dict);
	}
    
}