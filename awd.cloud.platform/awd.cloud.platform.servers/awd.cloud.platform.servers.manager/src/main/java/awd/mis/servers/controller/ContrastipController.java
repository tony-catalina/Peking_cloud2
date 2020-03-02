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
import awd.mis.servers.entity.ContrastipEntity;
import awd.mis.servers.service.ContrastipService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RefreshScope
@RequestMapping("/contrastip")
@AccessLogger("Contrastip")
@Api(value = "Contrastip", description = "")
public class ContrastipController implements GenericEntityController<ContrastipEntity, String, QueryParamEntity, ContrastipEntity> {

    private ContrastipService contrastipService;

    @Override
    public ContrastipEntity modelToEntity(ContrastipEntity model, ContrastipEntity entity) {
        return model;
    }

    @Autowired
    public void setContrastipService(ContrastipService contrastipService) {
        this.contrastipService = contrastipService;
    }

    @Override
    public CrudService<ContrastipEntity, String> getService() {
        return contrastipService;
    }

    @Override
    @OpenAPI
    @ApiOperation("自定义查询")
    @GetMapping
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<PagerResult<ContrastipEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
        return ResponseMessage.ok(contrastipService.selectPager(arg1));
    }

    @OpenAPI
    @ApiOperation("缓存手动")
    @GetMapping("/cached")
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<String> cached() {
        contrastipService.cached();
        return ResponseMessage.ok();
    }

    @Override
    @ApiOperation("新增")
    @PostMapping
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<String> save(@RequestBody ContrastipEntity data) {
        ResponseMessage<String> responseMessage = GenericEntityController.super.save(data);
        contrastipService.cached();
        return responseMessage;
    }

    @Override
    @ApiOperation("根据id更新")
    @PutMapping(path = {"/{id:.+}"})
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody ContrastipEntity data) {
        ResponseMessage responseMessage = GenericEntityController.super.updateByPrimaryKey(id, data);
        contrastipService.cached();
        return responseMessage;
    }

    @Override
    @PatchMapping
    @AccessLogger("{save_or_update}")
    @ApiOperation("保存数据,如果数据不存在则新增一条数据")
    @OpenAPI
    public ResponseMessage<String> saveOrUpdate(@RequestBody ContrastipEntity data) {
        ResponseMessage responseMessage = GenericEntityController.super.saveOrUpdate(data);
        contrastipService.cached();
        return responseMessage;
    }

    @Override
    @DeleteMapping(path = {"/{id:.+}"})
    @AccessLogger("{delete_by_primary_key}")
    @ApiOperation("根据ID删除数据")
    @ApiResponses({@ApiResponse(code = 200, message = "删除成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "要删除的数据不存在")})
    @OpenAPI
    public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
        ResponseMessage responseMessage = GenericEntityController.super.deleteByPrimaryKey(id);
        contrastipService.cached();
        return responseMessage;
    }

}
