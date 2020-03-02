/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;

import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.WgzdEntity;

public interface WgzdService extends CrudService<WgzdEntity, String> {

	void cached();

}
