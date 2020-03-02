/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service.imp;

import java.util.*;

import awd.framework.common.utils.JSONUtil;
import awd.mis.servers.api.MessageService;
import org.dromara.raincat.annotation.PropagationEnum;
import org.dromara.raincat.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import awd.framework.common.core.IDGenerator;
import awd.framework.common.core.param.TermType;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.simple.GenericEntityService;
import awd.mis.servers.dao.UsermenuDao;
import awd.mis.servers.entity.MenusEntity;
import awd.mis.servers.entity.Usermenu;
import awd.mis.servers.entity.UsermenuEntity;
import awd.mis.servers.service.MenusService;
import awd.mis.servers.service.UsermenuService;

@Service("usermenuService")
public class SimpleUsermenuService extends GenericEntityService<UsermenuEntity, String> implements UsermenuService {

	@Autowired
    private UsermenuDao usermenuDao;
	@Autowired
	private MenusService menusService;
	@Autowired
	private MessageService messageService;

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected IDGenerator<String> getIDGenerator(UsermenuEntity entity) {
    	String jsbh=entity.getJsbh();
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public UsermenuDao getDao() {
        return usermenuDao;
    }

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public void allowAll(String userid, String appid) {
		usermenuDao.allowAll(userid,appid);		
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public String forbid(String user,String jsbh, Usermenu usermenu) {
		List<String> users=usermenu.getUserid();
		Map<String, String> menus=usermenu.getAppmenu();
		for (String id : users) {
			QueryParamEntity delparam=new QueryParamEntity();
			delparam.and("userid", id);
			usermenuDao.delete(delparam);
			Set<String> menukeys=menus.keySet();
			for (String menu : menukeys) {	
				MenusEntity entity=getMenu(menu);
				String appcode=entity!=null?entity.getAppcode():"";
				UsermenuEntity usermenuEntity=new UsermenuEntity();
				usermenuEntity.setJsbh(jsbh);
				usermenuEntity.setAppcode(appcode);
				usermenuEntity.setMenu(menu);
				usermenuEntity.setUserid(id);
				usermenuEntity.setCreator(user);
				usermenuEntity.setCreatetime(Calendar.getInstance().getTime());				
				this.insert(usermenuEntity);
			}
		}
		for (String id : users){
            this.sendMesage("102","2",id);
        }
		return "设置成功！";
	}


	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	private MenusEntity getMenu(String menu) {
		QueryParamEntity param=new QueryParamEntity();
		param.and("menu", TermType.eq, menu);
		MenusEntity enity=menusService.selectSingle(param);
		return enity;
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
	public String insert(UsermenuEntity entity) {
		entity.setCreatetime(Calendar.getInstance().getTime());
		return super.insert(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public String saveOrUpdate(UsermenuEntity entity) {
		return super.saveOrUpdate(entity);
	}

	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<UsermenuEntity> selectByPk(List<String> id) {
		return super.selectByPk(id);
	}
	
	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public UsermenuEntity selectByPk(String pk) {
		return super.selectByPk(pk);
	}
	
	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public PagerResult<UsermenuEntity> selectPager(Entity arg0) {
		return super.selectPager(arg0);
	}
	
	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(List<UsermenuEntity> data) {
		return super.updateByPk(data);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(String pk, UsermenuEntity entity) {
		entity.setUpdatetime(Calendar.getInstance().getTime());
		return super.updateByPk(pk, entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	protected int updateByPk(UsermenuEntity entity) {
		return super.updateByPk(entity);
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
