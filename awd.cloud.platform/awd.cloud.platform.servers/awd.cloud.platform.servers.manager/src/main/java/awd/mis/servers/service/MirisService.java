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

public interface MirisService extends CrudService<MirisEntity, String> {
    MirisEntity selectByPk(String zjh, String hmwzbh);

    @UseDataSource("biometric_ds")
    void cached();
}
