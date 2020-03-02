/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.service;

import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.Entity;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.mis.servers.entity.DocumentEntity;
import awd.mis.servers.entity.DocumentdetailEntity;
import awd.mis.servers.model.DocumentdetailModel;

public interface DocumentdetailService extends CrudService<DocumentdetailEntity, String> {

    PagerResult<DocumentdetailModel> selectDetail(QueryParamEntity queryParamEntity );
    
    ResponseMessage<String> updateDetail(DocumentdetailEntity date);
}
