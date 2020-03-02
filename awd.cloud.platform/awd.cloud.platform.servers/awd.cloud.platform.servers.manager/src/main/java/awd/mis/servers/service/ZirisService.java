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

import awd.mis.servers.entity.MirisEntity;
import awd.mis.servers.entity.ZirisEntity;

public interface ZirisService extends CrudService<ZirisEntity, String> {
    ZirisEntity selectByPk(String rybh, String hmwzbh);

    ResponseMessage<PagerResult<Map<String, Object>>> jbxxlist(Entity param);

    @UseDataSource("biometric_ds")
    void cached();
}
