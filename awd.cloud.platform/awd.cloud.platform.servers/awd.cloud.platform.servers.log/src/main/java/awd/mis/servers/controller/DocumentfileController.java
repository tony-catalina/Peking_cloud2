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
import awd.framework.common.utils.StringUtils;
import awd.framework.common.utils.file.FileUtils;
import awd.framework.expands.logging.AccessLogger;
import awd.mis.servers.entity.CloudfileEntity;
import awd.mis.servers.entity.DocumentfileEntity;
import awd.mis.servers.service.DocumentfileService;
import awd.mis.servers.utils.FastDFSFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


@RestController
@RefreshScope
@RequestMapping("/documentfile")
@AccessLogger("Documentfile")
@Api(value = "Documentfile", description = "公文文件")
public class DocumentfileController implements GenericEntityController<DocumentfileEntity, String, QueryParamEntity, DocumentfileEntity> {

    private DocumentfileService documentfileService;

    Logger logger = LoggerFactory.getLogger(DocumentfileController.class);

    @Override
    public DocumentfileEntity modelToEntity(DocumentfileEntity model, DocumentfileEntity entity) {
        return model;
    }

    @Autowired
    public void setDocumentfileService(DocumentfileService documentfileService) {
        this.documentfileService = documentfileService;
    }

    @Override
    public CrudService<DocumentfileEntity, String> getService() {
        return documentfileService;
    }


    @Override
    @OpenAPI
    public ResponseMessage<PagerResult<DocumentfileEntity>> list(HttpServletRequest reqest, QueryParamEntity param) {
        return GenericEntityController.super.list(reqest, param);
    }

    @RequestMapping(value = "/listExcludeFile", method = RequestMethod.GET)
    @ApiOperation("忽略文件查询")
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限")})
    @ResponseBody
    @OpenAPI
    public ResponseMessage<PagerResult<DocumentfileEntity>> listExcludeFile(HttpServletRequest reqest, QueryParamEntity param) {
        param.excludes("file");
        return GenericEntityController.super.list(reqest, param);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation("文件上传")
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限")})
    @ResponseBody
    @OpenAPI
    public Map<String, Object> upload(@RequestPart("file") MultipartFile file,
                                      @RequestParam("uuid") String uuid) throws IOException {

        Map<String, Object> result = new HashMap<>();
        String fileName = file.getOriginalFilename();
        String path = "";
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        InputStream inputStream = file.getInputStream();
        if (FileUtils.isFileTypeTrueForDocument(inputStream)) {
            byte[] file_buff = null;
            int len1 = 0;
            if (inputStream != null) {
                inputStream = file.getInputStream();
                len1 = inputStream.available();
                file_buff = new byte[len1];
                inputStream.read(file_buff);
                inputStream.close();
            }
            FastDFSFile fastfile = new FastDFSFile(fileName, file_buff, ext);
            try {
                DocumentfileEntity documentfileEntity = new DocumentfileEntity();
                documentfileEntity.setUuid(uuid);
                documentfileEntity.setFile(file.getBytes());
                path = documentfileService.saveOrUpdate(documentfileEntity, fastfile);

            } catch (Exception e) {
                logger.error("upload file Exception!", e);
                result.put("code", 400);
                result.put("data", "");
                result.put("msg", "文件保存失败！");
            }

            if (!StringUtils.isNullOrEmpty(path)) {
                result.put("code", 200);
                result.put("data", path);
                result.put("msg", "文件保存成功！");
            }
        } else {
            logger.error("upload file Exception,{}", "文件类型错误只支持JPEG(JPG)、PNG、PDF");
            result.put("code", 400);
            result.put("data", "");
            result.put("msg", "文件保存失败，文件类型错误只支持JPEG(JPG)、PNG、PDF");
        }


        return result;
    }

	@Override
	@OpenAPI
	public ResponseMessage<DocumentfileEntity> getByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.getByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
		return GenericEntityController.super.deleteByPrimaryKey(id);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> saveOrUpdate(@RequestBody DocumentfileEntity data) {
		return GenericEntityController.super.saveOrUpdate(data);
	}

	@Override
	@OpenAPI
	public ResponseMessage updateByPrimaryKey(@PathVariable String id,@RequestBody  DocumentfileEntity data) {
		return GenericEntityController.super.updateByPrimaryKey(id, data);
	}

	@Override
	@OpenAPI
	public ResponseMessage<String> save(@RequestBody DocumentfileEntity data) {
		return GenericEntityController.super.save(data);
	}
    
    

}
