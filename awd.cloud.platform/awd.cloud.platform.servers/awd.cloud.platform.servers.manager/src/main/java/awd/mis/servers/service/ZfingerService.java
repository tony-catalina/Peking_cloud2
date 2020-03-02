/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.service.api.CrudService;

import java.util.Map;

import awd.mis.servers.entity.ZfingerEntity;

public interface ZfingerService extends CrudService<ZfingerEntity, String> {
	/**
	 * 循环查找表字段是否含有rybh，有的话就自动生成jbxxList 方法，没有就不生成
	 */
	ResponseMessage<PagerResult<Map<String, Object>>> jbxxlist(Entity param);

    @UseDataSource("biometric_ds")
    ZfingerEntity selectByPk(String rybh, String szbh);

    @UseDataSource("biometric_ds")
    void cached();
}
