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
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.simple.GenericEntityService;
import awd.mis.servers.dao.ApplunboDao;
import awd.mis.servers.entity.AppEntity;
import awd.mis.servers.entity.ApplunboEntity;
import awd.mis.servers.service.AppService;
import awd.mis.servers.service.ApplunboService;
import awd.mis.servers.tools.CacheUtils;

@Service("applunboService")
public class SimpleApplunboService extends GenericEntityService<ApplunboEntity, String> implements ApplunboService {

    @Autowired
    private ApplunboDao applunboDao;

    @Autowired
    private AppService appService;

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected IDGenerator<String> getIDGenerator(ApplunboEntity entity) {
        String jsbh = "";
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public ApplunboDao getDao() {
        return applunboDao;
    }

    @Override
    @UseDataSource("read_ds")
    //@ExtCacheable(key="soa_manager_applunbo_cache_page+#p0",expireTime=1800)
    public PagerResult<ApplunboEntity> selectPager(Entity param) {
        return super.selectPager(param);
    }


    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public String saveOrUpdate(ApplunboEntity entity) {
        return super.saveOrUpdate(entity);
    }

    @Override
    @UseDataSource("read_ds")
    public List<ApplunboEntity> select() {
        QueryParamEntity q = QueryParamEntity.empty().and("state", "R2");
        List<ApplunboEntity> applunboEntityList = applunboDao.query(q);
        applunboEntityList.sort((b, a) -> a.getCreatetime().compareTo(b.getCreatetime()));
        System.err.println("applunboEntityList==="+JSON.toJSONString(applunboEntityList));
        if (applunboEntityList.size() > 4) {
            applunboEntityList = applunboEntityList.subList(0, 4);
        }
        for (ApplunboEntity applunboEntity : applunboEntityList) {
        	System.err.println("applunboEntity.getAppcode()==="+applunboEntity.getAppcode());
            //AppEntity appEntity = appService.selectSingle(QueryParamEntity.empty().and("appcode", applunboEntity.getAppcode()));
           
        	AppEntity appEntity = CacheUtils.get().getAppEntity(applunboEntity.getAppcode());
            
            try {
            	String pic = appEntity.getPic1();
            	String icon=appEntity.getIcon();
            	String name=appEntity.getName();
	            if (!StringUtils.isNullOrEmpty(pic)) {
	                applunboEntity.setPhotoURL(pic);
	                applunboEntity.setIcon(icon);
	                applunboEntity.setName(name);
	            }
            }catch (Exception e) {
				// TODO: handle exception
            	e.printStackTrace();
			}
        }
        return applunboEntityList;
    }
    



    

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public String insert(ApplunboEntity entity) {
		return super.insert(entity);
	}

    /**
     * 设置applunbo图
     */
    
    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public String setAppLunBo(List<ApplunboEntity> list){
    	String code;
    	try {
        	//新增数据
        	for(ApplunboEntity e:list) {
        		this.insert(e);
        	}
        	code="200";
    	}catch (Exception e) {
    		e.printStackTrace();
    		code="500";
		}
    	return code;
    }
    
    
	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected int updateByPk(ApplunboEntity entity) {
        return super.updateByPk(entity);
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
    @UseDataSource("read_ds")	
	public ApplunboEntity selectByPk(String pk) {
		return super.selectByPk(pk);
	}

	@Override
    @UseDataSource("read_ds")
	public List<ApplunboEntity> selectByPk(List<String> id) {
		return super.selectByPk(id);
	}
	

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(List<ApplunboEntity> data) {
		return super.updateByPk(data);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(String pk, ApplunboEntity entity) {
		return super.updateByPk(pk, entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public void setAppLunboR3() {
		applunboDao.setAppLunboR3();		
	}
    
}
