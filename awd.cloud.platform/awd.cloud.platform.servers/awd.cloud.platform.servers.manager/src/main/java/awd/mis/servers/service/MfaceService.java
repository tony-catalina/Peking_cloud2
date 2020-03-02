/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;

import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.MfaceEntity;

public interface MfaceService extends CrudService<MfaceEntity, String> {
    MfaceEntity selectByZjh(String pk);

    @UseDataSource("biometric_ds")
    void cached();
}
