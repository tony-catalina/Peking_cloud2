/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;

import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.Usermenu;
import awd.mis.servers.entity.UsermenuEntity;

public interface UsermenuService extends CrudService<UsermenuEntity, String> {

	void allowAll(String userid, String appid);

	String forbid(String user,String jsbh, Usermenu usermenu);

}
