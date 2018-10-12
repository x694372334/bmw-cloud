package com.stylefeng.guns.modular.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.common.utils.HttpUtils;
import com.stylefeng.guns.core.beetl.ShiroExt;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.node.MenuNode;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.model.Menu;
import com.stylefeng.guns.modular.system.model.Role;
import com.stylefeng.guns.modular.system.service.IMenuService;
import com.stylefeng.guns.modular.system.service.IRoleService;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 菜单服务
 *
 * @author fengshuonan
 * @date 2017-05-05 22:20
 */
@Service
public class MenuServiceImpl  implements IMenuService {

    
    @Resource
    private RelationServiceImpl relationService;
    
    @Autowired
    private IRoleService roleService;

	@Value("${bmw.cloud.baseservice.url}")
	private String bmw_cloud_baseservice_url="";
	
	@Value("${menu.getMenu}")
	private String menu_getMenu="";
	
	@Value("${menu.add}")
	private String menu_add="";
	
	@Value("${menu.edit}")
	private String menu_edit="";
	
	@Value("${menu.del}")
	private String menu_del="";
	
	@Value("${menu.detail}")
	private String menu_detailMenu="";
	
	@Value("${menu.tree}")
	private String menu_tree="";
	
	@Value("${menu.menusById}")
	private String menu_menusById="";
	
	@Value("${menu.getUrl}")
	private String menu_getUrl="";
	
	@Value("${menu.menusByRoleIds}")
	private String menu_menuByRoleId="";
	
	@Value("${menu.menuTreeByRoleIds}")
	private String menu_menuTreeByRoleIds="";
	
	@Value("${menu.selectOne}")
	private String menu_selectOne="";
    
	@Value("${menu.menusByRoleId}")
	private String menu_menusByRoleId="";
	@Override
	public JSONArray selectMenus(String condition, String level) {
			//Json对象类
	    	JSONObject json=new JSONObject();
	    	json.put("name", condition); 
	    	json.put("level", level);
	    	//添加所需查询的条件
	    	JSONArray jsonArray=null;
	    	try {
	    		//base64加密这个json对象
				String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
				//通过http调用bmw-cloud项目里的列表查询功能，并把参数带过去
				String test = HttpUtils.doGet(bmw_cloud_baseservice_url+menu_getMenu+params);
				//取到返回值在items下
				jsonArray=JSON.parseObject(test).getJSONArray("items");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return jsonArray;
	}
	
	 @Override
	public void addMenu(Menu menu) {
		 try {
				/*String jsonStr=JSONObject.toJSONString(menu);
				String params=new String(Base64Utils.encode(jsonStr.getBytes("UTF-8")));
				HttpUtils.doPost(bmw_cloud_baseservice_url+menu_add+params,null);*/
				
				HttpUtils.doPost(bmw_cloud_baseservice_url+menu_add,JSONObject.toJSONString(menu).toString(),null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	 }
	 
	 @Override
	public void updateEditMenu(Menu menu) {
		 try {
				/*String jsonStr=JSONObject.toJSONString(menu);
				String params=new String(Base64Utils.encode(jsonStr.getBytes("UTF-8")));
				HttpUtils.doPost(bmw_cloud_baseservice_url+menu_edit+params,null);*/
				HttpUtils.doPost(bmw_cloud_baseservice_url+menu_edit,JSONObject.toJSONString(menu).toString(),null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	 }
	
    @Override
    public void delMenu(Long menuId) {
    	JSONObject json=new JSONObject();
        json.put("menuId", menuId);
		try {
			String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			HttpUtils.doPost(bmw_cloud_baseservice_url+menu_del+params,null);
	        //缓存菜单的名称
	        LogObjectHolder.me().set(ConstantFactory.me().getMenuName(menuId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void delMenuContainSubMenus(Long menuId) {
    	Menu menu=new Menu();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+menu_detailMenu+menuId);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				menu=jsonObject.toJavaObject(Menu.class);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //删除当前菜单
        delMenu(menuId);

        //删除所有子菜单
//        Wrapper<Menu> wrapper = new EntityWrapper<>();
//        wrapper = wrapper.like("pcodes", "%[" + menu.getCode() + "]%");
//        List<Menu> menus = menuMapper.selectList(wrapper);
//        for (Menu temp : menus) {
//            delMenu(temp.getId());
//        }
    }
    
    @Override
    public Menu detailMenu(Long menuId) {
    	Menu menu=new Menu();
    	try {
			String rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+menu_detailMenu+menuId);
			JSONObject jsonObject=JSON.parseObject(rlt).getJSONObject("items");
			if(jsonObject!=null) {
				menu=jsonObject.toJavaObject(Menu.class);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return menu;
    }

    @Override
    public List<Long> getMenuIdsByRoleId(Integer roleId) {
    	List<Long> list = null;
    	JSONArray jsonArray=null;
    	try {
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+menu_menusByRoleId+roleId,null);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
			if(jsonArray!=null) {
				list = new ArrayList<Long>();
				for(int i=0;i<jsonArray.size();i++) {
					if(null!=jsonArray.get(i)) {
						list.add(Long.valueOf(jsonArray.get(i).toString()));
					}
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
    }

    @Override
    public List<ZTreeNode> menuTreeList(Integer roleId) {
    	ShiroExt shiro = new ShiroExt();
    	List<ZTreeNode> list = new ArrayList<ZTreeNode>();
    	JSONArray jsonArray=null; 
    	String rlt = "";
    	try {
			if (!shiro.getUser().isAdmin) {
				Role role = roleService.getRoleDetail(roleId);
				Role condition = new Role();
				condition.setParentEId(role.getParentEId());
				condition.setVersion(1);
				JSONObject jsonObject = (JSONObject) roleService.findRole(condition).get(0);
				Integer rid = null;
				if(jsonObject!=null) {
					rid=jsonObject.toJavaObject(Role.class).getId();
				}
				rlt = HttpUtils.doPost(bmw_cloud_baseservice_url + menu_menuByRoleId + rid, null);
			}else {
				rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+menu_tree+"/1");
			}
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
			for(int i=0;i<jsonArray.size();i++) {
				JSONObject ob  = (JSONObject) jsonArray.get(i);//得到json对象
				jsonArray.get(i);
				ZTreeNode tree=JSONObject.toJavaObject(ob, ZTreeNode.class);
				list.add(tree);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
    }

    @Override
    public List<ZTreeNode> menuTreeListByMenuIds(List<Long> menuIds,Integer roleId) {
    	ShiroExt shiro = new ShiroExt();
    	List<ZTreeNode> list = new ArrayList<ZTreeNode>();
    	Map<Long, Long> map = menuIds.stream().collect(Collectors.toMap(Long::longValue, a -> a,(b1,b)->b));
    	String rlt = "";
    	try {
    		if (!shiro.getUser().isAdmin) {
				Role role = roleService.getRoleDetail(roleId);
				Role condition = new Role();
				condition.setParentEId(role.getParentEId());
				condition.setVersion(1);
				JSONObject jsonObject = (JSONObject) roleService.findRole(condition).get(0);
				Integer rid = null;
				if(jsonObject!=null) {
					rid=jsonObject.toJavaObject(Role.class).getId();
				}
				rlt = HttpUtils.doPost(bmw_cloud_baseservice_url + menu_menuTreeByRoleIds + rid, null);
			}else {
			    rlt=HttpUtils.doGet(bmw_cloud_baseservice_url+menu_tree+"/1");
			}
			JSONArray jsonArray=JSON.parseObject(rlt).getJSONArray("items");
			for(int i=0;i<jsonArray.size();i++) {
				JSONObject ob  = (JSONObject) jsonArray.get(i);
				ZTreeNode tree=JSONObject.toJavaObject(ob, ZTreeNode.class);
				Long id=ob.getLong("id");
				if(map.get(id)!=null){
					tree.setChecked(true);
				}
				
				list.add(tree);
			}
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;
    }

    @Override
    public int deleteRelationByMenu(Long menuId) {
    	relationService.deleteRelationByMenu(menuId);
        return 1;
    }

    @Override
    public List<String> getResUrlsByRoleId(Integer roleId) {
    	JSONObject json=new JSONObject();
    	json.put("roleId", roleId);
    	JSONArray jsonArray=null;
    	List<String> list = new ArrayList<>();
    	try {
			String params = Base64Utils.encodeToString(json.toString().getBytes("UTF-8"));
			String test = HttpUtils.doPost(bmw_cloud_baseservice_url+menu_getUrl+params , null);
			JSONArray data=JSON.parseObject(test).getJSONArray("items");
			JSONArray array = new JSONArray(data);//将json字符串转成json数组
			for (int i = 0; i < array.size(); i++) {//循环json数组
				String dataString = array.get(i).toString();//得到json对象
				
				list.add(dataString);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return list;	
    }

    @Override
    public List<MenuNode> getMenusByRoleIds(List<Integer> roleIds) {
    	List<MenuNode> list = new ArrayList<>();
    	String param = "";
    	for(Integer roleId : roleIds) {
    		param = param+roleId+",";
    	}
    	param = param.substring(0, param.length()-1);
    	try {
			String str=HttpUtils.doPost(bmw_cloud_baseservice_url+menu_menuByRoleId+param ,null);
			JSONArray data=JSON.parseObject(str).getJSONArray("items");
				JSONArray array = new JSONArray(data);//将json字符串转成json数组
				for (int i = 0; i < array.size(); i++) {//循环json数组
					JSONObject ob  = (JSONObject) array.get(i);//得到json对象
					MenuNode menu = JSONObject.toJavaObject(ob, MenuNode.class);
					list.add(menu);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return list;
    }
    
    @Override
    public Menu selectOne(Menu menu) {
    	Menu menudata = new Menu();
    	JSONArray jsonArray=null;
    	try {
    		String params = Base64Utils.encodeToString(menu.toString().getBytes("UTF-8"));
			String rlt=HttpUtils.doPost(bmw_cloud_baseservice_url+menu_selectOne+params,null);
			jsonArray=JSON.parseObject(rlt).getJSONArray("items");
			menudata=jsonArray.toJavaObject(Menu.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return menudata;    	
    }
    
}
