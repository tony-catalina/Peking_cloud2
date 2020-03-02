/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service.imp;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.queryParam;

import java.util.Calendar;
import java.util.List;

import org.dromara.raincat.annotation.PropagationEnum;
import org.dromara.raincat.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.service.simple.GenericEntityService;
import awd.mis.servers.entity.PlEntity;
import awd.mis.servers.dao.PlDao;
import awd.mis.servers.service.PlService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("plService")
public class SimplePlService extends GenericEntityService<PlEntity, String> implements PlService {

	@Autowired
    private PlDao plDao;

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected IDGenerator<String> getIDGenerator(PlEntity entity) {
    	String jsbh="000000000";
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public PlDao getDao() {
        return plDao;
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
	public String insert(PlEntity entity) {
		entity.setCreatetime(Calendar.getInstance().getTime());
		return super.insert(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public String saveOrUpdate(PlEntity entity) {
		return super.saveOrUpdate(entity);
	}

	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public PlEntity selectByPk(String pk) {
		return super.selectByPk(pk);
	}

	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<PlEntity> selectByPk(List<String> id) {
		return super.selectByPk(id);
	}

	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public PagerResult<PlEntity> selectPager(Entity arg0) {
		return super.selectPager(arg0);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	protected int updateByPk(PlEntity entity) {
		return super.updateByPk(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(List<PlEntity> data) {
		return super.updateByPk(data);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(String pk, PlEntity entity) {
		return super.updateByPk(pk, entity);
	}

    
}
