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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.service.simple.GenericEntityService;
import awd.framework.common.utils.StringUtils;
import awd.mis.servers.dao.DictionaryDao;
import awd.mis.servers.entity.DictionaryEntity;
import awd.mis.servers.service.DictionaryService;
import awd.mis.servers.tools.CacheUtils;

@Service("dictionaryService")
public class SimpleDictionaryService extends GenericEntityService<DictionaryEntity, String> implements DictionaryService {

	@Autowired
    private DictionaryDao dictionaryDao;

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected IDGenerator<String> getIDGenerator(DictionaryEntity entity) {
        return () -> {
            return getSEQUID("");
        };
    }

    @Override
    public DictionaryDao getDao() {
        return dictionaryDao;
    }

	

	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<Map<String, Object>> getAllFields() {
		return dictionaryDao.getAllFields();
	}
	
	
	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void cached() {
		List<Map<String, Object>> fieldtypeList=getAllFields();
		String fieldstr="";
		int num=0;
		for (Map<String, Object> field : fieldtypeList) {
			if(field.get("fieldname")!=null) {
				fieldstr+=field.get("fieldname").toString()+",";		
				try {
					List<DictionaryEntity> dictList=getByField(field.get("fieldname").toString());
					num=num+dictList.size();
					for (DictionaryEntity dictionaryEntity : dictList) {	
							CacheUtils.get().saveDictionary(dictionaryEntity);	
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}			
		}
		CacheUtils.get().saveDictionaryFild(fieldstr);
		logger.info("------------------------------字典缓存数量--------------------------------------------------");
		logger.info(String.valueOf(num));
	}


	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public String insert(DictionaryEntity entity) {
		entity.setCreatetime(Calendar.getInstance().getTime());
		this.refreshDictionary(entity);
		return super.insert(entity);
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
    public String saveOrUpdate(DictionaryEntity entity) {
        return super.saveOrUpdate(entity);
    }
	

    @Override
    @UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<DictionaryEntity> selectByPk(List<String> id) {
        return super.selectByPk(id);
    }
	

    @Override
    @UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
    public DictionaryEntity selectByPk(String pk) {
        return super.selectByPk(pk);
    }

    @Override
    @UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
    public PagerResult<DictionaryEntity> selectPager(Entity arg0) {
        return super.selectPager(arg0);
    }

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(String pk, DictionaryEntity entity) {
		entity.setUpdatetime(Calendar.getInstance().getTime());
		//首先根据id获取原始值
		DictionaryEntity dic = this.selectByPk(pk);
		if(!StringUtils.isNullOrEmpty(dic)) {
			//删除原始缓存
			CacheUtils.get().del("cloud_dictionary"+dic.getFieldname().toUpperCase()+"_"+dic.getCode());
		}
		//加载新缓存
		this.refreshDictionary(entity);
		return super.updateByPk(pk, entity);
	}  

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected int updateByPk(DictionaryEntity entity) {
        return super.updateByPk(entity);
    }

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public int updateByPk(List<DictionaryEntity> data) {
        return super.updateByPk(data);
    }

    @UseDataSource("read_ds")
   	@Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public DictionaryEntity getDictionaryByFieldCode(String field, String code) {
        return dictionaryDao.findByFieldCode(field, code);
    }

    @Override
    @UseDataSource("read_ds")
   	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<DictionaryEntity> getByField(String filed) {
		return dictionaryDao.findByField(filed);
	}
    
    public void refreshDictionary(DictionaryEntity entity) {
    	System.err.println("******开始缓存新加入字典***********");
		//保存时刷新缓存
		//首先获取缓存
		String cache=CacheUtils.get().getDictionary(entity.getFieldname(), entity.getCode());
		//如果已有该缓存则清除
		if(!StringUtils.isNullOrEmpty(cache) ) {
			CacheUtils.get().del("cloud_dictionary"+entity.getFieldname().toUpperCase()+"_"+entity.getCode());
		}
		//执行缓存
		CacheUtils.get().saveDictionary(entity);
    }

	@Override
	public int deleteByPk(String pk) {
		// TODO Auto-generated method stub
		//首先根据id获取原始值
		DictionaryEntity dic = this.selectByPk(pk);
		if(!StringUtils.isNullOrEmpty(dic)) {
			//删除原始缓存
			CacheUtils.get().del("cloud_dictionary"+dic.getFieldname().toUpperCase()+"_"+dic.getCode());
		}
		return super.deleteByPk(pk);
	}
    
    

}
