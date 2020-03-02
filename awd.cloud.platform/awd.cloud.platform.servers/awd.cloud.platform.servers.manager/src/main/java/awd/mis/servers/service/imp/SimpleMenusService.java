/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service.imp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.dromara.raincat.annotation.PropagationEnum;
import org.dromara.raincat.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.core.param.TermType;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.simple.GenericEntityService;
import awd.framework.common.utils.ApplicationContextHolder;
import awd.framework.common.utils.StringUtils;
import awd.framework.expands.redisclient.RedisUtils;
import awd.mis.servers.dao.MenusDao;
import awd.mis.servers.entity.MenusEntity;
import awd.mis.servers.service.MenusService;
import awd.mis.servers.tools.CacheUtils;
import awd.mis.servers.tools.Result;

@Service("menusService")
public class SimpleMenusService extends GenericEntityService<MenusEntity, String> implements MenusService {

	@Autowired
    private MenusDao menusDao;

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected IDGenerator<String> getIDGenerator(MenusEntity entity) {
        return () -> {
            return getSEQUID("");
        };
    }

    @Override
    public MenusDao getDao() {
        return menusDao;
    }

	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void cached() {
		List<MenusEntity> list=select();
		for (MenusEntity menusEntity : list) {
			String key=CacheUtils.CACHE_MENUS +"_"+menusEntity.getMenu().toUpperCase() ;
			String value = JSONObject.toJSONString(menusEntity); 
			CacheUtils.get().set(key, value); 	
		}	
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int deleteByPk(String pk) {
		return super.deleteByPk(pk);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public String getSEQUID(String jsbh) {
		return super.getSEQUID(jsbh);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public String insert(MenusEntity entity) {
		entity.setCreatetime(Calendar.getInstance().getTime());
		return super.insert(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public String saveOrUpdate(MenusEntity entity) {
		return super.saveOrUpdate(entity);
	}

	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<MenusEntity> selectByPk(List<String> id) {
		return super.selectByPk(id);
	}
	
	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public MenusEntity selectByPk(String pk) {
		return super.selectByPk(pk);
	}
	
	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public PagerResult<MenusEntity> selectPager(Entity arg0) {
		return super.selectPager(arg0);
	}
	
	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(List<MenusEntity> data) {
		return super.updateByPk(data);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	protected int updateByPk(MenusEntity entity) {
		return super.updateByPk(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(String pk, MenusEntity entity) {
		entity.setUpdatetime(Calendar.getInstance().getTime());
		return super.updateByPk(pk, entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public String initMenu(String appcode, String menu, String fname, String name, String url, String creator) {
		System.err.println("添加菜单++++++++++++++++++++++++++++++++++");
		QueryParamEntity param=new QueryParamEntity();
		param.setPaging(false);
		param.and("appcode", TermType.eq, appcode)
		.and("menu", TermType.eq, menu);
		MenusEntity menuentity=selectSingle(param);
		if(menuentity!=null) {
			menuentity.setParent(fname);
			menuentity.setMenuname(name);
			menuentity.setUrl(url);
			menuentity.setUpdator(creator);
			//new SimpleDateFormat("yyyy-MM-HH hh:mm:ss").format(date)
			menuentity.setUpdatetime(Calendar.getInstance().getTime());
		}else {
			menuentity=new MenusEntity();
			menuentity.setAppcode(appcode);
			menuentity.setMenu(menu);
			menuentity.setParent(fname);
			menuentity.setMenuname(name);
			menuentity.setUrl(url);
			menuentity.setCreator(creator);
			menuentity.setCreatetime(Calendar.getInstance().getTime());
		}
		saveOrUpdate(menuentity);
		System.err.println("添加菜单成功++++++++++++++++++++++++++++++++++");
		return "初始化成功";
	}   
	
	
	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public String addMenusPl(List<Map<String,Object>> list) {
		System.err.println("添加菜单++++++++++++++++++++++++++++++++++");
		for(Map<String,Object> map : list) {
		QueryParamEntity param=new QueryParamEntity();
		param.setPaging(false);
		param.and("appcode", TermType.eq, map.get("appcode"))
		.and("menu", TermType.eq,  map.get("menu"));
		MenusEntity menuentity=selectSingle(param);
		if(menuentity!=null) {
			menuentity.setParent(map.get("fname").toString());
			menuentity.setMenuname(map.get("name").toString());
			menuentity.setUrl(map.get("url").toString());
			menuentity.setUpdator(map.get("creator").toString());
			//new SimpleDateFormat("yyyy-MM-HH hh:mm:ss").format(date)
			menuentity.setUpdatetime(Calendar.getInstance().getTime());
		}else {
			menuentity=new MenusEntity();
			menuentity.setAppcode(map.get("appcode").toString());
			menuentity.setMenu(map.get("menu").toString());
			menuentity.setParent(map.get("fname").toString());
			menuentity.setMenuname(map.get("name").toString());
			menuentity.setUrl(map.get("url").toString());
			menuentity.setCreator(map.get("creator").toString());
			menuentity.setCreatetime(Calendar.getInstance().getTime());
		}
		saveOrUpdate(menuentity);
		System.err.println("添加菜单成功++++++++++++++++++++++++++++++++++");
		}
		return "初始化成功";
	}  
    
	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public Result<String> initMenuList(String appcode, List<Map<String, Object>> menuList, String creator) {
		Result<String> result = new Result<>();
		for (Map<String, Object> menuMap : menuList) {
			String menu = (String) menuMap.get("menu");
			String fname = (String) menuMap.get("parent");
			String name = (String) menuMap.get("menuname");
			String url = (String) menuMap.get("url");
			if (StringUtils.isNullOrEmpty(menu)
					|| StringUtils.isNullOrEmpty(fname)
					|| StringUtils.isNullOrEmpty(name)
					|| StringUtils.isNullOrEmpty(url)) {
				result.setCode(500);
				result.setMsg("不符合菜单json样式！");
				return result;
			}else if (this.menuIsExist(menu)) {
				result.setCode(500);
				result.setMsg("菜单：" + menu + "与已有菜单重复！");
				return result;
			}
			this.initMenu(appcode,menu,fname,name,url,creator);
		}
		result.setCode(200);
		result.setMsg("菜单设置成功");
		return result;
	}
	
	
	/**
	 * menu表既然menu字段是主键，那么我就根据menu判断实体是否存在，并返回提示
	 * @param menu
	 */
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public boolean menuIsExist(String menu) {
		QueryParamEntity param = new QueryParamEntity();
		param.and("menu", TermType.eq, menu);
		MenusEntity menuentity = selectSingle(param);
		if (StringUtils.isNullOrEmpty(menuentity)) {
			return false;
		}
		return true;
	}
	
	
    
}
