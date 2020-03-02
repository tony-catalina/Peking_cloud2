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

import awd.cloud.suppers.finger.dao.BzwtzmDao;
import awd.cloud.suppers.finger.entity.BzwtzmEntity;
import awd.cloud.suppers.finger.service.BzwtzmService;
import awd.cloud.suppers.finger.tools.CacheUtils;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.simple.GenericEntityService;

@Service("bzwtzmService")
public class SimpleBzwtzmService extends GenericEntityService<BzwtzmEntity, String> implements BzwtzmService {

	@Autowired
    private BzwtzmDao bzwtzmDao;
	
    @Override
    protected IDGenerator<String> getIDGenerator(BzwtzmEntity entity) {
    	String jsbh=entity.getJsbh();
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public BzwtzmDao getDao() {
        return bzwtzmDao;
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
	public String insert(BzwtzmEntity entity) {
		return super.insert(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional
	public String saveOrUpdate(BzwtzmEntity entity) {
		return super.saveOrUpdate(entity);
	}

	@Override
	@UseDataSource("read_ds")
	public BzwtzmEntity selectByPk(String pk) {
		return super.selectByPk(pk);
	}

	@Override
	@UseDataSource("read_ds")
	public List<BzwtzmEntity> selectByPk(List<String> id) {
		return super.selectByPk(id);
	}

	@Override
	@UseDataSource("read_ds")
	public PagerResult<BzwtzmEntity> selectPager(Entity arg0) {
		return super.selectPager(arg0);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional
	protected int updateByPk(BzwtzmEntity entity) {
		return super.updateByPk(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional
	public int updateByPk(List<BzwtzmEntity> data) {
		return super.updateByPk(data);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional
	public int updateByPk(String pk, BzwtzmEntity entity) {
		return super.updateByPk(pk, entity);
	}

	@UseDataSource("write_ds")
	@Transactional
	public int fingerfeatureRegist(BzwtzmEntity entity) {
    	this.saveOrUpdate(entity);
    	return 1;
    }
    
	@Override
	@UseDataSource("write_ds")
	@Transactional
	public int deleteByRybh(String rybh) { 
		BzwtzmEntity entity = this.getEntyByRybh(rybh,"R2");
		return this.deleteByPk(entity.getId());
	}
	
	@UseDataSource("read_ds")
    public BzwtzmEntity getEntyByRybh(String rybh, String state) {
    	QueryParamEntity q = new QueryParamEntity(); 
    	q.and("rybh", rybh);
    	q.and("state", state);
    	BzwtzmEntity entity = this.selectSingle(q);
    	System.err.println("entity---------"+entity);
    	return entity;
    }
    
	@UseDataSource("read_ds")
    public List<BzwtzmEntity> getEntyListByRybh(String rybh , String state) {
    	QueryParamEntity q = new QueryParamEntity(); 
    	q.and("rybh", rybh);
    	q.and("state", state);
    	List<BzwtzmEntity> entityList = this.select(q);
    	return entityList;
    }
	
	@UseDataSource("read_ds")
	public List<Map<String, Object>> getParamListByJsbh(String jsbh) {
		List<Map<String, Object>> list = bzwtzmDao.selectParam(jsbh);
		return list;
	}

	@UseDataSource("read_ds")
    public List<BzwtzmEntity> getEntyListByJsbh(String jsbh) {
    	QueryParamEntity q = new QueryParamEntity(); 
    	q.and("jsbh", jsbh);
    	q.and("state", "R2");
    	List<BzwtzmEntity> entityList = this.select(q);
    	return entityList;
    }
	
	@UseDataSource("read_ds")
	public boolean entyIsExist(String rybh) {
		int count = bzwtzmDao.selectCount(rybh);
		if (count > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public void cacheBzwtzm() {
		try {
			CacheUtils.get().removePattern(CACHE_KEY_FINGER+"_bzwtzm_*");
			QueryParamEntity query = new QueryParamEntity();
			query.and("state", "R2");
            List<BzwtzmEntity> li = this.select(query);
            System.err.println("加载指纹特征码缓存");
            li.forEach(p -> CacheUtils.get().set(CACHE_KEY_FINGER +"_bzwtzm_" + p.getRybh() , JSONObject.toJSONString(p)));
        } catch (Exception e) {
            e.printStackTrace();
        }		
	}
}
