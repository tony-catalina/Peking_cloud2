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

import awd.mis.servers.entity.ZirisEntity;
import awd.mis.servers.service.ZirisService;
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
@RequestMapping("/ziris")
@AccessLogger("Ziris")
@Api(value = "Ziris", description = "在押人员虹膜信息")
public class ZirisController implements GenericEntityController<ZirisEntity, String, QueryParamEntity, ZirisEntity> {

    private ZirisService zirisService;

    @Override
    public ZirisEntity modelToEntity(ZirisEntity model, ZirisEntity entity) {
        return model;
    }

    @Autowired
    public void setZirisService(ZirisService zirisService) {
        this.zirisService = zirisService;
    }

    @Override
    public CrudService<ZirisEntity, String> getService() {
        return zirisService;
    }

    @Override
    @OpenAPI
    @ApiOperation("自定义查询")
    @GetMapping
    @HystrixCommand
    @ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
            @ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
    public ResponseMessage<PagerResult<ZirisEntity>> list(HttpServletRequest arg0, QueryParamEntity arg1) {
        arg1.excludes("hmtx");
        return ResponseMessage.ok(zirisService.selectPager(arg1));
    }

    @Override
    @PostMapping
    public ResponseMessage<String> save(@RequestBody ZirisEntity data) {
        return ResponseMessage.error("已暂停");
    }

    @Override
    @PutMapping(path = {"/{id:.+}"})
    public ResponseMessage updateByPrimaryKey(@PathVariable String id, @RequestBody ZirisEntity data) {
        return ResponseMessage.error("已暂停");
    }

    @Override
    @PatchMapping
    public ResponseMessage<String> saveOrUpdate(@RequestBody ZirisEntity data) {
        return ResponseMessage.error("已暂停");
    }

    @Override
    @GetMapping(path = {"/{id:.+}"})
    public ResponseMessage<ZirisEntity> getByPrimaryKey(@PathVariable String id) {
        return ResponseMessage.error("已暂停");
    }

    @Override
    @DeleteMapping(path = {"/{id:.+}"})
    public ResponseMessage<Integer> deleteByPrimaryKey(@PathVariable String id) {
        return ResponseMessage.error("已暂停");
    }

    /**
     * 循环查找表字段是否含有rybh，有的话就自动生成jbxxList 方法，没有就不生成
     */
    @GetMapping("/jbxxlist")
    @OpenAPI
    public ResponseMessage<PagerResult<Map<String, Object>>> getJbxxList(HttpServletRequest arg0, QueryParamEntity arg1) {
        ResponseMessage<PagerResult<Map<String, Object>>> list = zirisService.jbxxlist(arg1);
        return list;
    }


}
