/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http:www.rapid-framework.org.cn
 */

package awd.mis.servers.service.imp;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.datasource.orm.core.param.QueryParam;
import awd.framework.common.datasource.orm.core.param.TermType;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.mis.servers.api.ManagerService;
import awd.mis.servers.entity.DocumentEntity;
import awd.mis.servers.entity.DocumentdetailEntity;
import awd.mis.servers.model.DocumentdetailModel;
import awd.mis.servers.model.UserinfoModel;
import awd.mis.servers.model.UserroleModel;
import awd.mis.servers.service.DocumentdetailService;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import org.dromara.raincat.annotation.PropagationEnum;
import org.dromara.raincat.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.service.simple.GenericEntityService;
import awd.mis.servers.entity.DocumentEntity;
import awd.mis.servers.dao.DocumentDao;
import awd.mis.servers.service.DocumentService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service("documentService")
public class SimpleDocumentService extends GenericEntityService<DocumentEntity, String> implements DocumentService {

    @Autowired
    private DocumentDao documentDao;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private DocumentdetailService documentdetailService;
    
    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected IDGenerator<String> getIDGenerator(DocumentEntity entity) {
        String jsbh = "000000000";
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public DocumentDao getDao() {
        return documentDao;
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
    public String insert(DocumentEntity entity) {
        entity.setState("R2");
        entity.setCreatetime(Calendar.getInstance().getTime());
        ResponseMessage<PagerResult<UserinfoModel>> userResult = managerService.getUsersToDocument(entity.getCsdw(), "8,9");
        List<UserinfoModel> userInfoModelList = userResult.getResult().getData();
        logger.info("获取到用户信息：{}", JSONObject.toJSONString(userInfoModelList));
        int n = 0;
        for (UserinfoModel user : userInfoModelList) {
            DocumentdetailEntity documentdetailEntity = new DocumentdetailEntity();
            documentdetailEntity.setHfr(user.getLoginname());
            documentdetailEntity.setHfrjh(user.getJh());
            documentdetailEntity.setUuid(entity.getUuid());
            documentdetailEntity.setFlag("0");
            documentdetailEntity.setJsbh(user.getJsbh());
            documentdetailEntity.setCreatetime(Calendar.getInstance().getTime());
            documentdetailEntity.setCreator(entity.getCreator());
            documentdetailService.insert(documentdetailEntity);
            n++;
        }
        System.err.println(n);
        return super.insert(entity);
    }

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public String saveOrUpdate(DocumentEntity entity) {
        return super.saveOrUpdate(entity);
    }

    @Override
    @UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<DocumentEntity> selectByPk(List<String> id) {
        return super.selectByPk(id);
    }

    @Override
    @UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
    public DocumentEntity selectByPk(String pk) {
        return super.selectByPk(pk);
    }

    @Override
    @UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
    public PagerResult<DocumentEntity> selectPager(Entity arg0) {
        return super.selectPager(arg0);
    }

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected int updateByPk(DocumentEntity entity) {
        return super.updateByPk(entity);
    }

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public int updateByPk(List<DocumentEntity> data) {
        return super.updateByPk(data);
    }

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public int updateByPk(String pk, DocumentEntity entity) {
        return super.updateByPk(pk, entity);
    }

}
