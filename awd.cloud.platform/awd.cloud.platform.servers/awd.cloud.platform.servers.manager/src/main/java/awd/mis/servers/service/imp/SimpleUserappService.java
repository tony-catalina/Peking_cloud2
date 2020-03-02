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
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.simple.GenericEntityService;
import awd.mis.servers.dao.UserappDao;
import awd.mis.servers.entity.Userapp;
import awd.mis.servers.entity.UserappEntity;
import awd.mis.servers.service.UserappService;

@Service("userappService")
public class SimpleUserappService extends GenericEntityService<UserappEntity, String> implements UserappService {

	@Autowired
    private UserappDao userappDao;

	@Autowired
	private MessageService messageService;

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected IDGenerator<String> getIDGenerator(UserappEntity entity) {
    	String jsbh="";
        return () -> {
            return getSEQUID("");
        };
    }

    @Override
    public UserappDao getDao() {
        return userappDao;
    }

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public void allowAll(String userid) {
		userappDao.allowAll(userid);
		
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
	public String insert(UserappEntity entity) {
		entity.setCreatetime(Calendar.getInstance().getTime());
		return super.insert(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public String saveOrUpdate(UserappEntity entity) {
		return super.saveOrUpdate(entity);
	}

	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<UserappEntity> selectByPk(List<String> id) {
		return super.selectByPk(id);
	}
	
	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public UserappEntity selectByPk(String pk) {
		return super.selectByPk(pk);
	}
	
	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public PagerResult<UserappEntity> selectPager(Entity arg0) {
		return super.selectPager(arg0);
	}
	
	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(List<UserappEntity> data) {
		return super.updateByPk(data);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(String pk, UserappEntity entity) {
		entity.setUpdatetime(Calendar.getInstance().getTime());
		return super.updateByPk(pk, entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	protected int updateByPk(UserappEntity entity) {
		return super.updateByPk(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public String forbid(String user,Userapp userapp) {
		List<String> users=userapp.getUserid();
		List<String> appcode=userapp.getAppcode();
		for (String id : users) {
			QueryParamEntity delparam=new QueryParamEntity();
			delparam.and("userid", id);
			userappDao.delete(delparam);
			for (String code : appcode) {
				QueryParamEntity param=new QueryParamEntity();
				param.and("userid", id).and("appcode", code);
				UserappEntity userappEntity=this.selectSingle(param);
				if(userappEntity==null) {
					userappEntity=new UserappEntity();
					userappEntity.setUserid(id);
					userappEntity.setAppcode(code);
					userappEntity.setCreator(user);
					userappEntity.setCreatetime(Calendar.getInstance().getTime());
					this.insert(userappEntity);
				}
			}
		}
		for (String id : users){
			this.sendMesage("102","2",id);
		}
		return "办理成功！";
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
