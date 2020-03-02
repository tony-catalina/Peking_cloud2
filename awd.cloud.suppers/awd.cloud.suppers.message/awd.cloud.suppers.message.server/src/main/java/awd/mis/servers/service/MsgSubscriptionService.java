/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;

import java.util.List;
import java.util.Map;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.MsgSubscriptionEntity;

public interface MsgSubscriptionService extends CrudService<MsgSubscriptionEntity, String> {
	
	List<Map<String, Object>> getMsgtypeByQueuename(String queuename,Map<String, String> params);
	
	List<?> getQueueByMsgtype(String msgType,Map<String, String> params);
	
	void saveListByStore(List<String> routingkeyList, String vhost, String queuename, String exchange);
	
	void updateListByStore(List<String> ids, String shbz);
	
	  /**
	   * 根据订阅表，批量绑定交换机与队列
	   */
	List<Map<String, Object>> bindingQueues(List<MsgSubscriptionEntity> list);

	Map<String, Object> unbindQueueAndExchange(MsgSubscriptionEntity entity);

	Map<String, Object> bindQueueAndExchange(MsgSubscriptionEntity entity);

	Map<String, Object> bindOrUnbind(MsgSubscriptionEntity entity);

	void deleteBindByQueuename(String vhost, String queuename);

	boolean isSubscript(String vhost,String exchange, String queuename, String routingkey);

}
