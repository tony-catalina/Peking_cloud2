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
import awd.framework.common.datasource.orm.core.param.Term;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.simple.GenericEntityService;
import awd.mis.servers.dao.UserroleDao;
import awd.mis.servers.entity.Userrole;
//import awd.mis.servers.entity.Userrole;
import awd.mis.servers.entity.UserroleEntity;
import awd.mis.servers.service.UserroleService;

@Service("userroleService")
public class SimpleUserroleService extends GenericEntityService<UserroleEntity, String> implements UserroleService {

	@Autowired
    private UserroleDao userroleDao;
	@Autowired
	private MessageService messageService;

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected IDGenerator<String> getIDGenerator(UserroleEntity entity) {
    	String jsbh="";
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public String batchAdd(String user, Userrole userrole) {
    	List<String> users=userrole.getUser();
    	List<String> roles=userrole.getRole();
    	System.out.println(users);
    	System.out.println(roles);
    	QueryParamEntity delparam=new QueryParamEntity();
    	Term term=new Term();
    	for (String userid : users) {
    		term.or("userid", userid);
		}
    	delparam.addTerm(term);
    	this.userroleDao.delete(delparam);
		for(String userid:users){
			this.sendMesage("104","1",userid);
		}
    	for (String userid : users) {
    		System.out.println(userid);
    		for (String roleid : roles) {
    			System.out.println(roleid);
				UserroleEntity userroleEntity=new UserroleEntity();
				userroleEntity.setUserid(userid);
				userroleEntity.setRolecode(roleid);
				userroleEntity.setCreator(user);
				userroleEntity.setCreatetime(Calendar.getInstance().getTime());
				this.insert(userroleEntity);
			}
			this.sendMesage("104","0",userid);
		}
		return "批量设置生成";
	}

	@Override
    public UserroleDao getDao() {
        return userroleDao;
    }

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int deleteByPk(String pk) {
    	UserroleEntity userrole = this.selectByPk(pk);
    	this.sendMesage("104","1",userrole.getUserid());
		return super.deleteByPk(pk);
	}

	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getSEQUID(String jsbh) {
		return super.getSEQUID(jsbh);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public String insert(UserroleEntity entity) {
		entity.setCreatetime(Calendar.getInstance().getTime());
		return super.insert(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public String saveOrUpdate(UserroleEntity entity) {
		return super.saveOrUpdate(entity);
	}

	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<UserroleEntity> selectByPk(List<String> id) {
		return super.selectByPk(id);
	}
	
	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public UserroleEntity selectByPk(String pk) {
		return super.selectByPk(pk);
	}
	
	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public PagerResult<UserroleEntity> selectPager(Entity arg0) {
		return super.selectPager(arg0);
	}
	
	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(List<UserroleEntity> data) {
		return super.updateByPk(data);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(String pk, UserroleEntity entity) {
		entity.setUpdatetime(Calendar.getInstance().getTime());
		return super.updateByPk(pk, entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	protected int updateByPk(UserroleEntity entity) {
		entity.setUpdatetime(Calendar.getInstance().getTime());
		return super.updateByPk(entity);
	}

	private void sendMesage(String msgType, String action, String PoliceID){
		//	用户角色信息  新增修改删除  发送消息 0:新增 1:删除  2:修改
		String msgtype = "JGZHYW_REMIND_01_YHJS";
		System.out.println(PoliceID);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("MsgType", msgType);
		map.put("UserID", PoliceID);
		map.put("Action", action);
		try {
			messageService.sendMsgJson(msgtype, JSONUtil.toJson(map));
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("发送消息失败================");
		}
	}
    
    
    
}
