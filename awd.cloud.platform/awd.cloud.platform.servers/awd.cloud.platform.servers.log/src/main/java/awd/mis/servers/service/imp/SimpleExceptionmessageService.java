/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service.imp;

import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.service.simple.GenericEntityService;
import awd.mis.servers.dao.ExceptionmessageDao;
import awd.mis.servers.entity.ExceptionmessageEntity;
import awd.mis.servers.service.ExceptionmessageService;

import org.dromara.raincat.annotation.PropagationEnum;
import org.dromara.raincat.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("exceptionmessageService")
public class SimpleExceptionmessageService extends GenericEntityService<ExceptionmessageEntity, String> implements ExceptionmessageService {

    @Autowired
    private ExceptionmessageDao exceptionmessageDao;

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected IDGenerator<String> getIDGenerator(ExceptionmessageEntity entity) {
        String jsbh = "000000000";
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public ExceptionmessageDao getDao() {
        return exceptionmessageDao;
    }

}
