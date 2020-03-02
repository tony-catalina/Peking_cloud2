/*
 * Copyright 2008 [rapid-framework], Inc. All rights reserved.
 * Website: http://www.rapid-framework.org.cn
 */

package awd.mis.servers.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import awd.mis.servers.entity.ZfaceEntity;
import awd.mis.servers.service.ZfaceService;
import awd.framework.common.controller.GenericEntityController;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.entity.PagerResult;
import awd.framework.common.entity.param.QueryParamEntity;
import awd.framework.common.service.api.CrudService;
import awd.framework.common.utils.OpenAPI;
import awd.framework.expands.logging.AccessLogger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RefreshScope
@RequestMapping("/zface")
@AccessLogger("Zface")
@Api(value = "Zface", description = "在押人员人脸信息")
public class ZfaceController implements GenericEntityController<ZfaceEntity, String, QueryParamEntity, ZfaceEntity> {

    private ZfaceService zfaceService;

    @Override
    public ZfaceEntity modelToEntity(ZfaceEntity model, ZfaceEntity entity) {
        return model;
    }

    @Autowired
    public void setZfaceService(ZfaceService zfaceService) {
        this.zfaceService = zfaceService;
    }

    @Override
    public CrudService<ZfaceEntity, String> getService() {
        return zfaceService;
    }

    @Override
    @OpenAPI
    @ApiOperation("自定义查询")
    @GetMapping
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<PagerResult<ZfaceEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
        arg1.excludes("rltx");
        return ResponseMessage.ok(zfaceService.selectPager(arg1));
    }

    @Override
    @ApiOperation("新增")
    @PostMapping
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<String> save(@RequestBody ZfaceEntity data) {
        return GenericEntityController.super.save(data);
    }

    @Override
    @ApiOperation("根据id更新")
    @PutMapping(path = {"/{id:.+}"})
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody ZfaceEntity data) {
        return GenericEntityController.super.updateByPrimaryKey(id, data);
    }

    @Override
    @PatchMapping
    @AccessLogger("{save_or_update}")
    @ApiOperation("保存数据,如果数据不存在则新增一条数据")
    @OpenAPI
    public ResponseMessage<String> saveOrUpdate(@RequestBody ZfaceEntity data) {
        return GenericEntityController.super.saveOrUpdate(data);
    }

    @Override
    @GetMapping(path = {"/{id:.+}"})
    @ApiOperation("根据主键查询数据")
    @AccessLogger("{get_by_id}")
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    @OpenAPI
    public ResponseMessage<ZfaceEntity> getByPrimaryKey(@PathVariable String id) {
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
        ResponseMessage<PagerResult<Map<String, Object>>> list = zfaceService.jbxxlist(arg1);
        return list;
    }


}
