/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;

import java.util.Map;

import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.RabbitQueuesEntity;

public interface RabbitQueuesService extends CrudService<RabbitQueuesEntity, String> {

	Map<String, Object> deleteQueueEntity(String id,String vhost,String queuename);
	
	String getAppnameByQueuename(String queuename);
}
