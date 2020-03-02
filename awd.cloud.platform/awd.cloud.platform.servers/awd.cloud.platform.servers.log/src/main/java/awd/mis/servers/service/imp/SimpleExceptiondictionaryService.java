/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http:www.rapid-framework.org.cn
 */

package awd.mis.servers.service.imp;

import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;

import org.dromara.raincat.annotation.PropagationEnum;
import org.dromara.raincat.annotation.TxTransaction;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.service.simple.GenericEntityService;
import awd.mis.servers.entity.ExceptiondictionaryEntity;
import awd.mis.servers.dao.ExceptiondictionaryDao;
import awd.mis.servers.service.ExceptiondictionaryService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service("exceptiondictionaryService")
public class SimpleExceptiondictionaryService extends GenericEntityService<ExceptiondictionaryEntity, String> implements ExceptiondictionaryService {

    @Autowired
    private ExceptiondictionaryDao exceptiondictionaryDao;

    @Override
    @UseDataSource("write_ds")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected IDGenerator<String> getIDGenerator(ExceptiondictionaryEntity entity) {
        String jsbh = entity.getJsbh();
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public ExceptiondictionaryDao getDao() {
        return exceptiondictionaryDao;
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
    public String insert(ExceptiondictionaryEntity entity) {
        if (entity.getCreatetime() == null) {
            entity.setCreatetime(Calendar.getInstance().getTime());
        }
        return super.insert(entity);
    }

    @Override
    @UseDataSource("write_ds")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public String saveOrUpdate(ExceptiondictionaryEntity entity) {
        return super.saveOrUpdate(entity);
    }

    @Override
    @UseDataSource("read_ds")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<ExceptiondictionaryEntity> selectByPk(List<String> id) {
        return super.selectByPk(id);
    }

    @Override
    @UseDataSource("read_ds")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public ExceptiondictionaryEntity selectByPk(String pk) {
        return super.selectByPk(pk);
    }

    @Override
    @UseDataSource("read_ds")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public PagerResult<ExceptiondictionaryEntity> selectPager(Entity arg0) {
        return super.selectPager(arg0);
    }

    @Override
    @UseDataSource("write_ds")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected int updateByPk(ExceptiondictionaryEntity entity) {
        return super.updateByPk(entity);
    }

    @Override
    @UseDataSource("write_ds")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public int updateByPk(List<ExceptiondictionaryEntity> data) {
        return super.updateByPk(data);
    }

    @Override
    @UseDataSource("write_ds")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public int updateByPk(String pk, ExceptiondictionaryEntity entity) {
        return super.updateByPk(pk, entity);
    }


}
