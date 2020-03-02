/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;


import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.Userapp;
//import awd.mis.servers.entity.Userapp;
import awd.mis.servers.entity.UserappEntity;

public interface UserappService extends CrudService<UserappEntity, String> {

	void allowAll(String userid);

	String forbid(String user,Userapp userapp);

}
