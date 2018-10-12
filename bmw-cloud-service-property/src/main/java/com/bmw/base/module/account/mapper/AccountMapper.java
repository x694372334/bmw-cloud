package com.bmw.base.module.account.mapper;

import com.bmw.base.module.account.domain.Account;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;


/**
 * 个人账户表Mapper类
 * 
 * @author zhangt
 * @date 2018-07-06 11:34:35
 */
@Mapper
public interface AccountMapper {

    /**
     * 新增实体类到数据库
     * 
     */
    int add(Account record);

    /**
     * 根据主键删除该记录
     * 
     */
    int delete(Integer id);

    /**
     * 根据主键修改该记录
     * 
     */
    int update(Account record);

    /**
     * 根据主键查询该记录
     * 
     */
    Account findById(Integer id);

    /**
     * 根据查询条件进行模糊查询
     * 
     */
    List<Account> findLike(Account condition);

    /**
     * 根据查询条件进行匹配查询
     * 
     */
    List<Account> findList(Account condition);
}