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
import awd.mis.servers.entity.ZfingerEntity;
import awd.mis.servers.service.ZfingerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/zfinger")
@AccessLogger("Zfinger")
@Api(value = "Zfinger")
public class ZfingerController implements GenericEntityController<ZfingerEntity, String, QueryParamEntity, ZfingerEntity> {

    private ZfingerService zfingerService;

    @Override
    public ZfingerEntity modelToEntity(ZfingerEntity model, ZfingerEntity entity) {
        return model;
    }

    @Autowired
    public void setZfingerService(ZfingerService zfingerService) {
        this.zfingerService = zfingerService;
    }

    @Override
    public CrudService<ZfingerEntity, String> getService() {
        return zfingerService;
    }

    @Override
    @OpenAPI
    @ApiOperation("自定义查询")
    @GetMapping
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<PagerResult<ZfingerEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
        arg1.excludes("zwtx");
        return ResponseMessage.ok(zfingerService.selectPager(arg1));
    }

    @Override
    @ApiOperation("新增")
    @PostMapping
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> save(@RequestBody ZfingerEntity data) {
        return GenericEntityController.super.save(data);
    }

    @Override
    @ApiOperation("根据id更新")
    @PutMapping(path = {"/{id:.+}"})
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody ZfingerEntity data) {
        return GenericEntityController.super.updateByPrimaryKey(id, data);
    }

    @Override
    @PatchMapping
    @AccessLogger("{save_or_update}")
    @ApiOperation("保存数据,如果数据不存在则新增一条数据")
    @OpenAPI
    public ResponseMessage<String> saveOrUpdate(@RequestBody ZfingerEntity data) {
        return GenericEntityController.super.saveOrUpdate(data);
    }

    @Override
    @GetMapping(path = {"/{id:.+}"})
    @ApiOperation("根据主键查询数据")
    @AccessLogger("{get_by_id}")
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<ZfingerEntity> getByPrimaryKey(@PathVariable String id) {
        return GenericEntityController.super.getByPrimaryKey(id);
    }

    @Override
    @DeleteMapping(path = {"/{id:.+}"})
    @AccessLogger("{delete_by_primary_key}")
    @ApiOperation("根据ID删除数据")
    @ApiResponses({@ApiResponse(code = 200, message = "删除成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "要删除的数据不存在")})
    @OpenAPI
    public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
        return GenericEntityController.super.deleteByPrimaryKey(id);
    }

    /**
     * 循环查找表字段是否含有rybh，有的话就自动生成jbxxList 方法，没有就不生成
     */
    @GetMapping("/jbxxlist")
    @OpenAPI
    public ResponseMessage<PagerResult<Map<String, Object>>> getJbxxList(HttpServletRequest arg0, QueryParamEntity arg1) {
        ResponseMessage<PagerResult<Map<String, Object>>> list = zfingerService.jbxxlist(arg1);
        return list;
    }


}
