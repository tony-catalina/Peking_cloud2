/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service.imp;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.dromara.raincat.annotation.PropagationEnum;
import org.dromara.raincat.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import awd.framework.common.core.IDGenerator;
import awd.framework.common.core.param.TermType;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.simple.GenericEntityService;
import awd.framework.common.utils.StringUtils;
import awd.mis.servers.dao.JsappDao;
import awd.mis.servers.entity.AppEntity;
import awd.mis.servers.entity.JsappEntity;
import awd.mis.servers.entity.MountEntity;
import awd.mis.servers.model.Userinfo;
import awd.mis.servers.service.AppService;
import awd.mis.servers.service.JsappService;
import awd.mis.servers.service.MountService;

@Service("jsappService")
public class SimpleJsappService extends GenericEntityService<JsappEntity, String> implements JsappService {

	@Autowired
    private JsappDao jsappDao;
	
	@Autowired
	private MountService mountService;
	
	@Autowired
	private AppService appService;
	
    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected IDGenerator<String> getIDGenerator(JsappEntity entity) {
        return () -> {
            return getSEQUID("");
        };
    }

    @Override
    public JsappDao getDao() {
        return jsappDao;
    }

    /**
     * 根据jsappEntity给MountEntity赋值
     * @param mEntity
     * @param jsappEntity
     * @return
     */
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    private String mountOrUnmountByJsappEntity(JsappEntity jsappEntity,String type) {//定义1是安装，0是卸载
    	String jsbh = jsappEntity.getJsbh();
    	String appcode = jsappEntity.getAppcode();
    	QueryParamEntity qParamEntity = new QueryParamEntity();
    	qParamEntity.and("jsbh", TermType.eq, jsbh)
    		.and("appcode", TermType.eq, appcode)
    		.and("state", TermType.eq, "R2");
    	MountEntity mEntity = new MountEntity();
    	mEntity.setAppcode(jsappEntity.getAppcode());
		mEntity.setJsbh(jsappEntity.getJsbh());
		mEntity.setCreator(jsappEntity.getCreator());
		mEntity.setCreatetime(jsappEntity.getCreatetime());
		mEntity.setState("R2");
    	if (type == "1") {	//安装应用，查找这个jsbh的这个app有没有卸载记录，有的话把记录改为R3，插入安装记录
    		qParamEntity.and("unmountor", TermType.notnull, "unmountor");	//好像使用了notnull之后，这个后面的值就无所谓了，只要不是空串就行
    		MountEntity unmountEntity = mountService.selectSingle(qParamEntity);
			if (!StringUtils.isNullOrEmpty(unmountEntity)) {
				unmountEntity.setState("R3");
				unmountEntity.setUpdator(jsappEntity.getUpdator());
				unmountEntity.setUpdatetime(jsappEntity.getUpdatetime());
				mountService.saveOrUpdate(unmountEntity);
			}
    		mEntity.setMountor(jsappEntity.getCreator());
    		mEntity.setMountime(jsappEntity.getCreatetime());
		}else if(type == "0"){	//卸载应用，查找这个jsbh的这个app有没有安装记录，有的话把记录改为R3，插入卸载记录
			qParamEntity.and("mountor", TermType.notnull, "mountor");	//好像使用了notnull之后，这个后面的值就无所谓了，只要不是空串就行
    		MountEntity mountEntity = mountService.selectSingle(qParamEntity);
			if (!StringUtils.isNullOrEmpty(mountEntity)) {
				mountEntity.setState("R3");
				mountEntity.setUpdator(jsappEntity.getUpdator());
				mountEntity.setUpdatetime(jsappEntity.getUpdatetime());
				mountService.saveOrUpdate(mountEntity);
			}
    		mEntity.setUnmountor(jsappEntity.getCreator());
    		mEntity.setUnmountime(jsappEntity.getCreatetime());
		}	
    	return mountService.insert(mEntity);
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
	public String insert(JsappEntity entity) {
		entity.setCreatetime(Calendar.getInstance().getTime());
		return super.insert(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public String saveOrUpdate(JsappEntity entity) {
		return super.saveOrUpdate(entity);
	}

	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<JsappEntity> selectByPk(List<String> id) {
		return super.selectByPk(id);
	}
	

	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public JsappEntity selectByPk(String pk) {
		return super.selectByPk(pk);
	}
	

	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public PagerResult<JsappEntity> selectPager(Entity arg0) {
		return super.selectPager(arg0);
	}
	
	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	protected int updateByPk(JsappEntity entity) {
		return super.updateByPk(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(List<JsappEntity> data) {
		return super.updateByPk(data);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(String pk, JsappEntity entity) {
		entity.setUpdatetime(Calendar.getInstance().getTime());
		return super.updateByPk(pk, entity);
	}

	/**
	 *卸载应用时，也插入一条安卸载记录
	 */
	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int delJsApp(Map map) {
    	String jsbh = (String) map.get("jsbh");
    	String appcode = (String) map.get("appcode");
    	QueryParamEntity qParamEntity = new QueryParamEntity();
    	qParamEntity.and("jsbh", TermType.eq, jsbh).and("appcode", TermType.eq, appcode);
		JsappEntity jsappEntity = this.selectSingle(qParamEntity);
		
		this.mountOrUnmountByJsappEntity(jsappEntity,"0");	//0是卸载
		
		return jsappDao.delJsApp(map);
	}

	/**
	 *安装应用时，也插入一条安装记录
	 */
	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public String insertJsappAndMount(JsappEntity jsappEntity) {
		
		this.mountOrUnmountByJsappEntity(jsappEntity,"1");	//1是安装
		
		return this.insert(jsappEntity);
	} 
    
    
}
