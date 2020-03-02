/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;

import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.MountEntity;

public interface MountService extends CrudService<MountEntity, String> {

	int getAppMountNum(String appcode);
}
