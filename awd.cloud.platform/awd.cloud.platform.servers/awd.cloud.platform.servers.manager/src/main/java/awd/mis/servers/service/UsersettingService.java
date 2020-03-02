/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;

import java.util.List;

import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.UsersettingEntity;

public interface UsersettingService extends CrudService<UsersettingEntity, String> {

	List<UsersettingEntity> getUserSetting(String userid,String app);

}
