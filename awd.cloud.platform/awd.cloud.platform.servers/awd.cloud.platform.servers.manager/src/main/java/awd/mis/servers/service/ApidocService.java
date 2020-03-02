/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;


import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.ApidocEntity;

public interface ApidocService extends CrudService<ApidocEntity, String> {
	/**
	 * 循环查找表字段是否含有rybh，有的话就自动生成jbxxList 方法，没有就不生成
	 */
}
