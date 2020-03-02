/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service.imp;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

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
import awd.framework.common.utils.StringUtils;
import awd.mis.servers.dao.SettingDao;
import awd.mis.servers.entity.SettingEntity;
import awd.mis.servers.entity.UserinfoEntity;
import awd.mis.servers.entity.UsersettingEntity;
import awd.mis.servers.service.SettingService;
import awd.mis.servers.service.UserinfoService;
import awd.mis.servers.service.UsersettingService;
import awd.mis.servers.tools.Result;

@Service("settingService")
public class SimpleSettingService extends GenericEntityService<SettingEntity, String> implements SettingService {

	@Autowired
    private SettingDao settingDao;
	@Autowired
	private UserinfoService userinfoService;
	@Autowired
	private UsersettingService usersettingService;

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected IDGenerator<String> getIDGenerator(SettingEntity entity) {
        return () -> {
            return getSEQUID("");
        };
    }

    @Override
    public SettingDao getDao() {
        return settingDao;
    }

	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<SettingEntity> getSetting(String appcode, String progroup) {
		return settingDao.getSetting(appcode,progroup);
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
	public String insert(SettingEntity entity) {
		entity.setCreatetime(Calendar.getInstance().getTime());
		return super.insert(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public String saveOrUpdate(SettingEntity entity) {
		return super.saveOrUpdate(entity);
	}

	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<SettingEntity> selectByPk(List<String> id) {
		return super.selectByPk(id);
	}
	


	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public SettingEntity selectByPk(String pk) {
		return super.selectByPk(pk);
	}
	
	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public PagerResult<SettingEntity> selectPager(Entity arg0) {
		return super.selectPager(arg0);
	}
	
	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(List<SettingEntity> data) {
		return super.updateByPk(data);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	protected int updateByPk(SettingEntity entity) {
		return super.updateByPk(entity);
	}

	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public int updateByPk(String pk, SettingEntity entity) {
		entity.setUpdatetime(Calendar.getInstance().getTime());
		return super.updateByPk(pk, entity);
	}

	@Override
	@UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public String getAppSetting(String appcode, String jsbh, String proname) {
		String result=null;
		QueryParamEntity param=new QueryParamEntity();
		param.and("jsbh", TermType.eq, jsbh)
		.and("glybz", TermType.eq,"1");
		UserinfoEntity userinfo=userinfoService.selectSingle(param);
		if(userinfo!=null) {
			QueryParamEntity settingparam=new QueryParamEntity();
			settingparam.and("jsbh", TermType.eq, jsbh)
			.and("userid", TermType.eq, userinfo.getId())
			.and("appcode", TermType.eq, appcode)
			.and("key", TermType.eq, proname);
			UsersettingEntity usersetting=usersettingService.selectSingle(settingparam);
			if(usersetting!=null) {
				return usersetting.getValue();
			}
		}		
		return result;
	}
	
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)	
	private void initSetting(String appcode, 
							 String progroup, 
							 String protype, 
							 String proname, 
							 String prolist, 
							 String provalue, 
							 String user) {
		QueryParamEntity query=new QueryParamEntity();
		query.and("appcode", TermType.eq, appcode)
			.and("progroup", TermType.eq, progroup)
			.and("proname", TermType.eq, proname);
		SettingEntity setting = this.selectSingle(query);
		if(StringUtils.isNullOrEmpty(setting)) {
			setting=new SettingEntity();
			setting.setAppcode(appcode);
			setting.setProgroup(progroup);	
			setting.setProname(proname);
			setting.setProtype(protype);
			setting.setProlist(prolist);	
			setting.setProvalue(provalue);				
			setting.setCreator(user);
		}else {
			setting.setProlist(prolist);	
			setting.setProvalue(provalue);	
			setting.setUpdator(user);
		}
		this.saveOrUpdate(setting);
	}
	
	
	@Override
	@UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
	public Result<String> initSettingList(String appcode, List<Map<String, Object>> settingList, String user){
		Result<String> result = new Result<>();
		for (Map<String, Object> settingMap : settingList) {
			String progroup = (String) settingMap.get("progroup");
			String protype = (String) settingMap.get("protype");
			String proname = (String) settingMap.get("proname");
			String prolist = (String) settingMap.get("prolist");
			String provalue = (String) settingMap.get("provalue");
			
			if (StringUtils.isNullOrEmpty(progroup)
					|| StringUtils.isNullOrEmpty(protype)
					|| StringUtils.isNullOrEmpty(proname)
					|| StringUtils.isNullOrEmpty(prolist)
					|| StringUtils.isNullOrEmpty(provalue)) {
				result.setCode(500);
				result.setMsg("不符合系统设置json样式！");
				return result;
			}
			this.initSetting(appcode, progroup, protype, proname, prolist, provalue, user);
		}
		
		result.setCode(200);
		result.setMsg("系统设置成功");
		return result;
	}
	
}
