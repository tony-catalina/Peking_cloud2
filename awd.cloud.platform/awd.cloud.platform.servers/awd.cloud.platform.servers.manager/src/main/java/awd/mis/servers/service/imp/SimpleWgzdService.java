/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service.imp;

import java.util.Calendar;
import java.util.List;

import org.dromara.raincat.annotation.PropagationEnum;
import org.dromara.raincat.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.service.simple.GenericEntityService;
import awd.framework.common.utils.ApplicationContextHolder;
import awd.framework.common.utils.StringUtils;
import awd.framework.expands.redisclient.RedisUtils;
import awd.mis.servers.dao.WgzdDao;
import awd.mis.servers.entity.DictionaryEntity;
import awd.mis.servers.entity.WgzdEntity;
import awd.mis.servers.service.WgzdService;
import awd.mis.servers.tools.CacheUtils;

@Service("wgzdService")
public class SimpleWgzdService extends GenericEntityService<WgzdEntity, String> implements WgzdService {

	@Autowired
    private WgzdDao wgzdDao;

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected IDGenerator<String> getIDGenerator(WgzdEntity entity) {
        return () -> {
            return getSEQUID("");
        };
    }

    @Override
    public WgzdDao getDao() {
        return wgzdDao;
    }

	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void cached() {
		List<WgzdEntity> listAll=select();
		for (WgzdEntity wgzdEntity : listAll) {
			String key=CacheUtils.CACHE_WGZD + wgzdEntity.getCode().toUpperCase() ;
			String value = JSONObject.toJSONString(wgzdEntity);
			CacheUtils.get().set(key, value); 
		}
		
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int deleteByPk(String pk) {
		//首先根据id获取原始值
		WgzdEntity dic = this.selectByPk(pk);
		if(!StringUtils.isNullOrEmpty(dic)) {
			//删除原始缓存
			CacheUtils.get().del("cloud_wgzd"+dic.getCode());
		}
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
	public String insert(WgzdEntity entity) {
		entity.setCreatetime(Calendar.getInstance().getTime());
		this.refreshDictionary(entity);
		return super.insert(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public String saveOrUpdate(WgzdEntity entity) {
		return super.saveOrUpdate(entity);
	}

	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<WgzdEntity> selectByPk(List<String> id) {
		return super.selectByPk(id);
	}
	
	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public WgzdEntity selectByPk(String pk) {
		return super.selectByPk(pk);
	}
	
	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public PagerResult<WgzdEntity> selectPager(Entity arg0) {
		return super.selectPager(arg0);
	}
	
	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(List<WgzdEntity> data) {
		return super.updateByPk(data);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(String pk, WgzdEntity entity) {
		entity.setUpdatetime(Calendar.getInstance().getTime());
		//首先根据id获取原始值
		WgzdEntity dic = this.selectByPk(pk);
		if(!StringUtils.isNullOrEmpty(dic)) {
			//删除原始缓存
			CacheUtils.get().del("cloud_wgzd"+dic.getCode());
		}
		this.refreshDictionary(entity);
		return super.updateByPk(pk, entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	protected int updateByPk(WgzdEntity entity) {
		return super.updateByPk(entity);
	} 
    
	public void refreshDictionary(WgzdEntity entity) {
    	System.err.println("******开始缓存新加入字典***********");
		//保存时刷新缓存
		//首先获取缓存
		Object cache=CacheUtils.get().getKey("cloud_wgzd"+entity.getCode());
		//如果已有该缓存则清除
		if(!StringUtils.isNullOrEmpty(cache) ) {
			CacheUtils.get().del("cloud_wgzd"+entity.getCode());
		}
		//执行缓存
		CacheUtils.get().set("cloud_wgzd"+entity.getCode(), JSONObject.toJSONString(entity));
    }

    
    
}
