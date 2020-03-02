/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service.imp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import awd.framework.common.datasource.orm.core.param.Sort;
import awd.framework.common.entity.param.QueryParamEntity;
import com.google.common.collect.Lists;

import org.dromara.raincat.annotation.PropagationEnum;
import org.dromara.raincat.annotation.TxTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.service.simple.GenericEntityService;
import awd.framework.common.utils.BeanUtils;
import awd.framework.common.utils.StringUtils;
import awd.mis.servers.api.ManagerService;
import awd.mis.servers.dao.MsgboxDao;
import awd.mis.servers.entity.MsgboxEntity;
import awd.mis.servers.model.UserinfoModel;
import awd.mis.servers.service.MsgboxService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("msgboxService")
public class SimpleMsgboxService extends GenericEntityService<MsgboxEntity, String> implements MsgboxService {

    @Autowired
    private MsgboxDao msgboxDao;
    @Autowired
    private ManagerService managerService;


    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected IDGenerator<String> getIDGenerator(MsgboxEntity entity) {
        String jsbh = entity.getJsbh();
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public MsgboxDao getDao() {
        return msgboxDao;
    }

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public int send(MsgboxEntity msgbox, String jsbh, String role, String group, String[] users) {
        if (!StringUtils.isNullOrEmpty(role)) {
            ResponseMessage<List<UserinfoModel>> roleRespone = managerService.getUserByRole(jsbh, role);
            if (roleRespone.getStatus() == 200 && roleRespone.getResult().size() > 0) {
                List<UserinfoModel> userlist = roleRespone.getResult();
                for (UserinfoModel userinfoModel : userlist) {
                    MsgboxEntity entity = new MsgboxEntity();
                    BeanUtils.copyProperties(msgbox, entity);
                    entity.setJsbh(jsbh);
                    entity.setJsrjh(userinfoModel.getJh());
                    saveOrUpdate(entity);
                }
            }

        }
        if (!StringUtils.isNullOrEmpty(group)) {
            ResponseMessage<List<UserinfoModel>> groupRespone = managerService.getUserByGroup(jsbh, group);
            if (groupRespone.getStatus() == 200 && groupRespone.getResult().size() > 0) {
                List<UserinfoModel> userlist = groupRespone.getResult();
                for (UserinfoModel userinfoModel : userlist) {
                    MsgboxEntity entity = new MsgboxEntity();
                    BeanUtils.copyProperties(msgbox, entity);
                    entity.setJsbh(jsbh);
                    entity.setJsrjh(userinfoModel.getJh());
                    saveOrUpdate(entity);
                }
            }
        }
        if (users.length > 0) {
            for (String jh : users) {
                MsgboxEntity entity = new MsgboxEntity();
                BeanUtils.copyProperties(msgbox, entity);
                entity.setJsbh(jsbh);
                entity.setJsrjh(jh);
                saveOrUpdate(entity);
            }
        }

        return 0;
    }

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public int acceptMsg(String id, String acceptor, Date accepttime) {
        MsgboxEntity entity = this.selectByPk(id);
        entity.setJsrjh(acceptor);
        entity.setJssj(accepttime);
        return this.updateByPk(entity);
    }

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public String updateAllMsgByJh(String jh) {
        return msgboxDao.updateAllMsgByMsg(jh);
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
    public String insert(MsgboxEntity entity) {
    	entity.setCreatetime(Calendar.getInstance().getTime());
        return super.insert(entity);
    }

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public String saveOrUpdate(MsgboxEntity entity) {
        return super.saveOrUpdate(entity);
    }

    @Override
    @UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
    public MsgboxEntity selectByPk(String pk) {
        return super.selectByPk(pk);
    }

    @Override
    @UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<MsgboxEntity> selectByPk(List<String> id) {
        return super.selectByPk(id);
    }

    @Override
    @UseDataSource("read_ds")
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
    public PagerResult<MsgboxEntity> selectPager(Entity arg0) {
        return super.selectPager(arg0);
    }

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected int updateByPk(MsgboxEntity entity) {
        return super.updateByPk(entity);
    }

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public int updateByPk(List<MsgboxEntity> data) {
        return super.updateByPk(data);
    }

    @Override
    @UseDataSource("write_ds")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public int updateByPk(String pk, MsgboxEntity entity) {
    	entity.setUpdatetime(Calendar.getInstance().getTime());
        return super.updateByPk(pk, entity);
    }


}
