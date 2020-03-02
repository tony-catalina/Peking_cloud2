/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.UserinfoEntity;
import awd.mis.servers.model.UserInfoOther;
import awd.mis.servers.model.Userinfo;

import java.util.List;
import java.util.Map;

public interface UserinfoService extends CrudService<UserinfoEntity, String> {

    UserinfoEntity findByNameAndPass(String jsbh, String name, String pwd);

    String save(Userinfo user);

    List<UserinfoEntity> getUserByJsbhRole(String jsbh, String role);
    
    List<UserinfoEntity> getUserByJsbhsRoles(String jsbhs, String roles);

    List<Map<String,String>> getUserByJsbh(String jsbh);

    ResponseMessage<Map<String,Object>> getUserByJsbhAndName(String jsbh,String loginname) throws Exception;

    ResponseMessage<List<Map<String,Object>>> mjjbxxQuery(String jsbh,String jh) throws Exception;
    PagerResult<UserInfoOther> getUserRole(Entity queryEntity);
}
