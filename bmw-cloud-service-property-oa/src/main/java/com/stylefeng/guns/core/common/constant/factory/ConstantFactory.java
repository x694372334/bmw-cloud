package com.stylefeng.guns.core.common.constant.factory;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.common.constant.cache.Cache;
import com.stylefeng.guns.core.common.constant.cache.CacheKey;
import com.stylefeng.guns.core.common.constant.state.ManagerStatus;
import com.stylefeng.guns.core.common.constant.state.MenuStatus;
import com.stylefeng.guns.modular.system.model.*;
import com.stylefeng.guns.modular.system.service.impl.DeptServiceImpl;
import com.stylefeng.guns.modular.system.service.impl.DictServiceImpl;
import com.stylefeng.guns.modular.system.service.impl.MenuServiceImpl;
import com.stylefeng.guns.modular.system.service.impl.NoticeServiceImpl;
import com.stylefeng.guns.modular.system.service.impl.RoleServiceImpl;
import com.stylefeng.guns.modular.system.service.impl.UserServiceImpl;
import com.bmw.medical.service.impl.DepartmentServiceImpl;
import com.bmw.property.model.Department;
import com.bmw.property.model.EnterpriseInfo;
import com.bmw.property.model.Position;
import com.bmw.property.service.impl.EnterpriseInfoServiceImpl;
import com.bmw.property.service.impl.PositionServiceImpl;
import com.bmw.property.service.impl.PropertyDepartmentServiceImpl;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.support.StrKit;
import com.stylefeng.guns.core.util.Convert;
import com.stylefeng.guns.core.util.SpringContextHolder;
import com.stylefeng.guns.core.util.ToolUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 常量的生产工厂
 *
 * @author lyl
 * @date 2018年4月23日 
 */
@Component
@DependsOn("springContextHolder")
public class ConstantFactory implements IConstantFactory {

    private RoleServiceImpl roleService = SpringContextHolder.getBean(RoleServiceImpl.class);
    private DeptServiceImpl deptService = SpringContextHolder.getBean(DeptServiceImpl.class);
    private PropertyDepartmentServiceImpl departmentService = SpringContextHolder.getBean(PropertyDepartmentServiceImpl.class);
    private DictServiceImpl dictService = SpringContextHolder.getBean(DictServiceImpl.class);
    private UserServiceImpl userService = SpringContextHolder.getBean(UserServiceImpl.class);
    private PositionServiceImpl positionService = SpringContextHolder.getBean(PositionServiceImpl.class);
    private EnterpriseInfoServiceImpl enterpriseInfoService = SpringContextHolder.getBean(EnterpriseInfoServiceImpl.class);
//    private MenuMapper menuMapper = SpringContextHolder.getBean(MenuMapper.class);
    private MenuServiceImpl menuService = SpringContextHolder.getBean(MenuServiceImpl.class);
    private NoticeServiceImpl noticeService = SpringContextHolder.getBean(NoticeServiceImpl.class);

    public static IConstantFactory me() {
        return SpringContextHolder.getBean("constantFactory");
    }

    /**
     * 根据用户id获取用户名称
     *
     * @author stylefeng
     * @Date 2017/5/9 23:41
     */
    @Override
    public String getUserNameById(Integer userId) {
        User user = userService.detailUser(userId);
        if (user != null) {
            return user.getName();
        } else {
            return "--";
        }
    }
    
    /**
     * 根据多个用户id获取用户名
     *
     * @author stylefeng
     * @Date 2017/5/9 23:41
     */
    @Override
    public String getUserNameByIds(String ids) {
    	String userNames = "";
    	String[] uid = ids.split(",");
    	for(String id : uid) {
    	User user = new User();
    	if(!"".equals(id)&&null!=id) {
        user = userService.detailUser(Integer.parseInt(id));
    	}else {
    		user = null;
    	}
        if (user != null) {
        	userNames = userNames+user.getName()+",";
        } 
    	}
    	if(!"".equals(userNames)) {
    	userNames = userNames.substring(0, userNames.length()-1);
    	}
    	return userNames;
    }

    /**
     * 根据用户id获取用户账号
     *
     * @author stylefeng
     * @date 2017年5月16日21:55:371
     */
    @Override
    public String getUserAccountById(Integer userId) {
        User user = userService.detailUser(userId);
        if (user != null) {
            return user.getAccount();
        } else {
            return "--";
        }
    }

    /**
     * 通过角色ids获取角色名称
     */
    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.ROLES_NAME + "'+#roleIds")
    public String getRoleName(String roleIds) {
        Integer[] roles = Convert.toIntArray(roleIds);
        StringBuilder sb = new StringBuilder();
        for (int role : roles) {
            Role roleObj = roleService.getRoleDetail(role);
            if(roleObj.getDeptid()!=null) {
//            	Department department = departmentService.getdetail(roleObj.getDeptid());
//            	EnterpriseInfo enterpriseInfo = enterpriseInfoService.getdetail(department.geteId());
//            	roleObj.setName(roleObj.getName()+"("+enterpriseInfo.getShortName()+")");
            }
            if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
                sb.append(roleObj.getName()).append(",");
            }
        }
        return StrKit.removeSuffix(sb.toString(), ",");
    }

    /**
     * 通过角色id获取角色名称
     */
    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_NAME + "'+#roleId")
    public String getSingleRoleName(Integer roleId) {
        if (0 == roleId) {
            return "--";
        }
        Role roleObj = roleService.getRoleDetail(roleId);
        if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
            return roleObj.getName();
        }
        return "";
    }

    /**
     * 通过角色id获取角色英文名称
     */
    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_TIP + "'+#roleId")
    public String getSingleRoleTip(Integer roleId) {
        if (0 == roleId) {
            return "--";
        }
        Role roleObj = roleService.getRoleDetail(roleId);
        if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
            return roleObj.getTips();
        }
        return "";
    }

   /* *//**
     * 获取部门名称
     *//*
    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.DEPT_NAME + "'+#deptId")
    public String getDeptName(Integer deptId) {
        Dept dept = deptService.deptDetail(deptId);
        if (ToolUtil.isNotEmpty(dept) && ToolUtil.isNotEmpty(dept.getFullname())) {
            return dept.getFullname();
        }
        return "";
    }*/
    
    /**
     * 获取部门名称
     */   
    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.DEPT_NAME + "'+#deptId")
    public String getDeptName(Integer deptId) {
//        Department dept = departmentService.getdetail(deptId);
//        if (ToolUtil.isNotEmpty(dept) && ToolUtil.isNotEmpty(dept.getName())) {
//            return dept.getName();
//        }
        return "";
    }
    
    /**
     * 获取职位名称
     */   
    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.DEPT_NAME + "'+#positionId")
    public String getPositionName(Integer positionId) {
//    	Position position = positionService.getdetail(positionId);
//        if (ToolUtil.isNotEmpty(position) && ToolUtil.isNotEmpty(position.getName())) {
//            return position.getName();
//        }
        return "";
    }
    
    /**
     * 获取企业名称
     */   
    @Override
    public String getEnterpriseInfoName(Integer enterpriseInfoId) {
//    	EnterpriseInfo enterpriseInfo = enterpriseInfoService.getdetail(enterpriseInfoId);
//        if (ToolUtil.isNotEmpty(enterpriseInfo) && ToolUtil.isNotEmpty(enterpriseInfo.getEnterpriseName())) {
//            return enterpriseInfo.getEnterpriseName();
//        }
        return "";
    }
    
    /**
     * 获取用户所属企业ID（非物业）
     */   
    @Override
    public Integer getEIdByUserId(Integer userId) {
    	List<UserRoleVO> vo = userService.findUserRoleViewByUserId(userId);
    	Integer result = null;
    	for(UserRoleVO item : vo) {
    		if(null!=item.getParenteid()) {
    			result = item.getParenteid();
    			break;
    		}
    	}
    		return result;
    }

    /**
     * 获取菜单的名称们(多个)
     */
    @Override
    public String getMenuNames(String menuIds) {
        Integer[] menus = Convert.toIntArray(menuIds);
        StringBuilder sb = new StringBuilder();
        for (int menu : menus) {
            Menu menuObj = menuService.detailMenu(Long.valueOf(menu));
            if (ToolUtil.isNotEmpty(menuObj) && ToolUtil.isNotEmpty(menuObj.getName())) {
                sb.append(menuObj.getName()).append(",");
            }
        }
        return StrKit.removeSuffix(sb.toString(), ",");
    }

    /**
     * 获取菜单名称
     */
    @Override
    public String getMenuName(Long menuId) {
        if (ToolUtil.isEmpty(menuId)) {
            return "";
        } else {
            Menu menu = menuService.detailMenu(menuId);
            if (menu == null) {
                return "";
            } else {
                return menu.getName();
            }
        }
    }

    /**
     * 获取菜单名称通过编号
     */
    @Override
    public String getMenuNameByCode(String code) {
        if (ToolUtil.isEmpty(code)) {
            return "";
        } else {
            Menu param = new Menu();
            param.setCode(code);
            Menu menu = menuService.selectOne(param);
            if (menu == null) {
                return "";
            } else {
                return menu.getName();
            }
        }
    }

    /**
     * 获取字典名称
     */
    @Override
    public String getDictName(Integer dictId) {
        if (ToolUtil.isEmpty(dictId)) {
            return "";
        } else {
            Dict dict = dictService.getdetail(dictId);
            if (dict == null) {
                return "";
            } else {
                return dict.getName();
            }
        }
    }

    /**
     * 获取通知标题
     */
    @Override
    public String getNoticeTitle(Integer dictId) {
        if (ToolUtil.isEmpty(dictId)) {
            return "";
        } else {
            Notice notice = noticeService.findById(dictId);
            if (notice == null) {
                return "";
            } else {
                return notice.getTitle();
            }
        }
    }

    /**
     * 根据字典名称和字典中的值获取对应的名称
     */
    @Override
    public String getDictsByName(String name, Integer val) {
    	Dict dict=new Dict();
        JSONArray jsonArray=dictService.findList(name);
        if(jsonArray!=null && jsonArray.size()==1) {
        	dict=jsonArray.getObject(0, Dict.class);
        }
        if (dict == null) {
            return "";
        } else {
            JSONArray json=dictService.getDictByPid(dict.getId());
            for (int i=0;i<json.size();i++) {
            	Dict item=json.getObject(i,Dict.class);
                if (item.getNum() != null && item.getNum().equals(val)) {
                    return item.getName();
                }
            }
            return "";
        }
    }

    /**
     * 获取性别名称
     */
    @Override
    public String getSexName(Integer sex) {
        return getDictsByName("xb", sex);
    }

    /**
     * 获取用户登录状态
     */
    @Override
    public String getStatusName(Integer status) {
        return ManagerStatus.valueOf(status);
    }

    /**
     * 获取菜单状态
     */
    @Override
    public String getMenuStatusName(Integer status) {
        return MenuStatus.valueOf(status);
    }

    /**
     * 查询字典
     */
    @Override
    public JSONArray findInDict(Integer id) {
        if (ToolUtil.isEmpty(id)) {
            return null;
        } else {
            JSONArray jsonArray=dictService.getDictByPid(id);
            if (jsonArray == null || jsonArray.size() == 0) {
                return null;
            } else {
                return jsonArray;
            }
        }
    }

    /**
     * 获取被缓存的对象(用户删除业务)
     */
    @Override
    public String getCacheObject(String para) {
        return LogObjectHolder.me().get().toString();
    }

    /**
     * 获取子部门id
     */
    @Override
    public List<Integer> getSubDeptId(Integer deptid) {
        Wrapper<Dept> wrapper = new EntityWrapper<>();
        JSONArray json =  deptService.listById(deptid);
       
        ArrayList<Integer> deptids = new ArrayList<>();

        if(json != null && json.size() > 0){
        	for (int i=0;i<json.size();i++) {
        		Dept dept=json.getObject(i,Dept.class);
        		deptids.add(dept.getId());
            }
        }
        return deptids;
    }

    /**
     * 获取所有父部门id
     */
    @Override
    public List<Integer> getParentDeptIds(Integer deptid) {
        Dept dept = deptService.deptDetail(deptid);
        String pids = dept.getPids();
        String[] split = pids.split(",");
        ArrayList<Integer> parentDeptIds = new ArrayList<>();
        for (String s : split) {
            parentDeptIds.add(Integer.valueOf(StrKit.removeSuffix(StrKit.removePrefix(s, "["), "]")));
        }
        return parentDeptIds;
    }

	@Override
	public JSONArray findDictByCode(String code) {
		
		if (ToolUtil.isEmpty(code)) {
            return null;
        } else {
            JSONArray jsonArray=dictService.getDictByCode(code);
            if (jsonArray == null || jsonArray.size() == 0) {
                return null;
            } else {
                return jsonArray;
            }
        }
	}


}
