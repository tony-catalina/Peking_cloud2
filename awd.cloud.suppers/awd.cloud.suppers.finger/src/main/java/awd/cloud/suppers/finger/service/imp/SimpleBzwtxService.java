/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.suppers.finger.service.imp;

import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;

import awd.cloud.suppers.finger.dao.BzwtxDao;
import awd.cloud.suppers.finger.entity.BzwtxEntity;
import awd.cloud.suppers.finger.entity.BzwtzmEntity;
import awd.cloud.suppers.finger.service.BzwtxService;
import awd.cloud.suppers.finger.service.BzwtzmService;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.simple.GenericEntityService;

@Service("bzwtxService")
public class SimpleBzwtxService extends GenericEntityService<BzwtxEntity, String> implements BzwtxService {

	@Autowired
    private BzwtxDao bzwtxDao;
	
	@Autowired
	private BzwtzmService bzwtzmService;

    @Override
    protected IDGenerator<String> getIDGenerator(BzwtxEntity entity) {
    	String jsbh=entity.getJsbh();
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public BzwtxDao getDao() {
        return bzwtxDao;
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
		String SEQUID = super.getSEQUID(jsbh);
		System.err.println("SEQUID---"+SEQUID);
		return super.getSEQUID(jsbh);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional
	public String insert(BzwtxEntity entity) {
		return super.insert(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional
	public String saveOrUpdate(BzwtxEntity entity) {
		return super.saveOrUpdate(entity);
	}

	@Override
	@UseDataSource("read_ds")
	public List<BzwtxEntity> selectByPk(List<String> id) {
		return super.selectByPk(id);
	}

	@Override
	@UseDataSource("read_ds")
	public BzwtxEntity selectByPk(String pk) {
		return super.selectByPk(pk);
	}

	@Override
	@UseDataSource("read_ds")
	public PagerResult<BzwtxEntity> selectPager(Entity arg0) {
		return super.selectPager(arg0);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional
	protected int updateByPk(BzwtxEntity entity) {
		return super.updateByPk(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional
	public int updateByPk(List<BzwtxEntity> data) {
		return super.updateByPk(data);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional
	public int updateByPk(String pk, BzwtxEntity entity) {
		return super.updateByPk(pk, entity);
	}

	@UseDataSource("write_ds")
	@Transactional
	public int fingerRegist(BzwtxEntity entity) {
    	this.saveOrUpdate(entity);
    	return 1;
    }
	
	@UseDataSource("write_ds")
	@Transactional
	public int fingerRegistWithYs(BzwtxEntity entity1,BzwtzmEntity entity2) {//使用一所指纹仪,直接保存指纹图像和特征码
		int zwtxNum = this.fingerRegist(entity1);
		int zwtzmNum = bzwtzmService.fingerfeatureRegist(entity2);
		if (zwtxNum == 1 && zwtzmNum == 1) {
			return 1;
		}else {
			return 0;
		}
	}
    
	@UseDataSource("read_ds")
    public BzwtxEntity getEntyByRybh(String rybh, String state) {
    	QueryParamEntity q = new QueryParamEntity(); 
    	q.and("rybh", rybh);
    	q.and("state", state);
    	return this.selectSingle(q);
    }
    
	@UseDataSource("read_ds")
    public List<BzwtxEntity> getEntyListByRybh(String rybh, String state) {
    	QueryParamEntity q = new QueryParamEntity(); 
    	q.and("rybh", rybh);
    	q.and("state", state);
    	List<BzwtxEntity> entityList = this.select(q); 
    	return entityList;
    }
	
	@UseDataSource("write_ds")
	@Transactional
	public int deleteByRybh(String rybh) {
		BzwtxEntity entity = this.getEntyByRybh(rybh,"R2");
		return this.deleteByPk(entity.getId());
	}
	
	@UseDataSource("write_ds")
	@Transactional
	public String deleteZwtxAndTzmByRybh(String rybh) {
		int num1 = this.deleteByRybh(rybh);
		System.err.println("---------删除 " + rybh + " 指纹图像 --------");
		int num2 = bzwtzmService.deleteByRybh(rybh);
		System.err.println("---------删除 " + rybh + " 指纹特征码 --------");
		if (num1 == 1 && num2 == 1) {
			return "1";
		}
		return "0";
	}
	
	@UseDataSource("write_ds")
	@Transactional
	public String updateZwtxAndTzmByRybh(String rybh ,String old_state,String new_state) {
		
		BzwtxEntity bzwtxEntity = this.getEntyByRybh(rybh,old_state);
		BzwtzmEntity bzwtzmEntity = bzwtzmService.getEntyByRybh(rybh,old_state);
		
		bzwtxEntity.setState(new_state);
		bzwtzmEntity.setState(new_state);
		
		String num1 = this.saveOrUpdate(bzwtxEntity);
		System.err.println("--------------人员 " + rybh + " 出所更改图像state为 " + new_state + "---------");
		String num2 = bzwtzmService.saveOrUpdate(bzwtzmEntity);
		System.err.println("--------------人员 " + rybh + " 出所更改特征码state为 " + new_state + "---------");
		
		if (num1 == bzwtxEntity.getId() && num2 == bzwtzmEntity.getId()) {
			return "1";
		}
		return "0";
	}
	
	@UseDataSource("read_ds")
	public Map<String, Object> selectZwCode(String rybh ,String state1, String state2) {
		BzwtxEntity entity = bzwtxDao.selectZwCode(rybh, state1, state2);
		Map<String, Object> map = Maps.newHashMap();
		map.put("zw1", entity.getZwtxxh11());
		map.put("zw2", entity.getZwtxxh21());
		return map;
	}
	
	@UseDataSource("read_ds")
	public boolean entyIsExist(String rybh) {
		int count = bzwtxDao.selectCount(rybh);
		if (count > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	@UseDataSource("read_ds")
	public boolean ryIsYcs(String rybh) {//判断人员是否已出所 stateSHI R3 的
		int count = bzwtxDao.selectYcsCount(rybh);
		if (count > 0) {
			return true;
		}else {
			return false;
		}
	}
	
}
