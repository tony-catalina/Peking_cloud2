/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.cloud.suppers.interfaces.service.interfaces;

import java.util.List;

import awd.cloud.suppers.interfaces.entity.UserinfoEntity;
import awd.framework.common.service.api.CrudService;

public interface UserinfoService extends CrudService<UserinfoEntity, String> {

	/**
	 * 调用manager 接口，查询用户类型为8 厂商 和 用户类型为 9 系统管理员的用户
	 * @return 
	 */
	List<UserinfoEntity> getUserByType();
	
	/**
	 * 定时从manager的userinfo表中获取数据保存到interface 的userinfo表中
	 * @return 保存的数量
	 */
	int setUserinfoFromManager();
}
