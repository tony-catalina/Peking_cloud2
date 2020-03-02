/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;

import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.Userrole;
import awd.mis.servers.entity.UserroleEntity;

public interface UserroleService extends CrudService<UserroleEntity, String> {

	String batchAdd(String user, Userrole userrole);

}
