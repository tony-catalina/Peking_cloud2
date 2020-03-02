/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service.imp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dromara.raincat.annotation.PropagationEnum;
import org.dromara.raincat.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.core.param.TermType;
import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.simple.GenericEntityService;
import awd.framework.common.utils.StringUtils;
import awd.mis.servers.api.LogsService;
import awd.mis.servers.dao.AppDao;
import awd.mis.servers.entity.AppEntity;
import awd.mis.servers.entity.JsappEntity;
import awd.mis.servers.model.HfModel;
import awd.mis.servers.service.AppService;
import awd.mis.servers.service.JsappService;
import awd.mis.servers.tools.CacheUtils;

@Service("appService")
public class SimpleAppService extends GenericEntityService<AppEntity, String> implements AppService {

    @Autowired
    private AppDao appDao;
    @Autowired
    private LogsService logsService;
    
    @Autowired
    private JsappService jsappService;

    

    @Override
	public CrudDao<AppEntity, String> getDao() {
		return this.appDao;
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public IDGenerator<String> getIDGenerator(AppEntity entity) {
        return () -> {
            return this.getSEQUID("");
        };
    }

	@Override
	@UseDataSource("read_ds")
	public PagerResult<AppEntity> selectPager(Entity arg0) {
		return super.selectPager(arg0);
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
	public String insert(AppEntity entity) {
		entity.setCreatetime(Calendar.getInstance().getTime());
		return super.insert(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public String saveOrUpdate(AppEntity entity) {
		return super.saveOrUpdate(entity);
	}

	@Override
	@UseDataSource("read_ds")
	public List<AppEntity> selectByPk(List<String> id) {
		return super.selectByPk(id);
	}
	

	@Override
	@UseDataSource("read_ds")
	public AppEntity selectByPk(String pk) {
		return super.selectByPk(pk);
	}
	

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	protected int updateByPk(AppEntity entity) {
		return super.updateByPk(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(List<AppEntity> data) {
		return super.updateByPk(data);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(String pk, AppEntity entity) {
		entity.setUpdatetime(Calendar.getInstance().getTime());
		return super.updateByPk(pk, entity);
	}
    

	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void cached() {
		List<AppEntity> listAll=select();
		for (AppEntity appEntity : listAll) {
			CacheUtils.get().saveApp(appEntity);				
		}	
	}
	
	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<AppEntity> getApplistWithJsappByJsbh(QueryParamEntity  params) {
	/*	QueryParamEntity qEntity = new QueryParamEntity();
		qEntity.and("jsbh", TermType.eq, jsbh);*/
		List<JsappEntity> jsappList = jsappService.select(params);
		List<AppEntity> appList = new ArrayList<>();
		for (JsappEntity jsapp : jsappList) {
			AppEntity appEntity = null;
			if (!StringUtils.isNullOrEmpty(CacheUtils.get().getAppEntity(jsapp.getAppcode()))) {
				appEntity = CacheUtils.get().getAppEntity(jsapp.getAppcode());
			}else {
				QueryParamEntity queryApp = new QueryParamEntity();
	        	queryApp.and("appcode", TermType.eq, jsapp.getAppcode());
	        	Set<String> exclude=new HashSet<String>();
	    		exclude.add("p1");
	    		exclude.add("p2");
	    		exclude.add("p3");
	    		queryApp.setExcludes(exclude);
	    		queryApp.and("state", TermType.eq,"R2");
	    		appEntity = this.selectSingle(queryApp);
	    		if (StringUtils.isNullOrEmpty(appEntity)) {
	    			continue;	
				}
	    		CacheUtils.get().setAppCacheWithoutByte(appEntity,jsapp.getAppcode());
			}
			appList.add(appEntity);
		}
		return appList;
	}
	
	
	@TxTransaction(propagation=PropagationEnum.PROPAGATION_REQUIRES_NEW,transactionManager="transactionManager",waitMaxTime=80000)
    @UseDataSource("write_ds")
	public ResponseMessage<String> test() {
		HfModel hf1=new HfModel();
		hf1.setHflbid("1231231231231");
		hf1.setHfsj(Calendar.getInstance().getTime());
		HfModel hf2=new HfModel();
		hf2.setHflbid("435345346534563");
		hf2.setHfsj(Calendar.getInstance().getTime());
		logsService.saveHf(hf1);
		logsService.saveHf(hf2);
//		AppEntity app=new AppEntity();
//		app.setAppcode("1");
//		app.setName("1");
//		app.setVersion("1");
//		app.setType("1");
//		app.setZone("1");
//		app.setUrl("1");
//		app.setFlag("1");
//		app.setState("1");
//		app.setCreator("1");
//		app.setCreatetime(Calendar.getInstance().getTime());
//		this.insert(app);
		return ResponseMessage.ok();
//		throw new IllegalStateException("by exFlag");
		
	}
	
	/**
	 * 根据appcode从缓存中获取没有图片byte的app信息，假如没有就从数据库中查询，并且把app保存到缓存中
	 */
	public AppEntity getAppWithoutByteInCaChe(String appcode) {
		AppEntity appEntity = CacheUtils.get().getAppEntity(appcode);
		if (StringUtils.isNullOrEmpty(appEntity)) {
			QueryParamEntity qEntity = new QueryParamEntity();
			qEntity.and("appcode", TermType.eq, appcode)
				.and("state", TermType.eq, "R2");
			Set<String> exclude=new HashSet<String>();
			exclude.add("p1");
			exclude.add("p2");
			exclude.add("p3");
			qEntity.setExcludes(exclude);
			appEntity = this.selectSingle(qEntity);
			if (!StringUtils.isNullOrEmpty(appEntity)) {
				CacheUtils.get().setAppCacheWithoutByte(appEntity, appcode);
			}
		}
		return appEntity;
	}
	
	
}
