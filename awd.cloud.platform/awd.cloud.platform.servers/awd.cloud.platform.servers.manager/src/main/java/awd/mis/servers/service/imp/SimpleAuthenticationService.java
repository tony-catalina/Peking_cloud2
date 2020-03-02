package awd.mis.servers.service.imp;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dromara.raincat.annotation.PropagationEnum;
import org.dromara.raincat.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import awd.framework.common.core.IDGenerator;
import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.datasource.orm.core.param.Term;
import awd.framework.common.datasource.orm.core.param.TermType;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.simple.GenericEntityService;
import awd.framework.common.utils.JSONUtil;
import awd.mis.servers.entity.AppEntity;
import awd.mis.servers.entity.GroupmenusEntity;
import awd.mis.servers.entity.GroupsEntity;
import awd.mis.servers.entity.JsappEntity;
import awd.mis.servers.entity.MenusEntity;
import awd.mis.servers.entity.RoleEntity;
import awd.mis.servers.entity.RoleappEntity;
import awd.mis.servers.entity.RolemenusEntity;
import awd.mis.servers.entity.UserappEntity;
import awd.mis.servers.entity.UsergroupEntity;
import awd.mis.servers.entity.UserinfoEntity;
import awd.mis.servers.entity.UsermenuEntity;
import awd.mis.servers.entity.UserroleEntity;
import awd.mis.servers.entity.UsersettingEntity;
import awd.mis.servers.model.GroupInfo;
import awd.mis.servers.model.JsInfo;
import awd.mis.servers.model.RoleInfo;
import awd.mis.servers.model.Userinfo;
import awd.mis.servers.service.AppService;
import awd.mis.servers.service.AuthenticationService;
import awd.mis.servers.service.GroupmenusService;
import awd.mis.servers.service.GroupsService;
import awd.mis.servers.service.JsappService;
import awd.mis.servers.service.MenusService;
import awd.mis.servers.service.RoleService;
import awd.mis.servers.service.RoleappService;
import awd.mis.servers.service.RolemenusService;
import awd.mis.servers.service.UserappService;
import awd.mis.servers.service.UsergroupService;
import awd.mis.servers.service.UserinfoService;
import awd.mis.servers.service.UsermenuService;
import awd.mis.servers.service.UserroleService;
import awd.mis.servers.service.UsersettingService;
import awd.mis.servers.tools.CacheUtils;

@Service("authenticationService")
public class SimpleAuthenticationService extends GenericEntityService<UserinfoEntity, String> implements AuthenticationService {
	
	@Autowired
	private UserinfoService userinfoService;
	@Autowired
	private UserappService userappService;
	@Autowired
	private UserroleService  userroleService;
	@Autowired
	private UsergroupService  usergroupService;
	@Autowired
	private UsermenuService usermenuService;
	@Autowired
	private UsersettingService usersettingService;
	
	@Autowired
	private GroupsService goupsService;
	@Autowired
	private JsappService jsappService;
	@Autowired
	private AppService appService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private RolemenusService rolemenusService;
	@Autowired
	private RoleappService roleappService;
	@Autowired
	private MenusService menusService;
	@Autowired
	private GroupmenusService groupmenusService;
	
	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected IDGenerator<String> getIDGenerator(UserinfoEntity entity) {
        return () -> {
            return getSEQUID(entity.getJsbh());
        };
    }
	
	
	@Override
	public CrudDao<UserinfoEntity, String> getDao() {
		return null;
	}


	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public boolean forbidApp(String userid, List<String> appids) {		
		UserinfoEntity userinfo=userinfoService.selectByPk(userid);
		//放开禁止 然后在重新添加
		userappService.allowAll(userid);
		for (String appcode : appids) {
			QueryParamEntity param=new QueryParamEntity();
			param.and("userid", userid)
			.and("appcode",appcode);
			UserappEntity userapp=userappService.selectSingle(param);
			if(userapp==null) {
				userapp=new UserappEntity();
				userapp.setAppcode(appcode);
				userapp.setUserid(userid);
				userapp.setCreator(userinfo.getLoginname());
				userapp.setCreatetime(Calendar.getInstance().getTime());
				userappService.insert(userapp);
			}			
		}
		return true;
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public boolean forbidMenu(String userid, String appid, List<String> menuids) {
		UserinfoEntity userinfo=userinfoService.selectByPk(userid);
		usermenuService.allowAll(userid,appid);
		for (String menuid : menuids) {
			QueryParamEntity param=new QueryParamEntity();
			param.and("userid", userid)
			.and("appcode",appid)
			.and("menu", menuid);
			UsermenuEntity usermenu=usermenuService.selectSingle(param);
			if(usermenu==null) {
				usermenu=new UsermenuEntity();
				usermenu.setJsbh(userinfo.getJsbh());
				usermenu.setAppcode(appid);
				usermenu.setMenu(menuid);
				usermenu.setCreator(userinfo.getLoginname());
				usermenu.setCreatetime(Calendar.getInstance().getTime());
				usermenuService.insert(usermenu);
			}
		}
		return true;
	}

	@Override
	@UseDataSource("read_ds")
	public GroupInfo getGroup(String groupid) {
		GroupInfo groupinfo=null;
		GroupsEntity groupsEntity=goupsService.selectByPk(groupid);
		if(groupsEntity!=null) {
			groupinfo=new GroupInfo();			
			List<MenusEntity> menuslist=getGroupmenu(groupid);			
			groupinfo.setId(groupsEntity.getId());
			groupinfo.setGroup(groupsEntity);
			groupinfo.setMenus(menuslist);
		}
		
		return groupinfo;
	}

	@UseDataSource("read_ds")
	private List<MenusEntity> getGroupmenu(String groupid) {
		List<MenusEntity> list=null;
		QueryParamEntity param=new QueryParamEntity();
		param.and("groupid", groupid);
		List<GroupmenusEntity> groupmenus=groupmenusService.select(param);
		QueryParamEntity menusparam=new QueryParamEntity();
		Term term=new Term();
		for (GroupmenusEntity groupmenusEntity : groupmenus) {
			term.or(groupid, groupmenusEntity.getGroupid());
		}
		menusparam.addTerm(term);
		list=menusService.select(menusparam);
		return list;
	}
	

	@Override
	@UseDataSource("read_ds")
	public JsInfo getJsApp(String jsbh) {
		JsInfo jsinfo=null;
		QueryParamEntity appparam=new QueryParamEntity();
		appparam.and("jsbh", jsbh);		
		List<JsappEntity> jsapplist=jsappService.select(appparam);
		if(jsapplist!=null) {
			List<AppEntity> applist=new ArrayList<>();
			for (JsappEntity jsappEntity : jsapplist) {
				AppEntity appmodel=appService.selectByPk(jsappEntity.getAppcode());
				applist.add(appmodel);
			}
			jsinfo=new JsInfo();
			jsinfo.setJsbh(jsbh);
			jsinfo.setApps(applist);
		}
		
		return jsinfo;
	}
	

	@Override
	@UseDataSource("read_ds")
	public RoleInfo getRole(String rolecode) {
		RoleInfo roleinfo=null;
		QueryParamEntity param=new QueryParamEntity();
		param.and("code", rolecode);
		RoleEntity roleEntity=roleService.selectSingle(param);
		if(roleEntity!=null) {
			List<AppEntity> applist=getAppsByRole(rolecode);
			List<MenusEntity> menulist=getMenusByRole(rolecode);
			roleinfo=new RoleInfo();
			roleinfo.setId(roleEntity.getId());
			roleinfo.setRole(roleEntity);
			roleinfo.setApps(applist);
			roleinfo.setMenus(menulist);
		}
		return roleinfo;
	}
	


	@UseDataSource("read_ds")
	private List<MenusEntity> getMenusByRole(String rolecode) {
		List<MenusEntity> result= new ArrayList<>();
		QueryParamEntity param=new QueryParamEntity();
		param.and("rolecode", TermType.eq, rolecode);
		List<Map<String, String>> menuslist=new ArrayList();
		List<RolemenusEntity> rolemenulist=rolemenusService.select(param);
		for (RolemenusEntity rolemenusEntity : rolemenulist) {
			Map<String, String> map=new HashMap<>();
			map.put("menu", rolemenusEntity.getMenu());
			menuslist.add(map);
		}
		for (Map<String, String>  menusmap : menuslist) {
			MenusEntity entity=JSONUtil.toBean(CacheUtils.get().getMenu(menusmap.get("menu")),MenusEntity.class);
			if(entity==null) {
				QueryParamEntity menusparam=new QueryParamEntity();
				menusparam.and("appcode", TermType.eq,menusmap.get("appcode"))
				.and("menu", TermType.eq, menusmap.get("menu"));
				entity=menusService.selectSingle(menusparam);
			}
			result.add(entity);
		}		
		return result;
	}
	

	@UseDataSource("read_ds")
	private List<AppEntity> getAppsByRole(String rolecode) {
		List<AppEntity>  result=null;
		QueryParamEntity param=new QueryParamEntity();
		param.and("rolecode", TermType.eq, rolecode);
		List<String> appcodes=new ArrayList<>();
		List<RoleappEntity> roleapplist=roleappService.select(param);
		for (RoleappEntity roleappEntity : roleapplist) {
			appcodes.add(roleappEntity.getAppcode());
		}
		if(appcodes!=null&&appcodes.size()>0) {
			QueryParamEntity appparam=new QueryParamEntity();
			appparam.and("appcode", TermType.in, appcodes);
			result=appService.select(appparam);
		}		
		return result;
	}
	


	@Override
	@UseDataSource("read_ds")
	public Userinfo getUserinfo(String jsbh, String username) {
		Userinfo userinfo=null;
		QueryParamEntity param=new QueryParamEntity();
		param.and("jsbh", jsbh)
		.and("loginname", username);		
		UserinfoEntity userinfoEntity=userinfoService.selectSingle(param);
		if(userinfoEntity!=null) {
			userinfo=new Userinfo();
			
			List<AppEntity> applist=getApps(userinfoEntity.getJsbh(),userinfoEntity.getId());
			GroupsEntity groups=getGroups(userinfoEntity.getId());
			List<MenusEntity> menulist=getMenus(userinfoEntity.getId(),applist);
			List<RoleEntity> rolelist=getRoleList(userinfoEntity.getId());
			List<UsersettingEntity> settinglist=getSetting(userinfoEntity.getId());
			userinfo.setId(userinfoEntity.getId());
			userinfo.setUserinfo(userinfoEntity);
			userinfo.setApps(applist);
			userinfo.setGroup(groups);	
			userinfo.setMenus(menulist);
			userinfo.setRoles(rolelist);
			userinfo.setSetting(settinglist);
			return userinfo;
		}
		return null;
	}
	



	@Override
	@UseDataSource("read_ds")
	public Userinfo getUserinfo(String id) {		
		Userinfo userinfo=null;
		UserinfoEntity userinfoEntity=userinfoService.selectByPk(id);
		if(userinfoEntity!=null) {
			userinfo=new Userinfo();
			
			List<AppEntity> applist=getApps(userinfoEntity.getJsbh(),userinfoEntity.getId());
			GroupsEntity groups=getGroups(userinfoEntity.getId());
			List<MenusEntity> menulist=getMenus(userinfoEntity.getId(),applist);
			List<RoleEntity> rolelist=getRoleList(userinfoEntity.getId());
			List<UsersettingEntity> settinglist=getSetting(userinfoEntity.getId());
			
			userinfo.setId(userinfoEntity.getId());
			userinfo.setUserinfo(userinfoEntity);
			userinfo.setApps(applist);
			userinfo.setGroup(groups);			
			userinfo.setMenus(menulist);
			userinfo.setRoles(rolelist);
			userinfo.setSetting(settinglist);
			return userinfo;
		}
		return null;
	}


	@UseDataSource("read_ds")
	private List<UsersettingEntity> getSetting(String userid) {
		QueryParamEntity param=new QueryParamEntity();
		param.and("userid", userid);
		param.and("state", "R2");
		return usersettingService.select(param);
	}
	


	@UseDataSource("read_ds")
	private List<MenusEntity> getMenus(String userid, List<AppEntity> applist) {
		List<MenusEntity> menulist=null;
		QueryParamEntity param=new QueryParamEntity();
		param.and("userid", userid);
		List<UsermenuEntity> usermenulist=usermenuService.select(param);
		
		if(applist!=null&&applist.size()>0) {
			menulist=new ArrayList<>();
			QueryParamEntity param1=new QueryParamEntity();
			for(int i=0;i<applist.size();i++) {
				param1.or("appcode", applist.get(i).getAppcode());
			}			
			List<MenusEntity> menus=menusService.select(param1);
			for (MenusEntity menusEntity : menus) {
				boolean  forbidden=false;
				for(UsermenuEntity usermenu:usermenulist) {
					if(usermenu.getMenu().equals(menusEntity.getMenu())) {
						forbidden=true;
						break;
					}
				}
				if(!forbidden) {//没有禁用
					menulist.add(menusEntity);
				}
				
			}
			
		}
		
		return menulist;
	}


	@UseDataSource("read_ds")
	private GroupsEntity getGroups(String userid) {
		GroupsEntity group=null;
		QueryParamEntity param=new QueryParamEntity();
		param.and("userid", userid);
		UsergroupEntity usergroup=usergroupService.selectSingle(param);
		if(usergroup!=null) {
			QueryParamEntity param1=new QueryParamEntity();
			param1.and("id", usergroup.getGroupid());
			group=goupsService.selectSingle(param1);
		}		
		return group;
	}
	
	
	@UseDataSource("read_ds")
	private List<RoleEntity> getRoleList(String userid){
		List<RoleEntity> result=null;
		QueryParamEntity param=new QueryParamEntity();
		param.and("userid", userid);
		List<UserroleEntity> list=userroleService.select(param);
		if(list!=null) {
			result=new ArrayList<>();
			for (UserroleEntity userroleEntity : list) {
				QueryParamEntity roleparam=new QueryParamEntity();
				roleparam.and("code", userroleEntity.getRolecode());
				RoleEntity role=roleService.selectSingle(roleparam);
				result.add(role);
			}
		}
		
		return result;
	}
	


	@UseDataSource("read_ds")
	private List<AppEntity> getApps(String jsbh, String userid) {
		List<String> ids=new ArrayList<>();
		QueryParamEntity appparam=new QueryParamEntity();
		appparam.and("jsbh", jsbh);	
		//监所安装过的APP
		Set<String> exclude=new HashSet<String>();
		exclude.add("p1");
		exclude.add("p2");
		exclude.add("p3");
		appparam.setExcludes(exclude);
		List<JsappEntity> jsapplist=jsappService.select(appparam);
		for (JsappEntity jsappEntity : jsapplist) {
			ids.add(jsappEntity.getAppcode());
		}		
		System.out.println("APP一共："+jsapplist.size()+"个");
		QueryParamEntity userparam=new QueryParamEntity();
		userparam.and("userid", userid);
		//用户明确禁用的APP
		List<UserappEntity> userapplist=userappService.select(userparam);
		for (UserappEntity userappEntity : userapplist) {
			ids.remove(userappEntity.getAppcode());
		}
		
		System.out.println("禁用了："+userapplist.size()+"个");
		QueryParamEntity param1=new QueryParamEntity();
		 if(ids.size()>0) {
			 String[] idsarray=new String[ids.size()];
			 for (int i=0;i<ids.size();i++) {
				idsarray[i]=ids.get(i);
			 }
			 param1.and("appcode",TermType.in,idsarray);	
		 }else {
			 param1.and("appcode",TermType.in,new String[] {"无数据"});
		 }	
		 Set<String> qexclude=new HashSet<String>();
		 qexclude.add("p1");
		 qexclude.add("p2");
		 qexclude.add("p3");
		 param1.setExcludes(qexclude);
		 List<AppEntity> list=appService.select(param1);
		 return list;
	}
	


	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)	
	public int deleteByPk(String pk) {
		return super.deleteByPk(pk);
	}


	@Override
	@UseDataSource("read_ds")
	public String getSEQUID(String jsbh) {
		return super.getSEQUID(jsbh);
	}


	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public String insert(UserinfoEntity entity) {
		entity.setCreatetime(Calendar.getInstance().getTime());
		return super.insert(entity);
	}


	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public String saveOrUpdate(UserinfoEntity entity) {
		return super.saveOrUpdate(entity);
	}


	@Override
	@UseDataSource("read_ds")
	public List<UserinfoEntity> selectByPk(List<String> id) {
		return super.selectByPk(id);
	}


	@Override
	@UseDataSource("read_ds")
	public UserinfoEntity selectByPk(String pk) {
		return super.selectByPk(pk);
	}


	@Override
	@UseDataSource("read_ds")
	public PagerResult<UserinfoEntity> selectPager(Entity arg0) {
		return super.selectPager(arg0);
	}


	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(List<UserinfoEntity> data) {
		return super.updateByPk(data);
	}


	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(String pk, UserinfoEntity entity) {
		entity.setUpdatetime(Calendar.getInstance().getTime());
		return super.updateByPk(pk, entity);
	}


	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	protected int updateByPk(UserinfoEntity entity) {
		return super.updateByPk(entity);
	}


    
}
