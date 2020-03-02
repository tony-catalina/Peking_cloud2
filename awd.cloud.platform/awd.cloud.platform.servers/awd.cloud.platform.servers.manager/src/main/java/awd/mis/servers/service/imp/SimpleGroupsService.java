/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service.imp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.dromara.raincat.annotation.PropagationEnum;
import org.dromara.raincat.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;

import awd.framework.common.core.IDGenerator;
import awd.framework.common.core.param.TermType;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.simple.GenericEntityService;
import awd.mis.servers.dao.GroupsDao;
import awd.mis.servers.entity.GroupsEntity;
import awd.mis.servers.service.GroupsService;

@Service("groupsService")
public class SimpleGroupsService extends GenericEntityService<GroupsEntity, String> implements GroupsService {

	@Autowired
    private GroupsDao groupsDao;

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected IDGenerator<String> getIDGenerator(GroupsEntity entity) {
    	String jsbh=entity.getJsbh();
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public GroupsDao getDao() {
        return groupsDao;
    }

    @UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<GroupsEntity> getGroups(String jsbh){
		return groupsDao.getGroups(jsbh);
    }

    @Override
    @UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Map<String, Object>> getJsTypeByGroups(String type){
    	QueryParamEntity qParamEntity = new QueryParamEntity();
    	//qParamEntity.and("fgroup", TermType.eq, "0").and("jsbh", TermType.like, "11%");	//这个是指定为北京的所
    	qParamEntity.and("fgroup", TermType.eq, "0");	//没有区分北京和上海的所
    	List<GroupsEntity> list = this.select(qParamEntity);
    	List<Map<String,Object>> result = new ArrayList<>();
    	for (GroupsEntity groupsEntity : list) {
    		String jsbh = groupsEntity.getJsbh();
			if (jsbh.substring((jsbh.length()-2), (jsbh.length()-1)).equals(type)) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("name", groupsEntity.getGroupname());
				map.put("jsbh", jsbh);
				result.add(map);
			}
		}
    	return result;
    }
    
    @Override
    @UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Map<String, Object>> getJsTreeTypeByGroups(String type){
    	QueryParamEntity qParamEntity = new QueryParamEntity();
    	//qParamEntity.and("fgroup", TermType.eq, "0").and("jsbh", TermType.like, "11%");	//这个是指定为北京的所
    	qParamEntity.and("fgroup", TermType.eq, "0");	//没有区分北京和上海的所
    	List<GroupsEntity> list = this.select(qParamEntity);
    	List<Map<String,Object>> result = new ArrayList<>();
    	for (GroupsEntity groupsEntity : list) {
    		String jsbh = groupsEntity.getJsbh();
			if (jsbh.substring((jsbh.length()-2), (jsbh.length()-1)).equals(type)) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("name", groupsEntity.getGroupname());
				map.put("id", jsbh);
				result.add(map);
			}
		}
    	return result;
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
	public String insert(GroupsEntity entity) {
		entity.setCreatetime(Calendar.getInstance().getTime());
		return super.insert(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public String saveOrUpdate(GroupsEntity entity) {
		return super.saveOrUpdate(entity);
	}

	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<GroupsEntity> selectByPk(List<String> id) {
		return super.selectByPk(id);
	}

	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public GroupsEntity selectByPk(String pk) {
		return super.selectByPk(pk);
	}
	
	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public PagerResult<GroupsEntity> selectPager(Entity arg0) {
		return super.selectPager(arg0);
	}
	
	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	protected int updateByPk(GroupsEntity entity) {
		return super.updateByPk(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(List<GroupsEntity> data) {
		return super.updateByPk(data);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(String pk, GroupsEntity entity) {
		entity.setUpdatetime(Calendar.getInstance().getTime());
		return super.updateByPk(pk, entity);
	}
    
    
    
}
