package com.stylefeng.guns.core.shiro.factory;

import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.common.constant.state.ManagerStatus;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.core.util.Convert;
import com.stylefeng.guns.core.util.SpringContextHolder;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IMenuService;
import com.stylefeng.guns.modular.system.service.impl.MenuServiceImpl;
import com.stylefeng.guns.modular.system.service.impl.UserServiceImpl;

import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@DependsOn("springContextHolder")
@Transactional(readOnly = true)
public class ShiroFactroy implements IShiro {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private MenuServiceImpl menuService;

    public static IShiro me() {
        return SpringContextHolder.getBean(IShiro.class);
    }

    @Override
    public User user(String account) {

        User user = userService.getByAccount(account);

        // 账号不存在
        if (null == user) {
            throw new CredentialsException();
        }
        // 账号被冻结
        if (user.getStatus() != ManagerStatus.OK.getCode()) {
            throw new LockedAccountException();
        }
        return user;
    }

    @Override
    public ShiroUser shiroUser(User user) {
        ShiroUser shiroUser = new ShiroUser();
        //判断是否是超级管理员
        String[] roleids = user.getRoleid().split(",");
        Boolean flag = ArrayUtils.contains(roleids, "1");
        if(flag) {
        	shiroUser.setIsAdmin(true);
        }else {
        	shiroUser.setIsAdmin(false);
        }
        shiroUser.setId(user.getId());
        shiroUser.seteId(user.geteId()!=null?user.geteId():1);
        shiroUser.setParentEId(user.getParentEId());
        if(user.getParentEId()!=null) {
        	String parentEName = ConstantFactory.me().getEnterpriseInfoName(user.getParentEId());
        	shiroUser.setParentEName(parentEName);
        }
        shiroUser.setAccount(user.getAccount());
        shiroUser.setDeptId(user.getDeptid());
        shiroUser.setDeptName(ConstantFactory.me().getDeptName(user.getDeptid()));
        shiroUser.setName(user.getName());

        Integer[] roleArray = Convert.toIntArray(user.getRoleid());
        List<Integer> roleList = new ArrayList<Integer>();
        List<String> roleNameList = new ArrayList<String>();
        for (int roleId : roleArray) {
            roleList.add(roleId);
            roleNameList.add(ConstantFactory.me().getSingleRoleName(roleId));
        }
        shiroUser.setRoleList(roleList);
        shiroUser.setRoleNames(roleNameList);

        return shiroUser;
    }

    @Override
    public List<String> findPermissionsByRoleId(Integer roleId) {
        return menuService.getResUrlsByRoleId(roleId);
    }

    @Override
    public String findRoleNameByRoleId(Integer roleId) {
        return ConstantFactory.me().getSingleRoleTip(roleId);
    }

    @Override
    public SimpleAuthenticationInfo info(ShiroUser shiroUser, User user, String realmName) {
        String credentials = user.getPassword();

        // 密码加盐处理
        String source = user.getSalt();
        ByteSource credentialsSalt = new Md5Hash(source);
        return new SimpleAuthenticationInfo(shiroUser, credentials, credentialsSalt, realmName);
    }

}
