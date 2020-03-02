/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http:www.rapid-framework.org.cn
 */

package awd.mis.servers.service.imp;

import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.service.simple.GenericEntityService;
import awd.mis.servers.dao.DocumentfileDao;
import awd.mis.servers.entity.DocumentfileEntity;
import awd.mis.servers.entity.DocumentfileEntity;
import awd.mis.servers.service.DocumentfileService;
import awd.mis.servers.utils.FastDFSClient;
import awd.mis.servers.utils.FastDFSFile;

import org.dromara.raincat.annotation.PropagationEnum;
import org.dromara.raincat.annotation.TxTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service("documentfileService")
public class SimpleDocumentfileService extends GenericEntityService<DocumentfileEntity, String> implements DocumentfileService {

    @Autowired
    private DocumentfileDao documentfileDao;

    Logger logger = LoggerFactory.getLogger(DocumentfileService.class);
    
    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected IDGenerator<String> getIDGenerator(DocumentfileEntity entity) {
        String jsbh = "000000000";
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public DocumentfileDao getDao() {
        return documentfileDao;
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
    public String insert(DocumentfileEntity entity) {
        return super.insert(entity);
    }

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public String saveOrUpdate(DocumentfileEntity entity) {
        return super.saveOrUpdate(entity);
    }

    @Override
    @UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<DocumentfileEntity> selectByPk(List<String> id) {
        return super.selectByPk(id);
    }

    @Override
    @UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
    public DocumentfileEntity selectByPk(String pk) {
        return super.selectByPk(pk);
    }

    @Override
    @UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
    public PagerResult<DocumentfileEntity> selectPager(Entity arg0) {
        return super.selectPager(arg0);
    }

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected int updateByPk(DocumentfileEntity entity) {
        return super.updateByPk(entity);
    }

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public int updateByPk(List<DocumentfileEntity> data) {
        return super.updateByPk(data);
    }

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public int updateByPk(String pk, DocumentfileEntity entity) {
        return super.updateByPk(pk, entity);
    }


    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public String saveOrUpdate(DocumentfileEntity documentfileEntity, FastDFSFile fastDFSFile) {

        String[] fileAbsolutePath = {};
        String path = "";
        try {
            fileAbsolutePath = FastDFSClient.upload(fastDFSFile);
            if (fileAbsolutePath != null && fileAbsolutePath.length > 0) {
                path = FastDFSClient.getTrackerUrl() + fileAbsolutePath[0] + "/" + fileAbsolutePath[1];
                documentfileEntity.setFilepath(path);
                this.saveOrUpdate(documentfileEntity);
            } else {
                logger.error("upload file failed,please upload again!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;

    }
}
