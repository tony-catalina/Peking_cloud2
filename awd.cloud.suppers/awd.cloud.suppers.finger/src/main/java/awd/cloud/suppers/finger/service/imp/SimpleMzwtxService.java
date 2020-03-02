/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.suppers.finger.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;

import awd.cloud.suppers.finger.dao.MzwtxDao;
import awd.cloud.suppers.finger.entity.MzwtxEntity;
import awd.cloud.suppers.finger.entity.MzwtzmEntity;
import awd.cloud.suppers.finger.service.MzwtxService;
import awd.cloud.suppers.finger.service.MzwtzmService;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.simple.GenericEntityService;

@Service("mzwtxService")
public class SimpleMzwtxService extends GenericEntityService<MzwtxEntity, String> implements MzwtxService {

	@Autowired
    private MzwtxDao mzwtxDao;

	@Autowired
	private MzwtzmService mzwtzmService;
	
    @Override
    protected IDGenerator<String> getIDGenerator(MzwtxEntity entity) {
    	String jsbh=entity.getJsbh();
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public MzwtxDao getDao() {
        return mzwtxDao;
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
	public String insert(MzwtxEntity entity) {
		return super.insert(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional
	public String saveOrUpdate(MzwtxEntity entity) {
		return super.saveOrUpdate(entity);
	}

	@Override
	@UseDataSource("read_ds")
	public MzwtxEntity selectByPk(String pk) {
		return super.selectByPk(pk);
	}

	@Override
	@UseDataSource("read_ds")
	public List<MzwtxEntity> selectByPk(List<String> id) {
		return super.selectByPk(id);
	}

	@Override
	@UseDataSource("read_ds")
	public PagerResult<MzwtxEntity> selectPager(Entity arg0) {
		return super.selectPager(arg0);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional
	protected int updateByPk(MzwtxEntity entity) {
		return super.updateByPk(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional
	public int updateByPk(List<MzwtxEntity> data) {
		return super.updateByPk(data);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional
	public int updateByPk(String pk, MzwtxEntity entity) {
		return super.updateByPk(pk, entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional
	public String saveMzwtx(MzwtxEntity mzwtxEntity) {
		this.saveOrUpdate(mzwtxEntity);
		return "1";
	}
	
	@Override
	@UseDataSource("write_ds")
	@Transactional
	public String mfingerRegistWithYs(MzwtxEntity mzwtxEntity,MzwtzmEntity mzwtzmEntity) {
		String zwtxStr = this.saveMzwtx(mzwtxEntity);
		String zwtzmStr = mzwtzmService.saveMzwtzm(mzwtzmEntity);
		if (zwtxStr == "1" && zwtzmStr == "1") {
			return "1";
		}else {
			return "0";
		}
	}

	
	@Override
	@UseDataSource("read_ds")
	public MzwtxEntity getEntyByZjh(String zjh) {
		QueryParamEntity query = new QueryParamEntity();
		query.and("rybh", zjh);
		query.and("state", "R2");
		return this.selectSingle(query);
	}    
	
	@Override
	@UseDataSource("write_ds")
	@Transactional
	public int deleteByZjh(String zjh) { 
		MzwtxEntity entity = this.getEntyByZjh(zjh);
		return this.deleteByPk(entity.getId());
	}
	
	
	@Override
	@UseDataSource("read_ds")
	public List<MzwtxEntity> queryByZjh(String zjh) {
		QueryParamEntity query = new QueryParamEntity();
		query.and("rybh", zjh);
		query.and("state", "R2");
		return this.select(query);
	}    
 
	@UseDataSource("write_ds")
	@Transactional
	public String deleteZwtxAndTzmByZjh(String zjh) {
		int num1 = this.deleteByZjh(zjh);
		System.err.println("---------删除民警  " + zjh + " 指纹图像 --------");
		int num2 = mzwtzmService.deleteByZjh(zjh);
		System.err.println("---------删除民警  " + zjh + " 指纹特征码 --------");
		if (num1 == 1 && num2 == 1) {
			return "1";
		}
		return "0";
	}
	
	@UseDataSource("write_ds")
	@Transactional
	public String updateZwtxAndTzmByZjh(String zjh ,String state) {
		
		MzwtxEntity mzwtxEntity = this.getEntyByZjh(zjh);
		MzwtzmEntity mzwtzmEntity = mzwtzmService.getEntyByZjh(zjh);
		
		mzwtxEntity.setState(state);
		mzwtzmEntity.setState(state);
		
		String num1 = this.saveOrUpdate(mzwtxEntity);
		System.err.println("--------------民警 " + zjh + " 出所更改图像state为 " + state + "---------");
		String num2 = mzwtzmService.saveOrUpdate(mzwtzmEntity);
		System.err.println("--------------民警  " + zjh + " 出所更改特征码state为 " + state + "---------");
		
		if (num1 == mzwtxEntity.getId() && num2 == mzwtzmEntity.getId()) {
			return "1";
		}
		return "0";
	}
	
	@UseDataSource("read_ds")
	public Map<String, Object> selectMjZwCode(String zjh ,String state1, String state2) {
		MzwtxEntity entity = mzwtxDao.selectMjZwCode(zjh, state1, state2);
		Map<String, Object> map = Maps.newHashMap();
		map.put("zw1", entity.getZwtxxh11());
		map.put("zw2", entity.getZwtxxh21());
		return map;
	}
	
	@UseDataSource("read_ds")
	public boolean entyIsExist(String zjh) {
		int count = mzwtxDao.selectCount(zjh);
		if (count > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	
}
