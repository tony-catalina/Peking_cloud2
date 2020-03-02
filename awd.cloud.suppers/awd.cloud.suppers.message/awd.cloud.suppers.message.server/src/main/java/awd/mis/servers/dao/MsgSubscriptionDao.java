/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import awd.framework.common.dao.api.CrudDao;
import awd.framework.common.entity.Entity;
import awd.mis.servers.entity.MsgSubscriptionEntity;

public interface MsgSubscriptionDao extends CrudDao<MsgSubscriptionEntity, String> {
	
	List<MsgSubscriptionEntity> query(Entity queryEntity);

    int count(Entity queryEntity);

	void deleteBindByQueuename(@Param("vhost") String vhost,@Param("queuename") String queuename);
	
	int hasSubscript(@Param("vhost") String vhost,
					@Param("exchange") String exchange,
					@Param("queuename") String queuename,
					@Param("routingkey") String routingkey);
    
}
