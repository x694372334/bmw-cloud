package com.bmw.base.module.account.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bmw.base.module.account.domain.Account;
import com.bmw.base.module.account.mapper.AccountMapper;
import com.bmw.common.log.ServiceOperationLog;
import com.bmw.common.utils.SpringContextUtils;


/**
 * 个人账户表Service类
 * 
 * @author zhangt
 * @date 2018-07-06 11:34:35
 */
@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;



    /**
     * 获取当前对象实例
     * <p><b>
     * 为了记录操作日志</br>
     * 在调用内部 add()、delete()、update()方法时候请这样书写 "this.currentProxy().add()"
     * </b></p>
     * 
     */
    public AccountService currentProxy() {
        return SpringContextUtils.getBean(AccountService.class);
    }

    /**
     * 新增实体类到数据库
     * 
     */
    @Transactional
    @ServiceOperationLog(type="insert",iden="sys_account",idenName="个人账户表",idBeanName="id")
    public int add(Account record) {
        return accountMapper.add(record);
    }

    /**
     * 根据主键删除该记录
     * 
     */
    @Transactional
    @ServiceOperationLog(type="delete",iden="sys_account",idenName="个人账户表",idBeanName="id")
    public int delete(Integer id) {
        return accountMapper.delete(id);
    }

    /**
     * 根据主键修改该记录
     * 
     */
    @Transactional
    @ServiceOperationLog(type="update",iden="sys_account",idenName="个人账户表",idBeanName="id")
    public int update(Account record) {
        return accountMapper.update(record);
    }

    /**
     * 根据主键查询该记录
     * 
     */
    public Account findById(Integer id) {
        return accountMapper.findById(id);
    }

    /**
     * 根据查询条件进行模糊查询
     * 
     */
    public List<Account> findLike(Account condition) {
        return accountMapper.findLike(condition);
    }

    /**
     * 根据查询条件进行匹配查询
     * 
     */
    public List<Account> findList(Account condition) {
        return accountMapper.findList(condition);
    }
}