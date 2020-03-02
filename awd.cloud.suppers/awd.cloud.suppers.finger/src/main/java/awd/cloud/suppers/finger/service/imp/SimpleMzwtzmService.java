/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.suppers.finger.service.imp;

import static awd.cloud.suppers.finger.tools.CacheUtils.CACHE_KEY_FINGER;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;

import awd.cloud.suppers.finger.dao.MzwtzmDao;
import awd.cloud.suppers.finger.entity.MzwtzmEntity;
import awd.cloud.suppers.finger.service.MzwtzmService;
import awd.cloud.suppers.finger.tools.CacheUtils;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.simple.GenericEntityService;;

@Service("mzwtzmService")
public class SimpleMzwtzmService extends GenericEntityService<MzwtzmEntity, String> implements MzwtzmService {

	@Autowired
    private MzwtzmDao mzwtzmDao;
	
    @Override
    protected IDGenerator<String> getIDGenerator(MzwtzmEntity entity) {
    	String jsbh=entity.getJsbh();
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public MzwtzmDao getDao() {
        return mzwtzmDao;
    }

    
	@Override
	@UseDataSource("write_ds")
	@Transactional
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
	@Transactional
	public String insert(MzwtzmEntity entity) {
		return super.insert(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional
	public String saveOrUpdate(MzwtzmEntity entity) {
		return super.saveOrUpdate(entity);
	}

	@Override
	@UseDataSource("read_ds")
	public MzwtzmEntity selectByPk(String pk) {
		return super.selectByPk(pk);
	}

	@Override
	@UseDataSource("read_ds")
	public List<MzwtzmEntity> selectByPk(List<String> id) {
		return super.selectByPk(id);
	}

	@Override
	@UseDataSource("read_ds")
	public PagerResult<MzwtzmEntity> selectPager(Entity arg0) {
		return super.selectPager(arg0);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional
	protected int updateByPk(MzwtzmEntity entity) {
		return super.updateByPk(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional
	public int updateByPk(List<MzwtzmEntity> data) {
		return super.updateByPk(data);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional
	public int updateByPk(String pk, MzwtzmEntity entity) {
		return super.updateByPk(pk, entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional
	public String saveMzwtzm(MzwtzmEntity mzwtzmEntity) {
		this.saveOrUpdate(mzwtzmEntity);
		return "1";
	}

	@Override
	@UseDataSource("read_ds")
	public MzwtzmEntity getEntyByZjh(String zjh) {
		QueryParamEntity query = new QueryParamEntity();
		query.and("rybh", zjh);
		query.and("state", "R2");
		return this.selectSingle(query);
	}   
	
	@Override
	@UseDataSource("read_ds")
	public List<MzwtzmEntity> queryMzwtzms(String zjh) {
		QueryParamEntity query = new QueryParamEntity();
		query.and("rybh", zjh);
		query.and("state", "R2");
		return this.select(query);
	}
	
	@Override
	@UseDataSource("read_ds")
	public List<MzwtzmEntity> getEntyListByJsbh(String jsbh) {
		QueryParamEntity query = new QueryParamEntity();
		query.and("jsbh", jsbh);
		query.and("state", "R2");
		List<MzwtzmEntity> list = this.select(query);
		for (int i = 0; i < list.size(); i++) {
			System.err.println("zjh"+i+"---"+list.get(i).getRybh());
		}
		return this.select(query);
	}
	
	@Override
	@UseDataSource("read_ds")
	public List<?> getParamListByJsbh(String jsbh) {
		List<?> list = mzwtzmDao.selectParam(jsbh);
		System.err.println("list---------"+list);
		return list;
	}

	@Override
	@UseDataSource("read_ds")
	public int deleteByZjh(String zjh) { 
		MzwtzmEntity entity = this.getEntyByZjh(zjh);
		return this.deleteByPk(entity.getId());
	}
	
	
	@Override
	@UseDataSource("read_ds")
	public boolean entyIsExist(String zjh) {
		int count = mzwtzmDao.selectCount(zjh);
		if (count > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void cacheMzwtzm() {
		try {
			CacheUtils.get().removePattern(CACHE_KEY_FINGER+"_mzwtzm_*");
			QueryParamEntity query = new QueryParamEntity();
			query.and("state", "R2");
            List<MzwtzmEntity> li = this.select(query);
            li.forEach(p -> CacheUtils.get().set(CACHE_KEY_FINGER +"_mzwtzm_" + p.getRybh() , JSONObject.toJSONString(p)));
        } catch (Exception e) {
            e.printStackTrace();
        }
	}    
    
}
