package com.stylefeng.guns.modular.system.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.IService;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.modular.system.model.Notice;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 通知表 服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-02-22
 */
public interface INoticeService {

    /**
     * 获取通知列表
     */
	JSONArray list(String condition);
	
	
	
	/**  
	* <p>Description: 根据id获取通知详情</p>  
	* @author lyl  
	* @date 2018年4月26日  
	*/  
	public Notice findById(Integer noticeId);
	
	
    /**  
    * <p>Description: 添加通知</p>  
    * @author lyl  
    * @date 2018年4月26日  
    */  
    public void add(Notice notice) ;
    
    
    
    /**  
    * <p>Description: 更新通知</p>  
    * @author lyl  
    * @date 2018年4月26日  
    */  
    public void update(Notice notice) ;
    
    
    /**  
    * <p>Description: 删除通知</p>  
    * @author lyl  
    * @date 2018年4月26日  
    */  
    public void del(Integer noticeId);
}
