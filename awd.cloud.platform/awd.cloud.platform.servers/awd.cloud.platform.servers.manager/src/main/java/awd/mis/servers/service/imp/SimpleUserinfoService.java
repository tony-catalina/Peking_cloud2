/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service.imp;

import java.util.*;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.core.param.TermType;
import awd.mis.servers.api.MessageService;
import awd.mis.servers.entity.*;
import awd.mis.servers.model.UserInfoOther;
import awd.mis.servers.service.*;
import awd.mis.servers.tools.CacheUtils;
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
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.StringUtils;
import awd.mis.servers.dao.UserinfoDao;
//import awd.mis.servers.entity.Userinfo;
import awd.mis.servers.model.Userinfo;

@Service("userinfoService")
public class SimpleUserinfoService extends GenericEntityService<UserinfoEntity, String> implements UserinfoService {

    @Autowired
    private UserinfoDao userinfoDao;
    @Autowired
    private UsergroupService usergroupService;
    @Autowired
    private UserroleService userroleService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MessageService messageService;


    @Override
    @UseDataSource("write_ds")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected IDGenerator<String> getIDGenerator(UserinfoEntity entity) {
        String jsbh = entity.getJsbh();
        return () -> {
            return getSEQUID(jsbh);
        };
    }

    @Override
    public UserinfoDao getDao() {
        return userinfoDao;
    }

    @Override
    @UseDataSource("read_ds")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public UserinfoEntity findByNameAndPass(String jsbh, String name, String pwd) {
        String state = "R2";
        return userinfoDao.findByNameAndPass(jsbh, name, pwd, state);
    }

    @Override
    @UseDataSource("read_ds")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public PagerResult<UserinfoEntity> selectPager(Entity arg0) {
        return super.selectPager(arg0);
    }

    @Override
    @UseDataSource("write_ds")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public int deleteByPk(String pk) {
        this.sendMesage("102","1",pk);
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
    public String insert(UserinfoEntity entity) {
        entity.setCreatetime(Calendar.getInstance().getTime());
        String id = super.insert(entity);
        this.sendMesage("102","0",id);
        return id;
    }

    @Override
    @UseDataSource("write_ds")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public String saveOrUpdate(UserinfoEntity entity) {
        return super.saveOrUpdate(entity);
    }

    @Override
    @UseDataSource("read_ds")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<UserinfoEntity> selectByPk(List<String> id) {
        return super.selectByPk(id);
    }

    @Override
    @UseDataSource("read_ds")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public UserinfoEntity selectByPk(String pk) {
        return super.selectByPk(pk);
    }


    @Override
    @UseDataSource("read_ds")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public int count(Entity param) {
        return super.count(param);
    }

    @Override
    @UseDataSource("write_ds")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    protected int updateByPk(UserinfoEntity entity) {
        return super.updateByPk(entity);
    }

    @Override
    @UseDataSource("write_ds")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public int updateByPk(List<UserinfoEntity> data) {
        return super.updateByPk(data);
    }

    @Override
    @UseDataSource("write_ds")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public int updateByPk(String pk, UserinfoEntity entity) {
        entity.setUpdatetime(Calendar.getInstance().getTime());
        this.sendMesage("102","2",entity.getId());
        return super.updateByPk(pk, entity);
    }

    @Override
    @UseDataSource("write_ds")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    @TxTransaction(propagation = PropagationEnum.PROPAGATION_REQUIRES_NEW, transactionManager = "transactionManager", waitMaxTime = 80000)
    public String save(Userinfo user) {
        System.err.println(JSONUtil.toJson(user));
        UserinfoEntity userinfo = user.getUserinfo();
        GroupsEntity group = user.getGroup();
        List<RoleEntity> rolelist = user.getRoles();
        String id = "";
        String jh = userinfo.getJh();
        String sfzh = userinfo.getSfzh();
        QueryParamEntity param1 = new QueryParamEntity();
        param1.and("jh", jh);
        QueryParamEntity param2 = new QueryParamEntity();
        param2.and("sfzh", sfzh);
        int count1 = this.count(param1);
        int count2 = this.count(param2);

        if (StringUtils.isNullOrEmpty(userinfo.getId())) {
            if (count1 > 0) {
                return "警号" + jh + "已存在！";
            } else if (count2 > 0) {
                return "身份证号" + sfzh + "已存在！";
            } else {
                id = this.insert(userinfo);
                //插入用户组
                UsergroupEntity usergroup = new UsergroupEntity();
                usergroup.setGroupid(group.getId());
                usergroup.setJsbh(userinfo.getJsbh());
                usergroup.setUserid(id);
                usergroup.setCreator(userinfo.getLoginname());
                usergroup.setCreatetime(Calendar.getInstance().getTime());
                usergroupService.insert(usergroup);
                //用户角色保存
                for (RoleEntity role : rolelist) {
                    UserroleEntity userrole = new UserroleEntity();
                    userrole.setRolecode(role.getCode());
                    userrole.setUserid(id);
                    userrole.setCreator(userinfo.getLoginname());
                    userrole.setCreatetime(Calendar.getInstance().getTime());
                    userroleService.insert(userrole);
                    this.sendMesage("104","0",userrole.getUserid());
                }
                return "保存成功！";
            }
        } else {
            this.saveOrUpdate(userinfo);
//			QueryParamEntity param=new QueryParamEntity();
//			param.and("jsbh", userinfo.getJsbh())
//			.and("userid", userinfo.getId());
//			UsergroupEntity usergroup =usergroupService.selectSingle(param);
//			usergroup.setJsbh(userinfo.getJsbh());
//			usergroup.setUserid(id);
//			usergroup.setCreator(userinfo.getLoginname());
//			usergroup.setCreatetime(Calendar.getInstance().getTime());
//			usergroupService.saveOrUpdate(usergroup);
//			QueryParamEntity roleparam=new QueryParamEntity();
//			roleparam.and("userid", userinfo.getId());
//			List<UserroleEntity> userolelist=userroleService.select(roleparam);
//			for (UserroleEntity userroleEntity : userolelist) {
//				userroleService.deleteByPk(userroleEntity.getId());
//			}
//			for (RoleEntity role : rolelist) {
//				UserroleEntity userrole=new UserroleEntity();
//				userrole.setRolecode(role.getCode());
//				userrole.setUserid(id);
//				userrole.setCreator(userinfo.getLoginname());
//				userrole.setCreatetime(Calendar.getInstance().getTime());
//				userroleService.insert(userrole);
//			}
            return "保存成功！";
        }
    }

    @Override
    @UseDataSource("read_ds")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<UserinfoEntity> getUserByJsbhRole(String jsbh, String role) {
        return userinfoDao.getUserByJsbhRole(jsbh, role);
    }

    @Override
    @UseDataSource("read_ds")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<UserinfoEntity> getUserByJsbhsRoles(String jsbhs, String roles) {
        return userinfoDao.getUserByJsbhsRoles(jsbhs, roles);
    }

    @Override
    @UseDataSource("read_ds")
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Map<String, String>> getUserByJsbh(String jsbh) {
        //zdbh表示jsbh相对应的总队
        String zdbh = jsbh;
        //进行监所编号的校验，如果尾号后三位为000表示该监所编号是总队，否则不是总队
        String jiaoyan = jsbh.substring(jsbh.length() - 3, jsbh.length());
        if (!("000".equals(jiaoyan))) {
            zdbh = jsbh.substring(0, 2) + "0000000";
        }
        return userinfoDao.getUserByJsbh(jsbh, zdbh);
    }

    /**
     * 根据监所编号和登录名获取当前登录用户权限信息
     *
     * @param jsbh
     * @param loginname
     * @return Map<String, Object>
     * @throws Exception
     */
    @Override
    @UseDataSource("read_ds")
    public ResponseMessage<Map<String, Object>> getUserByJsbhAndName(String jsbh, String loginname) throws Exception {
        QueryParamEntity queryParamEntity = new QueryParamEntity();
        Map<String, Object> map = new HashMap<>();
        queryParamEntity.and("jsbh", TermType.eq, jsbh);
        queryParamEntity.and("loginname", TermType.eq, loginname);
        queryParamEntity.and("state", TermType.eq, "R2");
        UserinfoEntity userinfoEntity = this.selectSingle(queryParamEntity);
        int i = 0;
        UserroleEntity userroleEntity = null;
        if (!StringUtils.isNullOrEmpty(userinfoEntity)) {
            i++;
            map.put("email", userinfoEntity.getEmail());
            map.put("glybz", userinfoEntity.getGlybz());
            map.put("glybzString", userinfoEntity.getGlybzString());
            map.put("jh", userinfoEntity.getJh());
            map.put("id", userinfoEntity.getId());
            map.put("loginname", userinfoEntity.getLoginname());
//			map.put("loginpass",userinfoEntity.getLoginpass());
            map.put("realname", userinfoEntity.getRealname());
            map.put("sfzh", userinfoEntity.getSfzh());
            map.put("xkid", userinfoEntity.getXkid());

            map.put("sjh", getDao().getUserinfoDh(userinfoEntity.getJh()).get("sjh"));

            queryParamEntity = new QueryParamEntity();
            queryParamEntity.and("userid", TermType.eq, userinfoEntity.getId());
            userroleEntity = userroleService.selectSingle(queryParamEntity);
        }


        if (!StringUtils.isNullOrEmpty(userroleEntity)) {
            queryParamEntity = new QueryParamEntity();
            queryParamEntity.and("code", TermType.eq, userroleEntity.getRolecode());
            RoleEntity roleEntity = roleService.selectSingle(queryParamEntity);
            if (!StringUtils.isNullOrEmpty(roleEntity)) {
                map.put("code", roleEntity.getCode());
                map.put("bm", roleEntity.getName());
            }
        }
        map.put("total", i);
        return ResponseMessage.ok(map);
    }

    @Override
    @UseDataSource("read_ds")
    public ResponseMessage<List<Map<String, Object>>> mjjbxxQuery(String jsbh, String sfzh) throws Exception {
        List<Map<String, Object>> users = new ArrayList<>();
        QueryParamEntity queryParamEntity = new QueryParamEntity();
        queryParamEntity.and("sfzh", TermType.in, sfzh);
        queryParamEntity.and("jsbh", TermType.eq, jsbh);
        queryParamEntity.and("state", TermType.eq, "R2");
        List<UserinfoEntity> userinfoEntitys = this.select(queryParamEntity);
        if (userinfoEntitys.size() > 0) {
            for (UserinfoEntity userinfoEntity : userinfoEntitys) {
                Map<String, Object> map = new HashMap<>();
                map.put("sfzh", userinfoEntity.getSfzh());
                map.put("loginpass", userinfoEntity.getLoginpass());
                map.put("userinfoId", userinfoEntity.getId());
                QueryParamEntity userroleParam = new QueryParamEntity();
                userroleParam.and("userid", TermType.eq, userinfoEntity.getId());
                UserroleEntity userroleEntity = userroleService.selectSingle(userroleParam);
                if (!StringUtils.isNullOrEmpty(userroleEntity)) {
                    QueryParamEntity roleParam = new QueryParamEntity();
                    roleParam.and("code", TermType.eq, userroleEntity.getRolecode());
                    RoleEntity roleEntity = roleService.selectSingle(roleParam);
                    map.put("role_name", roleEntity.getName());
                    map.put("role_id", roleEntity.getId());
                }
                users.add(map);
            }

        }

        return ResponseMessage.ok(users);
    }

    @Override
    public PagerResult<UserInfoOther> getUserRole(Entity queryEntity) {
        PagerResult<UserInfoOther> pagerResult = new PagerResult();

        int total = this.getDao().getUserRoleCount(queryEntity);
        pagerResult.setTotal(total);
        pagerResult.setData(userinfoDao.getUserRole(queryEntity));
        return pagerResult;
    }

    private void sendMesage(String msgType, String action, String PoliceID){
        //	用户信息信息  新增修改删除  发送消息 0:新增 1:删除  2:修改
        String msgtype="";
        if("104".equals(msgType)){
             msgtype = "JGZHYW_REMIND_01_YHJS";
        }else {
             msgtype = "JGZHYW_REMIND_01_YH";
        }
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
