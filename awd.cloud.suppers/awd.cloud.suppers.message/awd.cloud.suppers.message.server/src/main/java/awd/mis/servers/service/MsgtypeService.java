/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;

import java.util.List;
import java.util.Map;

import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.MsgtypeEntity;

public interface MsgtypeService extends CrudService<MsgtypeEntity, String> {

	List<Map<String, Object>> getUnbindByQueuename(String vhost, String exchange, String queuename,Map<String, String> params);

	Map<String, Object> addMsgType(MsgtypeEntity entity);

	Map<String, Object> updateMsgType(String id,MsgtypeEntity entity);

	int deleteMsgType(String id, String routingkey);
}
