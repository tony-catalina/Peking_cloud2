/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http:www.rapid-framework.org.cn
 */

package awd.mis.servers.service.imp;

import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.utils.BeanUtils;
import awd.framework.common.utils.JSONUtil;
import awd.mis.servers.entity.DocumentEntity;
import awd.mis.servers.entity.DocumentdetailEntity;
import awd.mis.servers.model.DocumentdetailModel;
import awd.mis.servers.service.DocumentService;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import org.dromara.raincat.annotation.PropagationEnum;
import org.dromara.raincat.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.core.param.TermType;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.datasource.orm.core.param.QueryParam;
import awd.framework.common.service.simple.GenericEntityService;
import awd.mis.servers.entity.DocumentdetailEntity;
import awd.mis.servers.dao.DocumentdetailDao;
import awd.mis.servers.service.DocumentdetailService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("documentdetailService")
public class SimpleDocumentdetailService extends GenericEntityService<DocumentdetailEntity, String> implements DocumentdetailService {

    @Autowired
    private DocumentdetailDao documentdetailDao;

    @Autowired
    private DocumentService documentService;

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected IDGenerator<String> getIDGenerator(DocumentdetailEntity entity) {
        String jsbh = entity.getJsbh();
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public DocumentdetailDao getDao() {
        return documentdetailDao;
    }

    @Override
    @UseDataSource("write_ds")
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
    public String insert(DocumentdetailEntity entity) {
        return super.insert(entity);
    }

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public String saveOrUpdate(DocumentdetailEntity entity) {
        return super.saveOrUpdate(entity);
    }

    @Override
    @UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<DocumentdetailEntity> selectByPk(List<String> id) {
        return super.selectByPk(id);
    }

    @Override
    @UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
    public DocumentdetailEntity selectByPk(String pk) {
        return super.selectByPk(pk);
    }

    @Override
    @UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
    public PagerResult<DocumentdetailEntity> selectPager(Entity arg0) {
        return super.selectPager(arg0);
    }

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected int updateByPk(DocumentdetailEntity entity) {
        return super.updateByPk(entity);
    }

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public int updateByPk(List<DocumentdetailEntity> data) {
        return super.updateByPk(data);
    }

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public int updateByPk(String pk, DocumentdetailEntity entity) {
        return super.updateByPk(pk, entity);
    }


    @Override
    @UseDataSource("read_ds")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public PagerResult<DocumentdetailModel> selectDetail(QueryParamEntity entity) {
        PagerResult<DocumentdetailModel> pagerResult = new PagerResult();
        if (!entity.isPaging()) {
            pagerResult.setData(documentdetailDao.getDocumentDetail(entity));
            pagerResult.setTotal(pagerResult.getData().size());
            return pagerResult;
        }
        int total = documentdetailDao.count(entity);
        pagerResult.setTotal(total);
        if (total == 0) {
            pagerResult.setData(Collections.emptyList());
        } else {
            pagerResult.setData(documentdetailDao.getDocumentDetail(entity));
        }
        return pagerResult;
    }

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public ResponseMessage<String> updateDetail(DocumentdetailEntity date) {
		DocumentdetailEntity documentdeatil = this.selectByPk(date.getId());
		if(documentdeatil!=null) {
			documentdeatil.setHfnr(date.getHfnr());
			documentdeatil.setHfsj(date.getHfsj());
			documentdeatil.setFlag("1");
			this.updateByPk(documentdeatil.getId(), documentdeatil);
			System.err.println("this"+documentdeatil);
			QueryParamEntity param = new QueryParamEntity();
			param.and("uuid", TermType.eq, documentdeatil.getUuid());
			DocumentEntity doEntity = documentService.selectSingle(param);
			if(doEntity!=null) {
				doEntity.setHfbj("2");
				documentService.updateByPk(doEntity.getId(), doEntity);
			}else {
				return ResponseMessage.error("无数据");
			}
		}else {
			return ResponseMessage.error("无数据");
		}
		return ResponseMessage.ok("回复成功");
	}
}
