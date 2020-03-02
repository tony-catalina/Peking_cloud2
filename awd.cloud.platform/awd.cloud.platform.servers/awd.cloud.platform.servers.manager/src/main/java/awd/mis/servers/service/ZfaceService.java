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
import awd.mis.servers.entity.ZfaceEntity;
import java.util.Map;

public interface ZfaceService extends CrudService<ZfaceEntity, String> {

    ResponseMessage<PagerResult<Map<String, Object>>> jbxxlist(Entity param);

    ZfaceEntity selectByRybh(String pk);

    @UseDataSource("biometric_ds")
    void cached();
}
