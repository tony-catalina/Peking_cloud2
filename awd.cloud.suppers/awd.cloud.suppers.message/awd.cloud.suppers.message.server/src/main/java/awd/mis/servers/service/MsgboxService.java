/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;

import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.MsgboxEntity;

import java.util.Date;

public interface MsgboxService extends CrudService<MsgboxEntity, String> {

	int acceptMsg(String id, String acceptor, Date accepttime);

	String updateAllMsgByJh( String jsbh);

}
