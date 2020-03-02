/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.entity.DocumentEntity;
import awd.mis.servers.entity.DocumentdetailEntity;
import awd.mis.servers.service.DocumentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RefreshScope
@RequestMapping("/document")
@AccessLogger("Document")
@Api(value = "Document", description = "公文")
public class DocumentController implements GenericEntityController<DocumentEntity, String, QueryParamEntity, DocumentEntity> {

    private DocumentService documentService;


    @Override
    public DocumentEntity modelToEntity(DocumentEntity model, DocumentEntity entity) {
        return model;
    }

    @Autowired
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Override
    public CrudService<DocumentEntity, String> getService() {
        return documentService;
    }

    @Override
    @OpenAPI
    public ResponseMessage<String> save(@RequestBody DocumentEntity data) {
        QueryParamEntity q = QueryParamEntity.empty();
        q.and("uuid", data.getUuid());
        int count = documentService.count(q);
        if (count > 0) {
            return ResponseMessage.error("UUID重复，请重试！");
        }
        return GenericEntityController.super.save(data);
    }

    @Override
    @OpenAPI
    public ResponseMessage<PagerResult<DocumentEntity>> list(HttpServletRequest reqest, QueryParamEntity param) {
        param.and("state", "R2");
        return GenericEntityController.super.list(reqest, param);
    }

	@Override
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<DocumentEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody DocumentEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody DocumentEntity data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}

    

}
