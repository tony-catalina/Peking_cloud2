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
import awd.framework.common.utils.JSONUtil;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.entity.DocumentEntity;
import awd.mis.servers.entity.DocumentdetailEntity;
import awd.mis.servers.model.DocumentdetailModel;
import awd.mis.servers.service.DocumentdetailService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;


@RestController
@RefreshScope
@RequestMapping("/documentdetail")
@AccessLogger("Documentdetail")
@Api(value = "Documentdetail", description = "监所公文回复信息")
public class DocumentdetailController implements GenericEntityController<DocumentdetailEntity, String, QueryParamEntity, DocumentdetailEntity> {

    private DocumentdetailService documentdetailService;


    @Override
    public DocumentdetailEntity modelToEntity(DocumentdetailEntity model, DocumentdetailEntity entity) {
        return model;
    }

    @Autowired
    public void setDocumentdetailService(DocumentdetailService documentdetailService) {
        this.documentdetailService = documentdetailService;
    }

    @Override
    public CrudService<DocumentdetailEntity, String> getService() {
        return documentdetailService;
    }


    @Override
    @OpenAPI
    public ResponseMessage<PagerResult<DocumentdetailEntity>> list(HttpServletRequest reqest, QueryParamEntity param) {
        ResponseMessage<PagerResult<DocumentdetailEntity>>xx = GenericEntityController.super.list(reqest, param);
        return xx;
    }

    @Override
    @OpenAPI
    public ResponseMessage<String> save(@RequestBody DocumentdetailEntity data) {
        return GenericEntityController.super.save(data);
    }

    @Override
    @OpenAPI
    public ResponseMessage updateByPrimaryKey(@RequestParam("id") String id, @RequestBody DocumentdetailEntity data) {
        return GenericEntityController.super.updateByPrimaryKey(id, data);
    }

    @RequestMapping(value = "/getDetail", method = RequestMethod.GET)
    @OpenAPI
    public ResponseMessage<String> getDocumentdetailModel(HttpServletRequest reqest, QueryParamEntity param) {

        return ResponseMessage.ok(JSONUtil.toJson(documentdetailService.selectDetail(param)));
    }
    
    @RequestMapping(value = "/updateDetail", method = RequestMethod.POST)
    @OpenAPI
    public ResponseMessage<String> updateDocumentdetailModel(@RequestBody DocumentdetailEntity data) {
		return documentdetailService.updateDetail(data);

    }

	@Override
	@OpenAPI
	public ResponseMessage<DocumentdetailEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody DocumentdetailEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}
    
    
}
