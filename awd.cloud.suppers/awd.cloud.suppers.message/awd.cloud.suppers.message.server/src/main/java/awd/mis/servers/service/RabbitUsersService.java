/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.RabbitQueuesEntity;
import awd.mis.servers.entity.RabbitUsersEntity;

public interface RabbitUsersService extends CrudService<RabbitUsersEntity, String> {

	void addRabbitUser(RabbitUsersEntity entity) throws ClientProtocolException, IOException;
	
	void addUserAndQueue(RabbitUsersEntity user,RabbitQueuesEntity queue) throws ClientProtocolException, IOException;

	void deleteUser(RabbitUsersEntity entity) throws ClientProtocolException, IOException;
	
	int deleteByList(List<RabbitUsersEntity> users);

	void updateRabbitUser(String id,RabbitUsersEntity entity) throws ClientProtocolException, IOException;

}
