/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service.imp;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import awd.framework.common.utils.JSONUtil;
import awd.mis.servers.api.MessageService;
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
import awd.mis.servers.dao.UsergroupDao;
import awd.mis.servers.entity.UsergroupEntity;
import awd.mis.servers.service.UsergroupService;

@Service("usergroupService")
public class SimpleUsergroupService extends GenericEntityService<UsergroupEntity, String> implements UsergroupService {

	@Autowired
    private UsergroupDao usergroupDao;

	@Autowired
	private MessageService messageService;

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected IDGenerator<String> getIDGenerator(UsergroupEntity entity) {
    	String jsbh=entity.getJsbh();
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public UsergroupDao getDao() {
        return usergroupDao;
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
	public String insert(UsergroupEntity entity) {
		entity.setCreatetime(Calendar.getInstance().getTime());
		return super.insert(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public String saveOrUpdate(UsergroupEntity entity) {
		return super.saveOrUpdate(entity);
	}

	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public UsergroupEntity selectByPk(String pk) {
		return super.selectByPk(pk);
	}
	
	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<UsergroupEntity> selectByPk(List<String> id) {
		return super.selectByPk(id);
	}
	
	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public PagerResult<UsergroupEntity> selectPager(Entity arg0) {
		return super.selectPager(arg0);
	}
	
	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	protected int updateByPk(UsergroupEntity entity) {
		return super.updateByPk(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(List<UsergroupEntity> data) {
		return super.updateByPk(data);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(String pk, UsergroupEntity entity) {
		entity.setUpdatetime(Calendar.getInstance().getTime());
		this.sendMesage("102","2",entity.getUserid());
		return super.updateByPk(pk, entity);
	}

	private void sendMesage(String msgType, String action, String PoliceID){
		//	用户信息信息  新增修改删除  发送消息 0:新增 1:删除  2:修改
		String msgtype = "JGZHYW_REMIND_01_YH";
		System.out.println(PoliceID);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("UserID", PoliceID);
		map.put("MsgType", msgType);
		map.put("Action", action);
		try {
			messageService.sendMsgJson(msgtype, JSONUtil.toJson(map));
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("发送消息失败================");
		}
	}
    
}
