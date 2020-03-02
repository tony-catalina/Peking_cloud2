/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service.imp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.dromara.raincat.annotation.PropagationEnum;
import org.dromara.raincat.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.service.simple.GenericEntityService;
import awd.framework.common.utils.ApplicationContextHolder;
import awd.framework.expands.redisclient.RedisUtils;
import awd.mis.servers.dao.ClassficDao;
import awd.mis.servers.entity.ClassficEntity;
import awd.mis.servers.service.ClassficService;
import awd.mis.servers.tools.CacheUtils;

@Service("classficService")
public class SimpleClassficService extends GenericEntityService<ClassficEntity, String>  implements ClassficService {

	@Autowired
    private ClassficDao classficDao;

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected IDGenerator<String> getIDGenerator(ClassficEntity entity) {
        return () -> {
            return getSEQUID("");
        };
    }
	

    @Override
    public ClassficDao getDao() {
        return classficDao;
    }

    @Override
    @UseDataSource("read_ds")
    public List<ClassficEntity> selectAllChildNode(String lx, String parentid) {
        List<ClassficEntity> listAll = new ArrayList<>();
        List<ClassficEntity> rootlist = classficDao.selectChildNode(lx, parentid);
        if (rootlist != null && rootlist.size() > 0) {
            for (ClassficEntity classficEntity : rootlist) {
                listAll.addAll(selectAllChildNode(lx, classficEntity.getClassid()));
            }
        } else {
            listAll.add(classficDao.selectByClassID(parentid));
        }
        return listAll;
    }

    @Override
    @UseDataSource("read_ds")
    public List<ClassficEntity> selectChildNode(String lx, String parentid) {
        return classficDao.selectChildNode(lx, parentid);
    }

	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void cached() {
		List<ClassficEntity> listAll=select();
		for (ClassficEntity classficEntity : listAll) {
			String key=CacheUtils.CACHE_CLASSFIC +classficEntity.getLx().toUpperCase()+"_"+ classficEntity.getClassid().toUpperCase() ;
			String value = JSONObject.toJSONString(classficEntity); 
			CacheUtils.get().set(key, value); 	
		}		
	}

    @Override
    @UseDataSource("read_ds")
    public List<ClassficEntity> selectByParentIdandName(String parentid, String name) {
        return classficDao.selectByParentIdandName(parentid, name);
    }


	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public String insert(ClassficEntity entity) {
		entity.setCreatetime(Calendar.getInstance().getTime());
		return super.insert(entity);
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
    public String saveOrUpdate(ClassficEntity entity) {
        return super.saveOrUpdate(entity);
    }
	

    @Override
    @UseDataSource("read_ds")
    public List<ClassficEntity> selectByPk(List<String> id) {
        return super.selectByPk(id);
    }

    @Override
    @UseDataSource("read_ds")
    public ClassficEntity selectByPk(String pk) {
        return super.selectByPk(pk);
    }

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(String pk, ClassficEntity entity) {
		entity.setUpdatetime(Calendar.getInstance().getTime());
		return super.updateByPk(pk, entity);
	}   
	
	
}
